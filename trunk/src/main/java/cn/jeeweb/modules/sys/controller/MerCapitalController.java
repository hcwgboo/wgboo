package cn.jeeweb.modules.sys.controller;

import cn.jeeweb.core.common.controller.BaseBeanController;
import cn.jeeweb.core.common.data.DuplicateValid;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.model.ValidJson;
import cn.jeeweb.core.query.annotation.PageableDefaults;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.dto.MerCapitalDto;
import cn.jeeweb.modules.sys.entity.MerCapital;
import cn.jeeweb.modules.sys.service.IMerCapitalService;
import cn.jeeweb.modules.sys.utils.DeepFieldCopy;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**   
 * @Title: 上架资金
 * @Description: 上架资金
 * @author java
 * @date 2018-11-12 20:01:10
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/mercapital")
@RequiresPathPermission("sys:mercapital")
public class MerCapitalController extends BaseBeanController<MerCapital> {

    @Autowired
    protected IMerCapitalService merCapitalService;

    public MerCapital get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return merCapitalService.selectById(id);
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
        EntityWrapper<MerCapitalDto> entityWrapper = new EntityWrapper<>(MerCapitalDto.class);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, MerCapitalDto.class);
        SerializeFilter filter = propertyPreFilterable.constructFilter(MerCapitalDto.class);
        PageJson<MerCapitalDto> pagejson = new PageJson<>(merCapitalService.selectAdvRuleRelationPage(queryable,request));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new MerCapitalDto());
        }
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") MerCapital merCapital, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(merCapital, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        MerCapital merCapital = get(id);
        MerCapitalDto dto = DeepFieldCopy.transform(merCapital, MerCapitalDto.class);
        model.addAttribute("data", dto);
        return display("edit");
    }

    /**
     * 商户充值
     * @param model
     * @param dto
     * @param result
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") MerCapitalDto dto, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("充值成功");
        try {
            merCapitalService.merCapitalCharge(dto);
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.fail("充值失败："+e.getMessage());
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(MerCapital merCapital, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(merCapital, result)) {
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
            if (StringUtils.isEmpty(merCapital.getId())) {
                merCapitalService.insert(merCapital);
            } else {
                merCapitalService.insertOrUpdate(merCapital);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        return ajaxJson;
    }

}
