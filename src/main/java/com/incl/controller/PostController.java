package com.incl.controller;

import com.incl.controller.messages.ResponseMessage;
import com.incl.dto.PostDTO;
import com.incl.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/posts")
public class PostController {
  
  @Autowired
  private PostService postService;
  
  @GetMapping
  public ResponseEntity<List<PostDTO>> getAllPosts() {
    List<PostDTO> postDTOList =
        postService.getAllPosts();
    if (CollectionUtils.isEmpty(postDTOList)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }
  }
  
  @GetMapping(value = "{id}")
  public ResponseEntity<PostDTO> getPost(
      final @PathVariable("id") Long id
  ) {
    PostDTO postDTO =
        postService.getPostById(id);
    if (postDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }
  }
  
  @PostMapping
  public ResponseEntity<?> createPost(
      final @Valid @RequestBody PostDTO postDTO
  ) {
    if (postDTO == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      postService.createPost(postDTO);
      return new ResponseEntity<>(
          ResponseMessage.POST_CREATED.getMessage(), HttpStatus.CREATED);
    }
  }
  
  @GetMapping(value = "all", params = "interest")
  public ResponseEntity<?> getPostsByArea(
      @PathParam("interest") Long interest
  ) {
    List<PostDTO> postDTOList =
        postService.getAllPostsByInterest(interest);
    if (CollectionUtils.isEmpty(postDTOList)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }
  }
}
