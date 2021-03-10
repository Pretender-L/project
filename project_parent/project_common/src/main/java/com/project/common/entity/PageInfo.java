package com.project.common.entity;

import java.io.Serializable;

/**
 * 分页对象
 */
public class PageInfo implements Serializable {
    private static final int DEFAULT_PAGE_SIZE = 20;

    //当前页
    private int currentPage;
    //每页显示条数
    private int pageSize = DEFAULT_PAGE_SIZE;

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
}
