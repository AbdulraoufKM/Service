package com.example.service.Model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
public class NewsArticle {

    @NotEmpty(message = "id Can not be empty")
    private String id;
    @NotEmpty(message = "title Can not be empty")
    @Size(max = 100 , message = "title Maximum length of 100 characters")
    private String title;
    @NotEmpty(message = "author Can not be empty")
    @Size(min = 5 , max = 20 , message = "author Must be more than 4 characters. ")
    private String author;
    @NotEmpty(message = "content Can not be empty")
    @Size(min = 201 , message = "Must be more than 200 characters. ")
    private String content;
    @NotEmpty(message = "category Can not be empty")
    @Pattern(regexp = ("politics|sports|technology"), message = "categoty Must be either \"politics\", \" sports\" or \" technology\" only. ")
    private String category;
    @NotEmpty(message = "image Url Can not be empty")
    private String imageUrl;
    @AssertFalse
    private Boolean isPublished;
    @NotEmpty(message = "publishDate Can not be empty")
    private String publishDate;
}
