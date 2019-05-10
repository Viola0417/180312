<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老师上传测试集</title>
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
	
</script>
<body>
请输入你要上传的测试集题目
	<form method='post' name='task_form' action="/111/servlet/TestNumServlet" onSubmit='return login_1();'>
		要选择的题号是：<input type='text' id='task_id1' name='task_id1' style='height:30px;width:220px;font-size:20px;'><br>
		<input type='submit' style='height:30px;width:60px;font-size:20px;'>		
	</form>
	<br/><br/>

        <table>
        	<c:forEach items="${tasklist}" var="task">
			 <tbody>
                  <td id="task_id">题目编号：</td><td><c:out value="${task.t_id}" />
                  <td id="tdsk_title">题目标题：</td><td><c:out value="${task.t_title}" />
                  <td id="task_description">题目描述：</td><td><c:out value="${task.t_description}" /></td>
            </c:forEach>
           </tbody>
       </table> 

<br/><br/>
</body>
</html>