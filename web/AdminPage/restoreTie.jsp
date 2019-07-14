<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/10/19
    Time: 10:40
    -->
    <title>Title</title>
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

</body>
</html>