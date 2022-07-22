package com_hch_exam_make;

import java.util.*;

public class Main {

  static void makeTestData(List<Article> article){
    for( int i = 0; i < 100; i++){
      int id = i + 1;
      article.add(new Article(id, "제목" + id, "내용" + id));
    }


  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    int articleLastNum = 0;

    List<Article> articles = new ArrayList<>();

    makeTestData(articles);

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

        System.out.println(filteredArticle);

        System.out.println(" - 게시물 리스트 - ");
        System.out.println("-----------------");
        System.out.println("번호 / 제목 / 내용");
        System.out.println("-----------------");

        List<Article> sortedArticle = filteredArticle;

        boolean orderByIdDesc = true;
        if(params.get("orderBy") != null && params.get("orderBy").equals("IdAsc")){
          orderByIdDesc = false;
        }
        if(orderByIdDesc){
          sortedArticle = Util.reverseList(sortedArticle);
        }

          for(Article article : sortedArticle){
            System.out.printf("%d / %s / %s\n", article.num, article.title, article.body);
          }

      }
      else if (rq.getUrlPath().equals("/usr/article/detail")){

        if(!params.containsKey("num")){
          System.out.println("번호를 입력해주세요");
          continue;
        }

        int num = 0;

        try{
          num = Integer.parseInt(params.get("num")); // num이 들어있나 확인/ 들어있는 String값num을 int값으로 변환
        }
        catch(NumberFormatException e){
          System.out.println("번호를 정수형 형태로 입력해주세요");
          continue;
        }



        if(articles.isEmpty()){//article이 비어있냐?? 라고물어보는함수// ==  article.size() == 0
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        Article article = articles.get(num-1);

        if(num > articles.size()){
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println(" - 게시물 상세보기 - ");
        System.out.printf("번호 : %d\n", article.num);
        System.out.printf("제목 : \"%s\"\n", article.title);
        System.out.printf("내용 : \"%s\"\n", article.body);

      }
      else if(rq.getUrlPath().equals("/usr/article/write")){
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

  // 이 함수는 원본리스트를 훼손하지 않고, 새 리스트를 만듭니다. 즉 정렬이 반대인 복사본리스트를 만들어서 반환합니다.
  public static<T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for ( int i = list.size() - 1; i >= 0; i-- ) {
      reverse.add(list.get(i));
    }
    return reverse;
  }
}