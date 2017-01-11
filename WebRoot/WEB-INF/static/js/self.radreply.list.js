$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				permcode: '/radius/checks/reply/list'//权限值
			},
			columns : ['username','attribute','op','value'],
			edit_url_pre:'/radius/checks/reply/',
			del_url_pre:'/radius/checks/reply/',
			url : contextpath + '/radius/checks/reply/search',
			extendsParamsFunc : function(a){
				a.params.username = $('input#username').val();
			}
		});
	});
	$('.btn-click').click();
});