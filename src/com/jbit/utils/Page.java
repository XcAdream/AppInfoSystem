package com.jbit.utils;

/**
 * Created by Xc on 2018/4/19.
 */
public class Page {

    private Integer totalCount;  //总条数
    private Integer totalPageCount;  //总页数
    private Integer currentPageNo=1;  //起始页
    private Integer pageSize=5;  //每页条数

    public void calcCount(Integer count){
        this.totalCount=count;
        totalPageCount=count%pageSize==0?count/pageSize:count/pageSize+1;
        if(currentPageNo>totalPageCount){
           currentPageNo=totalPageCount;
        }
        if(currentPageNo<1){
            currentPageNo=1;
        }
    }
    public Integer first(){
        return (currentPageNo-1)*pageSize;
    }
    public Integer last(){
        return pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
