<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生做答界面</title>
</head>
<body>
学生开始做题
<br>
<h1>请上传你的答案文件</h1>
	<form method = "post" action = "/111/servlet/UploadAnswerServlet" enctype = "multipart/form-data">
		<input type = "file" name = "uploadFile" />
		<br/><br/>
		<input type = "submit" value = "上传">
	</form>
</body>
</html>