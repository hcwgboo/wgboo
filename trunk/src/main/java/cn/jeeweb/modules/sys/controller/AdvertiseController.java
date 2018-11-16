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
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.dto.AdvertiseRuleRelationDto;
import cn.jeeweb.modules.sys.entity.Advertise;
import cn.jeeweb.modules.sys.entity.AdvertiseRule;
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.service.IAdvertiseRuleRelationService;
import cn.jeeweb.modules.sys.service.IAdvertiseRuleService;
import cn.jeeweb.modules.sys.service.IAdvertiseService;
import cn.jeeweb.modules.sys.utils.UserUtils;
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
 * @Title: 广告
 * @Description: 广告
 * @author java
 * @date 2018-11-12 19:58:04
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sys/advertise")
@RequiresPathPermission("sys:advertise")
public class AdvertiseController extends BaseBeanController<Advertise> {

    @Autowired
    protected IAdvertiseService advertiseService;
    @Autowired
    private IAdvertiseRuleRelationService advertiseRuleRelationService;

    public Advertise get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return advertiseService.selectById(id);
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
        EntityWrapper<Advertise> entityWrapper = new EntityWrapper<Advertise>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<Advertise> pagejson = new PageJson<Advertise>(advertiseService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {

        User user = UserUtils.getUser();
        Optional<List<AdvertiseRuleRelationDto>> optional = Optional.ofNullable(advertiseRuleRelationService.selectRuleByMercharntId(user.getId()));
        request.setAttribute("rule", JSONArray.toJSON(optional.orElse(new ArrayList<AdvertiseRuleRelationDto>())));

        model.addAttribute("data", newModel());
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") Advertise advertise, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("新增广告成功");
        try {
            advertiseService.insertAdvertise(advertise);
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.fail("新增广告失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        User user = UserUtils.getUser();
        Optional<List<AdvertiseRuleRelationDto>> optional = Optional.ofNullable(advertiseRuleRelationService.selectRuleByMercharntId(user.getId()));
        request.setAttribute("rule", JSONArray.toJSON(optional.orElse(new ArrayList<AdvertiseRuleRelationDto>())));

        Advertise advertise = get(id);
        model.addAttribute("data", advertise);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") Advertise advertise, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
    	advertiseService.updateAdvertise(advertise);
        return null;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(Advertise advertise, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(advertise, result)) {
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
            if (StringUtils.isEmpty(advertise.getId())) {
                advertiseService.insert(advertise);
            } else {
                advertiseService.insertOrUpdate(advertise);
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
            advertiseService.deleteById(id);
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
            advertiseService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        Advertise advertise = get(id);
        model.addAttribute("data", advertise);
        return display("edit");
    }

    /**
     * 提交审核
     */
    @RequestMapping(value = "submitCheckAdv", method = RequestMethod.GET)
    public AjaxJson submitCheckAdv(HttpServletRequest request) {
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.success("提交成功,请等待");
        String id = request.getParameter("id");
        advertiseService.submitCheckAdv(id);
        return ajaxJson;
    }

    @RequestMapping(value = "checklist", method = RequestMethod.GET)
    public String checkList(Model model, HttpServletRequest request, HttpServletResponse response){

        return display("checklist");
    }



    /**
     * 审核通过
     * @param request
     * @return
     */
    @RequestMapping(value = "checkSuccessAdv", method = RequestMethod.GET)
    public AjaxJson checkSuccessAdv(HttpServletRequest request) {
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.success("审核通过");
        String id = request.getParameter("id");
        advertiseService.checkSuccessAdv(id);
        return ajaxJson;
    }

    /**
     * 审核不通过
     * @param request
     * @return
     */
    @RequestMapping(value = "checkFailAdv", method = RequestMethod.GET)
    public AjaxJson checkFailAdv(HttpServletRequest request) {
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.success("审核不通过");
        String id = request.getParameter("id");
        advertiseService.checkFailAdv(id);
        return ajaxJson;
    }

}
