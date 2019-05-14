package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.Student_Dao;
import entity.Student;
import service.ReadExcel;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    //public UploadServlet() {
      //  super();
        // TODO Auto-generated constructor stub
    //}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final String UPLOAD_DIRECTORY = "List";
	private static final String LIST_NAME = "student_list";
	
	//上传配置
	private static final int MEMORY_THRESHOLD = 1024*1024*3;//3MB
	private static final int MAX_FILE_SIZE = 1024*1024*40;//40MB
	private static final int MAX_REQUEST_SIZE = 1024*1024*50;//50MB
	//上传数据及保存文件
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("____________________________________________");
		//检测是否为多媒体上传
		if(!ServletFileUpload.isMultipartContent(request)) {
			//如果不是则停止
			PrintWriter writer = response.getWriter();
			writer.println("错误！");
			//System.out.println("不包含enctype=multipart/form-data");
			writer.flush();
			return;
		}
		//配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置内存临界值-超过后将产生临时文件并存储于临时目录
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		System.out.println("设置临时存储目录");
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//设置最大请求值
		upload.setSizeMax(MAX_REQUEST_SIZE);
		//中文处理
		upload.setHeaderEncoding("UTF-8");
		//构造临时路径来存储上传的文件
		//这个路径相对当前应用的目录
		String uploadPath = request.getServletContext().getRealPath("./")+File.separator+UPLOAD_DIRECTORY;
		
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
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + LIST_NAME;
						//System.out.println(filePath);
						File storeFile = new File(filePath);
						//在控制台输出文件的上传路径
						//System.out.println(filePath);
						//保存文件到硬盘
						item.write(storeFile);
						request.getSession().setAttribute("message", "文件上传成功");
						//导入数据库
						//System.out.println("现在要开始导入数据库了");
						Student_Dao s_dao = new Student_Dao();
						InputStream inputStream = new FileInputStream(filePath);
						String suffix = "xlsx";
						int startRow = 1;
						
						ReadExcel xlsMain = new ReadExcel();
						List<Student> list = xlsMain.readXls(inputStream, suffix, startRow);
						
						//System.out.println("执行结束");
						
						for(Student s:list) {
							//System.out.println(s.toString());
							try {
								s_dao.addStudent(s);
							}catch(SQLException e) {
								e.printStackTrace();
							}
						}
						
					}
				}
			}
			
			
		} catch (Exception e) {
			request.getSession().setAttribute("message", "错误信息："+e.getMessage());
		}
			//跳转到message.jsp
		response.sendRedirect("../Res.jsp");
		//request.getServletContext().getRequestDispatcher("/upfilemsg.jsp").forward(request,response);
		}
	}

