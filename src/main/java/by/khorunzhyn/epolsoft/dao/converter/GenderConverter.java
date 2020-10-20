package by.khorunzhyn.epolsoft.dao.converter;

import by.khorunzhyn.epolsoft.data.Gender;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender,String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
       if(attribute==null){
           return null;
       }
       return attribute.name();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData==null){
            return null;
        }
        return Gender.valueOf(dbData);
    }
}
