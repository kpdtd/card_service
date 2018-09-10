<!--充值页面-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!--充值话费页面-->
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
	    <!--reset.css重置样式文件-->
	    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/public/reset.css"/>
	    <!--所有html里公共样式，如header、footer-->
	    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/public/common.css"/>
	    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/rechargeCall.css"/>
	    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/pop-ups.css"/>
	    <!--flexible.js适配屏幕-->
	    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
	    <!--fastclick.js解决移动端点透问题-->
	    <script src="<%=request.getContextPath() %>/js/public/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=request.getContextPath() %>/js/public/common.js" type="text/javascript" charset="utf-8"></script>
		<title>话费充值</title>
	</head>
	<body>
		<ul class="callRechargeList clear">
			<!--默认充50的选项选中-->
			<c:forEach var="charge" items="${chargeLists}" varStatus="cl">
				<c:if test="${charge.money==defaultCharge.money}">
					<li class="callRechargeItem active" value="${charge.id}">${charge.money/100}元</li>
					<input type="hidden" id="chargeid${charge.id}" value="${charge.money}">
				</c:if>
				<c:if test="${charge.money!=defaultCharge.money}">
					<li class="callRechargeItem" value="${charge.id}">${charge.money/100}元</li>
					<input type="hidden" id="chargeid${charge.id}" value="${charge.money}">
				</c:if>
			</c:forEach>
		</ul>		<div class="desc">
			<h3>说明：首次充值50元以上，多送50元话费</h3>
			<p>赠送金额24小时内到账(激活7日内充值有效),其他问题请咨询公众号客服</p>
		</div>
		<div class="paySection clear">
			<div class="payMoney">
				<h4>立即支付：
					<c:if test="${defaultCharge!=null}">
						<span class="blueTxt" id="showMoney">${defaultCharge.money/100}元</span>
					</c:if>
				</h4>
				<input  type="hidden" id="moenyFen" value="${defaultCharge.money}" />
				<input  type="hidden" id="chargeListId" value="${defaultCharge.id}" />
				<input  type="hidden" id="userId" value="${userId}" />
			</div>
			<!--添加disabled="disabled"属性后任何事件失效-->
			<input type="button" value="立即支付" class="payBtn" id="goPay"/>
			<form method="post" action="./getRechargeCallFinishPage" id="getRechargeCallFinishPage">
				<input type="hidden" name="outTradeNo" value="" id="outTradeNo"/>
			</form>
		</div>
		<!--领取成功弹框-->
		<%--<div id="sub_success" class="sub_success_pop">
			<h4>微信支付网络异常</h4>
			<div class="pop_footer clear">
				<a onclick="hidePop('sub_success')">确认</a>
			</div>
		</div>
		<div class="overLay"></div>--%>

		<div id="pop_01" class="pop_01" data-animation="layer-fadeInUpBig"><p>微信网络连接异常</p></div>

		<script type="text/javascript">
			/*//弹框消失
            function hidePop(id){
                $('#'+id).hide();
                $('.overLay').hide();
            };
            //弹框出现
            function showPop(id){
                $('#'+id).show();
                $('.overLay').show();
            };*/

            function showAlert(id){
                $("#"+id).addClass("showAlert");
                setTimeout(function(){
                    $("#"+id).removeClass("showAlert");
                },3501);
            }

			$(function(){
				$(".callRechargeItem").on("click",function(){
					$("li.callRechargeItem").removeClass("active");
					$(this).addClass("active");
					var id = $(this).val();
					var money = $("#chargeid"+id).val();
					$("#showMoney").empty();
                    $("#showMoney").append(money / 100 + "元");
                    $("#moenyFen").val(money);
                    $("#chargeListId").val(id);
				});



                /**
                 * 1、goodsId |必须 |String |充值chargeList的ID
                 * 2、giftId |可空 |String |赠送的商品ID 对应赠送信息得数据ID
                 * 3、money |必须 |Integer |支付金额 单位：分，按产品列表
                 * 4、payType |必须 ｜  Integer | 支付类型 1、微信；2支付宝；3:微信扫码 ;5:公众号；7：话费
                 */
                $("#goPay").on("click", function () {
                    var money = $("#moenyFen").val();
                    var userId = $("#userId").val();
                    var chargeListId = $("#chargeListId").val();
                    if (money == '' || userId == '' || chargeListId == '') {
                        $("#errorType1").show();
                        return;
                    }
                    var json = "{'goodsId':'" + chargeListId + "','giftId':'-1','money':'" + money + "','userId':'" + userId + "'}";
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
                            if(obj.code==1){
                                // showPop("sub_success");
                                showAlert('pop_01');
                                // alert("微信支付网络异常")
							}
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
                                            $("#outTradeNo").val(obj.data.nonceStr);
                                            var form = document.getElementById('getRechargeCallFinishPage');
                                            form.submit();
                                        }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                                        else {
                                            // alert("支付失败")
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
