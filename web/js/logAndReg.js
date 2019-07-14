"use strict";

/* Login And Register */

if(!window.XMLHttpRequest){
    alert("你的浏览器不支持XMLHttpRequest,请更换浏览器或升级版本");
}

let cover    = sel(".logandreg");	//遮罩层
let logbtn   = sel(".login");		//登录按钮
let regbtn   = sel(".register");	//注册按钮
let welcome  = sel(".welcome");		//欢迎信息
let logout   = sel(".logout");		//注销按钮
let logwin   = sel(".logdiv");		//登录窗体
let regwin   = sel(".regdiv");		//注册窗体
let logclose = sel(".closelog");	//登录关闭按钮
let regclose = sel(".closereg");	//注册关闭按钮
let logsub   = sel(".logsub");		//登录提交按钮
let regsub   = sel(".regsub");		//注册提交按钮
let logmpt   = sel(".logmpt");		//登录提示信息
let regmpt   = sel(".regmpt");		//注册提示信息
let ipts     = selAll(".ipts");		//输入框
let tips     = selAll(".tips");		//输入提示信息
let captcha  = sel(".captcha");		//验证码
let captchacover = sel(".captchacover");//验证码遮罩

if(uid!==""&&usr!==""){
    /* 顶栏改变 */
    logbtn.style.display="none";
    regbtn.style.display="none";
    welcome.style.display="inline";
    welcome.innerHTML=usr;
    logout.style.display="inline";
}

/* 注销 */
/*logout.onclick=()=>{
    let idflag = cookie.delete("uid");
    let flag = cookie.delete("usr");
    if(idflag===1&&flag===1){
        location.reload();
    } else if(idflag!==1||flag!==1){
        alert("网络异常,稍候再试");
        location.reload();
    } else {
        alert("删除失败,检测到异常字符,请尝试删除浏览器cookie");
        location.reload();
    }
};*/

/* 登陆 */
logbtn.onclick=()=>{
    cover.style.display="flex";
    logwin.style.display="block";
};
logclose.onclick=()=>{
    cover.style.display="none";
    logwin.style.display="none";
};
logsub.onclick=()=>{
    let logusr = sel(".logusr");
    let logpwd = sel(".logpwd");
    let usrval = logusr.value;
    let pwdval = logpwd.value;
    if(usrval!==""&&pwdval!==""){
        logmpt.innerHTML="正在登录";
        ajax({
            isPost: true,
            url: "LoginServlet",
            data: {
                "aot": usrval,
                "pwd": pwdval
            },
            success:(xhr)=>{
                const res = xhr.responseText;
                if(res==="err"){
                    logmpt.innerHTML="登录失败,账号/密码不符";
                } else if(res==="blocked"){
                    logmpt.innerHTML="--!你的账号已被封禁!--";
                } else if(res!==""&&res!==void 0){
                    const json = JSON.parse(res);
                    sel("#fuid").value=json[0];
                    sel("#fusr").value=json[1];
                    sel("#for").submit();
                } else {
                    logmpt.innerHTML="登录失败,未知错误";
                }
            }
        });
        // let xhr = new XMLHttpRequest();
        // xhr.open("post","LoginServlet",true);
        // xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        // xhr.send("aot="+usrval+"&pwd="+pwdval);
        // xhr.onreadystatechange=()=>{
        //     if(xhr.readyState===4&&xhr.status===200){
        //         const xhrt = xhr.responseText;
        //         if(xhrt!=="err"&&xhrt!=="blocked"&&xhrt!==""&&xhrt!==null&&xhrt!==void 0){
        //             let json=JSON.parse(xhrt);
        //             sel("#fuid").value=json[0];
        //             sel("#fusr").value=json[1];
        //             sel("#for").submit();
        //         } else if(xhrt==="blocked"){
        //             logmpt.innerHTML="--!你的账号已被封禁!--";
        //         } else if(xhrt==="err"){
        //             logmpt.innerHTML="登录失败,账号/密码不符";
        //         } else {
        //             logmpt.innerHTML="登录失败,账号/密码不符";
        //         }
        //     }
        // };
    } else {
        logmpt.innerHTML="不能为空";
    }
};

/* 注册 */
/* 验证码生成 */
function captchaSet(){
    const base = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0";
    let str="";
    for(let i=0;i<6;i++){
        str+=base.charAt(Math.floor(Math.random()*36))
    }
    let r=Math.floor(Math.random()*210);
    let g=Math.floor(Math.random()*210);
    let b=Math.floor(Math.random()*210);
    captcha.style.background=`linear-gradient(90deg, rgba(0,0,0,0) 60%, rgba(${r},${g},${b},0.2) 65%, rgb(${r},${g},${b}))`;
    captcha.innerHTML=str;
    console.log(str);
}

regbtn.onclick=()=>{
    cover.style.display="flex";
    regwin.style.display="block";
    for(let i=0;i<ipts.length;i++){
        ipts[i].value="";
    }
    captchaSet();
};
regclose.onclick=()=>{
    cover.style.display="none";
    regwin.style.display="none";
};
captchacover.onclick=()=>{
    captchaSet();
};

/* 注册信息验证 */
function checkAccount(){
    let regEx = /^[A-z]+[0-9]+[A-z0-9]*$/;
    let result = regEx.test(ipts[0].value);

    if(ipts[0].value.length<4||ipts[0].value.length>18){
        result=false;
    }
    if(result){
        tips[0].innerHTML="正确";
    } else {
        tips[0].innerHTML="错误";
    }
    return result;
}
function checkNickname(){
    let regEx = /[^A-z0-9\u4e00-\u9fa5]/;
    let result=!regEx.test(ipts[1].value);

    if(ipts[1].value.length<4||ipts[1].value.length>12){
        result=false;
    }
    if(result){
        tips[1].innerHTML="正确";
    } else {
        tips[1].innerHTML="错误";
    }
    return result;
}
function checkPassword(){
    let regEx = /[^A-z0-9!@#$%,.^]/;
    let result=!regEx.test(ipts[2].value);

    if(ipts[2].value.length<4||ipts[2].value.length>16){
        result=false;
    }
    if(result){
        tips[2].innerHTML="正确";
    } else {
        tips[2].innerHTML="错误";
    }
    return result;
}
function checkPwdAgain(){
    let result=(ipts[2].value===ipts[3].value&&ipts[3].value!=="");
    if(result){
        tips[3].innerHTML="正确";
    } else {
        tips[3].innerHTML="错误";
    }
    return result;
}
function checkCaptcha(){
    let result=(ipts[4].value.toUpperCase()===captcha.innerHTML);
    if(result){
        tips[4].innerHTML="正确";
    } else {
        tips[4].innerHTML="错误";
    }
    return result
}

ipts[0].onfocus=()=>{
    tips[0].innerHTML="4-18字符,字母+数字组成,字母开头,不能含有其他字符";
};
ipts[0].onblur=checkAccount;

ipts[1].onfocus=()=>{
    tips[1].innerHTML="4-12字符,不能含有特殊符号";
};
ipts[1].onblur=checkNickname;

ipts[2].onfocus=()=>{
    tips[2].innerHTML="4-16字符,字母或数字,可以使用!@#$%,.^符号";
};
ipts[2].onblur=checkPassword;

ipts[3].onfocus=()=>{
    tips[3].innerHTML="确认密码";
};
ipts[3].onblur=checkPwdAgain;

ipts[4].onfocus=()=>{
    tips[4].innerHTML="输入验证码";
};
ipts[4].onblur=checkCaptcha;

regsub.onclick=()=>{
    if(checkAccount()&&checkNickname()&&checkPassword()&&checkPwdAgain()&&checkCaptcha()){
        regmpt.innerHTML="注册中";
        ajax({
            isPost: true,
            url: "RegisterServlet",
            data: {
                "account": ipts[0].value,
                "nickname": ipts[1].value,
                "password": ipts[2].value
            },
            success:(xhr)=>{
                const res = xhr.responseText;
                if(res==="suc"){
                    alert("注册成功");
                    regwin.style.display="none";
                    logwin.style.display="block";
                } else if(res==="aot"){
                    regmpt.innerHTML="账户名已存在";
                } else if(res==="usr"){
                    regmpt.innerHTML="昵称已存在";
                } else {
                    regmpt.innerHTML="注册失败";
                }
            }
        });
        // let xhr = new XMLHttpRequest();
        // xhr.open("post","RegisterServlet",true);
        // xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        // xhr.send("account="+ipts[0].value+"&nickname="+ipts[1].value+"&password="+ipts[2].value);
        // xhr.onreadystatechange=()=>{
        //     if(xhr.readyState===4&&xhr.status===200){
        //         let xhrt=xhr.responseText;
        //         console.log(xhrt!=="");
        //         if(xhrt==="suc"){
        //             alert("注册成功");
        //             regwin.style.display="none";
        //             logwin.style.display="block";
        //         } else if(xhrt==="aot") {
        //             regmpt.innerHTML="账户名已存在";
        //         } else if(xhrt==="usr") {
        //             regmpt.innerHTML="昵称已存在";
        //         } else {
        //             regmpt.innerHTML="注册失败";
        //         }
        //     }
        // };
    } else {
        regmpt.innerHTML="不满足注册条件";
    }
};