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
		//System.out.println("学生准备做答题目");
		String task_id =request.getParameter("task_id2");
		request.setCharacterEncoding("utf-8");
		if(task_id=="") {
			System.out.println("得到的值为空");
			String message="输入题号不能为空";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");
			//return;
		}else {
			Task t = new Task();
			Task_Dao t_dao = new Task_Dao();
			int TaskNo = 0;
			TaskNo = t_dao.SearchLastNum();//在数据库获取当前题的题号
			//判断输入的题是否在数据库中
			int enterTask = Integer.parseInt(task_id);
			if(enterTask<=TaskNo&&enterTask>0) {
				//System.out.println("要做答的题目在数据库中");
				//把该题目存入session
				request.getSession().setAttribute("task_id",enterTask);
				response.sendRedirect("../s_addalgo.jsp");
				
			}else {
				//System.out.println("要做答的题目不在数据库中");
				String message="题目号码不正确";
				request.getSession().setAttribute("message", message);
				response.sendRedirect("../Res.jsp");
				//request.getRequestDispatcher("../downloadFail.jsp").forward(request, response);
			}
		
		}
		//System.out.println("学生要做答的是："+task_id+"题目");
	}

}
