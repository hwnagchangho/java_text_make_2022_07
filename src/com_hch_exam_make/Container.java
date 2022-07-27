package com_hch_exam_make;

import java.util.Scanner;

public class Container {// container 는 공공재자원으로 사용할 것들을 모아두는것이고 한개만 존재할 수 있따.
  static Scanner sc;

  static UsrArticleController usrArticleController;

  static{ //static은 프로그램이 실행된뒤에 딱한번 실행된다? 그래서 controller생성자는 여기에 객체가 생길때 실행이되나?
    sc = new Scanner(System.in);

    usrArticleController = new UsrArticleController();
  }
}
//이 컨테이너는 app이 실행되기전 먼저 실행이되나?
