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
					<div class="panel-heading">用户 编辑页面</div>
					<div class="panel-body">
						<form action="${context}/system/perms/user/${user.id}/update" class="form-horizontal" method="POST">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">登录名称</label>
								<div class="col-sm-8">
									<input type="text" id="username" name="username" value="${user.username}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">用户姓名</label>
								<div class="col-sm-8">
									<input type="text" id="name" name="name" value="${user.name}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-8">
									<input type="password" id="password" name="password" value="${user.password}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="passwordr" class="col-sm-2 control-label">重复密码</label>
								<div class="col-sm-8">
									<input type="password" id="passwordr" name="passwordr" class="form-control"
										placeholder="【必填项】" />
								</div>
							</div>
							<div class="form-group">
								<label for="selectRoleId" class="col-sm-2 control-label">角色选择</label>
								<div class="col-sm-8">
									<select id="selectRoleId" name="selectRoleId" class="form-control">
										 <c:forEach items="${requestScope.roleList}" var="item" varStatus="status">
										 	 <option value="${item.id}">${item.name}</option>
										 </c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="memo" class="col-sm-2 control-label">备注</label>
								<div class="col-sm-8">
									<textarea id="memo" class="form-control" rows="3" name="memo"></textarea>
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
	<script type="text/javascript">
		$(function(){
			$('#selectRoleId option').each(function(){
				$this = $(this);
				if($this.val() === "${myRoleId}"){
					$this.attr("selected","true");
				}
			});
			$('#memo').val("${user.memo}");
		});
	</script>
	<script>
	   		function userValid(){
	   		//username
	         var vipName = $("#name");
	         if(vipName.val() == null || vipName.val().length == 0){
	            alert("vip会员姓名不能为空，请填上.");
	            vipName.focus();
	            return false;
	         }else if(vipName.val().length > 50){
	            alert("景区名称最大长度不能超过50个字符，请调整.");
	            vipName.focus();
	            return false;
	         }
	        //name
	         var vipCard = $("#card");
	         if(vipCard.val() == null || vipCard.val().length == 0){
	            alert("卡号不能为空，请填上.");
	            vipCard.focus();
	            return false;
	         }else if(vipCard.val().length > 4){
	            alert("卡号最大长度不能超过4个字符，请调整.");
	            vipCard.focus();
	            return false;
	         }
	         return true;
	   }
	</script>
</body>
</html>