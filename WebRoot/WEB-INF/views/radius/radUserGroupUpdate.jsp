<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ include file="../common/taglib.jsp"%>
<html>
<head>
<title>RadUserGroup管理页面</title>
<%@ include file="../common/importHeader.jsp"%>
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
						<li><a href="#">Radius管理</a></li>
						<li><a href="#">Rad分组管理</a></li>
						<li><a href="#">Rad用户分组</a></li>
					</ul>
				</div>
				<!-- form starts -->
				<div class="panel panel-primary panel-mo">
					<div class="panel-heading">RadUserGroup编辑页面</div>
					<div class="panel-body">
						<form action="${context}/radius/group/radusergoup/${radUserGroup.id}/update" class="form-horizontal" method="POST">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">用户名称</label>
								<div class="col-sm-8">
									<input type="text" id="username" name="username" value="${radUserGroup.username}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="groupname" class="col-sm-2 control-label">分组名称</label>
								<div class="col-sm-8">
									<input type="text" id="groupname" name="groupname" value="${radUserGroup.groupname}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="priority" class="col-sm-2 control-label">优先次序</label>
								<div class="col-sm-8">
									<input type="text" id="priority" name="priority" value="${radUserGroup.priority}" class="form-control"/>
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
	</div>
	<!--/.fluid-container-->
	<%@ include file="../common/importJS.jsp"%>
</body>
</html>