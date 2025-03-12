package com.example.service.Controller;

import com.example.service.Api.ApiResponse;
import com.example.service.Model.NewsArticle;
import com.example.service.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/newsArticle")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getNewsArticle(){
        ArrayList<NewsArticle> newsArticles = newsArticleService.getNewsArticles();
        return ResponseEntity.status(200).body(newsArticles);
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        boolean isAdded = newsArticleService.addNewsArticle(newsArticle);

        if (isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("News article is added"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Failed to added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id, NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body( message);
        }

        Boolean isUpdated = newsArticleService.updateNewsArticle(id,newsArticle);

        if (isUpdated){
            return ResponseEntity.status(200).body( new ApiResponse("News Article is updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ApiResponse("Id is not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable String id ){
        Boolean isDeleted = newsArticleService.deleteNewsArticle(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(  new ApiResponse("News Article deleted"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ApiResponse("Failed to delete news article"));
    }

    @PutMapping("/publish")
    public ResponseEntity publishNewsArticle(@PathVariable String id) {
        Boolean isPublish = newsArticleService.publishNewsArticle(id);
        if (isPublish) {
            return ResponseEntity.status(200).body(new ApiResponse("News Article published"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to publish news article"));
    }

    @GetMapping("/getPublished")
    public ResponseEntity getAllPublished(){
        return ResponseEntity.status(200).body(newsArticleService.getPublished());
    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        return ResponseEntity.status(200).body(newsArticleService.getByCategory(category));
    }
}
