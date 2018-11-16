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
import cn.jeeweb.modules.sys.dto.AdvertiseRuleRelationDto;
import cn.jeeweb.modules.sys.entity.AdvertiseRule;
import cn.jeeweb.modules.sys.entity.AdvertiseRuleRelation;
import cn.jeeweb.modules.sys.service.IAdvertiseRuleRelationService;
import cn.jeeweb.modules.sys.service.IAdvertiseRuleService;
import cn.jeeweb.modules.sys.utils.DeepFieldCopy;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeFilter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**   
 * @Title: 广告规则
 * @Description: 广告规则
 * @author cql
 * @date 2018-11-12 19:55:20
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/advertiserulerelation")
@RequiresPathPermission("sys:advertiserulerelation")
public class AdvertiseRuleRelationController extends BaseBeanController<AdvertiseRuleRelation> {

    @Autowired
    protected IAdvertiseRuleRelationService advertiseRuleRelationService;
    @Autowired
    private IAdvertiseRuleService advertiseRuleService;

    public AdvertiseRuleRelation get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return advertiseRuleRelationService.selectById(id);
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
        EntityWrapper<AdvertiseRuleRelationDto> entityWrapper = new EntityWrapper<>(AdvertiseRuleRelationDto.class);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, AdvertiseRuleRelationDto.class);
        SerializeFilter filter = propertyPreFilterable.constructFilter(AdvertiseRuleRelationDto.class);
        PageJson<AdvertiseRuleRelationDto> pagejson = new PageJson<>(advertiseRuleRelationService.selectAdvRuleRelationPage(queryable,request));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {

        Optional<List<AdvertiseRule>> optional = Optional.ofNullable(advertiseRuleService.selectList(new EntityWrapper<>()));
        request.setAttribute("rule", JSONArray.toJSON(optional.orElse(new ArrayList<AdvertiseRule>())));

        model.addAttribute("data", new AdvertiseRuleRelationDto());
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") AdvertiseRuleRelation advertiseRuleRelation, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(advertiseRuleRelation, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        Optional<List<AdvertiseRule>> optional = Optional.ofNullable(advertiseRuleService.selectList(new EntityWrapper<>()));
        request.setAttribute("rule", JSONArray.toJSON(optional.orElse(new ArrayList<AdvertiseRule>())));

        AdvertiseRuleRelationDto relationDto = new AdvertiseRuleRelationDto();
        if(!StringUtils.isEmpty(id)) {
            relationDto = advertiseRuleRelationService.selectAdvRuleById(id);
        }
        model.addAttribute("data", relationDto);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") AdvertiseRuleRelation advertiseRuleRelation, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(advertiseRuleRelation, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(AdvertiseRuleRelation advertiseRuleRelation, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(advertiseRuleRelation, result)) {
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
            if (StringUtils.isEmpty(advertiseRuleRelation.getId())) {
                advertiseRuleRelationService.insert(advertiseRuleRelation);
            } else {
                advertiseRuleRelationService.insertOrUpdate(advertiseRuleRelation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            advertiseRuleRelationService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            advertiseRuleRelationService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        AdvertiseRuleRelation advertiseRuleRelation = get(id);
        model.addAttribute("data", advertiseRuleRelation);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<AdvertiseRuleRelation> entityWrapper = new EntityWrapper<AdvertiseRuleRelation>(entityClass);
            valid = advertiseRuleRelationService.doValid(duplicateValid,entityWrapper);
            if (valid) {
                validJson.setStatus("y");
                validJson.setInfo("验证通过!");
            } else {
                validJson.setStatus("n");
                if (!StringUtils.isEmpty(duplicateValid.getErrorMsg())) {
                    validJson.setInfo(duplicateValid.getErrorMsg());
                } else {
                    validJson.setInfo("当前信息重复!");
                }
            }
        } catch (Exception e) {
            validJson.setStatus("n");
            validJson.setInfo("验证异常，请检查字段是否正确!");
        }
        return validJson;
    }
}
