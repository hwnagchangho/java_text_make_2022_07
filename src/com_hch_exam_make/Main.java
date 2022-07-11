package com_hch_exam_make;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    int articlelastNum = 0;

    while(true){
      System.out.printf("명령)");
      String cmd = sc.next();

      if (cmd.equals("exit")){
        break;
      }
      else if(cmd.equals("/usr/article/write")){
        System.out.println(" - 게시물 등록 - ");
        System.out.printf("제목 : ");
        String title = sc.next();
        System.out.printf("내용 : ");
        String body = sc.next();

        int num = ++articlelastNum; // == articleLastNum + 1; articleLastNum++;

        System.out.printf("%d번 게시물이 등록되었습니다.\n", num);
      }
      else{
        System.out.println("입력된 명령어 : " + cmd);
      }
    }
    System.out.println("== 프로그램 종료 ==");
  }
}