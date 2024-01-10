package edu.project3;

import java.time.LocalDate;
import java.util.Map;

public class LogReport {

    private String fileName;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int totalRequests;
    private int averageResponseSize;
    private Map<String, Integer> topResources;
    private Map<Integer, Integer> responseCodeCounts;

    public int getTotalRequests() {
        return totalRequests;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateFrom(LocalDate date) {
        this.dateFrom = date;
    }

    public void setDateTo(LocalDate date) {
        this.dateTo = date;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    public String getFileName() {
        return fileName;
    }

    public int getAverageResponseSize() {
        return averageResponseSize;
    }

    public void setAverageResponseSize(int averageResponseSize) {
        this.averageResponseSize = averageResponseSize;
    }

    public Map<String, Integer> getTopResources() {
        return topResources;
    }

    public void setTopResources(Map<String, Integer> topResources) {
        this.topResources = topResources;
    }

    public Map<Integer, Integer> getResponseCodeCounts() {
        return responseCodeCounts;
    }

    public void setResponseCodeCounts(Map<Integer, Integer> responseCodeCounts) {
        this.responseCodeCounts = responseCodeCounts;
    }

    @SuppressWarnings("MagicNumber") String getResponseCodeName(int responseCode) {
        return switch (responseCode) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            case 304 -> "Not modified";
            default -> "Unknown";
        };
    }
}
