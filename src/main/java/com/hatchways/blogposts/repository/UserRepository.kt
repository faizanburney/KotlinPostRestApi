package com.hatchways.blogposts.repository;

import com.hatchways.blogposts.model.User;
import java.util.List;

@org.springframework.stereotype.Repository
public interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<User, Long> {
  @org.springframework.data.jpa.repository.Query("SELECT u from User as u where u.username = :username")
  User findByUsername(String username);

  @org.springframework.data.jpa.repository.Query("SELECT u.id from User as u where u.id in :ids")
  List<Long> findByIdIn(List<Long> ids);
}
