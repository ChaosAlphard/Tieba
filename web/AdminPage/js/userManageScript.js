"use strict";

/* ManageUser */

const inp = selAll(".inp");
const userID = sel(".userID");
const sub = sel(".sub");        //查询
const avatar = sel(".avatar");  //信息
const banned = sel(".banned");  //封禁
const normal = sel(".normal");  //通常
const barser = sel(".barser");  //吧务
let resID=0;

sub.onclick=()=>{
    sub.disabled=true;
    setTimeout(()=>{sub.disabled=false},1000);

    const val = userID.value;
    if(val.match(/^[0-9]+$/)){
        avatar.innerHTML="正在查询";
        inp[1].innerHTML=inp[2].innerHTML="";
        banned.disabled=true;
        normal.disabled=true;
        barser.disabled=true;
        ajax({
            isPost: false,
            url: "/BannedUser",
            data: {
                "userID": val
            },
            showData: true,
            success:(xhr)=>{
                const res = xhr.responseText;
                if(res==="err"||res===""||res===null||res===void 0){
                    avatar.innerHTML = "服务器繁忙";
                } else if(res==="dataErr"){
                    avatar.innerHTML = "没有记录";
                } else {
                    const json = JSON.parse(res)[0];
                    resID = json['UID'];
                    inp[1].innerHTML = json['username'];
                    switch(json['adminLv']){
                        case 0:
                            inp[2].innerHTML="封禁";
                            normal.disabled=false;
                            barser.disabled=false;
                            break;
                        case 1:
                            inp[2].innerHTML="正常";
                            banned.disabled=false;
                            barser.disabled=false;
                            break;
                        case 2:
                            inp[2].innerHTML="吧务";
                            banned.disabled=false;
                            normal.disabled=false;
                            break;
                        case 3:
                            inp[2].innerHTML="管理";
                            break;
                        default:
                            inp[3].innerHTML="未知";
                            break;
                    }
                    const img = document.createElement('img');
                    img.src=`/img/avatar/${json['UID']}.jpg`;
                    img.style.width="128px";
                    img.style.height="128px";
                    img.onerror=()=>{img.src="/img/avatar/0.jpg"};
                    avatar.innerHTML="";
                    avatar.appendChild(img);
                }
            }
        })
    } else {
        avatar.innerHTML = "请输入正确ID";
    }
};

banned.onclick=()=>{
    ajax({
        isPost: true,
        url: "/BannedUser",
        data: {
            "userID": resID,
            "status": "0"
        },
        success:(xhr)=>{
            const res = xhr.responseText;
            if(res==="suc"){
                avatar.innerHTML = "封禁成功";
                banned.disabled=true;
                normal.disabled=false;
                barser.disabled=false;
            } else if(res==="dataErr"){
                avatar.innerHTML = "没有记录";
            } else if(res==="lvErr"){
                avatar.innerHTML = "权限不足";
            } else if(res==="lock"){
                avatar.innerHTML = "该用户权限已被管理员锁定";
            }
        }
    })
};

normal.onclick=()=>{
    ajax({
        isPost: true,
        url: "/BannedUser",
        data: {
            "userID": resID,
            "status": "1"
        },
        success:(xhr)=>{
            const res = xhr.responseText;
            if(res==="suc"){
                avatar.innerHTML = "成功设为普通用户";
                banned.disabled=false;
                normal.disabled=true;
                barser.disabled=false;
            } else if(res==="dataErr"){
                avatar.innerHTML = "没有记录";
            } else if(res==="lvErr"){
                avatar.innerHTML = "权限不足";
            } else if(res==="lock"){
                avatar.innerHTML = "该用户权限已被管理员锁定";
            }
        }
    })
};

barser.onclick=()=>{
    ajax({
        isPost: true,
        url: "/BannedUser",
        data: {
            "userID": resID,
            "status": "2"
        },
        success:(xhr)=>{
            const res = xhr.responseText;
            if(res==="suc"){
                avatar.innerHTML = "成功设为吧务";
                banned.disabled=false;
                normal.disabled=false;
                barser.disabled=true;
            } else if(res==="dataErr"){
                avatar.innerHTML = "没有记录";
            } else if(res==="lvErr"){
                avatar.innerHTML = "权限不足";
            } else if(res==="lock"){
                avatar.innerHTML = "该用户权限已被管理员锁定";
            }
        }
    })
};