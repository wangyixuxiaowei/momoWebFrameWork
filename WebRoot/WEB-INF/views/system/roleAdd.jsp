<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ include file="../common/taglib.jsp"%>
<html>
<head>
<title>用户信息管理页面</title>
<%@ include file="../common/importHeader.jsp"%>
<link href="<c:url value='/bower_components/easyui/themes/default/easyui.css'/>" rel='stylesheet'>
<style type="text/css">
	.panel-body-mo{
		border-color: none;
		border-width: none;
    	border-style: none;
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
						<li><a href="#">系统管理</a></li>
						<li><a href="#">权限管理</a></li>
						<li><a href="#">角色管理</a></li>
					</ul>
				</div>
				<!-- form starts -->
				<div class="panel panel-primary panel-mo">
					<div class="panel-heading">角色添加页面</div>
					<div class="panel-body panel-body-mo">
						<form action="${context}/system/perms/role/save" class="form-horizontal" method="POST">
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">角色名称</label>
								<div class="col-sm-8">
									<input type="text" id="name" name="name" class="form-control"
										placeholder="【必填项】" />
								</div>
							</div>
							<div class="form-group">
								<label for="memo" class="col-sm-2 control-label">备注</label>
								<div class="col-sm-8">
									<textarea id="memo" class="form-control" rows="3" name="memo"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="permValues" class="col-sm-2 control-label">权限列表</label>
								<div class="col-sm-8">
									<span id="cc" class="form-control easyui-combotree" data-options="url:'${context}/system/perms/role/permtree',method:'post'" multiple style="width:200px;"></span>
									<input type="hidden" id="permValues" value="" name="permValues" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" class="btn btn-primary" value="保存" /> 
									<input type="reset" class="btn btn-warning" value="重置" /> 
									<input type="hidden" name="_method" value="PUT" />
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- form ends -->
				<!-- content ends -->
			</div>
			<!--/#content.col-md-0-->
		</div>
		<!--/fluid-row-->

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">Ã</button>
						<h3>Settings</h3>
					</div>
					<div class="modal-body">
						<p>Here settings can be configured...</p>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
						<a href="#" class="btn btn-primary" data-dismiss="modal">Save
							changes</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/.fluid-container-->
	<%@ include file="../common/importJS.jsp"%>
	<script src="<c:url value='/bower_components/easyui/jquery.easyui.min.js'/>"></script>
	<script src="<c:url value='/js/json2.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/self.momo.tools.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/js/self.role.add.js'/>" type="text/javascript"></script>
</body>
</html>