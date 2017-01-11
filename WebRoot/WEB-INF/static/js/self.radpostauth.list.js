$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				permcode: '/radius/aa/postauth/list'//权限值
			},
			columns : ['username','reply','authdatestr'],
			del_url_pre:'/radius/aa/postauth/',
			url : contextpath + '/radius/aa/postauth/search',
			extendsParamsFunc : function(a){
				a.params.username = $('input#username').val();
			}
		});
	});
	$('.btn-click').click();
});