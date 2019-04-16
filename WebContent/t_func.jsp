<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
<jsp:useBean id="logTeacher" class="entity.Teacher" scope="session"/>
当前用户：<jsp:getProperty name="logTeacher" property="t_name"/><br/>
<h1>上传文件</h1><br/>
	<form method = "post" action = "/111/servlet/UploadServlet" enctype = "multipart/form-data">
		选择一个文件：
		<input type = "file" name = "uploadFile" />
		<br/><br/>
		<input type = "submit" value = "上传">
	</form>
<br/><br/>
<h1>导入数据库</h1><br/><br/>
	<form method = "post" action = "/111/servlet/UpdateStuServlet">
		<input type = "submit" value = "导入">
	</form>
</body>
</html>