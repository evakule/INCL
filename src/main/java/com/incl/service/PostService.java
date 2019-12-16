package com.incl.service;


import com.incl.dto.PostDTO;

import java.util.List;

public interface PostService {
  List<PostDTO> getAllPosts();
  
  PostDTO getPostById(Long id);
  
  void createPost(PostDTO postDTO);
  
  List<PostDTO> getAllPostsByInterest(Long interestId);
  
  
}
