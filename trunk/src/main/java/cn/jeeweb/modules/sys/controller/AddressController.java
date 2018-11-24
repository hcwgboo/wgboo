package cn.jeeweb.modules.sys.controller;

import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.tags.pop.Addres;
import cn.jeeweb.core.tags.pop.Region;
import cn.jeeweb.core.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("${admin.url.prefix}/sys/address")
@RequiresPathPermission("sys:address")
public class AddressController {

    @RequestMapping(value = "getAreaByCityId", method = RequestMethod.GET)
    @ResponseBody
    public List<Addres> getAreaByCityId(HttpServletRequest request) throws ExceptionResultInfo {
        String cityId = request.getParameter("cityId");
        if (StringUtils.isEmpty(cityId)) throw new ExceptionResultInfo("参数错误");
        List<Addres> area = Region.getArea(cityId);
        return area;
    }

    @RequestMapping(value = "getCityByProvinceId", method = RequestMethod.GET)
    @ResponseBody
    public List<Addres> getCityByProvinceId(HttpServletRequest request) throws ExceptionResultInfo {
        String provinceId = request.getParameter("provinceId");
        if (StringUtils.isEmpty(provinceId)) throw new ExceptionResultInfo("参数错误");
        List<Addres> city = Region.getCity(provinceId);
        return city;
    }
}
