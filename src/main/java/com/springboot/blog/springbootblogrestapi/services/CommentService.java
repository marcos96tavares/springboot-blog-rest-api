package com.springboot.blog.springbootblogrestapi.services;

import com.springboot.blog.springbootblogrestapi.payload.CommentsDto;

import java.util.List;

public interface CommentService {

   CommentsDto createComment(long postId, CommentsDto commentsDto);

   List<CommentsDto> getCommentsByPostId(long postId);
}
