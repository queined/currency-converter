package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class History {
    private Currency to;
    private Currency from;
    private LocalDateTime currentDateTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public History(Currency to, Currency from) {
        this.to = to;
        this.from = from;
        this.currentDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[" + currentDateTime.format(formatter) + "] " + from + " -> " + to;
    }

}
