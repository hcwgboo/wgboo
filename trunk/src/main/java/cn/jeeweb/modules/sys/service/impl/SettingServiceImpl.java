package cn.jeeweb.modules.sys.service.impl;


import cn.jeeweb.modules.sys.service.ISettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("settingService")
public class SettingServiceImpl implements ISettingService {
	
}
