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
 * @date 2018年2月6日 下午1:51:54
 * @Description：字典项转换select
 *
 */
public class DictSelectTag extends TagSupport {
	private static Logger logger = LoggerFactory.getLogger(DictSelectTag.class);
	/**
	 * 元素id
	 */
	private String id;
	/**
	 * 元素的name bean的属性
	 */
	private String name;
	private String dict;
	private String value;
	private boolean form_flg = false;
	/**
	 * 查询方式
	 */
	private String condition = "eq";
	/**
	 * 元素的样式
	 */
	private String clazz = "form-control i-select";
	private int width = 185; 

	private boolean readonly = false;

	private boolean disabled = false;

	public int doStartTag() throws JspException {
		if (StringUtils.isEmpty(dict) || StringUtils.isEmpty(name)) {
			return Tag.SKIP_BODY;
		}
		toHTML(DictUtils.getDictList(dict));
		return Tag.EVAL_PAGE;
	}

	private static final String OPTION_TEMPLATE = "<option value='%s'>%s</option>";
	private static final String OPTION_CHECKED_TEMPLATE = "<option value='%s'selected >%s</option>";
	private static final String EMPTY_OPTION_TEMPLATE = "<option value=''>请选择</option>";

	public void toHTML(List<Dict> dicts) {
		try {
			

			
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer(50 * dicts.size());
			sb.append("<select name='" + name + "'" + (StringUtils.isEmpty(id) ? "" : ("id='" + id + "'"))
					+ (readonly ? " onfocus='this.defaultIndex=this.selectedIndex;' onchange='this.selectedIndex=this.defaultIndex;' " : "") + (disabled ? " disabled " : "") 
					
					+"style='"+ ("width:"+ width +"px;") +"'"
					+ " condition='" + condition
					+ "' class='" + clazz + "'>");
			if (StringUtils.isEmpty(value) && !form_flg) {
				sb.append(EMPTY_OPTION_TEMPLATE);
			}

			for (Dict dictBean : dicts) {
				if (!StringUtils.isEmpty(value) && value.equals(dictBean.getValue())) {
					sb.append("\n" + String.format(OPTION_CHECKED_TEMPLATE, dictBean.getValue(), dictBean.getLabel()));
				} else {
					sb.append("\n" + String.format(OPTION_TEMPLATE, dictBean.getValue(), dictBean.getLabel()));
				}
			}
			sb.append("\n</select>");
			out.print(sb.toString());
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isForm_flg() {
		return form_flg;
	}

	public void setForm_flg(boolean form_flg) {
		this.form_flg = form_flg;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getDict() {
		return dict;
	}

	public void setDict(String dict) {
		this.dict = dict;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
