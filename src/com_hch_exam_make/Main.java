package com_hch_exam_make;

import java.util.*;

public class Main {

  static int articleLastNum = 0;

  static List<Article> articles = new ArrayList<>();

  static void makeTestData(){
    for( int i = 0; i < 100; i++){
      int id = i + 1;
      articles.add(new Article(id, "제목" + id, "내용" + id));
    }


  }
  public static void main(String[] args) {
    Scanner sc = Container.sc;

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    makeTestData();

    if ( articles.size() > 0) {
      articleLastNum = articles.get(articles.size() - 1).num;
    }

    while(true){
      System.out.print("명령)");
      String cmd = sc.next();

      Rq rq= new Rq(cmd);
      Map<String, String> params = rq.getParams();

      if (cmd.equals("exit")){
        break;
      }
      else if (rq.getUrlPath().equals("/usr/article/list")){ //cmd를 rq.getUrlPath()로바꾸면 아무리 복잡한 명령어를 넣어도 ? 이전으로 호출
        actionUsrArticleList(rq, params);
      }
      else if (rq.getUrlPath().equals("/usr/article/detail")){
        actionUsrArticleDetail(rq,params);
      }
      else if(rq.getUrlPath().equals("/usr/article/write")){
        actionUsrArticleWrite(rq, sc, params);
      }
      else if(rq.getUrlPath().equals("/usr/article/modify")){
        actionUsrArticleModify(rq, sc, params);
      }
      else if(rq.getUrlPath().equals("/usr/article/delete")){
        actionUsrArticleDelete(rq, params);
      }
      else{
        System.out.println("입력된 명령어 : " + cmd);
      }
    }
    System.out.println("== 프로그램 종료 ==");

    sc.close();
  }

  private static void actionUsrArticleDelete(Rq rq, Map<String, String> params) {
    if(!params.containsKey("num")){
      System.out.println("번호를 입력해주세요");
      return;
    }

    int num = 0;

    try{
      num = Integer.parseInt(params.get("num")); // num이 들어있나 확인/ 들어있는 String값num을 int값으로 변환
    }
    catch(NumberFormatException e){
      System.out.println("번호를 정수형 형태로 입력해주세요");
      return; //continue 대신 쓸수 있는 함수는 return 이다. continue가 밑에껄 생략하고 위에올라가는것처럼 return도 똑같다.
      //continue는 반복문 안에서만 효율을 발휘한다.
    }



    if(articles.isEmpty()){//article이 비어있냐?? 라고물어보는함수// ==  article.size() == 0
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(num-1);

    if(num > articles.size()){
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    articles.remove(article);
    System.out.println(article.num + "번 게시물이 삭제되었습니다.");


  }

  private static void actionUsrArticleModify(Rq rq, Scanner sc, Map<String, String> params) {
    if(!params.containsKey("num")){
      System.out.println("번호를 입력해주세요");
      return;
    }

    int num = 0;

    try{
      num = Integer.parseInt(params.get("num")); // num이 들어있나 확인/ 들어있는 String값num을 int값으로 변환
    }
    catch(NumberFormatException e){
      System.out.println("번호를 정수형 형태로 입력해주세요");
      return; //continue 대신 쓸수 있는 함수는 return 이다. continue가 밑에껄 생략하고 위에올라가는것처럼 return도 똑같다.
      //continue는 반복문 안에서만 효율을 발휘한다.
    }



    if(articles.isEmpty()){//article이 비어있냐?? 라고물어보는함수// ==  article.size() == 0
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(num-1);

    if(num > articles.size()){
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.print("새 제목 : ");
    article.title = sc.next();
    System.out.print("새 내용 : ");
    article.body = sc.next();

    System.out.printf("%d번 게시물이 수정되었습니다.\n", num);
  }

  private static void actionUsrArticleWrite(Rq rq, Scanner sc, Map<String, String> params) {
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

  private static <params> void actionUsrArticleDetail(Rq rq, Map<String, String> params) {
    if(!params.containsKey("num")){
      System.out.println("번호를 입력해주세요");
      return;
    }

    int num = 0;

    try{
      num = Integer.parseInt(params.get("num")); // num이 들어있나 확인/ 들어있는 String값num을 int값으로 변환
    }
    catch(NumberFormatException e){
      System.out.println("번호를 정수형 형태로 입력해주세요");
      return; //continue 대신 쓸수 있는 함수는 return 이다. continue가 밑에껄 생략하고 위에올라가는것처럼 return도 똑같다.
      //continue는 반복문 안에서만 효율을 발휘한다.
    }



    if(articles.isEmpty()){//article이 비어있냐?? 라고물어보는함수// ==  article.size() == 0
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(num-1);

    if(num > articles.size()){
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println(" - 게시물 상세보기 - ");
    System.out.printf("번호 : %d\n", article.num);
    System.out.printf("제목 : \"%s\"\n", article.title);
    System.out.printf("내용 : \"%s\"\n", article.body);
  }

  private static void actionUsrArticleList(Rq rq, Map<String, String> params) {
    List<Article> filteredArticle = articles;

    String searchKeyword = params.get("searchKeyword");

    if(searchKeyword != null){
      searchKeyword = params.get("searchKeyword");

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

    boolean orderByIdDesc = true;
    if(params.containsKey("orderBy") && params.get("orderBy").equals("IdAsc")){
      orderByIdDesc = false;
    }
    if(orderByIdDesc){
      sortedArticle = Util.reverseList(sortedArticle);
    }

    for(Article article : sortedArticle){
      System.out.printf("%d / %s / %s\n", article.num, article.title, article.body);
    }

  }
}
