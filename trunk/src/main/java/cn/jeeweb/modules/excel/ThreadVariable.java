package cn.jeeweb.modules.excel;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ThreadVariable {
	private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

	public static void clearThreadVariable() {
		threadLocal.remove();
	}

	public static void setModuleOne(String moduleOne) {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
		}
		map.put(ExcelImportConstant.MODULE_ONE, moduleOne);
		threadLocal.set(map);
	}

	public static String getModuleOne() {
		Map map = (Map) threadLocal.get();
		if (map != null) {
			return (String) map.get(ExcelImportConstant.MODULE_ONE);
		}
		return null;
	}

	public static void setModuleTow(String moduleTow) {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
		}
		map.put(ExcelImportConstant.MODULE_TOW, moduleTow);
		threadLocal.set(map);
	}

	public static String getModuleTow() {
		Map map = (Map) threadLocal.get();
		if (map != null) {
			return (String) map.get(ExcelImportConstant.MODULE_TOW);
		}
		return null;
	}

	public static void setModuleThree(String moduleThree) {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
		}
		map.put(ExcelImportConstant.MODULE_THREE, moduleThree);
		threadLocal.set(map);
	}

	public static String getModuleThree() {
		Map map = (Map) threadLocal.get();
		if (map != null) {
			return (String) map.get(ExcelImportConstant.MODULE_THREE);
		}
		return null;
	}

	public static void setImportNo(String importNo) {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
		}
		map.put(ExcelImportConstant.IMPORT_NO, importNo);
		threadLocal.set(map);
	}

	public static String getImportNo() {
		Map map = (Map) threadLocal.get();
		if (map != null) {
			return (String) map.get(ExcelImportConstant.IMPORT_NO);
		}
		return null;
	}

	public static void setModule(String module) {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
		}
		map.put(ExcelImportConstant.MODULE, module);
		threadLocal.set(map);
	}

	public static String getModule() {
		Map map = (Map) threadLocal.get();
		if (map != null) {
			return (String) map.get(ExcelImportConstant.MODULE);
		}
		return null;
	}
	
	public static void setSourcesState(Object sourcesState) {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
		}
		map.put("sourcesState", sourcesState);
		threadLocal.set(map);
	}

	public static Object getSourcesState() {
		Map map = (Map) threadLocal.get();
		if (map != null) {
			return (Object) map.get("sourcesState");
		}
		return null;
	}
	
//	public static void setLoginUserInfo(Object loginUserInfo) {
//		Map map = (Map) threadLocal.get();
//		if (map == null) {
//			map = new HashMap();
//		}
//		map.put(LoginUserInfoUtil.LOGIN_USER_INFO, loginUserInfo);
//		threadLocal.set(map);
//	}
//
//	public static LoginUserInfo getLoginUserInfo() {
//		Map map = (Map) threadLocal.get();
//		if (map != null) {
//			return (LoginUserInfo) map.get(LoginUserInfoUtil.LOGIN_USER_INFO);
//		}
//		return null;
//	}


	public static void setSchedulerState(Object schedulerState) {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
		}
		map.put("schedulerState", schedulerState);
		threadLocal.set(map);
	}

	public static Object getSchedulerState() {
		Map map = (Map) threadLocal.get();
		if (map != null) {
			return (Object) map.get("schedulerState");
		}
		return null;
	}
	
//	public static void setSchedulerLoginUserInfo(Object loginUserInfo) {
//		Map map = (Map) threadLocal.get();
//		if (map == null) {
//			map = new HashMap();
//		}
//		map.put("schedulerLoginUserInfo", loginUserInfo);
//		threadLocal.set(map);
//	}

//	public static LoginUserInfo getSchedulerLoginUserInfo() {
//		Map map = (Map) threadLocal.get();
//		if (map != null) {
//			return (LoginUserInfo) map.get("schedulerLoginUserInfo");
//		}
//		return null;
//	}

	public static HttpServletRequest getHttpServletRequest() {
		Map map = (Map) threadLocal.get();
		if (map != null) {
			return (HttpServletRequest) map.get("httpServletRequest");
		}
		return null;
	}
	public static void setHttpServletRequest(Object httpServletRequest) {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = new HashMap();
		}
		map.put("httpServletRequest", httpServletRequest);
		threadLocal.set(map);
	}
}