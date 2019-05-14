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
		//doGet(request, response);
		//System.out.println("ѧ���޸�����");
		Student s = new Student();
		Student_Dao s_dao = new Student_Dao();
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		//System.out.println("����post����");
		String origin_password = request.getParameter("origin_password");
		String new_password = request.getParameter("new_password");
		//System.out.println("ԭ�������ǣ�"+origin_password+"�������ǣ�"+new_password);
		//�ȸ���ѧ�ż��ԭ�������������Ƿ���ȷ
		//��ȡѧ��ѧ��
		HttpSession  hs = request.getSession();
		int stu_id = (int) hs.getAttribute("stu_id");
		try {
			s = s_dao.query(stu_id);
			String db_pwd = s.getS_password();
			if(db_pwd.equals(origin_password)) {
				//����������ȷ
				//�ٸ���ѧ�Ÿ����µ�����
				s = new Student();
				s.setS_id(stu_id);
				s.setS_password(new_password);
				s_dao.updateStudent(s);
				String message="�Ѿ��ɹ�����";
				request.getSession().setAttribute("message", message);
				response.sendRedirect("../Res.jsp");
				//request.getRequestDispatcher("../s_UpdatePwdSuc.jsp").forward(request, response);
			}else {
				String message="������������뷵����������";
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
