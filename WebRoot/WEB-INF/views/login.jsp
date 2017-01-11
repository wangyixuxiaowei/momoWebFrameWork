<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="common/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>it's Momo | 登录</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="capitek,login,view" name="description" />
	<meta content="xuxw" name="author" />
	<meta name="MobileOptimized" content="320">
	<link href="<c:url value='/plugins/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/plugins/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/css/login.css'/>" rel="stylesheet" type="text/css"/>
	<script src="<c:url value='/plugins/jquery/jquery-1.10.2.min.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/plugins/bootstrap/js/bootstrap.min.js'/>" type="text/javascript" ></script>
	<script src="<c:url value='/js/jquery.validate.min.js'/>" type="text/javascript"></script> 
	<script src="<c:url value='/js/user.validate.js'/>" type="text/javascript"></script> 
	<link rel="shortcut icon" href="favicon.ico" />
	<style>
		body{
			font-size:12px;
			font-family:"Microsoft Yahei";
			background:url("<c:url value='/images/login_view.jpg'/>") no-repeat -180px -100px;
			height:800px;
		}
		.iconauth {
		    color: #000;
		    font-size: 22px;
		    font-style: normal;
		}
		.self-login{
			width:100px;
			border:none;
		}
	</style>
</head>

<!-- BEGIN BODY -->
<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo"></div>
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form:form modelAttribute="contentModel" class="login-form form-horizontal" method="POST">
			<h3 class="form-title">账户登录</h3>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="col-sm-offset-1 col-sm-1 control-label">
					<span>
						<i class="fa fa-user iconauth"></i>
					</span>
				</label>
				<div class="input-icon col-sm-9">
					<form:input path="username" class="form-control placeholder-no-fix" autocomplete="off" placeholder="用户名"/><br/>
					<form:errors path="username" class="field-has-error"></form:errors>  
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-offset-1 col-sm-1 control-label">
					<span>
						<i class="fa fa-lock iconauth"></i>
					</span>
				</label>
				<div class="input-icon col-sm-9">
					<form:password path="password" class="form-control placeholder-no-fix" autocomplete="off" placeholder="密码"/><br/>
					<form:errors path="password" class="field-has-error"></form:errors>    
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<button type="submit" class="btn btn-info pull-right self-login">登 录</button>            
				</div>
			</div>
		</form:form>
		<!-- END LOGIN FORM -->        
	</div>
	<script type="text/javascript">
		$(function() {
		  	AccountValidate.handleLogin();
	    });
	</script>
</body>
</html>