package by.khorunzhyn.epolsoft.controller;

import by.khorunzhyn.epolsoft.dto.address.AddressDto;
import by.khorunzhyn.epolsoft.dto.address.AddressDtoMapper;
import by.khorunzhyn.epolsoft.entity.Address;
import by.khorunzhyn.epolsoft.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressDtoMapper addressDtoMapper;

    @GetMapping("/address")
    public List<AddressDto> getAll() {
        List<Address> entityList = addressService.findAll();
        List<AddressDto> dtoList = new ArrayList<>();
        for (Address entity : entityList) {
            dtoList.add(addressDtoMapper.mapEntityToDto(entity));
        }
        return dtoList;
    }

    @GetMapping("address/{id}")
    public AddressDto get(@PathVariable("id") Long id) {
        Address entity = addressService.findOne(id).orElse(null);
        return addressDtoMapper.mapEntityToDto(entity);
    }


}
