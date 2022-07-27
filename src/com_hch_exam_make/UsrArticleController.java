package com_hch_exam_make;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsrArticleController {
  int articleLastNum;

  List<Article> articles;

  UsrArticleController(){
    articleLastNum = 0;
    articles = new ArrayList<>();

    makeTestData();

    if ( articles.size() > 0) {
      articleLastNum = articles.get(articles.size() - 1).num;
    }
  }

  void makeTestData(){
    for( int i = 0; i < 100; i++){
      int id = i + 1;
      articles.add(new Article(id, "제목" + id, "내용" + id));
    }
  }
  public void actionDelete(Rq rq) {
    int num = rq.getIntParam("num", 0);

    if(num == 0){
      System.out.println("번호를 올바르게 입력해주세요");
    }

    if(articles.isEmpty()){//article이 비어있냐?? 라고물어보는함수// ==  article.size() == 0
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    if(num > articles.size()){
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(num-1);


    articles.remove(article);
    System.out.println(article.num + "번 게시물이 삭제되었습니다.");


  }

  public void actionModify(Rq rq, Scanner sc) {

    int num = rq.getIntParam("num", 0);

    if(num == 0){
      System.out.println("번호를 올바르게 입력해주세요");
    }


    if(articles.isEmpty()){//article이 비어있냐?? 라고물어보는함수// ==  article.size() == 0
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    if(num > articles.size()){
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(num-1);


    System.out.print("새 제목 : ");
    article.title = sc.next();
    System.out.print("새 내용 : ");
    article.body = sc.next();

    System.out.printf("%d번 게시물이 수정되었습니다.\n", num);
  }

  public void actionWrite(Rq rq, Scanner sc) {
    System.out.println(" - 게시물 등록 - ");
    System.out.print("제목 : ");
    String title = sc.next();
    System.out.print("내용 : ");
    String body = sc.next();

    int num = ++articleLastNum; // == articleLastNum + 1; articleLastNum++;

    Article article = new Article(num, title, body);

    articles.add(article);

    System.out.println("입력받은 객체 : "+ article);
    System.out.printf("%d번 게시물이 등록되었습니다.\n", article.num);
  }

  public void actionDetail(Rq rq) {

    int num = rq.getIntParam("num", 0);

    if(num == 0){
      System.out.println("번호를 올바르게 입력해주세요");
    }


    if(articles.isEmpty()){//article이 비어있냐?? 라고물어보는함수// ==  article.size() == 0
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    if(num > articles.size()){
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(num-1);

    //길이가 벗어나게 하면 오류가 떠서 아래에 try catch로 다시 만들어줬는데 그밑을보니 이미 오류가 났을경우 처리되는 함수가 만들어져있음
    //그래서 이거를 안으로 넣어줌 따로 trycatch를 만들어줄 필요가 없음

    //원래는 try안에 넣어서 해결햇는데 rq.getIntparam을 다시 만든후에는 길이가 넘어가면 오류가 해결이안된다.

    //해결책은 if문이 입구컷을 해주어야되기 때문에 Article article = articles.get(num-1);보다 위에있어야하는데 아래있어서 안되는것이었다.

//    try{
//      Article article = articles.get(num-1);
//
//      System.out.println(" - 게시물 상세보기 - ");
//      System.out.printf("번호 : %d\n", article.num);
//      System.out.printf("제목 : \"%s\"\n", article.title);
//      System.out.printf("내용 : \"%s\"\n", article.body);
//    }
//    catch(IndexOutOfBoundsException e){
//      System.out.println("게시물 범위를 벗어났습니다.");
//      return;
//    }


    System.out.println(" - 게시물 상세보기 - ");
    System.out.printf("번호 : %d\n", article.num);
    System.out.printf("제목 : \"%s\"\n", article.title);
    System.out.printf("내용 : \"%s\"\n", article.body);
  }

  public void actionList(Rq rq) {

    String searchKeyword = rq.getParam("searchKeyword", "");

    List<Article> filteredArticle = articles;


    if(searchKeyword.length() > 0){
      filteredArticle = new ArrayList<>();

      for(Article article : articles){
        boolean matched = article.title.contains(searchKeyword) || article.body.contains(searchKeyword);

        if(matched) {
          filteredArticle.add(article);
        }
      }
    }

//    System.out.println(filteredArticle);

    System.out.println(" - 게시물 리스트 - ");
    System.out.println("-----------------");
    System.out.println("번호 / 제목 / 내용");
    System.out.println("-----------------");

    List<Article> sortedArticle = filteredArticle;

    String orderBy = rq.getParam("orderBy" , "IdDesc");

    boolean orderByIdDesc = orderBy.equals("IdDesc");

    if(orderByIdDesc){
      sortedArticle = Util.reverseList(sortedArticle);
    }

    for(Article article : sortedArticle){
      System.out.printf("%d / %s / %s\n", article.num, article.title, article.body);
    }

  }

}
