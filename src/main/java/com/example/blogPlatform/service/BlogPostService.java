package com.example.blogPlatform.service;

import com.example.blogPlatform.Blog;
import com.example.blogPlatform.model.BlogPost;
import com.example.blogPlatform.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;
    private Blog blog;


    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost createBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public Optional<BlogPost> getBlogPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost updateBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public void deleteBlogPostById(Long id) {
        blogPostRepository.deleteById(id);
    }
    }

