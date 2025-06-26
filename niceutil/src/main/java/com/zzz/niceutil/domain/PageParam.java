package com.zzz.niceutil.domain;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 分页参数
 */
@Data
public class PageParam {
    @NotNull(message = "分页参数不能为空")
    protected Integer pageIndex = 1;

    @NotNull(message = "每页数量不能为空")
    @Max(value = 500, message = "每页最大为500")
    protected Integer pageSize = 20;

    protected Boolean searchCount;

    protected List<OrderItem> orders;

    public @NotNull(message = "分页参数不能为空") Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(@NotNull(message = "分页参数不能为空") Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public @NotNull(message = "每页数量不能为空") @Max(value = 500, message = "每页最大为500") Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(@NotNull(message = "每页数量不能为空") @Max(value = 500, message = "每页最大为500") Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }
}
