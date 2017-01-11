(function($){
	momo.tools = {
			checkedHashMap : {},
			final_permval : 1000000,
			parseParentCode : function(ptree,node) {
				var parent_node = ptree.tree('getParent', node.target);  
				if (parent_node != null && parent_node.attributes.code != '') {
					_code = parent_node.attributes.code;
					if (momo.tools.checkedHashMap[_code] == null) {
						momo.tools.checkedHashMap[_code] = [];
						momo.tools.checkedHashMap[_code][momo.tools.checkedHashMap[_code].length] = 'view';
						momo.tools.parseParentCode(ptree,parent_node);
					} else {
						return;
					}
				} else{
					return;
				}	
			},
			parseCheckedData : function(a,b) {
				moduleChecked = a;
				for (var i = 0; i < moduleChecked.length; i++) {
					if (moduleChecked[i].id > momo.tools.final_permval) {
						var permtree = $('#'+b).combotree('tree');
						var parent_m = permtree.tree('getParent', moduleChecked[i].target);  
						_code = parent_m.attributes.code;
						if (momo.tools.checkedHashMap[_code] == null) {
							momo.tools.checkedHashMap[_code] = [];
						}
						momo.tools.checkedHashMap[_code][momo.tools.checkedHashMap[_code].length] = moduleChecked[i].attributes.code;
						momo.tools.parseParentCode(permtree,parent_m);
						_code = '';
					}
				}
				var tmpCheckedData = [];
				i = 0;
				for (key in momo.tools.checkedHashMap) {
					tmpCheckedData[i] = {};
					tmpCheckedData[i]['code'] = key;
					tmpCheckedData[i]['action'] = momo.tools.checkedHashMap[key].join(',');
					i++;
				}
				return tmpCheckedData;
			}
	};
})(jQuery);