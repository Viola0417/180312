<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>task</title>
</head>
<%
    request.setCharacterEncoding("UTF-8");
%>
<script type='text/javascript'>
	function login_1(){
		var task = document.getElementById('task_id1').value;
		if(task == ''||task == null){
			alert("输入题号不能为空");
			return false;
		}
		return true;
	}
	function login_2(){
		var task = document.getElementById('task_id2').value;
		if(task == ''||task == null){
			alert("输入题号不能为空");
			return false;
		}
		return true;
	function login_3(){
		var task = document.getElementById('task_id3').value;
		if(task == ''||task == null){
			alert("输入题号不能为空");
			return false;
		}
		return true;
	}
</script>
<body>
这里应该展示了所有的task<br/>
请输入你要下载的训练集文件号码：
	<form method='post' name='task_form' action="/111/servlet/DownloadTaskServlet" onSubmit='return login_1();'>
		要下载的题号是：<input type='text' id='task_id1' name='task_id1' style='height:30px;width:220px;font-size:20px;'><br>
		<input type='submit' style='height:30px;width:60px;font-size:20px;'>		
	</form>
	<br/><br/>
	
请输入你要下载的测试集文件号码：
	<form method='post' name='task_form' action="/111/servlet/DownloadTestServlet" onSubmit='return login_3();'>
		要下载的题号是：<input type='text' id='task_id3' name='task_id3' style='height:30px;width:220px;font-size:20px;'><br>
		<input type='submit' style='height:30px;width:60px;font-size:20px;'>		
	</form>
	<br/><br/>
	
请输入要做的题目
	<form method='post' name='task_form' action="/111/servlet/CompleteTaskServlet" onSubmit='return login_2();'>
		要完成的题号是：<input type='text' id='task_id2' name='task_id2' style='height:30px;width:220px;font-size:20px;'><br>
		<input type='submit' style='height:30px;width:60px;font-size:20px;'>		
	</form>
        <table>
        	<c:forEach items="${tasklist}" var="task">
			 <tbody>
                  <td id="task_id">题目编号：</td><td><c:out value="${task.t_id}" />
                  <td id="tdsk_title">题目标题：</td><td><c:out value="${task.t_title}" />
                  <td id="task_description">题目描述：</td><td><c:out value="${task.t_description}" /></td>
            </c:forEach>
           </tbody>
       </table> 
</body>
</html>