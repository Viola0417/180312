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
	
	//�ϴ�����
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
		//doGet(request, response);
		System.out.println("����ѧ���ϴ���������Ŀ�����ҰѱȶԵĽ�����log��");
		HttpSession  hs = request.getSession();
		int stu_id = (int) hs.getAttribute("stu_id");
		//HttpSession  hs = request.getSession();
		int task_id = (int) hs.getAttribute("task_id");
		System.out.println("��ǰѧ����ѧ����:"+stu_id+"�����������Ŀ�ǣ�"+task_id);
		
		
		//���ϴ�ѧ���������ļ�
		//����Ƿ�Ϊ��ý���ϴ�
		if(!ServletFileUpload.isMultipartContent(request)) {
			//���������ֹͣ
			PrintWriter writer = response.getWriter();
			writer.println("����");
			System.out.println("������enctype=multipart/form-data");
			writer.flush();
			return;
		}
		//�����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//�����ڴ��ٽ�ֵ-�����󽫲�����ʱ�ļ����洢����ʱĿ¼
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//������ʱ�洢Ŀ¼
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		//System.out.println("������ʱ�洢Ŀ¼");
		ServletFileUpload upload = new ServletFileUpload(factory);
		//��������ļ��ϴ�ֵ
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//�����������ֵ
		upload.setSizeMax(MAX_REQUEST_SIZE);
		//���Ĵ���
		upload.setHeaderEncoding("UTF-8");
		//������ʱ·�����洢�ϴ����ļ�
		//���·����Ե�ǰӦ�õ�Ŀ¼
		STU_DIRECTORY = String.valueOf(stu_id);
		TASK_DIRECTORY = String.valueOf(task_id);
		String uploadPath = request.getServletContext().getRealPath("./")+File.separator+STU_DIRECTORY+"_"+TASK_DIRECTORY;
		System.out.println("ѧ���ϴ��𰸵�·����:"+uploadPath);	
		//���Ŀ¼�����ھʹ���
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			//���������������ȡ�ļ�����
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			if(formItems != null && formItems.size() > 0) {
				//����������
				for(FileItem item:formItems) {
					//�����ڱ��е��ֶ�
					if(!item.isFormField()) {
						//String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + LIST_NAME;
						System.out.println(filePath);
						File storeFile = new File(filePath);
						//�ڿ���̨����ļ����ϴ�·��
						System.out.println(filePath);
						//�����ļ���Ӳ��
						item.write(storeFile);
						//.sendRedirect("../T_UploadAnswer.jsp");
					}
				}
			}
			
			
		} catch (Exception e) {
			response.sendRedirect("../AddAnswerFail.jsp");
		}
		
		//�ԱȲ��Լ���׼����ѧ���ϴ��Ĵ�
		String f_path_1 = request.getServletContext().getRealPath("./")+File.separator+TASK_DIRECTORY+File.separator+ANSWER;
		System.out.println("��׼���ļ���ַ��"+f_path_1);
		InputStream inputStream_1 = new FileInputStream(f_path_1);
		String f_path_2 = uploadPath + File.separator + LIST_NAME;
		System.out.println("ѧ���ϴ��𰸵�ַ:"+f_path_2);
		InputStream inputStream_2 = new FileInputStream(f_path_2);
		String suffix = "xlsx";
		int startRow = 0;
	
		//List<String[]> result = 
					//parser.parseExcel(inputStream, suffix, startRow);
		ParseTest xlsMain = new ParseTest();
		split s = new split();
		calculation c = new calculation();
		//Student student = null;
		List<String> standard_list =  xlsMain.readXls(inputStream_1,suffix,startRow);
		List<String> input_list = xlsMain.readXls(inputStream_2,suffix,startRow);
		int list_len = standard_list.size();
		//��ȷ��a=��ȷʶ����ĸ�������/ʶ����ĸ�������
		List<Integer> a_list = new ArrayList<Integer>();
		
		double precision = 0;
		double recall = 0;
		List<Double> precision_list = new ArrayList<Double>();//�������ÿһ�����ݵ���ȷ�� 
		List<Double> recall_list = new ArrayList<Double>();//�������ÿһ�����ݵ��ٻ���
		//��ʼ����׼ȷ��	P
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
		
		System.out.println("׼ȷ���ǣ�"+precision_list);
		System.out.println("�ٻ����ǣ�"+recall_list);
		//��������׼ȷ��P
		double P = 0;
		double sum = 0;
		for(int i=0;i<precision_list.size();i++) {
			sum = sum + precision_list.get(i);
		}
		P = sum / (double)precision_list.size();
		System.out.println("׼ȷ���ǣ�"+P);
		
		//���������ٻ���R
		double R = 0;
		sum = 0;
		for(int i=0;i<recall_list.size();i++) {
			sum = sum + recall_list.get(i);
		}
		R = sum / (double)recall_list.size();
		System.out.println("�ٻ����ǣ�"+R);
		
		//����F
		double F = 0;
		F = (R * P) / (R + P);
		System.out.println("F�ǣ�"+F);
		//System.out.println("ִ�н���");
		
		//�ѽ������log���ݿ�
		Log l = new Log();
		Log_Dao l_dao = new Log_Dao();
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
		String F_str = String.valueOf(F);
		String R_str = String.valueOf(R);
		String P_str = String.valueOf(P);
		request.getSession().setAttribute("F", F_str);
		request.getSession().setAttribute("R", F_str);
		request.getSession().setAttribute("P", F_str);
		request.getRequestDispatcher("../ShowRes.jsp").forward(request, response);;
		System.out.println("ִ�н���");
	}

}
