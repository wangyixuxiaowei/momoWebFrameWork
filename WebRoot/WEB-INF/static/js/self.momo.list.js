(function($){
	$.fn.momopager = function(opts){
		var options = $.extend({},$.fn.momopager.defaults,opts);
		return this.each(function(index,value){
			var $curElement = $(this);
			momo.list._toAjax(options,$curElement);
		});
	};
	$.fn.momopager.defaults = {
		queryflag : 0,
		nob : 0,
		params : {
			pageNo : 1,
			pageSize : 25
		},
		columns :[],
		edit_url_pre:'',
		del_url_pre:'',
		url : contextpath + '/system/perms/role/search',
		extendsParamsFunc : function(a){
			//a.params.username = $('input#username').val();
			//a.params.name = $('input#name').val();
		},
		extendsOpFunc : null
	};
	momo.list = {
		_dealPostData : function (opt){
			if(opt.extendsParamsFunc){
				opt.extendsParamsFunc(opt);
			}
		},
		_toAjax : function (opt,$a){
			if(opt.queryflag != 0){
				momo.list._ajax(opt,$a);
			}else{
				var postdata = momo.list._dealPostData(opt);
				if(postdata==''){
					return false;
				}else{
					momo.list._ajax(opt,$a);
				}
			}
		},
		_freshMsg : function ($a){
			$a.find('tbody').remove();
			var msg_show = '<tr class="non_show_tr warning"><td colspan="10" class="non_show_td"><i class="fa fa-3x fa-blue fa-spinner fa-pulse"></i></td></tr>';
			$a.append('<tbody>'+msg_show+'</tbody>');
		},
		_ajax : function (opt,$a){
			$.ajax({
				type:'post',
				contentType:'application/json',
				url:opt.url,
				processData:false,
				dataType:'json',
				data:JSON.stringify(opt.params),
				beforeSend:function(){
					momo.list._freshMsg($a);
				},
				success:function(sdata){
					opt.queryflag = 1 ;
					momo.list._consPaper(sdata,opt,$a);
					momo.list._appendTbodyContents(sdata,opt,$a);
				},
				error:function(){
					alert('网络错误……');
				}
			});
		},
		_appendTbodyContents : function (d,opt,$a){
			var tr_c = '';
			var ditems = d.items;
			var dactionp = d.actionPermsMap;
			var flag_arr = opt.columns;
			if(ditems && ditems.length>0){
				var bool_exopfunc = $.isFunction(opt.extendsOpFunc);
				$.each(ditems,function(i,di){
					var td_c='<td>'+(nob+i+1)+'</td>';
					$.each(flag_arr,function(f,item){
						td_c += '<td>'+di[item]+'</td>';
					});
					tr_c += '<tr>'+td_c;
					if(opt.edit_url_pre != '' || opt.del_url_pre != ''){
						tr_c += '<td class="op">';
						var di_id = di['id'];
						if(opt.edit_url_pre != ''){
							var edit_but = '<button type="button" class="btn btn-default">无修改权限</button>';
							if(dactionp	&& dactionp.edit === true){
								var edit_url = contextpath + opt.edit_url_pre + di_id + '/edit';
								edit_but = '<a type="button" class="btn btn-primary vip-mod" href="'+edit_url+'">修改</a>';
							}
							tr_c += edit_but;
						}
						if(opt.del_url_pre != ''){
							var dele_but = '&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default">无删除权限</button>&nbsp;&nbsp;&nbsp;&nbsp;'; 
							if(dactionp	&& dactionp.delete === true){
								var del_url = contextpath + opt.del_url_pre + di_id + '/delete';
								dele_but = '&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" class="btn btn-danger vip-del" href="'+ del_url +'" onclick="return confirm(\'亲，确实要删除这条记录吗？\')">删除</a>';
							}
							tr_c += dele_but;
						}
						if(bool_exopfunc){
							var ex_op_but = opt.extendsOpFunc(dactionp,di_id);
							tr_c += ex_op_but;
						}
						tr_c += '</td>';
					}
					tr_c += '</tr>';
				});
			}else{
				tr_c += '<tr class="non_show_tr warning"><td colspan="10" class="non_show_td">无查询记录</td></tr>';
			}
			$a.find('tbody').remove();
			$a.append('<tbody>'+tr_c+'</tbody>');
		},
		_consPaper : function (data,opt,$a){
			var def_pagecount = 10;
			var pagesize = data.pageSize;//每页显示记录数
			var itemcount = data.itemCount;//全部分页总共记录数
			var haspre = data.hasPre;//是否有前一页
			var hasnext = data.hasNext;//是否有下一页
			var pagecount = data.pageCount;//全部记录实际总的页数
			var pageindex = data.pageIndex;//当前页码
			var paperRowHtml = $('.pager-mo .row-mo');
			nob = (pageindex-1) * pagesize;
			$('a.a-mo').unbind("click");
			paperRowHtml.remove();
			var newPaperContents = '<div class="row row-mo">';
			newPaperContents +=	'<div class="col-md-5 col-sm-12"><div class="pager-info-mo text-primary">每页显示 '+pagesize+' 条，共 '+itemcount+' 条记录</div></div><div class="col-md-7 col-sm-12"><div class="dataTables_paginate paging_bootstrap"><ul class="pagination pagination-mo pull-right">';
			if(haspre){
				newPaperContents += '<li class="prev"><a href="#" class="a-mo" data-pageno="'+(pageindex - 1)+'">上一页</a></li>';
			}else{
				newPaperContents += '<li class="prev disabled"><span>上一页</span></li>';
			}
			if(pagecount <= def_pagecount){
				for(var i=1;i<=pagecount;i++){
					if(i==pageindex){
						newPaperContents += '<li class="active"><a href="#">'+i+'</a></li>';
					}else{
						newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ i +'">'+ i +'</a></li>';
					}
				}
			}else{
				for(var i=1;i<=def_pagecount;i++){
					if(pageindex <= def_pagecount/2){
						if(i == pageindex){
							newPaperContents += '<li class="active"><a href="#">'+i+'</a></li>';
						}else if(def_pagecount-i>2){
							newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ i +'">'+ i +'</a></li>';
						}else if(i==pagecount-2){
							newPaperContents += '<li><span>...</span></li>';
						}else{
							var pageNo_1 = pagecount-(def_pagecount-i);
							newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ pageNo_1 +'">'+ pageNo_1 +'</a></li>';
						}
					}else if(pagecount-pageindex<def_pagecount/2){
						if(i<3){
							newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ i +'">'+ i +'</a></li>';
						}else if(i==3){
							newPaperContents += '<li><span>...</span></li>';
						}else{
							var pageNo_2 = pagecount-(def_pagecount-i);
							if(pageNo_2 == pageindex){
								newPaperContents += '<li class="active"><a href="#">'+pageNo_2+'</a></li>';
							}else{
								newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ pageNo_2 +'">'+pageNo_2+'</a></li>';
							}
						}
					}else{
						if(i<3){
							newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ i +'">'+i+'</a></li>';
						}else if(i==3 || i == def_pagecount-2){
							newPaperContents += '<li><span>...</span></li>';
						}else if(i>def_pagecount - 2){
							var pageNo_3 = pagecount-(def_pagecount-i);
							newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ pageNo_3 +'">'+pageNo_3+'</a></li>';
						}else{
							if(def_pagecount%2==0){
								if(i == def_pagecount/2){
									newPaperContents += '<li class="active"><a href="#">'+pageindex+'</a></li>';
								}else if(i < def_pagecount/2){
									var pageNo_4 = pageindex-(def_pagecount/2-i);
									newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ pageNo_4 +'">'+pageNo_4+'</a></li>';
								}else{
									var pageNo_5=pageindex+(i-def_pagecount/2);
									newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ pageNo_5 +'">'+pageNo_5+'</a></li>';
								}
							}else{
								if(i==(def_pagecount+1)/2){
									newPaperContents += '<li class="active"><a href="#">'+pageindex+'</a></li>';
								}else if(i<(def_pagecount+1)/2){
									var pageNo_6=pageindex-((def_pagecount+1)/2-i);
									newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ pageNo_6 +'">'+pageNo_6+'</a></li>';
								}else{
									var pageNo_7=pageindex+(i-(def_pagecount+1)/2);
									newPaperContents += '<li><a href="#" class="a-mo" data-pageno="'+ pageNo_7 +'">'+pageNo_7+'</a></li>';
								}
							}
						}
					}
				}
			}
			if (hasnext) {
				newPaperContents += '<li class="next"><a href="#" class="a-mo" data-pageno="'+(pageindex + 1)+'">下一页</a></li>';
			}else{
				newPaperContents += '<li class="next disabled"><span>下一页</span></li>';
			}
			newPaperContents += '</ul></div></div></div>';
			$('.pager-mo').append(newPaperContents);
			$('a.a-mo').on("click",function(e){
				e.preventDefault();
				if($(this).data('pageno')){
					opt.params.pageNo = $(this).data('pageno');
				}
				momo.list._toAjax(opt,$a);
			}); 
		}
	};
})(jQuery);