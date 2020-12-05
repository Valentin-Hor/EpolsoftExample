package by.khorunzhyn.epolsoft.service.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class ParserHelper {

    private static final Logger logger = LoggerFactory.getLogger(ParserHelper.class);

    private final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public LocalDate getLocalDate(String dateString) {

        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
        } catch (DateTimeParseException e) {
            logger.error("Can not parse to LocalDate value = \"" + dateString + "\"");
            throw new RuntimeException(e);
        }
    }
}

