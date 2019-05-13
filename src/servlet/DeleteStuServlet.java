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
import entity.Student;

/**
 * Servlet implementation class DeleteStuServlet
 */
@WebServlet("/DeleteStuServlet")
public class DeleteStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStuServlet() {
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
		String s_id = null;
		request.setCharacterEncoding("utf-8");
		s_id=request.getParameter("s_id");
		System.out.println("要被删除掉的学生的学号是："+s_id);
		//判断这个学号是否存在
		Student s = new Student();
		Student_Dao s_dao = new Student_Dao();
		Log_Dao l_dao = new Log_Dao();
		int res = 0;
		int stu_id = Integer.parseInt(s_id);
		try {
			//res=1学生存在；res=0学生不存在
			res = s_dao.CheckLogByStu(stu_id);
			if(res==0) {
				//学生不存在的时候
				String message="该学生不在本系统";
				request.getSession().setAttribute("message", message);
				response.sendRedirect("../Res.jsp");
				//request.getRequestDispatcher("../Res.jsp").forward(request, response);
			}else if(res==1) {
				//学生存在的时候
				s_dao.delStudent(stu_id);
				l_dao.delLog(stu_id);
				String message="该学生极其做题记录已经成功删除";
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
