package com_hch_exam_make.interceptor;

import com_hch_exam_make.Rq;

public class NeedLogoutInterceptor implements Interceptor {

  @Override
  public boolean run(Rq rq){
    if(rq.isLogined() == false){
      return true;
    }

    switch (rq.getUrlPath()){
      case "/usr/member/login":
      case "/usr/member/join":
      case "/usr/member/findloginId":
      case "/usr/member/findloginPw":
        System.out.println("이미 로그인 되었습니다.");
        return false;
    }
    return true;
  }
}
