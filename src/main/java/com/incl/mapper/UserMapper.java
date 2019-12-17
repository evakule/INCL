package com.incl.mapper;

import com.incl.dto.UserDTO;
import com.incl.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  
  @Mapping(source = "country.id", target = "countryId")
  UserDTO toDto(UserEntity userEntity);
  
  List<UserDTO> toDtoList(List<UserEntity> userEntityList);
}
