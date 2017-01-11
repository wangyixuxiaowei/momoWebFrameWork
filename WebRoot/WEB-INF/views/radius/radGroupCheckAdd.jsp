<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ include file="../common/taglib.jsp"%>
<html>
<head>
<title>RadGroupCheck管理页面</title>
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
						<li><a href="#">Rad分组检查</a></li>
					</ul>
				</div>
				<!-- form starts -->
				<div class="panel panel-primary panel-mo">
					<div class="panel-heading">RadGroupCheck添加页面</div>
					<div class="panel-body">
						<form:form modelAttribute="contentModel" action="${context}/radius/group/radgroupcheck/save" class="form-horizontal" method="POST">
							<div class="form-group">
								<label for="groupname" class="col-sm-2 control-label">分组名称</label>
								<div class="col-sm-8">
									<form:input path="groupname" name="groupname" class="form-control"
										placeholder="【必填项】" />
									<form:errors path="groupname" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="attribute" class="col-sm-2 control-label">属性名称</label>
								<div class="col-sm-8">
									<form:input path="attribute" name="attribute" class="form-control"
										placeholder="【必填项】" />
									<form:errors path="attribute" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="op" class="col-sm-2 control-label">操作符</label>
								<div class="col-sm-8">
									<form:input path="op" name="op" class="form-control"
										placeholder="【必填项】" />
									<form:errors path="op" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="value" class="col-sm-2 control-label">属性值</label>
								<div class="col-sm-8">
									<form:input path="value" name="value" class="form-control"
										placeholder="【必填项】" />
									<form:errors path="value" class="field-has-error"></form:errors>
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