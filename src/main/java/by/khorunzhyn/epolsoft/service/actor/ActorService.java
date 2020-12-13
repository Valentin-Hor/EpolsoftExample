package by.khorunzhyn.epolsoft.service.actor;

import by.khorunzhyn.epolsoft.dao.ActorRepository;
import by.khorunzhyn.epolsoft.data.Gender;
import by.khorunzhyn.epolsoft.entity.Actor;
import by.khorunzhyn.epolsoft.entity.Address;
import by.khorunzhyn.epolsoft.entity.Counter;
import by.khorunzhyn.epolsoft.entity.Movie;
import by.khorunzhyn.epolsoft.service.BaseCsvServiceImpl;
import by.khorunzhyn.epolsoft.service.counter.CounterService;
import by.khorunzhyn.epolsoft.service.csv.CommonCsvService;
import by.khorunzhyn.epolsoft.service.helper.ParserHelper;
import by.khorunzhyn.epolsoft.service.movie.MovieService;
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
public class ActorService extends BaseCsvServiceImpl<Actor, ActorRepository> {

    private static final Logger logger = LoggerFactory.getLogger(ActorService.class);

    private final String ACTOR_COUNTER_TITLE = "WRITE_ACTOR_COUNTER";

    @Value("${csv.actor.url}")
    private String csvFileUrl;

    @Autowired
    private CommonCsvService commonCsvService;

    @Autowired
    private CounterService counterService;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ParserHelper parserHelper;

    @Autowired
    private MovieService movieService;

    @Override
    protected ActorRepository getRepository() {
        return actorRepository;
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
    protected List<Actor> fillAndSave(CSVParser parser) {

        List<Actor> savedEntityList = new ArrayList<>();

        if (parser != null) {
            try {
                List<CSVRecord> recordList = parser.getRecords();
                Counter importCounter = counterService.getByTitle(ACTOR_COUNTER_TITLE);
                Long offset = importCounter.getCounterOffset();
                Long count = importCounter.getCount();
                if (offset < recordList.size()) {
                    logger.info("Save Actor records from csv to db with Id  from {} to {}", offset + 1, offset + count);
                    savedEntityList = saveActors(recordList, count, offset);
                    changeCounter(importCounter, offset + count);
                }
            } catch (Exception e) {
                logger.error("Failed get records from csv file");
            }

        }
        return savedEntityList;
    }

    private List<Actor> saveActors(List<CSVRecord> recordList, Long count, Long offset) {
        List<Actor> savedEntityList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            CSVRecord csvRecord = recordList.get((int) (offset + i));
            try {
                Actor actor = new Actor();
                actor.setFirstName(csvRecord.get("first_name"));
                actor.setLastName(csvRecord.get("last_name"));
                actor.setDateOfBirth(parserHelper.getLocalDate(csvRecord.get("date_of_birth")));
                actor.setGender(Gender.valueOf(csvRecord.get("gender")));

                Address address = new Address();
                address.setCountry(csvRecord.get("country"));
                address.setCity(csvRecord.get("city"));
                address.setStreet(csvRecord.get("street"));
                address.setHouseNumber(Integer.parseInt(csvRecord.get("house_number")));
                actor.setAddress(address);

                String movieListAsString = csvRecord.get("movies");
                String[] movies = movieListAsString.split(",");
                List<Movie> movieList = new ArrayList<>();
                for (String movieTitle : movies) {
                    Movie movie = movieService.getMovieByTitle(movieTitle);
                    movieList.add(movie);
                }
                actor.setMovies(movieList);

                savedEntityList.add(save(actor));

            } catch (Exception e) {
                logger.error("Record №" +csvRecord.getRecordNumber() + " with VALUE = " + csvRecord.toMap().toString() + " is incorrect");
            }

        }
        return savedEntityList;
    }

    private Counter changeCounter(Counter counter, Long newOffset) {
        counter.setCounterOffset(newOffset);
        return counterService.save(counter);
    }


}
