package by.khorunzhyn.epolsoft.service.filesstorage;


import org.springframework.core.io.Resource;

public interface FilesStorageService {

    public Resource load(String filename);
}
