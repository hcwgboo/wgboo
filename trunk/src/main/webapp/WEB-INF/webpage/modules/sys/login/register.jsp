<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="fns" uri="/WEB-INF/tlds/jeeweb-functions.tld"%>
<%@taglib prefix="form" uri="/WEB-INF/tlds/jeeweb-form.tld"%>
<%@taglib prefix="grid" uri="/WEB-INF/tlds/jeeweb-grid.tld"%>
<%@taglib prefix="html" uri="/WEB-INF/tlds/jeeweb-html.tld"%>
<%@taglib prefix="view" uri="/WEB-INF/tlds/jeeweb-view.tld"%>
<%@taglib prefix="pop" uri="/WEB-INF/tlds/jeeweb-pop.tld"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="adminPath" value="${pageContext.request.contextPath}${fns:getAdminUrlPrefix()}" />
<c:set var="appPath" value="${pageContext.request.contextPath}"/>
<c:set var="staticPath" value="${pageContext.request.contextPath}${fns:getStaticUrlPrefix()}/static"/>

<spring:message code="platform.name"  var="platformName"/>
<spring:message code="platform.copyright"  var="platformCopyright"/>
<spring:message code="platform.version"  var="platformVersion"/>

<!DOCTYPE html>
<html>
<head>
    <title>商家注册</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="merchantRegisterForm">
    <form:form id="merchantRegisterForm" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">
		              <label><font color="red">*</font>id:</label>
		            </td>
					<td class="width-35">
						<form:input path="id" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
		              <label>公司名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="companyname" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
		              <label>真实名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="realname" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
		              <label><font color="red">*</font>用户名:</label>
		            </td>
					<td class="width-35">
						<form:input path="username" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
		              <label>密码:</label>
		            </td>
					<td class="width-35">
						<form:input path="password" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
		              <label>salt:</label>
		            </td>
					<td class="width-35">
						<form:input path="salt" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
		              <label>联系电话:</label>
		            </td>
					<td class="width-35">
						<form:input path="phone" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
		              <label>商家状态:</label>
		            </td>
					<td class="width-35">
						<form:input path="status" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
		              <label>附件ids:</label>
		            </td>
					<td class="width-35">
						<form:input path="ids" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>

		   </tbody>
		</table>
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
</html>