package com_hch_exam_make.service;

import com_hch_exam_make.dto.Article;
import com_hch_exam_make.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService(){
    articleRepository = new ArticleRepository();
  }

  public int write(String title, String body) {
    return articleRepository.write(title, body);
  }

  public void makeTestData() {
    for( int i = 0; i < 100; i++){
      String title = "제목" + (i + 1);
      String body = "내용" + (i + 1);
      write(title, body);
    }
  }

  public List<Article> getArticles() {
    return articleRepository.getArticles();
  }

  public void deleteArticleByNum(int num) {
    articleRepository.deleteArticleByNum(num);
  }

}
