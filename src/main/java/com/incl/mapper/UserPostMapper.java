package com.incl.mapper;

import com.incl.dto.UserPostDTO;
import com.incl.model.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPostMapper {
  
  @Mapping(source = "interest.id", target = "interestId")
  UserPostDTO toDto(PostEntity postEntity);
}
