package com.incl.service;

import com.incl.dto.UserInterestAreaDTO;

import java.util.List;

public interface InterestAreaService {
  List<UserInterestAreaDTO> getAllInterests();
  
  UserInterestAreaDTO getInterestById(Long id);
  
  void subscribeToInterest(Long interestId, Long userId);
  
  UserInterestAreaDTO getTopBySubscribedUsers();
  
  UserInterestAreaDTO getByMaxPosts();
  
  UserInterestAreaDTO getByMinPosts();
}
