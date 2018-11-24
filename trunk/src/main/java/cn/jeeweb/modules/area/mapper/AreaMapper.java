package cn.jeeweb.modules.area.mapper;

import cn.jeeweb.modules.area.entity.Area;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**   
 * @Title: 区域信息数据库控制层接口
 * @Description: 区域信息数据库控制层接口
 * @author zhangyouwei
 * @date 2018-02-02 09:33:55
 * @version V1.0   
 *
 */
public interface AreaMapper extends BaseMapper<Area> {

	List<Area> selectAreaListByCityId(String cityId);
    
}