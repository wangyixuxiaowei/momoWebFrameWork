<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ include file="../common/taglib.jsp"%>
<html>
	<head>
		<title>用户钻取后管理页面</title>
		<%@ include file="../common/importHeader.jsp"%>
		<link href="<c:url value='/css/self.momo.edittable.css'/>" rel='stylesheet'>
	</head>
	<body>
		<%@ include file="../common/top.jsp"%>
		<div class="ch-container">
			<div class="row">
				<%@ include file="../common/leftMenu.jsp"%>
				<!-- content starts -->
				<div id="content" class="col-lg-10 col-sm-10">
					<div>
						<ul class="breadcrumb">
						<li><a href="#">Radius管理</a></li>
						<li><a href="#">用户管理</a></li>
						<li><a href="#">钻取管理</a></li>
						</ul>
					</div>
					<div class="list_container">
						<!-- radcheck start -->
						<div class="panel panel-default panel-mo">
							<div class="panel-heading">
								radcheck管理列表
							</div>
							<div class="panel-body list" id="check_list">
								
							</div>
						</div>
						<!-- radcheck end -->
						<!-- radreply start -->
						<div class="panel panel-default panel-mo">
							<div class="panel-heading">
								radreply管理列表
							</div>
							<div class="panel-body list" id="reply_list">
							</div>
						</div>
						<!-- radreply end -->
						<!-- radusergroup start -->
						<div class="panel panel-default panel-mo">
							<div class="panel-heading">
								radusergroup管理列表
							</div>
							<div class="panel-body list" id="group_list">
							</div>
						</div>
						<!-- radusergroup end -->
					</div>
				</div>
				<div style="display:none;" id="template">
					<div class="list-row" id="1">
						<div class="list-col"><input type="text" name="" value="" readonly></input></div>
						<div class="list-col"><input type="text" name="" value="" readonly></input></div>
						<div class="list-col"><input type="text" name="" value="" readonly></input></div>
						<div class="list-col action"><a href="#" class="e">编辑</a>&nbsp;<a href="#" class="d">删除</a></div>
					</div>
				</div>
				<!-- content ends -->
			</div>
			<!--/fluid-row-->
		</div>
		<!--/.fluid-container-->
		<%@ include file="../common/importJS.jsp"%>
		<script src="<c:url value='/js/json2.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/js/self.momo.edittable.js'/>" type="text/javascript"></script>
		<script type="text/javascript">
			var params_username = '${username}';
		</script>
		<script src="<c:url value='/js/self.raduserdetail.js'/>" type="text/javascript"></script>
	</body>
</html>
