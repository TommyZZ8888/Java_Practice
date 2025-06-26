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
}
