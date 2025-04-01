package com.zzz.niceutil.utils;

import java.util.Date;

public class DateUtil {

    /**
     * 计算两个日期之差
     * @param begin 开始日期
     * @param end 结束日期
     * @return
     */
    public static long dateDiff(Date begin, Date end) {
        long diff=0;
    try {
        long eTime = end.getTime();
        long sTime = begin.getTime();
         diff = (eTime - sTime) / 1000;

    }catch (Exception e){
        e.printStackTrace();
    }
    return diff;
    }

}
