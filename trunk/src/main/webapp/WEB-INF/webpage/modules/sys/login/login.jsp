<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <title><spring:message code="sys.site.title" arguments="${platformName}"/></title>
    <meta name="keywords" content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
    <meta name="description" content="<spring:message code="sys.site.description" arguments="${platformName}"/>">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="Thu, 19 Nov 1900 08:52:00 GMT">
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="${staticPath}/vendors/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
    <html:css  name="favicon,bootstrap,font-awesome,animate,pace,iCheck,toastr,bootstrapvalidator"/>
    <!--Loading style-->
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/themes/style1/orange-blue.css" class="default-style">
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/themes/style1/orange-blue.css" id="theme-change" class="style-change color-change">
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/style-responsive.css">
    <link type="text/css" rel="stylesheet" href="${staticPath}/uadmin/css/login/login.css">
    <style type="text/css">
        .company{
            height: 10px;
            margin-bottom: 40px;
        }
        .companyinput{
            width: 300px;
            height: 46px;
            border: 1px solid #ccc;
            outline: none;
            font-size: 16px;
            padding-left: 10px;
        }
    </style>
</head>

<body style="overflow: scroll">
<div class="page-form" style="background: #f0f2f5;">
    <form  id="loginform"  method="post" >
        <div class="logo"><img src="${staticPath}/common/img/logo.png" alt=""></div>
        <div class="body-content">
            <p class="welcome"><spring:message code="sys.site.title" arguments="${platformName}"/></p>
            <div class="user">
                <div class="user-icon">
                	<img src="${staticPath}/common/img/user.png"/>	
                </div>
                <input type="text " name="username" class="form-control" placeholder="<spring:message
                    code="sys.login.username.placeholder"/>">
            </div>
            <div class="pwd">
                <div class="pwd-icon">
                	<img src="${staticPath}/common/img/key.png"/>
                </div>
                <input type="password" name="password" class="form-control"  placeholder="<spring:message code="sys.login.password.placeholder"/>">
            </div>
            <c:if test="${showCaptcha eq 1}">
                <div class="form-group">
                    <div class="pull-left">
                        <input   name="jcaptchaCode" class="form-control" placeholder="<spring:message code="sys.login.captcha.placeholder"/>" required="">
                    </div>
                    <div class="pull-right">
                        <img id="img_jcaptcha"  src="${adminPath}/jcaptcha.jpg" width="100" height="35" onclick="changeJcaptchaSrc();" />
                    </div>
                </div>
                <div class="clearfix"></div>
            </c:if>
            <div class="form-group" style="margin-top:10px;">
                <div class="pull-left">
                    <div class="checkbox-list">
                        <label><input id="rememberMe"  type="checkbox" name="rememberMe" value="1" class="i-checks">&nbsp;
                            <spring:message code="sys.login.rememberMe"/></label>
                    </div>
                    
                </div>
                 <a style="display:inline-block;margin-top:13px;" class="pull-right" id="doRegister" >立即注册</a>
                <div class="">
                    <button type="submit" id="login-btn">登录</button>
                </div>
            </div>
            <div class="clearfix"></div>
            <!-- <p class="faq"><span style="color:#999999;">如您没有账号，请联系： </span> <span style="color:#000000">xxx-xxx </span></p> -->
            <div class=" " style="position: fixed;bottom: 0;margin-left:-70px;">
            <div class=" " style="position: fixed;bottom: 0;">

        </div>
            </div>
    </form>
</div>
<div id="">

</div>
<div class=" " style="width:500px;bottom: 0;margin-left: 0px;margin-top:50px;">
    <p style=" text-align: center;">Copyright©2018 潢川微广播网络科技有限公司. All Rights Reserved.
           </p>
    <p  style=" text-align: center;">
        	豫ICP备18039659号-1
    </p>
</div>
<html:js  name="jquery,bootstrap,iCheck,bootstrap-hover-dropdown,toastr,bootstrapvalidator"/>
<script src="${staticPath}/uadmin/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${staticPath}/uadmin/js/jquery-ui.js"></script>
<script src="${staticPath}/uadmin/js/html5shiv.js"></script>
<script src="${staticPath}/uadmin/js/respond.min.js"></script>
<script src="${staticPath}/uadmin/js/jquery.menu.js"></script>
<script src="${staticPath}/common/js/curdtools_jqgrid.js"></script>
<script>
    //BEGIN CHECKBOX & RADIO
    $('input[type="checkbox"]').iCheck({
        checkboxClass: 'icheckbox_minimal-grey',
        increaseArea: '20%' // optional
    });
    $('input[type="radio"]').iCheck({
        radioClass: 'iradio_minimal-grey',
        increaseArea: '20%' // optional
    });
    //END CHECKBOX & RADIO
    function changeJcaptchaSrc(){
        document.getElementById("img_jcaptcha").src='${adminPath}/jcaptcha.jpg?_='+(new Date()).getTime();
    }
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#loginform").bootstrapValidator();
        var error="${error}";
        if (error!="")
        {
            showToast(error,"error");
        }
    });

    function showToast(msg,shortCutFunction)
    {
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "progressBar": true,
            "positionClass": "toast-bottom-right",
            "onclick": null,
            "showDuration": "400",
            "hideDuration": "1000",
            "timeOut": "7000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
        toastr[shortCutFunction](msg,"提示");
    }

    $("#doRegister").click(function () {
       // $("#loginform").hide();
        //$("#regisetrform").show();
        window.location.href = "${adminPath}/sys/merchantregister/register";
    });

    $("#doLogin").click(function () {
        $("#loginform").show();
        $("#regisetrform").hide();
    });

    $("#register-btn").click(function () {
        var param = {
            companyname : $("#companyname").val(),
            realname : $("#realname").val(),
            username : $("#username").val(),
            password1 : $("#password1").val(),
            password2 : $("#password2").val(),
        }
        $.ajax({
            type: 'post',
            dataType: 'json',
            data: param,
            url:"${adminPath}/register",
            success:function (data) {
                debugger
                if(data.ret){
                    msgDialog(data.msg);
                    $("#loginform").show();
                    $("#regisetrform").hide();
                    $("#companyname").val("");
                    $("#realname").val("");
                    $("#username").val("");
                    $("#password1").val("");
                    $("#password2").val("");
                }else {
                    msgDialog();
                }
            },
            error:function (error) {
                msgDialog();
            }
        })
    });

</script>
</body>

</html>