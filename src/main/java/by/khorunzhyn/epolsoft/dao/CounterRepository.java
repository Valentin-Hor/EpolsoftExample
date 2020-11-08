package by.khorunzhyn.epolsoft.dao;

import by.khorunzhyn.epolsoft.entity.Counter;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends BaseRepository<Counter> {
    Counter findByTitle(String title);
}
