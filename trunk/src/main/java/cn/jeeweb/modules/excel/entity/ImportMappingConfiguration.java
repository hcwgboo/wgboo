package cn.jeeweb.modules.excel.entity;

import java.io.Serializable;

/**
 * 
 * @Description:解析导入配置类
 * @author zhangyouwei1988@sina.cn
 * @date: 2016-8-30 下午6:26:01
 */
public class ImportMappingConfiguration implements Serializable {

	private java.lang.String start_row_no;// 数据读取开始行号
	private java.lang.String header_row_no;// 数据读取开始行号
	private java.lang.String property_cname;// 资管平台字段中文名
	private java.lang.String property_ename;// 资管平台字段英文名
	private java.lang.String column_no;// 对应excel模板 列序号
	private java.lang.String item_type;// 字段类型
	private java.lang.String is_null;// 是否允许为空
	private java.lang.String code;// 字典时字典的code

	public java.lang.String getStart_row_no() {
		return start_row_no;
	}

	public void setStart_row_no(java.lang.String start_row_no) {
		this.start_row_no = start_row_no;
	}

	public java.lang.String getHeader_row_no() {
		return header_row_no;
	}

	public void setHeader_row_no(java.lang.String header_row_no) {
		this.header_row_no = header_row_no;
	}


	public java.lang.String getProperty_cname() {
		return property_cname;
	}

	public void setProperty_cname(java.lang.String property_cname) {
		this.property_cname = property_cname;
	}

	public java.lang.String getProperty_ename() {
		return property_ename;
	}

	public void setProperty_ename(java.lang.String property_ename) {
		this.property_ename = property_ename;
	}

	public java.lang.String getColumn_no() {
		return column_no;
	}

	public void setColumn_no(java.lang.String column_no) {
		this.column_no = column_no;
	}

	public java.lang.String getItem_type() {
		return item_type;
	}

	public void setItem_type(java.lang.String item_type) {
		this.item_type = item_type;
	}

	public java.lang.String getIs_null() {
		return is_null;
	}

	public void setIs_null(java.lang.String is_null) {
		this.is_null = is_null;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

}
