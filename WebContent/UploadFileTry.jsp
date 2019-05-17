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
	
    <!-- Custom styles for this template 
    <link href="https://fonts.googleapis.com/css?family=Saira+Extra+Condensed:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    -->
    <link href="css/resume.min.css" rel="stylesheet">
    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
	<link rel="stylesheet" href="assets/css/reset.css">
	<link rel="stylesheet" href="assets/css/supersized.css">
	<link rel="stylesheet" href="assets/css/style.css">

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
		<jsp:useBean id="logTeacher" class="entity.Teacher" scope="session"/>
		当前用户：<jsp:getProperty name="logTeacher" property="t_name"/><br/>
		<br/><br/>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#about">首页</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#addStu">导入学生</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#addTask">新增题目</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#uploadTest">上传测试集</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#checkRank">查看学生做题情况</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#delStu">删除学生</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#delTask">删除题目</a>
          </li>
        </ul>
      </div>
    </nav>

    <div class="container-fluid p-0">

      <section class="resume-section p-3 p-lg-5 d-flex d-column" id="about">
		<div class="my-auto">
          <h1>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;面向数据挖掘与机器学习的评测系统  </h1>
         <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
         </div>
      </section>
      
     
      <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="addStu">
 
        <div class="my-auto">
          <h1>导入学生</h1> 
  		  <form method = "post" action = "/111/servlet/UploadServlet" enctype = "multipart/form-data">
		  	<input type = "file" name = "uploadFile" />
		  	<br/><br/>
			<input type = "submit" value = "上传文件" class="btn-warning">	
		  </form> 
        </div>
      </section>
      
      
  <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="addTask">
        <div class="my-auto">

          <div class="resume-item d-flex flex-column flex-md-row mb-5">
            <div class="resume-content mr-auto">
				<h3>请上传你的做答描述</h3>
            </div>
          </div>
          <br/>
          <div class="mb-5">
		<h4>
		<form method = "post" action = "/111/servlet/UploadDesServlet">
	    <p>
		关键算法：
		<br/>
     	<input type="checkbox" name="algo" value="SVM" />SVM&emsp;
     	<input type="checkbox" name="algo" value="决策树" /> 决策树&emsp;
     	<input type="checkbox" name="algo" value="KNN最近邻" />KNN最近邻&emsp;
     	<input type="checkbox" name="algo" value="随机森林" />随机森林&emsp;
     	<input type="checkbox" name="algo" value="朴素贝叶斯" />朴素贝叶斯&emsp;
     	<input type="checkbox" name="algo" value="逻辑回归" />逻辑回归&emsp;
     	<input type="checkbox" name="algo" value="其他" />其他
   		</p>
   		<br>
		<textarea rows="3" cols="60" id='description' name='description' class="username" placeholder="步骤描述"></textarea>
		<br/><br/>
		<input type = "submit" value = "提交描述" class="btn-warning">	
		</form>
		
		</h4>

	</div>
        </div>
      </section>

      
      
      <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="uploadTest">
        <div class="my-auto">
          <h1>上传训练集</h1>   
        </div>
      </section>
      
      
      <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="checkRank">
        <div class="my-auto">
          <h1>查看学生做题情况</h1>    
        </div>
      </section>
      
      
      
      <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="delStu">
   	   <div class="mb-5">
       <font color="black">
          <h1>删除学生</h1>  
         </font>
       </div>
    </section>
      
      
      <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="delTask">
        <div class="my-auto">
         <font color="black">
          <h1>删除题目</h1>
            </font>       
        </div>
      </section>

   
    </div>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/jquery-1.8.2.min.js"></script>
  
    <script src="assets/js/supersized.3.2.7.min.js"></script>
      <!-- 
    <script src="assets/js/supersized-init.js"></script>
   -->
    <script src="assets/js/scripts.js"></script>
    <!-- Plugin JavaScript -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="js/resume.min.js"></script>

  </body>

</html>



