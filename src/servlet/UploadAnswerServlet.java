package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UploadAnswerServlet
 */
@WebServlet("/UploadAnswerServlet")
public class UploadAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadAnswerServlet() {
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
		System.out.println("����ѧ���ϴ���������Ŀ�����ҰѱȶԵĽ�����log��");
		HttpSession  hs = request.getSession();
		int stu_id = (int) hs.getAttribute("stu_id");
		//HttpSession  hs = request.getSession();
		int task_id = (int) hs.getAttribute("task_id");
		System.out.println("��ǰѧ����ѧ����:"+stu_id+"�����������Ŀ�ǣ�"+task_id);
	}

}
