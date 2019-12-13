package com.incl.mapper;

import com.incl.dto.UserCountryDTO;
import com.incl.model.CountryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
  
  UserCountryDTO toDto(CountryEntity countryEntity);
}
