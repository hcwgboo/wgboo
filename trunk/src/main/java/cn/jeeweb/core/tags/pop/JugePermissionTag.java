package  cn.jeeweb.core.tags.pop;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jeeweb.modules.sys.utils.UserUtils;

/**
 * 
 * @author zhangyouwei
 * @email zhangyouwei1988@sina.cn
 * @date 2018年2月5日 上午9:09:56
 * @Description：自定义权限标签 单独使用
 *
 */
public class JugePermissionTag extends TagSupport {
	private static Logger logger = LoggerFactory
			.getLogger(JugePermissionTag.class);
	private String permission;

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int doStartTag() throws JspException {

		if (permission == null || permission.length() == 0) {
			return Tag.SKIP_BODY;
		}

		String[] permissions = permission.split(",");

		try {
			for (int i = 0; i < permissions.length; i++) {
				if (UserUtils.getPermissionsList().contains(this.permission)) {
					return Tag.EVAL_BODY_INCLUDE;
				}
			}
		
		} catch (Exception e) {
			logger.error("JugePermissionTag报错", e);
		}
		return Tag.SKIP_BODY;
		
	}

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}
}
