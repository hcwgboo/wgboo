package cn.jeeweb.core.filter.xss;

import cn.jeeweb.modules.common.utils.SLEmojiFilter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		return StringEscapeUtils.escapeHtml4(super.getHeader(name));
	}

	@Override
	public String getQueryString() {
		return StringEscapeUtils.escapeHtml4(super.getQueryString());
	}
	
	@Override
	public String getParameter(String name) {
		return StringEscapeUtils.escapeHtml4(super.getParameter(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
                //表情符处理
                values[i] = SLEmojiFilter.filterEmoji(values[i]);

				escapseValues[i] = charReplace(values[i]);
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}

	/**
	 * 特殊字符转义
	 */
	public String charReplace(String str){
		String s = "";
		if(StringUtils.isBlank(str)) return str;
		switch (str){
			case "_":
				s = "\\_";
				break;
			case "%":
				s = "\\%";
				break;
			case "\n":
				s = "\\n";
				break;
			case "\\":
				s = "\\\\";
				break;
			case "\'":
				s = "\'";
				break;
			case "\b":
				s = "\\b";
				break;
			case "\t":
				s = "\\t";
				break;
			case "\r":
				s = "\\r'";
				break;
			default:
				s=str;
		}
		return s;
	}

}