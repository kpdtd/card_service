<%--
  Created by IntelliJ IDEA.
  User: liangxuekai
  Date: 18/8/8
  Time: 下午2:59
  To change this template use File | Settings | File Templates.
--%>
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
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, minimal-ui">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/public/reset.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath() %>/css/activity/activity5/activity5-pay.css"/>
    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
    <title>订单详情</title>
</head>
<body>
<div class="payFinish">
    <img src="<%=request.getContextPath() %>/img/public/pay_finish.png" alt=""/>
    <p>支付成功</p>

    <h6>您可以在公众号“服务”-“物流查询”</h6>
    <a id="goInquire">查询物流信息</a>
</div>
<form action="./checkExpressResultPage" method="post" id="checkExpressResultPage">
    <input type="hidden" id="mobile" name="mobile" value="${mobile}"/>
</form>
<script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        $("#goInquire").on("click", function () {
            var form = document.getElementById('checkExpressResultPage');
            form.submit();
        });
    });
</script>
</body>
</html>

