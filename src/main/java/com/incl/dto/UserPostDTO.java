package com.incl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDTO {
  private Long id;
  private String title;
  private String description;
  private Long interestId;
}
