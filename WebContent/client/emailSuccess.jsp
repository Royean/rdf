<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/main.css" type="text/css" />
	<script type="text/javascript" src="js/my.js"></script>
<style>
*{margin:0;padding:0;outline:none;font-family:\5FAE\8F6F\96C5\9ED1,宋体;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;-khtml-user-select:none;user-select:none;cursor:default;font-weight:lighter;}
.center{margin:0 auto;}
.whole{width:100%;height:100%;line-height:100%;position:fixed;bottom:0;left:0;z-index:-1000;overflow:hidden;}
.whole img{width:100%;height:100%;}
.mask{width:100%;height:100%;position:absolute;top:0;left:0;background:#000;opacity:0.6;filter:alpha(opacity=60);}
.b{width:100%;text-align:center;height:400px;position:absolute;top:50%;margin-top:-230px}.a{width:150px;height:50px;margin-top:30px}.a a{display:block;float:left;width:150px;height:50px;background:#fff;text-align:center;line-height:50px;font-size:18px;border-radius:25px;color:#333}.a a:hover{color:#000;box-shadow:#fff 0 0 20px}
p{color:#fff;margin-top:40px;font-size:24px;}
#num{margin:0 5px;font-weight:bold;}
</style>

<body>
<div class="whole">
	<img src="images/back.jpg" />
    <div class="mask"></div>
</div>
<div class="b">
		<p style="font-family:'Microsoft YaHei UI Light';color: white;font-size:36px;">已发送邮件，请注意查收</p>
		<p>
		</p>
		<a style="font-family:'Microsoft YaHei UI Light';color: white;font-size:20px;"
		href="${pageContext.request.contextPath}/client/RegisterLogin.jsp">
									<span style="font-family:'Microsoft YaHei UI Light';color: white;font-size:20px;" id="second">5</span>秒后自动为您转跳回登录页
								</a>
	</div>

</body>
</html>