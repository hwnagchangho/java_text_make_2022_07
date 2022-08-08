package com_hch_exam_make.container;

import com_hch_exam_make.Session;
import com_hch_exam_make.controller.UsrArticleController;
import com_hch_exam_make.controller.UsrMemberController;
import com_hch_exam_make.repository.ArticleRepository;
import com_hch_exam_make.repository.MemberRepository;
import com_hch_exam_make.service.ArticleService;
import com_hch_exam_make.service.MemberService;
import lombok.Getter;

import java.util.Scanner;

public class Container {// container 는 공공재자원으로 사용할 것들을 모아두는것이고 한개만 존재할 수 있따.
  @Getter
  private static Scanner sc; //class안에 변수들은 모두 private처리를 해주어야한다. 이유는 다른곳에서 접근하지못하고 오직 해당클래스에서만 사용하기위해서
  @Getter
  private static Session session;
  @Getter
  private static MemberRepository memberRepository;
  @Getter
  private static ArticleRepository articleRepository;

  @Getter
  private static MemberService memberService;
  @Getter
  private static ArticleService articleService;


  @Getter
  private static UsrArticleController usrArticleController;
  @Getter
  private static UsrMemberController usrMemberController;

  static{ //static은 프로그램이 실행된뒤에 딱한번 실행된다? 그럼 프로그램이 실행된 후에 바로 생성이 된 상태?
    sc = new Scanner(System.in);

    session = new Session();

    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    memberService = new MemberService();
    articleService = new ArticleService();

    usrArticleController = new UsrArticleController();
    usrMemberController = new UsrMemberController();

  }

  public static Session getSession() {//**질문 : 세션은 원래 사용자마다 한명인데 우리 프로그램은 한명만 쓰기 때문에 이렇게 만들어준다? 무슨소리
    return session;
  }
}
//**질문 : 이 컨테이너는 App가 실행되기전 먼저 실행이되나?  static은 프로그램이 실행햇을 때 가장 먼저 딱한번 실행된다 따라서 static안에 있는
//객체는 미리 만들어져 있다.


//**질문 : 세션이 공공재이기 때문에 getSession을 여기다 만들고 반환받는건가? Session 클래스가 공공재이기 때문에 Container에 만들어 놓은 것이고
// getSession은 가지고 가는 녀석이다 따라서 이곳에서 session에 저장되있는 녀석들을 반환해준다.
