package com.incl.service;

import com.incl.dto.CountryDTO;
import com.incl.mapper.CountryMapper;
import com.incl.model.CountryEntity;
import com.incl.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class CountryServiceImpl implements CountryService {
  
  @Autowired
  private CountryRepository countryRepository;
  
  @Autowired
  private CountryMapper countryMapper;
  
  @Override
  public List<CountryDTO> getAllCountries() {
    List<CountryEntity> countryEntityList = countryRepository.findAll();
    return countryMapper.toDtoList(countryEntityList);
  }
  
  @Override
  public CountryDTO getCountryById(final Long id) {
    CountryEntity countryEntity = countryRepository.getOne(id);
    return countryMapper.toDto(countryEntity);
  }
}
