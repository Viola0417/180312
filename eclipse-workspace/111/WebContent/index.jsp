<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测评系统</title>
</head>
<body>
	<h1>测试servlet</h1>
	<a href = "servlet/HelloServlet">get方式请求HelloServlet</a><br>
	<form action="servlet/HelloServlet" method="post">
		<input type="submit" value="用post方式请求">
	</form>
</body>
</html>