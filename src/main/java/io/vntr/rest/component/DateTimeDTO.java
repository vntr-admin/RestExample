package io.vntr.rest.component;

/**
 * Created by robertlindquist on 5/17/17.
 */
public class DateTimeDTO {
    private String date;
    private String time;
    private String timestamp;

    public DateTimeDTO() {
    }

    public DateTimeDTO(String date, String time, String timestamp) {
        this.date = date;
        this.time = time;
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
