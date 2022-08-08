package com_hch_exam_make.service;

import com_hch_exam_make.container.Container;
import com_hch_exam_make.dto.Member;
import com_hch_exam_make.repository.MemberRepository;

public class MemberService {

  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.getMemberRepository();
  }
  public void makeTestData() {
    for (int i = 0; i < 3; i++) {
      String loginId = "user" + (i + 1);
      String loginPw = loginId;

      join(loginId, loginPw);
    }
  }

  public int join(String loginId, String loginPw) {
    return memberRepository.join(loginId, loginPw);
  }

  public Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }
}
