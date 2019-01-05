<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>首页</title>
    <style type="text/css">
        .demo{width:760px; margin:20px auto 0 auto; height:20px;}
        .button1 
        {
            display: inline-block;
            outline: none;
            position: absolute;
            bottom:4%;
            left:30%;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            font: 16px/100% 'Microsoft yahei',Arial, Helvetica, sans-serif;
            padding: .5em 2em .55em;
            text-shadow: 0 1px 1px rgba(0,0,0,.3);
            -webkit-border-radius: .5em;
            -moz-border-radius: .5em;
            border-radius: .5em;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
            box-shadow: 0 1px 2px rgba(0,0,0,.2);
        }
        .button2 
        {
            display: inline-block;
            outline: none;
            position: absolute;
            bottom:4%;
            right:30%;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            font: 16px/100% 'Microsoft yahei',Arial, Helvetica, sans-serif;
            padding: .5em 2em .55em;
            text-shadow: 0 1px 1px rgba(0,0,0,.3);
            -webkit-border-radius: .5em;
            -moz-border-radius: .5em;
            border-radius: .5em;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
            box-shadow: 0 1px 2px rgba(0,0,0,.2);
        }
        .button1:hover 
        {
            text-decoration: none;
        }
        .button1:active 
        {
            position: absolute;
            bottom:4%;
        }
        .button2:hover 
        {
            text-decoration: none;
        }
        .button2:active 
        {
            position: absolute;
            bottom:4%;
        }
        .white
        {
            color: #606060;
            border: solid 1px #b7b7b7;
            background: #fff;
            background: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#ededed));
            background: -moz-linear-gradient(top,  #fff,  #ededed);
            filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#ededed');
        }
        .white:hover 
        {
            background: #ededed;
            background: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#dcdcdc));
            background: -moz-linear-gradient(top,  #fff,  #dcdcdc);
            filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#dcdcdc');
        }
        .white:active 
        {
            color: #999;
            background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#fff));
            background: -moz-linear-gradient(top,  #ededed,  #fff);
            filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#ededed', endColorstr='#ffffff');
        }
    </style>
</head>
<body>
<video autoplay muted loop style="width:100%;">
<source src="<%=request.getContextPath()%>/得数据者得天下.mp4">
<style>
    body
    {
        margin:0;
    }
    video
    {
        position: absolute;
        bottom:0;
        min-width:100%;
        min-height:100%;
        width:auto;
        height:auto;
        z-index: -9999;
    }
</style>
</video>
    <div class="demo">
        <button1 class="button1 white" onclick="location='${pageContext.request.contextPath }/search.jsp'">开始搜索</button1>
        <button2 class="button2 white" onclick="location='${pageContext.request.contextPath }/contactus.html'">关于我们</button2>
    </div>
</div>

</div>
<p style="font-family:'Old English Text MT';color: white;font-size:72px;text-indent:1.5em;margin:1cm 4cm 3cm 1cm">RDF</p>
</body>
</html>