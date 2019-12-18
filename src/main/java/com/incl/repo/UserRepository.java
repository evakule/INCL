package com.incl.repo;

import com.incl.model.CountryEntity;
import com.incl.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  List<UserEntity> getUsersByCountry(CountryEntity country);
  
  UserEntity findByName(String name);
}
