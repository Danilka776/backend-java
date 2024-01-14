package edu.project3;

import java.time.LocalDate;

public class LogRecord {

    private LocalDate date;
    private String request;
    private int responseCode;
    private int responseSize;

    public LogRecord(LocalDate date, String request, int responseCode, int responseSize) {
        this.date = date;
        this.request = request;
        this.responseCode = responseCode;
        this.responseSize = responseSize;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getRequest() {
        return request;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }
}
