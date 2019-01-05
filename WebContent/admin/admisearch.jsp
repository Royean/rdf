<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>管理员搜索界面</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css">
<script type="text/javascript">
function logout()
{	
	document.getElementById("form1").action="${pageContext.request.contextPath}/logout"
	document.getElementById("form1").submit();
}

function Addkey()
{	
	document.getElementById("form1").action="${pageContext.request.contextPath}/KeyServlet"
	document.getElementById("form1").submit();
}
document.onkeydown = function(e)
{
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) 
    {
        Addkey();
    }
}
</script>
</head>
<body>
<%
if(session.getAttribute("luser")!=null)
{
%>
<form id="form1" name="form1" action="<%=request.getContextPath()%>/KeyServlet" method="post">
<div id="top-image">
	<input type="hidden" name="identity" value="admin">
   <input style="font-family:'YouYuan';font-size:24px;color:white;background-color:transparent;
   border:0;position:absolute;right:12%" type="button" value="个人中心"onclick="location='${pageContext.request.contextPath }/admin/index.jsp'">
   <input style="font-family:'YouYuan';font-size:24px;color:white;background-color:transparent;
   border:0;position:absolute;right:2%" type="button" value="退出登录" onclick="logout()">

   <div id="content" class="container center-block">
   <div class="jumbotron">

      <div class="container">
        <div style="text-align: center; width: 600px;">
        <img src="${pageContext.request.contextPath}/client/images/logo2.png" width="220px" height="130px" alt=""style="margin: 0 auto;"/>
        </div>
        <p>Start your searching by inputing key words with interval ";"</p>
        <div class="input-group-lg">
          <input name="keyword" type="text" class="form-control" placeholder="input key word:" aria-describedby="sizing-addon1" onkeydown="">
          </div>
      </div>
    </div>
  </div>
</div>
</form>

<script src="${pageContext.request.contextPath}/client/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/client/js/ios-parallax.js"></script> 
<script type="text/javascript">
$(document).ready(function() 
{
    $('#top-image').iosParallax
    (
       {
	      movementFactor: 50
       }
    );
}
);
</script>
<%} %>
<%
if(session.getAttribute("luser")==null)
{
%>
<script type="text/javascript">window.self.location="/rdf/error/privilege.jsp"</script>
<%} %> 
</body>
</html>
