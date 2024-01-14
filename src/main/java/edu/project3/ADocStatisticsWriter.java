package edu.project3;

import java.util.Map;

public class ADocStatisticsWriter implements StatisticsWriter {
    private LogReport logReport;

    ADocStatisticsWriter(LogReport logReport) {
        this.logReport = logReport;
    }

    public String print() {
        StringBuilder asciiDoc = new StringBuilder();

        asciiDoc.append("==== Общая информация\n\n");
        asciiDoc.append("|        Метрика        |     Значение |\n");
        asciiDoc.append("|:---------------------:|-------------:|\n");
        asciiDoc.append(String.format("|       Файл(-ы)        | `%s` |\n", logReport.getFileName()));
        asciiDoc.append(String.format("|    Начальная дата     |   %s |\n", logReport.getDateFrom()));
        asciiDoc.append(String.format("|     Конечная дата     |   %s |\n", logReport.getDateTo()));
        asciiDoc.append(String.format("|  Количество запросов  |       %d |\n", logReport.getTotalRequests()));
        asciiDoc.append(String.format("| Средний размер ответа |         %db |\n\n",
                logReport.getAverageResponseSize()));

        asciiDoc.append("==== Запрашиваемые ресурсы\n\n");
        asciiDoc.append("|     Ресурс      | Количество |\n");
        asciiDoc.append("|:---------------:|-----------:|\n");

        for (Map.Entry<String, Integer> entry : logReport.getTopResources().entrySet()) {
            asciiDoc.append(String.format("|  `%s`  |      %d |\n", entry.getKey(), entry.getValue()));
        }
        asciiDoc.append("\n");

        asciiDoc.append("==== Коды ответа\n\n");
        asciiDoc.append("| Код |          Имя          | Количество |\n");
        asciiDoc.append("|:---:|:---------------------:|-----------:|\n");

        for (Map.Entry<Integer, Integer> entry : logReport.getResponseCodeCounts().entrySet()) {
            asciiDoc.append(String.format("|  %d |          %s           |       %d |\n", entry.getKey(),
                logReport.getResponseCodeName(entry.getKey()), entry.getValue()
            ));
        }

        return asciiDoc.toString();
    }

}
