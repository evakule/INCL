package com.incl.repo;

import com.incl.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
  CountryEntity getByPhonePrefix(String phonePrefix);
}
