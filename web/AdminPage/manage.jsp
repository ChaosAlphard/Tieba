<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/10/11
    Time: 15:25
    -->
    <title>Manage</title>
    <link rel="stylesheet" href="../plugins/fontawesome.css">
    <link rel="stylesheet" href="css/manageStyle.css">
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
    <div class="title">管理页面</div>
    <div class="gridContent">
        <div class="g1"><a href="tieManage.jsp" target="_blank" rel="noopener" class="fa fa-trash-o">删除帖子</a></div>
        <div class="g2 admin"><a href="userManage.jsp" target="_blank" rel="noopener" class="fa fa-user-o">设置吧务</a></div>
        <div class="g3"><a href="tieManage.jsp" target="_blank" rel="noopener" class="fa fa-star">帖子加精</a></div>
        <div class="g4"><a href="tieManage.jsp" target="_blank" rel="noopener" class="fa fa-star-o">取消加精</a></div>
        <div class="g5 admin"><a href="userManage.jsp" target="_blank" rel="noopener" class="fa fa-user-o">封禁用户</a></div>
        <div class="g6 admin"><a href="barManage.jsp" target="_blank" rel="noopener" class="fa fa-sticky-note-o">创建贴吧</a></div>
        <%--<div class="g7">修改贴吧信息</div>--%>
        <%--<div class="g8">误删楼层恢复</div>--%>
    </div>
</div>

<a href="/AdminLogout" class="exit fa fa-sign-out"></a>

<script src="../js/readCookie.js"></script>
<script src="js/manageScript.js"></script>
</body>
</html>
