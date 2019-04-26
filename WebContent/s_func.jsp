<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生</title>
</head>
<body>
<jsp:useBean id="logStudent" class="entity.Student" scope="session"/>
当前用户：<jsp:getProperty name="logStudent" property="s_name"/><br/>

<h1>查看文件</h1>
	<form method='post' action="/111/servlet/PrintTaskServlet">
		<input type='submit' value='查看文件' style='height:30px;width:60px;font-size:20px;'>		
	</form>



</body>

</html>