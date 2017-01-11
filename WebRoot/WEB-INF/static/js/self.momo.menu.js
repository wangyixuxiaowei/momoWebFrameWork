(function($){
	momo.menu = {
		url : contextpath +'/auth/menu',
		init : function(){
			$.ajax({
				type : 'post',
				contentType : 'application/json',
				url : momo.menu.url,
				async : false,
				processData : false,
				dataType : 'json',
				data : null,
				success : function(sdata){
					momo.menu.consPaper(sdata);
				},
				error : function(){
					alert('网络错误……');
				}
			});
		},
		consPaper : function (data){
			var toappend = '';
			$.each(data,function(i,di){
				toappend += '<li class="nav-header">'+di.name+'</li>';
				if(di.children.length > 0){
					var di_ch1 = di.children;
					$.each(di_ch1,function(j,d1){
						if(d1.children.length > 0){
							var di_ch2 = d1.children;
							toappend += '<li class="accordion"><a href="#"><i class="glyphicon glyphicon-plus"></i><span>'+d1.name+'</span></a><ul class="nav nav-pills nav-stacked">';
		                        $.each(di_ch2,function(x,d2){
		                        	toappend += '<li><a href="'+momo.menu.formatHref(d2.href)+'">'+ d2.name +'</a></li>';
		                        });
		                        toappend += '</ul></li>';
						}else{
							toappend += '<li><a class="ajax-link" href="'+ momo.menu.formatHref(d1.href) +'"><i class="glyphicon glyphicon-home"></i><span>'+ d1.name +'</span></a></li>';
						}
					});
				}
			});
			$('#navul').append(toappend);
		},
		formatHref : function(h){
			return contextpath + h;
		}
	};
})(jQuery);
$(function(){
	momo.menu.init();
});