package by.khorunzhyn.epolsoft.dto.actor;

import by.khorunzhyn.epolsoft.dto.BaseDtoMapper;
import by.khorunzhyn.epolsoft.dto.address.AddressDtoMapper;
import by.khorunzhyn.epolsoft.dto.movie.MovieDto;
import by.khorunzhyn.epolsoft.dto.movie.MovieDtoMapper;
import by.khorunzhyn.epolsoft.entity.Actor;
import by.khorunzhyn.epolsoft.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorDtoMapper extends BaseDtoMapper<Actor, ActorDto> {

    @Autowired
    private AddressDtoMapper addressDtoMapper;

    @Autowired
    private MovieDtoMapper movieDtoMapper;

    @Override
    protected ActorDto createDto() {
        return new ActorDto();
    }

    @Override
    protected void fillDto(ActorDto dto, Actor entity) {

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setGender(entity.getGender());
        dto.setAddress(addressDtoMapper.mapEntityToDto(entity.getAddress()));
        List<Movie> entityList = entity.getMovies();
        List<MovieDto> dtoList = new ArrayList<>();
        for (Movie movie : entityList) {
            dtoList.add(movieDtoMapper.mapEntityToDto(movie));
        }
        dto.setMovieList(dtoList);
    }

    @Override
    protected Actor createEntity() {
        return new Actor();
    }

    @Override
    protected void fillEntity(Actor entity, ActorDto dto) {

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setGender(dto.getGender());
        entity.setAddress(addressDtoMapper.mapDtoToEntity(dto.getAddress()));
        List<MovieDto> dtoList = dto.getMovieList();
        List<Movie> entityList = new ArrayList<>();
        for (MovieDto movieDto : dtoList) {
            entityList.add(movieDtoMapper.mapDtoToEntity(movieDto));
        }
        entity.setMovies(entityList);

    }
}
