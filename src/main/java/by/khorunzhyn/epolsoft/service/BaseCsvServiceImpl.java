package by.khorunzhyn.epolsoft.service;

import by.khorunzhyn.epolsoft.dao.BaseRepository;
import by.khorunzhyn.epolsoft.entity.BaseEntity;
import by.khorunzhyn.epolsoft.service.csv.CommonCsvService;
import org.apache.commons.csv.CSVParser;

import java.util.List;

public abstract class BaseCsvServiceImpl<E extends BaseEntity, R extends BaseRepository<E>>
        extends BaseServiceImpl<E,R>
        implements BaseCsvService<E> {

    protected abstract CommonCsvService getCommonCsvService();

    protected abstract String getCsvFileUrl();

    protected abstract List<E> fillAndSave(CSVParser parser);

    @Override
    public List<E> importFromCsv() {
        String csvFileUrl = getCsvFileUrl();
        CSVParser parser = getCommonCsvService().getParser(csvFileUrl);
        return fillAndSave(parser);
    }

}
