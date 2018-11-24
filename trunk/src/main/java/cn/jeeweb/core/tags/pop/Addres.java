package cn.jeeweb.core.tags.pop;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @author yuanw
 * @version V1.0
 * @Title:
 * @Description:
 * @date 2018-04-12 09:04
 */
@TableName("sys_address")
public class Addres {
	
	@TableId(value = "id", type = IdType.UUID)
	private String id;

	/**
	 * 省市县名称
	 */
	@TableField(value="name")
	private String name;

	/**
	 * 本级编码
	 */
	@TableField(value="value")
	private String value;

	/**
	 * 父级编码
	 */
	@TableField(value="parent")
	private String parent;

	/**
	 * 用于区分省市县
	 */
	@TableField(value="type")
	private String type;
	
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
