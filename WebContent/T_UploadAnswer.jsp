<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老师上传答案文件</title>
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/resume.min.css" rel="stylesheet">
</head>

<body id="page-top" background="img/back.jpg"  
	style="background-repeat:no-repeat ;
	background-size:100% 100%;
	background-attachment: fixed;">


     <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="interests">
        <div class="my-auto">
        <br/><br/><br/><br/><br/>
          <h3 class="mb-5">上传答案文件</h3>
          <br/>
          <h4>
  		  <form method = "post" action = "/111/servlet/T_UploadAnswerServlet" enctype = "multipart/form-data">
		  	<input type = "file" name = "uploadFile" />
		  	<br/><br/>
			<input type = "submit" value = "上传文件" class="btn-warning">	
		  </form>
		  </h4>
        </div>
      </section>
</body>
</html>