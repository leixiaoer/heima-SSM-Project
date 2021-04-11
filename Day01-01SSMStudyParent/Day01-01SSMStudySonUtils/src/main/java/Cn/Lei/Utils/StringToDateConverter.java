package Cn.Lei.Utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 里奥
 *把字符转换成日期类
 */
public class StringToDateConverter implements Converter<String, Date> {

    /**
     *
     * @param source  传入的字符串的值
     * @return
     */
    @Override
    public Date convert(String source){
        //判断
        if (source == null) {
            throw new RuntimeException("请你传入参数(生日)！");
        }
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //将转换后的日期返回
            return df.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException("数据类型转换出现错误！");
        }
    }
}
