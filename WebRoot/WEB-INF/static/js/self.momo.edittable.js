/** 
	author : xuxiaowei 
	date   : 2016-07-08 19:16
	version: 1.0.0
*/
(function($){
	$.fn.moEditGrid = function(opts){
		/* 获取到当前对象的参数开始 */
		var options = $.extend(
			{},
			$.fn.moEditGrid.defaults,
			{
				to_add_arry : new Array(),
				to_edi_arry : new Array(),
				to_del_arry : new Array(),
				cache_obj : {}
			},
			opts
		);
		/* 获取到当前对象的参数结束 */
		var tools = moEditGrid_util;
		return this.each(function(){
			
			var $curElement = $(this);
			
			/* 初始化构建表头开始 */
			var $title = $('<div class="list-row list-title" style="border-radius:3px 3px 0 0"></div>');
			var col_nums = 0;
			$.each(options.column,function(k,v){
				$title.append('<div class="list-col"><span>'+ v +'</span></div>');
				col_nums++;
			});
			options.col_num = col_nums;
			$title.append('<div class="list-col"><span><a href="#" class="a">新增记录</a></span></div>').appendTo($curElement);
			/* 初始化构建表头结束 */
			
			/* 生成数据行模板开始 */
			var $dataRowTemplate = tools.generDataRowTemplate(options.column);
			/* 生成数据行模板结束 */
			
			//异步获取数据库已经存在的记录
            //data from ajax request -- to do!
			//改成从数据库获取
			tools.dataByAjax(options,$curElement);
			/* 动作事件监听开始 */
			$curElement.click(function(e){
				e.preventDefault();
				if($(e.target).is('a')){
					var $row = tools.getRow(e);//被操作的行jq对象
					var $row_id = tools.getRowId(e);//被操作记录的行id
					var e_t_cname = e.target.className;//按钮class
					if(e_t_cname == 'a_sub'){//提交
						var a_ok_list = $curElement.find('a[class$="_ok"]');
						if(a_ok_list.length > 0){
							alert('某些行的【确认】操作未执行完毕！请重新确认');
							return false;
							//$.each(a_ok_list,function(){
							//	$('#mo').append($(this).html());
							//}); 
						}
						//判断三个缓存数组是否有数据
						var add_entity = new Array();
						if(options.to_add_arry.length > 0){
							add_entity = tools.collectDatas(options.to_add_arry,$curElement);
						}
						var edit_entity = new Array();
						if(options.to_edi_arry.length > 0){
							edit_entity = tools.collectDatas(options.to_edi_arry,$curElement);
						}
						var delete_entity = new Array();
						if(options.to_del_arry.length > 0){
							delete_entity = tools.collectDatas(options.to_del_arry,$curElement);
						};
						var result = {};
						result.addList = JSON.stringify(add_entity);
						result.editList = JSON.stringify(edit_entity);
						result.deleteList = JSON.stringify(delete_entity);
/* ★★★★★提交 ★★★★★*/	
tools.submit_server(options,result,$curElement);
/* ★★★★★提交 ★★★★★*/			
					}else if(e_t_cname == 'a'){//添加
						if($curElement.find('div.list-row[id^='+options.ADD_ID_PRE+']').length >= options.NEW_ADD_NUM){
							alert('已经到达此次添加行数的限定阈值 ：'+ options.NEW_ADD_NUM);
							return false;
						}
						var new_id = options.ADD_ID_PRE + (++options.ir);
						//连缀的魅力:)
						//$curElement.find('div.list-row:eq(1)').clone()
						$dataRowTemplate.clone()
										.attr('id',new_id)
										.find('input')
										.prop('readonly',false)
										.prop('value','')
										.css({'background-color':'#fff'})
										.end()
										.find('a.e')
										.before(options.ADD_OK)
										.remove()
										.end()
										.insertAfter($curElement.find('div.list-row[id!=mm]:last'))
										.find('input:first')
										.focus();
					}else if(e_t_cname == 'e'){//编辑
						if($.inArray($row_id,options.to_edi_arry) < 0){
							tools.cacheRowInputs($row_id,options,$curElement);//把当前行的各input数据保存到缓存里，供用户取消修改使用
						}
						$row.find('input')
								   .prop('readonly',false)
								   .css({'background-color':'#FEFD87'})
								   .end()
								   .find('a.e')
								   .after(options.EDIT_OK)
								   .remove()
								   .end()
								   .find('input:first')
								   .focus();
					}else if(e_t_cname == 'd'){//删除
						if($row_id.indexOf(options.ADD_ID_PRE) < 0){
							options.to_del_arry.push($row_id);
							tools.delFromArr($row_id,options.to_edi_arry);
						}else{
							tools.delFromArr($row_id,options.to_add_arry);
						}
						$row.remove();
						tools.sub_show(options,$curElement);
						tools.del_show(options,$curElement);
					}else if(e_t_cname == 'e_ok'){//确认修改
						options.to_edi_arry.push($row_id);
						tools.sub_show(options,$curElement);
						$row.find('input')
						   .prop('readonly',true)
						   .end()
						   .find('a.e_ok').before(options.EDIT).remove().end().find('a.e_cl').remove();
						   //to do 执行行记录的验证 -- to do!
					}else if(e_t_cname == 'e_cl'){//取消修改
						tools.delFromArr($row_id,options.to_edi_arry);
						$row.find('input')
						   .prop('readonly',true)
						   .css({'background-color':'#fff'})
						   .end()
						   .find('a.e_ok').before(options.EDIT).remove().end().find('a.e_cl').remove();
						if(options.cache_obj['c_'+$row_id]){
							tools.pushOldInputs($row_id,options,$curElement);
						}else{
							alert('取消修改失败！请重新刷新列表数据。');
						}
						tools.del_show(options,$curElement);
					}else if(e_t_cname == 'a_ok'){//确认添加
						options.to_add_arry.push($row_id);
						tools.sub_show(options,$curElement);
						$row.find('input')
						   .prop('readonly',true)
						   .end()
						   .find('a.a_ok')
						   .remove().end().find('a.e_cl').remove();
						   //to do 执行行记录的验证 -- to do!
					}
				}
			});
			/* 动作事件监听结束 */
		});
	};
	/* 默认的属性开始 */
	$.fn.moEditGrid.defaults = {
		NEW_ADD_NUM : 15,
		ADD_ID_PRE : '100000',
		NEW_SUB_STR : '<div class="list-row" id="mm"><div class="list-col action" id="newsub"><a href="#" class="a_sub">提交</a></div></div>',
		ADD_OK : '<a href="#" class="a_ok">确认添加</a>',
		EDIT_OK : '<a href="#" class="e_ok">确认修改 </a><a href="#" class="e_cl">初始内容</a>',
		EDIT : '<a href="#" class="e">编辑</a>',
		to_add_arry : new Array(),//['n_1','n_2',……,'n_x']
		to_edi_arry : new Array(),//[1,2,……,n]
		to_del_arry : new Array(),//[1,2,……,n]
		ir : 0,
		url : '',
		column : {
			'name':'姓名',
			'age':'年龄',
			'memo':'描述'
		},
		data : [
			{'id':1,'name':'墨墨1','age':'1','memo':'墨墨1描述'},
			{'id':2,'name':'墨墨2','age':'2','memo':'墨墨2描述'},
			{'id':3,'name':'墨墨3','age':'3','memo':'墨墨3描述'}
		]
	};
	/* 默认的属性结束 */
	
	/* 工具类开始 */
	var moEditGrid_util = {
		//row_template :'<div class="list-row" id=""><div class="list-col"><input type="text" name="" value="" readonly></input></div><div class="list-col"><input type="text" name="" value="" readonly></input></div><div class="list-col"><input type="text" name="" value="" readonly></input></div><div class="list-col action"><a href="#" class="e">编辑</a>&nbsp<a href="#" class="d">删除</a></div></div>';
		delFromArr : function(i,arr){
			var j = $.inArray(i,arr);
			if(j > -1){
				arr.splice(j,1);
			}
			return arr;
		},
		del_show : function(op,thi){
			if(op.to_add_arry.length < 1 && op.to_edi_arry.length < 1 && op.to_del_arry.length < 1){
				thi.find('div#newsub').parent().remove();
			}
		},
		sub_show : function(op,thi){
			if(thi.find('#newsub').length < 1){
				var msize = op.col_num * 151;
				$(op.NEW_SUB_STR).find('#newsub').css('margin-left',msize+'px').end().appendTo(thi);
			}
		},
		getRow : function(e){
			return $(e.target).parents('div.list-row:first');
		},
		getRowId : function (e){
			return this.getRow(e).attr('id');
		},
		//获取操作行的记录内容
		//参数为行记录的id
		getRowInputs : function (i,thi){
			var $row = thi.find('#'+i);
			var formObj = {};
			$row.find('input').each(function(){
				formObj[$(this).attr('name')] = $(this).val();
			});
			return formObj;
		},
		//缓存当前行的原始数据
		cacheRowInputs : function (i,op,thi){
			var formObj = this.getRowInputs(i,thi);
			var cache_key = 'c_'+i
			if(!op.cache_obj[cache_key]){//看是否已经缓存了该行记录
				op.cache_obj[cache_key] = formObj;
			}
		},
		pushOldInputs : function (i,op,thi){
			var $row = thi.find('#'+i);
			var formObj = op.cache_obj['c_'+i]
			$row.find('input').each(function(){
				var k = $(this).attr('name');
				if(formObj[k]){
					$(this).val(formObj[k]);
				}
			});
		},
		generDataRowTemplate : function(cols){
			var $temp = $('<div class="list-row" id=""></div>');
			$.each(cols,function(k,v){
				$temp.append('<div class="list-col"><input type="text" name="'+ k +'" value="" readonly></input></div>');
			});
			$temp.append('<div class="list-col action"><a href="#" class="e">编辑</a>&nbsp;<a href="#" class="d">删除</a></div>');
			return $temp;
		},
		dataByAjax : function(p,e){
			$.ajax({
				type:'post',
				contentType:'application/json',
				url:p.url+'/drill',
				processData:false,
				dataType:'json',
				data:JSON.stringify(p.params),
/*				beforeSend:function(){
					momo.list._freshMsg($a);
				},*/
				success:function(d){
					e.append(moEditGrid_util.consDatas(d,p.column,e));
				},
				error:function(){
					alert('网络错误……');
				}
			});
		},
		consDatas : function(datas,cols,$curlist){
			$.each(datas,function(){
				var data = this;
				var $list_row = $('<div class="list-row"></div>').attr('id',data.id);
				$.each(cols,function(k,v){
					$list_row.append('<div class="list-col"><input type="text" name="'+k+'" value="'+data[k]+'" readonly></input></div>');
				});
				$list_row.append('<div class="list-col action"><a href="#" class="e">编辑</a>&nbsp;<a href="#" class="d">删除</a></div>').appendTo($curlist);
			});
		},
		collectDatas : function(arr,$thi){
			var r = $.map(arr, function(v) {
				var obj_cur = {};
				obj_cur.id = v;
				$thi.find('#' + v).find('input').each(function() {
					var input_i = $(this);
					obj_cur[input_i.attr('name')] = input_i.val();
				});
				return obj_cur;
			});
			return r;
		},
		submit_server : function(p,r,$thi){
			$.ajax({
				type:'post',
				contentType:'application/json',
				url:p.url +'/merge',
				processData:false,
				dataType:'json',
				data:JSON.stringify(r),
/*				beforeSend:function(){
					momo.list._freshMsg($a);
				},*/
				success:function(data){
					if(data.rs && data.rs == 'ok'){
						p.to_add_arry = new Array();
						p.to_edi_arry = new Array();
						p.to_del_arry = new Array();
						p.cache_obj = {};
						alert('提交成功！');
						$thi.find('div.list-row[id]').remove();
						moEditGrid_util.dataByAjax(p,$thi);
					}else{
						alert('提交失败！');
					}
				},
				error:function(){
					alert('网络错误……');
				}
			});
		}
	};
	/* 工具类结束 */	
})(jQuery);