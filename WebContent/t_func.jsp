<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量导入学生</title>
</head>
<body>
<jsp:useBean id="logTeacher" class="entity.Teacher" scope="session"/>
当前用户：<jsp:getProperty name="logTeacher" property="t_name"/><br/>
<h1>批量导入学生</h1><br/>
	<form method = "post" action = "/111/servlet/UploadServlet" enctype = "multipart/form-data">
		选择学生文件导入学生：
		<input type = "file" name = "uploadFile" />
		<br/><br/>
		<input type = "submit" value = "上传">
	</form>
	
	<form method = "post" action = "/111/servlet/UploadTaskDesServlet" name = "task_form">
		<h1>题目描述</h1>
		题目标题：<input type='text' id='title_name' name='title_name' style='height:30px;width:220px;font-size:20px;'><br>
		题目描述：<input type='text' id='description_name' name='description_name' style='height:30px;width:220px;font-size:20px;'><br>
		<input type = "submit" value = "提交描述">	
	</form>
	
	<h1>查看学生做答情况</h1>
	<form method='get' action="/111/SelectRank.jsp">
		<input type='submit' value='查看排名' style='height:30px;width:60px;font-size:20px;'>		
	</form>
	
</body>
</html>