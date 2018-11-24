package cn.jeeweb.modules.area.controller;

import cn.jeeweb.core.common.controller.BaseBeanController;
import cn.jeeweb.core.common.data.DuplicateValid;
import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.model.ValidJson;
import cn.jeeweb.core.query.annotation.PageableDefaults;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.area.entity.Area;
import cn.jeeweb.modules.area.entity.City;
import cn.jeeweb.modules.area.service.IAreaService;
import cn.jeeweb.modules.area.service.ICityService;
import com.alibaba.fastjson.JSON;
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
import java.util.List;

/**
 * @Title: 城市信息
 * @Description: 城市信息
 * @author zhangyouwei
 * @date 2018-02-02 09:33:55
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/area/city")
@RequiresPathPermission("area:city")
public class CityController extends BaseBeanController<City> {

    @Autowired
    protected ICityService cityService;
	@Autowired
	private IAreaService areaService;
    public City get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return cityService.selectById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }
    
    @RequiresMethodPermissions("list")
    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<City> entityWrapper = new EntityWrapper<City>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<City> pagejson = new PageJson<City>(cityService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }
    
    @RequiresMethodPermissions("create")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        return display("edit");
    }

    @RequiresMethodPermissions("create")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") City city, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) throws ExceptionResultInfo {
    	addOrUpdateVerify(city);
        return doSave(city, request, response, result);
    }
    
    @RequiresMethodPermissions("update")
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        City city = get(id);
        model.addAttribute("data", city);
		// 获得区域信息数据
		List<Area> areaList = areaService.selectList(new EntityWrapper<Area>(Area.class).eq("city.id",city.getId()));
		model.addAttribute("areaList", areaList);
        return display("edit");
    }
    
    @RequiresMethodPermissions("update")
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") City city, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) throws ExceptionResultInfo {
    	addOrUpdateVerify(city);
        return doSave(city, request, response, result);
    }
    
    private void addOrUpdateVerify(City city) throws ExceptionResultInfo {
    	if(city==null){
    		throw new ExceptionResultInfo("城市输入不能为空！");
    	}else if((city.getCode()==null || "".equals(city.getCode())) && (city.getName()==null || "".equals(city.getName()))){
			throw new ExceptionResultInfo("城市名称，或者城市编码不能为空！");
		}
    	List<City> list = cityService.selectList(new EntityWrapper<City>().eq("code", city.getCode()));
    	if (list.size()>0) {
			throw new ExceptionResultInfo("城市编码重复，请重新填写！");
		}
    	List<City> nameList = cityService.selectList(new EntityWrapper<City>().eq("code", city.getName()));
    	if(nameList.size()>0){
    		throw new ExceptionResultInfo("城市名称重复，请重新填写！");
    	}
    }
    
    
    @RequiresMethodPermissions({"update","create"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(City city, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(city, result)) {
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
            if (StringUtils.isEmpty(city.getId())) {
                cityService.add(city);
            } else {
                cityService.addOrUpdate(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail( e.getMessage());
        }
        return ajaxJson;
    }

    @RequiresMethodPermissions("delete")
    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            cityService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequiresMethodPermissions("delete")
    @RequestMapping(value = "batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            cityService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }
    @RequiresMethodPermissions("list")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        City city = get(id);
        model.addAttribute("data", city);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<City> entityWrapper = new EntityWrapper<City>(entityClass);
            valid = cityService.doValid(duplicateValid,entityWrapper);
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

