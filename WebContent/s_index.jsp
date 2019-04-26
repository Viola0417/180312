<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生登录界面</title>
</head>
<script type='text/javascript'>
	function login(){
		//alert("你好呀");
		var stu_name = document.getElementById('s_name').value;
		var stu_pwd = document.getElementById("s_password").value;
		if(stu_name == ''||stu_name == null){
			alert("用户名不能为空");
			return false;
		}
		else if(stu_pwd == ''||stu_pwd == null){
			alert("密码不能为空");
			return false;
		}
		return true;
	}
</script>
<body>
	<form method='post' name='s_form' action="/111/servlet/StuLoginServlet" onSubmit='return login();'>
		用户名：<input type='text' id='s_name' name='s_name' style='height:30px;width:220px;font-size:20px;'><br>
		密    码：<input type='password' id='s_password' name='s_password' style='height:30px;width:220px;font-size:20px;'><br>
		<input type='reset' style='height:30px;width:60px;font-size:20px;'>
		<input type='submit' style='height:30px;width:60px;font-size:20px;'>
		
	</form>
	
</body>
</html>