<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../common/taglib.jsp"%>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>MoMo 首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Momo a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Momo">
<%@ include file="../common/importHeader.jsp"%>
<style type="text/css">
	.row-mo{
		margin-top: 80px;
	}
</style>
</head>
<body>
	<%@ include file="../common/top.jsp"%>
	<div class="ch-container">
		<div class="row">
			<%@ include file="../common/leftMenu.jsp"%>
			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->
				<div>
					<ul class="breadcrumb">
						<li><a href="#">首页</a></li>
						<li><a href="#">开始</a></li>
					</ul>
				</div>
				<div class="row row-mo">
					<div class="col-xs-12 col-md-4 text-center">
		           		<img src="<c:url value='/images/moindex.gif'/>">
		          	</div>
		          	<div class="col-xs-12 col-md-8">
		           		<h1>MOMO 管理系统框架</h1>
		           		<p>创建rest方式的web分层系统，集成了权限控制、模块管理功能以及通过CXF实现的WebService接口等功能.</p>
		           		<a class="btn btn-default" href="#"> 立刻开始吧 </a>
		         	</div>
				</div>
				<!-- content ends -->
			</div>
			<!--/#content.col-md-0-->
		</div>
		<!--/fluid-row-->
	</div>
	<!--/.fluid-container-->
	<%@ include file="../common/importJS.jsp"%>
</body>
</html>