package com.incl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
  private Long id;
  @NotNull
  private String title;
  @NotNull
  private String description;
  @NotNull
  private Long interestAreaId;
  private CountryDTO country;
  @NotNull
  private Long userId;
}
