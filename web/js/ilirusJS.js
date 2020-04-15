"use strict";

/* 此文件用于存放自己封装的函数, 方便以后调用 */

/* Selector, 用于简化网页元素的获取 */
function sel(x) {
    return document.querySelector(x);
}
function selAll(x) {
    return document.querySelectorAll(x);
}

/* 简化控制台输出 */
function log(x) {
    console.log(x);
}

class Ilirus {
    constructor(){}

    /* 封装ajax, 用于简化ajax调用 */
    ajax({
        contentType="application/x-www-form-urlencoded",
        isPost=true,
        url="url",
        data={"key":"value"},
        showData=false,
        async=true,
        success=(xhr)=>{console.log(xhr.responseText);}
    }) {
        let xhr=null;
        if(window.XMLHttpRequest){
            xhr = new XMLHttpRequest();
        }

        let params=[];
        for(const key in data){
            params.push(key+"="+data[key]);
        }
        const postData = params.join("&");

        if(showData){
            console.log("method: "+(isPost?"post":"get"));
            console.log("isAsync: "+(async?"yse":"no"));
            console.log("url: "+url);
            console.log("data: "+postData);
        }

        if(isPost){
            xhr.open("post",url,async);
            xhr.setRequestHeader("Content-type",contentType);
            xhr.send(postData);
        } else {
            xhr.open("get",url+"?"+postData,async);
            xhr.send(null);
        }

        xhr.onreadystatechange=()=>{
            if(xhr.readyState===4&&xhr.status===200){
                success(xhr);
            }
        }
    }

}