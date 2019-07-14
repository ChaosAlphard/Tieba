"use strict";

/* Administrator Login */

if(!window.XMLHttpRequest){
    alert("你的浏览器不支持XMLHttpRequest,请更换浏览器或升级版本");
}

const aoc = sel(".aoc");
const pwd = sel(".pwd");
const submit = sel(".submit>button");
const info = sel(".info");

submit.onclick=()=>{
    const aocval = aoc.value;
    const pwdval = pwd.value;

    if(aocval.length>0&&pwdval.length>0){
        info.innerHTML = "正在登录";
        ajax({
            isPost:true,
            url:"/AdminLogin",
            data:{
                "aot":aocval,
                "pwd":pwdval
            },
            showData:true,
            success:(xhr)=>{
                const res = xhr.responseText;
                if(res==="suc"){
                    location.assign("manage.jsp")
                } else if(res==="lvErr"){
                    info.innerHTML="你的账号没有管理员权限";
                } else {
                    info.innerHTML="账号或密码错误";
                }
            }
        })
    } else {
        info.innerHTML="不能为空";
    }
};