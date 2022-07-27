package com_hch_exam_make;

import java.util.Scanner;

public class Container {// container 는 공공재자원으로 사용할 것들을 모아두는것이고 한개만 존재할 수 있따.
  static Scanner sc;

  static Session session;

  static UsrArticleController usrArticleController;

  static UsrMemberController usrMemberController;

  static{ //static은 프로그램이 실행된뒤에 딱한번 실행된다? 그래서 controller생성자는 여기에 객체가 생길때 실행이되나?
    sc = new Scanner(System.in);

    session = new Session();

    usrArticleController = new UsrArticleController();

    usrMemberController = new UsrMemberController();

  }

  public static Session getSession() {//**질문 : 세션은 원래 사용자마다 한명인데 우리 프로그램은 한명만 쓰기 때문에 이렇게 만들어준다? 무슨소리
    return session;
  }
}
//이 컨테이너는 App가 실행되기전 먼저 실행이되나?


//**질문 : 세션이 공공재이기 때문에 getSession을 여기다 만들고 반환받는건가?