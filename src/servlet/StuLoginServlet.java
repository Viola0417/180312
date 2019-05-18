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

import dao.Student_Dao;
import dao.Task_Dao;
import dao.Teacher_Dao;
import entity.Student;
import entity.Task;
import entity.Teacher;
import util.DbUtil;
/**
 * Servlet implementation class StuLoginServlet
 */
@WebServlet("/StuLoginServlet")
public class StuLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//System.out.println("处理post请求");
		//Teacher t = new Teacher();
		//Connection con=null;
		try {
			//检测输入密码与数据库中密码是否一致
			Student_Dao s_dao = new Student_Dao();
			Student s_temp = new Student();
			String s_name,s_password;
			s_password=request.getParameter("s_password");
			s_name=request.getParameter("s_name");
			if(s_name!=null) {
				s_temp=s_dao.queryByName(s_name);
			}
			
			//System.out.println(s_temp.toString());
			String db_password=s_temp.getS_password();
			//System.out.println(db_password);
			//System.out.println("这个人的学号是："+s_temp.getS_id());
			//把学生学号存进叫stu_id的session，方便以后servlet使用
			request.getSession().setAttribute("stu_id",s_temp.getS_id());
			if(db_password==null) {
				System.out.println("这个用户名不存在");
				String message="这个用户不存在";
				request.getSession().setAttribute("message", message);
				response.sendRedirect("../Res.jsp");
				//request.getRequestDispatcher("../s_logFail.jsp").forward(request, response);
				}else if(db_password.equals(s_password)) {
					//System.out.println("密码一致");
					request.getSession().setAttribute("logStudent", s_temp);
					//跳转页面
					Task t = new Task();
					Task_Dao t_dao = new Task_Dao();
					List<Task> taskList = new ArrayList<Task>();
					try {
						taskList = t_dao.query();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					request.getSession().setAttribute("tasklist", taskList);
					response.sendRedirect("../s_func.jsp");
					//request.getRequestDispatcher("../s_func.jsp").forward(request, response);
					}else {
						//System.out.println("密码错误");
						String message="用户名与密码不匹配";
						request.getSession().setAttribute("message", message);
						response.sendRedirect("../Res.jsp");
					}
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}