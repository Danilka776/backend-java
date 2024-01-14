package edu.project3;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.project3.LogAnalyzer.analyzeLocalLog;
import static edu.project3.LogAnalyzer.analyzeRemoteLog;
import static edu.project3.LogParser.parseDate;

public class Main {

    private Main() {
    }

    static String logPathName;
    static LocalDate dateFrom;
    static LocalDate dateTo;

    private final static Logger LOGGER = LogManager.getLogger();
    public static final String PARSE_DATE_PATTERN = "yyyy-MM-dd";

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

            LocalDate fromDate = parseDate(fromStr, PARSE_DATE_PATTERN);
            dateFrom = fromDate;
            LocalDate toDate = parseDate(toStr, PARSE_DATE_PATTERN);
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
}


//
//==== Общая информация
//
//    |        Метрика        |     Значение |
//    |:---------------------:|-------------:|
//    |       Файл(-ы)        | `https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs` |
//    |    Начальная дата     |   null |
//    |     Конечная дата     |   null |
//    |  Количество запросов  |       51462 |
//    | Средний размер ответа |         -8162b |
//
//    ==== Запрашиваемые ресурсы
//
//    |     Ресурс      | Количество |
//    |:---------------:|-----------:|
//    |  `GET /downloads/product_3 HTTP/1.1`  |      73 |
//    |  `HEAD /downloads/product_2 HTTP/1.1`  |      70 |
//    |  `GET /downloads/product_1 HTTP/1.1`  |      30272 |
//    |  `GET /downloads/product_2 HTTP/1.1`  |      21034 |
//    |  `HEAD /downloads/product_1 HTTP/1.1`  |      13 |
//
//    ==== Коды ответа
//
//    | Код |          Имя          | Количество |
//    |:---:|:---------------------:|-----------:|
//    |  304 |          Not modified           |       13330 |
//    |  416 |          Unknown           |       4 |
//    |  403 |          Unknown           |       38 |
//    |  404 |          Not Found           |       33876 |
//    |  200 |          OK           |       4028 |
//    |  206 |          Unknown           |       186 |
