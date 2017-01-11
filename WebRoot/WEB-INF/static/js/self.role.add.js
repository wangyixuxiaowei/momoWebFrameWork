
$(function(){
	$('form').submit(function(){
		var permtree = $('#cc').combotree('tree');
		var moduleChecked = permtree.tree('getChecked');
		if(moduleChecked.length > 0){
			var parseData = momo.tools.parseCheckedData(moduleChecked,'cc');
			var moduleCheckData = JSON.stringify(parseData);
			$('#permValues').val(moduleCheckData);
		}else{
			alert('最少必须选一项!');
			return false;
		}
	});
});