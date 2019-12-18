package com.incl.controller;

import com.incl.dto.UserDTO;
import com.incl.dto.UserRegistrationDto;
import com.incl.service.CustomerService;
import com.incl.service.exception.InvalidPhoneNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/customers")
public class CustomerController {
  @Autowired
  private CustomerService customerService;
  
  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllCustomers() {
    List<UserDTO> userDTOList = customerService.getAllUsers();
    if (CollectionUtils.isEmpty(userDTOList)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }
  }
  
  @GetMapping(value = "{id}")
  public ResponseEntity<UserDTO> getCustomer(
      final @PathVariable("id") Long id
  ) {
    UserDTO userDTO = customerService.getUserById(id);
    if (userDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
  }
  
  @PostMapping
  public ResponseEntity<?> registration(
      final @RequestBody @Valid UserRegistrationDto newUser
      ) {
    if (newUser == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      try {
        customerService.createUser(newUser);
      } catch (InvalidPhoneNumberException e) {
        return new ResponseEntity<>(
            e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
  }
}
