package com.hatchways.blogposts.schema;

import java.util.ArrayList;
import java.util.List;

public class PostsResponseWrapper {
    private List<PostResponse> posts;

    public PostsResponseWrapper() {
        posts = new ArrayList<>();
    }

    public PostsResponseWrapper(List<PostResponse> posts) {
        this.posts = posts;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }
}
