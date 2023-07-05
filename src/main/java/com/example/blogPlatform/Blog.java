package com.example.blogPlatform;

import com.example.blogPlatform.model.BlogPost;
import com.example.blogPlatform.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Blog implements CommandLineRunner {

    private final BlogPostService blogPostService;

    @Autowired
    public Blog(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @Override
    public void run(String... args) throws Exception {
        BlogPost blog1 = new BlogPost();
        blog1.setTitle("My Favorite Quote");
        blog1.setContent("You do not just wake up and become the butterfly..growth is a process- rupi kaur");
        blog1.setAuthor("Lee Vue");
        blogPostService.createBlogPost(blog1);

        BlogPost blog2 = new BlogPost();
        blog2.setTitle("Last summer...");
        blog2.setContent("Was the best summer ever! Cannot wait to spend this summer with my cousins!");
        blog2.setAuthor("Avis Yang");
        blogPostService.createBlogPost(blog2);

        BlogPost blog3 = new BlogPost();
        blog3.setTitle("Christmas 2023");
        blog3.setContent("Hopefully we can go back to Disney World again for Christmas!! It was so much fun!!!!!");
        blog3.setAuthor("Paisli Yang");
        blogPostService.createBlogPost(blog3);
    }

    public void addBlogPost(BlogPost blogPost) {
        blogPostService.createBlogPost(blogPost);
    }

    public void removeBlogPost(BlogPost blogPost) {
        blogPostService.deleteBlogPostById(blogPost.getId());
    }
}
