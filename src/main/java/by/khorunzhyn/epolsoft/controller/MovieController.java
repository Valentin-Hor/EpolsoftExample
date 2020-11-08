package by.khorunzhyn.epolsoft.controller;

import by.khorunzhyn.epolsoft.dto.movie.MovieDto;
import by.khorunzhyn.epolsoft.dto.movie.MovieDtoMapper;
import by.khorunzhyn.epolsoft.entity.Movie;
import by.khorunzhyn.epolsoft.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService service;

    @Autowired
    private MovieDtoMapper movieDtoMapper;

    @GetMapping("/movies")
    public List<MovieDto> getAll() {
        List<Movie> entityList = service.findAll();
        List<MovieDto> dtoList = new ArrayList<>();
        for (Movie entity : entityList) {
            dtoList.add(movieDtoMapper.mapEntityToDto(entity));
        }
        return dtoList;
    }

    @GetMapping("/movies/{id}")
    public MovieDto get(@PathVariable("id") Long id) {
        Movie entity = service.findOne(id).orElse(null);
        return movieDtoMapper.mapEntityToDto(entity);
    }

}
