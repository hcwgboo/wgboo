package cn.jeeweb.modules.sms.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import cn.jeeweb.modules.sys.entity.LogSmscode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * @author yuanw
 * @version V1.0
 * @Title: 验证码短信
 * @Description:
 * @date 2018-04-13 08:56
 */
public class SmsCode {
    private static ResourceBundle config = ResourceBundle.getBundle("smsConfig");

    private static Logger logger = LoggerFactory.getLogger(SmsCode.class);
    //产品名称:云通信短信API产品,开发者无需替换
    static String product = config.getString("smsCode.product");
    //产品域名,开发者无需替换
    static String domain =config.getString("smsCode.domain");

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static String accessKeyId = config.getString("smsCode.accessKeyId");
    static String accessKeySecret = config.getString("smsCode.accessKeySecret");

    public static SendSmsResponse sendSms(LogSmscode sms){
        SendSmsResponse sendSmsResponse = new SendSmsResponse();
        String ty ="";
        try{
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", config.getString("smsCode.defaultConnectTimeout"));
            System.setProperty("sun.net.client.defaultReadTimeout", config.getString("smsCode.defaultReadTimeout"));

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(sms.getTelphone());
            request.setSignName(config.getString("smsCode.SignName"));
            //参数
            String str = "{\"code\":\""+sms.getCode()+"\"}";
            if(sms.getType() == SmsCodeType.REGISTER){
                logger.info("==========================调用注册验证码接口开始==========================");
                request.setTemplateCode(config.getString("smsCode.register.templateCode"));
                ty="注册";
            }
            request.setTemplateParam(str);

            sendSmsResponse = acsClient.getAcsResponse(request);
            logger.info("==========================调用短信接口成功==========================");
            return sendSmsResponse;
        }catch (Exception e){
            logger.info("==========================调用验证码接口失败==========================");
            return  null;
        }finally {
            logger.info("====验证码接口类型：["+ty+"]  验证码：["+sms.getCode()+"]   返回结果集："+ JSON.toJSONString(sendSmsResponse));
        }

    }

}
