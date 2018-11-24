<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="decorator" content="form"/> 
</head>

<body class="white-bg"  formid="changeuserpageForm">
    <form:form id="changeuserpageForm" modelAttribute="data"  method="post" action="${adminPath }/changeUserAccount" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		       <tr>
		         <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>切换的账号:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="username" class="form-control " datatype="*" nullmsg="切换的账号！" htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
		   </tbody>
		</table>   
	</form:form>
</body>
</html>