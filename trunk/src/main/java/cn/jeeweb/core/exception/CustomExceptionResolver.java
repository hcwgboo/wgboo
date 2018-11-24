package cn.jeeweb.core.exception;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.jeeweb.core.model.ResultInfo;
/**
 * 
 * @author caiqili
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// json转换器
    // 将异常信息转json
    private HttpMessageConverter<ExceptionResultInfo> jsonMessageConverter;
 
    public HttpMessageConverter<ExceptionResultInfo> getJsonMessageConverter() {
        return jsonMessageConverter;
    }
    public void setJsonMessageConverter(
            HttpMessageConverter<ExceptionResultInfo> jsonMessageConverter) {
        this.jsonMessageConverter = jsonMessageConverter;
    }

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		logger.error("异常信息：",ex);
//		if (ex instanceof MaxUploadSizeExceededException ) {
//			 return this.resolveJsonException(request, response, null,ex);
//		}
		
		if (handler !=null) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// 取出方法
			Method method = handlerMethod.getMethod();
			
			ResponseBody responseBody=AnnotationUtils.findAnnotation(method, 
	                ResponseBody.class);
	        if (responseBody!=null) {
	            // 将异常信息转json输出
	            return this.resolveJsonException(request, response, handlerMethod,ex);
	        }
		}
		
		
        
        ExceptionResultInfo exceptionResultInfo = resolveExceptionCustom(ex);
        ModelAndView modelAndView=new ModelAndView();
        String view = "/error/error";
        //异常代码
        ResultInfo resultInfo = exceptionResultInfo.getResultInfo();
        if(resultInfo!=null){
        	int messageCode = exceptionResultInfo.getResultInfo().getCode();
        	//如果是106则跳转到登陆
        	if(messageCode==106){
        		//跳转到登陆
        		view = "/base/login";
        	}
        	// 将异常信息在异常页面显示
        	request.setAttribute("exceptionResultInfo", 
        			exceptionResultInfo.getResultInfo()); 
        	modelAndView.addObject("messageCode", 
        			exceptionResultInfo.getResultInfo().getCode());
        	modelAndView.addObject("message", 
        			exceptionResultInfo.getResultInfo().getMessage());
        }else{
        	modelAndView.addObject("message",exceptionResultInfo.getMessage());
        	modelAndView.addObject("messageCode", ResultInfo.TYPE_RESULT_FAIL);
        }
        modelAndView.setViewName(view);
        return modelAndView;
	}
	
	private ExceptionResultInfo resolveExceptionCustom(Exception ex) {
        ResultInfo resultInfo=null;
        ExceptionResultInfo exceptionResultInfo = null;
 
        if (ex instanceof ExceptionResultInfo) {
            //抛出系统自定义异常
        	resultInfo=((ExceptionResultInfo) ex).getResultInfo();
        	if(resultInfo!=null){
        		exceptionResultInfo = new ExceptionResultInfo(resultInfo);
        	}else{
        		String mesage = ((ExceptionResultInfo) ex).getMessage();
        		exceptionResultInfo = new ExceptionResultInfo(mesage);
        	}
        } else if (ex instanceof MaxUploadSizeExceededException) {
        	 resultInfo = new ResultInfo();
             //resultInfo.setType("1");
             resultInfo.setMessage("上传文件最大5M！");
             exceptionResultInfo = new ExceptionResultInfo(resultInfo);
		}else {
            // 重新构造“未知错误”异常,比如runtimeException
            resultInfo = new ResultInfo();
            resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
            resultInfo.setMessage("系统错误！");
            exceptionResultInfo = new ExceptionResultInfo(resultInfo);
        }
        return exceptionResultInfo;
    }
	
	 // 将异常信息转json输出
    private ModelAndView resolveJsonException(HttpServletRequest request,
            HttpServletResponse response, HandlerMethod handlerMethod,
            Exception ex) {
        // 异常信息解析
        ExceptionResultInfo exceptionResultInfo = resolveExceptionCustom(ex);
 
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        
        try {
            //将exceptionResultInfo对象转成json输出,这里使用spring异常处理的工具
            jsonMessageConverter.write(exceptionResultInfo, MediaType.APPLICATION_JSON, outputMessage);
        } catch (HttpMessageNotWritableException e) {
           // e.printStackTrace();
            logger.error("异常信息：",ex);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        	logger.error("异常信息：",ex);
        }
        return new ModelAndView();
    }

}
