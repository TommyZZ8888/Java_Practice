package com.zzz.niceutil.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;


import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class StreamFilter<T> {


    private Stream<T> stream;

    private StreamFilter(List<T> list) {
        stream = list.stream();
    }

    public static <T> StreamFilter<T> build(List<T> list) {
        return new StreamFilter<T>(list);
    }

    public Stream<T> getStream() {
        return stream;
    }

    public StreamFilter<T> setFilter(SFunction<T, ?> column, String query) throws Exception {
        if (StringUtils.isBlank(query)) {
            return this;
        }
        Method writeReplace = column.getClass().getDeclaredMethod("writeReplace");
        writeReplace.setAccessible(true);
        SerializedLambda invoke = (SerializedLambda) writeReplace.invoke(column);
        String implMethodName = invoke.getImplMethodName();
        String property = implMethodName.substring(3);
        String firstChar = property.substring(0, 1);
        String key = property.replaceFirst(firstChar, firstChar.toLowerCase());
        stream = stream.filter(item -> {
            try {
                Field field = item.getClass().getDeclaredField(key);
                field.setAccessible(true);
                String value = (String) field.get(item);
                if (StringUtils.isBlank(value)) {
                    return false;
                }
                return value.contains(query);
            } catch (NoSuchFieldException | IllegalAccessException ignore) {
                return false;
            }
        });
        return this;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
