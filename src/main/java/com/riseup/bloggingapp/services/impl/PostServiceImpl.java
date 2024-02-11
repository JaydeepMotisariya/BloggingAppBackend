package com.riseup.bloggingapp.services.impl;

import com.riseup.bloggingapp.entities.Category;
import com.riseup.bloggingapp.entities.Post;
import com.riseup.bloggingapp.entities.User;
import com.riseup.bloggingapp.exception.ResourceNotFoundException;
import com.riseup.bloggingapp.payloads.CategoryDto;
import com.riseup.bloggingapp.payloads.PostDto;
import com.riseup.bloggingapp.repositories.CategoryRepo;
import com.riseup.bloggingapp.repositories.PostRepo;
import com.riseup.bloggingapp.repositories.UserRepo;
import com.riseup.bloggingapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User ", "User id ", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category ", "Category id ", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "Post id ", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "Post id ", postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> allPosts = this.postRepo.findAll();
        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post ", "Post Id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category ", "Category id ", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User ", "User id ", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}