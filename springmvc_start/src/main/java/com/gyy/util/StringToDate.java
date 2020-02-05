package com.gyy.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义的类型转换器
 * 由String转换为日期类
 */
public class StringToDate implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if(source == null || source.length() == 0){
            throw new RuntimeException("数据为空");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException("解析失败");
        }
    }
}
