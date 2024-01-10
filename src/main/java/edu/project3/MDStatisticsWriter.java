package edu.project3;

import java.util.Map;

public class MDStatisticsWriter implements StatisticsWriter {

    private LogReport logReport;

    MDStatisticsWriter(LogReport logReport) {
        this.logReport = logReport;
    }

    @SuppressWarnings("MultipleStringLiterals")
    public String print() {
        StringBuilder markdown = new StringBuilder();

        markdown.append("#### Общая информация\n\n");
        markdown.append("|        Метрика        |     Значение |\n");
        markdown.append("|:---------------------:|-------------:|\n");
        markdown.append(String.format("|       Файл(-ы)        | `%s` |\n", logReport.getFileName()));
        markdown.append(String.format("|    Начальная дата     |   %s |\n", logReport.getDateFrom()));
        markdown.append(String.format("|     Конечная дата     |   %s |\n", logReport.getDateTo()));
        markdown.append(String.format("|  Количество запросов  |       %d |\n", logReport.getTotalRequests()));
        markdown.append(String.format("| Средний размер ответа |         %db |\n\n",
                logReport.getAverageResponseSize()));

        markdown.append("#### Запрашиваемые ресурсы\n\n");
        markdown.append("|     Ресурс      | Количество |\n");
        markdown.append("|:---------------:|-----------:|\n");

        for (Map.Entry<String, Integer> entry : logReport.getTopResources().entrySet()) {
            markdown.append(String.format("|  `%s`  |      %d |\n", entry.getKey(), entry.getValue()));
        }
        markdown.append("\n");

        markdown.append("#### Коды ответа\n\n");
        markdown.append("| Код |          Имя          | Количество |\n");
        markdown.append("|:---:|:---------------------:|-----------:|\n");

        for (Map.Entry<Integer, Integer> entry : logReport.getResponseCodeCounts().entrySet()) {
            markdown.append(String.format("|  %d |          %s           |       %d |\n", entry.getKey(),
                logReport.getResponseCodeName(entry.getKey()), entry.getValue()
            ));
        }

        return markdown.toString();
    }
}
