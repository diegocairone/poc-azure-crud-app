package com.cairone.poc.core.mappers;

import com.cairone.poc.core.model.CityModel;
import com.cairone.poc.data.entities.CityEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityMapper implements Converter<CityEntity, CityModel> {

    @Override
    public CityModel convert(CityEntity source) {
        return CityModel.builder()
                .setId(source.getId())
                .setName(source.getName())
                .build();
    }
}
