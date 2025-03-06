package com.hatchways.blogposts.service

import com.hatchways.blogposts.exception.BadRequestException
import com.hatchways.blogposts.model.Post
import com.hatchways.blogposts.model.User
import com.hatchways.blogposts.repository.PostRepository
import com.hatchways.blogposts.repository.UserRepository
import com.hatchways.blogposts.schema.PostRequest
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
        private val postRepository: PostRepository,
        private val userRepository: UserRepository,
        private val modelMapper: ModelMapper
) : PostService {

  override fun createPost(postRequestBody: PostRequest, username: String): Post {
    val post = modelMapper.map(postRequestBody, Post::class.java)
    val user = userRepository.findByUsername(username)
      ?: throw BadRequestException("User not found")
    post.users = setOf(user)
    postRepository.save(post)
    postRepository.flush()
    return post
  }

  override fun fetchPosts(pageSize: Int, pageNumber: Int, sortingOrder: String): Page<Post> {
    val sortOrder = if (sortingOrder == "ASC") Sort.Direction.ASC else Sort.Direction.DESC
    val sort = Sort.by(sortOrder, "id")
    val pageable: Pageable = PageRequest.of(pageNumber, pageSize, sort)

    val page = postRepository.findAll(pageable)
    if (pageNumber > page.totalPages) {
      throw BadRequestException("Page number exceeds total pages")
    }
    return page
  }
}