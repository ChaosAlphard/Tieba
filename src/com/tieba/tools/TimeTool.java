package com.tieba.tools;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeTool {
    private static final DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentTime(){
        return formatPattern.format(LocalDateTime.now());
    }
    public static String formatTime(Date d){
        return formatPattern.format(LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()));
    }
    public static String formatTime(Timestamp t){
        return formatPattern.format(LocalDateTime.ofInstant(t.toInstant(), ZoneId.systemDefault()));
    }
}