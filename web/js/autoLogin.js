"use strict";

/* 自动登录 */

(function() {
    ajax({
        isPost:false,
        url:"LoginServlet",
        data:{"key":"value"},
        success:(xhr)=>{
            console.log(xhr.responseText);
            if(xhr.responseText==="reload"){
                location.reload();
            }
        }
    });
}());