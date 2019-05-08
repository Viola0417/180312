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
		System.out.println("ѧ��׼��������Ŀ");
		String task_id = null;
		request.setCharacterEncoding("utf-8");
		task_id=request.getParameter("task_id2");
		//System.out.println("ѧ��Ҫ������ǣ�"+task_id+"��Ŀ");
		Task t = new Task();
		Task_Dao t_dao = new Task_Dao();
		int TaskNo = 0;
		TaskNo = t_dao.SearchLastNum();//�����ݿ��ȡ��ǰ������
		//�ж���������Ƿ������ݿ���
		int enterTask = Integer.parseInt(task_id);
		if(enterTask<=TaskNo&&enterTask>0) {
			System.out.println("Ҫ�������Ŀ�����ݿ���");
			//�Ѹ���Ŀ����session
			request.getSession().setAttribute("task_id",enterTask);
			response.sendRedirect("../s_addalgo.jsp");
			
		}else {
			System.out.println("Ҫ�������Ŀ�������ݿ���");
			String warning="��Ŀ���벻��ȷ";
			request.getSession().setAttribute("warning", warning);
			response.sendRedirect("../downloadFail.jsp");
			//request.getRequestDispatcher("../downloadFail.jsp").forward(request, response);
		}
	}

}
