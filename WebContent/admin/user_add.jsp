<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-用户添加</title>
<link href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery.min.js"></script>
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
			<li>用户管理</li>
			<li>用户添加</li>
		</ul>
	</div>
<form action="${pageContext.request.contextPath}/UserinfoinsertServlet" class="form-horizontal" method="post">
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户昵称</label>
					<div class="col-xs-9 ">
						<input type="text" name="username" class="form-control" placeholder="请输入昵称"/>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户邮箱</label>
					<div class="col-xs-9 ">
						<input type="text" name="email" class="form-control" placeholder="请输入用户邮箱" />
					</div>
				</div>
			</div>
		</div>

		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">账号信息</h5>
		<!-- 开始5 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户账号</label>
					<div class="col-xs-9">
						<input type="text" name="id" class="form-control" placeholder="请输入用户账号"/>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户密码</label>
					<div class="col-xs-9 ">
						<input type="text" name="password" class="form-control" placeholder="请输入用户密码" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户角色</label>
					<div class="col-xs-9 ">
						<input type="text" name="role" class="form-control" placeholder="请输入用户角色（1:管理员2:普通用户）"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="submit" class="btn btn-success" value="保存用户" /> <input
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