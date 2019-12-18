package com.incl.mapper;

import com.incl.dto.UserRegistrationDto;
import com.incl.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {
  
  UserEntity toEntity(UserRegistrationDto newUser);
}
