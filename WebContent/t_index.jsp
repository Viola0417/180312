<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老师登录界面</title>
</head>
<script type='text/javascript'>
	function login(){
		//alert("你好呀");
		var t_name = document.getElementById('t_name').value;
		var t_pwd = document.getElementById("t_password").value;
		if(t_name == ''||t_name == null){
			alert("用户名不能为空");
			return false;
		}
		else if(t_pwd == ''||t_pwd == null){
			alert("密码不能为空");
			return false;
		}
		return true;
	}
</script>
<body>
	<form method='post' name='t_form' action="/111/servlet/LoginServlet" onSubmit='return login();'>
		用户名：<input type='text' id='t_name' name='t_name' style='height:30px;width:220px;font-size:20px;'><br>
		密    码：<input type='password' id='t_password' name='t_password' style='height:30px;width:220px;font-size:20px;'><br>
		<input type='reset' style='height:30px;width:60px;font-size:20px;'>
		<input type='submit' style='height:30px;width:60px;font-size:20px;'>
		
	</form>
	
</body>
</html>