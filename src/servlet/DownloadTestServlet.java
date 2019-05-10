package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
 * Servlet implementation class DownloadTestServlet
 */
@WebServlet("/DownloadTestServlet")
public class DownloadTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadTestServlet() {
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
		System.out.println("准备下载test了");
		request.setCharacterEncoding("utf-8");
		//System.out.println("处理post请求");
		task_id=request.getParameter("task_id3");
		ServletContext context = this.getServletContext();
		String test_file_path = context.getRealPath(task_id+"/test");
		File file = new File(test_file_path);
		if(file.exists()) {
			System.out.println("文件存在");
			String[] names={"test.xlsx"};
	        FileInputStream input_test = new FileInputStream(new File(test_file_path));
	       // FileInputStream input_test = new FileInputStream(new File(test_file_path));
	        //FileInputStream[] inputs={input_train,input_test};
	        FileInputStream[] inputs={input_test};
	        
	        //ZIP打包
	        String zip_path =  request.getServletContext().getRealPath("./")+File.separator+"zip_test";
	        File zipFile = new File(zip_path);
	        byte[] buf = new byte[1024];
	        int len;
	        ZipOutputStream zout=new ZipOutputStream(new FileOutputStream(zipFile));
	        for (int i = 0; i < inputs.length; i++) {  
	            FileInputStream in =inputs[i];  
	            zout.putNextEntry(new ZipEntry(names[i]));    
	            while ((len = in.read(buf)) > 0) {  
	                zout.write(buf, 0, len);  
	            }  
	            zout.closeEntry();  
	            in.close();  
	        }
	        zout.close();
	        
	        
	        System.out.println("下载zip");
	        FileInputStream zipInput =new FileInputStream(zipFile);
	        OutputStream out = response.getOutputStream();
	        response.setContentType("application/octet-stream");
	        String zip_name = task_id+".zip";
	        response.setHeader("Content-Disposition", "attachment; filename="+zip_name);
	        while ((len=zipInput.read(buf))!= -1){  
	            out.write(buf,0,len);  
	        }
	        zipInput.close();
	        out.flush();
	        out.close();
	        //删除压缩包
	        zipFile.delete();
		}else {
			System.out.println("文件不存在");
			String warning="文件不存在";
			request.getSession().setAttribute("warning", warning);
			response.sendRedirect("../downloadFail.jsp");
		}
	}

}
