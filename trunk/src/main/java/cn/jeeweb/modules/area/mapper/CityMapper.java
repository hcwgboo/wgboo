package cn.jeeweb.modules.area.mapper;

import cn.jeeweb.modules.area.entity.City;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**   
 * @Title: 城市信息数据库控制层接口
 * @Description: 城市信息数据库控制层接口
 * @author zhangyouwei
 * @date 2018-02-02 09:33:55
 * @version V1.0   
 *
 */
public interface CityMapper extends BaseMapper<City> {
	List<City> selectCityList();
}