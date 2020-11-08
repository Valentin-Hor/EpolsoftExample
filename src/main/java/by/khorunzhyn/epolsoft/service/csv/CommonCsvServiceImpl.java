package by.khorunzhyn.epolsoft.service.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
class CommonCsvServiceImpl implements CommonCsvService {

    private static final Logger logger = LoggerFactory.getLogger(CommonCsvServiceImpl.class);

    @Autowired
    private FileService fileService;

    @Override
    public CSVParser getParser(String csvFileUrl) {

        File file = fileService.getFile(csvFileUrl);
        if (file != null) {
            try {
                Reader reader = new FileReader(file);

                logger.info("Start parsing file: {}", file.getName());
                CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT
                        .withDelimiter('/')
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
                logger.info("Finish parsing file: {}", file.getName());

                return parser;
            } catch (FileNotFoundException e) {
                logger.error("File not found: " + file.getName(), e);
            } catch (IOException e) {
                logger.error("Failed parsing file: " + file.getName(), e);
            }
        }
        return null;
    }
}
