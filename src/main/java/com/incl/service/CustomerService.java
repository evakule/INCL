package com.incl.service;

import com.incl.dto.UserDTO;
import com.incl.dto.UserRegistrationDto;
import com.incl.service.exception.InvalidPhoneNumberException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService {
  
  List<UserDTO> getAllUsers();
  
  UserDTO getUserById(Long id);
  
  List<String> getUsersPhonesByCountryAndInterests(
      Long countryId,
      Long interestId
  );
  
  void createUser(UserRegistrationDto newUser) throws InvalidPhoneNumberException;
}
