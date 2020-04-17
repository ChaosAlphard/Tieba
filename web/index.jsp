<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/6/10
    Time: 12:34
    -->
    <title>Tieba</title>
    <link rel="stylesheet" href="plugins/fontawesome.css">
    <link rel="stylesheet" href="css/logAndReg.css">
    <link rel="stylesheet" href="css/indexStyle.css">
</head>
<body>
<div class="loading" style="opacity: 1; display: flex;">
    <span class="wel">Welcome</span>
    <span class="lod">--加载中--</span>
    <div class="cir"></div>
    <div class="support">--支持以下浏览器--</div>
    <div class="fa fa-chrome chr">Chrome 68+</div>
    <div class="fa fa-firefox ff">Firefox 61+</div>
</div>
<div class="topnav"><%@ include file="logAndReg.jsp"%></div>

<div class="base">
    <div class="leftblock">
        <div class="avatar"><img id="avatar" src="img/avatar/0.jpg"></div>
        <div class="search">
            <form action="SearchServlet" method="get" target="_blank"  rel="noopener">
                <input type="text" name="search" placeholder="搜索贴吧">
                <button type="submit" class="fa fa-search"></button>
            </form>
        </div>
        <div class="horizon">关注的吧</div>
        <div class="favbar"></div>
    </div>
    <div class="rightblock"></div>
</div>

<script src="js/indexMain.js"></script>
</body>
</html>
