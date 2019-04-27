package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entity.Task;
import dao.Task_Dao;

/**
 * Servlet implementation class UploadTaskDesServlet
 */
@WebServlet("/T_UploadAnswerServlet")
public class T_UploadAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public T_UploadAnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	private static final String LIST_NAME = "answer";
	
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
		System.out.println("��ʦ�ϴ����ļ�");
		//Task t = new Task();
		Task_Dao t_dao = new Task_Dao();
		int TaskNo = 0;
		TaskNo = t_dao.SearchLastNum();//�����ݿ��ȡ��ǰ������
		String TaskStr = String.valueOf(TaskNo);
		System.out.println(TaskNo);
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
		String uploadPath = request.getServletContext().getRealPath("./")+File.separator+TaskStr;
		
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
						//String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + LIST_NAME;
						System.out.println(filePath);
						File storeFile = new File(filePath);
						//�ڿ���̨����ļ����ϴ�·��
						System.out.println(filePath);
						//�����ļ���Ӳ��
						item.write(storeFile);
						response.sendRedirect("../AddTaskOK.jsp");
					}
				}
			}
			
			
		} catch (Exception e) {
			response.sendRedirect("../AddTestFail.jsp");
		}
			//��ת��message.jsp
		//request.getRequestDispatcher("/uptaskmsg.jsp").forward(request, response);
		//request.getServletContext().getRequestDispatcher("/uptaskmsg.jsp").forward(request,response);
		}

}
