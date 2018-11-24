package cn.jeeweb.core.tags.pop;

import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.tags.SysFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

/**
 * @author yuanw
 * @version V1.0
 * @Title:
 * @Description:
 * @date 2018-04-11 19:36
 */
public class AddreslSelectTag extends TagSupport {
	private static Logger logger = LoggerFactory.getLogger(AddreslSelectTag.class);
	// private static final String EMPTY_CITY_OPTION_TEMPLATE = "<option
	// value=''>城市</option>";
	// private static final String EMPTY_AREA_OPTION_TEMPLATE = "<option
	// value=''>区域</option>";
	private static final String OPTION_TEMPLATE = "<option value='%s'>%s</option>";
	private static final String OPTION_CHECKED_TEMPLATE = "<option value='%s'selected >%s</option>";
	private static final String FORM_TEMPLATE = "<label class='Validform_checktip'></label>";
	private static final String EMPTY_OPTION_TEMPLATE = "<option value=''>请选择</option>";

	//

	/**
	 * 元素id
	 */
	private String provinceId;
	private String cityId;
	private String areaId;
	/**
	 * 元素的name bean的属性
	 */
	private String provinceName;
	private String cityName;
	private String areaName;
	private String provinceValue;
	private String cityValue;
	private String areaValue;
	private boolean needArea = true;
	private boolean needCity = true;
	private boolean form_flg = false;

	private boolean readonly = false;

	private boolean disabled = false;
	private int pwidth = 120;
	private int cwidth = 120;
	private int awidth = 120;
	private String selecCallback = "";

	/**
	 * 查询方式
	 */
	private String condition = "eq";
	/**
	 * 元素的样式
	 */
	private String clazz = "form-control i-select";

	public int doStartTag() throws JspException {
		if (provinceName == null || provinceName.length() == 0 || cityName == null || cityName.length() == 0
				|| areaName == null || areaName.length() == 0) {
			return Tag.SKIP_BODY;
		}

		List<Addres> provinces = Region.getProvince();
		List<Addres> citys;
		List<Addres> areas;
		citys = Region.getCity(provinceValue);
		areas = Region.getArea(cityValue);

		toHTML(provinces, citys, areas);

		return Tag.EVAL_PAGE;
	}

	/**
	 * 将所有的Code转换成Option
	 *
	 * @param
	 */
	public void toHTML(List<Addres> provinces, List<Addres> citys, List<Addres> areas) {
		JspWriter out = this.pageContext.getOut();
		int size=0;
		if(citys!=null) size=citys.size();
		StringBuffer sb = new StringBuffer(50 * size);

		sb.append("<select name='" + provinceName + "' id='" + provinceId + "' condition='" + condition + "' class='"
				+ clazz + "'" + "style=" + "display:inline-block;" + ("width:" + pwidth + "px;") + ""
				+ (readonly
						? " onfocus='this.defaultIndex=this.selectedIndex;' onchange='this.selectedIndex=this.defaultIndex;' "
						: "")
				+ (disabled ? " disabled " : "") + ">");
		if (!form_flg) {
			// 放置一个空选项
			sb.append(EMPTY_OPTION_TEMPLATE);
		}

		try {
			int i = 0;
			for (Addres province : provinces) {
				if (provinceValue != null && provinceValue.length() > 0 && province.getValue().equals(provinceValue)) {
					sb.append("\n" + String.format(OPTION_CHECKED_TEMPLATE, province.getValue(), province.getName()));
				} else {
					if (i == 0) {
						sb.append("\n" + String.format(EMPTY_OPTION_TEMPLATE, province.getValue(), province.getName()));
					}
					sb.append("\n" + String.format(OPTION_TEMPLATE, province.getValue(), province.getName()));
				}
				i++;
			}
			sb.append("\n</select>");

			if (!form_flg) {
				// 放置一个空选项
				sb.append(EMPTY_OPTION_TEMPLATE);
			}

			if (needCity) {
				sb.append("\n<select name='" + cityName + "' id='" + cityId + "'  class='" + clazz + "'" + "style="
						+ "display:inline-block;" + ("width:" + cwidth + "px;") + ""
						+ (readonly
								? " onfocus='this.defaultIndex=this.selectedIndex;' onchange='this.selectedIndex=this.defaultIndex;' "
								: "")
						+ (disabled ? " disabled " : "") + ">\n");

				sb.append(EMPTY_OPTION_TEMPLATE);

				// 根据分析得到需要选中选项的值
				if (!ObjectUtils.isNullOrEmpty(citys)) {
					for (Addres city : citys) {
						// "<option value='%s'>%s</option>"
						if (cityValue != null && cityValue.length() > 0 && city.getValue().equals(cityValue)) {
							sb.append("\n" + String.format(OPTION_CHECKED_TEMPLATE, city.getValue(), city.getName()));
						} else {
							sb.append("\n" + String.format(OPTION_TEMPLATE, city.getValue(), city.getName()));
						}
					}
				}
				sb.append("\n</select>");

				if (!form_flg) {
					sb.append(EMPTY_OPTION_TEMPLATE);
				}
			}

			if (needArea) {
				sb.append("\n<select name='" + areaName + "' id='" + areaId + "'  class='" + clazz + "'" + "style="
						+ "display:inline-block;" + ("width:" + awidth + "px;") + ""
						+ (readonly
								? " onfocus='this.defaultIndex=this.selectedIndex;' onchange='this.selectedIndex=this.defaultIndex;' "
								: "")
						+ (disabled ? " disabled " : "") + ">\n");
				sb.append(EMPTY_OPTION_TEMPLATE);

				if (!ObjectUtils.isNullOrEmpty(areas)) {
					for (Addres area : areas) {
						// "<option value='%s'>%s</option>"
						if (areaValue != null && areaValue.length() > 0 && area.getValue().equals(areaValue)) {
							sb.append("\n" + String.format(OPTION_CHECKED_TEMPLATE, area.getValue(), area.getName()));
						} else {
							sb.append("\n" + String.format(OPTION_TEMPLATE, area.getValue(), area.getName()));
						}
					}

					sb.append("\n</select>");
				}

				if (form_flg) {
					sb.append(FORM_TEMPLATE);
				}
			}
			if (!disabled || !readonly) {
				sb.append(createSelectJs());
			}
			out.print(sb.toString());
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
	}

	private String createSelectJs() {

		StringBuffer sb = new StringBuffer(50);
		sb.append("\n<script>");
		// ready开始
		sb.append("$(document).ready(function(){");
		if (needCity) {
			// change开始
			sb.append("\n$(\"#" + provinceId + "\").change(function(){");
			// 获取被选中的数据
			sb.append("\n var selectProvinceId=$(\"#" + provinceId + "\").find(\"option:selected\").attr(\"value\");");
			sb.append("\n$(\"#" + cityId + "\").empty();\n");
			sb.append("\n$(\"#" + cityId + "\").append(\"<option value=''>请选择</option>\");");
			sb.append("\n$(\"#" + areaId + "\").empty();\n");
			sb.append("\n$(\"#" + areaId + "\").append(\"<option value=''>请选择</option>\");");

			sb.append("\n$.ajax({async:false,url:\"" + SysFunctions.getAdminUrlPrefix()
					+ "/area/city/findCity\",data:{\"provinceId\":selectProvinceId},\n" + "success:function(data){\n")
					.append("\n if(data == null) return;");
			sb.append("\nfor(var i=0;i<data.length;i++){\n$(\"#" + cityId
					+ "\").append(\"<option value='\"+data[i].value+\"' >\"+data[i].name+\"</option>\");\n}")
					.append("}\n});");
			if (!StringUtils.isEmpty(selecCallback)) {
				sb.append("var selectProvinceName=$(\"#" + provinceId + "\").find(\"option:selected\").text();\n");
				sb.append("var selectProvinceValue=$(\"#" + provinceId + "\").find(\"option:selected\").attr(\"value\");\n");
				sb.append(selecCallback + "(this, selectProvinceName, selectProvinceValue, 1);");
			}
			// change结束
			sb.append("\n});");
		}else{
			if (!StringUtils.isEmpty(selecCallback)) {
				sb.append("var selectProvinceName=$(\"#" + provinceId + "\").find(\"option:selected\").text();\n");
				sb.append("var selectProvinceValue=$(\"#" + provinceId + "\").find(\"option:selected\").attr(\"value\");\n");
				sb.append(selecCallback + "(this, selectProvinceName, selectProvinceValue, 1);");
			}
		}
		if (needArea) {
			// change开始
			sb.append("\n$(\"#" + cityId + "\").change(function(){");
			// 获取被选中的数据
			sb.append("\n var selectCityId=$(\"#" + cityId + "\").find(\"option:selected\").attr(\"value\");");
			sb.append("\n$(\"#" + areaId + "\").empty();\n");
			sb.append("\n$(\"#" + areaId + "\").append(\"<option value=''>请选择</option>\");");
			sb.append("\n$.ajax({async:false,url:\"" + SysFunctions.getAdminUrlPrefix()
					+ "/area/city/findArea\",data:{\"cityId\":selectCityId},\n" + "success:function(data){\n")
					.append("\n if(data == null) return;");
			// if (!form_flg) {
			// sb.append("\n$(\"#" + areaId + "\").append(\"<option
			// value=''>区域</option>\");");
			// }
			sb.append("\nfor(var i=0;i<data.length;i++){\n$(\"#" + areaId
					+ "\").append(\"<option value='\"+data[i].value+\"'>\"+data[i].name+\"</option>\");\n}")
					.append("}\n});");
			if (!StringUtils.isEmpty(selecCallback)) {
				sb.append("var selectCityName=$(\"#" + cityId + "\").find(\"option:selected\").text();\n");
				sb.append("var selectCityValue=$(\"#" + cityId + "\").find(\"option:selected\").attr(\"value\");\n");
				sb.append(selecCallback + "(this, selectCityName, selectCityValue, 2);");
			}
			// change结束
			sb.append("\n});");
			if (!StringUtils.isEmpty(selecCallback)) {
				sb.append("\n$(\"#" + areaId + "\").change(function(){");
				sb.append("var selectAreaName=$(\"#" + areaId + "\").find(\"option:selected\").text();\n");
				sb.append("var selectAreaValue=$(\"#" + areaId + "\").find(\"option:selected\").attr(\"value\");\n");
				sb.append(selecCallback + "(this, selectAreaName, selectAreaValue, 3);");
				sb.append("\n});");
			}
		} else {
			if (needCity && !StringUtils.isEmpty(selecCallback)) {
				sb.append("\n$(\"#" + cityId + "\").change(function(){");
				sb.append("var selectCityName=$(\"#" + cityId + "\").find(\"option:selected\").text();\n");
				sb.append("var selectCityValue=$(\"#" + cityId + "\").find(\"option:selected\").attr(\"value\");\n");
				sb.append(selecCallback + "(this, selectCityName, selectCityValue, 2);");
				sb.append("\n});");
			}
		}
		// ready结束
		sb.append("\n});");
		sb.append("\n</script>");
		return sb.toString();
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public String getCityValue() {
		return cityValue;
	}

	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}

	public String getAreaValue() {
		return areaValue;
	}

	public void setAreaValue(String areaValue) {
		this.areaValue = areaValue;
	}

	public boolean isForm_flg() {
		return form_flg;
	}

	public void setForm_flg(boolean form_flg) {
		this.form_flg = form_flg;
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

	public String getProvinceValue() {
		return provinceValue;
	}

	public void setProvinceValue(String provinceValue) {
		this.provinceValue = provinceValue;
	}

	public boolean isNeedArea() {
		return needArea;
	}

	public void setNeedArea(boolean needArea) {
		this.needArea = needArea;
	}

	public int getPwidth() {
		return pwidth;
	}

	public void setPwidth(int pwidth) {
		this.pwidth = pwidth;
	}

	public int getCwidth() {
		return cwidth;
	}

	public void setCwidth(int cwidth) {
		this.cwidth = cwidth;
	}

	public int getAwidth() {
		return awidth;
	}

	public void setAwidth(int awidth) {
		this.awidth = awidth;
	}

	public String getSelecCallback() {
		return selecCallback;
	}

	public void setSelecCallback(String selecCallback) {
		this.selecCallback = selecCallback;
	}

	public boolean isNeedCity() {
		return needCity;
	}

	public void setNeedCity(boolean needCity) {
		this.needCity = needCity;
	}
	

}
