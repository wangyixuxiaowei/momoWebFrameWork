$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				//permcode: '/system/oplog'//权限值
			},
			columns : ['operator','operationType','module','operationTimeStr','operatorIP','operationResult'],
			//edit_url_pre:'/system/perms/user/',
			//del_url_pre:'/system/perms/user/',
			url : contextpath + '/system/oplog/search',
			extendsParamsFunc : function(a){
				a.params.operator = $('input#operator').val();
				a.params.operationType = $('input#operationType').val();
				a.params.module = $('input#module').val();
				a.params.operationResult = $('input#operationResult').val();
			}
		});
	});
	$('.btn-click').click();
});