$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				permcode: '/system/perms/user/list'//权限值
			},
			columns : ['username','name','pwdUpdateTimeStr','enable','memo'],
			edit_url_pre:'/system/perms/user/',
			del_url_pre:'/system/perms/user/',
			url : contextpath + '/system/perms/user/search',
			extendsParamsFunc : function(a){
				a.params.username = $('input#username').val();
				a.params.name = $('input#name').val();
			}
		});
	});
	$('.btn-click').click();
});