package cn.jeeweb.modules.area.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.area.entity.Area;
import cn.jeeweb.modules.area.mapper.AreaMapper;
import cn.jeeweb.modules.area.service.IAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**   
 * @Title: 区域信息
 * @Description: 区域信息
 * @author zhangyouwei
 * @date 2018-02-02 09:33:55
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("areaService")
public class AreaServiceImpl  extends CommonServiceImpl<AreaMapper,Area> implements IAreaService {

	@Override
	public List<Area> selectAreaListByCityId(String cityId) {
		return baseMapper.selectAreaListByCityId(cityId);
	}

}
