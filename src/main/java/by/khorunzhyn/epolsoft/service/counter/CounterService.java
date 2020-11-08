package by.khorunzhyn.epolsoft.service.counter;

import by.khorunzhyn.epolsoft.dao.CounterRepository;
import by.khorunzhyn.epolsoft.entity.Counter;
import by.khorunzhyn.epolsoft.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService extends BaseServiceImpl<Counter, CounterRepository> {

    @Autowired
    private CounterRepository counterRepository;

    public Counter getByTitle(String title) {
        return getRepository().findByTitle(title);
    }

    @Override
    protected CounterRepository getRepository() {
        return counterRepository;
    }

}
