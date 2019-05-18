package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Task_Dao;
import entity.Task;
import service.ParseTest;
import service.calculation;
import service.writeExcel;
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
		String task_id = request.getParameter("task_id3");
		if(task_id=="") {
			//System.out.println("得到的值为空");
			String message="输入题号不能为空";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("../Res.jsp");			
		}else {
			HttpSession  hs = request.getSession();
			int stu_id = (int) hs.getAttribute("stu_id");
			int b = stu_id % 100;
			//String filename = null;
			System.out.println("准备下载test了");
			request.setCharacterEncoding("utf-8");

			ServletContext context = this.getServletContext();
			String test_file_path = context.getRealPath(task_id+"/test");
			File file = new File(test_file_path);
			if(file.exists()) {
				//System.out.println("文件存在");
				String[] names={"test.xlsx"};
		        //FileInputStream input_test = new FileInputStream(new File(test_file_path));
		       // FileInputStream input_test = new FileInputStream(new File(test_file_path));
		        //FileInputStream[] inputs={input_train,input_test};
		       
		      //把原有的test文件打乱顺序
		        //+++++++++++++++++++++++++++++++++++++++++++++==
				//System.out.println("学生上传答案地址:"+f_path_2);
				InputStream inputStream = new FileInputStream(test_file_path);
				String suffix = "xlsx";
				int startRow = 0;
			
				//List<String[]> result = 
							//parser.parseExcel(inputStream, suffix, startRow);
				ParseTest xlsMain = new ParseTest();
				//split s = new split();
				calculation c = new calculation();
				//Student student = null;
				List<String> rvs_list = new ArrayList<String>();
				String rvs_path =  request.getServletContext().getRealPath("./")+File.separator+stu_id+"_"+task_id+"_test";
				System.out.println(rvs_path);
				
				List<String> standard_list =  xlsMain.readXls(inputStream,suffix,startRow);
				if(b==1 || b==0) {
					rvs_list = standard_list;
				}else {
					rvs_list = c.reverse(standard_list, stu_id, b);
				}
				
				writeExcel w = new writeExcel();
				try {
					w.we(rvs_list,rvs_path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("结束");
		      //+++++++++++++++++++++++++++++++++++++++++++++==
		        
				FileInputStream input_test = new FileInputStream(rvs_path);
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
		        
		        
		        //System.out.println("下载zip");
		        FileInputStream zipInput =new FileInputStream(zipFile);
		        OutputStream out = response.getOutputStream();
		        response.setContentType("application/octet-stream");
		        String zip_name = task_id+"_test.zip";
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
				String message="文件不存在";
				request.getSession().setAttribute("message", message);
				response.sendRedirect("../Res.jsp");
			}
	
		}
	}
}
