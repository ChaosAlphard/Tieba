"use strict";

/* index.jsp use this file */

const img=sel("#avatar");
img.src="./img/avatar/"+uid+".jpg";
img.onerror=()=>{
    img.src="./img/avatar/0.jpg";
};

/* loading page */
if(usr !== ""){
    sel(".wel").innerHTML="Welcome, "+usr;
}

const rightBlock = sel(".rightblock");
const favBar = sel(".favbar");

(async function() {
    try {
        setTimeout(()=>{
            sel(".lod").innerHTML="链接超时";
        },10000);
        const awaitLogin = await autoLogin();
        if(awaitLogin==="reload") {
            location.reload()
        }
        if(uid) {
            await Promise.all([getRecentlyTie(), getFavBars()]);
        } else {
            await getRecentlyTie();
        }
    } catch(e) {
        sel(".lod").innerHTML=e[0];
        console.warn(e[1]);
    } finally {
        hiddenLoading();
    }
}());

function hiddenLoading() {
    let lod = sel(".loading");
    setTimeout(()=>{
        lod.style.opacity="0";
    },500);
    setTimeout(()=>{
        lod.style.display="none";
    },1300);
}

function autoLogin() {
    return new Promise((resolve, reject) => {
        ajax({
            isPost:false,
            url:"LoginServlet",
            data:{"key":"value"},
            success:(xhr)=>{
                console.log(xhr.responseText);
                resolve(xhr.responseText);
            },
            error:(xhr)=>{
                console.error(xhr.status);
                reject(["网络异常",xhr])
            }
        });
    });
}

function getRecentlyTie() {
    return new Promise((resolve, reject) => {
        ajax({
            isPost:false,
            url:"RecentlyTieServlet",
            data:{"key":"value"},
            success:(xhr)=>{
                if(xhr.responseText === "err"){
                    reject(["获取帖子数据失败", xhr]);
                    return;
                }
                try {
                    const json = JSON.parse(xhr.responseText);
                    for (const tie of json) {
                        const lastTie = document.createElement('div');
                        const tieTitle = document.createElement('a');
                        const tieMain = document.createElement('div');
                        const tieTime = document.createElement('div');
                        lastTie.classList.add("lastTie");
                        tieTitle.classList.add("tieTitle");
                        tieMain.classList.add("tieMain");
                        tieTime.classList.add("tieTime");

                        tieTitle.href = `Ties?post=${tie['tieID']}`;
                        tieTitle.target = "_blank";
                        tieTitle.rel = "noopener";
                        tieTitle.innerHTML = tie['tieTitle'];
                        tieMain.innerHTML = tie['tieMain'];
                        tieTime.innerHTML = tie['updateTime'];

                        lastTie.appendChild(tieTitle);
                        lastTie.appendChild(tieMain);
                        lastTie.appendChild(tieTime);

                        rightBlock.appendChild(lastTie);
                    }
                    resolve();
                } catch(e) {
                    reject(["解析帖子数据失败", e]);
                }
            },
            error:xhr=>{
                console.error("getRecentlyTie",xhr);
                reject(["网络异常",xhr])
            }
        });
    })
}

function getFavBars() {
    return new Promise((resolve, reject) => {
        ajax({
            isPost:false,
            url:"FavBarsServlet",
            data:{
                "uid": uid
            },
            showData:true,
            success:(xhr)=>{
                if(xhr.responseText === "err"){
                    reject(["获取关注贴吧数据失败", xhr]);
                    return;
                }
                try {
                    const json = JSON.parse(xhr.responseText);
                    for (const bar of json) {
                        const barLink = document.createElement('a');
                        barLink.classList.add("barlink");
                        barLink.href = `Bars?id=${bar['barID']}`;
                        barLink.target = "_blank";
                        barLink.rel = "noopener";
                        barLink.innerHTML = `${bar['barName']}吧`;
                        favBar.appendChild(barLink);
                    }
                    resolve();
                } catch(e) {
                    reject(["解析帖吧数据失败",xhr]);
                }
            },
            error:(xhr)=>{
                console.error("getFavBars",xhr);
                reject(["网络异常",xhr]);
            }
        })
    })
}