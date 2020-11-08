package by.khorunzhyn.epolsoft.service;

import by.khorunzhyn.epolsoft.dao.BaseRepository;
import by.khorunzhyn.epolsoft.entity.BaseEntity;
import by.khorunzhyn.epolsoft.service.csv.CommonCsvService;
import org.apache.commons.csv.CSVParser;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseServiceImpl<E extends BaseEntity, R extends BaseRepository<E>> implements BaseService<E> {

    protected abstract R getRepository();

    @Override
    public void delete(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(E entity) {
        getRepository().delete(entity);
    }

    @Override
    @Transactional
    public Optional<E> findOne(Long id) {
        return getRepository().findById(id);
    }

    @Override
    @Transactional
    public List<E> findAllById(List<Long> ids) {
        return getRepository().findAllById(ids);
    }

    @Override
    public E save(E entity) {
        return getRepository().saveAndFlush(entity);
    }

    @Override
    @Transactional
    public List<E> findAll() {
        return getRepository().findAll();
    }

}
