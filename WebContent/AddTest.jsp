<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老师上传测试集</title>
</head>
<body>
上传测试集文件
	<form method = "post" action = "/111/servlet/UploadTestServlet" enctype = "multipart/form-data">
		上传测试集：
		<input type = "file" name = "uploadFile" />
		<br/><br/>
		<input type = "submit" value = "上传">
	</form>
<br/><br/>
</body>
</html>