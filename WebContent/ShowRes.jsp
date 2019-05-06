<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生查看结果</title>
</head>
<body>
<h1>您已经完成了做答</h1>
<br/><br/>
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
<br/>
这道题做答记录为：
<%
	String log_num = (String)session.getAttribute("log_num");
	out.print(log_num);
%>
这道题做答人数为：
<%
	String log_dis_num = (String)session.getAttribute("log_dis_num");
	out.print(log_dis_num);
%>
<br/>
在所有回答本题的记录中F的排名为：
<%
	String log_rank = (String)session.getAttribute("log_rank");
	out.print(log_rank);
%>
在所有回答本题的学生中F的排名为：
<%
	String stu_rank = (String)session.getAttribute("stu_rank");
	out.print(stu_rank);
%>
</body>
</html>