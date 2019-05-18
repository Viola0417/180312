<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>做答结果</title>

    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/resume.min.css" rel="stylesheet">
    

</head>
<body background="img/back.jpg">
 <body id="page-top">


     <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="interests">
        <div class="my-auto">
        <br/><br/><br/><br/>
          <h3 class="mb-5">您已经完成了做答</h3>
          
          <h4>
		     计算的召回率recall是：
			<% 
			String R = (String)session.getAttribute("R"); 
			out.print(R);
			%>
		 <br/>
		  计算的准确率precision是：
			<%
		 	String P = (String)session.getAttribute("P");
		 	out.print(P);
			%>
		<br/>
		综合的F是：
			<%
			String F = (String)session.getAttribute("F");
			out.print(F);
			%>
		<br/><br/>
		这道题做答记录为：
			<%
			String log_num = (String)session.getAttribute("log_num");
			out.print(log_num);
			%>
		条
		<br/>
		这道题做答人数为：
			<%
			String log_dis_num = (String)session.getAttribute("log_dis_num");
			out.print(log_dis_num);
			%>
		人
		<br/>
		在所有回答本题的记录中F的排名为：第
			<%
			String log_rank = (String)session.getAttribute("log_rank");
			out.print(log_rank);
			%>
		名
		<br/>
		在所有回答本题的学生中F的排名为：第
			<%
			String stu_rank = (String)session.getAttribute("stu_rank");
			out.print(stu_rank);
			%>
		名
		  </h4>
        </div>
      </section>

  </body>
</body>
</html>