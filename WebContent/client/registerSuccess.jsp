<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" import="beanEntity.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>注册成功</title>
	<link rel="stylesheet" href="css/main.css" type="text/css" />
	<script type="text/javascript" src="js/my.js"></script>
</head>
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

<body class="main">
<div class="whole">
	<img src="images/back.jpg" />
    <div class="mask"></div>
</div>
<%
if(session.getAttribute("ruser")!=null)
{
%>
	<div class="b">
		<p style="font-family:'Microsoft YaHei UI Light';color: white;font-size:36px;">注册成功,别忘记激活帐户啊！</p>
		<p style="font-family:'Microsoft YaHei UI Light';color: white;font-size:36px;">申请账号为： <%out.println(session.getAttribute("rid")); %>	
		</p>
		<p>
		</p>
		<a style="font-family:'Microsoft YaHei UI Light';color: white;font-size:20px;"
		href="${pageContext.request.contextPath}/client/RegisterLogin.jsp">
									<span style="font-family:'Microsoft YaHei UI Light';color: white;font-size:20px;" id="second">10</span>秒后自动为您转跳回登录页
								</a>
	</div>
	<%-- <div id="divcontent">
		<table width="850px" border="0" cellspacing="0">
			<tr>
				<td style="padding:30px; text-align:center">
					<table width="60%" border="0" cellspacing="0" style="margin-top:70px">
						<tr>
							<!-- <td style="width:98">
								<img src="/clientimages/IconTexto_WebDev_009.jpg" width="128" height="128" />
							</td> -->
							<td style="padding-top:30px">
								<font style="font-weight:bold; color:#FF0000">注册成功,别忘记激活帐户啊！</font><br />
								<br /> 
								 <font style="font-weight:bold; color:#FF0000">申请账号为：</font>
								 <%out.println(session.getAttribute("rid")); %>								 							
								<br />
								<br />
								<a href="${pageContext.request.contextPath}/client/RegisterLogin.jsp">
									<span id="second">10</span>秒后自动为您转跳回登录页
								</a>
							</td>
						</tr>
					</table>
					<h1>&nbsp;</h1></td>
			</tr>
		</table>
	</div> --%>
	
<%
}
%>	
	
</body>
</html>
