<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>老师登录界面</title>
</head>
<body>
<p>登录成功</p>
<jsp:useBean id="logTeacher" class="entity.Teacher" scope="session"/>
当前用户：<jsp:getProperty name="logTeacher" property="t_name"/><br/>
</body>
</html>