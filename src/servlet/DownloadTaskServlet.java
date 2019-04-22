package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Task_Dao;
import entity.Task;

/**
 * Servlet implementation class DownloadTaskServlet
 */
@WebServlet("/DownloadTaskServlet")
public class DownloadTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadTaskServlet() {
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
		String task_id = null;
		//String filename = null;
		System.out.println("准备下载task了");
		request.setCharacterEncoding("utf-8");
		//System.out.println("处理post请求");
		task_id=request.getParameter("task_id");
		System.out.println(task_id);
		Task t = new Task();
		Task_Dao t_dao = new Task_Dao();
		int TaskNo = 0;
		TaskNo = t_dao.SearchLastNum();//在数据库获取当前题的题号
		//判断输入的题是否在数据库中
		int enterTask = Integer.parseInt(task_id);
		if(enterTask<=TaskNo&&enterTask>0) {
			System.out.println("这道题在数据库中，准备开始下载文件");
			//设置文件MIME类型
			response.setContentType(getServletContext().getMimeType(task_id));
			//设置Content-Disposition
			response.setHeader("Content-Disposition", "attachment;filename="+task_id);
			
			//获取要下载的文件的绝对路径
			ServletContext context = this.getServletContext();
			//String tryFileName = context.getRealPath("./");
			//System.out.println(tryFileName);

			String fullFileName = context.getRealPath(task_id+"/train");
			System.out.println(fullFileName);
			
			//输入流为项目文件，输出流指向浏览器
			InputStream is = new FileInputStream(fullFileName);
			ServletOutputStream os = response.getOutputStream();
			
			//设置缓冲区   is.read()当文件读完时返回-1
			int len = -1;
			byte[] b = new byte[1024];
			while((len=is.read(b))!=-1) {
				os.write(b,0,len);
			}
			//关闭流
			is.close();
			os.close();
			String message = "题目已经下载成功";
			request.getSession().setAttribute("message", message);
			request.getRequestDispatcher("../downloadSuc.jsp").forward(request, response);
		}else {
			System.out.println("这道题不在数据库中");
			String warning="题目号码不正确";
			request.getSession().setAttribute("warning", warning);
			request.getRequestDispatcher("../downloadFail.jsp").forward(request, response);
		}
	}

}
