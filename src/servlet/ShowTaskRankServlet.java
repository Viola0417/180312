package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Log_Dao;
import dao.Task_Dao;
import entity.Log;
import entity.Task;

/**
 * Servlet implementation class ShowTaskRankServlet
 */
@WebServlet("/ShowTaskRankServlet")
public class ShowTaskRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTaskRankServlet() {
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
		System.out.println("����չʾ��Ŀ����");
		//���жϸ����Ƿ����
		//1.��Ŀ��������
		//2.��Ŀ��û��������
		//3.������Ŀ�����¼
		String task_id = null;
		request.setCharacterEncoding("utf-8");
		task_id=request.getParameter("task_id");
		//System.out.println("ѧ��Ҫ������ǣ�"+task_id+"��Ŀ");
		Task t = new Task();
		Task_Dao t_dao = new Task_Dao();
		Log_Dao l_dao = new Log_Dao();
		int TaskNo = 0;
		int res = 0;
		TaskNo = t_dao.SearchLastNum();//�����ݿ��ȡ��ǰ������
		//�ж���������Ƿ������ݿ���
		int enterTask = Integer.parseInt(task_id);
		if(enterTask<=TaskNo&&enterTask>0) {
			//System.out.println("Ҫ�鿴����Ŀ�����ݿ���");
			try {
				res = l_dao.CheckLogByTask(enterTask);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(res!=0) {
				//������м�¼
				List<Log> log_list = new ArrayList<Log>();
				try {
					log_list = l_dao.QueryByTaskNo(enterTask);
					for(Log l:log_list) {
						System.out.println(l.toString());
					}
					request.getSession().setAttribute("log_list", log_list);
					//String message = "Ϊʲô��������";
					//request.getSession().setAttribute("message", message);
					response.sendRedirect("../ShowTaskRankSuc.jsp");
					//request.getRequestDispatcher("/ShowTaskRankSuc.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				//����û��ѧ������
				String warning="����⻹û��ѧ������";
				request.getSession().setAttribute("warning", warning);
				response.sendRedirect("../ShowTaskRankFail.jsp");
				//request.getRequestDispatcher("../ShowTaskRankFail.jsp").forward(request, response);
			}
			

		}else {
			//���ⲻ�����ݿ���
			String warning="����⻹δ�����";
			request.getSession().setAttribute("warning", warning);
			request.getRequestDispatcher("../ShowTaskRankFail.jsp").forward(request, response);
		}
	}

}
