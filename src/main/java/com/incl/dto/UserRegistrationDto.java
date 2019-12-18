package com.incl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
  @NotNull
  private String name;
  @NotNull
  private String encryptedPassword;
  @Email
  @NotNull
  private String email;
  @NotNull
  @Size(min = 9, max = 14)
  @Pattern(regexp="^\\+(?:[0-9] ?){6,14}[0-9]$")
  private String phone;
}
