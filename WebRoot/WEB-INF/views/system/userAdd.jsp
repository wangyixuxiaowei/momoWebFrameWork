<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ include file="../common/taglib.jsp"%>
<html>
<head>
<title>用户信息管理页面</title>
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
						<li><a href="#">系统管理</a></li>
						<li><a href="#">权限管理</a></li>
						<li><a href="#">用户管理</a></li>
					</ul>
				</div>
				<!-- form starts -->
				<div class="panel panel-primary panel-mo">
					<div class="panel-heading">用户添加页面</div>
					<div class="panel-body">
						<form:form modelAttribute="contentModel" action="${context}/system/perms/user/save" class="form-horizontal" method="POST">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">登录名称</label>
								<div class="col-sm-8">
									<form:input path="username" name="username" class="form-control"
										placeholder="【必填项】" />
									<form:errors path="username" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">用户姓名</label>
								<div class="col-sm-8">
									<form:input path="name" name="name" class="form-control"
										placeholder="【必填项】" />
									<form:errors path="name" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-8">
									<form:password path="password" name="password" class="form-control"
										placeholder="【必填项】" />
									<form:errors path="password" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="passwordr" class="col-sm-2 control-label">重复密码</label>
								<div class="col-sm-8">
									<form:password path="confirmPassword" name="confirmPassword" class="form-control"
										placeholder="【必填项】" />
									<form:errors path="confirmPassword" class="field-has-error"></form:errors>
								</div>
							</div>
							<div class="form-group">
								<label for="selectRoleId" class="col-sm-2 control-label">角色选择</label>
								<div class="col-sm-8">
									<form:select path="selectRoleId" name="selectRoleId" class="form-control">
										 <c:forEach items="${requestScope.roleList}" var="item" varStatus="status">
										 	 <option value="${item.id}">${item.name}</option>
										 </c:forEach>
									</form:select>
									<form:errors path="selectRoleId" class="field-has-error"></form:errors>
								</div>
							</div>      
							<div class="form-group">
								<label for="memo" class="col-sm-2 control-label">备注</label>
								<div class="col-sm-8">
									<form:textarea path="memo" class="form-control" rows="3" name="memo"/>
									<form:errors path="memo" class="field-has-error"></form:errors>
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