"use strict";

/* tie.jsp use this file */

const favorite = sel(".favorite");
const replyContent = sel(".replyContent");
const replySubmit = sel(".replySubmit");
const info = sel(".submitInfo");
const eps = sel(".eps");
const epses = sel(".epses");
const imgs = eps.querySelectorAll("img");
const pageBtn = selAll(".rePage>a");
const pagePort = sel(".pagePort");
const dfloor = selAll(".dfloor");
const dmain = selAll(".dmain");
const dreply = selAll(".dreply");
const toTop = sel(".toTop");

/* 对未登录用户隐藏回帖 */
if(!(uid&&usr)){
    sel(".tieReply").style.display="none";
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

let isFavorite=0;
/* 检查是否收藏 */
ajax({
    isPost: false,
    url: "FavoriteTie",
    data: {
        "UID": uid,
        "tieID": tieID
    },
    success:(xhr)=>{
        const res = xhr.responseText;
        if(res==="favorited"){
            isFavorite=1;
            favorite.innerHTML="取消收藏";
        }
    }
});
/* 收藏/取消收藏 */
favorite.onclick=()=>{
    ajax({
        isPost: true,
        url: "FavoriteTie",
        data: {
            "UID": uid,
            "tieID": tieID,
            "tieTitle": encodeURIComponent(tieTitle),
            "favorite": isFavorite
        },
        showData: true,
        success:(xhr)=>{
            const res = xhr.responseText;
            if(res==="suc"){
                if(isFavorite===0){
                    favorite.innerHTML="取消收藏";
                    isFavorite=1;
                } else {
                    favorite.innerHTML="收藏帖子";
                    isFavorite=0;
                }
            } else if(res==="dataErr"){
                alert("数据异常");
                location.reload();
            } else {
                alert("请登录后再试");
            }
        }
    });
};

pagePort.onclick=()=>{
    const page = sel(".pageSet").value;
    location.assign(`Ties?post=${tieID}&page=${page}`);
};
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
        // replyContent.appendChild(img); //原始插入
        /* 优化表情插入 */
        let div = selAll(".replyContent>div");
        let divi = div.length-1;
        if(divi<0){
            replyContent.appendChild(document.createElement('div'));
            div = selAll(".replyContent>div");
            divi = div.length-1;
        }
        div[divi].insertBefore(img,div[divi].querySelector("br"));
    }
}

/* 回复 */
for(let i=0;i<dreply.length;i++){
    dreply[i].onclick=()=>{
        const fs = document.createElement('fieldset');
        const lg = document.createElement('legend');
        lg.innerHTML = dfloor[i].innerHTML;
        fs.appendChild(lg);
        fs.innerHTML+=dmain[i].innerHTML;
        /* 限定引用层数 */
        const fsChild = fs.querySelector("fieldset");
        if(fsChild){
            fs.removeChild(fsChild);
        }
        /* 清除前置引用 */
        const fsq = replyContent.querySelector('fieldset');
        if(fsq){
            replyContent.removeChild(fsq);
        }
        fs.setAttribute("contenteditable",false);
        // replyContent.appendChild(fs);
        replyContent.insertBefore(fs,replyContent.childNodes[0]);
        replyContent.scrollIntoView({behavior:"smooth",block:"center"});
    }
}

/* 提交 */
replySubmit.onclick=()=>{
    replySubmit.disabled=true;
    const main = replyContent.innerHTML;
    if(main.length>0&&main.length<10000){
        replySubmit.innerHTML = "正在提交...";
        ajax({
            isPost:true,
            url:"NewReplyServlet",
            data:{
                "tieID": tieID,
                "uid": uid,
                "usr": usr,
                "main": encodeURIComponent(main)
            },
            showData:true,
            success:(xhr)=>{
                const res = xhr.responseText;
                if(res==="suc"){
                    alert("回复成功");
                    location.reload();
                } else if(res==="dataErr"){
                    alert("数据异常");
                } else if(res==="daoErr"){
                    alert("回复失败, 服务器繁忙");
                } else {
                    alert("回复失败, 未知错误");
                }
                replySubmit.innerHTML="提交";
                replySubmit.disabled=false;
            }
        })
    } else {
        info.style.color="red";
        setTimeout(()=>{
            replySubmit.disabled=false;
            info.style.color="black";
        },3000)
    }
};