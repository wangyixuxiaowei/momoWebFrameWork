<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ include file="../common/taglib.jsp"%>
<html>
<head>
<title>NAS管理页面</title>
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
						<li><a href="#">NAS管理</a></li>
					</ul>
				</div>
				<!-- form starts -->
				<div class="panel panel-primary panel-mo">
					<div class="panel-heading">NAS编辑页面</div>
					<div class="panel-body">
						<form action="${context}/radius/nas/${nas.id}/update" class="form-horizontal" method="POST">
							<div class="form-group">
								<label for="nasname" class="col-sm-2 control-label">NAS名称</label>
								<div class="col-sm-8">
									<input type="text" id="nasname" name="nasname" value="${nas.nasname}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="shortname" class="col-sm-2 control-label">名称简写</label>
								<div class="col-sm-8">
									<input type="text" id="shortname" name="shortname" value="${nas.shortname}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="type" class="col-sm-2 control-label">类型</label>
								<div class="col-sm-8">
									<input type="text" id="type" name="type" value="${nas.type}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="ports" class="col-sm-2 control-label">端口</label>
								<div class="col-sm-8">
									<input type="text" id="ports" name="ports" value="${nas.ports}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="secret" class="col-sm-2 control-label">密钥</label>
								<div class="col-sm-8">
									<input type="text" id="secret" name="secret" value="${nas.secret}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="server" class="col-sm-2 control-label">服务器</label>
								<div class="col-sm-8">
									<input type="text" id="server" name="server" value="${nas.server}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="community" class="col-sm-2 control-label">群组</label>
								<div class="col-sm-8">
									<input type="text" id="community" name="community" value="${nas.community}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-sm-2 control-label">描述</label>
								<div class="col-sm-8">
									<textarea id="description" name="description" rows="3" class="form-control"></textarea>
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
	<script type="text/javascript">
		$(function(){
			$('#memo').val("${nas.description}");
		});
	</script>
</body>
</html>