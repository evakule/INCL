package com.incl.service;

import com.incl.dto.CountryDTO;

import java.util.List;

public interface CountryService {
  List<CountryDTO> getAllCountries();
  
  CountryDTO getCountryById(Long id);
}
