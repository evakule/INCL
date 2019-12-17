package com.incl.mapper;

import com.incl.dto.UserInterestAreaDTO;
import com.incl.model.InterestAreaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserInterestAreaMapper {
  
  UserInterestAreaDTO toDto(InterestAreaEntity interestAreaEntity);
  
  List<UserInterestAreaDTO> toDtoList(List<InterestAreaEntity> interestAreaEntities);
}
