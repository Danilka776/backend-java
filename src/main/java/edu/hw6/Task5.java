package edu.hw6;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task5 {
    private Task5() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static class HackerNews {

        private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
        private static final String ITEM_URL_FORMAT = "https://hacker-news.firebaseio.com/v0/item/%d.json";
        private static final int RESPONSE_CODE = 200;

        private final HttpClient httpClient;

        public HackerNews() {
            this.httpClient = HttpClient.newHttpClient();
        }

        public long[] hackerNewsTopStories() {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TOP_STORIES_URL))
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == RESPONSE_CODE) {
                    String[] items = response.body().replaceAll("[\\[\\]\"]", "").split(",");
                    long[] result = new long[items.length];
                    for (int i = 0; i < items.length; i++) {
                        result[i] = Long.parseLong(items[i]);
                    }
                    return result;
                }
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
            return new long[0];
        }

        public String news(long id) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format(ITEM_URL_FORMAT, id)))
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == RESPONSE_CODE) {
                    Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
                    Matcher matcher = pattern.matcher(response.body());
                    if (matcher.find()) {
                        return matcher.group(1);
                    }
                }
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
            return "Unknown News";
        }

    }

}
