package edu.project3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.ParseException;

public class LogParser {

    private LogParser() {
    }

    public static final String PARSE_LOG_DATE_PATTERN = "dd/MMM/yyyy:HH:mm:ss Z";

    public static LogRecord parseLogRecord(String logLine) throws ParseException {
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

            LocalDate timeLocal = parseDate(timeLocalStr, PARSE_LOG_DATE_PATTERN);

            return new LogRecord(timeLocal, request, status, bodyBytesSent);
        } else {
            throw new IllegalArgumentException("Invalid log format: " + logLine);
        }
    }

    static LocalDate parseDate(String dateStr, String pattern) throws ParseException {
        if (dateStr != null) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
            return LocalDate.parse(dateStr, dateFormat);
        }
        return null;
    }

}
