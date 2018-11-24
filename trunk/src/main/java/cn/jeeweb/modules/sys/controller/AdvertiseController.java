package cn.jeeweb.modules.sys.controller;

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
import cn.jeeweb.core.tags.pop.Addres;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.constants.DictConstants;
import cn.jeeweb.modules.sys.dto.AdvertiseRuleRelationDto;
import cn.jeeweb.modules.sys.entity.Advertise;
import cn.jeeweb.modules.sys.entity.AdvertiseRule;
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.service.IAddressService;
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
    @Autowired
    private IAddressService addressService;

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
        entityWrapper.eq("merchant_id", UserUtils.getUser().getId());
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
        List<Addres> list = new ArrayList<>();
        request.setAttribute("regions", JSONArray.toJSON(list));

        model.addAttribute("data", newModel());
        return display("edit");
    }

    /**
     * 判断商家是否有新增规则
     * @return
     */
    @RequestMapping(value = "merchantRule", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson merchantRule(){
        AjaxJson ajaxJson = new AjaxJson();
        User user = UserUtils.getUser();
        List<AdvertiseRuleRelationDto> list = advertiseRuleRelationService.selectRuleByMercharntId(user.getId());
        if(list != null && list.size() > 0){
            ajaxJson.setData(true);
        }else {
            ajaxJson.setData(false);
        }
        return ajaxJson;
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
        String regions = advertise.getRegion();
        List<Addres> list = new ArrayList<>();
        if(!StringUtils.isEmpty(regions)){
            String[] strs = regions.split(",");
            list = addressService.selectList(new EntityWrapper<Addres>().eq("type", 3).in("value", strs));
        }
        request.setAttribute("regions", JSONArray.toJSON(list));

        model.addAttribute("data", advertise);
        if(DictConstants.GGSXJ_DICT_VALUE_1.equals(advertise.getReleaseStatus())){
            return display("view");
        }
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") Advertise advertise, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("修改成功");
        try {
            advertiseService.updateAdvertise(advertise);
        }catch (Exception e){
            e.printStackTrace();
            ajaxJson.fail("修改失败");
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

    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        User user = UserUtils.getUser();
        Optional<List<AdvertiseRuleRelationDto>> optional = Optional.ofNullable(advertiseRuleRelationService.selectRuleByMercharntId(user.getId()));
        request.setAttribute("rule", JSONArray.toJSON(optional.orElse(new ArrayList<AdvertiseRuleRelationDto>())));

        Advertise advertise = get(id);
        String regions = advertise.getRegion();
        List<Addres> list = new ArrayList<>();
        if(!StringUtils.isEmpty(regions)){
            String[] strs = regions.split(",");
            list = addressService.selectList(new EntityWrapper<Addres>().eq("type", 3).in("value", strs));
        }
        request.setAttribute("regions", JSONArray.toJSON(list));

        model.addAttribute("data", advertise);
        return display("view");
    }

    /**
     * 提交审核
     */
    @RequestMapping(value = "submitCheckAdv", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson submitCheckAdv(HttpServletRequest request) {
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.success("提交成功,请等待");
        String id = request.getParameter("id");
        try {
            advertiseService.submitCheckAdv(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail(e.getMessage());
        }
        return ajaxJson;
    }

    @RequestMapping(value = "checklist", method = RequestMethod.GET)
    public String checkList(Model model, HttpServletRequest request, HttpServletResponse response){

        return display("checklist");
    }

    @RequestMapping(value = "checkAjaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void checkAjaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<Advertise> entityWrapper = new EntityWrapper<>(entityClass);
        entityWrapper.ne("status", DictConstants.GGSH_DICT_VALUE_0).orderBy("release_status asc, update_date desc");
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<Advertise> pagejson = new PageJson<>(advertiseService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    /**
     * 审核通过
     * @param request
     * @return
     */
    @RequestMapping(value = "checkSuccessAdv", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson checkSuccessAdv(HttpServletRequest request) {
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.success("审核通过");
        String id = request.getParameter("id");
        try {
            advertiseService.checkSuccessAdv(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("系统错误");
        }
        return ajaxJson;
    }

    /**
     * 审核不通过
     * @param request
     * @return
     */
    @RequestMapping(value = "checkFailAdv", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson checkFailAdv(HttpServletRequest request) throws ExceptionResultInfo {
    	AjaxJson ajaxJson = new AjaxJson();
    	ajaxJson.success("审核不通过");
        String id = request.getParameter("id");
        advertiseService.checkFailAdv(id);
        return ajaxJson;
    }

}
