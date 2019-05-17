<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>老师功能界面</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="vendor/devicons/css/devicons.min.css" rel="stylesheet">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">
    <link href="css/resume.min.css" rel="stylesheet">
    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>

  </head>

  <body id="page-top">

    <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top" id="sideNav">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">
        <span class="d-block d-lg-none">Start Bootstrap</span>
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#allCase">查看所有完成情况</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#graph">查看完成情况统计图</a>
          </li>
        </ul>
      </div>
    </nav>

    <div class="container-fluid p-0">
      <section class="resume-section p-3 p-lg-5 d-flex d-column" id="about">
		<div class="my-auto">
          <h3>学生完成情况统计</h3>
         <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
         </div>
      </section>
      


    
      
  
  <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="allCase">
        <div class="my-auto">
          <div class="mb-5">
			<h3>该学生所有完成情况</h3><br/>
			<div class="container">
			  <div class="row">
					    <table class="table table-striped table-bordered table-hover">
							<thead>
							<tr>
							<th>做答题号</th>
							<th>准确率</th>
							<th>召回率</th>
							<th>F</th>
							<th>作答时间</th>
							</tr>
							</thead>
							<c:forEach items="${log_list}" var="log">
								<tbody>
								<td><c:out value="${log.task_id}" /></td>
								<td><c:out value="${log.p}" /></td>
								</td><td><c:out value="${log.r}" /></td>
								<td><c:out value="${log.f}" /></td>
								</td><td><c:out value="${log.time}" /></td>
								</c:forEach>
								</tbody>
						</table> 
				    </div>				    
		</div> 
      </div>
   </section>

      
      
    <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="graph">
        <div class="my-auto">
          <div class="mb-5">
          		<h4>完成结果统计图</h4><br/><br/>
  				<img src="<%=request.getContextPath() %>/2.jpeg" />
		  </div>
        </div>
   </section>
      

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
 
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <script src="js/resume.min.js"></script>

  </body>

</html>



