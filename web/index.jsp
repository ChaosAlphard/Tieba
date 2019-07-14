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
    <link rel="stylesheet" href="./plugins/fontawesome.css">
    <link rel="stylesheet" href="./css/logAndReg.css">
    <link rel="stylesheet" href="./css/indexStyle.css">
</head>
<body>
<div class="loading" style="opacity: 1; display: flex;">
    <span class="wel">Welcome</span>
    <span class="lod">--Now Loading--</span>
    <div class="cir"></div>
    <div class="support">--浏览器支持--</div>
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

<div class="footer"><a href="AdminPage/login.jsp" target="_blank" rel="noopener">管理员登陆</a></div>

<%--<script src="js/customFunction.js"></script>--%>
<%--<script src="js/logAndReg.js"></script>--%>
<%--<script src="js/autoLogin.js"></script>--%>
<script src="js/indexMain.js"></script>
</body>
</html>

<%--
used icon
user 用户
lock 锁
check 对
谷歌火狐图标
发帖时间
paper-plane 纸飞机
history



<iframe style="width:330px; height:300px; position:fixed; left:0; bottom:0; margin:0; border:none; z-index:12;" src="http://music.163.com/outchain/player?type=0&id=2483570337&auto=0&height=430"></iframe>
--%>