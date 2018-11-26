package cn.jeeweb.modules.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeFilter;

import cn.jeeweb.core.common.controller.BaseBeanController;
import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.annotation.PageableDefaults;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.JedisUtils;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.excel.validate.ValidateHelper;
import cn.jeeweb.modules.sms.util.VerificationCode;
import cn.jeeweb.modules.sys.Constants;
import cn.jeeweb.modules.sys.entity.LogSmscode;
import cn.jeeweb.modules.sys.entity.MerchantRegister;
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.service.ILogSmscodeService;
import cn.jeeweb.modules.sys.service.IMerchantRegisterService;
import cn.jeeweb.modules.sys.service.IUserService;

/**   
 * @Title: 商家注册
 * @Description: 商家注册
 * @author cql
 * @date 2018-11-24 10:55:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/merchantregister")
@RequiresPathPermission("sys:merchantregister")
public class MerchantRegisterController extends BaseBeanController<MerchantRegister> {

    @Autowired
    protected IMerchantRegisterService merchantRegisterService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ILogSmscodeService logSmscodeService;

    public MerchantRegister get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return merchantRegisterService.selectById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<MerchantRegister> entityWrapper = new EntityWrapper<MerchantRegister>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<MerchantRegister> pagejson = new PageJson<MerchantRegister>(merchantRegisterService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") MerchantRegister merchantRegister, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(merchantRegister, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        MerchantRegister merchantRegister = get(id);
        model.addAttribute("data", merchantRegister);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") MerchantRegister merchantRegister, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(merchantRegister, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(MerchantRegister merchantRegister, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(merchantRegister, result)) {
            // 错误提示
            String errorMsg = errorMsg(result);
            if (!StringUtils.isEmpty(errorMsg)) {
                ajaxJson.fail(errorMsg);
            } else {
                ajaxJson.fail("保存失败");
            }
            return ajaxJson;
        }
        try {
            if (StringUtils.isEmpty(merchantRegister.getId())) {
                merchantRegisterService.insert(merchantRegister);
            } else {
                merchantRegisterService.insertOrUpdate(merchantRegister);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/view", method = RequestMethod.GET)
    public String view(@PathVariable("id") String id, Model model, HttpServletRequest request,
                         HttpServletResponse response) {
        MerchantRegister merchantRegister = get(id);
        String ids = merchantRegister.getIds();
        List<String> list = new ArrayList<>();
        if(!StringUtils.isEmpty(ids)){
            list = Arrays.asList(ids.split(","));
        }
        request.setAttribute("urls", JSONArray.toJSON(list));
        model.addAttribute("data", merchantRegister);
        return display("edit");
    }

    /**
     * 商户审核通过
     * @param request
     * @return
     */
    @RequestMapping(value = "checkMerSuccess", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson checkMerSuccess(HttpServletRequest request){
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("审核通过");
        try {
            String id = request.getParameter("id");
            merchantRegisterService.checkMerSuccess(id);
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.fail("审核错误：" + e.getMessage());
        }
        return ajaxJson;
    }

    /**
     * 商户审核不通过
     * @param request
     * @return
     */
    @RequestMapping(value = "checkMerFail", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson checkMerFail(HttpServletRequest request){
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("审核拒绝");
        try {
            String id = request.getParameter("id");
            merchantRegisterService.checkMerFail(id);
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.fail("审核错误：" + e.getMessage());
        }
        return ajaxJson;
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model, HttpServletRequest request, HttpServletResponse response) {
        ///F:\project\wgb\mng\trunk\src\main\webapp\WEB-INF\webpage\modules\sys\login\register.jsp
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        return display("register");
    }
    
    /**
	 * 发送验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws ExceptionResultInfo
	 */
	@RequestMapping(value="sendVerifyCode", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson sendVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ExceptionResultInfo{
		AjaxJson ajaxJson = new AjaxJson();
		//1.参数校验
		String type = request.getParameter("type");
		String telephone = request.getParameter("telephone");
		if (StringUtils.isEmpty(type) || StringUtils.isEmpty(telephone)){
			ajaxJson.fail("参数错误");
			return ajaxJson;
		}
		//2.手机号码校验
		if(!ValidateHelper.regCheckTelphone(telephone)){
			ajaxJson.fail("手机号码不合法");
			return ajaxJson;
		}
		//3.校验用户是否存在
		User user = userService.selectOne(new EntityWrapper<User>().eq("username", telephone).in("del_flag", Arrays.asList("0", "1")));
		
		if(user != null){
			ajaxJson.fail("账号已存在");
			return ajaxJson;
		}

		//获取验证码（1小时内，用一种类型验证码一致）
		String defCodeKey = Constants.DEF_CODE + Constants.REGISTER + "_" + telephone;
		String defCode = JedisUtils.get(defCodeKey);
		if(cn.jeeweb.core.utils.ObjectUtils.isNullOrEmpty(defCode)){
			defCode = VerificationCode.getDefCode();
			JedisUtils.set(defCodeKey,defCode,Constants.DEF_CODE_TIME);
		}

		//4.发送验证码
		LogSmscode sms = new LogSmscode();
		sms.setType(Integer.parseInt(type));
		sms.setTelphone(telephone);
		sms.setCode(defCode);
		boolean flag = logSmscodeService.sendSmsCode(sms);
		if(!flag) {
			ajaxJson.fail("验证码发送失败，请联系客服");
		}else{
			//5.存入缓存中
			String cashKey = Constants.SMS_CODE + Constants.REGISTER + "_" + telephone; 
			String value = JedisUtils.get(cashKey);
			if(!StringUtils.isEmpty(value)){
				JedisUtils.del(cashKey);
			}
			JedisUtils.set(cashKey, sms.getCode(), 300);
//			ajaxJson.setData(sms.getCode());
		}
		return ajaxJson;
	}
	
	/**
	 * 总代官网注册
	 * @param dto
	 * @return
	 * @throws ExceptionResultInfo
	 */
	@RequestMapping(value="officialRegister", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson officialRegister(MerchantRegister merchantRegister) throws ExceptionResultInfo{
		AjaxJson ajaxJson = new AjaxJson();
		try {
			if(merchantRegister==null) {
				ajaxJson.fail("参数错误");
				return ajaxJson;
			}
			merchantRegisterService.officialRegister(merchantRegister);
			ajaxJson.success("提审成功，请等待官方审核！");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail(e.getMessage());
		}
		return ajaxJson;
	}
}
