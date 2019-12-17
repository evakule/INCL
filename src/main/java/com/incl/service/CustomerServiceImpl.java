package com.incl.service;

import com.incl.dto.UserDTO;
import com.incl.mapper.UserMapper;
import com.incl.model.UserEntity;
import com.incl.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserMapper userMapper;
  
  @Override
  public List<UserDTO> getAllUsers() {
    List<UserEntity> userEntityList = userRepository.findAll();
    return userMapper.toDtoList(userEntityList);
  }
  
  @Override
  public UserDTO getUserById(Long id) {
    UserEntity userEntity = userRepository.getOne(id);
    return userMapper.toDto(userEntity);
  }
}
