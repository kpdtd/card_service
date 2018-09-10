
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--激活卡片--输入ICCID界面-->
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/activateCardStep.css"/>
    <!--flexible.js适配屏幕-->
    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
    <!--fastclick.js解决移动端点透问题-->
    <script src="<%=request.getContextPath() %>/js/public/fastclick.js" type="text/javascript" charset="utf-8"></script>
    <title>开卡激活</title>
</head>
<body class="max-body">
<div id="step1">
    <h4 class="top_title">输入卡片信息</h4>
    <label for="iccid_ipt" class="iccid_lable clear">
        <span>输入卡号</span>
        <form id="form_sample_1" action="#" class="form-horizontal dialog" method="post"
              onsubmit="return false;">
            <input type="text" id="iccid_ipt" class="ipt_style" placeholder="输入卡背面20位号码" value="" name="iccid"/>
            <input type="hidden" name="cardId" id="cardId" value=""/>
            <input type="hidden" name="openid" id="openid" nam value="${openid}"/>
        </form>
    </label>
    <p class="error_tips" id="error_tips_1">输入号码有误，请重新输入</p>
    <p class="error_tips" id="error_tips_2">服务器错误，请刷新页面后重试</p>
    <p class="error_tips" id="error_tips_3">输入号码不能为空，请重新输入</p>
    <p class="error_tips" id="error_tips_4">此卡已经激活，请直接登录</p>

    <!--btn的背景图片地址在common.css中-->
    <div class="btn" onclick="check()">下一步&nbsp;&nbsp;身份验证</div>
    <input id="error" value="${error}" type="hidden"/>
    <div class="bottom_sampleImg">
        <p>您收到的大象卡</p>
        <img src="<%=request.getContextPath() %>/img/activateCardStep/sampleImg.png"/>
    </div>
</div>
<!--step2~确认卡号后5位-->
<div id="step2" class="step2_pop">
    <h4>确认卡号后五位数是否正确</h4>
    <div class="num clear">
        <!--这五个span标签必须在同一行，否则影响样式-->
        <span class="num_cell" id="numCell1"></span><span class="num_cell" id="numCell2"></span><span class="num_cell"
                                                                                                      id="numCell3"></span><span
            class="num_cell" id="numCell4"></span><span class="num_cell" id="numCell5"></span>
    </div>
    <div class="step2_pop_footer clear">
        <a onclick="step2_error()">重新输入</a><a id="submit_btn">是的正确</a>
    </div>
</div>
<div class="overLay"></div>
<script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">

    //点击"下一步 身份验证"时需要的方法,我现在写的方法仅作为静态页面调试时使用
    function check() {
        $("#error_tips_1").hide()
        $("#error_tips_2").hide()
        $("#error_tips_3").hide()
        $("#error_tips_4").hide()

        var formData = 'iccid=' + $("#iccid_ipt").val();
        $.ajax({
            url: '../anl/verifIccid',
            type: 'POST',
            data: formData,
            dataType: "json",
            success: function (returndata) {
                if (returndata == 1) {
                    $("#error_tips_1").show();
                    return;
                } else if (returndata == 2) {
                    $("#error_tips_2").show();
                    return;
                } else if (returndata == 3) {
                    $("#error_tips_3").show();
                    return;
                } else if (returndata == 4) {
                    $("#error_tips_4").show();
                    return;
                } else {
                    $("#cardId").val(returndata.data);

                    var isRight = true;
                    //iccid输入框中数值
                    var iccid_value = $("#iccid_ipt").val();
                    //iccid输入框中数值后五位
                    var iccid_lastNum = iccid_value.substr(-5, 5);
                    if (isRight) {
                        //如果第一次校验正确 显示overLay和step2_pop;否则显示error_tips
                        $(".overLay").show();
                        $(".step2_pop").show();
                    } else {
                        $(".error_tips").show();
                    }

                    //iccid输入框中数值后五位中的第一位..第二位..依次填入step2_pop中灰色方框内
                    var iccid_lastNum1 = iccid_lastNum.substr(0, 1);
                    var iccid_lastNum2 = iccid_lastNum.substr(1, 1);
                    var iccid_lastNum3 = iccid_lastNum.substr(2, 1);
                    var iccid_lastNum4 = iccid_lastNum.substr(3, 1);
                    var iccid_lastNum5 = iccid_lastNum.substr(4, 1);
                    $("#numCell1").html(iccid_lastNum1);
                    $("#numCell2").html(iccid_lastNum2);
                    $("#numCell3").html(iccid_lastNum3);
                    $("#numCell4").html(iccid_lastNum4);
                    $("#numCell5").html(iccid_lastNum5);
                }
            },
            error: function (returndata) {
            },


        });

    }

    //二次校验时"重新输入"按钮方法，关闭overLay和step2_pop
    function step2_error() {
        $(".overLay").hide();
        $(".step2_pop").hide();
    }

    $("#submit_btn").click(function () {
        $("#form_sample_1").attr("action", "./getCardByIccid");
        document.getElementById("form_sample_1").submit();
    });

    window.onload = function () {
        var e = $("#error").val();
        if (e == 1) {
            $("#error_tips_1").show();
        } else if (e == 2) {
            $("#error_tips_2").show();
        } else if (e == 3) {
            $("#error_tips_3").show();
        } else if (e == 4) {
            $("#error_tips_4").show();
        } else {

        }
    }
</script>
</body>
</html>

