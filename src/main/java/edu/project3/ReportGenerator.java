package edu.project3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    private ReportGenerator() {
    }

    public static LogReport generateReport(List<LogRecord> logRecords) {
        LogReport report = new LogReport();

        // Общая информация
        report.setFileName(Main.logPathName);
        report.setDateFrom(Main.dateFrom);
        report.setDateTo(Main.dateTo);
        report.setTotalRequests(logRecords.size());
        report.setAverageResponseSize(calculateAverageResponseSize(logRecords));

        // Запрашиваемые ресурсы
        Map<String, Integer> resourceCounts = calculateResourceCounts(logRecords);
        report.setTopResources(resourceCounts);

        // Коды ответа
        Map<Integer, Integer> responseCodeCounts = calculateResponseCodeCounts(logRecords);
        report.setResponseCodeCounts(responseCodeCounts);

        return report;
    }

    public static int calculateAverageResponseSize(List<LogRecord> logRecords) {
        if (logRecords.isEmpty()) {
            return 0;
        }

        int totalSize = 0;
        for (LogRecord rec : logRecords) {
            totalSize += rec.getResponseSize();
        }

        return totalSize / logRecords.size();
    }

    public static Map<String, Integer> calculateResourceCounts(List<LogRecord> logRecords) {
        Map<String, Integer> resourceCounts = new HashMap<>();
        for (LogRecord rec : logRecords) {
            String resource = rec.getRequest();
            resourceCounts.put(resource, resourceCounts.getOrDefault(resource, 0) + 1);
        }
        return resourceCounts;
    }

    public static Map<Integer, Integer> calculateResponseCodeCounts(List<LogRecord> logRecords) {
        Map<Integer, Integer> responseCodeCounts = new HashMap<>();
        for (LogRecord rec : logRecords) {
            int responseCode = rec.getResponseCode();
            responseCodeCounts.put(responseCode, responseCodeCounts.getOrDefault(responseCode, 0) + 1);
        }
        return responseCodeCounts;
    }

}
