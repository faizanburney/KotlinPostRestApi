package com.hatchways.blogposts.schema;

import java.util.ArrayList;
import java.util.List;

public class PostsResponseWrapper {
    private List<PostResponse> posts;
    private int totalPages;

    public PostsResponseWrapper() {
        posts = new ArrayList<>();
    }

    public PostsResponseWrapper(List<PostResponse> posts, int totalPages) {
        this.posts = posts;
        this.totalPages = totalPages;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
