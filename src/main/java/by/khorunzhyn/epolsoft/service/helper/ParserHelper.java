package by.khorunzhyn.epolsoft.service.helper;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ParserHelper {

    private final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public LocalDate getLocalDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
    }
}
