package com.incl.dto;

import com.incl.model.status.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private Long id;
  private String name;
  private String encryptedPassword;
  private String email;
  private String phone;
  @Enumerated(EnumType.STRING)
  private CustomerStatus customerStatus;
  private Long countryId;
  private List<UserPostDTO> posts;
  private Set<RoleDTO> roles;
  private List<UserInterestAreaDTO> interests;
}
