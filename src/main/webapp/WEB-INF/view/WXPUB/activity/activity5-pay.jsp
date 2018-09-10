<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--支付9元运费页面-->
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/activity/activity5/activity5-pay.css"/>
    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/pop-ups.css"/>
    <title>订单详情</title>
</head>
<body>
<div class="part1">
    <ul class="part1-1">
        <h2 class="payStatus">待支付</h2>
        <li>手机号：<span>${activityCardInfo.mobile}</span></li>
        <li>收货地址：<span>${activityCardInfo.address}</span></li>
    </ul>
    <ul class="part1-2">
        <li>商品信息：<span>大象日租卡</span></li>
        <%--<li>商品数量：<span>2张</span></li>--%>
        <li>支付方式：<span>微信支付</span></li>
    </ul>
</div>
<div class="part2">
    <ul class="part2-1">
        <li>商品金额：<span>￥0.00</span></li>
        <li>运费：<span>￥${activityCardInfo.price/100}</span></li>
    </ul>
    <div class="part2-2">
        <p>实付款：<span>￥${activityCardInfo.price/100}</span><p>
    </div>
</div>
<div class="payBtn">
    <p>总价：<span>￥${activityCardInfo.price/100}</span></p>
    <input type="hidden" id="moenyFen" value="${activityCardInfo.price}" />
    <input type="hidden" id="activityCardInfoId" value="${activityCardInfo.id}" />
    <input type="hidden" id="mobile" value="${activityCardInfo.mobile}" />
    <a id="goPay">立即支付</a>
</div>
<div id="pop_01" class="pop_01" data-animation="layer-fadeInUpBig"><p>支付已取消</p></div>
<script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    function showAlert(id){
        $("#"+id).addClass("showAlert");
        setTimeout(function(){
            $("#"+id).removeClass("showAlert");
        },3501);
    }


    $(function(){
        /**
         * 1、goodsId |必须 |String |对应的activityCardInfo的ID
         * 2、giftId |可空 |String |赠送的商品ID 对应赠送信息得数据ID
         * 3、money |必须 |Integer |支付金额 单位：分，按产品列表
         * 4、payType |必须 ｜  Integer | 支付类型 1、微信；2支付宝；3:微信扫码 ;5:公众号；7：话费
         */
        $("#goPay").on("click", function () {
            var money = $("#moenyFen").val();
            var activityCardInfoId = $("#activityCardInfoId").val();
            var json = "{'goodsId':'" + activityCardInfoId + "','giftId':'-1','money':'" + money + "','payType':'5'}";
            $.ajax({
                url: '../anl/wxPublicCreateOrder',
                type: 'POST',
                dataType: "json",
                contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
                data: json,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (obj) {
                    /**
                     *	{
							 *		"appId": "wx2421b1c4370ec43b",     //公众号名称，由商户传入
							 *		"timeStamp": "1395712654",         //时间戳，自1970年以来的秒数
							 *		"nonceStr": "e61463f8efa94090b1f366cccfbbb444", //随机串
							 *		"package": "prepay_id=u802345jgfjsdfgsdg888",
							 *		"signType": "MD5",         //微信签名方式：
							 *		"paySign": "70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名
							 *	}
                     * @param Obj
                     */
                    function onBridgeReady() {

                        WeixinJSBridge.invoke(
                            'getBrandWCPayRequest', {
                                "appId": obj.data.appId,     //公众号名称，由商户传入
                                "timeStamp": obj.data.timeStamp,         //时间戳，自1970年以来的秒数
                                "nonceStr": obj.data.nonceStr, //随机串
                                "package": obj.data.package,
                                "signType": "MD5",         //微信签名方式：
                                "paySign": obj.data.paySign//微信签名
                            },
                            function (res) {
                                // alert(res.err_msg);
                                if (res.err_msg == "get_brand_wcpay_request:ok") {
                                   /* $("#outTradeNo").val(obj.data.nonceStr);
                                    var form = document.getElementById('getRechargeCallFinishPage');
                                    form.submit();*/
                                    window.location.href='../../anl/toPayFinishPage?mobile='+$("#mobile").val();
                                }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                                else {
                                    var json = {'outTradeNo':obj.data.nonceStr}
                                    $.ajax({
                                        url: '../anl/toCancelOrder',
                                        type: 'POST',
                                        contentType: "application/json;charset=utf-8", //这个是发送信息至服务器时内容编码类型
                                        data: json,
                                        async: false,
                                        cache: false,
                                        contentType: false,
                                        processData: false,
                                        success: function (obj) {
                                            if(obj=='success'){
                                                showAlert('pop_01');
                                            }
                                        },
                                        error: function (returndata) {
                                            showAlert('pop_01');
                                        }
                                    });
                                }
                            }
                        );

                    }

                    if (typeof WeixinJSBridge == "undefined") {
                        if (document.addEventListener) {
                            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                        } else if (document.attachEvent) {
                            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                        }
                    } else {
                        onBridgeReady();
                    }
                },
                error: function (returndata) {
                    alert(returndata.codemsg);
                }
            });
        });
    })

</script>

</body>
</html>

