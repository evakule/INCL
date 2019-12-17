package com.incl.model;

import com.incl.model.status.InterestAreaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "interests")
public class InterestAreaEntity extends BaseEntity {
  @Column(name = "title")
  private String title;
  @Column(name = "interest_area_status")
  @Enumerated(EnumType.STRING)
  private InterestAreaStatus interestAreaStatus;
  
  @OneToMany(
      mappedBy = "interest",
      fetch = FetchType.LAZY,
      cascade = CascadeType.DETACH)
  private List<PostEntity> posts;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "FK_INTEREST_USER",
      joinColumns = {@JoinColumn(name = "interest_id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private List<UserEntity> users;
}
