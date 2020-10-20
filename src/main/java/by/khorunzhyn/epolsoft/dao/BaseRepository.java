package by.khorunzhyn.epolsoft.dao;

import by.khorunzhyn.epolsoft.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E,Long> {
}
