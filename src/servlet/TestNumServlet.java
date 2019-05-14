package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Task_Dao;
import entity.Task;

/**
 * Servlet implementation class TestNumServlet
 */
@WebServlet("/TestNumServlet")
public class TestNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestNumServlet() {
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
		//System.out.println("OK");
		request.setCharacterEncoding("utf-8");
		String task_id = request.getParameter("task_id1");
		//System.out.println(task_id);
		Task t = new Task();
		Task_Dao t_dao = new Task_Dao();
		int TaskNo = 0;
		TaskNo = t_dao.SearchLastNum();//�����ݿ��ȡ��ǰ������
		//�ж���������Ƿ������ݿ���
		int enterTask = Integer.parseInt(task_id);
		if(enterTask<0) {
			System.out.println("�����ʽ�����д�");
			String message="�����������";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");
			
		}
		if(enterTask<=TaskNo&&enterTask>0) {
			System.out.println("���������ݿ���");
			request.getSession().setAttribute("task_id", task_id);
			response.sendRedirect("../AddTestFile.jsp");
			
		}else{
			System.out.println("���ⲻ�����ݿ���");
			String message="���ⲻ�����ݿ���";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");
			
		}
	}

}
