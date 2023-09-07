package com.springboot.blog.springbootblogrestapi.services.impl;

import com.springboot.blog.springbootblogrestapi.entity.Post;
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.payload.postDto;
import com.springboot.blog.springbootblogrestapi.repository.PostRepository;
import com.springboot.blog.springbootblogrestapi.services.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
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

        List<Post> posts = postRepository.findAll();
        return  posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public postDto updatePost(postDto postDto, long id) {

        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post upDatePost = postRepository.save(post);

        return mapToDto(upDatePost);



    }

    @Override
    public void deletePostById(long id) {

        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));

        postRepository.delete(post);

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
