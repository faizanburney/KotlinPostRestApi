package com.hatchways.blogposts.repository;

import com.hatchways.blogposts.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.Query;


@org.springframework.stereotype.Repository
public interface PostRepository extends org.springframework.data.jpa.repository.JpaRepository<Post, Long>  {
  @Query("SELECT p FROM Post p JOIN p.users user WHERE user.id in :userId")
  List<Post> findAllByUserId(List<Long> userId);

  List<Post> findAll();
}
