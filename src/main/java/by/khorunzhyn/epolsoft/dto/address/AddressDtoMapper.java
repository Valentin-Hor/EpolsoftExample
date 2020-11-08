package by.khorunzhyn.epolsoft.dto.address;

import by.khorunzhyn.epolsoft.dto.BaseDtoMapper;
import by.khorunzhyn.epolsoft.entity.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressDtoMapper extends BaseDtoMapper<Address, AddressDto> {

    @Override
    protected AddressDto createDto() {
        return new AddressDto();
    }

    @Override
    protected void fillDto(AddressDto dto, Address entity) {

        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());
        dto.setHouseNumber(entity.getHouseNumber());

    }

    @Override
    protected Address createEntity() {
        return new Address();
    }

    @Override
    protected void fillEntity(Address entity, AddressDto dto) {

        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setHouseNumber(dto.getHouseNumber());

    }
}
