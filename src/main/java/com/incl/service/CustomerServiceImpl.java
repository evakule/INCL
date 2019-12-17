package com.incl.service;

import com.incl.dto.UserDTO;
import com.incl.mapper.UserMapper;
import com.incl.model.CountryEntity;
import com.incl.model.InterestAreaEntity;
import com.incl.model.UserEntity;
import com.incl.repo.CountryRepository;
import com.incl.repo.InterestAreaRepository;
import com.incl.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public final class CustomerServiceImpl implements CustomerService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private CountryRepository countryRepository;
  
  @Autowired
  private InterestAreaRepository interestAreaRepository;
  
  @Autowired
  private UserMapper userMapper;
  
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
}
