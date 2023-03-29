package com.enigma.ecommerce.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class GenerateDate {
    public static Date generate(String dateparams) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateparams);
        } catch (ParseException p) {
            System.err.println(p.getMessage());
        }
        return date;
    }
}
