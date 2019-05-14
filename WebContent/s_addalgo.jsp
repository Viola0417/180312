<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生做答界面</title>
</head>
<body>
学生开始做题
<br>
<h1>请上传你的做答描述</h1>
	<form method = "post" action = "/111/servlet/UploadDesServlet">
	    <p>
		关键算法：
     	<input type="checkbox" name="algo" value="SVM" />SVM
     	<input type="checkbox" name="algo" value="决策树" /> 决策树
     	<input type="checkbox" name="algo" value="KNN最近邻" />KNN最近邻
     	<input type="checkbox" name="algo" value="随机森林" />随机森林
     	<input type="checkbox" name="algo" value="朴素贝叶斯" />朴素贝叶斯
     	<input type="checkbox" name="algo" value="逻辑回归" />逻辑回归
     	<input type="checkbox" name="algo" value="其他" />其他
   		</p>
		步骤描述：<input type='text' id='description' name='description' style='height:30px;width:220px;font-size:20px;'><br>
		<input type = "submit" value = "提交描述">	
	</form>
</body>
</html>