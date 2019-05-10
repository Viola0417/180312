<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示学生的做答情况</title>
</head>
<%
    request.setCharacterEncoding("UTF-8");
%>
<body>
<h1>本学生的所有做答情况</h1>
	<table>
		<c:forEach items="${log_list}" var="log">
		<tbody>
                  <td id="task_id">做答的题号：</td><td><c:out value="${log.task_id}" />
                  <td id="P">准确率：</td><td><c:out value="${log.p}" />
                  <td id="R">召回率：</td><td><c:out value="${log.r}" />
                  <td id="F">F：</td><td><c:out value="${log.f}" />
                  <td id="time">作答时间：</td><td><c:out value="${log.time}" />
            
         </c:forEach>
         </tbody>
    </table>
    <img src="<%=request.getContextPath() %>/2.jpeg" />
</body>
</html>