$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				permcode: '/radius/group/radgroupreply/list'//权限值
			},
			columns : ['groupname','attribute','op','value'],
			edit_url_pre:'/radius/group/radgroupreply/',
			del_url_pre:'/radius/group/radgroupreply/',
			url : contextpath + '/radius/group/radgroupreply/search',
			extendsParamsFunc : function(a){
				a.params.groupname = $('input#groupname').val();
				a.params.attribute = $('input#attribute').val();
			}
		});
	});
	$('.btn-click').click();
});