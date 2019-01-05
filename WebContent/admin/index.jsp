<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible"  content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/assets/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/assets/css/admin.css">
    <link rel="icon" href="/favicon.ico">
    <title>个人中心</title>
</head>
<body class="layui-layout-body">
<%
if(session.getAttribute("luser")!=null)
{
%>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header custom-header">

        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item slide-sidebar" lay-unselect>
                <a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
            </li>
        </ul>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">退出</a>
                <dl class="layui-nav-child">

                    <dd><a href="${pageContext.request.contextPath}/client/search2.jsp">返回搜索</a></dd>
                    <dd><a href="${pageContext.request.contextPath}/logout">退出登陆</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side custom-admin">
        <div class="layui-side-scroll">

            <div class="custom-logo">
                <img src="${pageContext.request.contextPath}/admin/assets/images/logo.png" alt=""/>
                <h1>管理员中心</h1>
            </div>
            <ul id="Nav" class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe609;</i>
                        <em>主页</em>
                    </a> 
                    <dl class="layui-nav-child">
                        <dd class="layui-this">
                        <a href="${pageContext.request.contextPath}/FindmyinfoServlet">个人信息</a></dd>
                        <%-- <a href="${pageContext.request.contextPath}/admin/myInfo.jsp">个人信息</a></dd> --%>
                    </dl>
                </li>
 
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe612;</i>
                        <em>用户</em>                          
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/UserinfoselectServlet">用户管理</a></dd>
                        <!--  <dd><a href="views/operaterule.html">权限配置</a></dd> -->
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe621;</i>
                        <em>日志</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/AdquerylogServlet">搜索日志</a></dd>
                    </dl>
                </li>
            </ul>

        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
            <ul id="appTabs" class="layui-tab-title custom-tab"></ul>
            <div id="appTabPage" class="layui-tab-content"></div>
        </div>
    </div>

    <div class="mobile-mask"></div>
</div>
<script src="${pageContext.request.contextPath}/admin/assets/layui.js"></script>
<script src="${pageContext.request.contextPath}/admin/index.js" data-main="home"></script>
 <%} %>
<%
if(session.getAttribute("luser")==null)
{
%>
<script type="text/javascript">window.self.location="/rdf/error/privilege.jsp"</script>
<%} %> 
</body>
</html>
