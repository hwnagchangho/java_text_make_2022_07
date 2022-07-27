package com_hch_exam_make;

import java.util.ArrayList;
import java.util.List;

public class UsrMemberController {
  int MemberLastNum;

  List<Member> members;

  UsrMemberController(){
    MemberLastNum = 0;
    members = new ArrayList<>();

    makeTestData();

    if ( members.size() > 0) {
      MemberLastNum = members.get(members.size() - 1).num;
    }
  }

  void makeTestData(){
    for( int i = 0; i < 3; i++){
      int id = i + 1;
      members.add(new Member(id, "user" + id, "user" + id));
    }
  }
  public void actionJoin(Rq rq) {
    System.out.println(" - 회원가입 - ");
    System.out.print("로그인 아이디 : ");
    String loginId = Container.sc.next();
    System.out.print("로그인 비밀번호 : ");
    String loginPw = Container.sc.next();
    System.out.print("로그인 비밀번호 확인 : ");
    String loginPwConfirm = Container.sc.next();

    if(loginPw.equals(loginPwConfirm) == false){
      System.out.println("비밀번호가 일치하지않습니다.");
      return;
    }

    int num = ++MemberLastNum;

    Member member = new Member(num, loginId, loginPw);

    members.add(member);

    System.out.println(member.loginId + "님 가입을 환영합니다.");
    System.out.printf("%d번 회원이 생성 되었습니다.\n", member.num);
  }
}
