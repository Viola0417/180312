package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.calculation;
import service.split;
import service.writeExcel;
import service.ParseTest;
import dao.Log_Dao;
import entity.Log;

/**
 * Servlet implementation class UploadAnswerServlet
 */
@WebServlet("/UploadAnswerServlet")
public class UploadAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadAnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static String STU_DIRECTORY = null;
    private static String TASK_DIRECTORY = null;
	private static final String LIST_NAME = "Answer";
	private static final String ANSWER = "answer";
	
	//上传配置
	private static final int MEMORY_THRESHOLD = 1024*1024*3;//3MB
	private static final int MAX_FILE_SIZE = 1024*1024*40;//40MB
	private static final int MAX_REQUEST_SIZE = 1024*1024*50;//50MB

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("处理学生上传上来的题目，并且把比对的结果存进log表");
		HttpSession  hs = request.getSession();
		
		int stu_id = (int) hs.getAttribute("stu_id");
		int b = stu_id % 100;
		//HttpSession  hs = request.getSession();
		int task_id = (int) hs.getAttribute("task_id");
		String algo = (String) hs.getAttribute("algo");
		String description = (String) hs.getAttribute("description");
		System.out.println(algo+"   "+description);
		//System.out.println("当前学生的学号是:"+stu_id+"，他做答的题目是："+task_id);
		
		//根据学号生成新的answer文件
		ServletContext context = this.getServletContext();
		String test_file_path = context.getRealPath(task_id+"/answer");
		File file = new File(test_file_path);
		if(file.exists()) {
			//System.out.println("文件存在");
			//String[] names={"answer.xlsx"};
	       
	      //把原有的test文件打乱顺序
	        //+++++++++++++++++++++++++++++++++++++++++++++==
			//System.out.println("学生上传答案地址:"+f_path_2);
			InputStream inputStream = new FileInputStream(test_file_path);
			String suffix = "xlsx";
			int startRow = 0;
		
			//List<String[]> result = 
						//parser.parseExcel(inputStream, suffix, startRow);
			ParseTest xlsMain = new ParseTest();
			//split s = new split();
			calculation c = new calculation();
			//Student student = null;
			List<String> rvs_list = new ArrayList<String>();
			String rvs_path =  request.getServletContext().getRealPath("./")+File.separator+stu_id+"_"+task_id+"_answer";
			System.out.println(rvs_path);
			
			List<String> standard_list =  xlsMain.readXls(inputStream,suffix,startRow);
			System.out.println("从原答案读出的长度为："+standard_list.size()+"内容是："+standard_list);
			if(b==1 || b==0) {
				rvs_list = standard_list;
			}else {
				rvs_list = c.reverse(standard_list, stu_id, b);
			}
			
			writeExcel w = new writeExcel();
			try {
				w.we(rvs_list,rvs_path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("结束");
		
		//先上传学生的做答文件
		//检测是否为多媒体上传
		if(!ServletFileUpload.isMultipartContent(request)) {
			//如果不是则停止
			PrintWriter writer = response.getWriter();
			writer.println("错误！");
			System.out.println("不包含enctype=multipart/form-data");
			writer.flush();
			return;
		}
		//配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置内存临界值-超过后将产生临时文件并存储于临时目录
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		//System.out.println("设置临时存储目录");
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//设置最大请求值
		upload.setSizeMax(MAX_REQUEST_SIZE);
		//中文处理
		upload.setHeaderEncoding("UTF-8");
		//构造临时路径来存储上传的文件
		//这个路径相对当前应用的目录
		STU_DIRECTORY = String.valueOf(stu_id);
		TASK_DIRECTORY = String.valueOf(task_id);
		String uploadPath = request.getServletContext().getRealPath("./")+File.separator+STU_DIRECTORY+"_"+TASK_DIRECTORY;
		//System.out.println("学生上传答案的路径是:"+uploadPath);	
		//如果目录不存在就创建
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			//解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			if(formItems != null && formItems.size() > 0) {
				//迭代表单数据
				for(FileItem item:formItems) {
					//处理不在表单中的字段
					if(!item.isFormField()) {
						//String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + LIST_NAME;
						//System.out.println(filePath);
						File storeFile = new File(filePath);
						//在控制台输出文件的上传路径
						//System.out.println(filePath);
						//保存文件到硬盘
						item.write(storeFile);
						//.sendRedirect("../T_UploadAnswer.jsp");
					}
				}
			}
			
			
		} catch (Exception e) {
			response.sendRedirect("../AddAnswerFail.jsp");
		}
		
		//对比测试集标准答案与学生上传的答案
		/*
		String f_path_1 = request.getServletContext().getRealPath("./")+File.separator+TASK_DIRECTORY+File.separator+ANSWER;
		System.out.println("标准答案文件地址："+f_path_1);
		InputStream inputStream_1 = new FileInputStream(f_path_1);
		*/
		InputStream inputStream_1 = new FileInputStream(rvs_path);
		String f_path_2 = uploadPath + File.separator + LIST_NAME;
		//System.out.println("学生上传答案地址:"+f_path_2);
		InputStream inputStream_2 = new FileInputStream(f_path_2);
		suffix = "xlsx";
		startRow = 0;
	
		//List<String[]> result = 
					//parser.parseExcel(inputStream, suffix, startRow);
		xlsMain = new ParseTest();
		split s = new split();
		//Student student = null;
		standard_list =  xlsMain.readXls(inputStream_1,suffix,startRow);
		List<String> input_list = xlsMain.readXls(inputStream_2,suffix,startRow);
		int list_len = standard_list.size();
		System.out.println("list_len是："+list_len);
		//正确率a=正确识别出的个体总数/识别出的个体总数
		List<Integer> a_list = new ArrayList<Integer>();
		
		double precision = 0;
		double recall = 0;
		List<Double> precision_list = new ArrayList<Double>();//用来存放每一个数据的正确率 
		List<Double> recall_list = new ArrayList<Double>();//用来存放每一个数据的召回率
		//开始计算准确率	P
		for(int i=0;i<list_len;i++) {
			String standard_str = standard_list.get(i);
			String input_str = input_list.get(i);
			Vector standard_v = s.splitStr(standard_str);
			Vector input_v = s.splitStr(input_str);
			precision = c.Cal_precision(standard_v, input_v);
			recall = c.Cal_recall(standard_v, input_v);
			recall_list.add(recall);
			precision_list.add(precision);
		}
		
		//System.out.println("准确率是："+precision_list);
		//System.out.println("召回率是："+recall_list);
		//计算最后的准确率P
		double P = 0;
		double sum = 0;
		for(int i=0;i<precision_list.size();i++) {
			sum = sum + precision_list.get(i);
		}
		P = sum / (double)precision_list.size();
		//System.out.println("准确率是："+P);
		
		//计算最后的召回率R
		double R = 0;
		sum = 0;
		for(int i=0;i<recall_list.size();i++) {
			sum = sum + recall_list.get(i);
		}
		R = sum / (double)recall_list.size();
		//System.out.println("召回率是："+R);
		
		//计算F
		double F = 0;
		F = (R * P) / (R + P);
		if(R+P==0) {
			F=0;
		}
		//System.out.println("F是："+F);
		//System.out.println("执行结束");
		
		//把结果插入log数据库
		Log l = new Log();
		Log_Dao l_dao = new Log_Dao();
		l.setAlgo(algo);
		l.setDescription(description);
		l.setStu_id(stu_id);
		l.setTask_id(task_id);
		l.setF(F);
		l.setP(P);
		l.setR(R);
		try {
			l_dao.addLog(l);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		try {
			//得到新添加记录的所有信息
			l = l_dao.CheckLastLog();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String F_str = String.valueOf(F);
		//System.out.println("F_str是："+F_str);
		String R_str = String.valueOf(R);
		String P_str = String.valueOf(P);
		String FF = (String) hs.getAttribute("F");
		//System.out.println("原来的F是:"+FF);
		hs.removeAttribute("F");
		hs.removeAttribute("R");
		hs.removeAttribute("P");
		request.getSession().setAttribute("F", F_str);
		FF = (String)hs.getAttribute("F");
		//System.out.println("现在的F是:"+FF);
		request.getSession().setAttribute("R", R_str);
		request.getSession().setAttribute("P", P_str);
		
		//System.out.println("执行结束");
		//要得到学生的排名
		//现在本题有多少条记录
		try {
			int log_num = l_dao.CheckLogByTask(task_id);
			//System.out.println("现在这道题有"+log_num+"条记录");
			String log_num_str = String.valueOf(log_num);
			hs.removeAttribute("log_num");
			request.getSession().setAttribute("log_num", log_num_str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//现在有多少人做过这道题
		try {
			int log_dis_num = l_dao.CheckDisLogByTask(task_id);
			//System.out.println("现在这道题有"+log_dis_num+"个学生做答过");
			String log_dis_num_str = String.valueOf(log_dis_num);
			hs.removeAttribute("log_dis_num");
			request.getSession().setAttribute("log_dis_num", log_dis_num_str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//计算现在的结果在所有记录中排第几
		try {
			//System.out.println("l的各个值"+l.getF()+l.getTask_id()+l.getStu_id());
			int log_rank = c.Cal_Log_Rank(l);
			//System.out.println("在所有做答本题的log记录中排名为："+log_rank);
			String log_rank_str = String.valueOf(log_rank);
			hs.removeAttribute("log_rank");
			request.getSession().setAttribute("log_rank", log_rank_str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int stu_rank = c.Cal_Stu_Rank(l);
			//System.out.println("在所有做答本题的学生记录中排名为："+stu_rank);
			String stu_rank_str = String.valueOf(stu_rank);
			hs.removeAttribute("stu_rank");
			request.getSession().setAttribute("stu_rank", stu_rank_str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("../ShowRes.jsp");
		//request.getRequestDispatcher("../ShowRes.jsp").forward(request, response);
		//System.out.println("执行结束");
		}
	}
}
