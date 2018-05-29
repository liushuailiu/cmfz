package com.fly.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

}
