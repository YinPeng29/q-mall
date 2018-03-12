package com.bays.utils;

import java.util.List;

/**
 * Created by yinpeng on 2017/10/30.
 * 分页工具
 */
public class PageTool<T> {
    private int currentPage;  //当前页
    private int pageSize;  //每页显示条数
    private int totalPage; //总页数
    private int startIndex;   //数据库查询开始行数 sql: limit startIndex,pageSize
    private int startPage;   //开始页
    private int endPage;    //终止页
    private int totalRecord;  //查询总数据
    private List<T> pagelist;   //每页显示的数据

    public PageTool(int currentPage, int pageSize, int totalRecord ) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        //计算总页数
        if(totalRecord % pageSize ==0){
            this.totalPage = totalRecord / pageSize;
        }else{
            this.totalPage = totalRecord / pageSize +1;
        }

        //数据库查询开始行数 sql: limit startIndex,pageSize
        this.startIndex = (currentPage-1) * pageSize;
        this.startPage = Field.startPage;
        this.endPage = Field.endPage;
        if(totalPage <= 5){
            this.endPage = this.totalPage;
        }else{
            this.startPage = currentPage - 2;
            this.endPage = currentPage +2;
            if(startPage < 0){
                this.startPage = 1;
                this.endPage = 5;
            }
            if(endPage > this.totalPage){
                this.endPage = totalPage;
                this.startPage = endPage - 5;
            }
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getPagelist() {
        return pagelist;
    }

    public void setPagelist(List<T> pagelist) {
        this.pagelist = pagelist;
    }
}