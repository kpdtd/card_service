<%--
  Created by IntelliJ IDEA.
  User: liangxuekai
  Date: 18/7/18
  Time: 下午6:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!--查询余额3.0-->
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/check_v3.0.css"/>
    <!--flexible.js适配屏幕-->
    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
    <!--fastclick.js解决移动端点透问题-->
    <script src="<%=request.getContextPath() %>/js/public/fastclick.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript"
            charset="utf-8"></script>
    <!--common.js 判断是不是在微信环境-->
    <script src="<%=request.getContextPath() %>/js/public/common.js" type="text/javascript" charset="utf-8"></script>
    <title>余额查询</title>
    <style type="text/css">
        .rzb_box_flow div:nth-child(2) h4{
            font-size: 18px;
            padding-bottom: 0.2rem;
        }
        .rzb_box_flow div:nth-child(3) h4{
            font-size: 14px;
            color: #666;
        }
    </style>
</head>
<body>
<div class="userInfo">
    <div class="clear">
        <p>注册手机号：<span>${user.phone}</span></p>
        <a href="./logOut" class="checkoutBtn">退出</a><!--微信环境-跳转到登录页,app环境-没有退出按钮-->
        <input type="hidden" id="userPhone" value="${user.phone}"/>
    </div>
</div>
<!--总话费余额-->
<div class="phone_overage">
    <p>总话费余额</p>
    <h4>${(userAccount.primaryAccount+userAccount.subAccount)/100}<span class="smallTxt">元</span></h4>
    <div class="bottom clear">
        <div class="bottomLeft">
            <p>预存余额</p>
            <h4>${userAccount.primaryAccount/100}<span class="smallTxt">元</span></h4>
        </div>
        <div class="bottomRight">
            <p>赠送余额</p>
            <h4>${userAccount.subAccount/100}<span class="smallTxt">元</span></h4>
        </div>
    </div>
</div>
<h3 class="updateTime">数据更新日期(${time})</h3>
<!--===流量使用情况===-->
<div class="rzb_wrap"  id="goFlowDetail">
    <h2>每日用量详情</h2>
    <!--流量使用情况 -->
    <div class="rzb_box clear">
        <div class="blueLine"></div>
        <div>
            <h4>${yesterdayFlowUsed/100}G</h4>
            <p>${countTime}</p>
        </div>
        <div>
            <h4>查询更多<img src="<%=request.getContextPath() %>/img/public/arrow_right.png" class="arrowRight"/></h4>
        </div>
    </div>
    <!--没有流量使用情况-->
    <div class="noRecharge" style="display: none;">
        <img src="<%=request.getContextPath() %>/img/public/noRecharge.png"/>
        <p>您还没有购买任何日租宝哦~</p>
    </div>
</div>
<!--===流量加油包===-->
<div class="rzb_wrap flow_wrap">
    <%-- <c:if test="${cardFlowInfo.data.pkgInfoList==null||fn:length(cardFlowInfo.data.pkgInfoList)==0}">
         <!--没有流量加油包情况-->
         <div class="noRecharge" &lt;%&ndash;style="display: none;"&ndash;%&gt;>
             <img src="<%=request.getContextPath() %>/img/public/noRecharge.png"/>
             <p>您还没有购买任何流量加油包哦~</p>
         </div>
     </c:if>--%>
    <c:if test="${cardFlowInfo.data.pkgInfoList!=null&&fn:length(cardFlowInfo.data.pkgInfoList)!=0}">
        <!--有流量加油包情况-->
        <h2>流量加油包</h2>
        <div>
            <c:forEach var="pakge" items="${cardFlowInfo.data.pkgInfoList}" varStatus="status">
                <c:if test="${pakge.pkgType==1}">
                    <div class="rzb_box clear">
                        <div class="redLine"></div>
                        <div>
                            <h4>${pakge.pkgName}</h4>
                            <p>有效日期(${pakge.pkgExpireTime})</p>
                        </div>
                        <div>
                            <h4>${pakge.restFlow}M</h4>
                            <p>当月流量余额</p>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
</div>
<form method="post" action="./getAppFlowGoods" id="getAppFlowGoods">
    <input type="hidden" name="userPhone" id="userPhoneForm"/>
</form>
<form method="post" action="./goFlowDetail" id="goFlowDetailForm">
    <input type="hidden" name="userPhone" id="userPhoneForFlowDetail"/>
</form>
<script>
    //isWX在common.js中定义
    //app环境-没有退出按钮
    if (!isWX) {
        $(".checkoutBtn").css({
            "display": "none"
        })
    }
    $("#goRecharge").click(function () {
        var val = $("#userPhone").val();
        $("#userPhoneForm").val(val);
        var form = document.getElementById('getAppFlowGoods');
        form.submit();
    })
    $("#goFlowDetail").click(function () {
        var val = $("#userPhone").val();
        $("#userPhoneForFlowDetail").val(val);
        var form = document.getElementById('goFlowDetailForm');
        form.submit();
    })

    $(".goCharge").click(function () {
        var val = $("#userPhone").val();
        $("#userPhoneForm").val(val);
        var form = document.getElementById('getAppFlowGoods');
        form.submit();
    })


</script>
</body>
</html>

