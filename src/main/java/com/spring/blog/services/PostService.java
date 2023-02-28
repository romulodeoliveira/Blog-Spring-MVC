package com.spring.blog.services;

import java.util.List;

import com.spring.blog.models.Post;

public interface PostService {

    List<Post> findAll();

    Post findById(long id);

    Post save(Post obj);

    public void deleteById(long id);

    public Post update(Post post);

}
