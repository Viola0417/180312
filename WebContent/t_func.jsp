<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量导入学生</title>
</head>
<script type='text/javascript'>
	function check(){
		//alert("你好呀");
		var s_id = document.getElementById('s_id').value;
		if(s_id == ''||s_id == null){
			alert("输入学号不能为空");
			return false;
		}
	}
	function check1(){
		//alert("你好呀");
		var t_id = document.getElementById('t_id').value;
		if(t_id == ''||t_id == null){
			alert("输入题号不能为空");
			return false;
		}
	}
</script>
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
		<h1>新增题目</h1>
		题目标题：<input type='text' id='title_name' name='title_name' style='height:30px;width:220px;font-size:20px;'><br>
		题目描述：<input type='text' id='description_name' name='description_name' style='height:30px;width:220px;font-size:20px;'><br>
		<p>
		题目类别：
     	<input type="checkbox" name="checkbox" value="回归" /> 回归
     	<input type="checkbox" name="checkbox" value="聚类" /> 聚类
     	<input type="checkbox" name="checkbox" value="其他" />其他
   		</p>
		<input type = "submit" value = "提交描述">	
	</form>
	
	<h1>上传测试集</h1>
	<form method='post' action="/111/servlet/AddTestFileServlet">
		<input type='submit' value='上传测试集' style='height:30px;width:60px;font-size:20px;'>		
	</form>
	
	
	<h1>查看学生做答情况</h1>
	<form method='get' action="/111/SelectRank.jsp">
		<input type='submit' value='查看排名' style='height:30px;width:60px;font-size:20px;'>		
	</form>
	
	
	<h1>删除学生</h1>
	<form method='post' action="/111/servlet/DeleteStuServlet" onSubmit='return check()'>
	          要删除的学生的学号是：<input type='text' id='s_id' name='s_id' style='height:30px;width:220px;font-size:20px;'><br>
		<input type='submit' value='删除该学生' style='height:30px;width:60px;font-size:20px;'>
	</form>
	
	<h1>删除题目</h1>
	<form method='post' action="/111/servlet/DeleteTaskServlet" onSubmit='return check1()'>
	          要删除的题目的题号是：<input type='text' id='t_id' name='t_id' style='height:30px;width:220px;font-size:20px;'><br>
		<input type='submit' value='删除该题目' style='height:30px;width:60px;font-size:20px;'>
	</form>
</body>
</html>