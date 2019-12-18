package com.incl.controller;

import com.incl.controller.messages.ResponseMessage;
import com.incl.dto.UserInterestAreaDTO;
import com.incl.service.InterestAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/interests")
public class InterestAreaController {
  
  @Autowired
  private InterestAreaService interestAreaService;
  
  @GetMapping
  public ResponseEntity<List<UserInterestAreaDTO>> getAllInterests() {
    List<UserInterestAreaDTO> interestAreaDTOList =
        interestAreaService.getAllInterests();
    if (CollectionUtils.isEmpty(interestAreaDTOList)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(interestAreaDTOList, HttpStatus.OK);
    }
  }
  
  @GetMapping(value = "{id}")
  public ResponseEntity<UserInterestAreaDTO> getInterest(
      final @PathVariable("id") Long id
  ) {
    UserInterestAreaDTO interestAreaDTO =
        interestAreaService.getInterestById(id);
    if (interestAreaDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(interestAreaDTO, HttpStatus.OK);
    }
  }
  
  @PutMapping(value = "{id}/subscribe", params = {"userId"})
  public ResponseEntity<?> subscribeToInterest(
      final @PathVariable("id") Long id,
      final @PathParam("userId") Long userId
  ) {
    if (id == null || userId == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      interestAreaService.subscribeToInterest(id, userId);
      return new ResponseEntity<>(ResponseMessage.USER_SUBSCRIBED, HttpStatus.OK);
    }
  }
  
  @GetMapping(value = "max-subscribers")
  public ResponseEntity<UserInterestAreaDTO> getByMaxSubscribers() {
    UserInterestAreaDTO interest =
        interestAreaService.getTopBySubscribedUsers();
    if (interest == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(interest, HttpStatus.OK);
    }
  }
  
  @GetMapping(value = "max-posts")
  public ResponseEntity<UserInterestAreaDTO> getByMaxPosts() {
    UserInterestAreaDTO interest =
        interestAreaService.getByMaxPosts();
    if (interest == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(interest, HttpStatus.OK);
    }
  }
  
  @GetMapping(value = "min-posts")
  public ResponseEntity<UserInterestAreaDTO> getByMinPosts() {
    UserInterestAreaDTO interest =
        interestAreaService.getByMinPosts();
    if (interest == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(interest, HttpStatus.OK);
    }
  }
}
