package com_hch_exam_make.controller;

import com_hch_exam_make.Rq;
import com_hch_exam_make.container.Container;
import com_hch_exam_make.dto.Article;
import com_hch_exam_make.dto.Board;
import com_hch_exam_make.service.ArticleService;
import com_hch_exam_make.service.BoardService;
import com_hch_exam_util.Util;

import java.util.ArrayList;
import java.util.List;

public class UsrArticleController {

  private ArticleService articleService;

  private BoardService boardService;

  public UsrArticleController(){
    articleService = Container.getArticleService();

    boardService = Container.getBoardService();

    makeTestData();
  }

  public void makeTestData(){
    boardService.makeTestData();
    articleService.makeTestData();
  }
  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0){
      System.out.println("번호를 올바르게 입력해주세요");
      return;
    }

    Article article = articleService.getArticleById(id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    articleService.deleteArticleById(article.getId());

    System.out.println(article.getId() + "번 게시물이 삭제되었습니다.");


  }

  public void actionModify(Rq rq) {

    int id = rq.getIntParam("id", 0);

    if(id == 0){
      System.out.println("번호를 올바르게 입력해주세요");
      return;
    }

    Article article = articleService.getArticleById(id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }


    System.out.print("새 제목 : ");
    String title = Container.getSc().nextLine().trim();
    System.out.print("새 내용 : ");
    String body = Container.getSc().nextLine().trim();

    article.setUpdateDate(Util.getNowDateStr());

    articleService.modify(article.getId(), title, body);

    System.out.printf("수정된 시간 : %s\n", article.getUpdateDate());
    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void actionDetail(Rq rq) {

    int id = rq.getIntParam("id", 0);

    if(id == 0){
      System.out.println("번호를 올바르게 입력해주세요");
      return;
    }

    Article article = articleService.getArticleById(id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }


    System.out.println(" - 게시물 상세보기 - ");
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("작성날짜 : %s\n", article.getRegDate());
    System.out.printf("수정날짜 : %s\n", article.getUpdateDate());
    System.out.printf("제목 : \"%s\"\n", article.getTitle());
    System.out.printf("내용 : \"%s\"\n", article.getBody());
  }

  public void actionList(Rq rq) {

    System.out.println(" - 게시물 리스트 - ");
    System.out.println("-----------------");
    System.out.println("BoardId / MemberId/ / 제목  번호/ 내용 / 현재 날짜");
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
      System.out.printf("%d / %d / %d / %s / %s / %s\n", article.getBoardId(), article.getMemberId(), article.getId(), article.getTitle(), article.getBody(), article.getRegDate());
    }

  }

  public void actionWrite(Rq rq) {

    int boardId = rq.getIntParam("boardId", 0);

    if(boardId == 0){
      System.out.println("BoardId를 입력해주세요");
      return;
    }

    Board board = boardService.getBoardById(boardId);

    if (board == null) {
      System.out.println("존재하지 않는 게시판입니다.");
      return;
    }


    System.out.printf("== %s 게시물 등록 == ", board.getName());
    System.out.print("제목 : ");
    String title = Container.getSc().next();
    System.out.print("내용 : ");
    String body = Container.getSc().next();

    int loginedMemberId = rq.getLoginedMemeberId();


    int id = articleService.write(1, loginedMemberId, title, body);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }

}
