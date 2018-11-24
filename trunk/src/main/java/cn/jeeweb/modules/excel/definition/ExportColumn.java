package cn.jeeweb.modules.excel.definition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by EvanBrook on 2016/10/19.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportColumn {
    //次序
    int seqNum() default 0;
    //名称
    String name() default "";
    String type() default "";
    //默认风格
    boolean defaultStyle() default true;

}
