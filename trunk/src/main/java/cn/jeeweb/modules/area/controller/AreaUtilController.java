package cn.jeeweb.modules.area.controller;

import cn.jeeweb.core.common.controller.BaseBeanController;
import cn.jeeweb.core.tags.pop.Addres;
import cn.jeeweb.core.tags.pop.Region;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.area.entity.Area;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("${admin.url.prefix}/area/city")
public class AreaUtilController extends BaseBeanController<Area> {

	@RequestMapping(value = "findCity")
	@ResponseBody
	protected void findCity(ServletRequest request, ServletResponse response,
			@RequestParam(value = "provinceId", required = true) String provinceId) throws Exception {

		List<Addres> citys = Region.getCity(provinceId);
		StringUtils.printJson((HttpServletResponse) response, citys);
	}

	@RequestMapping(value = "findArea")
	@ResponseBody
	protected void findArea(ServletRequest request, ServletResponse response,
							@RequestParam(value = "cityId", required = true) String cityId) throws Exception {

		List<Addres> areas = Region.getArea(cityId);
		StringUtils.printJson((HttpServletResponse) response, areas);
	}


}
