package com.ecobank.srms.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/* @author sa_oladipo created on 5/17/22 */
@Slf4j
public class DateUtils {


    /**
     * Convert date to local date time
     * @param date: the date object
     * @return the date object
     */
    public static LocalDateTime getLocalDateTimeFromDate(Date date){

        log.info("Date:  {}", date);
        try {
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }


    /***
     * Converts LocalDate object to Date object
     * @param localDate: the local date object
     * @return the date object
     */
    public static Date getDateFromLocalDate(LocalDate localDate){

        if(localDate == null)
            return null;

        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    /**
     * Converts LocalDate object to java.sql.Date object
     * @param localDate: the local date object
     * @return the java.sql date object
     */
    public static java.sql.Date getSqlDateFromLocalDate(LocalDate localDate){

        if(localDate == null)
            return null;

        return java.sql.Date.valueOf(localDate);
    }
}
