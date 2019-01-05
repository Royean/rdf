<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.io.BufferedReader"
    import="java.io.IOException"
	import="java.io.InputStreamReader"
	import="java.io.PrintWriter"
	import="java.io.FileInputStream"
	import="java.io.FileReader"
	import="java.net.Socket"
	import="java.util.Scanner"
	import="java.io.BufferedWriter"
	import="java.io.File"
	import="java.io.FileWriter"
    import="java.io.OutputStream"
    import="dao.ResultDao"
    import="beanEntity.Result"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结果页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/client/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/client/js/angular.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/client/js/index.js"></script>
</head>

<body ng-app="myApp" ng-controller="productController">
<img src="/rdf/RDF2.png" height="75" width="100" />
<div class="container">
  <form class="navbar-form pull-left" action="<%=request.getContextPath()%>/CommonKeyServlet" method="post">
    <input name="keyword" type="text" class="span2" placeholder="Search" ng-model="search.id"><!--这里设置按那块搜索项目-->
  </form>
  <table class="table">
    <thead>
      <tr> 
        <!--dropup：true 这个class就显示，即升序，否则不显示！--> 
        <!--注意，这里是ng-class，还有droupup:order中间是没有任何空格的！！！！-->
        <th ng-class="{dropup:order ===''}" ng-click="changeOrder('id')"> 实体<span class="caret"></span> </th>
        <th ng-class="{dropup:order === ''}" ng-click="changeOrder('price')"> 下载<span class="caret"></span> </th>
      </tr>
    </thead>
    <tbody>
      <!--<tr ng-repeat="product in products | filter:{id:search}">--> 
      <!--order+orderType注意这两个字段是有顺序的 不能反着写-->
      <!-- <tr ng-repeat="product in products | filter:search | orderBy : order+orderType"> -->
        <%

	Socket client =null; 
	BufferedWriter writer=null;		
	BufferedReader input=null;
	OutputStream os= null;
	String keyword="ls\n"+request.getParameter("keyword");
	System.out.println(keyword);
	try {
		client=new Socket("127.0.0.1", 8008);
		os = client.getOutputStream();
		os.write(keyword.getBytes());
		os.flush();
		client.shutdownOutput();
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writer = new BufferedWriter(new FileWriter(new File("/home/ty/result.txt")));
        String line;
        //if(request.getParameter("key_id")==null) System.out.println("!!!!null");
        int i=0;
        while (true) { 
            line = input.readLine();
			if(line==null) {
            	break;	
            }
			
			
	        System.out.println("搜索结果：" + line);
	        writer.write(line+"\n");  
	        if(i%4==0) {
	        	if(line.contains("first")) {
	        		
	        	}
	        	else{
	            //System.out.println("point 1");
	            ResultDao resultdao=new ResultDao();
	            Result result=new Result();
	            //if(request.Parameter("key_id")==null) System.out.println("null2");
	            System.out.println(Integer.valueOf(request.getParameter("key_id").toString()).intValue());
	          
	            result.setkey_ID(Integer.valueOf(request.getParameter("key_id").toString()).intValue());
	            //System.out.println(Integer.valueOf(request.getAttribute("key_id").toString()).intValue());
	            //System.out.println(line);
	            result.setresult(line);
	            System.out.println(Integer.valueOf(request.getParameter("key_id").toString()).intValue());
	            System.out.println(line);
	            resultdao.addResult(result);
	        	}
			}
			i++;
    	}
	}catch (Exception e) {
    	e.printStackTrace();
    }
	finally {
		if(client!=null)
        	client.close();
		if(writer!=null)
        	writer.close();
		if(input!=null)
        	input.close();
		if(os!=null){
			os.close();
		}
    }

%>

<%
    BufferedReader bufferedReader =null;
	try{
		bufferedReader = new BufferedReader(new FileReader("/home/ty/result.txt"));
    	String txt;
    	String buttonName;
    	int i=0;
    	while((txt = bufferedReader.readLine()) != null) {
    		//i++;
    		if(i%4==0) {
    			if(txt.contains("first")) {
    				%><tr>
    	    		<td><%=txt%></td>
    	    		<td></td>
    	    		<td></td></tr><%
    			}
    			else{
    		%><tr>
    		<td><%=txt%></td>
    		
    		<td><a href="SPARQL/NO.<%=String.valueOf(i)%>.txt" download="SPARQL.txt">点击下载</a></td></tr><%
        		System.out.print(txt+"!!!");
    			}
    		}
    		i++;
    	}
    	
	}catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		if(bufferedReader!=null)
		bufferedReader.close();
	}
%>
      </tr>
    </tbody>
  </table>
</div>


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
</body>
</html>