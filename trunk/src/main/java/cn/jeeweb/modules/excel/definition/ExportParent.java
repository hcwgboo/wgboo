package cn.jeeweb.modules.excel.definition;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by EvanBrook on 2016/10/19.
 */
public class ExportParent<T> {
	
	 /**字段主键*/
	private String id;
    /**创建者*/
	private String createBy;
    /**创建时间*/
	private Date createDate;
    /**更新者*/
	private String updateBy;
    /**更新时间*/
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
	private String delFlag;
    /**备注信息*/
    private String remarks;

    private Class<T> entity;

    public ExportParent(){

    }

    @SuppressWarnings("unchecked")
    public List<ExportFieldBean> initColumn() {
        List<ExportFieldBean> list = new ArrayList<ExportFieldBean>();
        this.entity = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

        if (this.entity != null) {

            Field[] fields = entity.getDeclaredFields();
//
            for (Field f : fields) {
                //获取字段中包含ExportColumn的注解
                ExportColumn meta = f.getAnnotation(ExportColumn.class);
                if (meta != null) {
                    ExportFieldBean sf = new ExportFieldBean(meta, f);
                    list.add(sf);
                }
            }

            //返回对象所表示的类或接口的所有可访问公共方法
            Method[] methods = entity.getMethods();

            for (Method m : methods) {
                ExportColumn meta = m.getAnnotation(ExportColumn.class);
                if (meta != null) {
                    ExportFieldBean sf = new ExportFieldBean(meta, m.getName(), m.getReturnType());
                    list.add(sf);
                }
            }
        }
        return list;

    }

    public List<ExportFieldBean> initHeader() {
        List<ExportFieldBean> list = new ArrayList<ExportFieldBean>();
        this.entity = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

        if (this.entity != null) {

            //获取类上包含ExportHeader的注解
            ExportHeader header = entity.getAnnotation(ExportHeader.class);
            if (header != null) {
                ExportFieldBean sf = new ExportFieldBean(header);
                list.add(sf);
            }
        }
        return list;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Class<T> getEntity() {
		return entity;
	}

	public void setEntity(Class<T> entity) {
		this.entity = entity;
	}
    
    
}
