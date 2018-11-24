package cn.jeeweb.modules.excel.definition;

import java.lang.reflect.Field;

/**
 * Created by EvanBrook on 2016/10/19.
 */
public class ExportFieldBean {

    public ExportFieldBean(){}

    public ExportFieldBean(ExportColumn column, Field field) {
        super();
        this.column = column;
        this.field = field;
        this.name=field.getName();
        this.type=field.getType();
    }

    public ExportFieldBean(ExportHeader header) {
        super();
        this.header = header;
    }

    public ExportFieldBean(ExportHeader header, String name, Class<?> type) {
        super();
        this.header = header;
        this.name = name;
        this.type = type;
    }

    public ExportFieldBean(ExportColumn column, String name, Class<?> type) {
        super();
        this.column = column;
        this.name = name;
        this.type = type;
    }


    private ExportColumn column;
    private ExportHeader header;
    private Field field;
    private String name;
    private Class<?> type;

    public ExportHeader getHeader() {
        return header;
    }

    public void setHeader(ExportHeader header) {
        this.header = header;
    }

    public ExportColumn getColumn() {
        return column;
    }

    public void setColumn(ExportColumn column) {
        this.column = column;
    }

    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }


}
