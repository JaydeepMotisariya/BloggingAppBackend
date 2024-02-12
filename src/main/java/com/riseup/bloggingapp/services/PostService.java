package com.riseup.bloggingapp.services;

import com.riseup.bloggingapp.entities.Post;
import com.riseup.bloggingapp.payloads.PostDto;
import com.riseup.bloggingapp.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //Create Post
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //Update Post
    PostDto updatePost(PostDto postDto, Integer postId);

    //Delete Post
    void deletePost(Integer postId);

    //Get All Post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    //Get Single Post
    PostDto getPostById(Integer postId);

    //Get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //Get all post by user
    List<PostDto> getPostsByUser(Integer userId);

    //Search Post
    List<Post> searchPosts(String keyword);
}
