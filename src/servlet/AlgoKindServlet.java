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

import dao.Task_Dao;
import service.multicolumn;
import service.pie3;

/**
 * Servlet implementation class AlgoKindServlet
 */
@WebServlet("/AlgoKindServlet")
public class AlgoKindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlgoKindServlet() {
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
		//查找数据库根据分类、回归、聚类得到图片
		String message1=null;
		String message2=null;
		String message3=null;
		request.setCharacterEncoding("utf-8");
		String path = request.getServletContext().getRealPath("./")+File.separator+"3.jpeg";
		System.out.println("存放图片路径为："+path);
		pie3 p = new pie3();
		String task_kind="分类";
		List<Integer> task_id_list = new ArrayList<Integer>();
		Task_Dao t_dao = new Task_Dao();
		try {
				p.generatePieChart(task_kind, path);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		path = request.getServletContext().getRealPath("./")+File.separator+"4.jpeg";
		task_kind = "回归";
		try {
				p.generatePieChart(task_kind, path);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		path = request.getServletContext().getRealPath("./")+File.separator+"5.jpeg";
		task_kind = "聚类";
		try {
				p.generatePieChart(task_kind, path);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		response.sendRedirect("../ShowAlgoKind.jsp");
	}

}
