(function(){
	$.extend($.fn.combotree.defaults, {
	    loader: function (param, success, error) {
	        var opts = $(this).tree("options");
	        if (!opts.url) {
	            return false;
	        }
	        if (opts.queryParams) {
	            param = $.extend({}, opts.queryParams, param);
	        }
	        $.ajax({
	            type: opts.method,
	            url: opts.url,
	            data: JSON.stringify(param),
	            contentType: "application/json; charset=utf-8",
	            //dataType:"json",//这个东西导致返回的json值有问题
	            success: function (data) {
	            	var dataObj = JSON.parse(data);
	            	success(dataObj.allPList);
	            	var checkedPermList = dataObj.checkedPList;
	                if(checkedPermList){
	                	for(var i = 0;i<checkedPermList.length;i++){
	                		var permtree = $('#cc').combotree('tree');
	                		var node = permtree.tree('find',checkedPermList[i]+1000000);
	                		if(node && node.target){
	                			permtree.tree('check',node.target);
	                		}
	                	}
	                } 
	            },
	            error: function () {
	                error.apply(this, arguments);
	            }
	        });
	    }
	});
})(jQuery);
$(function(){
	var parsmObj = {};
	parsmObj.roleId = cur_role_id;
	$('#cc').combotree({
	    url: contextpath + '/system/perms/role/rolepermtree',
	    method: 'post',
	    queryParams:parsmObj
	}); 
	$('form').submit(function(){
		var permtree = $('#cc').combotree('tree');
		var moduleChecked = permtree.tree('getChecked');
		if(moduleChecked.length > 0){
			var moduleCheckData = JSON.stringify(momo.tools.parseCheckedData(moduleChecked,'cc'));
			$('#permValues').val(moduleCheckData);
		}else{
			alert('最少必须选一项!');
			return false;
		}
	});
});