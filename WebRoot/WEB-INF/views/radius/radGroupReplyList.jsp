<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ include file="../common/taglib.jsp"%>
<html>
	<head>
		<title>RadGroupReply管理页面</title>
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
						<li><a href="#">Rad分组响应</a></li>
						</ul>
					</div>
					<div class="panel panel-default panel-mo">
					<div class="panel-body">
					<form class="form-horizontal"> 
						<div class="form-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-3">分组名称</label>
										<div class="col-md-9">
											<input id="groupname" class="form-control placeholder-no-fix" autocomplete="off" placeholder="分组名称"/>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-3">属性名称</label>
										<div class="col-md-9">
											<input id="attribute" class="form-control placeholder-no-fix" autocomplete="off" placeholder="属性名称"/>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<div class="col-md-6">
											<a type="button" class="btn btn-primary pull-right btn-click"><i class="fa fa-search fa-fw"></i> 查 询 </a>                      
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
					</div>
				</div>
				<div class="panel panel-primary panel-mo">
					<div class="panel-heading">
						RadGroupReply管理列表&nbsp;&nbsp;&nbsp;&nbsp;
						<mo:hasPermission code="/radius/group/radgroupreply/list" action="add">
						<a type="btn" class="btn btn-danger btn-sm btn-mo" href="<c:url value='/radius/group/radgroupreply/add'/>"> 添加记录 </a>
						</mo:hasPermission>
					</div>
					<div class="panel-body">
						<table class="table table-mo table-bordered table-hover">
							<thead class="tr-mo">
								<tr>
									<th>编号</th>
									<th>分组名称</th>
									<th>属性名称</th>
									<th>操作符</th>
									<th>属性值</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr class="non_show_tr warning"><td colspan="10" class="non_show_td">无查询记录</td></tr>
							</tbody>
						</table>
						<!--分页区域 start-->
						<div class="pager-mo"></div>
						<!--end 分页区域-->
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
		<script src="<c:url value='/js/json2.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/js/self.momo.list.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/js/self.radgroupreply.list.js'/>" type="text/javascript"></script>
	</body>
</html>
