package by.khorunzhyn.epolsoft.service.csv;

import org.apache.commons.csv.CSVParser;

public interface CommonCsvService {

    CSVParser getParser(String csvFileUrl);
}
