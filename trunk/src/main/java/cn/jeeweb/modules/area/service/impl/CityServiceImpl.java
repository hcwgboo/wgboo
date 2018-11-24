package cn.jeeweb.modules.area.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.utils.ServletUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.area.entity.Area;
import cn.jeeweb.modules.area.entity.City;
import cn.jeeweb.modules.area.mapper.CityMapper;
import cn.jeeweb.modules.area.service.IAreaService;
import cn.jeeweb.modules.area.service.ICityService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: 城市信息
 * @Description: 城市信息
 * @author zhangyouwei
 * @date 2018-02-02 09:33:55
 * @version V1.0
 *
 */
@Transactional(noRollbackFor= Exception.class)
@Service("cityService")
public class CityServiceImpl extends CommonServiceImpl<CityMapper, City> implements ICityService {
	@Autowired
	private IAreaService areaService;

	@Override
	public boolean insert(City city) {
		// 保存主表
		super.insert(city);
		/*// 保存区域信息
		String areaListJson = StringEscapeUtils.unescapeHtml4(ServletUtils.getRequest().getParameter("areaListJson"));
		List<Area> areaList = JSONObject.parseArray(areaListJson, Area.class);
		for (Area area : areaList) {
			// 保存字段列表
			area.setCity(city);
			areaService.insert(area);
		}*/
		return true;
	}

	@Override
	public boolean insertOrUpdate(City city) {
		try {
			/*// 获得以前的数据
			List<Area> oldAreaList = areaService
					.selectList(new EntityWrapper<Area>(Area.class).eq("city.id", city.getId()));
			// 字段
			String areaListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("areaListJson"));
			List<Area> areaList = JSONObject.parseArray(areaListJson, Area.class);*/
			// 更新主表
			super.insertOrUpdate(city);
			/*areaList = JSONObject.parseArray(areaListJson, Area.class);
			List<String> newsAreaIdList = new ArrayList<String>();
			// 保存或更新数据
			for (Area area : areaList) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(area.getId())) {
					// 保存字段列表
					area.setCity(city);
					areaService.insert(area);
				} else {
					areaService.insertOrUpdate(area);
				}
				newsAreaIdList.add(area.getId());
			}

			// 删除老数据
			for (Area area : oldAreaList) {
				String areaId = area.getId();
				if (!newsAreaIdList.contains(areaId)) {
					areaService.deleteById(areaId);
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}

	@Override
	public List<City> selectCityList() {

		return baseMapper.selectCityList();
	}

	@Transactional
	@Override
	public Boolean add(City city) {
		// 保存主表
		insert(city);
		// 保存区域信息
		String areaListJson = StringEscapeUtils.unescapeHtml4(ServletUtils.getRequest().getParameter("areaListJson"));
		List<Area> areaList = JSONObject.parseArray(areaListJson, Area.class);
		if (areaList.size()==0) {
			return true;
		}
		List<String> codeList = areaList.stream().map(Area::getCode).collect(Collectors.toList());
		List<Area> list = areaService.selectList(new EntityWrapper<Area>().in("code", codeList));
		if (list.size() > 0) {
			throw new RuntimeException ("区域编码重复，请重新填写！");
		}
		List<String> nameListStr = areaList.stream().map(Area::getName).collect(Collectors.toList());
		List<Area> nameList = areaService.selectList(new EntityWrapper<Area>().in("code", nameListStr));
		if(nameList.size()>0){
			throw new RuntimeException ("区域名称重复，请重新填写！");
		}
		for (Area area : areaList) {
			// 保存字段列表
			area.setCity(city);
			areaService.insert(area);
		}
		return true;
	}

	@Transactional
	@Override
	public Boolean addOrUpdate(City city){
		try {
			// 获得以前的数据
			List<Area> oldAreaList = areaService
					.selectList(new EntityWrapper<Area>(Area.class).eq("city.id", city.getId()));
			// 字段
			String areaListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("areaListJson"));
			List<Area> areaList = JSONObject.parseArray(areaListJson, Area.class);
			// 更新主表
			insertOrUpdate(city);
			if (areaList.size()==0) {
				return true;
			}
			//区域名称和编码不能重复
			addOrUpdateArea(areaList);

			areaList = JSONObject.parseArray(areaListJson, Area.class);
			List<String> newsAreaIdList = new ArrayList<String>();
			// 保存或更新数据
			for (Area area : areaList) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(area.getId())) {
					// 保存字段列表
					area.setCity(city);
					areaService.insert(area);
				} else {
					areaService.insertOrUpdate(area);
				}
				newsAreaIdList.add(area.getId());
			}

			// 删除老数据
			for (Area area : oldAreaList) {
				String areaId = area.getId();
				if (!newsAreaIdList.contains(areaId)) {
					areaService.deleteById(areaId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}

	private void addOrUpdateArea(List<Area> areaList){
		List<String> codeList = areaList.stream().map(Area::getCode).collect(Collectors.toList());
		List<Area> list = areaService.selectList(new EntityWrapper<Area>().in("code", codeList));
		if (list.size() > 0) {
			throw new RuntimeException ("区域编码重复，请重新填写！");
		}
		List<String> nameListStr = areaList.stream().map(Area::getName).collect(Collectors.toList());
		List<Area> nameList = areaService.selectList(new EntityWrapper<Area>().in("code", nameListStr));
		if(nameList.size()>0){
			throw new RuntimeException ("区域名称重复，请重新填写！");
		}
	}

}
