<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <span class="captcha"></span>
                <div class="captchacover"></div>
                <input type="text" class="ipts"><label class="tips">输入验证码</label>
            </div>
            <div class="regsubdiv">
                <button type="button" class="fa fa-check regsub">注册</button>
            </div>
            <div class="regmpt"></div>
        </div>
    </div>
</div>
<script src="js/customFunction.js"></script>
<script src="js/autoLogin.js"></script>
<script>
    const uid = "${sessionScope.uid}";
    const usr = "${sessionScope.usr}";
</script>
<%--async or defer--%>
<%--<script async="async"></script>--%>
<script src="js/logAndReg.js" async="async"></script>