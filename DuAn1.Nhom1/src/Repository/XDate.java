/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hoanh
 */
public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();

    public static Date toDate(String date, String... pattern) {
        try {
             if (pattern.length > 0) {
                formater.applyPattern(pattern[0]);
            }
            if (date == null) {
                return XDate.now();
            }
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String toString(Date date, String... pattern) {
        
        if (pattern.length > 0) {
            formater.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = XDate.now();
        }
        
        return formater.format(date);

    }

    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }
    
    public static Date now(){
        long milis = System.currentTimeMillis();
        return new java.util.Date(milis);
    }
}
