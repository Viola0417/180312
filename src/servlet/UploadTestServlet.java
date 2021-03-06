package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entity.Student;
import entity.Task;
import service.ReadExcel;
import dao.Student_Dao;
import dao.Task_Dao;
/**
 * Servlet implementation class UploadFileServlet
 */
@WebServlet("/UploadTestServlet")
public class UploadTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	//private static final String UPLOAD_DIRECTORY = "List";
	private static final String LIST_NAME = "test";
	
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
		//doGet(request, response);
		HttpSession  hs = request.getSession();
		String task_id = (String) hs.getAttribute("task_id");
		System.out.println("task_id是：！！！！！！"+task_id);
		//System.out.println("开始新建文件夹");
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
		String uploadPath = request.getServletContext().getRealPath("./")+File.separator+task_id;
		
		System.out.println(uploadPath);
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
						System.out.println("上传的文件名是："+fileName);
						int len = fileName.length();
						String suffix_str = fileName.substring(len-4);
						System.out.println("后缀是："+suffix_str);
						if(!suffix_str.equals("xlsx")) {
							String message="上传测试集文件失败，只接收.xlsx文件";
							request.getSession().setAttribute("message", message);
							response.sendRedirect("../Res.jsp");
						}else {
							String filePath = uploadPath + File.separator + LIST_NAME;
							System.out.println(filePath);
							File storeFile = new File(filePath);
							//在控制台输出文件的上传路径
							System.out.println(filePath);
							//保存文件到硬盘
							item.write(storeFile);
							response.sendRedirect("../T_UploadAnswer.jsp");
						}
					}
				}
			}
			
			
		} catch (Exception e) {
			String message="该题目上传失败";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");
		}
			//跳转到message.jsp
		//request.getRequestDispatcher("/uptaskmsg.jsp").forward(request, response);
		//request.getServletContext().getRequestDispatcher("/uptaskmsg.jsp").forward(request,response);
		}
	}

