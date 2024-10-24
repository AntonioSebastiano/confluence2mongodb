package com.ulixe.confluence2mongodb.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String v) {
        if (v == null) {
            return null;
        }
        String dateTimeWithoutMillis = v.split("\\.")[0]; // Take the first part before any milliseconds

        return LocalDateTime.parse(dateTimeWithoutMillis, FORMATTER);
    }

    @Override
    public String marshal(LocalDateTime v) {
        if (v == null) {
            return null;
        }
        return v.format(FORMATTER);
    }
}