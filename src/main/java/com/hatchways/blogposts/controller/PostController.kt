package com.hatchways.blogposts.controller;


import com.hatchways.blogposts.model.Post
import com.hatchways.blogposts.schema.PostRequest
import com.hatchways.blogposts.schema.PostResponse
import com.hatchways.blogposts.schema.PostResponseWrapper
import com.hatchways.blogposts.schema.PostsResponseWrapper
import com.hatchways.blogposts.service.PostService
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern

@RestController
@RequestMapping("/api")
class PostController (
    private val postService: PostService,
    private val modelMapper: ModelMapper
) {

    @GetMapping("/posts")
    fun fetchPosts(
        @RequestParam(defaultValue = "1") @Min(1) currentPage: Int,
        @RequestParam(defaultValue = "10") @Min(1) pageSize: Int,
        @RequestParam(defaultValue = "ASC") @Pattern(regexp = "ASC|DESC") sortBy: String
    ): ResponseEntity<PostsResponseWrapper> {
        val postsPage: Page<Post> = postService.fetchPosts(pageSize, currentPage - 1, sortBy)
        val response = PostsResponseWrapper(
            postsPage.content.map { modelMapper.map(it, PostResponse::class.java) },
            postsPage.totalPages
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping("/posts")
    fun createPost(
        @Valid @RequestBody postRequestBody: PostRequest,
        authentication: Authentication
    ): ResponseEntity<PostResponseWrapper> {
        val post: Post = postService.createPost(postRequestBody, authentication.name)
        val postResponse: PostResponse = modelMapper.map(post, PostResponse::class.java)
        val response = PostResponseWrapper(postResponse)
        return ResponseEntity.ok(response)
    }
}