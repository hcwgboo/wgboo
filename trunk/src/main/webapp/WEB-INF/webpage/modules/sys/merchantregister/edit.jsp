<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>商家注册</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor,common" />
</head>

<body class="white-bg"  formid="merchantRegisterForm">
    <form:form id="merchantRegisterForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">
		              <label>真实名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="realname" htmlEscape="false" disabled="true" class="form-control"      />
					</td>
					<td  class="width-15 active text-right">	
		              <label>用户名:</label>
		            </td>
					<td class="width-35">
						<form:input path="username" htmlEscape="false" disabled="true" class="form-control"      />
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label>公司名称:</label>
					</td>
					<td class="width-35">
						<form:input path="companyname" htmlEscape="false" disabled="true" class="form-control"      />
					</td>
					<td  class="width-15 active text-right">
						<label>联系电话:</label>
					</td>
					<td class="width-35">
						<form:input path="phone" htmlEscape="false" disabled="true"  class="form-control"      />
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
		              <label>商家状态:</label>
		            </td>
					<td class="width-35">
						<form:select path="status" htmlEscape="false" disabled="true"  class="form-control"   dict="sjsh"   ></form:select>
					</td>
					<td  class="width-15 active text-right">
						<label>创建时间:</label>
					</td>
					<td class="width-35">
						<form:input path="createDate" htmlEscape="false" datefmt="yyyy-MM-dd HH:mm:ss" disabled="true"  class="form-control"      />
					</td>
				</tr>
		   </tbody>
		</table>
		<div id='showPic'></div>
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<script type="text/javascript">
    $(document).ready(function() {
		var list = ${urls}
		if(list.length > 0){
            var img= "<div class='addPicWrapper' style='border: 1px solid #aeaeae;padding: 10px'>";
		    for(var i=0;i<list.length;i++){
				img += "<img src='"+list[i]+"' id='"+id+"' class='file-preview-image'  style='border:1px  solid #aeaeae;padding:5px;'/>";
            }
            img += "</div>";
            $("#showPic").append(img);
        }
    });
</script>
</body>
</html>