package com.incl.service;

import com.incl.dto.UserInterestAreaDTO;
import com.incl.mapper.UserInterestAreaMapper;
import com.incl.model.InterestAreaEntity;
import com.incl.model.UserEntity;
import com.incl.repo.InterestAreaRepository;
import com.incl.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class InterestAreaServiceImpl implements InterestAreaService {
  
  @Autowired
  private InterestAreaRepository interestAreaRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserInterestAreaMapper userInterestAreaMapper;
  
  @Override
  public List<UserInterestAreaDTO> getAllInterests() {
    List<InterestAreaEntity> interestAreaEntityList =
        interestAreaRepository.findAll();
    return userInterestAreaMapper.toDtoList(interestAreaEntityList);
  }
  
  @Override
  public UserInterestAreaDTO getInterestById(final Long id) {
    InterestAreaEntity interestAreaEntity = interestAreaRepository.getOne(id);
    return userInterestAreaMapper.toDto(interestAreaEntity);
  }
  
  @Override
  public void subscribeToInterest(final Long interestId, final Long userId) {
    InterestAreaEntity interest = interestAreaRepository.getOne(interestId);
    UserEntity userEntity = userRepository.getOne(userId);
    interest.addUser(userEntity);
    interestAreaRepository.save(interest);
  }
  
  public UserInterestAreaDTO getTopBySubscribedUsers() {
    List<InterestAreaEntity> areaEntityList =
        interestAreaRepository.findAll();
    InterestAreaEntity interestAreaEntity = getByMaxUsers(areaEntityList);
    return userInterestAreaMapper.toDto(interestAreaEntity);
  }
  
  @Override
  public UserInterestAreaDTO getByMaxPosts() {
    List<InterestAreaEntity> areaEntityList =
        interestAreaRepository.findAll();
    InterestAreaEntity interestAreaEntity = getByMaximumPosts(areaEntityList);
    return userInterestAreaMapper.toDto(interestAreaEntity);
  }
  
  @Override
  public UserInterestAreaDTO getByMinPosts() {
    List<InterestAreaEntity> areaEntityList =
        interestAreaRepository.findAll();
    InterestAreaEntity interestAreaEntity = getByMinimumPosts(areaEntityList);
    return userInterestAreaMapper.toDto(interestAreaEntity);
  }
  
  private InterestAreaEntity getByMaxUsers(final List<InterestAreaEntity> list) {
    InterestAreaEntity maxInterestByUsers = list.get(0);
    for (InterestAreaEntity interest : list) {
      if (interest.getUsers().size() > maxInterestByUsers.getUsers().size()) {
        maxInterestByUsers = interest;
      }
    }
    return maxInterestByUsers;
  }
  
  private InterestAreaEntity getByMaximumPosts(
      final List<InterestAreaEntity> list) {
    InterestAreaEntity maxInterestByPosts = list.get(0);
    for (InterestAreaEntity interest : list) {
      if (interest.getPosts().size() > maxInterestByPosts.getPosts().size()) {
        maxInterestByPosts = interest;
      }
    }
    return maxInterestByPosts;
  }
  
  private InterestAreaEntity getByMinimumPosts(
      final List<InterestAreaEntity> list) {
    InterestAreaEntity minInterestByPosts = list.get(0);
    for (InterestAreaEntity interest : list) {
      if (interest.getPosts().size() < minInterestByPosts.getPosts().size()) {
        minInterestByPosts = interest;
      }
    }
    return minInterestByPosts;
  }
}
