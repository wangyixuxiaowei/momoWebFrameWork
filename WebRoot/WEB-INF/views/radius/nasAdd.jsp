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
					<div class="panel-heading">NAS添加页面</div>
					<div class="panel-body">
						<form:form modelAttribute="contentModel" action="${context}/radius/nas/save" class="form-horizontal" method="POST">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">NAS名称</label>
								<div class="col-sm-8">
									<form:input path="nasname" name="nasname" class="form-control"
										placeholder="【必填项】" />
									<form:errors path="nasname" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="attribute" class="col-sm-2 control-label">名称简写</label>
								<div class="col-sm-8">
									<form:input path="shortname" name="shortname" class="form-control"/>
									<form:errors path="shortname" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="type" class="col-sm-2 control-label">类型</label>
								<div class="col-sm-8">
									<form:input path="type" name="type" class="form-control" />
									<form:errors path="type" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="ports" class="col-sm-2 control-label">端口</label>
								<div class="col-sm-8">
									<form:input path="ports" name="ports" class="form-control"/>
									<form:errors path="ports" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="secret" class="col-sm-2 control-label">密钥</label>
								<div class="col-sm-8">
									<form:input path="secret" name="secret" class="form-control" placeholder="【必填项】" />
									<form:errors path="secret" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="server" class="col-sm-2 control-label">服务器</label>
								<div class="col-sm-8">
									<form:input path="server" name="server" class="form-control"/>
									<form:errors path="server" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="community" class="col-sm-2 control-label">群组</label>
								<div class="col-sm-8">
									<form:input path="community" name="community" class="form-control"/>
									<form:errors path="community" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-sm-2 control-label">描述</label>
								<div class="col-sm-8">
									<form:textarea path="description" name="description" rows="5"
									class="form-control"/>
									<form:errors path="description" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" class="btn btn-primary" value="保存" /> 
									<input type="reset" class="btn btn-warning" value="重置" /> 
									<input type="hidden" name="_method" value="PUT" />
								</div>
							</div>
						</form:form>
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