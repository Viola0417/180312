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

    <title>学生功能界面</title>

    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
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
        <span class="d-none d-lg-block">
          <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="img/profile.jpg" alt="">
        </span>
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <jsp:useBean id="logStudent" class="entity.Student" scope="session"/>
		当前用户：<jsp:getProperty name="logStudent" property="s_name"/><br/>
		<br/><br/>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">
         <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#about">首页</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#completeTask">做答</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#downloadTrain">下载训练集</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#downloadTest">下载测试集</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#showTask">展示题目</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#modifyPwd">修改密码</a>
          </li>
        </ul>
      </div>
    </nav>

    <div class="container-fluid p-0">

      <section class="resume-section p-3 p-lg-5 d-flex d-column" id="about">
		<div class="my-auto">
          <h3>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;面向数据挖掘与机器学习的评测系统  </h3>
         <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
         </div>
      </section>
      
      
    <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="completeTask">
        <div class="my-auto">
				<h4>做答</h4>
          <br/>
          <div class="mb-5">
          	<br/>
			    <form method='post' name='task_form' action="/111/servlet/CompleteTaskServlet">
			    <input type='text' id='task_id2' name='task_id2' class="username" placeholder="题号">
			    &emsp;<input type = "submit" value = "做答" class="btn-warning">	
		        </form> 
			<br/>
			<a href="#showTask">查看题目</a>
		</div>
        </div>
      </section>
      
      
      
     <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="downloadTrain">
        <div class="my-auto">
          <div class="mb-5">
			<h4>下载训练集</h4>
			<br/><br/><br/>
	          	<form method='post' name='task_form' action="/111/servlet/DownloadTaskServlet">
			    <input type='text' id='task_id1' name='task_id1' class="username" placeholder="题号">
			    &emsp;<input type = "submit" value = "下载" class="btn-warning">	
		        </form> 
		 </div>
		 <a href="#showTask">查看题目</a>
        </div>
   </section>

    
      
  
  <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="downloadTest">
        <div class="my-auto">
          <div class="mb-5">
			<h4>下载测试集</h4>
			<br/><br/><br/>
	            <form method='post' name='task_form' action="/111/servlet/DownloadTestServlet">
			    <input type='text' id='task_id3' name='task_id3' class="username" placeholder="题号">
			    &emsp;<input type = "submit" value = "下载" class="btn-warning">	
		        </form> 
		 </div>
		 <a href="#showTask">查看题目</a>
        </div>
      </section>

      
    <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="showTask">
        <div class="my-auto">
          <div class="mb-5">
 			<div class="container">
		        <table class="table table-striped table-bordered table-hover">
		        	<thead>
		    			 <tr>
		   			   		 <th>题目编号</th>
		      				 <th>题目标题</th>
		      				 <th>题目描述</th>
		    			 </tr>
		  		   </thead>
		  		   <c:forEach items="${tasklist}" var="task">
					 <tbody>
		                  <td><c:out value="${task.t_id}" /></td>
		                  <td><c:out value="${task.t_title}" /></td>
		                  </td><td><c:out value="${task.t_description}" /></td>
		            </c:forEach>
		           </tbody>
		       </table>
       		</div>  
		</div>
        </div>
      </section>
   
      
    <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="modifyPwd">
        <div class="my-auto">
          <div class="mb-5">
			<h4>修改密码</h4>
				<br/><br/><br/>
		        <form action="/111/servlet/StuUpdatePwdServlet" method="post">
	                <input type="password" name="origin_password" class="password" placeholder="原始密码"><br/><br/>
	                <input type="password" name="new_password" class="password" placeholder="新密码"><br/><br/>
	                <input type = "submit" value = "修改" class="btn-warning">	
	            </form>
		 </div>
        </div>
   </section>
      
   
    </div>

    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="js/resume.min.js"></script>

  </body>

</html>




