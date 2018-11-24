package cn.jeeweb.core.tags.pop;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.entity.Dict;
import cn.jeeweb.modules.sys.utils.DictUtils;

/**
 * 
 * @author zhangyouwei
 * @email zhangyouwei1988@sina.cn
 * @date 2018年2月9日 下午7:07:17
 * @Description：字典Formatter Formatter方法名称默认name+Formatter
 *
 */
public class FormatterPropertyTag extends TagSupport {
	private static Logger logger = LoggerFactory.getLogger(FormatterPropertyTag.class);
	private static final long serialVersionUID = 3763261848194745083L;

	/**
	 * bean中对应实体变量的名称.
	 */
	private String name = "";
	/**
	 * 字典code
	 */
	private String dict = "";

	public int doStartTag() throws JspException {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(dict)) {
			return Tag.SKIP_BODY;
		}

		List<Dict> dicts = DictUtils.getDictList(dict);
		toHTML(dicts);

		return Tag.EVAL_PAGE;
	}

	/**
	 * @param codes
	 */
	private void toHTML(List<Dict> dicts) {
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append(createFormatterFunction(dicts));
		try {
			out.print(sb.toString());
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
	}

	/**
	 * 将所有的propertyDict转换成javascript数组
	 * 
	 * @param propertyDicts
	 * @return
	 */
	private String getArrayData(List<Dict> dicts) {

		if (dicts == null || dicts.size() == 0)
			return "";
		StringBuffer sb = new StringBuffer(dicts.size() * 50);
		try {

			Dict d = null;

			for (int i = 0; i < dicts.size(); i++) {
				d = dicts.get(i);
				if (i == 0) {
					sb.append(d.getValue() + ":" + d.getLabel());
				} else {
					sb.append(";" + d.getValue() + ":" + d.getLabel());
				}
			}

		} catch (Exception e) {
			logger.error("异常信息", e);
		}

		return sb.toString();
	}

	private String createFormatterFunction(List<Dict> dicts) {
		StringBuffer sb = new StringBuffer(50);
		
//		cellValue = 
//				+ "</span>";
//		return cellValue;
		sb.append("\n function " + name + "Formatter(value, options, row){")
				.append("\n try {\n if (value == undefined || value =='null' || value =='') {\n return ''; \n}")
				.append("\n var cellValue = value;\n var formatterValue = '" + getArrayData(dicts) + "';")
				.append("\n if (formatterValue != undefined && formatterValue !=null && formatterValue != '') {")
				.append("\n var formatterValues = formatterValue.split(';');\n for (var i = 0; i < formatterValues.length; i++) { "
						+ "\n var formatterItem = formatterValues[i]; \n var labelValues = formatterItem.split(':'); "
						+ "\n if (labelValues[0] == cellValue) { \n cellValue = labelValues[1]; \n}\n}\n} \n cellValue= \"<span  originalValue='\" +value + \"'>\" + cellValue + \"</span>\"; \n return cellValue; "
						+ "\n } catch (err) {} \n return value; \n}");

		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDict() {
		return dict;
	}

	public void setDict(String dict) {
		this.dict = dict;
	}

}
