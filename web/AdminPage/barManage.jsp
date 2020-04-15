<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/10/23
    Time: 9:47
    -->
    <title>创建贴吧</title>
    <link rel="stylesheet" href="../plugins/fontawesome.css">
    <link rel="stylesheet" href="css/barManageStyle.css">
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
    <div class="title">创建贴吧</div>
    <div class="content">
        <div class="lab">贴吧名称</div>
        <div class="inp"><input type="text" class="barName">吧</div>
        <div class="info">长度不超过12个字符, 不能含有特殊字符</div>
        <div class="lab">贴吧简介</div>
        <div class="inp"><div class="editable" contenteditable="true"></div></div>
        <div class="info">长度不超过280个字符</div>
        <div class="cre"><button type="button">创建贴吧</button></div>
    </div>
</div>

<script src="../js/readCookie.js"></script>
<script src="js/barManageScript.js"></script>
</body>
</html>