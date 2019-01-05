<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示信息</title>
</head>
<body>
<script type="text/javascript">
	//此页面用来提示添加，修改，删除信息的成功或者失败的信息
	alert('${info}');//提示信息，错误or成功
	//跳转到user_info.jsp查询页面，后面会修改为servlet
	//window.location="view/system/userinfo/userinfo_list.jsp";
	//这句话的作用是当插入成功 的时候就可以显示在页面下
	window.location="${pageContext.request.contextPath}/FindmyinfoServlet";
</script>
</body>
</html>