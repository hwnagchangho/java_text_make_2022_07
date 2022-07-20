package com_hch_exam_make;

import java.util.*;

public class Main {

  static void makeTestData(List<Article> article){

    article.add(new Article(1, "제목1", "내용1"));
    article.add(new Article(2, "제목2", "내용2"));
    article.add(new Article(3, "제목3", "내용3"));
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    int articlelastNum = 0;

    List<Article> article = new ArrayList<>();

    makeTestData(article);

    if ( article.size() > 0) {
      articlelastNum = article.get(article.size() - 1).num;
    }

    while(true){
      System.out.printf("명령)");
      String cmd = sc.next();

      Rq rq= new Rq(cmd);
      rq.getParams();

      if (rq.getUrlPath().equals("exit")){
        break;
      }
      else if (rq.getUrlPath().equals("/usr/article/list")){ //cmd를 rq.getUrlPath()로바꾸면 아무리 복잡한 명령어를 넣어도 ?이전으로 호출
        System.out.println(" - 게시물 리스트 - ");
        System.out.println("-----------------");
        System.out.println("번호 / 제목 / 내용");


        for(int i = article.size() - 1; i >= 0; i--){
          Article articles = article.get(i);
          System.out.printf("%d / %s / %s\n", articles.num, articles.title, articles.body);
        }

        System.out.println("-----------------");

      }
      else if (rq.getUrlPath().equals("/usr/article/detail")){


        int num = Integer.parseInt(rq.getParams().get("num")); // num이 들어있나 확인/ 들어있는 String값num을 int값으로 변환


        if(article.isEmpty()){//article이 비어있냐?? 라고물어보는함수// ==  article.size() == 0
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        Article articles = article.get(num-1);

        if(num > article.size()){
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println(" - 게시물 상세보기 - ");
        System.out.printf("번호 : %d\n", articles.num);
        System.out.printf("제목 : \"%s\"\n", articles.title);
        System.out.printf("내용 : \"%s\"\n", articles.body);

      }
      else if(rq.getUrlPath().equals("/usr/article/write")){
        System.out.println(" - 게시물 등록 - ");
        System.out.printf("제목 : ");
        String title = sc.next();
        System.out.printf("내용 : ");
        String body = sc.next();

        int num = ++articlelastNum; // == articleLastNum + 1; articleLastNum++;

        Article articles = new Article(num, title, body);

        article.add(articles);

        System.out.println("입력받은 객체 : "+ articles);
        System.out.printf("%d번 게시물이 등록되었습니다.\n", articles.num);
      }
      else{
        System.out.println("입력된 명령어 : " + cmd);
      }
    }
    System.out.println("== 프로그램 종료 ==");

    sc.close();
  }
}

class Article{
  int num;

  String title;

  String body;

  public String toString(){
    return String.format("{num : %d, title : \"%s\", body : \"%s\"}", num, title, body);
  }

  Article(int num_, String title_, String body_){
    this.num = num_;
    this.title = title_;
    this.body = body_;

  }

}

class Rq {
  String url;
  // 필드추가가능
  Map<String, String> params;
  String path;


  Rq(String url) {
    this.url = url;
    this.params = Util.getParamsFromUrl(url);
    this.path = Util.getUrlPathFromUrl(url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return path;
  }
}

// 수정불가능
class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2); // 2개 이상 나뉘는건 원치 않는다.
    if (urlBits.length == 1) { // 나뉘지 않았다면. `?` 가 없다는 뜻, 즉 더이상 할일이 없다는 뜻
      return params;
    }
    String queryStr = urlBits[1];
    for (String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2); // 2개 이상 나뉘는건 원치 않는다.
      if (bits.length == 1) { // 나뉘지 않았다면 `=`가 없다는 뜻, 즉 잘못된 파라미터라는 뜻
        continue; // for 문 상단으로 돌아간다. 즉 아래 코드가 스킵된다.
      }
      params.put(bits[0], bits[1]);
    }
    return params;
  }
  static String getUrlPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }
}