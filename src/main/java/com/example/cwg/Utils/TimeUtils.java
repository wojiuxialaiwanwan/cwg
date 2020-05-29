package com.example.cwg.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String getPicName(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return df.format(new Date())+(int)(Math.random()*1000+8999);
    }
}
