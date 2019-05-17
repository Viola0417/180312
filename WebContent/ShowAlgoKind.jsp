<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示题目类别与学生提交算法关系</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
</head>
<%
    request.setCharacterEncoding("UTF-8");
%>
<body>
	<div class="container">
	<h4>题目类别与学生提交算法统计图</h4>
	  <div class="row">
		    <div class="col-md-6">
			    <h1>回归</h1>
		    	<img src="<%=request.getContextPath() %>/4.jpeg" />&emsp;&emsp;
		    </div>
		    <div class="col-md-6">
			    <h1>聚类</h1>
			    <img src="<%=request.getContextPath() %>/5.jpeg" />	    	
				    </div>
		</div>
	</div> 
</body>
</html>