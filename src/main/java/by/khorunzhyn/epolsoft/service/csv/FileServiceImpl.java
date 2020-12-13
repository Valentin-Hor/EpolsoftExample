package by.khorunzhyn.epolsoft.service.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public File getFile(String fileUrl) {

//        logger.info("Loading resource: {}", fileUrl);
        Resource csv = resourceLoader.getResource(fileUrl);

        try {
            return csv.getFile();
        } catch (Exception e) {
            logger.error("Failed getting file");
        }

        return null;
    }

}
