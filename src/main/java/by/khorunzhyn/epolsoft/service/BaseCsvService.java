package by.khorunzhyn.epolsoft.service;

import by.khorunzhyn.epolsoft.entity.BaseEntity;

import java.util.List;

public interface BaseCsvService<T extends BaseEntity> extends BaseService<T> {

    List<T> importFromCsv();

}
