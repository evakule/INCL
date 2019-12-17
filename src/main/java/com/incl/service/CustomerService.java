package com.incl.service;

import com.incl.dto.UserDTO;

import java.util.List;

public interface CustomerService {
  
  List<UserDTO> getAllUsers();
  
  UserDTO getUserById(Long id);
}
