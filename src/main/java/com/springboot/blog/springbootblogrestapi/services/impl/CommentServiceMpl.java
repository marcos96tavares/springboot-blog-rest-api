package com.springboot.blog.springbootblogrestapi.services.impl;

import com.springboot.blog.springbootblogrestapi.entity.Comments;
import com.springboot.blog.springbootblogrestapi.entity.Post;
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.payload.CommentsDto;
import com.springboot.blog.springbootblogrestapi.repository.CommentsRepository;
import com.springboot.blog.springbootblogrestapi.repository.PostRepository;
import com.springboot.blog.springbootblogrestapi.services.CommentService;
import com.springboot.blog.springbootblogrestapi.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceMpl implements CommentService {

    private CommentsRepository commentsRepository;
    private PostRepository postRepository;

    public CommentServiceMpl(CommentsRepository commentsRepository, PostRepository postRepository) {
        this.commentsRepository = commentsRepository;
        this.postRepository = postRepository;
    }



    @Override
    public CommentsDto createComment(long postId, CommentsDto commentsDto) {

        Comments comments = mapToEntity(commentsDto);

        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("post", "id", postId)
        );


        comments.setPost(post);
        Comments newComment =commentsRepository.save(comments);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentsDto> getCommentsByPostId(long postId) {
        List<Comments> comments = commentsRepository.findByPostId(postId);

        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }


    private CommentsDto mapToDto(Comments comments){

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setId(comments.getId());
        commentsDto.setEmail(comments.getEmail());
        commentsDto.setBody(comments.getBody());
        commentsDto.setName(comments.getName());

        return commentsDto;
    }


    private Comments mapToEntity(CommentsDto commentsDto){

        Comments comments = new Comments();
        comments.setBody(commentsDto.getBody());
        comments.setId(commentsDto.getId());
        comments.setName(commentsDto.getName());
        comments.setEmail(commentsDto.getEmail());
        return comments;
    }



}
