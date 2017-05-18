package io.vntr.rest.component;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by robertlindquist on 5/17/17.
 */
@Component
public class DateTimeMapper {

    private static final TimeZone CHICAGO = TimeZone.getTimeZone("America/Chicago");

    private static final DateTimeFormatter timestampFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("hh:mm a");

    public DateTimeDTO fromEntity(Date date) {
        if(date == null) {
            return null;
        }
        DateTimeZone dateTimeZone = DateTimeZone.forTimeZone(CHICAGO);
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.withZone(dateTimeZone);

        String timestampStr = timestampFormatter.print(dateTime);
        String dateStr      = dateFormatter     .print(dateTime);
        String timeStr      = timeFormatter     .print(dateTime);

        DateTimeDTO dateTimeDTO = new DateTimeDTO(dateStr, timeStr, timestampStr);
        return dateTimeDTO;
    }
}
