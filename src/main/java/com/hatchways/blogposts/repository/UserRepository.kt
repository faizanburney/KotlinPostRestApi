package com.hatchways.blogposts.repository

import com.hatchways.blogposts.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

  @Query("SELECT u from User as u where u.username = :username")
  fun findByUsername(username: String): User?

  @Query("SELECT u.id from User as u where u.id in :ids")
  fun findByIdIn(ids: List<Long>): List<Long>
}