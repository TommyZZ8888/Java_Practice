package com.zzz.niceutil.utils.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Map;

/**
 * @ClassName:ExcelListener
 * @Description:
 * @Author: vren
 * @Date: 2022/6/1 17:25
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private LinkedList<T> data = new LinkedList<>();

    public LinkedList<T> getData() {
        return data;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
//        log.info("头信息:{}", headMap);
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        data.add(t);
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        System.out.println(extra);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

}
