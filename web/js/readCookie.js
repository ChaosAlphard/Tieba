"use strict";

/* 此文件用于存放自己封装的函数, 方便以后调用 */
/* L('ω')┘三└('ω')｣ */
/* lastUpdate: 2018.7.10 */

/* Selector 用于简化网页元素的获取 */
/* createTime: 2018.7.10 */
function sel(x) {
    return document.querySelector(x);
}
function selAll(x) {
    return document.querySelectorAll(x);
}

/* cookie(对象) 封装cookie的读写操作 */
/* createTime: 2018.6.12 */
const cookie = {
    read:function(key) {
        let result = void 0;
        /* 使用split分割cookie字符串 */
        const cookieArr = document.cookie.split(";");
        const keyt = key+"=";

        for(let i=0;i<cookieArr.length;i++){
            /* 使用cs对象将分割的cookie字符串装起来 */
            let cs = cookieArr[i];
            while(cs.charAt(0) === " "){
                /* 如果cs第一个字符为空白字符,则去掉它 */
                cs = cs.substring(1);
            }
            if(cs.indexOf(keyt) !== -1){
                /* 查找key对应的value,返回解码后的字符串 */
                const str = cs.substring(keyt.length, cs.length);
                result = decodeURI(str);
            }
        }

        return result;
    },
    add:function(key, value, expireDays) {
        /* 对字符串进行编码 */
        const encodeValue = encodeURI(value);
        /* cookie保存时间 */
        const days = expireDays*24*60*60*1000;
        let date = new Date();
        date.setTime(date.getTime()+days);
        /* 计算超时时间并转换为cookie所用格式 */
        const time = "expires="+date.toUTCString();
        document.cookie = key+"="+encodeValue+";"+time;
    },
    delete:function(key) {
        let status=0;
        const dk = key;
        /* 将超时时间设为-1以删除cookie */
        this.add(dk,"del",-1);
        /* 检查是否成功删除 */
        if(this.read(dk)===void 0){
            status=1;
        }
        return status;
    }
};

/* 封装ajax, 用于简化ajax调用 */
/* createTime: 2018.7.10 */
function ajax({
    isPost=true,
    url="url",
    data={"key":"value"},
    showData=false,
    waitTime=5000,
    success=(xhr)=>{console.log(xhr.responseText)},
    error=(err)=>{console.error(err.status)},
    timeout=(xhr)=>{console.warn(xhr)}
}={}) {
    if(!window.XMLHttpRequest){
        alert("你的浏览器不支持XMLHttpRequest");
        return;
    }

    let xhr=new XMLHttpRequest();

    let params=[];
    for(const key in data){
        /*Object对象转换为Object数组*/
        params.push(key+"="+data[key]);
    }
    /* Object数组转换为ajax格式字符串 */
    const postData = params.join("&");

    if(showData){
        console.log("isPost: "+(isPost?"true":"false"));
        console.log("url: "+url);
        console.log("postData: "+postData);
    }

    xhr.timeout = waitTime;
    if(isPost){
        xhr.open("post",url,true);
        xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xhr.send(postData);
    } else {
        xhr.open("get",url+"?"+postData,true);
        xhr.send(null);
    }

    // xhr.readyState为3 时, 调用onprogress()
    // hxr.readyState为4 时, 调用onload()
    xhr.onreadystatechange=()=>{
        if(xhr.readyState===4&&xhr.status===200){
            success(xhr);
        } else if(xhr.readyState===4&&xhr.status>=500) {
            error(xhr);
        }
    };
    xhr.onerror = () => {
        error(xhr);
    };
    xhr.ontimeout = e => {
        timeout(e);
    };
}