package com.incl.dto;

import com.incl.model.status.InterestAreaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInterestAreaDTO {
  private Long id;
  private String title;
  @Enumerated(EnumType.STRING)
  private InterestAreaStatus interestAreaStatus;
}
