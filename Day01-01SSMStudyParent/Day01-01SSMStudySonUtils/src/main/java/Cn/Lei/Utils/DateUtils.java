package Cn.Lei.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
    日期转换工具
 */
public class DateUtils {

    //日期转换成字符串          date日期      strTime要转换成什么格式的字符日期
    public static String dateToString(Date date,String strTime){
        //获取格式化时间对象
        SimpleDateFormat sdf = new SimpleDateFormat(strTime);
        //将日期转换成字符串
        String format = sdf.format(date);
        //返回字符串对象
        return format;

    }


    //字符串转换成日期   前面是字符串日期时间  后面是转换成日期的格式
    public static Date stringToDate(String str, String patt)throws Exception{
        //获取格式化时间对象
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        //字符串转换成时间对象
        Date parse = sdf.parse(str);
        //返回时间对象
        return parse;
    }

}
