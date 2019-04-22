package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Task_Dao;
import entity.Task;

/**
 * Servlet implementation class DownloadTaskServlet
 */
@WebServlet("/DownloadTaskServlet")
public class DownloadTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String task_id = null;
		//String filename = null;
		System.out.println("׼������task��");
		request.setCharacterEncoding("utf-8");
		//System.out.println("����post����");
		task_id=request.getParameter("task_id");
		System.out.println(task_id);
		Task t = new Task();
		Task_Dao t_dao = new Task_Dao();
		int TaskNo = 0;
		TaskNo = t_dao.SearchLastNum();//�����ݿ��ȡ��ǰ������
		//�ж���������Ƿ������ݿ���
		int enterTask = Integer.parseInt(task_id);
		if(enterTask<=TaskNo&&enterTask>0) {
			System.out.println("����������ݿ��У�׼����ʼ�����ļ�");
			//�����ļ�MIME����
			response.setContentType(getServletContext().getMimeType(task_id));
			//����Content-Disposition
			response.setHeader("Content-Disposition", "attachment;filename="+task_id);
			
			//��ȡҪ���ص��ļ��ľ���·��
			ServletContext context = this.getServletContext();
			//String tryFileName = context.getRealPath("./");
			//System.out.println(tryFileName);

			String fullFileName = context.getRealPath(task_id+"/train");
			System.out.println(fullFileName);
			
			//������Ϊ��Ŀ�ļ��������ָ�������
			InputStream is = new FileInputStream(fullFileName);
			ServletOutputStream os = response.getOutputStream();
			
			//���û�����   is.read()���ļ�����ʱ����-1
			int len = -1;
			byte[] b = new byte[1024];
			while((len=is.read(b))!=-1) {
				os.write(b,0,len);
			}
			//�ر���
			is.close();
			os.close();
			String message = "��Ŀ�Ѿ����سɹ�";
			request.getSession().setAttribute("message", message);
			request.getRequestDispatcher("../downloadSuc.jsp").forward(request, response);
		}else {
			System.out.println("����ⲻ�����ݿ���");
			String warning="��Ŀ���벻��ȷ";
			request.getSession().setAttribute("warning", warning);
			request.getRequestDispatcher("../downloadFail.jsp").forward(request, response);
		}
	}

}
