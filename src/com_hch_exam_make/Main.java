package com_hch_exam_make;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  static void makeTestData(ArrayList<Article> article){

    article.add(new Article(1, "제목1", "내용1"));
    article.add(new Article(2, "제목2", "내용2"));
    article.add(new Article(3, "제목3", "내용3"));
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    int articlelastNum = 0;

    Article lastArticle = null;
    ArrayList<Article> article = new ArrayList<Article>();

    makeTestData(article);

    if ( article.size() > 0) {
      articlelastNum = article.get(article.size() - 1).num;
    }

    while(true){
      System.out.printf("명령)");
      String cmd = sc.next();

      if (cmd.equals("exit")){
        break;
      }
      else if (cmd.equals("/usr/article/list")){
        System.out.println(" - 게시물 리스트 - ");
        System.out.println("-----------------");
        System.out.println("번호 / 제목 / 내용");


        for(int i = article.size() - 1; i >= 0; i--){
          Article articles = article.get(i);
          System.out.printf("%d / %s / %s\n", articles.num, articles.title, articles.body);
        }

        System.out.println("-----------------");

      }
      else if (cmd.equals("/usr/article/detail")){

        if(lastArticle == null){
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println(" - 게시물 상세보기 - ");
        Article articles = lastArticle;
        System.out.printf("번호 : %d\n", articles.num);
        System.out.printf("제목 : \"%s\"\n", articles.title);
        System.out.printf("내용 : \"%s\"\n", articles.body);

      }
      else if(cmd.equals("/usr/article/write")){
        System.out.println(" - 게시물 등록 - ");
        System.out.printf("제목 : ");
        String title = sc.next();
        System.out.printf("내용 : ");
        String body = sc.next();

        int num = ++articlelastNum; // == articleLastNum + 1; articleLastNum++;

        Article articles = new Article(num, title, body);
        lastArticle = articles;

        //article.add(new Article(num, title, body)); => articles변수를 받아서 article에 넣어서 출력

        System.out.println("입력받은 객체 : "+ articles);
        System.out.printf("%d번 게시물이 등록되었습니다.\n", articles.num);
      }
      else{
        System.out.println("입력된 명령어 : " + cmd);
      }
    }
    System.out.println("== 프로그램 종료 ==");

    sc.close(); // Scanner 끝날 때 써주어야한다.
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