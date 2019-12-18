package com.incl.batch;

import com.incl.model.CountryEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public final class CountryProcessor implements
    ItemProcessor<CountryEntity, CountryEntity> {
  @Override
  public CountryEntity process(final CountryEntity countryEntity)
      throws Exception {
    final Long countryId = countryEntity.getId();
    final String countryTitle = countryEntity.getTitle().toUpperCase();
    final String countryPhonePrefix = countryEntity.getPhonePrefix();
    return new CountryEntity(countryId, countryTitle, countryPhonePrefix);
  }
}
