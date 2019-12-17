package com.incl.mapper;

import com.incl.dto.PostDTO;
import com.incl.model.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "interest.id", target = "interestAreaId")
  PostDTO toDto(PostEntity postEntity);
  
  @Mapping(source = "userId", target = "user.id")
  @Mapping(source = "interestAreaId", target = "interest.id")
  PostEntity toEntity(PostDTO postDTO);
  
  List<PostDTO> toDtoList(List<PostEntity> postEntities);
}
