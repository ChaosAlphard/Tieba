"use strict";

/* AdminManage */

const grid = selAll(".gridContent>div");
const gridContent = sel(".gridContent");
const admin = selAll(".admin");

//对非管理员隐藏用户管理页
if(adminLV!=="3"){
    for(const item of admin){
        gridContent.removeChild(admin[i]);
    }
    // grid[1].style.display=grid[4].style.display=grid[5].style.display="none";
}

for(let i=0;i<grid.length;i++){
    setTimeout(()=>{
        grid[i].style.transform = "scale(1)";
    },(i*200+200));
}