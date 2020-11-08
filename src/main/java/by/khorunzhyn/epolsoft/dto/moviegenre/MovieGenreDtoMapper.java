package by.khorunzhyn.epolsoft.dto.moviegenre;

import by.khorunzhyn.epolsoft.dto.BaseDtoMapper;
import by.khorunzhyn.epolsoft.entity.MovieGenre;
import org.springframework.stereotype.Service;

@Service
public class MovieGenreDtoMapper extends BaseDtoMapper<MovieGenre, MovieGenreDto> {


    @Override
    protected MovieGenreDto createDto() {
        return new MovieGenreDto();
    }

    @Override
    protected void fillDto(MovieGenreDto dto, MovieGenre entity) {
        dto.setTitle(entity.getTitle());
    }

    @Override
    protected MovieGenre createEntity() {
        return new MovieGenre();
    }

    @Override
    protected void fillEntity(MovieGenre entity, MovieGenreDto dto) {
        entity.setTitle(dto.getTitle());
    }
}
