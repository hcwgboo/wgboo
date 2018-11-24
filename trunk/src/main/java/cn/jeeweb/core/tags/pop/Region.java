package cn.jeeweb.core.tags.pop;

import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.SpringContextHolder;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.service.IAddressService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanw
 * @version V1.0
 * @Title:
 * @Description:
 * @date 2018-04-11 20:18
 */
@SuppressWarnings("unchecked")
public class Region {

	public static final String area_name = "areaName";
	public static final String area_code = "areaCode";
	public static final String city_code = "cityCode";
	public static final String city_name = "cityName";
	public static final String province_code = "provinceCode";
	public static final String province_name = "provinceName";

	private static IAddressService addressService = SpringContextHolder.getBean(IAddressService.class);

	// 查省，parent 为空且 type =1
	public static List<Addres> getProvince() {
		List<Addres> list = addressService.selectList(new EntityWrapper<Addres>().eq("type", "1"));
		return list;
	}

	// 查市 根据省value查所有的市， type=2
	public static List<Addres> getCity(String provinceValue) {
		if (ObjectUtils.isNullOrEmpty(provinceValue))
			return null;
		return addressService.selectList(new EntityWrapper<Addres>().eq("parent", provinceValue).eq("type", "2"));
	}

	// 查区县 根据市value查所有的区县， type=3
	public static List<Addres> getArea(String cityValue) {
		if (ObjectUtils.isNullOrEmpty(cityValue))
			return null;
		return addressService.selectList(new EntityWrapper<Addres>().eq("parent", cityValue).eq("type", "3"));
	}

	// 根据省value 查省的name
	public static String getProvinceName(String provinceValue) {
		if (ObjectUtils.isNullOrEmpty(provinceValue))
			return null;
		Addres addres = addressService
				.selectOne(new EntityWrapper<Addres>().eq("value", provinceValue).eq("type", "1"));
		return addres != null ? addres.getName() : null;
	}

	// 根据市value 查市的name
	public static String getCityName(String cityValue) {
		if (ObjectUtils.isNullOrEmpty(cityValue))
			return null;
		Addres addres = addressService.selectOne(new EntityWrapper<Addres>().eq("value", cityValue).eq("type", "2"));
		return addres != null ? addres.getName() : null;
	}

	// 根据区县value 查区县的name
	public static String getAreaName(String areaValue) {
		if (ObjectUtils.isNullOrEmpty(areaValue))
			return null;
		Addres addres = addressService.selectOne(new EntityWrapper<Addres>().eq("value", areaValue).eq("type", "3"));
		return addres != null ? addres.getName() : null;
	}

	// 根据区域value,查询区域(含市编码)
	public static Addres getAreaNameAndCityCode(String areaValue) {
		if (ObjectUtils.isNullOrEmpty(areaValue))
			return null;
		return addressService.selectOne(new EntityWrapper<Addres>().eq("value", areaValue).eq("type", "3"));
	}

	// 根据市value,查询区域（含省级编码)
	public static Addres getCityIdAndProvinceId(String cityValue) {
		if (ObjectUtils.isNullOrEmpty(cityValue))
			return null;
		return addressService.selectOne(new EntityWrapper<Addres>().eq("value", cityValue).eq("type", "2"));
	}

	// 根据 省级名称，返回对应的value
	public static String getProvinceId(String provinceName) {
		if (ObjectUtils.isNullOrEmpty(provinceName))
			return null;
		Addres addres = addressService.selectOne(new EntityWrapper<Addres>().eq("name", provinceName).eq("type", "1"));
		return addres != null ? addres.getValue() : null;
	}

	// 根据 市名称，返回市对应的value
	public static String getCityId(String cityName) {
		if (ObjectUtils.isNullOrEmpty(cityName))
			return null;
		Addres addres = addressService.selectOne(new EntityWrapper<Addres>().eq("name", cityName).eq("type", "2"));
		return addres != null ? addres.getValue() : null;
	}

	// 根据区域名称，查询区域value
	public static String getAreaId(String areaName) {
		if (ObjectUtils.isNullOrEmpty(areaName))
			return null;
		Addres addres = addressService.selectOne(new EntityWrapper<Addres>().eq("name", areaName).eq("type", "3"));
		return addres != null ? addres.getValue() : null;
	}

	// 根据 名称获取父级
	public static Map<String, String> getValueAndParent(String name) {

		Map<String, String> map = new HashMap<>();
		if (ObjectUtils.isNullOrEmpty(name))
			return map;
		String areaValue = getAreaId(name);
		if (!ObjectUtils.isNullOrEmpty(areaValue)) {
			Addres area = getAreaNameAndCityCode(areaValue);
			Addres city = getCityIdAndProvinceId(area.getParent());
			String provinceName = getProvinceName(city.getParent());
			map.put("areaId", areaValue);
			map.put("areaName", name);
			map.put("cityId", city.getValue());
			map.put("cityName", city.getName());
			map.put("provinceId", city.getParent());
			map.put("provinceName", provinceName);
		} else {// 非区县
			String cityId = getCityId(name);
			if (!ObjectUtils.isNullOrEmpty(cityId)) {
				Addres city = getCityIdAndProvinceId(cityId);
				String provinceName = getProvinceName(city.getParent());
				map.put("cityId", cityId);
				map.put("cityName", name);
				map.put("provinceId", city.getParent());
				map.put("provinceName", provinceName);
			} else {// 非市
				String provinceId = getProvinceId(name);
				if (!ObjectUtils.isNullOrEmpty(provinceId)) {
					map.put("provinceId", provinceId);
					map.put("provinceName", name);
				}
			}
		}
		return map;
	}

	// 根据局域code，获取对应的省名称，省编码，市名称，市编码，区名称
	public static Map<String, String> getAddr(String areaCode) {
		Map<String, String> map = new HashMap<>();
		if (ObjectUtils.isNullOrEmpty(areaCode))
			return map;
		Addres areaName = getAreaNameAndCityCode(areaCode);
		if(areaName==null) {
			areaName = addressService.selectOne(new EntityWrapper<Addres>().eq("parent", areaCode).eq("type", "3"));
		}
		
		if (!ObjectUtils.isNullOrEmpty(areaName)) {
			map.put("areaName", areaName.getName());
			map.put("areaCode", areaName.getValue());
			map.put("cityCode", areaName.getParent());
		}
		if (!ObjectUtils.isNullOrEmpty(areaName) && !ObjectUtils.isNullOrEmpty(areaName.getParent())) {
			Addres city = getCityIdAndProvinceId(areaName.getParent());
			if (!ObjectUtils.isNullOrEmpty(city))
				map.put("provinceCode", city.getParent());
			if (!ObjectUtils.isNullOrEmpty(city))
				map.put("cityName", city.getName());
			if (!ObjectUtils.isNullOrEmpty(city.getParent()))
				map.put("provinceName", getProvinceName(city.getParent()));
		}
		return map;
	}

	public static Map<String, Object> validateRegion(String provinceName, String cityName, String areaName) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(provinceName) || StringUtils.isEmpty(cityName) || StringUtils.isEmpty(areaName)) {
			return map;
		}
		String provinceId = getProvinceId(provinceName);
		if (!ObjectUtils.isNullOrEmpty(provinceId)) {
			map.put("provinceId", provinceId);
			map.put("provinceName", provinceName);
		} else {
			return map;
		}
		List<Addres> cityList = getCity(provinceId);
		List<String> cityNames = cityList.stream().map(Addres::getName).collect(Collectors.toList());
		String cityId = "";
		if (cityNames.contains(cityName)) {
			cityId = getCityId(cityName);
			map.put("cityId", cityId);
			map.put("cityName", getCityName(cityId));
		} else {
			return map;
		}
		List<Addres> areaList = getArea(cityId);
		List<String> areaNames = areaList.stream().map(Addres::getName).collect(Collectors.toList());
		if (areaNames.contains(areaName)) {
			String areaId = getAreaId(areaName);
			map.put("areaId", areaId);
			map.put("areaName", areaName);
		}
		return map;
	}

	// 查市 根据市value查市， type=2
	public static List<Addres> getCityByValues(List<String> values) {
		if (ObjectUtils.isNullOrEmpty(values))
			return null;
		return addressService.selectList(new EntityWrapper<Addres>().in("value", values).eq("type", "2"));
	}
}
