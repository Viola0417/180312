package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Task;
import dao.Task_Dao;

/**
 * Servlet implementation class UploadTaskDesServlet
 */
@WebServlet("/UploadTaskDesServlet")
public class UploadTaskDesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadTaskDesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    String title,description,kind;
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
		request.setCharacterEncoding("utf-8");
		//System.out.println("处理post请求");
		title=request.getParameter("title_name");
		description=request.getParameter("description_name");
		kind=request.getParameter("checkbox");
		//System.out.println("kind:"+kind);
		//System.out.println(title);
		//System.out.println(description);
		
		Task t = new Task();
		Task_Dao t_dao = new Task_Dao();
		t.setT_description(description);
		t.setT_title(title);
		t.setT_kind(kind);
		try {
			t_dao.addTask(t);
			response.sendRedirect("../AddTask.jsp");
			//request.getRequestDispatcher("../AddTask.jsp").forward(request, response);;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		request.getSession().setAttribute("title", title);
		request.getSession().setAttribute("description", description);
		response.sendRedirect("../AddTask.jsp");
		*/
		System.out.println("添加题目结束");
	}

}
