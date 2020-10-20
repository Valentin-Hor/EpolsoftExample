package by.khorunzhyn.epolsoft.dao;

import by.khorunzhyn.epolsoft.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends BaseRepository<Movie> {
}
