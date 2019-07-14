<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/8/15
    Time: 21:22
    -->
    <title>注销登录</title>
    <style>
        body{
            margin: 0;
            background: #9370DD url("../img/bg.jpg") fixed top center / cover;
        }
        .info{
            position: absolute;
            top: calc(50vh - 200px);
            left: calc(50vw - 600px);
            width: 1200px;
            height: 400px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background: url("../img/bg-blur.jpg") fixed top center / cover;
            font-size: 42px;
            color: #FFFFFF;
            border-radius: 120px;
            box-shadow: 0 0 60px 20px #9370DD inset;
        }
    </style>
</head>
<body>
    <div class="info">你已成功注销登录</div>
</body>
</html>