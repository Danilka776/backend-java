package edu.project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogAnalyzer {

    private LogAnalyzer() {
    }

    private static String logPathName;
    private static LocalDate dateFrom;
    private static LocalDate dateTo;

    private final static Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings({"MultipleStringLiterals", "RegexpSinglelineJava", "UncommentedMain"})
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("p", "path", true, "Путь к лог-файлам (локальный шаблон или URL)");
        options.addOption("f", "from", true, "Начальная дата в формате ISO8601");
        options.addOption("t", "to", true, "Конечная дата в формате ISO8601");
        options.addOption("fmt", "format", true, "Формат вывода (markdown или adoc)");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            String logPath = cmd.getOptionValue("path");
            logPathName = logPath;
            String fromStr = cmd.getOptionValue("from");
            String toStr = cmd.getOptionValue("to");
            String format = cmd.getOptionValue("format");

            LocalDate fromDate = parseDate(fromStr);
            dateFrom = fromDate;
            LocalDate toDate = parseDate(toStr);
            dateTo = toDate;

            if (logPath != null) {
                if (logPath.startsWith("http")) {
                    analyzeRemoteLog(new URL(logPath), fromDate, toDate, format);
                } else {
                    analyzeLocalLog(logPath, fromDate, toDate, format);
                }
            } else {
                System.out.println("Необходимо указать путь к лог-файлам.");
            }

        } catch (ParseException | IOException e) {
            LOGGER.info(e.getMessage());
        }

    }

    private static LocalDate parseDate(String dateStr) throws ParseException {
        if (dateStr != null) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            return LocalDate.parse(dateStr, dateFormat);
        }
        return null;
    }

    private static LocalDate parseLogDate(String dateStr) throws ParseException {
        if (dateStr != null) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
            return LocalDate.parse(dateStr, dateFormat);
        }
        return null;
    }

    @SuppressWarnings({"MultipleStringLiterals", "RegexpSinglelineJava"})
    private static void analyzeLocalLog(String path, LocalDate fromDate, LocalDate toDate, String format)
        throws IOException, ParseException {
        List<LogRecord> logRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LogRecord logRecord = parseLogRecord(line);
                if (logRecord != null) {
                    LocalDate logRecordDate = logRecord.getDate();
                    if ((fromDate == null || logRecordDate.isAfter(fromDate))
                        && (toDate == null || logRecordDate.isBefore(toDate))) {
                            logRecords.add(logRecord);
                    }
                }
            }
        }

        LogReport logReport = generateReport(logRecords);

        if (format.equalsIgnoreCase("markdown")) {
            System.out.println(logReport.toMarkdown());
        } else if (format.equalsIgnoreCase("adoc")) {
            System.out.println(logReport.toADoc());
        } else {
            System.out.println("Неподдерживаемый формат вывода. Используйте 'markdown' или 'adoc'.");
        }
    }

    @SuppressWarnings({"MultipleStringLiterals", "RegexpSinglelineJava"})
    private static void analyzeRemoteLog(URL url, LocalDate fromDate, LocalDate toDate, String format)
        throws IOException, ParseException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        List<LogRecord> logRecords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LogRecord logRecord = parseLogRecord(line);
                if (logRecord != null) {
                    LocalDate logRecordDate = logRecord.getDate();
                    if ((fromDate == null || logRecordDate.isAfter(fromDate))
                        && (toDate == null || logRecordDate.isBefore(toDate))) {
                        logRecords.add(logRecord);
                    }
                }
            }
        } finally {
            connection.disconnect();
        }

        LogReport logReport = generateReport(logRecords);

        if (format.equalsIgnoreCase("markdown")) {
            System.out.println(logReport.toMarkdown());
        } else if (format.equalsIgnoreCase("adoc")) {
            System.out.println(logReport.toADoc());
        } else {
            System.out.println("Неподдерживаемый формат вывода. Используйте 'markdown' или 'adoc'.");
        }
    }

    private static LogRecord parseLogRecord(String logLine) throws ParseException {
        Pattern logPattern = Pattern.compile(
            "(?<remoteAddr>[^\\s]+) - (?<remoteUser>[^\\s]+) \\["
                + "(?<timeLocal>[^\\]]+)\\] \""
                + "(?<request>[^\\\"]+)\" "
                + "(?<status>\\d+) "
                + "(?<bodyBytesSent>\\d+) \""
                + "(?<httpReferer>[^\\\"]+)\" \""
                + "(?<httpUserAgent>[^\\\"]+)\""
        );
        Matcher matcher = logPattern.matcher(logLine);

        if (matcher.matches()) {
            String remoteAddr = matcher.group("remoteAddr");
            String remoteUser = matcher.group("remoteUser");
            String timeLocalStr = matcher.group("timeLocal");
            String request = matcher.group("request");
            int status = Integer.parseInt(matcher.group("status"));
            int bodyBytesSent = Integer.parseInt(matcher.group("bodyBytesSent"));
            String httpReferer = matcher.group("httpReferer");
            String httpUserAgent = matcher.group("httpUserAgent");

            LocalDate timeLocal = parseLogDate(timeLocalStr);

            return new LogRecord(timeLocal, request, status, bodyBytesSent);
        } else {
            throw new IllegalArgumentException("Invalid log format: " + logLine);
        }
    }

    private static LogReport generateReport(List<LogRecord> logRecords) {
        LogReport report = new LogReport();

        // Общая информация
        report.setFileName(logPathName);
        report.setDateFrom(dateFrom);
        report.setDateTo(dateTo);
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

    private static int calculateAverageResponseSize(List<LogRecord> logRecords) {
        if (logRecords.isEmpty()) {
            return 0;
        }

        int totalSize = 0;
        for (LogRecord rec : logRecords) {
            totalSize += rec.getResponseSize();
        }

        return totalSize / logRecords.size();
    }

    private static Map<String, Integer> calculateResourceCounts(List<LogRecord> logRecords) {
        Map<String, Integer> resourceCounts = new HashMap<>();
        for (LogRecord rec : logRecords) {
            String resource = rec.getRequest();
            resourceCounts.put(resource, resourceCounts.getOrDefault(resource, 0) + 1);
        }
        return resourceCounts;
    }

    private static Map<Integer, Integer> calculateResponseCodeCounts(List<LogRecord> logRecords) {
        Map<Integer, Integer> responseCodeCounts = new HashMap<>();
        for (LogRecord rec : logRecords) {
            int responseCode = rec.getResponseCode();
            responseCodeCounts.put(responseCode, responseCodeCounts.getOrDefault(responseCode, 0) + 1);
        }
        return responseCodeCounts;
    }
}

