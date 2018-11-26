<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>广告规则</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor,common" />
</head>

<body class="white-bg"  formid="advAdvertiseRuleForm">
    <form:form id="advAdvertiseRuleForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">
		              <label><font color="red">*</font>规则名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false" class="form-control" datatype="*" nullmsg="规则名称不能为空！"  />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>商户类型:</label>
					</td>
					<td class="width-35">
						<form:select path="type" htmlEscape="false" class="form-control" dict="shlx" ></form:select>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>任务类型:</label>
					</td>
					<td class="width-35">
						<form:select path="taskType" htmlEscape="false" class="form-control" dict="rwlx" ></form:select>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>平台佣金比例:</label>
					</td>
					<td class="width-35">
						<form:input path="ratio" htmlEscape="false" onblur="ratioChange();" class="form-control"  datatype="*"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>上级分销佣金占比:</label>
					</td>
					<td class="width-35">
						<form:input path="superiorCommissionRatio" onblur="ratioChange();" htmlEscape="false" class="form-control"  datatype="*"     />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>下级分销佣金占比:</label>
					</td>
					<td class="width-35">
						<form:input path="subCommissionRatio" onblur="ratioChange();" htmlEscape="false" class="form-control"  datatype="*"     />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		   		<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>最少条数:</label>
					</td>
					<td class="width-35">
						<form:input path="minSize" htmlEscape="false" class="form-control"  datatype="n"     />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>每条最少金额:</label>
					</td>
					<td class="width-35">
						<form:input path="minMoney" htmlEscape="false" class="define-input-class"  datatype="n" style="width: 200px;"   /><span style="color:red;font-size: 20px;">（分）</span>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		   		<tr>
					<td  class="width-15 active text-right">
						<label>说明:</label>
					</td>
					<td class="width-35" colspan="3">
						<form:textarea path="describe" htmlEscape="false" class="form-control" rows="3"   ></form:textarea>
					</td>
				</tr>

		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor,jqGrid_curdtools" />
<script type="text/javascript">
	function ratioChange() {
		var ratio = $("#ratio").val();
		ratio = parseFloat(ratio);
        var sup = $("#superiorCommissionRatio").val();
        sup = parseFloat(sup);
        var sub = $("#subCommissionRatio").val();
        sub = parseFloat(sub);
        if(ratio && sup && sub){
			if((ratio + sub + sup) != 1){
                $("#ratio").val("")
                $("#superiorCommissionRatio").val("");
                $("#subCommissionRatio").val("");
			    msgDialog("平台、上级分销、下级分销佣金之和等于1");
			}
		}
    }

</script>
</body>
</html>