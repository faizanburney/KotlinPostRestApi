package com.hatchways.blogposts.service

import com.hatchways.blogposts.exception.UserExistsException
import com.hatchways.blogposts.model.User
import com.hatchways.blogposts.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val userRepository: UserRepository
) : UserService {

  override fun createUser(username: String, password: String): User {
    val existingUser = userRepository.findByUsername(username)
    if (existingUser != null) {
      throw UserExistsException("User already exists")
    }

    val user = User(username, BCrypt.hashpw(password, BCrypt.gensalt()))
    userRepository.save(user)
    userRepository.flush()
    return user
  }

  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")
    return user
  }
}