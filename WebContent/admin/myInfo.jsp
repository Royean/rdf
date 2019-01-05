<%@page import="beanEntity.User"%>
<%@page import="dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>查看个人信息</title>
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
      <form action="${pageContext.request.contextPath}/MyinfoupdateServlet" class="form-horizontal" method="post">
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 第一行 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户账号</label>
					<div class="col-xs-9">
						<input type="text" readonly="readonly" value="${myuser.id }" name="id" class="form-control" placeholder="请输入用户账号" value="admin123" />
						<!-- 账号设置为只读，不可以修改的模式 -->
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户昵称</label>
					<div class="col-xs-9 ">
						<input type="text" value="${myuser.username}" name="username" class="form-control" placeholder="请输入昵称" value="高富帅"  />
					</div>
				</div>
			</div>
		</div>
		<!-- 第二行 -->
		<div class="row">
		<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">密码</label>
					<div class="col-xs-9">
						<input type="password" readonly="readonly" value="${myuser.password }" name="password" class="form-control" placeholder="请输入密码" value="admin123" />
						<!-- 注册时间设置为只读，不可以修改的模式 -->
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">注册时间</label>
					<div class="col-xs-9">
						<input type="text" readonly="readonly" value="${myuser.registTime }" name="registTime" class="form-control" placeholder="请输入注册时间" value="admin123" />
						<!-- 注册时间设置为只读，不可以修改的模式 -->
					</div>
				</div>
			</div>
		</div>
		<!-- 第三行 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">邮箱</label>
					<div class="col-xs-9">
						<input type="text" value="${myuser.email }" name="email" class="form-control" placeholder="请输入邮箱" value="admin123" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户角色</label>
					<div class="col-xs-9 ">
						<input type="text" readonly="readonly" value="${myuser.role}" name="role" class="form-control" placeholder="请输入用户角色（1:管理员2:普通用户）" value="20" />
						(1:管理员   2:普通用户)
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-xs-offset-4">
				<input type="submit" class="btn btn-success" value="保存用户" /> <input
					type="reset" class="btn btn-danger" value="重置信息" /> <a
				href="${pageContext.request.contextPath}/admin/updatePassword.jsp" class="btn btn-success">修改密码</a>
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