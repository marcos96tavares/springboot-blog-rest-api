package com.springboot.blog.springbootblogrestapi.services;

import com.springboot.blog.springbootblogrestapi.payload.postDto;

import java.util.List;

public interface PostService {

    postDto CreatePost(postDto postDto);

    List<postDto> getAllPost();

    postDto updatePost(postDto postDto, long id);

    void deletePostById(long id);

    postDto findPostBytitle(String title);

}
