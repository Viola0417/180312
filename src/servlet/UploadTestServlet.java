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
		HttpSession  hs = request.getSession();
		String task_id = (String) hs.getAttribute("task_id");
		System.out.println("task_id�ǣ�������������"+task_id);
		//System.out.println("��ʼ�½��ļ���");
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
		String uploadPath = request.getServletContext().getRealPath("./")+File.separator+task_id;
		
		System.out.println(uploadPath);
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
						String fileName = new File(item.getName()).getName();
						System.out.println("�ϴ����ļ����ǣ�"+fileName);
						int len = fileName.length();
						String suffix_str = fileName.substring(len-4);
						System.out.println("��׺�ǣ�"+suffix_str);
						if(!suffix_str.equals("xlsx")) {
							String message="�ϴ����Լ��ļ�ʧ�ܣ�ֻ����.xlsx�ļ�";
							request.getSession().setAttribute("message", message);
							response.sendRedirect("../Res.jsp");
						}else {
							String filePath = uploadPath + File.separator + LIST_NAME;
							System.out.println(filePath);
							File storeFile = new File(filePath);
							//�ڿ���̨����ļ����ϴ�·��
							System.out.println(filePath);
							//�����ļ���Ӳ��
							item.write(storeFile);
							response.sendRedirect("../T_UploadAnswer.jsp");
						}
					}
				}
			}
			
			
		} catch (Exception e) {
			String message="����Ŀ�ϴ�ʧ��";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");
		}
			//��ת��message.jsp
		//request.getRequestDispatcher("/uptaskmsg.jsp").forward(request, response);
		//request.getServletContext().getRequestDispatcher("/uptaskmsg.jsp").forward(request,response);
		}
	}

