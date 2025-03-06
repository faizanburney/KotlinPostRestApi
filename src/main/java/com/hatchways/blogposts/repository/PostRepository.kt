package com.hatchways.blogposts.repository

import com.hatchways.blogposts.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {

  @Query("SELECT p FROM Post p JOIN p.users user WHERE user.id in :userId")
  fun findAllByUserId(userId: List<Long>): List<Post>

}