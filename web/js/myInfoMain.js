"use strict";

if(!window.XMLHttpRequest){
    alert("你的浏览器不支持XMLHttpRequest,请更换浏览器或升级版本");
}

/* 登陆状态检测 */
if(uid===""||usr===""){
    alert("登录状态异常!\n没有找到Session, 请在主界面更新Session");
    location.replace("./index.jsp");
}

/* 用户信息 */
let avatar=document.querySelector(".avatar");
let userId=document.querySelector(".userid");
let nickname=document.querySelector(".nickname");

avatar.src="./img/avatar/"+uid+".jpg";
avatar.onerror=()=>{
    avatar.src="./img/avatar/0.jpg";
};
userId.innerHTML="UID: "+uid;
nickname.innerHTML="昵称: "+usr;

let cover=document.querySelector(".uploadcover");
let close=document.querySelector(".avatarclose");
let editAvt=document.querySelector(".aspan");
editAvt.onclick=()=>{
    cover.style.display="flex";
};
close.onclick=()=>{
    cover.style.display="none";
};

/* 头像上传 */
let img=document.querySelector("#image");
let file=document.querySelector("#file");
let sub=document.querySelector("#sub");
// let pre=document.querySelector("#pre");

let cropper = new Cropper(img,{
    viewMode: 1,
    dragMode: "move",
    aspectRatio: 1 / 1,
    preview: '.preview',
    toggleDragModeOnDblclick: false
    //crop虽然预览效果好，但是会导致浏览器内存溢出，弃用。
    // crop: ()=>{
    //     pre.src=cropper.getCroppedCanvas({
    //         width: 210,
    //         height: 210,
    //         imageSmoothingEnabled: true,
    //         imageSmoothingQuality: 'high'
    //     }).toDataURL("image/jpeg");
    // }
});

file.onchange=()=>{
    let imgsrc = window.URL.createObjectURL(file.files[0]);
    cropper.replace(imgsrc);
};

sub.onclick=()=>{
    let uppic;
    uppic=cropper.getCroppedCanvas({
        width: 210,
        height: 210,
        imageSmoothingEnabled: true,
        imageSmoothingQuality: 'high'
    }).toDataURL("image/jpeg");
    console.log(uppic);

    if(uppic.indexOf("data:image/jpeg;base64,")!==-1){
        let data=new FormData();
        data.append("img",uppic);
        data.append("uid",uid);

        let xhr=new XMLHttpRequest();
        xhr.open("post","UploadServlet",true);
        xhr.send(data);
        xhr.onreadystatechange=()=>{
            if(xhr.readyState===4&&xhr.status===200){
                let xhrt=xhr.responseText;
                if(xhrt==="err"){
                    alert("上传失败,服务器找不到路径\nV:/RNC/IDEA/Tieba/web/img/avatar/")
                } else {
                    alert("上传成功\n更新浏览器缓存以查看效果");
                    location.reload();
                }
            }
        }
    } else {
        alert("裁剪图片出错,请重试");
    }
};

/* 2018.10.20 新增 */
const favoriteTies = sel(".ties");
void function() {
    ajax({
        isPost: false,
        url: "FavTiesServlet",
        data: {
            "uid": uid
        },
        success:(xhr)=>{
            const res=xhr.responseText;
            console.log(res);
            if(res!=="err"&&res!==""&&res!==void 0){
                const json = JSON.parse(res);
                json.forEach((tie)=>{
                    const favLink=document.createElement('a');
                    favLink.href=`/Ties?post=${tie['tieID']}`;
                    favLink.target="_blank";
                    favLink.rel="noopener";
                    favLink.innerHTML=tie['tieTitle'];
                    favoriteTies.appendChild(favLink);
                })
            }
        }
    })
}();

/*
arr.forEach(callback(currentValue[, index[, array]]) {
    //your iterator
}[, thisArg]);

callback:
    currentValueOptional    //数组中正在处理的当前元素的值。
    indexOptional           //数组中正在处理的当前元素的索引。
    arrayOptional           //正在应用forEach()的数组。
    thisArg
    callback中的this关键字可引用的对象。
*/