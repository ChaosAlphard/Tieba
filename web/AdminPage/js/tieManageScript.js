"use strict";

/* ManageTie */

// const lab = selAll(".lab");    //标签
const inp = selAll(".inp");     //信息框
const tieID = sel(".tieID");
const sub = sel(".sub");        //查询按钮
const del = sel(".del");        //删除按钮
const setEli = sel(".setEli");  //加精
const unsetEli = sel(".unsetEli");//撤精
const info = sel(".info");      //信息
const dtl = sel (".detailed");
let dtlFlag = 0;                //帖子摘要状态
let delFlag = 0;                //删除标识(是否可被删除)
let resID = 0;                  //返回的ID

sub.onclick=()=>{
    sub.disabled=true;
    setTimeout(()=>{sub.disabled=false},1000);

    dtlFlag=0;      //状态标识
    delFlag=0;
    const val = tieID.value;

    if(val.match(/^[0-9]+$/)){
        info.innerHTML = "正在查询";
        inp[1].innerHTML=inp[2].innerHTML=inp[3].innerHTML="";
        setEli.disabled=true;
        unsetEli.disabled=true;
        ajax({
            isPost: false,
            url: "/DeleteTie",
            data: {
                "tieID": val
            },
            showData: true,
            success:(xhr)=>{
                const res = xhr.responseText;
                // console.log(res);
                if(res==="err"||res===null||res===void 0||res===""){
                    info.innerHTML = "服务器繁忙";
                } else if(res==="dataErr") {
                    info.innerHTML = "没有记录";
                } else {
                    const json = JSON.parse(res)[0];
                    dtlFlag = 1;
                    delFlag = 1;
                    resID=json['tieID'];

                    inp[1].innerHTML = json['tieTitle'];
                    const img = document.createElement('img');
                    img.src=`/img/avatar/${json['tieUserID']}.jpg`;
                    img.style.width="32px";
                    img.style.height="32px";
                    inp[2].innerHTML = json['tieUser'];
                    inp[2].appendChild(img);
                    inp[3].innerHTML = json['postTime'];
                    info.innerHTML = "点击查看帖子摘要";
                    dtl.innerHTML = json['tieMain'];
                    if(json['elite']===0){
                        setEli.disabled=false;
                        unsetEli.disabled=true;
                    } else {
                        setEli.disabled=true;
                        unsetEli.disabled=false;
                    }
                }
            }
        });
    } else {
        info.innerHTML = "请正确输入字符";
    }
};
del.onclick=()=>{
    if(delFlag===1&&confirm("确定要删除吗")){
        del.disabled=true;
        setTimeout(()=>{del.disabled=false},1000);
        ajax({
            isPost: true,
            url: "/DeleteTie",
            data: {
                "tieID": resID
            },
            showData:true,
            success:(xhr)=>{
                const res = xhr.responseText;
                if(res==="suc"){
                    info.innerHTML="删除成功";
                } else if(res==="dataErr"){
                    info.innerHTML="找不到记录";
                } else if(res==="lvErr"){
                    alert("身份认证失效，请重新登录");
                    location.assign("login.jsp");
                } else {
                    info.innerHTML="服务器繁忙";
                }
            }
        })
    }
};

info.onclick=()=>{
    if(dtlFlag===1){dtl.style.top = "calc(50vh - 304px)"}
};
dtl.onclick=()=>{
    dtl.style.top = "-1000px";
};

setEli.onclick=()=>{
    ajax({
        isPost: true,
        url: "/SetElite",
        data: {
            "tieID": resID,
            "status": "1"
        },
        showData: true,
        success:(xhr)=>{
            const res = xhr.responseText;
            if(res==="suc"){
                setEli.disabled=true;
                unsetEli.disabled=false;
                info.innerHTML="加精成功";
            } else if(res==="dataErr"){
                info.innerHTML="找不到记录";
            } else if(res==="lvErr"){
                alert("身份认证失效，请重新登录");
                location.assign("login.jsp");
            } else {
                info.innerHTML="输入格式错误";
            }
        }
    })
};
unsetEli.onclick=()=>{
    ajax({
        isPost: true,
        url: "/SetElite",
        data: {
            "tieID": resID,
            "status": "0"
        },
        showData: true,
        success:(xhr)=>{
            const res = xhr.responseText;
            if(res==="suc"){
                setEli.disabled=false;
                unsetEli.disabled=true;
                info.innerHTML="撤精成功";
            } else if(res==="dataErr"){
                info.innerHTML="找不到记录";
            } else if(res==="lvErr"){
                alert("身份认证失效，请重新登录");
                location.assign("login.jsp");
            } else {
                info.innerHTML="输入格式错误";
            }
        }
    })
};