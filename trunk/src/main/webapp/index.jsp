<%@ page import="cn.jeeweb.modules.sys.tags.SysFunctions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<% String adminPath = SysFunctions.getAdminUrlPrefix();
    request.setAttribute("adminPath",adminPath);
%>


<!DOCTYPE html>
<html>
<body>
<script type="text/javascript">
    window.setTimeout(function() {
        window.location.replace('${adminPath}/');
    }, 1);
</script>
</body>
</html>