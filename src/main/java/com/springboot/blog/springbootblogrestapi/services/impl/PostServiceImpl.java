package com.springboot.blog.springbootblogrestapi.services.impl;

import com.springboot.blog.springbootblogrestapi.entity.Post;
import com.springboot.blog.springbootblogrestapi.payload.postDto;
import com.springboot.blog.springbootblogrestapi.repository.PostRepository;
import com.springboot.blog.springbootblogrestapi.services.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }




    @Override
    public postDto CreatePost(postDto postDto) {

        Post post = mapToPost(postDto);

        Post newPost = postRepository.save(post);

        return mapToDto(newPost);


    }

    @Override
    public List<postDto> getAllPost() {
        return null;
    }






    // create new postDto from post
    private postDto mapToDto(Post post){

        postDto postDto = new postDto(){};

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());

        return postDto;

    }

    // create new post from postDto
    private  Post mapToPost (postDto postDto){

        Post post = new Post();

        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        return post;
    }


}
