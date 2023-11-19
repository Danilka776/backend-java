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

    @SuppressWarnings("MultipleStringLiterals")
    public String toMarkdown() {
        StringBuilder markdown = new StringBuilder();

        markdown.append("#### Общая информация\n\n");
        markdown.append("|        Метрика        |     Значение |\n");
        markdown.append("|:---------------------:|-------------:|\n");
        markdown.append(String.format("|       Файл(-ы)        | `%s` |\n", fileName));
        markdown.append(String.format("|    Начальная дата     |   %s |\n", dateFrom));
        markdown.append(String.format("|     Конечная дата     |   %s |\n", dateTo));
        markdown.append(String.format("|  Количество запросов  |       %d |\n", totalRequests));
        markdown.append(String.format("| Средний размер ответа |         %db |\n\n", averageResponseSize));

        markdown.append("#### Запрашиваемые ресурсы\n\n");
        markdown.append("|     Ресурс      | Количество |\n");
        markdown.append("|:---------------:|-----------:|\n");

        for (Map.Entry<String, Integer> entry : topResources.entrySet()) {
            markdown.append(String.format("|  `%s`  |      %d |\n", entry.getKey(), entry.getValue()));
        }
        markdown.append("\n");

        markdown.append("#### Коды ответа\n\n");
        markdown.append("| Код |          Имя          | Количество |\n");
        markdown.append("|:---:|:---------------------:|-----------:|\n");

        for (Map.Entry<Integer, Integer> entry : responseCodeCounts.entrySet()) {
            markdown.append(String.format("|  %d |          %s           |       %d |\n", entry.getKey(),
                getResponseCodeName(entry.getKey()), entry.getValue()
            ));
        }

        return markdown.toString();
    }

    public String toADoc() {
        StringBuilder asciiDoc = new StringBuilder();

        asciiDoc.append("==== Общая информация\n\n");
        asciiDoc.append("|        Метрика        |     Значение |\n");
        asciiDoc.append("|:---------------------:|-------------:|\n");
        asciiDoc.append(String.format("|       Файл(-ы)        | `%s` |\n", fileName));
        asciiDoc.append(String.format("|    Начальная дата     |   %s |\n", dateFrom));
        asciiDoc.append(String.format("|     Конечная дата     |   %s |\n", dateTo));
        asciiDoc.append(String.format("|  Количество запросов  |       %d |\n", totalRequests));
        asciiDoc.append(String.format("| Средний размер ответа |         %db |\n\n", averageResponseSize));

        asciiDoc.append("==== Запрашиваемые ресурсы\n\n");
        asciiDoc.append("|     Ресурс      | Количество |\n");
        asciiDoc.append("|:---------------:|-----------:|\n");

        for (Map.Entry<String, Integer> entry : topResources.entrySet()) {
            asciiDoc.append(String.format("|  `%s`  |      %d |\n", entry.getKey(), entry.getValue()));
        }
        asciiDoc.append("\n");

        asciiDoc.append("==== Коды ответа\n\n");
        asciiDoc.append("| Код |          Имя          | Количество |\n");
        asciiDoc.append("|:---:|:---------------------:|-----------:|\n");

        for (Map.Entry<Integer, Integer> entry : responseCodeCounts.entrySet()) {
            asciiDoc.append(String.format("|  %d |          %s           |       %d |\n", entry.getKey(),
                getResponseCodeName(entry.getKey()), entry.getValue()
            ));
        }

        return asciiDoc.toString();
    }

    @SuppressWarnings("MagicNumber")
    private String getResponseCodeName(int responseCode) {
        return switch (responseCode) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            // Добавьте другие коды ответа по необходимости
            default -> "Unknown";
        };
    }
}
