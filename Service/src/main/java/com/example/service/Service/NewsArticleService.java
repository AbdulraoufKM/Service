package com.example.service.Service;

import com.example.service.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles(){
       return newsArticles;
    }

    public Boolean addNewsArticle( NewsArticle newsArticle){
        for (NewsArticle newsArticle1 : newsArticles){
            if (newsArticle1.getId().equals(newsArticle.getId())){
                return false;
            }
        }
        newsArticles.add(newsArticle);
        return true;
    }

    public Boolean updateNewsArticle(String id, NewsArticle newsArticle){
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)){
                newsArticles.set(i,newsArticle);
                return true;
            }
        }
        return false;
    }


    public Boolean deleteNewsArticle(String id){
        for (NewsArticle newsArticle : newsArticles){
            if (newsArticle.getId().equals(id)){
                newsArticles.remove(newsArticle);
                return true;
            }
        }
        return false;
    }


    public boolean publishNewsArticle(String id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)) {
                newsArticles.get(i).setIsPublished(true);
                return true;
            }
        }
        return false;
    }


    public ArrayList<NewsArticle> getPublished(){
        ArrayList<NewsArticle>newsArticles1 = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles){
            if (newsArticle.getIsPublished()){
                newsArticles1.add(newsArticle);
            }
        }
        return newsArticles1;
    }

    public ArrayList<NewsArticle> getByCategory(String category){
        ArrayList<NewsArticle> newsArticles1 = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles){
            if (newsArticle.getCategory().equals(category)){
                newsArticles1.add(newsArticle);
            }
        }
        return newsArticles1;
    }
}
