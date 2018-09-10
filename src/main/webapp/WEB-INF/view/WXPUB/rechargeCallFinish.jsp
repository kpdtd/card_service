<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!--充值成功页面-->
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
	    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/recharge_finish.css"/>
	    <!--flexible.js适配屏幕-->
	    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
	   <!--fastclick.js解决移动端点透问题-->
	    <script src="<%=request.getContextPath() %>/js/public/fastclick.js" type="text/javascript" charset="utf-8"></script>
		<title>交费完成</title>
	</head>
	<body>
		<div class="top_tips">
			<img src="<%=request.getContextPath() %>/img/recharge_finish/finish_icon.png"/>
			<c:if test="${flag==1}">
				<span>支付成功</span>
			</c:if>
			<c:if test="${flag==0}">
				<img src="/img/recharge_finish/unfinish_icon.png"/>
				<span>支付失败</span>
			</c:if>
			<!--<img src="/img/recharge_finish/unfinish_icon.png"/>
			<span>支付失败</span>-->
		</div>
		<ul class="orderList">
			<li>
				<span class="grayTxt">充值号码:</span>
				<span>${phone}</span>
			</li>
			<li>
				<span  class="grayTxt">充值金额:</span>
				<span class="blueTxt">${userOrder.money/100}</span>元
			</li>
			<li>
				<span  class="grayTxt">充值时间:</span>
				<span>${time}</span>
			</li>
		</ul>
		<%--<a href="./getWXOpenId?state=toRechargeCall" class="btn_hollow">继续缴费</a>--%>
		<%--赵宇 20180725修改--%>
		<!--<a href="./getWXOpenId?state=toGetCoupon" class="btn_hollow">查看活动</a>
		<div class="divideLine"></div>-->
		<div class="bottom_tips">
			<h4>温馨提示</h4>
			<p>充值金额及时到账，若发现金额不对时，请联系公众号客服予以解决</p>
		</div>
	</body>
</html>
