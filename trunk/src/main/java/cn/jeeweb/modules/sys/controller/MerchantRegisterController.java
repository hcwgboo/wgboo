package cn.jeeweb.modules.sys.controller;

import cn.jeeweb.core.common.controller.BaseBeanController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.annotation.PageableDefaults;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.entity.MerchantRegister;
import cn.jeeweb.modules.sys.service.IMerchantRegisterService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return "modules/sys/login/register";
    }
}
