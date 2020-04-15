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
<div class="topnav">
    <span class="login">登录</span>
    <span class="register">注册</span>
    <a href="myInfo.jsp" target="_blank" class="welcome" style="display: none" rel="noopener"></a>
    <a href="LogoutServlet" class="logout" style="display: none">注销</a>
    <div class="logandreg" style="display: none">
        <iframe name="hiddenFrame" src="" style="display:none; position:absolute"></iframe>
        <form action="LoginConfirm" target="hiddenFrame" id="for" method="post" class="logdiv" style="display: none">
            <div class="fa fa-close closelog"></div>
            <div class="logtitle">登录</div>
            <div class="logform">
                <div class="usrdiv">
                    <span class="fa fa-user"></span>
                    <input type="text" name="aot" class="logusr">
                </div>
                <div class="pwddiv">
                    <span class="fa fa-lock"></span>
                    <input type="password" name="pwd" class="logpwd">
                </div>
                <div class="logsubdiv">
                    <button type="button" class="fa fa-check logsub">登录</button>
                </div>
                <div class="logmpt">7天内自动登录</div>
                <div>
                    <input id="fuid" type="text" name="uid" style="visibility: hidden">
                    <input id="fusr" type="text" name="usr" style="visibility: hidden">
                </div>
            </div>
        </form>
        <div class="regdiv" style="display: none">
            <div class="fa fa-close closereg"></div>
            <div class="regtitle">注册</div>
            <div class="regform">
                <div class="account">
                    <span>账户:</span><input type="text" class="ipts"><label class="tips">4-18字符,字母+数字组成,字母开头,不能含有其他字符</label>
                </div>
                <div class="nickname">
                    <span>昵称:</span><input type="text" class="ipts"><label class="tips">4-12字符,不能含有特殊符号</label>
                </div>
                <div class="password">
                    <span>密码:</span><input type="password" class="ipts"><label class="tips">4-16字符,字母或数字,可以使用!@#$%,.^符号</label>
                </div>
                <div class="checkpwd">
                    <span>确认密码:</span><input type="password" class="ipts"><label class="tips">确认密码</label>
                </div>
                <div class="captdiv">
                    <div class="captchacover"><canvas class="captcha"></canvas></div>
                    <input type="text" class="ipts"><label class="tips">输入验证码</label>
                </div>
                <div class="regsubdiv">
                    <button type="button" class="fa fa-check regsub">注册</button>
                </div>
                <div class="regmpt"></div>
            </div>
        </div>
    </div>
    <script src="js/readCookie.js"></script>
    <script>
      const uid = "${sessionScope.uid}";
      const usr = "${sessionScope.usr}";
    </script>
    <%--async or defer--%>
    <%--<script async="async"></script>--%>
    <script src="js/logAndReg.js" async="async"></script>
</div>

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

<%--<script src="js/readCookie.js"></script>--%>
<%--<script src="js/logAndReg.js"></script>--%>
<%--<script src="js/autoLogin.js"></script>--%>
<script src="js/indexMain.js"></script>

<%--<iframe style="width:330px; height:300px; position:fixed; left:0; bottom:0; margin:0; border:none; z-index:12;" src="http://music.163.com/outchain/player?type=0&id=2483570337&auto=0&height=430"></iframe>--%>
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
--%>