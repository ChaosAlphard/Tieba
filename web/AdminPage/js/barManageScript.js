"use strict";

/* create bar */

const barName = sel(".barName");
const barContent = sel(".editable");
const info= selAll(".info");
const cre = sel(".cre>button");

cre.onclick=()=>{
    const name = barName.value;
    const cont = barContent.innerHTML;

    cre.disabled=true;
    setTimeout(()=>{
        cre.disabled=false;
        info[0].style.color = "#000000";
        info[1].style.color = "#000000"
    },3000);

    console.log(name.length);
    console.log(cont.length);
    if(isBarName(name)&&isBarCont(cont)){
        ajax({
            isPost: true,
            url: "/CreateBar",
            data: {
                "barName": encodeURI(name),
                "barContent": encodeURI(cont)
            },
            showData: true,
            success:(xhr)=>{
                const res = xhr.responseText;
                if(res==="suc"){
                    alert("创建成功");
                } else if("rep"){
                    alert("已存在同名贴吧");
                } else {
                    alert("创建失败");
                }
            }
        })
    } else {
        info[0].style.color = "#FF0000";
    }
};

function isBarName(name) {
    let regEx = /[^A-z0-9\u4e00-\u9fa5]/;
    let result=!regEx.test(name);
    if(result){
        return name.length>0&&name.length<=12;
    } else {
        info[0].style.color = "#FF0000";
        return false;
    }
}

function isBarCont(cont) {
    if(cont.length>0&&cont.length<=280){
        return true;
    } else {
        info[1].style.color = "#FF0000";
        return false;
    }
}