package edu.project3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.ParseException;
import static edu.project3.LogParser.parseLogRecord;
import static edu.project3.ReportGenerator.generateReport;

public class LogAnalyzer {

    private LogAnalyzer() {
    }

    @SuppressWarnings({"MultipleStringLiterals", "RegexpSinglelineJava"})
    public static void analyzeLocalLog(String path, LocalDate fromDate, LocalDate toDate, String format)
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
        printFormattedLog(format, logReport);
    }


    @SuppressWarnings({"MultipleStringLiterals", "RegexpSinglelineJava"})
    public static void analyzeRemoteLog(URL url, LocalDate fromDate, LocalDate toDate, String format)
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
        printFormattedLog(format, logReport);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private static void printFormattedLog(String format, LogReport logReport) {
        String data = "";
        if (format.equalsIgnoreCase("markdown")) {
            StatisticsWriter toMarkdown = new MDStatisticsWriter(logReport);
            data = toMarkdown.print();
        } else if (format.equalsIgnoreCase("adoc")) {
            StatisticsWriter toADoc = new ADocStatisticsWriter(logReport);
            data = toADoc.print();
        } else {
            System.out.println("Неподдерживаемый формат вывода. Используйте 'markdown' или 'adoc'.");
            System.exit(0);
        }

        String filePath = "src/main/java/edu/project3/Statistic.txt";
        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл: " + e.getMessage());
        }

    }

}
