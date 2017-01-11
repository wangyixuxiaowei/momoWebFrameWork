$.extend($.fn.validatebox.defaults.rules, {
	vd: {
		validator: function(value, param){
			var d1 = new Date();
			var d2 = myparser(value);
			return d2<= d1;
		},
		message: '所选日期早于当前日期.'
	},	
	imsi: {
		validator : function(value) {
			return /^[0-9]{15}$/.test(value);
		},
		message : 'IMSI号格式不正确.'
	},		
	mdn: {
		validator : function(value) {
			return /^(86){0,1}(133|153|180|181|189)[0-9]{8}$/.test(value);
		},
		message : 'MDN号格式不正确.'
	},
	ip: {
		validator : function(value) {
			return /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/.test(value);
		},
		message : 'IP地址格式不正确.'
	},
	psiz: {
		validator : function(value) {
			return /^[1-9]{1}[0-9]{1,2}$/.test(value);
		},
		message : '允许的数值范围为10~999.'
	},
	lighval: {
		validator : function(value) {
			return /^\d*$/.test(value);
		},
		message : '要求长度为20位之内的数值.'
	}
})
$(function(){
	dtimeboxinit();
	opselect({
		"id":2,
		"text":"MDN",
		"desc":"op2"
	});
	loadgrid();
});
$('#sd').datetimebox({ 
	required:true,
	missingMessage: "必填项",
	validType:'vd',
	showSeconds:false
});
$('#ed').datetimebox({ 
	required:true,
	missingMessage: "必填项",
	validType:'vd',
	showSeconds:false
});
$('#sele').combobox({ 
	valueField:'id',    
	textField:'text',   
	required:true,    
	editable:false ,   
	url:'ops_list_data.json',   
	onSelect:opselect
});
$('#ligh').combobox({ 
	valueField:'desc',    
	textField:'text',   
	required:true,    
	editable:false,   
	url:'high_list_data.json'
});
$('.easyui-linkbutton').get(0).onclick = bindEve1;
ToolBar.init();
function dateRange(d){
	if(d > new Date()){
		d = null;
	}
	return d;
}

function loadgrid(){
   var dg = $('#test');
   dg.datagrid({
	  title:'用户详单查询',
	  toolbar:'#tb',
	  iconCls:'icon-ok',
	  fit:true,
	  nowrap: false,
	  striped:false,
	  queryParams:{},
	  sortName: '',
	  sortOrder: 'asc',
	  remoteSort: false,
	  idField:'',
	  /*frozenColumns:[[
		{field:'inv',width:80,halign:'center',rowspan:2,title:'序号',align:'center',sortable:true},
		{field:'date',width:100,halign:'center',rowspan:2,title:'日期',align:'center',sortable:true},
		{field:'name',title:'名称',halign:'center',width:80,rowspan:2,align:'center'}
  	  ]],
  	  columns:[
  		  [
  			  {colspan:9,title:'可滚动的详细记录'}
  		  ],[
  			{field:'amount',width:80,title:'数量',align:'right'},
  			{field:'price',width:80,title:'价格',align:'right'},
  			{field:'cost',width:100,title:'总计',align:'right'},
  			{field:'note',width:110,title:'记录1',align:'left'},
  			{field:'note',width:110,title:'记录2',align:'left'},
  			{field:'note',width:110,title:'记录3',align:'left'},
  			{field:'note',width:110,title:'记录4',align:'left'}, 
  			{field:'note',width:110,title:'记录5',align:'left'},
  			{field:'note',width:110,title:'记录6',align:'left'} 
  		  ]
  	  ],*/
	  /*frozenColumns:[[
	          		{field:'sdate',width:200,halign:'center',rowspan:2,title:'开始日期',align:'center',sortable:true},
	          		{field:'edate',width:200,halign:'center',rowspan:2,title:'结束日期',align:'center',sortable:true},
	          		{field:'imsi',title:'用户IMSI号',halign:'center',width:150,rowspan:2,align:'center'},
	          		{field:'mdn',title:'用户MDN号',halign:'center',width:150,rowspan:2,align:'center'},
	          		{field:'ip',title:'PDN地址',halign:'center',width:150,rowspan:2,align:'center'}
	  ]],
	  columns:[
		  [
			  {colspan:7,title:'统计指标'}
		  ],[
			{field:'pdntype',width:100,halign:'center',title:'PDN地址类型',align:'left'},
			{field:'pgwip',width:120,halign:'center',title:'PGW地址',align:'left'},
			{field:'sgwip',width:120,halign:'center',title:'SGW地址',align:'left'},
			{field:'nettype',width:80,halign:'center',title:'网络类型',align:'left'},
			{field:'upflow',width:150,halign:'center',title:'上行流量(MB)',align:'left',sortable:true},
			{field:'downflow',width:150,halign:'center',title:'下行流量(MB)',align:'left',sortable:true},
			{field:'onlinetime',width:150,halign:'center',title:'在线时长(小时)',align:'left',sortable:true} 
		  ]
	  ],*/
		columns:[[
			{field:'sdate',width:200,halign:'center',rowspan:2,title:'开始时间',align:'center',sortable:true},
			{field:'edate',width:200,halign:'center',rowspan:2,title:'结束时间',align:'center',sortable:true},
			{field:'imsi',title:'IMSI号码',halign:'center',width:150,rowspan:2,align:'center'},
			{field:'mdn',title:'MDN号码',halign:'center',width:150,rowspan:2,align:'center'},
			{field:'pdncid',width:100,halign:'center',title:'PDN标识',align:'left'},
			{field:'ip',title:'PDN地址',halign:'center',width:150,rowspan:2,align:'center'},
			{field:'cfc',width:120,halign:'center',title:'关闭原因',align:'left'},
			{field:'nettype',width:80,halign:'center',title:'网络类型',align:'left'},
			{field:'upflow',width:150,halign:'center',title:'上行流量',align:'left',sortable:true},
			{field:'downflow',width:150,halign:'center',title:'下行流量',align:'left',sortable:true},
			{field:'onlinetime',width:150,halign:'center',title:'在线时长',align:'left',sortable:true},
			{field:'timeusage',width:150,halign:'center',title:'业务时长',align:'left',sortable:true}
			]
		],
  	  pagination:true,
	  pageSize:30,
	  singleSelect:true,
	  rownumbers:true,
	  loadMsg:'一直在努力, 马上显示 ...',
	  rowStyler: row_style_func,
	  onLoadSuccess: function(){
		  var rows = $("#test").datagrid("getRows");
		  var exp_btn = $('.easyui-linkbutton').get(1);
		  if(rows.length>0){
			exp_btn.style.display = "inline-block";
		  }else{
			exp_btn.style.display = "none";
			$('#test').datagrid('insertRow',{
				row: {
				}
			});
			$("tr[datagrid-row-index='0']").css({"visibility":"hidden"});
		  }
	  }
	});
   dg.datagrid('getPager').pagination({
		layout:['refresh','list','sep','prev','sep','next'],
		showRefresh:true,
		displayMsg: "",
		pageList: [10,20,30,50,100,200,300]
	});
   $('#test').datagrid('loadData',{total:0,rows:[]});
}

var row_style_func = function(index,row){
	var lv = $('#lighv').val();
	var lg = $('#ligh').combobox('getValue');
	if (lv && row[lg] >= lv){
		return 'background-color:#FF8800;color:#fff;font-weight:bold;';
	}
};



function bindEve1(){
	var vp = validParam();
	if(!vp)return;
	var dgopts = $('#test').datagrid('options');
	var queryParams = dgopts.queryParams;  
	queryParams.sd = $('#sd').datebox('getValue').replace(/[-: ]/g,'');
	queryParams.ed = $('#ed').datebox('getValue').replace(/[-: ]/g,'');
	var selev = $('#sele').combobox('getValue');
	queryParams.se = selev;
	queryParams.op = $('#opv'+selev).val();
	queryParams.dif = 1;
	dgopts.url='LteDpdData.cahtml';
	PARAMS = queryParams;
	$.post("../../stat508/UserTracker.cahtml",{paramJ:$.toJSON(PARAMS),reportT:"LTE用户详单查询"},
			 function(data){
	 },"text");
	if($('#ff').form('validate')){
		$('#test').datagrid('loadData',{total:0,rows:[]});
		$("#test").datagrid('reload'); 
	}
}
function validParam(){
	var sd = $("#sd").datetimebox('getValue');
	var ed = $("#ed").datetimebox('getValue');
	var regExp = new RegExp("-| ","g");
	sd = parseDate(sd,regExp,":");
	var ss = sd.split(":");
	var y = parseInt(ss[0], 10);
	var mt = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	var h = parseInt(ss[3], 10);
	var mi = parseInt(ss[4], 10);
	var sd_date = new Date(y,mt,d,h,mi);
	ed = parseDate(ed,regExp,":");
	ss = ed.split(":");
	y = parseInt(ss[0], 10);
	mt = parseInt(ss[1], 10);
	d = parseInt(ss[2], 10);
	h = parseInt(ss[3], 10);
	mi = parseInt(ss[4], 10);
	var ed_date = new Date(y,mt,d,h,mi);
	var r = monthDiff(sd_date,ed_date);
	if(r>2){
		alert("月跨度不能超过两个月");
		return false;
	}else{
		return true;
	}
}

function monthDiff(startdate,stopdate){
	var y=stopdate.getYear()-startdate.getYear();
	var m=stopdate.getMonth()-startdate.getMonth();
	if(m>=0){
		return 12*y+m+1;
	}
	if(m<0){
		return (12+m+1)+(y-1)*12;
	}
}

function bindDMem(){
	$.post("lteclearmem.cahtml",{
				"dx" :1
			},function(data) {
				var str = data=="y"?"成功":"失败";
				alert("清除缓存"+str);
			},"text");
}
function parseDate(s,p,t){
	//2014-04-01 00:00:00
	//strM.replace(/a/g,"A")
	//s = s.replace(/(-| )/g,":");
	s = s.replace(p,t);
	return s;
}

function myparser(s) {
	if (!s)
	return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}
function getCurDateSd(){
	var d = new Date();
	var y = d.getFullYear();
	var m = d.getMonth() + 1;
	var d = d.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-'+ (d < 10 ? ('0' + d) : d) + ' ' +'00:00';
}
function getCurDateEd(){
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var mi = date.getMinutes();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-'+ (d < 10 ? ('0' + d) : d) + ' ' +(h < 10 ? ('0' + h) : h) + ':'+(mi < 10 ? ('0' + mi) : mi);
}
function opselect(rec){
	var div_t = rec.desc;
	$('.lagop').each(function(index){
		var disy = 'none';
		var enablev = 'disableValidation';
		var thisVali = $(this).find('input:first-child');
		if(this.id == div_t){
			disy = 'inline';
			enablev = 'enableValidation';
		}
		this.style.display = disy;
		$(this).find('input:first-child').validatebox(enablev);
	});
}
function dtimeboxinit(){
	$("#sd").datetimebox('setValue', getCurDateSd());
	$("#ed").datetimebox('setValue', getCurDateEd());
}