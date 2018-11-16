package cn.jeeweb.modules.sys.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

public interface DeepFieldCopy {
    public static <FromType,ToType> ToType transform(FromType sourceObject, Class<ToType> resultClass) {
        return JSON.parseObject(JSON.toJSONString(sourceObject), resultClass);
    }

    public static <FromType,ToType> List<ToType> transformList(List<FromType> sourceList, Class<ToType> resultClass) {
        return JSON.parseArray(JSON.toJSONString(sourceList), resultClass);
    }

    public static <FromType,ToType> ToType transform(FromType sourceObject, Class<ToType> resultClass, SerializerFeature... features) {
        return JSON.parseObject(JSON.toJSONString(sourceObject, features), resultClass);
    }

    public static <FromType,ToType> List<ToType> transformList(List<FromType> sourceList, Class<ToType> resultClass, SerializerFeature... features) {
        return JSON.parseArray(JSON.toJSONString(sourceList, features), resultClass);
    }

    public static void main(String[] argv) {
        Object source = new Object();

        System.out.println("result="+transform(source, String.class));
    }

}
