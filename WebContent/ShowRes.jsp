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
</body>
</html>