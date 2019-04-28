<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生</title>
</head>
<script type='text/javascript'>
	function check(){
		//alert("你好呀");
		var origin_password = document.getElementById('origin_password').value;
		var new_password = document.getElementById("new_password").value;
		if(origin_password == ''||origin_password == null){
			alert("原密码不能为空");
			return false;
		}
		else if(new_password == ''||new_password == null){
			alert("新密码不能为空");
			return false;
		}
		return true;
	}
</script>
<body>
<jsp:useBean id="logStudent" class="entity.Student" scope="session"/>
当前用户：<jsp:getProperty name="logStudent" property="s_name"/><br/>

<h1>查看文件</h1>
	<form method='post' action="/111/servlet/PrintTaskServlet">
		<input type='submit' value='查看文件' style='height:30px;width:60px;font-size:20px;'>		
	</form>

<br/><br/>
<h1>修改密码</h1>
	<form method='post' action="/111/servlet/StuUpdatePwdServlet" onSubmit='return check()'>
	原始密码：<input type='password' id='origin_password' name='origin_password' style='height:30px;width:220px;font-size:20px;'><br>
          新设置密码：<input type='password' id='new_password' name='new_password' style='height:30px;width:220px;font-size:20px;'><br>
    <input type='submit' value='确认提交' style='height:30px;width:60px;font-size:20px;'>		
	</form>
</body>

</html>