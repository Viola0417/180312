package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Log_Dao;
import dao.Student_Dao;
import dao.Task_Dao;
import entity.Student;

/**
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet(name = "DeleteTask", urlPatterns = { "/DeleteTask" })
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTaskServlet() {
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
		String t_id = request.getParameter("t_id");
		request.setCharacterEncoding("utf-8");
		if(t_id=="") {
			//System.out.println("得到的值为空");
			String message="输入题号不能为空";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");
			//return;
		}else {
			Task_Dao t_dao = new Task_Dao();
			Log_Dao l_dao = new Log_Dao();
			int res = 0;
			int task_id = Integer.parseInt(t_id);
			try {
				//res=1题目存在；res=0题目不存在
				res = t_dao.CheckLogByTask(task_id);
				if(res==0) {
					//题目不存在的时候
					String message="该题目未被添加过";
					request.getSession().setAttribute("message", message);
					response.sendRedirect("../Res.jsp");
					//request.getRequestDispatcher("../Res.jsp").forward(request, response);
				}else if(res==1) {
					//题目存在的时候
					t_dao.delTask(task_id);
					l_dao.delLogbyTask(task_id);
					String message="该题目以及关于其的做题记录已经成功删除";
					request.getSession().setAttribute("message", message);
					response.sendRedirect("../Res.jsp");
					//request.getRequestDispatcher("../Res.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}	

}
