package by.khorunzhyn.epolsoft.dao;

import by.khorunzhyn.epolsoft.entity.BaseEntity;
import by.khorunzhyn.epolsoft.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
}
