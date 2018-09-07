
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!--流量使用详情-->
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/flowDetail.css"/>
    <!--flexible.js适配屏幕-->
    <script src="<%=request.getContextPath() %>/js/public/flexible.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/js/public/jquery/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
    <title>使用量查询</title>
</head>
<body class="max-body">
<h2 class="topTitle">最近7天流量使用情况</h2>
<ul>
   <%-- <li class="listBox">
        <div class="blueLine"></div>
        <p class="times">2018-08-06</p>
        <p class="num">1.23G</p>
    </li>
    <li class="listBox">
        <div class="blueLine"></div>
        <p class="times">2018-08-05</p>
        <p class="num">3.25G</p>
    </li>--%>

    <c:forEach var="userFlowUsedDay" items="${usedDayList}" varStatus="status">
            <li class="listBox">
                <div class="blueLine"></div>
                <p class="times">${userFlowUsedDay.recordTimeStr}</p>
                    <%--页面要求保留两位小数，此处直接除以10，忽略最后一位，页面上直接除以100显示就可以了--%>
                <c:if test="${userFlowUsedDay.flowUnit=='G'}">
                    <p class="num">${userFlowUsedDay.flowStr}${userFlowUsedDay.flowUnit}</p>
                </c:if>
                <c:if test="${userFlowUsedDay.flowUnit=='M'}">
                    <p class="num">${userFlowUsedDay.flow}${userFlowUsedDay.flowUnit}</p>
                </c:if>
            </li>
    </c:forEach>
</ul>
</body>
</html>

