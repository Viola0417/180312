package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Student_Dao;
import entity.Student;

/**
 * Servlet implementation class StuUpdatePwdServlet
 */
@WebServlet("/StuUpdatePwdServlet")
public class StuUpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuUpdatePwdServlet() {
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
		//System.out.println("学生修改密码");
		Student s = new Student();
		Student_Dao s_dao = new Student_Dao();
		request.setCharacterEncoding("utf-8");
		String origin_password = request.getParameter("origin_password");
		String new_password = request.getParameter("new_password");
		
		if(origin_password==""){
			String message="输入原密码不能为空";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");			
		}else if(new_password==""){
			String message="输入新密码不能为空";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");	
		}else {
			System.out.println("原来密码是："+origin_password+"新密码是："+new_password);
			//先根据学号检查原来的密码输入是否正确
			//获取学生学号
			HttpSession  hs = request.getSession();
			int stu_id = (int) hs.getAttribute("stu_id");
			try {
				s = s_dao.query(stu_id);
				String db_pwd = s.getS_password();
				if(db_pwd.equals(origin_password)) {
					//密码输入正确
					//再根据学号更新新的密码
					s = new Student();
					s.setS_id(stu_id);
					s.setS_password(new_password);
					s_dao.updateStudent(s);
					String message="已经成功更新";
					request.getSession().setAttribute("message", message);
					response.sendRedirect("../Res.jsp");
					//request.getRequestDispatcher("../s_UpdatePwdSuc.jsp").forward(request, response);
				}else {
					String message="原密码输入错误，请返回重新输入";
					request.getSession().setAttribute("message", message);
					response.sendRedirect("../Res.jsp");
					//request.getRequestDispatcher("../s_UpdatePwdFail.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
