package com.egoo.seckill.bigfish.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 转换类
 * @author  lixing
 */
@Component
@Slf4j
public class CV {


    //string转double
    public static Double toD(String value){
        return BigDecimal.valueOf(Double.valueOf(value)).setScale(2,BigDecimal.ROUND_UP ).doubleValue();
    }
    public static Double toD(Double value){
        return BigDecimal.valueOf(value).setScale(2,BigDecimal.ROUND_UP ).doubleValue();
    }

    public static Double toD_down(Double value){
        return BigDecimal.valueOf(value).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static Long toL(String value){
        return BigDecimal.valueOf(Long.valueOf(value)).longValue();
    }

    public static Integer toInt(Long value){return Integer.valueOf(value+"");}

    public static Integer toInt(Double value){return BigDecimal.valueOf(value).intValue();}


}
