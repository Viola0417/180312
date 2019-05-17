<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>添加算法与描述</title>

    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/resume.min.css" rel="stylesheet">
    

</head>

 <body id="page-top" background="img/orange.jpg"  
	style="background-repeat:no-repeat ;
	background-size:100% 100%;
	background-attachment: fixed;">


      <section class="resume-section p-3 p-lg-5 d-flex flex-column">
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

  </body>
</body>
</html>