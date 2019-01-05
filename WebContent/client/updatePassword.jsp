<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>个人信息-密码修改</title>
<link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
</head>
<body>
<%
if(session.getAttribute("luser")!=null)
{
%>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>个人信息</li>
			<li>密码修改</li>
		</ul>
	</div>
<form action="${pageContext.request.contextPath}/ChangeServlet" method="post" >
<!-- 旧密码 -->

<p></p>
            <div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label"><p></p>旧密码</label>
					<div class="col-xs-9 ">
						<input type="password" name="oldpwd" id="oldpwd" class="form-control" placeholder="请输入旧密码"  />
					</div>
				</div>
			</div>
			</div>
			<!-- <div class="oldpwd">

				<span style="position:absolute;left:4%;top:4%">旧密码      </span>
				<input style="position:absolute;left:11%;top:3%" type="password" name="oldpwd" id="oldpwd" placeholder="请填写旧密码"> 
				<span style="color: #F00; margin-top: 5px;position:absolute;left:26%;top:3%">* </span> <span id="oldpwd2"></span>

			</div> -->
			<p></p>
			<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label"><p></p>新密码</label>
					<div class="col-xs-9 ">
						<input type="password" name="newpwd" id="newpwd" class="form-control" placeholder="请输入新密码" />
					</div>
				</div>
			</div>
			</div>
		<!-- 	<div class="newpwd">

				<span style="position:absolute;left:4%;top:31%">新密码</span>
				<input style="position:absolute;left:11%;top:30%" type="password" name="newpwd" id="newpwd" placeholder="请填写新密码"> 
				<span style="color: #F00; margin-top: 5px;position:absolute;left:26%;top:30%">* </span> <span id="newpwd2"></span>

			</div> -->
			<p></p>
			<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label"><p></p>确认密码</label>
					<div class="col-xs-9 ">
						<input type="password" name="confpwd" id="confpwd" class="form-control" placeholder="请确认新密码" />
					</div>
				</div>
			</div>
			</div>
			<%-- <div class="confpwd">

				<span style="position:absolute;left:2%;top:58%">确认新密码</span>
				<input style="position:absolute;left:11%;top:57%"type="password" name="confpwd" id="confpwd" placeholder="请确认新密码"> 
				<span style="color: #F00; margin-top: 5px;position:absolute;left:26%;top:57%">* </span> <span id="confpwd2"></span>
            <input type="hidden" name="ID" id="ID" value="${session.getAttribute("userid")}"/>
			</div> --%>
<!-- div class="form-actions"> -->
<p></p>
<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="submit" class="btn btn-success" value="确认修改" /> <input
					type="reset" class="btn btn-danger" value="重置信息" />
			</div>
		</div>

</form>
<%} %>
<%
if(session.getAttribute("luser")==null)
{
%>
<script type="text/javascript">window.self.location="/rdf/error/privilege.jsp"</script>
<%} %> 
</body>
</html>