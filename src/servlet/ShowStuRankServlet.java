package servlet;

import java.io.File;
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
import dao.Student_Dao;
import dao.Task_Dao;
import entity.Log;
import entity.Student;
import entity.Task;
import service.multicolumn;
import service.pie;

/**
 * Servlet implementation class ShowTaskRankServlet
 */
@WebServlet("/ShowStuRankServlet")
public class ShowStuRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStuRankServlet() {
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
		System.out.println("����չʾѧ���������");
		//���жϸ�ѧ���Ƿ����
		//1.ѧ����������
		//2.ѧ����û������
		//3.����ѧ�������¼
		String s_id = null;
		request.setCharacterEncoding("utf-8");
		s_id=request.getParameter("stu_id");
		Student s = new Student();
		Student_Dao s_dao = new Student_Dao();
		//System.out.println("ѧ��Ҫ������ǣ�"+task_id+"��Ŀ");
		Log_Dao l_dao = new Log_Dao();
		int res = 0;
		int log_res = 0;
		//�ж�ѧ���Ƿ����,res=1����ڣ�res=0�򲻴���
		int stu_id = Integer.parseInt(s_id);
		try {
			res = s_dao.CheckLogByStu(stu_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res!=0) {
			//��ѧ������
			//�жϸ�ѧ���Ƿ��������¼
			try {
				log_res = l_dao.CheckLogByStu(stu_id);
				if(log_res!=0) {
					//��ѧ��������
					List<Log> log_list = new ArrayList<Log>();
					try {
						//log_list = l_dao.QueryByTaskNo(enterTask);
						log_list = l_dao.QueryByStuNo(stu_id);
						for(Log l:log_list) {
							System.out.println(l.toString());
						}
						request.getSession().setAttribute("log_list", log_list);
						//String message = "Ϊʲô��������";
						//request.getSession().setAttribute("message", message);
						//��ʼ��ͼ
						String path = request.getServletContext().getRealPath("./")+File.separator+"2.jpeg";
						System.out.println("���ͼƬ·��Ϊ��"+path);
						multicolumn m = new multicolumn();
						m.generateColumnChart(stu_id, path);
						response.sendRedirect("../ShowStuRankSuc.jsp");
						//request.getRequestDispatcher("/ShowStuRankSuc.jsp").forward(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					String warning="��ѧ����δ����";
					request.getSession().setAttribute("warning", warning);
					response.sendRedirect("../ShowStuRankFail.jsp");
					//request.getRequestDispatcher("../ShowStuRankFail.jsp").forward(request, response);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else {
			String warning="��ѧ�����ڱ�ϵͳ";
			request.getSession().setAttribute("warning", warning);
			request.getRequestDispatcher("../ShowStuRankFail.jsp").forward(request, response);
		}
	}

}
