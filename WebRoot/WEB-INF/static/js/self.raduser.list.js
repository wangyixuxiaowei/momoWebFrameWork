$(function(){
	$('.btn-click').click(function(e){
		$('table.table-mo').momopager({
			queryflag : 0,
			nob : 0,
			params : {
				pageNo : 1,
				pageSize : 25,
				permcode: '/radius/raduser/list'//权限值
			},
			columns : ['username'],
			edit_url_pre:'/radius/raduser/',
			del_url_pre:'/radius/raduser/',
			url : contextpath + '/radius/raduser/search',
			extendsParamsFunc : function(a){
				a.params.username = $('input#username').val();
			},
			extendsOpFunc : function(p,i){
				var ex_op_but = '';
				if(p && p.drill === true){
					var ex_op_url = contextpath + '/radius/raduser/' + i + '/drill';
					ex_op_but = '&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" class="btn btn-primary vip-mod" href="'+ex_op_url+'">钻取</a>';
				}else{
					ex_op_but = '&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default">无钻取权限</button>';
				}
				return ex_op_but;
			}
		});
	}).click();
});