<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>上传答案文件</title>

    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/resume.min.css" rel="stylesheet">
    

</head>

<body id="page-top" background="img/orange.jpg"  
	style="background-repeat:no-repeat ;
	background-size:100% 100%;
	background-attachment: fixed;">


     <section class="resume-section p-3 p-lg-5 d-flex flex-column" id="interests">
        <div class="my-auto">
          <h3 class="mb-5">请上传答案文件</h3>
          <br/><br/>
          <h4>
  		  <form method = "post" action = "/111/servlet/UploadAnswerServlet" enctype = "multipart/form-data">
		  	<input type = "file" name = "uploadFile" />
		  	<br/><br/>
			<input type = "submit" value = "上传文件" class="btn-warning">	
		  </form>
		  </h4>
        </div>
      </section>

  </body>
</body>
</html>