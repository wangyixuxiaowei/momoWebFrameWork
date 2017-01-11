var jQuery = jQuery || {};
jQuery.url = jQuery.url || {};
/**
 * ���ַ����%#&+=�Լ���\sƥ��������ַ����urlת��
 * 
 * @name jQuery.url.escapeSymbol
 * @function
 * @grammar jQuery.url.escapeSymbol(source)
 * @param {string}
 *            source ��Ҫת����ַ�.
 * @return {string} ת��֮����ַ�.
 * @remark ����get����ת�塣�ڷ�����ֻ����gbk������ҳ����gbk����ʱ�����Ծ���ת���ֱ�ӷ�get����
 * 
 * @return {string} ת�����ַ�
 */
jQuery.url.escapeSymbol = function(source) {
	return String(source).replace(
			/[#%&+=\/\\\ \��\f\r\n\t]/g,
			function(all) {
				return '%'
						+ (0x100 + all.charCodeAt()).toString(16).substring(1)
								.toUpperCase();
			});
};
/**
 * ��json���������query�ַ�
 * 
 * @name jQuery.url.jsonToQuery
 * @function
 * @grammar jQuery.url.jsonToQuery(json[, replacer])
 * @param {Object}
 *            json ��Ҫ������json����
 * @param {Function=}
 *            replacer_opt ��ֵ�������⴦��ĺ���function (value, key)
 * @see jQuery.url.queryToJson,jQuery.url.getQueryValue
 * 
 * @return {string} - ��������ַ�����ֵ����URI���룬{a:'&1 '} ==> "a=%261%20"��
 */
jQuery.url.jsonToQuery = function(json, replacer_opt) {
	var result = [], itemLen, replacer = replacer_opt || function(value) {
		return jQuery.url.escapeSymbol(value);
	};
	//jQuery.object.each
	jQuery.each(json, function(item, key) {
		// ����ֻ����itemΪ���顢�ַ��������ͣ�������Ƕ�׵�object
		if (jQuery.lang.isArray(item)) {
			itemLen = item.length;
			// value��ֵ��ҪencodeURIComponentת����
			// FIXED �Ż���escapeSymbol����
			while (itemLen--) {
				result.push(key + '=' + replacer(item[itemLen], key));
			}
		} else {
			//result.push(key + '=' + replacer(item, key));
			result.push(item + '=' + replacer(key));
		}
	});
	return result.join('&');
};