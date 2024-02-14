package com.riseup.bloggingapp.services.impl;

import com.riseup.bloggingapp.entities.Comment;
import com.riseup.bloggingapp.entities.Post;
import com.riseup.bloggingapp.exception.ResourceNotFoundException;
import com.riseup.bloggingapp.payloads.CommentDto;
import com.riseup.bloggingapp.repositories.CommentRepo;
import com.riseup.bloggingapp.repositories.PostRepo;
import com.riseup.bloggingapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post ", "post id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment ", "comment id", commentId));
        this.commentRepo.delete(comment);
    }
}
