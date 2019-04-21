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

import dao.Task_Dao;
import entity.Task;
/**
 * Servlet implementation class PrintTaskServlet
 */
@WebServlet("/PrintTaskServlet")
public class PrintTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintTaskServlet() {
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
		System.out.println("现在要开始查找task");
		Task t = new Task();
		Task_Dao t_dao = new Task_Dao();
		List<Task> taskList = new ArrayList<Task>();
		try {
			taskList = t_dao.query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Task t2:taskList) {
			System.out.println(t2.toString());
		}
		System.out.println("task查找结束");
		//doGet(request, response);
		request.setAttribute("tasklist", taskList);
		request.getRequestDispatcher("/TaskIndex.jsp").forward(request, response);
	}

}
