<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="dao.ResultDao,net.sf.json.JSONArray,net.sf.json.JSONObject"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/assets/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/assets/css/view.css"/>
    <link rel="icon" href="/favicon.ico">
    <title>个人中心</title>
</head>
<body class="layui-view-body">
<%
if(session.getAttribute("luser")!=null)
{
%>
<%!JSONArray jsonArray=new JSONArray();
JSONObject jsonObject=new JSONObject();%>
<%
ResultDao resultDao=new ResultDao();
//String str=resultDao.returnResult(10000);
jsonArray=resultDao.returnResult1(Integer.valueOf(session.getAttribute("userid").toString()).intValue());
//jsonObject=null;
//jsonObject=jsonArray.getJSONObject(0);
%>
	<% System.out.println(request.getAttribute("jsonstr"));%>
    <div class="layui-content">
        <div class="layui-page-header">
            <div class="pagewrap">
                <span class="layui-breadcrumb">
                  <a href="">日志</a>
                  <a><cite>搜索日志</cite></a>
                </span>
                <h2 class="title">搜索日志</h2>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="form-box">
                        <div class="layui-form layui-form-item">
                            <div class="layui-inline">
                            <form action="${pageContext.request.contextPath}/ClientLogServlet" method="post">
                                <div class="layui-form-mid">关键词:</div>
                                <div class="layui-input-inline" style="width: 150px;">
                                  <input name="key" type="text" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">搜索结果:</div>
                                <div class="layui-input-inline" style="width: 150px;">
                                  <input name="result" type="text" autocomplete="off" class="layui-input">
                                </div>
                                
                                <button class="layui-btn layui-btn-blue">查询</button>
                                <!-- <button class="layui-btn layui-btn-primary" onclick="note_click()">重置</button> -->
                                <input type="reset" value="重置" class="layui-btn layui-btn-primary">
                            </form>
                            </div>
                        </div>
                        
                        <table id="demo"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/client/assets/layui.all.js"></script>
    <script>
	    var element = layui.element;
		var table = layui.table;
		var form = layui.form;
		
		<%
		if(session.getAttribute("result")!=null) {
			 //System.out.println("传值:"+session.getAttribute("result").toString());
			 jsonArray=JSONArray.fromObject(session.getAttribute("result"));
			 System.out.println("传值:"+jsonArray.toString());
		}
		%>
		var k = <%=jsonArray.toString()%>;
		//展示已知数据
		table.render({
		  elem: '#demo'
		  ,cols: [[ //标题栏
		            {field: 'id', title: '用户ID', width: 130, sort: true},
		            {field: 'result_ID', title: '结果ID', width: 130, sort: true}
		            ,{field: 'key_ID', title: '关键词ID', width: 130}
		            ,{field: 'key_word', title: '关键词', minWidth: 130}
		            ,{field: 'result', title: '结果', minWidth: 130}
		    /* ,{field: 'experience', title: '积分', width: 80, sort: true} */
		  ]]
		  ,data:k
		  ,skin: 'line' //表格风格
		  ,even: true
		  ,page: true //是否显示分页
		  ,limits: [5, 7, 10]
		  ,limit: 5 //每页默认显示的数量
		});
    </script>
    
    <script type="text/javascript">
    function note_click(){
    	var txt=document.getElementById('key');
    	txt.value ='ffff';
    	var txt2=document.getElementById('result');
    	txt2.value ='ffff';
    }
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