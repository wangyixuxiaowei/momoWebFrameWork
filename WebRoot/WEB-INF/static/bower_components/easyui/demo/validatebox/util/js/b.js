window.mo = window.mo || {
	version :"1-0-0",
	emptyFn : function() {
	}
};
mo.url=mo.url||{};
mo.url.jsonToQuery = function(e) {
	if (!e || typeof e != "object") {
		return null
	}
	var c = "";
	var b = [];
	var d = 0;
	for ( var a in e) {
		b[d] = mo.url.simpleEscape(a) + "=" + mo.url.simpleEscape(e[a]);
		d++;
	}
	b = b.join("&");
	return b;
};
mo.isString = function(a) {
	return (typeof a == "object" && a && a.constructor == String)|| typeof a == "string";
};
mo.url.simpleEscape = function(a) {
	if (mo.isString(a)) {
		return a.replace(/\%/gi, "%25").replace(/\&/gi, "%26").replace(/\+/gi,"%2B").replace(/\ /gi, "%20").replace(/\//gi, "%2F").replace(/\#/gi, "%23").replace(/\=/gi, "%3D");
	}
	return a;
};