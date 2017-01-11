$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				permcode: '/radius/group/radusergoup/list'//权限值
			},
			columns : ['username','groupname','priority'],
			edit_url_pre:'/radius/group/radusergoup/',
			del_url_pre:'/radius/group/radusergoup/',
			url : contextpath + '/radius/group/radusergoup/search',
			extendsParamsFunc : function(a){
				a.params.username = $('input#username').val();
				a.params.groupname = $('input#groupname').val();
				a.params.priority = $('input#priority').val();
			}
		});
	});
	$('.btn-click').click();
});