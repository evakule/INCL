package com.incl.mapper;

import com.incl.dto.RoleDTO;
import com.incl.model.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
  RoleDTO toDTO(RoleEntity roleEntity);
}
