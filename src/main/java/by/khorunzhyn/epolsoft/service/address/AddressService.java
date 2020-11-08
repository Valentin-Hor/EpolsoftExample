package by.khorunzhyn.epolsoft.service.address;

import by.khorunzhyn.epolsoft.dao.AddressRepository;
import by.khorunzhyn.epolsoft.entity.Address;
import by.khorunzhyn.epolsoft.service.BaseCsvServiceImpl;
import by.khorunzhyn.epolsoft.service.BaseServiceImpl;
import by.khorunzhyn.epolsoft.service.csv.CommonCsvService;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService extends BaseCsvServiceImpl<Address, AddressRepository> {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Value("${csv.address.url}")
    private String csvFileUrl;

    @Autowired
    private CommonCsvService commonCsvService;


    @Autowired
    private AddressRepository addressRepository;

    @Override
    protected AddressRepository getRepository() {
        return addressRepository;
    }

    @Override
    protected CommonCsvService getCommonCsvService() {
        return commonCsvService;
    }

    @Override
    protected String getCsvFileUrl() {
        return csvFileUrl;
    }

    @Override
    @Transactional
    protected List<Address> fillAndSave(CSVParser parser) {
        List<Address> savedEntityList = new ArrayList<>();
        if (parser != null) {

            logger.info("Save Address from csv to db");
            for (CSVRecord csvRecord : parser) {
                Address address = new Address();
                address.setCountry(csvRecord.get("country"));
                address.setCity(csvRecord.get("city"));
                address.setStreet(csvRecord.get("street"));
                address.setHouseNumber(Integer.parseInt(csvRecord.get("house_number")));

                savedEntityList.add(save(address));
            }
        }

        return savedEntityList;
    }
}
