package by.khorunzhyn.epolsoft.controller;

import by.khorunzhyn.epolsoft.dto.actor.ActorDto;
import by.khorunzhyn.epolsoft.dto.actor.ActorDtoMapper;
import by.khorunzhyn.epolsoft.entity.Actor;
import by.khorunzhyn.epolsoft.service.actor.ActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActorController {

    private static final Logger logger = LoggerFactory.getLogger(ActorController.class);

    @Autowired
    private ActorService service;

    @Autowired
    private ActorDtoMapper actorDtoMapper;

    @GetMapping("/actors")
    public List<ActorDto> getAll() {
        List<Actor> entityList = service.findAll();
        List<ActorDto> dtoList = new ArrayList<>();
        for (Actor entity : entityList) {
            dtoList.add(actorDtoMapper.mapEntityToDto(entity));
        }
        return dtoList;
    }

    @GetMapping("/actor/{id}")
    public ActorDto get(@PathVariable("id") Long id) {
        Actor entity = service.findOne(id).orElse(null);
        return actorDtoMapper.mapEntityToDto(entity);
    }

}
