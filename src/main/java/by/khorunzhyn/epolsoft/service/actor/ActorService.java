package by.khorunzhyn.epolsoft.service.actor;

import by.khorunzhyn.epolsoft.dao.ActorRepository;
import by.khorunzhyn.epolsoft.entity.Actor;
import by.khorunzhyn.epolsoft.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService extends BaseServiceImpl<Actor,ActorRepository> {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    protected ActorRepository getRepository(){
        return actorRepository;
    }
}
