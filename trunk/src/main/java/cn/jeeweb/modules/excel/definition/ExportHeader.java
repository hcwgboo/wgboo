package cn.jeeweb.modules.excel.definition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by EvanBrook on 2016/10/19.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportHeader {
    //次序
    int seqNum() default 0;
    //名称
    String name() default "";
    //合并开始列
    int start() default 0;
    //合并结束列
    int end() default 0;
    //默认风格
    boolean defaultStyle() default true;
}
