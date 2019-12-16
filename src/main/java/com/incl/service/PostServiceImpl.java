package com.incl.service;

import com.incl.dto.PostDTO;
import com.incl.mapper.PostMapper;
import com.incl.model.CountryEntity;
import com.incl.model.InterestAreaEntity;
import com.incl.model.PostEntity;
import com.incl.model.UserEntity;
import com.incl.repo.CountryRepository;
import com.incl.repo.InterestAreaRepository;
import com.incl.repo.PostRepository;
import com.incl.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class PostServiceImpl implements PostService {
  
  @Autowired
  private PostRepository postRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private CountryRepository countryRepository;
  
  @Autowired
  private InterestAreaRepository interestAreaRepository;
  
  @Autowired
  private PostMapper postMapper;
  
  @Override
  public List<PostDTO> getAllPosts() {
    List<PostEntity> postEntities = postRepository.findAll();
    return postMapper.toDtoList(postEntities);
  }
  
  @Override
  public PostDTO getPostById(final Long id) {
    PostEntity postEntity = postRepository.getOne(id);
    return postMapper.toDto(postEntity);
  }
  
  @Override
  public void createPost(PostDTO postDTO) {
    UserEntity userEntity = userRepository.getOne(postDTO.getUserId());
    PostEntity postEntity = postMapper.toEntity(postDTO);
    Long countryId = userEntity.getCountry().getId();
    CountryEntity countryEntity = countryRepository.getOne(countryId);
    postEntity.setCountry(countryEntity);
    postRepository.save(postEntity);
  }
  
  @Override
  public List<PostDTO> getAllPostsByInterest(Long interestId) {
    InterestAreaEntity interestAreaEntity =
        interestAreaRepository.getOne(interestId);
    List<PostEntity> postDTOList =
        postRepository.getAllByInterest(interestAreaEntity);
    return postMapper.toDtoList(postDTOList);
  }
}
