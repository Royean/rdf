<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<title>登录</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/sign-up-login.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/inputEffect.css" />
<link rel="stylesheet" href="css/tooltips.css" />
<link rel="stylesheet" href="css/spop.min.css" />

<script src="js/jquery.min.js"></script>
<script src="js/snow.js"></script>
<script src="js/jquery.pure.tooltips.js"></script>
<script src="js/spop.min.js"></script>
<script>	
	(function() 
	{
	  	// trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
	  	if (!String.prototype.trim) 
	  	{
	  		(function() 
	  		{
	  			// Make sure we trim BOM and NBSP
	  			var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
	  			String.prototype.trim = function() 
	  			{
	  				return this.replace(rtrim, '');
	  			};
			  }
			)
			();
		}
	  	
		[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach
		( 
			function( inputEl ) 
			{
			    // in case the input is already filled..
			    if( inputEl.value.trim() !== '' ) 
			    {
				  classie.add( inputEl.parentNode, 'input--filled' );
			    }
			    // events:
			    inputEl.addEventListener( 'focus', onInputFocus );
			    inputEl.addEventListener( 'blur', onInputBlur );
		    } 
		);
		
		function onInputFocus( ev ) 
		{
			classie.add( ev.target.parentNode, 'input--filled' );
		}
		
		function onInputBlur( ev ) 
		{
			if( ev.target.value.trim() === '' ) 
			{
				classie.remove( ev.target.parentNode, 'input--filled' );
			}
		}
		
	}
	) 
	();
	
	$(function() 
	{	
		$('#login #login-password').focus
		(
			function() 
		    {
			    $('.login-owl').addClass('password');
		    }).blur
		    (
		        function() 
		        {
			        $('.login-owl').removeClass('password');
		        }
		    );		
		$('#login #register-password').focus
		(
			function() 
			{
			    $('.register-owl').addClass('password');
		    }).blur
		    (
		    	function() 
		    	{
			        $('.register-owl').removeClass('password');
		        }
		    );
		$('#login #register-repassword').focus
		(
			function() 
			{
			    $('.register-owl').addClass('password');
		    }).blur
		    (
		    	function() 
		    	{
			        $('.register-owl').removeClass('password');
		        }
		    );
		$('#login #forget-password').focus
		(
			function() 
			{
			    $('.forget-owl').addClass('password');
		    }).blur
		    (
		    	function() 
		    	{
			        $('.forget-owl').removeClass('password');
		        }
		    );
	});
	//注册
	function conf()
	{
		var password = $("#register-password").val(),
			repassword = $("#register-repassword").val(),
			flag = true,
			validatecode = null;
		//判断用户名密码是否为空
		var regExp = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9_!@#$%^&*()]{8,16}$");
		if(password == "")
		{
			$.pt
			(
				{
        		   target: $("#register-password"),
        		   position: 'r',
        		   align: 't',
        		   width: 'auto',
        		   height: 'auto',
        		   content:"密码不能为空"
        	    }
			);
			flag = false;
			return false;
		} 
		else if(!regExp.test(password))
		{
			$.pt
			(
				{
        		   target: $("#register-password"),
        		   position: 'r',
        		   align: 't',
        		   width: 'auto',
        		   height: 'auto',
        		   content:"至少一个小写字母，一个大写字母和一个数字，8-16位,特殊符号只允许_!@#$%^&*()"
        	    }
			);
			flag = false;
			return false;
		} 
		else if(password != repassword)
		{
			$.pt
			(
				{
	        	   target: $("#register-repassword"),
	        	   position: 'r',
	        	   align: 't',
	        	   width: 'auto',
	        	   height: 'auto',
	        	   content:"两次输入的密码不一致"
	        	 }
			);
			flag = false;
			return false;
		}
		return flag;
	}

</script>
<style type="text/css">
html{width: 100%; height: 100%;}

body
{
	background-repeat: no-repeat;

	background-position: center center #2D0F0F;

	background-color: #00BDDC;

	background-image: url(images/497c4fb82d5fd00159370cc1dbd9230a.jpg);

	background-size: 100% 100%;
}

.snow-container { position: fixed; top: 0; left: 0; width: 100%; height: 100%; pointer-events: none; z-index: 100001; }

</style>
</head>
<body>
<%
if(session.getAttribute("luser")!=null)
{
%>
	<!-- 雪花背景 -->
	<div class="snow-container"></div>
	<!-- 登录控件 -->
	<div id="login">
		<input id="tab-1" type="radio" name="tab" class="sign-in hidden" checked />
		<input id="tab-2" type="radio" name="tab" class="sign-up hidden" />
		<input id="tab-3" type="radio" name="tab" class="sign-out hidden" />
		<div class="wrapper">
	<div class="login sign-in-htm">
				<form class="container offset1 loginform" action="<%=request.getContextPath()%>/ResetsuccessServlet" method="post" onsubmit="return conf();">
					<!-- 猫头鹰控件 -->
					<div id="owl-login" class="login-owl">
						<div class="hand"></div>
						<div class="hand hand-r"></div>
						<div class="arms">
							<div class="arm"></div>
							<div class="arm arm-r"></div>
						</div>
					</div>
					<div class="pad input-container">
					<%  
                          int id=Integer.parseInt(request.getParameter("ffid"));  
                     %>
						<section class="content">
							<span class="input input--hideo">
								<input name="idd" readonly="readonly" class="input__field input__field--hideo" type="text" value="<%=id %>" id="id" placeholder="账号" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
					<span class="input input--hideo">
								<input name="rpwd" class="input__field input__field--hideo" type="password" id="register-password" placeholder="请输入新密码" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="register-repassword" placeholder="请确认新密码" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-repassword">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
						
						</section>
					</div>
					<div class="form-actions">
						<input class="btn btn-primary" type="submit" name="submit" value="确认" 
							style="color:white;"/>
					</div>
				</form>
			</div>
			</div>
	</div>
	</div>
	<%} %>
<%
if(session.getAttribute("luser")==null)
{
%>
<script type="text/javascript">window.self.location="/rdf/error/privilege.jsp"</script>
<%} %> 
</body>
</html>