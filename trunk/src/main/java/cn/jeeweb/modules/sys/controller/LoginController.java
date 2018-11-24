package cn.jeeweb.modules.sys.controller;

import cn.jeeweb.core.common.controller.BaseController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.utils.JeewebPropertiesUtil;
import cn.jeeweb.modules.sys.dto.UserRegisterDto;
import cn.jeeweb.modules.sys.security.shiro.exception.RepeatAuthenticationException;
import cn.jeeweb.modules.sys.security.shiro.realm.UserRealm.Principal;
import cn.jeeweb.modules.sys.security.shiro.web.filter.authc.FormAuthenticationFilter;
import cn.jeeweb.modules.sys.security.shiro.web.filter.authc.credential.RetryLimitHashedCredentialsMatcher;
import cn.jeeweb.modules.sys.service.IUserService;
import cn.jeeweb.modules.sys.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("${admin.url.prefix}")
public class LoginController extends BaseController {
	@Autowired
	private RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;
	@Autowired
	private IUserService userService;

	@Value("${admin.url.prefix}")
	private String path;
	
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletRequest response, Model model) {
		// 我的电脑有缓存问题
		Principal principal = UserUtils.getPrincipal(); // 如果已经登录，则跳转到管理首页
		if (principal != null && !principal.isMobileLogin()) {
			return new ModelAndView("redirect:" + path);
		}

		String useruame = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		//boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
	//	boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	//	String message = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_ERROR_PARAM);
	//	useruame = "admin";
		// 是否开启验证码
		if (RepeatAuthenticationException.class.getName().equals(exception)
				|| retryLimitHashedCredentialsMatcher.isShowCaptcha(useruame)) { // 重复认证异常加入验证码。
			model.addAttribute("showCaptcha", "1");
		} else {
			model.addAttribute("showCaptcha", "0");
		}

		// 强制登陆跳转
		if (ExcessiveAttemptsException.class.getName().equals(exception)
				|| retryLimitHashedCredentialsMatcher.isForceLogin(useruame)) { // 重复认证异常加入验证码。
			// model.addAttribute("showCaptcha", "1");
		}

		String register = JeewebPropertiesUtil.getConfig("register.key");
		model.addAttribute("register", register);

		return new ModelAndView("modules/sys/login/login");
	}

	@RequestMapping("/logout")
	public ModelAndView logout() {
		try {
			Subject subject = SecurityUtils.getSubject();
			if (subject != null && subject.isAuthenticated()) {
				subject.logout();
			}
			return new ModelAndView("modules/sys/login/login");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("modules/sys/login/index");
	}

	/**
	 * 验证账号是否已经注册
	 * @return
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson register(HttpServletRequest request){
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("注册成功");
		try {
			String companyname = request.getParameter("companyname");
			String realname = request.getParameter("realname");
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");

			UserRegisterDto dto = new UserRegisterDto();
			dto.setCompanyname(companyname);
			dto.setRealname(realname);
			dto.setUsername(username);
			dto.setPassword1(password1);
			dto.setPassword2(password2);
			userService.userRegister(dto);
		}catch (Exception e){
			e.printStackTrace();
			ajaxJson.fail("注册失败："+ e.getMessage());
		}
		return ajaxJson;
	}

}
