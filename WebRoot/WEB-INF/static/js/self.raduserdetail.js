$(function(){
	$('#check_list').moEditGrid({
		ir : 0,		
		column : {
			'username':'用户名',
			'attribute':'属性',
			'op':'操作符',
			'value':'值'
		},
		params : {
			username : params_username
		},
		url : contextpath + '/radius/checks/check'
	});
	$('#reply_list').moEditGrid({
		ir : 10,		
		column : {
			'username':'用户名',
			'attribute':'属性',
			'op':'操作符',
			'value':'值'
		},
		params : {
			username : params_username
		},
		url : contextpath + '/radius/checks/reply'
	});
	$('#group_list').moEditGrid({
		ir : 100,		
		column : {
			'username':'用户名',
			'groupname':'分组名称',
			'priority':'优先级别'
		},
		params : {
			username : params_username
		},
		url : contextpath + '/radius/group/radusergoup'
	});
});