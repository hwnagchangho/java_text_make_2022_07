package com_hch_exam_make;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    System.out.printf("명령)");
    String cmd = sc.next();
    System.out.println("입력된 명령어 : " + cmd);
    System.out.println("== 프로그램 종료 ==");
  }
}