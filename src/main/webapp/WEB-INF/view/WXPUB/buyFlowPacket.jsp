<!--充值页面-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/recharge.css"/>
    <!--flexible.js适配屏幕-->
    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
    <!--fastclick.js解决移动端点透问题-->
    <script src="<%=request.getContextPath() %>/js/public/fastclick.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js" type="text/javascript" charset="utf-8"></script>
    <title>购买加油包</title>

</head>
<body>
<h4 class="top_title"
    style="text-align: center;padding-bottom: 0.4rem;position: fixed;top: 0;width: 100%;text-align: center;background: #fff;z-index: 99;">
    购买加油包</h4>
<div style="margin-bottom: 1.4rem;margin-top: 1.5rem;">
    <input type="hidden" id="userId" value="${userId}">
    <c:forEach var="WxlibL" items="${wxlibList}">
        <ul class="step1_list clear">
            <c:forEach var="Wxlib" items="${WxlibL}">
                <li class="step1_cell" value="${Wxlib.id}">
                    <p> ${Wxlib.name}/月 </p>
                    <input id="firstId${Wxlib.id}" value="${Wxlib.goodsDataList[0].id}" type="hidden"/>
                    <input id="firstMoney${Wxlib.id}" value="${Wxlib.goodsDataList[0].money}" type="hidden"/>
                    <input id="firstDisPrice${Wxlib.id}" value="${Wxlib.goodsDataList[0].disPrice}" type="hidden"/>
                    <input id="firstInfo${Wxlib.id}" value="${Wxlib.goodsDataList[0].info}" type="hidden"/>
                        <%--<input type="hidden" id="goodsId" name="goodsId" value="${Wxlib.id}"/>--%>
                </li>
            </c:forEach>
        </ul>
        <ul class="step2_list">
            <c:forEach var="Wxlib" items="${WxlibL}">
                <li style="display: none;"> <%--id="goodsId${Wxlib.id}"--%>
                    <ul class="clear" id="ulGoodsId${Wxlib.id}">
                        <c:forEach var="goodsData" items="${Wxlib.goodsDataList}" varStatus="gd">
                            <c:if test="${gd.index==0}">
                                <li class="step2_cell active2" id="active_${goodsData.id}" value="${goodsData.id}">
                            </c:if>
                            <c:if test="${gd.index!=0}">
                                <li class="step2_cell" id="active_${goodsData.id}" value="${goodsData.id}">
                            </c:if>
                            <input id="money${goodsData.id}" value="${goodsData.money}" type="hidden"/>
                            <input id="disPrice${goodsData.id}" value="${goodsData.disPrice}" type="hidden"/>
                            <input id="info${goodsData.id}" value="${goodsData.info}" type="hidden"/>

                            <p class="step2_txt1">购买${goodsData.info}个月</p>
                            <p class="step2_txt2">${goodsData.price}元/<span class="smallTxt">月</span></p>

                            <c:if test="${goodsData.discount != 0.0}">
                                <span class="corner">${goodsData.discount}折</span>
                            </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </c:forEach>
    <h4 class="bottomTips" id="prompt">请先选择您每月需要的额度</h4>
    <h4 class="bottomTips" id="warning"></h4>
</div>
<div class="paySection clear">
    <div class="payMoney">
        <h4>立即支付：<span class="blueTxt" id="money"></span></h4>
        <input type="hidden" id="moenyFen"/>
        <p>优惠金额：<span class="blueTxt" id="disPrice"  style="color: #D61A37;"></span></p>
    </div>
    <!--添加disabled="disabled"属性后任何事件失效-->
    <input type="button" id="goPay" value="立即支付" style="background: #999;" class="payBtn"/>
    <form method="post" action="./getNewRechargeFinishPage" id="getRechargeFinishPage">
        <input type="hidden" id="goodsId" name="goodsId"/>
        <input type="hidden" id="userAccountLogId" name="userAccountLogId"/>
        <input type="hidden" id="cardIdF" name="cardId"/>
    </form>
</div>
<script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        $("#errorType1").hide()

    })
    $('.step1_list').each(function () {
        $(this).children().each(function (i) {
            $(this).click(function (event) {
                $("#moenyFen").val("");
                // $("#disPrice").val("");
                $("#goodsId").val("");
                event.stopPropagation();
                $(".payBtn").css({"background": "#999"});
                $("#prompt").html("请选择您想购买的时长");
                var nextUL = $(this).parent().next(".step2_list");
                $('.step2_list').hide();
                nextUL.show();

                var curr = nextUL.children().eq(i);
                $('.step2_list').children().slideUp();
                curr.stop().slideToggle();

                $("li.step1_cell").removeClass("active");
                $("li.step2_cell").removeClass("active2");
                $(this).addClass('active');


                $("#money").empty();
                $("#disPrice").empty();
                $("#prompt").html("选择完毕，可以进行支付啦");
                // $("li.step2_cell").removeClass("active2");
                var id = $(this).val();
                var goodsId = $("#firstId" + id).val();
                var money = $("#firstMoney" + id).val();
                var disPrice = $("#firstDisPrice" + id).val();
                // alert(goodsId+","+money+"分"+disPrice);
                $("#goodsId").val(goodsId);
                $("#moenyFen").val(money);
                $("#money").append(money / 100 + "元");
                $("#disPrice").append(disPrice + "元");
                // alert($("#active2_" + goodsId).val());
                $("#active_" + goodsId).addClass("active2");
                $(".payBtn").css({"background": "#1D98FA"});
            });
        })
    });
    /**
     * 1、goodsId |必须 |String |商品ID 对应列表返回的数据ID
     * 2、giftId |可空 |String |赠送的商品ID 对应赠送信息得数据ID
     * 3、money |必须 |Integer |支付金额 单位：分，按产品列表
     * 4、payType |必须 ｜  Integer | 支付类型，花费支付默认为7
     */
    $("#goPay").on("click", function () {
        var money = $("#moenyFen").val();
        // var cardId = $("#cardId").val();
        var goodsId = $("#goodsId").val();
        var payType = 7
        if (money == '' || goodsId == '') {
            $("#errorType1").show();
            return;
        }
        var json = "{'goodsId':'" + goodsId + "','giftId':'-1','money':'" + money + "','payType':'" + payType + "','cardId':'"+$("#cardId").val()+"'}";
        $.ajax({
            url: '../api/createOrder',
            type: 'POST',
            dataType: "json",
            contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
            data: json,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (obj) {
                if(obj.code==0){
                    $("#userAccountLogId").val(obj.data.id);
                    $("#cardIdF").val($("#cardId").val());
                    var form = document.getElementById('getRechargeFinishPage');
                    form.submit();
                }else {
                    if(obj.code==5005){
                        alert(obj.codemsg);
                    }else {
                        // alert("余额不足");
                        $("#warning").attr("style","color: red");
                        $("#warning").html("微信系统异常");
                        hideTxt();
                    }
                }
            },
            error: function (returndata) {
                alert(returndata.codemsg);
            }
        });
    });


    $(".step2_cell").on("click", function (event) {
        $("#money").empty();
        $("#disPrice").empty();
        event.stopPropagation();
        $("#prompt").html("选择完毕，可以进行支付啦");
        $("li.step2_cell").removeClass("active2");
        $(this).addClass('active2');
        var goodsId = $(this).val();
        var money = $("#money" + goodsId).val();
        var disPrice = $("#disPrice" + goodsId).val();
        $("#goodsId").val(goodsId);
        $("#moenyFen").val(money);
        $("#money").append(money / 100 + "元");
        $("#disPrice").append(disPrice + "元");
        $(".payBtn").css({"background": "#1D98FA"});
    });

    $(document).click(function () {
        $(".payBtn").css({"background": "#999"});
        $("#money").empty();
        $("#disPrice").empty();
        $("#moenyFen").val("");
        $("#goodsId").val("");
        $("#prompt").html("请先选择您每月需要的额度");
        $('.step2_list').slideUp();
        $('.step2_list').children().slideUp();
        $("li.step1_cell").removeClass("active");
        $("li.step2_cell").removeClass("active2");
    });
    function hideTxt(){
        setTimeout(function(){
            $("#warning").fadeOut();

        },1000);
    }
</script>
</body>
</html>
