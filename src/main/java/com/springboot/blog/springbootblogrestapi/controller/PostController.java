package com.springboot.blog.springbootblogrestapi.controller;


import com.springboot.blog.springbootblogrestapi.payload.postDto;
import com.springboot.blog.springbootblogrestapi.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity<postDto> creatPost(@RequestBody postDto postDto){
        return new ResponseEntity<>(postService.CreatePost(postDto), HttpStatus.CREATED);
    }


    @GetMapping
    public List<postDto> getAllPost(){
        return postService.getAllPost();
    }


    @PutMapping("/{id}")
    public ResponseEntity<postDto>updatePost(@RequestBody postDto postDto,
                                             @PathVariable(name = "id") long id){

        postDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delectePost( @PathVariable(name = "id") long id){

        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted", HttpStatus.OK);
    }


    @GetMapping("/titles/{title}")
    public ResponseEntity<postDto> getpostbytitle(@RequestBody postDto postDto,
                                                  @PathVariable (name = "title") String title){

        postDto postDto1 = postService.findPostBytitle(title);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }


}
