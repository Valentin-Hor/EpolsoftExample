package by.khorunzhyn.epolsoft.service.movie;

import by.khorunzhyn.epolsoft.dao.MovieRepository;
import by.khorunzhyn.epolsoft.entity.Movie;
import by.khorunzhyn.epolsoft.entity.MovieGenre;
import by.khorunzhyn.epolsoft.service.BaseServiceImpl;
import by.khorunzhyn.epolsoft.service.moviegenre.MovieGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService extends BaseServiceImpl<Movie, MovieRepository> {

    @Autowired
    private MovieGenreService movieGenreservice;

    @Autowired
    private MovieRepository repository;

    @Override
    protected MovieRepository getRepository() {
        return repository;
    }

    public Movie create(Movie entity, Long movieGenreId) {
        MovieGenre movieGenre = movieGenreservice.findOne(movieGenreId).orElse(null);
        entity.setMovieGenre(movieGenre);
        return save(entity);
    }

    public Movie update(Long id,String title,Long movieGenreId){
        Optional<Movie> optional = findOne(id);

        if(!optional.isPresent()){
            return null;
        }

        Movie entity = optional.get();
        entity.setTitle(title);

        MovieGenre movieGenre = movieGenreservice.findOne(movieGenreId).orElse(null);
        entity.setMovieGenre(movieGenre);

        return save(entity);
    }



}
