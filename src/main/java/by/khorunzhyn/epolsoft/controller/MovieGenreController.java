package by.khorunzhyn.epolsoft.controller;

import by.khorunzhyn.epolsoft.dto.moviegenre.MovieGenreDto;
import by.khorunzhyn.epolsoft.dto.moviegenre.MovieGenreDtoMapper;
import by.khorunzhyn.epolsoft.entity.MovieGenre;
import by.khorunzhyn.epolsoft.service.moviegenre.MovieGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieGenreController {

    @Autowired
    private MovieGenreService service;

    @Autowired
    private MovieGenreDtoMapper dtoMapper;

    @GetMapping("/moviegenres")
    public List<MovieGenreDto> getAll(){
        List<MovieGenre> entityList = service.findAll();
        List<MovieGenreDto> dtoList = new ArrayList<>();
        for (MovieGenre entity: entityList){
            dtoList.add(dtoMapper.mapEntityToDto(entity));
        }
        return dtoList;
    }

    @GetMapping("/moviegenres/id")
    public MovieGenreDto get(@PathVariable("id") Long id){
        MovieGenre entity = service.findOne(id).orElse(null);
        return dtoMapper.mapEntityToDto(entity);
    }

}
