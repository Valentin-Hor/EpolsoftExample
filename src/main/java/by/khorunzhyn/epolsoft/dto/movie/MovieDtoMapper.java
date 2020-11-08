package by.khorunzhyn.epolsoft.dto.movie;

import by.khorunzhyn.epolsoft.dto.BaseDtoMapper;
import by.khorunzhyn.epolsoft.entity.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieDtoMapper extends BaseDtoMapper<Movie, MovieDto> {

    @Override
    protected MovieDto createDto() {
        return new MovieDto();
    }

    @Override
    protected void fillDto(MovieDto dto, Movie entity) {
        dto.setTitle(entity.getTitle());
        dto.setMovieGenre(entity.getMovieGenre());
    }

    @Override
    protected Movie createEntity() {
        return new Movie();
    }

    @Override
    protected void fillEntity(Movie entity, MovieDto dto) {
        entity.setTitle(dto.getTitle());
        entity.setMovieGenre(dto.getMovieGenre());
    }
}
