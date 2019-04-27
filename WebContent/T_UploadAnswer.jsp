<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传答案</title>
</head>
<body>
上传答案文件
	<form method = "post" action = "/111/servlet/T_UploadAnswerServlet" enctype = "multipart/form-data">
		上传答案：
		<input type = "file" name = "uploadFile" />
		<br/><br/>
		<input type = "submit" value = "上传">
	</form>
<br/><br/>
</body>
</html>