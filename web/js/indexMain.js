"use strict";

/* index.jsp use this file */

const img=sel("#avatar");
img.addEventListener('error',()=>{
    img.src="img/avatar/0.jpg";
});
img.src=`./img/avatar/${uid}.jpg`;
// img.onerror=()=>{
//     img.src="./img/avatar/0.jpg";
// };

/* loading page */
if(usr !== ""){
    sel(".wel").innerHTML=`Welcome, ${usr}`;
}

function hiddenLoading() {
    let lod = sel(".loading");
    setTimeout(()=>{
        lod.style.opacity="0";
    },500);
    setTimeout(()=>{
        lod.style.display="none";
    },1300);
}

const rightBlock = sel(".rightblock");
const favBar = sel(".favbar");

void async function() {
        await getRecentlyTie().catch(e=>{
            sel(".lod").innerHTML="数据获取失败";console.log(e)
        });
        if(uid) {
            await getFavBars().catch(e=>{
                sel(".lod").innerHTML="数据获取失败";console.log(e)
            });
        }
        hiddenLoading();
        appendIframe();
}();

function getRecentlyTie() {
    return new Promise((resolve, reject) => {
        ajax({
            isPost:false,
            url:"RecentlyTieServlet",
            data:{"key":"value"},
            success:(xhr)=>{
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
                    reject(e);
                }
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
                    reject(e);
                }
            }
        })
    })
}

function appendIframe() {
    const iframe = document.createElement('iframe');
    iframe.src="http://music.163.com/outchain/player?type=0&id=2483570337&auto=0&height=430";
    iframe.setAttribute("style","width:330px; height:300px; position:fixed; left:0; bottom:0; margin:0; border:none; z-index:12;");
    sel("body").appendChild(iframe);
}