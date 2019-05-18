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
		System.out.println("进入algokindservlet");
		request.setCharacterEncoding("utf-8");
		String path = request.getServletContext().getRealPath("./")+File.separator+"3.jpeg";
		//System.out.println("存放图片路径为："+path);
		pie3 p = new pie3();

		
		path = request.getServletContext().getRealPath("./")+File.separator+"4.jpeg";
		String task_kind = "回归";
		try {
				p.generatePieChart(task_kind, path);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		System.out.println("回归正常");
		path = request.getServletContext().getRealPath("./")+File.separator+"5.jpeg";
		task_kind = "聚类";
		try {
				p.generatePieChart(task_kind, path);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("聚类正常");
		
		response.sendRedirect("../ShowAlgoKind.jsp");
	}

}
