package com.incl.service;

import com.incl.dto.UserDTO;
import com.incl.dto.UserRegistrationDto;
import com.incl.mapper.UserMapper;
import com.incl.mapper.UserRegistrationMapper;
import com.incl.model.CountryEntity;
import com.incl.model.InterestAreaEntity;
import com.incl.model.UserEntity;
import com.incl.model.status.CustomerStatus;
import com.incl.repo.CountryRepository;
import com.incl.repo.InterestAreaRepository;
import com.incl.repo.UserRepository;
import com.incl.service.exception.InvalidPhoneNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public final class CustomerServiceImpl implements CustomerService {
  @Value("${incl.user.not-found}")
  private String userNotFoundMessage;
  @Value("${incl.country.invalid-phone-number}")
  private String invalidPhoneNumberMessage;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private CountryRepository countryRepository;
  
  @Autowired
  private InterestAreaRepository interestAreaRepository;
  
  @Autowired
  private UserMapper userMapper;
  
  @Autowired
  private UserRegistrationMapper userRegistrationMapper;
  
  @Override
  public List<UserDTO> getAllUsers() {
    List<UserEntity> userEntityList = userRepository.findAll();
    return userMapper.toDtoList(userEntityList);
  }
  
  @Override
  public UserDTO getUserById(final Long id) {
    UserEntity userEntity = userRepository.getOne(id);
    return userMapper.toDto(userEntity);
  }
  
  @Override
  public List<String> getUsersPhonesByCountryAndInterests(
      final Long countryId,
      final Long interestId
  ) {
    CountryEntity countryEntity =
        countryRepository.getOne(countryId);
    InterestAreaEntity interestAreaEntity =
        interestAreaRepository.getOne(interestId);
    List<UserEntity> usersList = userRepository
        .getUsersByCountry(countryEntity);
    return getUsersPhones(usersList, interestAreaEntity);
  }
  
  private List<String> getUsersPhones(
      final List<UserEntity> usersList,
      final InterestAreaEntity interestAreaEntity
  ) {
    if (Objects.isNull(interestAreaEntity)) {
      return Collections.emptyList();
    }
    return usersList.stream()
        .filter(userEntity ->
            !CollectionUtils.isEmpty(userEntity.getInterests())
                && userEntity.getInterests().contains(interestAreaEntity))
        .map(UserEntity::getPhone)
        .filter(phone -> phone.contains("+"))
        .collect(Collectors.toList());
  }
  
  @Override
  public void createUser(final UserRegistrationDto newUser)
      throws InvalidPhoneNumberException {
    String newUserPhone = newUser.getPhone();
    CountryEntity countryEntity = getCountryByPhone(newUserPhone);
    if (Objects.isNull(countryEntity)) {
      throw new InvalidPhoneNumberException(invalidPhoneNumberMessage);
    }
    UserEntity userEntity = userRegistrationMapper.toEntity(newUser);
    userEntity.setCountry(countryEntity);
    userEntity.setCustomerStatus(CustomerStatus.ACTIVE);
    userRepository.save(userEntity);
  }
  
  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByName(name);
    if (Objects.isNull(userEntity)) {
      throw new UsernameNotFoundException(userNotFoundMessage);
    }
    return userEntity;
  }
  
  private CountryEntity getCountryByPhone(final String newUserPhone) {
    CountryEntity countryEntity = null;
    for (int prefixSize = 4; prefixSize >= 2; prefixSize--) {
      countryEntity = countryRepository.getByPhonePrefix(newUserPhone.substring(0, prefixSize));
      if (Objects.nonNull(countryEntity)) {
        return countryEntity;
      }
    }
    return countryEntity;
  }
}
