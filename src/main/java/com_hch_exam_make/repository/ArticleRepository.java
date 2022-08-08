package com_hch_exam_make.repository;

import com_hch_exam_make.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
  private int lastNum;
  private List<Article> articles;

  public ArticleRepository() {
    lastNum = 0;

    articles = new ArrayList<>();
  }

  public int write(int boardId, String title, String body) {
    int num = lastNum + 1;
    Article article = new Article(num, boardId, title, body);
    articles.add(article);
    lastNum = num;

    return num;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public void deleteArticleByNum(int num) {
    Article article = getArticleByNum(num);

    if (article != null) {
      articles.remove(article);
    }
  }

  public Article getArticleByNum(int num) {
    for(Article article : articles){
      if(article.getNum() == num){
        return article;
      }
    }
    return null;
  }
}
