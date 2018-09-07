<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--登录~手机号，发送验证码页面-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="full-screen" content="yes">
    <meta name="x5-fullscreen" content="true">
    <meta name="applicable-device" content="mobile">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, minimal-ui">
    <!--reset.css重置样式文件-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/public/reset.css"/>
    <!--所有html里公共样式，如header、footer-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/public/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css"/>
    <!--flexible.js适配屏幕-->
    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
    <!--fastclick.js解决移动端点透问题-->
    <script src="<%=request.getContextPath() %>/js/public/fastclick.js" type="text/javascript" charset="utf-8"></script>
    <title>登录大象卡</title>
</head>
<body class="max-body">
<h4 class="top_title">输入手机号</h4>
<form id="form_sample_1" action="#" class="form-horizontal dialog" method="post"
      onsubmit="return false;">
    <label for="telNum" class="tel_label clear">
        <span>手机号</span>
        <input type="hidden" id="openId" value="${openid}"/>
        <input type="tel" id="telNum" value="" placeholder="请输入手机号" name="mobile" maxlength="11"/>

    </label>
    <label class="verifCode_label clear">
        <input type="text" id="verifCode" value="" placeholder="请输入验证码" name="validationCode" maxlength="4"/>
        <input type="button" id="verifCode_btn" value="获取验证码" onclick="getVerifCode(this)"/>
    </label>
</form>
<div class="tips">
    <p class="tip_msg">验证码已发送，留意短信提醒</p>
    <!--有四种错误提示-->
    <p class="tip_error" id="errorType1">您输入的验证码有误，请重新输入</p>
    <p class="tip_error" id="errorType3">当前手机号未绑定大象卡，请填写开卡的手机号</p>
    <p class="tip_error" id="errorType4">请输入正确的手机号</p>
    <p class="tip_error" id="errorType5">手机号错误或验证码超过有效期</p>
    <p class="tip_error" id="errorType6">未知错误</p>
</div>
<div class="btn" onclick="userLogin(this)">下一步</div>
<p class="bottomTips">每个手机号，只能绑定一张大象卡</p>


<script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    //"验证码已发送"字样显示，2s后隐藏
    function showMsgTips() {
        $("#errorType1").hide()
        $("#errorType2").hide()
        $("#errorType3").hide()
        $("#errorType4").hide()
        $("#errorType5").hide();
        $(".tip_msg").fadeIn();
        setTimeout(function () {
            $(".tip_msg").fadeOut();
        }, 2000);
    }

    //倒计时效果
    var countdown = 90;

    function countDown(obj) {

        //倒计时js方法，也可以换别的方法
        if (countdown == 0) {
            obj.removeAttribute("disabled");
            obj.value = "获取验证码";
            obj.style.color = "#1D98FA";
            countdown = 90;
            return;
        } else {
            obj.setAttribute("disabled", true);
            obj.style.color = "#999";
            obj.value = countdown + "s";
            countdown--;
        }
        setTimeout(function () {
            countDown(obj)
        }, 1000);
    }

    function userLogin(obj) {
        $("#errorType1").hide()
        $("#errorType2").hide()
        $("#errorType3").hide()
        $("#errorType4").hide()
        $("#errorType5").hide();
        $("#errorType6").hide();
        $.ajax({
            url: './userLogin',
            type: 'POST',
            dataType: "json",
            data: $('#form_sample_1').serializeArray(),
            success: function (returndata) {
                if (returndata.code == 1004) {
                    $("#errorType5").show();
                } else if (returndata.code == 1010) {
                    $("#errorType3").show();
                } else if (returndata.code == 1005) {
                    $("#errorType1").show();
                } else if (returndata.code == 1006) {
                    $("#errorType4").show();
                } else if (returndata == '') {
                    $("#errorType6").show();
                } else {
                    location.href = "./getCardFlowInfo";
                }
            },
            error: function (returndata) {

            }
        });

    }

    //"发送验证码"按钮所属事件
    function getVerifCode(obj) {
        $("#errorType1").hide()
        $("#errorType2").hide()
        $("#errorType3").hide()
        $("#errorType4").hide()
        $("#errorType5").hide();
        var sMobile = $("#telNum").val();
        if (!(/^1\d{10}$/.test(sMobile)) || $("#telNum").val().length != 11) {
            $("#errorType4").show();
            $("#telNum").focus();
            setTimeout(function () {
                $("#errorType4").fadeOut();
            }, 2000);
            return false;
        } else {
            showMsgTips();
            countDown(obj);
            var formData = '{"mobile":' + $("#telNum").val() + '}';
            $.ajax({
                url: '../anl/getValidationCode',
                type: 'POST',
                dataType: "json",
                contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {

                },
                error: function (returndata) {
                    alert(returndata.codemsg);
                }
            });
        }
    }
</script>
</body>
</html>
<!--
未完成：1、校验手机号
-->