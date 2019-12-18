package com.incl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "countries")
public class CountryEntity extends BaseEntity {
  @Column(name = "title")
  private String title;
  @Column(name = "phone_prefix")
  private String phonePrefix;
  
  @OneToMany(
      mappedBy = "country",
      fetch = FetchType.LAZY,
      cascade = CascadeType.DETACH)
  private List<UserEntity> users;
  
  @OneToMany(
      mappedBy = "country",
      fetch = FetchType.LAZY,
      cascade = CascadeType.DETACH)
  private List<PostEntity> posts;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "FK_COUNTRY_INTEREST",
      joinColumns = {@JoinColumn(name = "country_id")},
      inverseJoinColumns = {@JoinColumn(name = "interest_id")})
  private List<InterestAreaEntity> interests;
  
  public CountryEntity(Long id, String title, String phonePrefix) {
    super(id);
    this.title = title;
    this.phonePrefix = phonePrefix;
  }
}
