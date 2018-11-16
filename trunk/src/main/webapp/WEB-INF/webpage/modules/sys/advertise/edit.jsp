<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>广告</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor,common" />
</head>

<body class="white-bg"  formid="advertiseForm">
    <form:form id="advertiseForm" modelAttribute="data" method="post" class="form-horizontal">
		<span id="introduce" style="color:red;"></span>
		<form:hidden path="id"/>
		<form:hidden path="minMoney" />
		<form:hidden path="minSize" />
		<form:hidden path="ruleId" />
		<form:hidden path="ratio" />

		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>广告标题:</label>
		            </td>
					<td class="width-35">
						<form:input path="title" htmlEscape="false" class="form-control" datatype="*" nullmsg="请输入广告标题"    />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label>请选择规则:</label>
					</td>
					<td class="width-35">
						<form:select path="ruleName" htmlEscape="false" onchange="ruleChange();" class="form-control" datatype="*" ></form:select>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>总金额:</label>
					</td>
					<td class="width-35">
						<form:input path="totalMoney" onblur="totalMoneyChange();" datatype="n" nullmsg="请输入总金额" htmlEscape="false" style="width: 195px;" class="define-input-class" /><span style="color:red;font-size: 20px;">（元）</span>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label>扣除费用:</label>
					</td>
					<td class="width-35">
						<form:input path="reduceMoney" disabled="true" htmlEscape="false" class="define-input-class" style="width: 195px;" /><span style="color:red;font-size: 20px;">（元）</span>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>转发次数:</label>
					</td>
					<td class="width-35">
						<form:input path="transpond" onblur="transpondChange();" htmlEscape="false" nullmsg="请输入转发次数" class="define-input-class"  style="width: 195px;"      /><span style="color:red;font-size: 20px;">（条）</span>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>转发金额:</label>
					</td>
					<td class="width-35">
						<form:input path="forwardMoney" onblur="forwardMoneyChange();" htmlEscape="false" nullmsg="请输入转发金额" class="define-input-class"  style="width: 195px;"  /><span style="color:red;font-size: 20px;">（分）</span>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>奖励规则:</label>
					</td>
					<td class="width-35" colspan="3">
						<form:textarea path="ruleMain" htmlEscape="false" nullmsg="请输入规则" class="form-control" style="height: 80px;" ></form:textarea>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>广告内容:</label>
					</td>
					<td class="width-35" colspan="3" >
						<form:textarea path="content" htmlEscape="false" nullmsg="请输入内容" class="form-control" style="height: 125px;" ></form:textarea>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor,underscore,jqGrid_curdtools" />
<script type="text/javascript">
	var list = [];
	var minMoney, minSize, ratio;
    $(document).ready(function() {
        var rule = ${rule};
        list = rule;
        for(var i=0; i<rule.length; i++ ){
            $("#ruleName").append('<option value="'+rule[i].advRuleId+'">'+rule[i].advRuleName+'</option>') ;
        }
        if("${data.ruleId}" != ""){
            _.each(list,function (item) {
                if(item.advRuleId == "${data.ruleId}"){
                    ratio = item.ratio;
                    minMoney = item.minMoney;
                    minSize = item.minSize;
                    $("#ruleId").val(item.advRuleId);
                }
            });
        }else {
            ratio = list[0].ratio;
            minMoney = list[0].minMoney;
            minSize = list[0].minSize;
            $("#ruleId").val(list[0].advRuleId);
        }
        $("#introduce").text("当前转发规则的最小金额：" + minMoney + "分，转发的最小条数为：" + minSize + "条。");

        //
		var total = $("#totalMoney").val();
		$("#reduceMoney").val(parseInt(total * ratio));
    });

	function ruleChange() {
        var value = $("#ruleName").find("option:selected").attr("value");
        _.each(list,function (item) {
            if(item.id == value){
                ratio = item.ratio;
                minMoney = item.minMoney;
                minSize = item.minSize;
                $("#advRuleId").val(item.advRuleId);
            }
        });

        $("#totalMoney").val("");
        $("#reduceMoney").val("");
        $("#transpond").val("");
        $("#forwardMoney").val("");
        $("#introduce").text("当前转发规则的最小金额：" + minMoney + "分，转发的最小条数为：" + minSize + "条。");
    }
    
    function totalMoneyChange() {
		debugger
        var total = $("#totalMoney").val();
		if(total){
            var reduce = total * ratio;
            $("#reduceMoney").val(reduce);

            // 检查最少条数，最少金额是否满足
            var transpond = $("#transpond").val();
            if(transpond){
                if(transpond < minSize){
                    msgDialog("广告最小转发条数需要大于" + minSize + "条；");
                    $("#transpond").val("");
                    return;
                }
                var size = (total - reduce) * 100 / minMoney;
                if(size < minSize){
                    msgDialog("广告最小转发条数需要大于" + minSize + "条；");
                    $("#totalMoney").val("");
                    $("#reduceMoney").val("");
                    return;
                }
                $("#transpond").val(parseInt(size));
            }
		}
    }
    
    function transpondChange() {
		debugger
        var transpond = $("#transpond").val();
		if(transpond){
            if(transpond < minSize){
                msgDialog("广告最小转发条数需要大于" + minSize + "条；");
                $("#transpond").val("");
                return;
            }
            var total = $("#totalMoney").val();
            if(total){
                var reduce = total * ratio;
                var perMoney = (total - reduce) * 100 / transpond;
                if(perMoney < minMoney){
                    msgDialog("每条广告转发金额小于最小转发金额" + minMoney + "分；");
                    $("#transpond").val("");
                    return;
                }
                $("#forwardMoney").val(parseInt(perMoney));
            }
		}
    }
    
    function forwardMoneyChange() {
		debugger
        var forwardMoney = $("#forwardMoney").val();
		if(forwardMoney){
            if(forwardMoney < minMoney){
                msgDialog("广告转发最小金额小于" + minMoney + "分；");
                $("#forwardMoney").val("");
                return;
            }
            var total = $("#totalMoney").val();
            if(total){
                var reduce = total * ratio;
                var size = (total - reduce) * 100 / forwardMoney;
                if(size < minSize){
                    msgDialog("广告转发条数小于" + minSize + "条；");
                    $("#forwardMoney").val("");
                    return;
				}
                $("#transpond").val(parseInt(size));
			}
		}
    }
</script>
</body>
</html>