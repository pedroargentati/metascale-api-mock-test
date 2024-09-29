package br.com.metascale.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    // Convert String to LocalDateTime
    public static LocalDateTime stringToLocalDateTime(String dateString) {
        return LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
    }

    // Convert String to Date
    public static Date stringToDate(String dateString) {
        try {
            return SIMPLE_DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format, expected: " + DATE_PATTERN, e);
        }
    }

    // Convert LocalDateTime to String
    public static String localDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    // Convert Date to String
    public static String dateToString(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    // Convert LocalDateTime to Date
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    // Convert Date to LocalDateTime
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return new java.sql.Timestamp(date.getTime()).toLocalDateTime();
    }
}
