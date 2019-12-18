package com.incl.batch;

import com.incl.model.CountryEntity;
import com.incl.repo.CountryRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryDatabaseWriter implements ItemWriter<CountryEntity> {
  
  @Autowired
  private CountryRepository countryRepository;
  
  @Override
  public void write(final List<? extends CountryEntity> list) throws Exception {
    countryRepository.saveAll(list);
  }
}
