package by.khorunzhyn.epolsoft.service.update;


import by.khorunzhyn.epolsoft.entity.Actor;
import by.khorunzhyn.epolsoft.entity.Counter;
import by.khorunzhyn.epolsoft.service.actor.ActorService;
import by.khorunzhyn.epolsoft.service.counter.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateActorService {

    private static final Logger logger = LoggerFactory.getLogger(ActorService.class);

    private final String ACTOR_COUNTER_TITLE = "READ_ACTOR_COUNTER";

    @Autowired
    private CounterService counterService;

    @Autowired
    private ActorService actorService;

    @Scheduled(initialDelay = 15000, fixedDelay = 2000)
    public void updateActorsFromDB() {
        List<Actor> actorList = actorService.findAll();
        Counter importCounter = counterService.getByTitle(ACTOR_COUNTER_TITLE);

        Long offset = importCounter.getCounterOffset();
        Long count = importCounter.getCount();
        if(offset<actorList.size()) {
            logger.info("Update Actor records from db with Id from {} to {} ", offset + 1, offset + count);
        }
        for (int i = 1; i <= count; i++) {
            Optional<Actor> actorOptional = actorService.findOne(offset + i);

            if (actorOptional.isPresent()) {

                logger.info("Swap the firstName with the lastName in Actor's record with Id = {}", offset + i);
                Actor foundActor = actorOptional.get();
                String temp = foundActor.getFirstName();
                foundActor.setFirstName(foundActor.getLastName());
                foundActor.setLastName(temp);
                actorService.save(foundActor);
            }
        }

        offset += count;
        if (offset <= actorList.size()) {
            importCounter.setCounterOffset(offset);
            counterService.save(importCounter);
        }
    }

}
