package cn.jeeweb.modules.excel;

import cn.jeeweb.modules.excel.validate.ValidateResult;


public interface DataConvert<T> {


	public ValidateResult validate(T domain, int realRow,String[][] beanDatas);


	public T convertToDomain(String[] cellValues);


	T convertToDomain(String[] cellValues, ValidateResult result,
			String[][] beanDatas);


	//判断是新增还是更新
	public boolean isRepeatData(T domain);

	//插入
	public T persistenceDomain(T domain);
	
	//修改
	public T updateDomain(T domain);

}
