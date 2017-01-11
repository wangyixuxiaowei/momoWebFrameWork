$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				permcode: '/radius/aa/acct/list'//权限值
			},
			columns : ['username','acctsessionid','acctuniqueid','nasipaddress','acctstarttimestr','acctupdatetimestr','acctstoptimestr'],
			del_url_pre:'/radius/aa/acct/',
			url : contextpath + '/radius/aa/acct/search',
			extendsParamsFunc : function(a){
				a.params.username = $('input#username').val();
			}
		});
	});
	$('.btn-click').click();
});