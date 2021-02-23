package com.project.pojo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class JpaPageInfo {
    /**
     * 当前页
     */
    private int page;
    /**
     * 每页显示条数
     */
    private int size;

    public Pageable getPageable() {
        return PageRequest.of(page - 1, size);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
