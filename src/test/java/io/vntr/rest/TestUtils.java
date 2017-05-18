package io.vntr.rest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

/**
 * Created by robertlindquist on 5/17/17.
 */
public class TestUtils {
    private static final DateTimeZone CHICAGO = DateTimeZone.forID("America/Chicago");

    public static Date getDateChicago(int year, int month, int dayOfMonth) {
        return new DateTime(year, month, dayOfMonth, 0, 0, 0, 0, CHICAGO).toDate();
    }

    public static Date getDateChicago(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        return new DateTime(year, month, dayOfMonth, hourOfDay, minute, 0, 0, CHICAGO).toDate();
    }

    public static Date getDateChicago(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, int millisecond){
        return new DateTime(year, month, dayOfMonth, hourOfDay, minute, second, millisecond, CHICAGO).toDate();
    }

}
