package by.khorunzhyn.epolsoft.service.csv;

import by.khorunzhyn.epolsoft.service.actor.ActorService;
import by.khorunzhyn.epolsoft.service.movie.MovieService;
import by.khorunzhyn.epolsoft.service.moviegenre.MovieGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WriteDataFromCsvService {


    @Autowired
    private ActorService actorService;

    @Autowired
    private MovieGenreService movieGenreService;

    @Autowired
    private MovieService movieService;


    @Scheduled(fixedDelay = 1000000)
    public void fillDbWithMovieGenreAndMovie() {
        movieGenreService.importFromCsv();
        movieService.importFromCsv();

    }

    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    public void fillDbWithActor() {
        actorService.importFromCsv();
    }

}
