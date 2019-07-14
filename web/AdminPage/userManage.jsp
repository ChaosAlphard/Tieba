<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/10/20
    Time: 15:57
    -->
    <title>用户管理</title>
    <link rel="stylesheet" href="../plugins/fontawesome.css">
    <link rel="stylesheet" href="css/userManageStyle.css">
    <script>
        const uid = "${sessionScope.auid}";
        const usr = "${sessionScope.ausr}";
        const adminLV = "${sessionScope.adminLV}";
        if(!((uid&&usr&&adminLV)&&adminLV==="3")){
            location.replace("login.jsp");
        }
    </script>
</head>
<body>
<div class="base">
    <div class="title">用户管理</div>
    <div class="content">
        <div class="lab">输入用户ID查询:</div>
        <div class="inp"><input type="text" class="userID"></div>
        <div><button type="button" class="sub">查询</button></div>
        <div class="lab">用户名:</div>
        <div class="inp"></div>
        <div class="avatar"></div>
        <div class="lab">账户状态:</div>
        <div class="inp"></div>
        <div class="btndiv">
            <button type="button" class="banned">封禁用户</button>
            <button type="button" class="normal">设为普通用户</button>
            <button type="button" class="barser">设为吧务</button>
        </div>
    </div>
</div>

<script src="../js/customFunction.js"></script>
<script src="js/userManageScript.js"></script>
</body>
</html>