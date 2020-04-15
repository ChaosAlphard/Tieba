<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/10/11
    Time: 12:51
    -->
    <title>AdminLogin</title>
    <link rel="stylesheet" href="../plugins/fontawesome.css">
    <link rel="stylesheet" href="css/loginStyle.css">
    <script src="../js/readCookie.js"></script>
</head>
<body>
<div class="base">
    <div class="title">Administrator Login</div>

    <div class="fa fa-user aocInfo">账户</div>
    <div class="aocData"><input type="text" class="aoc"></div>
    <div class="fa fa-lock aocInfo">密码</div>
    <div class="aocData"><input type="password" class="pwd"></div>

    <div class="submit"><button class="fa fa-check" type="button">登录</button></div>
    <div class="info"></div>
</div>

<script src="js/loginScript.js"></script>
</body>
</html>