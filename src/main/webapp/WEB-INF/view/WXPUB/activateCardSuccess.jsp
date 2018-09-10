<%--
  Created by IntelliJ IDEA.
  User: liangxuekai
  Date: 18/3/12
  Time: 上午11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--激活成功页面-->
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/activateCardSuccess.css"/>
    <!--flexible.js适配屏幕-->
    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
    <!--fastclick.js解决移动端点透问题-->
    <script src="<%=request.getContextPath() %>/js/public/fastclick.js" type="text/javascript" charset="utf-8"></script>
    <title>激活成功</title>
</head>
<body class="max-body">
<div class="success_icon">
    <img src="<%=request.getContextPath() %>/img/activateCardSuccess/success.png"/>
    <p>激活成功</p>
</div>
<div class="tips">
    <p class="tips">感谢使用大象卡，您现在可以将卡片插入手机中，充值后就可以享用大象卡高速便宜的流量啦</p>
    <p class="tips">为了方便使用，可以下载大象卡助手，查询流量充值等，如有问题，请直接在公众号给客服留言</p>
</div>
<%--<div class="btn" id="getWxFlowGoods">首次充值50元以上送50元</div>--%>
<div class="btn" id="getWxFlowGoods">去充值</div>
<!--底部预留区域-->
<div class="bottomSection">
    <input type="hidden" id="userId" value="${userId}"/>
</div>
<script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">


    $("#getWxFlowGoods").on("click", function () {
        // location.href = "./getWxFlowGoods?cardId=" + $("#cardId").val();
        location.href = "./getWXOpenId?state=toRechargeCall";
    });
</script>
</body>
</html>


