package com.incl.controller;

import com.incl.dto.CountryDTO;
import com.incl.service.CountryService;
import com.incl.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/countries")
public class CountryController {
  
  @Autowired
  private CountryService countryService;
  
  @Autowired
  private CustomerService customerService;
  
  @GetMapping
  public ResponseEntity<List<CountryDTO>> getAllCountries() {
    List<CountryDTO> countryDTOList = countryService.getAllCountries();
    if (CollectionUtils.isEmpty(countryDTOList)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(countryDTOList, HttpStatus.OK);
    }
  }
  
  @GetMapping(value = "{id}")
  public ResponseEntity<CountryDTO> getCountry(
      final @PathVariable("id") Long id
  ) {
    CountryDTO countryDTO = countryService.getCountryById(id);
    if (countryDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(countryDTO, HttpStatus.OK);
    }
  }
  
  @GetMapping(value = "{id}/phones", params = {"interest"})
  public ResponseEntity<List<String>> getUsersPhonesByCountryAndInterest(
      final @PathVariable("id") Long countryId,
      final @PathParam("interest") Long interest
  ) {
    List<String> usersPhoneList =
        customerService.getUsersPhonesByCountryAndInterests(
            countryId, interest);
    if (CollectionUtils.isEmpty(usersPhoneList)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(usersPhoneList, HttpStatus.OK);
    }
  }
}
