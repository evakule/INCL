package com.incl.repo;

import com.incl.model.InterestAreaEntity;
import com.incl.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
  List<PostEntity> getAllByInterest(InterestAreaEntity interestAreaEntity);
}
