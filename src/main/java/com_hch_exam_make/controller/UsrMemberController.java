package com_hch_exam_make.controller;

import com_hch_exam_make.Rq;
import com_hch_exam_make.container.Container;
import com_hch_exam_make.dto.Member;
import com_hch_exam_make.service.MemberService;

public class UsrMemberController {
  private MemberService memberService;

  public UsrMemberController(){
    memberService = Container.getMemberService();

    memberService.makeTestData();

  }

  public void actionJoin(Rq rq) {
    System.out.println(" - 회원가입 - ");
    System.out.print("로그인 아이디 : ");
    String loginId = Container.getSc().next();
    System.out.print("로그인 비밀번호 : ");
    String loginPw = Container.getSc().next();
    System.out.print("로그인 비밀번호 확인 : ");
    String loginPwConfirm = Container.getSc().next();

    if(loginPw.equals(loginPwConfirm) == false){
      System.out.println("비밀번호가 일치하지않습니다.");
      return;
    }

    int id = memberService.join(loginId, loginPw);

    Member member = memberService.getMemberByLoginId(loginId);

    System.out.println(member.getLoginId() + "님 가입을 환영합니다.");
    System.out.printf("%d번 회원이 생성 되었습니다.\n", member.getId());
  }

  public void actionLogin(Rq rq) {

    System.out.print("로그인 아이디 : ");
    String loginId = Container.getSc().next().trim();

    if(loginId.length() == 0){
      System.out.println("아이디를 입력해주세요");
      return;
    }

    Member member = memberService.getMemberByLoginId(loginId);

    if(member == null){
      System.out.println("해당 회원이 존재하지 않습니다.");
      return;
    }

    System.out.print("로그인 비밀번호 : ");
    String loginPw = Container.getSc().next().trim();

    if(loginPw.length() == 0){
      System.out.println("비밀번호를 입력해주세요");
      return;
    }

    if(member.getLoginPw().equals(loginPw) == false){
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    rq.login(member);

    System.out.printf("%s님 환영합니다.\n", member.getLoginId());

  }


  public void actionLogout(Rq rq) {
    rq.logout();

    System.out.println("로그아웃 되었습니다.");
  }
}
