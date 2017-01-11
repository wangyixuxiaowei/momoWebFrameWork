<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    String context = request.getContextPath();
    request.setAttribute("context",context);
%>
<!--导航栏 start-->
<div class="navbar navbar-default" role="navigation">
	<div class="navbar-inner">
		<button type="button" class="navbar-toggle pull-left animated flip">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#"> 
			<img alt="M" class="fa fa-cog fa-spin fa-fw hidden-xs" />
			<span>Momo</span>
		</a>
		<!-- user dropdown starts -->
		<div class="btn-group pull-right">
			<button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				<i class="glyphicon glyphicon-user"></i>
				<span class="hidden-sm hidden-xs"> 当前登录用户 ： ${sessionScope.currentUser.username} </span> 
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li><a href="#">选项</a></li>
				<li class="divider"></li>
				<li><a href="<c:url value='/auth/logout'/>">退出</a></li>
			</ul>
		</div>
		<!-- user dropdown ends -->
	</div>
</div>
<!--导航栏 end-->