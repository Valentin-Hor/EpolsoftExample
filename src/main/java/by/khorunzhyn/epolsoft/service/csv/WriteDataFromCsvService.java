package by.khorunzhyn.epolsoft.service.csv;

import by.khorunzhyn.epolsoft.service.actor.ActorService;
import by.khorunzhyn.epolsoft.service.movie.MovieService;
import by.khorunzhyn.epolsoft.service.moviegenre.MovieGenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WriteDataFromCsvService {

    private static final Logger logger = LoggerFactory.getLogger(WriteDataFromCsvService.class);

    @Autowired
    private ActorService actorService;

    @Autowired
    private MovieGenreService movieGenreService;

    @Autowired
    private MovieService movieService;


    @Scheduled(fixedDelay = 1000000)
    public void fillDbWithMovieGenreAndMovie() {
        logger.info("Start filling DataBase with Movie and MovieGenre from CSV files");
        movieGenreService.importFromCsv();
        movieService.importFromCsv();
        logger.info("Reading and saving data is complete");

    }

    @Scheduled(fixedDelay = 1000)
    public void fillDbWithActor() {
        logger.info("Start filling DataBase Actor from CSV files");
        actorService.importFromCsv();
        logger.info("Reading and saving data is complete");
    }

}
