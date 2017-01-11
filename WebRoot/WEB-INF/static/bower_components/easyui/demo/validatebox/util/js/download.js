var PARAMS = {};
(function() {
    var a = {};
    getUniqueId = function(c) {
        var d = c || 8;
        var e = "";
        while (d--) {
            e += b()
        }
        if (!a[e]) {
            a[e] = 1;
            return e
        } else {
            return getUniqueId(d)
        }
    };
    var b = function() {
        var d = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        var c = d.length;
        return d.charAt(Math.floor(Math.random() * c))
    }
})();
function insertAfter(c, a) {
    var b = a.parentNode;
    if (b.lastChild == a) {
        b.appendChild(c)
    } else {
        b.insertBefore(c, a.nextSibling)
    }
}
var ToolBar = {
    name: "ToolBar",
    downloadContainer: "DownloadContainer",
    init: function() {
		$('.easyui-linkbutton').get(1).onclick = ToolBar.showDownLoadContainer;
    },
    showDownLoadContainer: function(c) {
        var b = $('#'+ToolBar.downloadContainer).get(0);
        var a = b.getElementsByTagName("h4");
        a[0].innerHTML = document.title;
        b.style.display = "block";
        ToolBar.bindDownload()
    },
    bindDownload: function() {
        var a = $('#'+ToolBar.downloadContainer).get(0);
        var g = a.getElementsByTagName("input");
        var h = a.getElementsByTagName("DIV");
        ToolBar.downType = "";
        h[1].onclick = ToolBar.hideDownloadContainer;
        if (!ToolBar.downMsgId) {
            var b = document.createElement("DIV");
            b.id = "Tip" + getUniqueId();
            b.className = "e-tip";
            insertAfter(b, $("#DownPdfFile").get(0).parentNode.parentNode);
            ToolBar.downMsgId = b.id
        }
        var c = ToolBar.downMsgId;
        for (var f = 0, e = g.length; f < e; f++) {
            var d = g[f];
            if (d.checked) {
                PARAMS[d.name] = d.value;
                if (d.name != "wordstat") {
                    ToolBar.downType = d.value
                }
                var j = d.parentNode;
                $('#'+c).innerHTML = j.title
            }
            if (d.type == "radio") {
                d.onclick = ToolBar.switchMsg(c)
            }
            if (d.type == "submit") {
                d.onclick = ToolBar.downloadFile
            }
            if (d.type == "button") {
                d.onclick = ToolBar.hideDownloadContainer
            }
        }
    },
    switchMsg: function(a) {
        return function() {
            PARAMS[this.name] = this.value;
            if (this.name != "wordstat") {
                ToolBar.downType = this.value
            }
            var b = this.parentNode;
            if (this.name != "wordstat") {
                $('$'+a).innerHTML = b.title
            }
        }
    },
    downloadFile: function() {
        var d = [];
        d.html = 10000;
        d.pdf = 3000;
        d.csv = 10000;
        d.txt = 10000;
/*        if (!DB || !DB.tbody || DB.tbody.length == 0) {
        	if(!(VAR.MOREROWDOWN && VAR.MOREROWDOWN == '1')){
        		return alert("Ŀǰʱ������޿����ص���ݣ���ѡ�������ʱ������أ�")
        	}
       }*/
        /*var b = parseFloat(DB.totalPage) * parseFloat(DB.pageItem);
        var e = ToolBar.downType;
        var f = true;
        if (b > d[e]) {
            f = window.confirm("�����ص�����ѳ���" + e + "�ļ������������ƣ���ȷ���Ƿ�������أ�")
        }
        if (!f) {
            return false
        }*/
        ToolBar.getDownloadUrl()
		x = $('#'+ToolBar.downloadContainer).get(0);
		x.style.display = "none";
    },
    hideDownloadContainer: function() {
        var b = $('#'+ToolBar.downloadContainer).get(0);
        var a = b.getElementsByTagName("input");
        var d = b.getElementsByTagName("div");
        for (var c = 0, f = a.length; c < f; c++) {
            var e = a[c];
            if (e.checked) {
                PARAMS[e.name] = "";
                ToolBar.downType = ""
            }
        }
        b.style.display = "none";
    },
    getDownloadUrl: function() {
		var urlStr = "LteDpdData.cahtml";
		PARAMS.dif = 2;
		var pn1 = $('#test').datagrid('options').pageNumber;
		var psz = $('#test').datagrid('options').pageSize;
		PARAMS.page = pn1;
		PARAMS.rows = psz;
		var param = mo.url.jsonToQuery(PARAMS);
		window.location.href = urlStr + "?" + param;
    }
};