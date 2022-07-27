package com_hch_exam_make;

public class Member {
  int num;

  String loginId;

  String loginPw;

  public String toString(){
    return String.format("{num : %d, loginId : \"%s\", loginPw : \"%s\"}", num, loginId, loginPw);
  }

  Member(int num, String loginId, String loginPw){
    this.num = num;
    this.loginId = loginId;
    this.loginPw = loginPw;

  }
}
