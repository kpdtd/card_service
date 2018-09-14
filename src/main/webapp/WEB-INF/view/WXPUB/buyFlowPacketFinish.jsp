<%--
  Created by IntelliJ IDEA.
  User: liangxuekai
  Date: 18/3/13
  Time: 下午5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
    <title>充值结果</title>
</head>
<body>
<div class="top_tips">
    <img src="<%=request.getContextPath() %>/img/recharge_finish/finish_icon.png"/>
    <span>支付成功</span>
    <!--<img src="<%=request.getContextPath() %>/img/recharge_finish/unfinish_icon.png"/>
    <span>支付失败</span>-->
</div>
<ul class="orderList">
    <li>
        <span class="grayTxt">充值号码:</span>
        <span>${phone}</span>
    </li>
    <li>
        <span  class="grayTxt">充值金额:</span>
        <span class="blueTxt">${goodsLibrary.price/100}</span>元
    </li>
    <li>
        <span  class="grayTxt">购买套餐:</span>
        <span class="blueTxt">${goodsLibrary.goodsName}</span>
    </li>
    <li>
        <span  class="grayTxt">购买时长:</span>
        <c:if test="${goodsLibrary.type==1}">
            <span class="blueTxt">12个月</span>
        </c:if>
        <c:if test="${goodsLibrary.type==2}">
            <span class="blueTxt">3个月</span>
        </c:if>
        <c:if test="${goodsLibrary.type==3}">
            <span class="blueTxt">1个月</span>
        </c:if>
        <c:if test="${goodsLibrary.type==5}">
            <span class="blueTxt">6个月</span>
        </c:if>
    </li>
    <li>
        <span  class="grayTxt">充值时间:</span>
        <span>${time}</span>
    </li>
</ul>
<a  id="finishBtu" class="btn_hollow" href="./getWXOpenId?state=toCheck" >完成</a>
<div class="divideLine"></div>
<div class="bottom_tips">
    <h4>温馨提示</h4>
    <p>充值金额将于24小时内到账，如充值失败，可以给公众号留言进行反馈</p>
</div>
<script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">

    $("#finishBtu").on("click", function (event) {
        android.close();
    });
</script>
</body>
</html>
