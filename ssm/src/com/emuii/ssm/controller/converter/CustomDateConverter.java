package com.emuii.ssm.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by Leslie on 2018\1\8 0008.<br>
 */
public class CustomDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        try {
            //进行日期转换
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
