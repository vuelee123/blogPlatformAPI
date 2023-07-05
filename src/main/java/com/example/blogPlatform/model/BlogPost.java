package com.example.blogPlatform.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

@Entity
public class BlogPost extends RepresentationModel<BlogPost> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Must enter a title")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    @NotBlank(message = "Must enter content")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String content;

    @NotBlank(message = "Must enter an Author")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
