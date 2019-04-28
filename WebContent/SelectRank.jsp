<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择排名</title>
</head>
<body>
	查看某道题的做答情况
	<form method='get' action="/111/SelectRankbyTask.jsp">
		<input type='submit' value='查看排名' style='height:30px;width:60px;font-size:20px;'>		
	</form>
	<br/><br/>
	查看某个学生的做答情况
	<form method='get' action="/111/SelectRankbyStu.jsp">
		<input type='submit' value='查看排名' style='height:30px;width:60px;font-size:20px;'>		
	</form>
</body>
</html>