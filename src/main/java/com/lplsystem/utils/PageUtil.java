package com.lplsystem.utils;

import java.util.List;

public class PageUtil {
    private int totalCount;//一共有多少条数据
    private int size;//每页几条数据
    private int totalPageCount;//一共有多少页
    private int page;//当前页码
    private List list;
    public int[] pageNums;//显示页数数组

    //获取startIndex的方法
    public int getStartIndex(){
        return size * (page -1);
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setPageNums() {
        pageNums = new int[Math.min(totalPageCount, 3)];
        //定义一个开始编号
        int begin = 1;
        //定义一个结束编号
        int end = 3;
        //定义数组初始下标
        int k = 0;

        //如果页数小于3
        if(totalPageCount < 3){
            end = totalPageCount;
        }
        //如果页数大于3
        if(totalPageCount > 3){
            begin = page - 1;
            end = page + 1;
            if(begin <= 0){
                begin = 1;
                end = 3;
            }
            if(end > totalPageCount){
                end = totalPageCount;
                begin = end - 2 ;
            }
        }
         //将从begin到end的标号放入数组中
        for(int i = begin; i <= end; i++){
            pageNums[k] = i;
            k++;
        }
    }

    public int[] getPageNums() {
        return pageNums;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPage(int page) {
        this.page = page;
    }

    //从数据库查询一共多少条数据
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount() {
        if(this.totalCount%this.size==0){
            this.totalPageCount=this.totalCount/this.size;
        }else{
            this.totalPageCount=this.totalCount/this.size+1;
        }
    }
}
