<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-用户修改</title>
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
			<li>用户管理</li>
			<li>用户修改</li>
		</ul>
	</div>
<form action="${pageContext.request.contextPath}/UserinfoupdateServlet" class="form-horizontal" method="post">
		<input type="hidden" name="id" value="${user.id }"/>
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户昵称</label>
					<div class="col-xs-9 ">
						<input type="text" value="${user.username }" name="username" class="form-control" placeholder="请输入昵称" value="高富帅"  />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户邮箱</label>
					<div class="col-xs-9 ">
						<input type="text" value="${user.email }" name="email" class="form-control" placeholder="请输入用户邮箱" value="20" />
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
						<input type="text" readonly="readonly" value="${user.id }" name="id" class="form-control" placeholder="请输入用户账号" value="admin123" />
						<!-- 账号设置为只读，不可以修改的模式 -->
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户密码</label>
					<div class="col-xs-9 ">
						<input type="text" value="${user.password }" name="password" class="form-control" placeholder="请输入用户密码" value="123456" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户角色</label>
					<div class="col-xs-9 ">
						<input type="text" value="${user.role}" name="role" class="form-control" placeholder="请输入用户角色（1:管理员2:普通用户）" value="20" />
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