package com_hch_exam_make.repository;

import com_hch_exam_make.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private List<Member> members;
  private int lastnum;

  public MemberRepository() {
    lastnum = 0;
    members = new ArrayList<>();
  }

  public int join(String loginId, String loginPw) {
    int num = lastnum + 1;
    Member member = new Member(num, loginId, loginPw);
    members.add(member);
    lastnum = num;

    return num;
  }

  public Member getMemberByLoginId(String loginId) {
    for (Member member : members) {
      if (member.getLoginId().equals(loginId)) {
        return member;
      }
    }

    return null;
  }

  public Member getMemberById(int id) {
    for (Member member : members) {
      if (member.getNum() == id) {
        return member;
      }
    }
    return null;
  }
}