<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生下载文件错误</title>
</head>
<body>
<% 
	String warning = (String)session.getAttribute("warning"); 
	out.print(warning);
%>
请返回重新登录
</body>
</html>