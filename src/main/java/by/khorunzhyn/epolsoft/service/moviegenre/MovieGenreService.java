package by.khorunzhyn.epolsoft.service.moviegenre;

import by.khorunzhyn.epolsoft.dao.MovieGenreRepository;
import by.khorunzhyn.epolsoft.entity.MovieGenre;
import by.khorunzhyn.epolsoft.service.BaseCsvServiceImpl;
import by.khorunzhyn.epolsoft.service.BaseServiceImpl;
import by.khorunzhyn.epolsoft.service.csv.CommonCsvService;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieGenreService extends BaseCsvServiceImpl<MovieGenre, MovieGenreRepository> {

    private static final Logger logger = LoggerFactory.getLogger(MovieGenreService.class);

    @Value("${csv.movie.genre.url}")
    private String csvFileUrl;

    @Autowired
    private CommonCsvService commonCsvService;

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @Override
    protected MovieGenreRepository getRepository() {
        return movieGenreRepository;
    }

    @Override
    protected CommonCsvService getCommonCsvService() {
        return commonCsvService;
    }

    @Override
    protected String getCsvFileUrl() {
        return csvFileUrl;
    }

    @Override
    @Transactional
    protected List<MovieGenre> fillAndSave(CSVParser parser) {

        List<MovieGenre> savedEntityList = new ArrayList<>();

        if (parser != null) {


            for (CSVRecord csvRecord : parser) {
                try {
                    MovieGenre movieGenre = new MovieGenre();
                    movieGenre.setTitle(csvRecord.get("title"));

                    savedEntityList.add(save(movieGenre));
                }catch (Exception e){
                    logger.error("Record №" + csvRecord.getRecordNumber() + " with VALUE = " + csvRecord.toMap().toString() + " is incorrect");
                }
            }
        }
        return savedEntityList;
    }

}
