package com.example.blogPlatform.controller;

import com.example.blogPlatform.Blog;
import com.example.blogPlatform.model.BlogPost;
import com.example.blogPlatform.repository.BlogPostRepository;
import com.example.blogPlatform.service.BlogPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class BlogPostController {
    private final BlogPostService blogPostService;
    private final BlogPostRepository blogPostRepository;
    private final Blog blog;

    @Autowired
    public BlogPostController(BlogPostService blogPostService, BlogPostRepository blogPostRepository, Blog blog) {
        this.blogPostService = blogPostService;
        this.blogPostRepository = blogPostRepository;
        this.blog = blog;
    }


    @GetMapping
    public String getAllBlogPosts(Model model) {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        model.addAttribute("blogPosts", blogPosts);
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "create";
    }

    @PostMapping("")
    public String createBlogPost(@Valid @ModelAttribute("blogPost") BlogPost blogPost, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }

        blogPostService.createBlogPost(blogPost);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/delete")
    public String getBlogPostById(@PathVariable Long id, Model model) {
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        if (blogPost.isPresent()) {
            model.addAttribute("blogPost", blogPost.get());
            return "delete";
        } else {
            throw new IllegalArgumentException("Invalid Blog Post ID");
        }
    }
    @PostMapping("/{id}/update")
    public String updateBlogPost(@PathVariable Long id, @Validated @ModelAttribute("blogPost") BlogPost updatedBlogPost, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update"; // Return to the form with validation errors
        }

        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        if (blogPost.isPresent()) {
            updatedBlogPost.setId(id);
            blogPostService.updateBlogPost(updatedBlogPost);
            return "redirect:/posts/" + id;
        } else {
            throw new IllegalArgumentException("Invalid Blog Post ID");
        }
    }

    @GetMapping("/{id}")
    public String getBlogPostDetails(@PathVariable("id") Long id, @RequestParam(required = false) String action, Model model) {
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        if (blogPost.isPresent()) {
            model.addAttribute("blogPost", blogPost.get());
            if ("delete".equals(action)) {
                blogPostService.deleteBlogPostById(id);
                blog.removeBlogPost(blogPost.get());
                return "redirect:/posts";
            }
            return "redirect:/posts";
        } else {
            throw new IllegalArgumentException("Invalid Blog Post ID");
        }
    }

    @GetMapping("/{id}/edit")
    public String editBlogPost(@PathVariable Long id, Model model) {
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        if (blogPost.isPresent()) {
            model.addAttribute("blogPost", blogPost.get());
            return "update";
        } else {
            throw new IllegalArgumentException("Invalid Blog Post ID");
        }
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteBlogPost(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        if (blogPost.isPresent()) {
            blogPostService.deleteBlogPostById(id);
            redirectAttributes.addFlashAttribute("message", "Blog post deleted successfully.");
        }

        return "redirect:/posts";
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<EntityModel<BlogPost>> getBlogPost(@PathVariable("id") Long id) {
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        if (blogPost.isPresent()) {
            EntityModel<BlogPost> blogPostResource = EntityModel.of(blogPost.get());
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BlogPostController.class).getBlogPostDetails(id, null, null)).withSelfRel();
            blogPostResource.add(selfLink);
            return ResponseEntity.ok(blogPostResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}