package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Task_Dao;
import entity.Task;

/**
 * Servlet implementation class CompleteTaskServlet
 */
@WebServlet("/CompleteTaskServlet")
public class CompleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteTaskServlet() {
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
		//System.out.println("ѧ��׼��������Ŀ");
		String task_id =request.getParameter("task_id2");
		request.setCharacterEncoding("utf-8");
		if(task_id=="") {
			System.out.println("�õ���ֵΪ��");
			String message="������Ų���Ϊ��";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");
			//return;
		}else {
			Task t = new Task();
			Task_Dao t_dao = new Task_Dao();
			int TaskNo = 0;
			int res = 0;
			int enterTask = Integer.parseInt(task_id);
			try {
				res = t_dao.CheckLogByTask(enterTask);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TaskNo = t_dao.SearchLastNum();//�����ݿ��ȡ��ǰ������
			//�ж���������Ƿ������ݿ���
			//int enterTask = Integer.parseInt(task_id);
			if(res==1) {
				//System.out.println("Ҫ�������Ŀ�����ݿ���");
				//�Ѹ���Ŀ����session
				request.getSession().setAttribute("task_id",enterTask);
				response.sendRedirect("../s_addalgo.jsp");
				
			}else {
				//System.out.println("Ҫ�������Ŀ�������ݿ���");
				String message="��Ŀ���벻��ȷ";
				request.getSession().setAttribute("message", message);
				response.sendRedirect("../Res.jsp");
				//request.getRequestDispatcher("../downloadFail.jsp").forward(request, response);
			}
		
		}
		//System.out.println("ѧ��Ҫ������ǣ�"+task_id+"��Ŀ");
	}

}
