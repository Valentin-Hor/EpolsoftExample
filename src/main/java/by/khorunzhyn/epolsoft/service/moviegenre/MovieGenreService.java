package by.khorunzhyn.epolsoft.service.moviegenre;

import by.khorunzhyn.epolsoft.dao.MovieGenreRepository;
import by.khorunzhyn.epolsoft.entity.MovieGenre;
import by.khorunzhyn.epolsoft.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieGenreService extends BaseServiceImpl<MovieGenre,MovieGenreRepository> {

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @Override
    protected MovieGenreRepository getRepository(){
        return movieGenreRepository;
    }

}
