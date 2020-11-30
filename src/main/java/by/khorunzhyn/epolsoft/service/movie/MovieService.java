package by.khorunzhyn.epolsoft.service.movie;

import by.khorunzhyn.epolsoft.dao.MovieRepository;
import by.khorunzhyn.epolsoft.entity.Movie;
import by.khorunzhyn.epolsoft.entity.MovieGenre;
import by.khorunzhyn.epolsoft.service.BaseCsvServiceImpl;
import by.khorunzhyn.epolsoft.service.BaseServiceImpl;
import by.khorunzhyn.epolsoft.service.csv.CommonCsvService;
import by.khorunzhyn.epolsoft.service.moviegenre.MovieGenreService;
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
import java.util.Optional;

@Service
public class MovieService extends BaseCsvServiceImpl<Movie, MovieRepository> {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Value("${csv.movie.url}")
    private String csvFileUrl;

    @Autowired
    private CommonCsvService commonCsvService;

    @Autowired
    private MovieGenreService movieGenreservice;

    @Autowired
    private MovieRepository repository;

    @Override
    protected MovieRepository getRepository() {
        return repository;
    }

    @Override
    protected CommonCsvService getCommonCsvService() {
        return commonCsvService;
    }

    @Override
    protected String getCsvFileUrl() {
        return csvFileUrl;
    }

    public Movie getMovieByTitle(String title) {
        return getRepository().findByTitle(title);
    }

    @Override
    @Transactional
    protected List<Movie> fillAndSave(CSVParser parser) {

        List<Movie> savedEntityList = new ArrayList<>();

        if (parser != null) {

//            logger.info("Save Movie from csv to db");
            for (CSVRecord csvRecord : parser) {
                Movie movie = new Movie();
                movie.setTitle(csvRecord.get("title"));
                Long movieGenreId = Long.valueOf(csvRecord.get("movie_genre_id"));

                savedEntityList.add(create(movie, movieGenreId));
            }
        }
        return savedEntityList;
    }

    public Movie create(Movie entity, Long movieGenreId) {
        MovieGenre movieGenre = movieGenreservice.findOne(movieGenreId).orElse(null);
        entity.setMovieGenre(movieGenre);
        return save(entity);
    }

    public Movie update(Long id, String title, Long movieGenreId) {
        Optional<Movie> optional = findOne(id);

        if (!optional.isPresent()) {
            return null;
        }

        Movie entity = optional.get();
        entity.setTitle(title);

        MovieGenre movieGenre = movieGenreservice.findOne(movieGenreId).orElse(null);
        entity.setMovieGenre(movieGenre);

        return save(entity);
    }


}
