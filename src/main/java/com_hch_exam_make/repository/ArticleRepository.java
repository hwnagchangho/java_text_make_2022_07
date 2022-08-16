package com_hch_exam_make.repository;

import com_hch_exam_make.dto.Article;
import com_hch_exam_util.Util;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
  private int lastId;
  private List<Article> articles;

  public ArticleRepository() {
    lastId = 0;

    articles = new ArrayList<>();
  }

  public int write(int boardId, int memberId, String title, String body) {
    int id = lastId + 1;
    String regDate = Util.getNowDateStr();

    String updateDate = regDate;
    Article article = new Article(id, regDate, updateDate, boardId, memberId, title, body);
    articles.add(article);
    lastId = id;

    return id;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public void deleteArticleById(int id) {
    Article article = getArticleById(id);

    if (article != null) {
      articles.remove(article);
    }
  }

  public Article getArticleById(int id) {
    for(Article article : articles){
      if(article.getId() == id){
        return article;
      }
    }
    return null;
  }

  public void modify(int id, String title, String body) {
    Article article = getArticleById(id);

    article.setTitle(title);
    article.setBody(body);
    article.setUpdateDate(Util.getNowDateStr());

  }
}
