<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>上架资金</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor,common" />
</head>

<body class="white-bg"  formid="merCapitalForm">
    <form:form id="merCapitalForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="merchantId"      />
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>账户余额:</label>
		            </td>
					<td class="width-35">
						<form:input path="balanceMoney" disabled="true" htmlEscape="false" class="form-control"      />
					</td>
					<td  class="width-15 active text-right">
						<label>充值金额:</label>
					</td>
					<td class="width-35">
						<form:input path="chargeMoney" htmlEscape="false" datatype="n"
									class="define-input-class"  style="width: 195px;" /><span style="color:red;font-size: 20px;">（元）</span>
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