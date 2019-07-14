<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/6/17
    Time: 15:42
    -->
    <title>个人信息</title>
    <link rel="stylesheet" href="./plugins/fontawesome.css">
    <link rel="stylesheet" href="./plugins/cropper.css">
    <link rel="stylesheet" href="./css/myInfoStyle.css">
</head>
<body>
<div class="info">
    <div class="adiv"><img class="avatar"><span class="fa fa-pencil aspan">修改头像</span></div>
    <div class="userid">UID</div>
    <div class="nickname">昵称</div>
    <div class="favtie">收藏的帖</div>
    <div class="ties"></div>
</div>
<div class="uploadcover" style="display: none;">
    <div class="upload">
        <span class="fa fa-close avatarclose"></span>
        <div id="pic"><img src="" id="image"></div>
        <div class="prediv">
            <%--<div class="preimg"><img src="" id="pre"></div>--%>
            <div class="preview p1"></div>
            <div class="preview p2"></div>
            <div class="preview p3"></div>
        </div>
        <div class="filediv">
            <input type="file" id="file" accept="image/*">
            <button type="button" id="sub">上传</button>
        </div>
        <div><span>不支持gif动图, 不支持透明背景</span></div>
    </div>
</div>

<script>
    const uid = "${sessionScope.uid}";
    const usr = "${sessionScope.usr}";
</script>
<script src="js/customFunction.js"></script>
<script src="./plugins/cropper.js"></script>
<script src="./js/myInfoMain.js"></script>
</body>
</html>