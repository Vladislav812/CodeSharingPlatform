package platform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActualDate {
    private String actDate;

    ActualDate() {
        String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        this.actDate = ldt.format(formatter);
    }

    public String getActDate() {
        return actDate;
    }
}
