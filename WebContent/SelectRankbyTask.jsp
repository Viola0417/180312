<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看某一道题的所有做答情况</title>
</head>
<script type='text/javascript'>
	function check(){
		var task = document.getElementById('task_id').value;
		if(task == ''||task == null){
			alert("输入题号不能为空");
			return false;
		}
		return true;
	}
</script>
<body>
请输入你要查看题目的题号
	<form method='post' name='task_form' action="/111/servlet/ShowTaskRankServlet" onSubmit='return check();'>
		要下载的题号是：<input type='text' id='task_id' name='task_id' style='height:30px;width:220px;font-size:20px;'><br>
		<input type='submit' style='height:30px;width:60px;font-size:20px;'>		
	</form>
</body>
</html>