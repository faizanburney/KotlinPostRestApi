package com.hatchways.blogposts.service

import com.hatchways.blogposts.model.Post
import com.hatchways.blogposts.schema.PostRequest
import org.springframework.data.domain.Page

interface PostService {
  /** Create a new post in the database. */
  fun createPost(postRequestBody: PostRequest, username: String): Post
  fun fetchPosts(pageSize: Int, pageNumber: Int, sortingOrder: String): Page<Post>
}