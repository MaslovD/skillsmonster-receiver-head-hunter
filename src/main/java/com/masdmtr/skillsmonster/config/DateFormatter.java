package com.masdmtr.skillsmonster.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Configuration
public class DateFormatter {

    @Bean
    public DateTimeFormatter getFormatter() {
        return new DateTimeFormatterBuilder()
                // date/time
                .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                // offset (hh:mm - "+00:00" when it's zero)
                .optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd()
                // offset (hhmm - "+0000" when it's zero)
                .optionalStart().appendOffset("+HHMM", "+0000").optionalEnd()
                // offset (hh - "Z" when it's zero)
                .optionalStart().appendOffset("+HH", "Z").optionalEnd()
                // create formatter
                .toFormatter();
    }
}
