"use strict";

/* bar.jps use this file */

const follow = sel(".follow");
const tieTitle = sel(".newTieTitle");
const tieContent = sel(".newTieContent");
const tieSubmit = sel(".tieSubmit");
const info = sel(".submitInfo");
const eps = sel(".eps");
const epses = sel(".epses");
const imgs = epses.querySelectorAll("img");
const pageBtn = selAll(".barPage>a");
const pagePort = sel(".pagePort");
const toTop = sel(".toTop");

/* 对未登录用户隐藏发帖 */
if(!(uid&&usr)){
    sel(".barNewTie").style.display="none";
    info.style.display="none";
}

window.addEventListener("scroll",()=>{
    if(window.pageYOffset>250){
        toTop.style.opacity="1";
        toTop.style.transform="translateX(0px)";
    } else {
        toTop.style.opacity="0";
        toTop.style.transform="translateX(100px)";
    }
});

toTop.onclick=()=>{
    sel("html").scrollIntoView({behavior:"smooth",block:"start"})
};

let isFollow=0;
/* 检查是否关注 */
ajax({
    isPost: false,
    url: "FollowBar",
    data: {
        "UID": uid,
        "barID": barID
    },
    success:(xhr)=>{
        const res = xhr.responseText;
        if(res==="followed"){
            isFollow=1;
            follow.innerHTML = "取消关注";
        }
    }
});
/* 关注/取关 */
follow.onclick=()=>{
    ajax({
        isPost: true,
        url: "FollowBar",
        data: {
            "UID": uid,
            "barID": barID,
            "barName": barName,
            "follow": isFollow
        },
        showData: true,
        success:(xhr)=>{
            const res = xhr.responseText;
            if(res==="suc"){
                if(isFollow===0){
                    follow.innerHTML = "取消关注";
                    isFollow=1;
                } else {
                    follow.innerHTML = "关注贴吧";
                    isFollow=0;
                }
            } else if(res==="dataErr"){
                alert("身份认证失败");
                location.reload();
            } else {
                alert("数据异常");
            }
        }
    });
};

/* 跳页 */
pagePort.onclick=()=>{
    const page = sel(".pageSet").value;
    location.assign(`Bars?id=${barID}&page=${page}`);
};
/* 首页/末页检测 */
if(curPage==="1"){
    pageBtn[0].style.pointerEvents="none";
    pageBtn[0].style.backgroundColor="#B2B2B2";
    pageBtn[0].style.color="#E2E2E2";
    pageBtn[1].style.pointerEvents="none";
    pageBtn[1].style.backgroundColor="#B2B2B2";
    pageBtn[1].style.color="#E2E2E2";
}
if(curPage===pageCount){
    pageBtn[2].style.pointerEvents="none";
    pageBtn[2].style.backgroundColor="#B2B2B2";
    pageBtn[2].style.color="#E2E2E2";
    pageBtn[3].style.pointerEvents="none";
    pageBtn[3].style.backgroundColor="#B2B2B2";
    pageBtn[3].style.color="#E2E2E2";
}

let epsFlag=0;
eps.onclick=()=>{
    if(epsFlag===0){
        epses.style.display = "flex";
        epsFlag=1;
    } else {
        epses.style.display = "none";
        epsFlag=0;
    }
};
/* 表情插入 */
for(let i=0;i<imgs.length;i++){
    imgs[i].onclick=()=>{
        const img = document.createElement('img');
        img.src=`/img/eps/${i}.png`;
        // tieContent.appendChild(img); //原始插入
        /* 优化表情插入 */
        let div = selAll(".newTieContent>div");
        let divi = div.length-1;
        if(divi<0){
            tieContent.appendChild(document.createElement('div'));
            div = selAll(".newTieContent>div");
            divi = div.length-1;
        }
        div[divi].insertBefore(img,div[divi].querySelector("br"));
    }
}

/* 提交 */
tieSubmit.onclick=()=>{
    tieSubmit.disabled=true;
    const title = tieTitle.value.replace(/</g,"&lt;").replace(/>/g,"&gt;");
    const content = tieContent.innerHTML;
    if(title.length>0&&content.length>0&&title.length<21&&content.length<1000){
        tieSubmit.innerHTML = "正在提交...";
        ajax({
            isPost:true,
            url:"NewTieServlet",
            data:{
                "barID": barID,
                "uid": uid,
                "usr": usr,
                "title": encodeURIComponent(title),
                "main": encodeURIComponent(content)
            },
            showData:true,
            success:(xhr)=>{
                const res = xhr.responseText;
                if(res==="suc"){
                    alert("发帖成功");
                    location.reload();
                } else if(res==="dataErr"){
                    alert("身份认证失败, 刷新网页后再试");
                } else if(res==="daoErr"){
                    alert("发帖失败, 服务器繁忙");
                } else {
                    alert("发帖失败, 未知错误");
                }
                tieSubmit.innerHTML="提交";
                tieSubmit.disabled=false;
            }
        })
    } else {
        info.style.color="red";
        setTimeout(()=>{
            tieSubmit.disabled=false;
            info.style.color="black";
        },3000)
    }
};