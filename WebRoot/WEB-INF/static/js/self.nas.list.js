$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				permcode: '/radius/nas/list'//权限值
			},
			columns : ['nasname','shortname','type','ports','secret','server','community','description'],
			edit_url_pre:'/radius/nas/',
			del_url_pre:'/radius/nas/',
			url : contextpath + '/radius/nas/search',
			extendsParamsFunc : function(a){
				a.params.nasname = $('input#nasname').val();
				a.params.shortname = $('input#shortname').val();
				a.params.type = $('input#type').val();
			}
		});
	});
	$('.btn-click').click();
});