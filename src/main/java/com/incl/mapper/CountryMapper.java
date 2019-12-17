package com.incl.mapper;

import com.incl.dto.CountryDTO;
import com.incl.model.CountryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
  
  CountryDTO toDto(CountryEntity countryEntity);
  
  List<CountryDTO> toDtoList(List<CountryEntity> countryEntityList);
}
