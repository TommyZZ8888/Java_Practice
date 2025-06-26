package com.zzz.niceutil.domain;

import lombok.Data;

import java.util.List;

/**
 * Page返回对象
 *
 * @Author lihaifan
 * @Date Created in 2017/10/31 15:05
 */
@Data
public class PageResult<T> {

    /**
     * 当前页
     */
    private Long page = 0L;

    /**
     * 每页的数量
     */
    private Long pageSize = 0L;

    /**
     * 总记录数
     */
    private Long total = 0L;

    /**
     * 总页数
     */
    private Long pages = 0L;

    /**
     * 结果集
     */
    private List<T> list;

    public void setPage(Long page) {
        this.page = page;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public Long getPages() {
        return pages;
    }

    public List<T> getList() {
        return list;
    }
}
