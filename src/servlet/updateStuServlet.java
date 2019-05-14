package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Student_Dao;
import entity.Student;
import service.ReadExcel;

/**
 * Servlet implementation class updateStuServlet
 */
@WebServlet("/updateStuServlet")
public class updateStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateStuServlet() {
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
		//System.out.println("过来了");
		String f_path;
		f_path = request.getParameter("f_name");
		//System.out.println(f_path);
		Student_Dao s_dao = new Student_Dao();
		//ParseExcel parser = new ParseExcel();
		InputStream inputStream = new FileInputStream(f_path);
		String suffix = "xlsx";
		int startRow = 1;
	
		//List<String[]> result = 
					//parser.parseExcel(inputStream, suffix, startRow);
		ReadExcel xlsMain = new ReadExcel();
		//Student student = null;
		List<Student> list = xlsMain.readXls(inputStream,suffix,startRow);
		
		//System.out.println("执行结束");
		
		for(Student s : list){
			//System.out.println(s.toString());
			try {
				s_dao.addStudent(s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//System.out.println("执行结束");
		//doGet(request, response);
	}

}
