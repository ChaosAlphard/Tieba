package com.tieba.tools;

import java.util.List;

public class ChangePage<T> {
    private int itemAmount = 10;

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public int countPage(List<T> lis){
        //获取分割的页数
        int n=lis.size()/itemAmount;
        //Java除法向下取整,如果有余数,需要额外加一页
        if(lis.size()%itemAmount!=0){
            n++;
        }
        System.out.println("取除:"+lis.size()/itemAmount+
        "  取余:"+lis.size()%itemAmount+"  最终页数:"+n);
        return n;
    }

    public List<T> getSubList(List<T> lis, int currentPage){
        List<T> l;
        int p=currentPage-1;
        if(currentPage*itemAmount>lis.size()){
            l=lis.subList(p*itemAmount,lis.size());
        } else {
            l=lis.subList(p*itemAmount,currentPage*itemAmount);
        }
        return l;
    }
}
/*
if(p*itemAmount>lis.size()){
    l=null;
} else if(currentPage*itemAmount>lis.size()){
    l=lis.subList(p*itemAmount,lis.size());
} else {
    l=lis.subList(p*itemAmount,currentPage*itemAmount);
}
*/