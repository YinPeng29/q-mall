package com.bays.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinpeng on 2017/10/31.
 */
public class DateTool {
    private static final Logger logger = LoggerFactory.getLogger(DateTool.class);

    /**
     * 时间戳转换之间
     * @param timestemp
     * @return
     */
    public static String tempToDate(String timestemp){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time=new Long(timestemp);
        String date = format.format(time);
        logger.info("时间戳转时间.."+timestemp+"==-->"+date);
        return date;
    }

    /**
     * 时间转换时间
     * @param paraDate
     * @return
     */
    public static long dateToTemp(String paraDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String paraDate = "2017-10-31 00:00:00";
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(paraDate);
        } catch (ParseException e) {
            logger.error("时间转时间戳发生异常...【DateTool.dateToTemp】",e);
            e.printStackTrace();
        }
        long time3 = parse.getTime();
        System.out.println(paraDate +" 时间戳 "+ time3);
        return time3;
    }

    /**
     * count天后的日期
     * @param count
     * @return
     */
    public static Map<String,String> catchDate(int count){
        //今日明日 00:00 时间戳
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        String today = String.valueOf(cal.getTimeInMillis());//今天0点时间戳
        cal.add(Calendar.DATE,count);
        String otherDay = String.valueOf(cal.getTimeInMillis());//count天后 0点时间戳
        Map<String,String> map = new HashMap<String,String>();
        map.put("today",today);
        map.put("otherDay",otherDay);
        logger.info(map.toString());
        return map;
    }
}
