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
	
	//�ϴ�����
	private static final int MEMORY_THRESHOLD = 1024*1024*3;//3MB
	private static final int MAX_FILE_SIZE = 1024*1024*40;//40MB
	private static final int MAX_REQUEST_SIZE = 1024*1024*50;//50MB
	//�ϴ����ݼ������ļ�
	
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
		//����Ƿ�Ϊ��ý���ϴ�
		if(!ServletFileUpload.isMultipartContent(request)) {
			//���������ֹͣ
			PrintWriter writer = response.getWriter();
			writer.println("����");
			//System.out.println("������enctype=multipart/form-data");
			writer.flush();
			return;
		}
		//�����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//�����ڴ��ٽ�ֵ-�����󽫲�����ʱ�ļ����洢����ʱĿ¼
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//������ʱ�洢Ŀ¼
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		System.out.println("������ʱ�洢Ŀ¼");
		ServletFileUpload upload = new ServletFileUpload(factory);
		//��������ļ��ϴ�ֵ
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//�����������ֵ
		upload.setSizeMax(MAX_REQUEST_SIZE);
		//���Ĵ���
		upload.setHeaderEncoding("UTF-8");
		//������ʱ·�����洢�ϴ����ļ�
		//���·����Ե�ǰӦ�õ�Ŀ¼
		String uploadPath = request.getServletContext().getRealPath("./")+File.separator+UPLOAD_DIRECTORY;
		
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
						String filePath = uploadPath + File.separator + LIST_NAME;
						//System.out.println(filePath);
						File storeFile = new File(filePath);
						//�ڿ���̨����ļ����ϴ�·��
						//System.out.println(filePath);
						//�����ļ���Ӳ��
						item.write(storeFile);
						request.getSession().setAttribute("message", "�ļ��ϴ��ɹ�");
						//�������ݿ�
						//System.out.println("����Ҫ��ʼ�������ݿ���");
						Student_Dao s_dao = new Student_Dao();
						InputStream inputStream = new FileInputStream(filePath);
						String suffix = "xlsx";
						int startRow = 1;
						
						ReadExcel xlsMain = new ReadExcel();
						List<Student> list = xlsMain.readXls(inputStream, suffix, startRow);
						
						//System.out.println("ִ�н���");
						
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
			request.getSession().setAttribute("message", "���ϴ�.xlsxΪ��׺���ļ�");
		}
			//��ת��message.jsp
		response.sendRedirect("../Res.jsp");
		//request.getServletContext().getRequestDispatcher("/upfilemsg.jsp").forward(request,response);
		}
	}

