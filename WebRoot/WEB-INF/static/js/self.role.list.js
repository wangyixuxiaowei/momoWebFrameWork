$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				permcode: '/system/perms/role/list'//权限值
			},
			columns :['name','memo'],
			edit_url_pre:'/system/perms/role/',
			del_url_pre:'/system/perms/role/',
			url : contextpath + '/system/perms/role/search',
			extendsParamsFunc : function(a){
				a.params.name = $('input#name').val();
			}
		});
	});
	$('.btn-click').click();
});