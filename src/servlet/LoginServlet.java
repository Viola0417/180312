package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Task_Dao;
import dao.Teacher_Dao;
import entity.Task;
import entity.Teacher;
import util.DbUtil;
/**
 * Servlet implementation class HelloServlet
 */
//@WebServlet("/HelloServlet")
public class LoginServlet extends HttpServlet {
	DbUtil db=new DbUtil();
	Teacher_Dao teacherDao = new Teacher_Dao();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("����get����");
	    doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
			//����������������ݿ��������Ƿ�һ��
			Teacher_Dao t_dao = new Teacher_Dao();
			Teacher t_temp = new Teacher();
			String t_name,t_password;
			t_password=request.getParameter("t_password");
			t_name=request.getParameter("t_name");
			if(t_name!=null) {
				t_temp=t_dao.query(t_name);
			}
			
			//System.out.println(t_temp.toString());
			String db_password=t_temp.getT_password();
			//System.out.println(db_password);
			if(db_password==null) {
				System.out.println("����û���������");
				String message="����û�������";
				request.getSession().setAttribute("message", message);
				response.sendRedirect("../Res.jsp");
				//request.getRequestDispatcher("/t_logFail.jsp").forward(request, response);;
				}else if(db_password.equals(t_password)) {
					System.out.println("����һ��");
					Task t = new Task();
					Task_Dao task_dao = new Task_Dao();
					List<Task> taskList = new ArrayList<Task>();
					try {
						taskList = task_dao.query();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					request.getSession().setAttribute("tasklist", taskList);
					request.getSession().setAttribute("logTeacher", t_temp);
					//��תҳ��
					response.sendRedirect("/111/t_func.jsp");
					//request.getRequestDispatcher("/t_func.jsp").forward(request, response);
					}else {
						System.out.println("�������");
						String message="�û��������벻ƥ��";
						request.getSession().setAttribute("message", message);
						response.sendRedirect("../Res.jsp");
						//request.getRequestDispatcher("/t_logFail.jsp").forward(request, response);
						//request.getRequestDispatcher("../t_index.jsp").forward(request, response);
					}
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
