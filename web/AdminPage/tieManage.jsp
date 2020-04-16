<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/10/16
    Time: 17:53
    -->
    <title>帖子管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../plugins/fontawesome.css">
    <link rel="stylesheet" href="css/tieManageStyle.css">
    <script>
        const uid = "${sessionScope.auid}";
        const usr = "${sessionScope.ausr}";
        const adminLV = "${sessionScope.adminLV}";
        if(!((uid&&usr&&adminLV)&&(adminLV==="2"||adminLV==="3"))){
            location.replace("login.jsp");
        }
    </script>
</head>
<body>
<div class="base">
    <div class="title">删除帖子</div>
    <div class="content">
        <div class="lab">输入帖子ID来查询:</div>
        <div class="inp"><input type="text" class="tieID"></div>
        <div><button type="button" class="sub">查询</button></div>
        <div class="lab">帖子标题:</div>
        <div class="inp"></div>
        <div class="info"></div>
        <div class="lab">发帖用户:</div>
        <div class="inp"></div>
        <div class="lab">发帖时间:</div>
        <div class="inp"></div>
        <div class="deldiv">
            <button type="button" class="del">删除帖子</button>
            <button type="button" class="setEli">设为精品</button>
            <button type="button" class="unsetEli">取消精品</button>
        </div>
    </div>
    <div class="detailed"></div>
</div>
</body>

<script src="../js/readCookie.js"></script>
<script src="js/tieManageScript.js"></script>
</html>