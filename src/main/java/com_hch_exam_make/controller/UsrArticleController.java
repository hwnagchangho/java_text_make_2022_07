package com_hch_exam_make.controller;

import com_hch_exam_make.Rq;
import com_hch_exam_make.container.Container;
import com_hch_exam_make.dto.Article;
import com_hch_exam_make.service.ArticleService;
import com_hch_exam_util.Util;

import java.util.ArrayList;
import java.util.List;

public class UsrArticleController {

  private ArticleService articleService;

  public UsrArticleController(){
    articleService = Container.getArticleService();
    makeTestData();
  }

  public void makeTestData(){
    articleService.makeTestData();
  }
  public void actionDelete(Rq rq) {
    int num = rq.getIntParam("num", 0);

    if(num == 0){
      System.out.println("번호를 올바르게 입력해주세요");
      return;
    }

    Article article = articleService.getArticleByNum(num);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    articleService.deleteArticleByNum(article.getNum());

    System.out.println(article.getNum() + "번 게시물이 삭제되었습니다.");


  }

  public void actionModify(Rq rq) {

    int num = rq.getIntParam("num", 0);

    if(num == 0){
      System.out.println("번호를 올바르게 입력해주세요");
      return;
    }

    Article article = articleService.getArticleByNum(num);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }


    System.out.print("새 제목 : ");
    article.setTitle((Container.getSc().next()));
    System.out.print("새 내용 : ");
    article.setBody((Container.getSc().next()));

    System.out.printf("%d번 게시물이 수정되었습니다.\n", num);
  }

  public void actionDetail(Rq rq) {

    int num = rq.getIntParam("num", 0);

    if(num == 0){
      System.out.println("번호를 올바르게 입력해주세요");
      return;
    }

    Article article = articleService.getArticleByNum(num);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }


    System.out.println(" - 게시물 상세보기 - ");
    System.out.printf("번호 : %d\n", article.getNum());
    System.out.printf("제목 : \"%s\"\n", article.getTitle());
    System.out.printf("내용 : \"%s\"\n", article.getBody());
  }

  public void actionList(Rq rq) {

    System.out.println(" - 게시물 리스트 - ");
    System.out.println("-----------------");
    System.out.println("번호 / 제목 / 내용");
    System.out.println("-----------------");

    String searchKeyword = rq.getParam("searchKeyword", "");

    List<Article> articles = articleService.getArticles();

    List<Article> filteredArticle = articles;


    if(searchKeyword.length() > 0){
      filteredArticle = new ArrayList<>();

      for(Article article : articles){
        boolean matched = article.getTitle().contains(searchKeyword) || article.getBody().contains(searchKeyword);

        if(matched) {
          filteredArticle.add(article);
        }
      }
    }


    List<Article> sortedArticle = filteredArticle;

    String orderBy = rq.getParam("orderBy" , "IdDesc");

    boolean orderByIdDesc = orderBy.equals("IdDesc");

    if(orderByIdDesc){
      sortedArticle = Util.reverseList(sortedArticle);
    }

    for(Article article : sortedArticle){
      System.out.printf("%d/ %d / %s / %s\n", article.getBoardId(), article.getNum(), article.getTitle(), article.getBody());
    }

  }

  public void actionWrite(Rq rq) {
    System.out.println(" - 게시물 등록 - ");
    System.out.print("제목 : ");
    String title = Container.getSc().next();
    System.out.print("내용 : ");
    String body = Container.getSc().next();

    int num = articleService.write(1, title, body);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", num);
  }

}
