<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="d" uri="http://displaytag.sf.net"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-用户查询</title>
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
			<li>用户查询</li>
		</ul>
	</div>
	<form action="${pageContext.request.contextPath}/UserinfoselectServlet" class="form-inline" method="post">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
				<label>账号:</label> 
				<input type="text" name="id" value="${user.id }" class="form-control" placeholder="请输入查询账号" />
				<label>昵称:</label> 
				<input type="text" name="username" value="${user.username }" class="form-control" placeholder="请输入查询昵称" />
				<select class="form-control" name="role">
					<option value="">全部</option>
					<option value="2" ${user.role==2?'selected':'' }>普通用户</option>
					<option value="1" ${user.role==1?'selected':'' }>管理员</option>
				</select> 
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> <a
				href="${pageContext.request.contextPath}/admin/user_add.jsp" class="btn btn-success">添加用户</a>
		</div>
		
		<div class="row" style="padding: 15px;">
			<d:table name="nlist" pagesize="5" requestURI="${pageContext.request.contextPath}/UserinfoselectServlet" class="table table-hover table-condensed">
				<d:column property="id" title="用户账号"></d:column>
				<d:column property="username" title="用户昵称"></d:column>
				<d:column property="password" title="用户密码"></d:column>
				<d:column property="email" title="用户邮箱"></d:column>
				<d:column property="registTime" title="注册时间"></d:column>
				<d:column property="role" title="角色(1:管理员 2:普通用户)"></d:column>			
				<d:column href="${pageContext.request.contextPath}/UserinfoupdateServlet" value="修改" title="修改" paramId="id" paramProperty="id"></d:column>
				<!-- 这里提交到的是相对应的servlet的doSet方法里面 -->
				
			</d:table>
			
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