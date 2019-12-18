package com.incl.model;

import com.incl.model.status.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {
  @Column(name = "name")
  private String name;
  @Column(name = "encrypted_password")
  private String encryptedPassword;
  @Column(name = "email")
  private String email;
  @Column(name = "phone")
  private String phone;
  @Column(name = "customer_status")
  @Enumerated(EnumType.STRING)
  private CustomerStatus customerStatus;
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  @JoinColumn(name = "country_id", nullable = false)
  private CountryEntity country;
  
  @OneToMany(
      mappedBy = "user",
      fetch = FetchType.LAZY,
      cascade = CascadeType.DETACH)
  private List<PostEntity> posts;
  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "FK_USER_ROLE",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private Set<RoleEntity> roles;
  
  @ManyToMany(
      fetch = FetchType.LAZY,
      mappedBy = "users",
      cascade = CascadeType.DETACH)
  private List<InterestAreaEntity> interests;
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }
  
  @Override
  public String getPassword() {
    return encryptedPassword;
  }
  
  @Override
  public String getUsername() {
    return name;
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return customerStatus.equals(CustomerStatus.ACTIVE);
  }
}
