<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, minimal-ui">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/public/reset.css"/>
    <!--zcity.css三级联动-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/activity/activity5/zcity.css">
    <!--activity5.css自己写的-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/activity/activity5/activity5.css"/>
    <!--flexible.js适配屏幕-->
    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
    <title></title>
    <style type="text/css">
        body{padding-bottom: 0.9rem;}
        .formBox{
            background-color: #F9F9F9;
            padding: 0 0;
            padding-top: 40px;
        }
        .formBox form table{
            display: inline-block;
            padding: 0 0.24rem;
        }
        .td_left{
            text-align: right;
            font-size: 14px;
            padding-right: 2%;
            width: 17%;
        }
        .bottomBtn{
            display: block;
            position: fixed;
            bottom: 0;
            width: 100%;
            height:0.9rem;
            line-height: 0.9rem;
            border-top: 1px solid #fff;
            background-color: #eee;
        }
        .bottomBtn > input[type=submit]{
            float: right;
            width: 70%;
            height:0.9rem;
            font-size:18px;
            color: #fff;
            background-color: #F36900;
        }

        .bottomBtn > span{
            float: left;
            width: 30%;
            background-color: #eee;
        }
        .bottomBtn > span img{
            width: 0.55rem;
            padding-top: 0.2rem;
        }

        @media ( min-width :768px){
            /*768 992 1200*/
            .bottomBtn{
                max-width: 768px;
            }
        }
        input[type="text"], input[type="tel"]{
            background: #fff;
        }
        input[name="payType"]{
            background: #EDEDED;
        }
        .formBox textarea{
            background: #fff;
        }
        .zcityGroup .zcityItem .zcityItem-head{
            border:1px solid #ccc;
        }
    </style>

</head>
<body>
<!--banner头图-->
<img src="<%=request.getContextPath() %>/img/activity/activity5/branch6/banner_branch6_2.png" class="bannerImg"/>
<!--====#page1 表单====-->
<div id="page1">
    <div class="formBox">
        <form>
            <table>
                <tr>
                    <td class="td_left"><span class="redTxt">*</span>姓名<p style="padding-top: 6px;"></p></td>
                    <td class="td_right">
                        <input type="text" placeholder="姓名" name="username"  id="username"/>
                        <p class="tiperror"></p>
                    </td>
                </tr>
                <tr>
                    <td class="td_left"><span class="redTxt">*</span>手机<p style="padding-top: 6px;"></p></td>
                    <td class="td_right">
                        <input type="tel" placeholder="手机" name="telnum" id="telnum" />
                        <p class="tiperror"></p>
                    </td>
                </tr>
                <tr>
                    <td class="td_left"><span class="redTxt">*</span>地区<p style="padding-top: 6px;"></p></td>
                    <td class="td_right">
                        <div class="zcityGroup" city-range="{'level_start':1,'level_end':3}"></div>
                        <p class="tiperror tiperror_address1"></p>
                    </td>
                </tr>
                <tr>
                    <td class="td_left"><span class="redTxt">*</span>地址<p style="padding-top: 6px;"></p></td>
                    <td class="td_right">
                        <input type="text" placeholder="街道，楼牌号" name="address2"  id="address2"/>
                        <p class="tiperror"></p>
                    </td>
                </tr>
                <tr>
                    <td class="td_left"><span class="redTxt">*</span>快递费</td>
                    <td class="td_right">
                        <input type="text" name="payType" value="9元（在线支付）" readonly/>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">留言</td>
                    <td class="td_right"><textarea  id="liuyan"></textarea></td>
                </tr>
            </table>
            <!--底部固定按钮-->
            <a class="bottomBtn clear" href="#page1">
                <span><img src="<%=request.getContextPath() %>/img/activity/activity5/branch6/chartIcon.png"/></span><input type="submit" value="免费领取大象卡" name="submit" id="submitBtn2" />
                <input type="hidden" id="randomNum" value="${ranNum}" />
                <input type="hidden" id="activityFlag" value="${activityFlag}" />
                <input type="hidden" id="urlSuffix" value="${urlSuffix}" />
            </a>
        </form>
        <!--领取成功弹框-->
        <div id="sub_success" class="sub_success_pop">
            <h4>领取成功</h4>
            <div class="pop_footer clear">
                <a onclick="hidePop('sub_success')">确认</a>
            </div>
        </div>
    </div>
</div>
<!--====介绍大象卡的图====-->
<img src="<%=request.getContextPath() %>/img/activity/activity5/branch6/pic-2.jpg"/>
<script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<!--zcity.js选择城市三级联动-->
<script src="<%=request.getContextPath() %>/js/activity5/zcity.js" type="text/javascript" charset="utf-8"></script>
<!--jquery.validate.js验证表单-->
<script src="<%=request.getContextPath() %>/js/activity5/jquery.validate.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

    function stopScroll(){
        $(".overLay").on("touchmove",function(e) {
            e.preventDefault();
        });
    }
    function showPop(id){
        $('#'+id).show();
        $('.overLay').show();
        stopScroll();
    }

    function hidePop(id){
        $('#'+id).hide();
        $('.overLay').hide();
    }
    $(document).ready(function(){
        //添加判断是否ios
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if(isiOS){
            $('input[name="buyNum"]+label').css({
                "margin-left":"-13px"
            });
            $('input[name="payType"]+label').css({
                "margin-left":"-13px"
            });

            var windowWidth = $(window).width();
            if(windowWidth <= 320){
                $("input[name='buyNum']+label:nth-of-type(2)").css({
                    "margin-left": "0"
                });
            }
        }

        //====功能4：调用城市三级联动====
        zcityrun(".zcityGroup");

        //=====功能5：验证表单====
        //jQuery.validator拓展校验手机号是否正确
        jQuery.validator.addMethod("isPhone", function(value, element) {
            var length = value.length;
            var mobile = /^1\d{10}$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请填写正确的手机号码");

        $("form").validate({
            rules: {
                username: {
                    required: true
                },
                telnum: {
                    required: true,
                    isPhone:true
                },
                address2: {
                    required: true
                }
            },
            messages: {
                username: {
                    required:"请填写您的姓名",
                },
                telnum: {
                    required: "请输入您的联系方式",
                    isPhone:"请填写正确的手机号码"
                },
                address2: {
                    required:"请填写您的详细地址",
                }
            },
            errorElement: "span",
            errorClass: "help-block",

            errorPlacement: function(error, element) {
                error.appendTo(element.siblings('.tiperror'));
            },
            submitHandler:function(form) {
                //验证三级联动(非表单元素)是否为空
                var address1 = $("input[name='address1']");
                var address = "";
                for (var i = 0; i < address1.length; i++) {
                    if(address1.eq(i).val() == "" || address1.eq(i).val() == undefined || address1.eq(i).val() == null){
                        $(".tiperror_address1").html("请选择您所在的省市县");
                        return;
                    }else {
                        address = address+address1.eq(i).val();
                    }
                }
                //表单校验成功后操作
                //跳转到支付9元运费页面
                //alert("ok");
                //显示“订单提交成功”弹框，并重置表单
                //表单提交在这里 具体方法参考jquery.validate.js
                // var money = $("#money").val();
                var money = 900;
                var username = $("#username").val();
                var telnum = $("#telnum").val();
                var address2 = address+$("#address2").val();
                var liuyan = $("#liuyan").val();
                var buyNum = 0;
                var randomNum = $("#randomNum").val();
                var activityFlag = $("#activityFlag").val();
                var urlSuffix = $("#urlSuffix").val();
                var flag = 1;
                var formData = "{'money':'"+money+"','username':'"+username+"','address2':'"+address2+"','mobile':'"+telnum+"','buyNum':'"+buyNum+"','liuyan':'"+liuyan+"','randomNum':'"+randomNum+"','activityFlag':'"+activityFlag+"','urlSuffix':'"+urlSuffix+"','flag':'"+flag+"'}";
                $.ajax({
                    url: '../anl/createActivetyCardInfo',
                    type: 'POST',
                    dataType: "json",
                    contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
                    data: formData,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (returndata) {
                        if(returndata.code==0){
                            // countLog.init();
                            //显示“订单提交成功”弹框
                            // showPop("sub_success");
                            window.location.href='../anl/toPayPage?activityCardId='+returndata.data.id;
                        }else{
                            alert("提交失败，请重新再试")
                        }
                    },
                    error: function (returndata) {
                    }
                });
                //form.submit();
                setTimeout(function(){
                    form.reset();
                },1000)
            }
        });
    })

</script>
</body>
</html>

