package com.springboot.blog.springbootblogrestapi.controller;


import com.springboot.blog.springbootblogrestapi.payload.CommentsDto;
import com.springboot.blog.springbootblogrestapi.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentsController {

   private CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentsDto> createComments(@PathVariable(value = "postId") long
                                                      postId, @RequestBody CommentsDto commentsDto){

        return new ResponseEntity<>(commentService.createComment(postId, commentsDto), HttpStatus.CREATED);
    }



    @GetMapping("/posts/{postId}/comments")
    public List<CommentsDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }


}
