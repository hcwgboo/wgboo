package cn.jeeweb.modules.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yuanw
 * @version V1.0
 * @Title:
 * @Description:
 * @date 2018-07-04 14:48
 */
public class SLEmojiFilter {
    /**
     * 替换表情符
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if(StringUtils.isNotBlank(source)){
            String slipStr = "";
            return source.replaceAll("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]|[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        }else{
            return source;
        }
    }
}
