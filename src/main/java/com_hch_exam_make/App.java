package com_hch_exam_make;

import com_hch_exam_make.container.Container;
import com_hch_exam_make.dto.Member;
import com_hch_exam_make.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

  private void forTestLoginByMemberId(int id) {
    Member member = Container.getMemberService().getMemberById(id);

    new Rq().login(member);

  }

  public void run(){

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    forTestLoginByMemberId(1);


    while(true){
      Rq rq = new Rq();

      String promptName = "명령";

      if (rq.isLogined()){
        Member loginedMember = rq.getLoginedMemeber();
        promptName = loginedMember.getLoginId();
      }

      System.out.printf("%s)", promptName);
      String cmd = Container.getSc().nextLine();


      rq.setCommand(cmd); //rq를 또만들어서는 안되기 때문에 따로 메서드를 만든다.

      if(runInterceptor(rq) == false){
        continue;
      }

      Map<String, String> params = rq.getParams();


      if (cmd.equals("exit")){
        break;
      }
      else if (rq.getUrlPath().equals("/usr/article/list")){ //cmd를 rq.getUrlPath()로바꾸면 아무리 복잡한 명령어를 넣어도 ? 이전으로 호출
        Container.getUsrArticleController().actionList(rq); // container로가서 usrarticlecontroller가실행되면서actionlist로들어간ㄷ?
      }
      else if (rq.getUrlPath().equals("/usr/article/detail")){
        Container.getUsrArticleController().actionDetail(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/write")){
        Container.getUsrArticleController().actionWrite(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/modify")){
        Container.getUsrArticleController().actionModify(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/delete")){
        Container.getUsrArticleController().actionDelete(rq);
      }
      else if(rq.getUrlPath().equals("/usr/member/join")){
        Container.getUsrMemberController().actionJoin(rq); //write나 join나 rq는 굳이 필요없지않나?
      }
      else if(rq.getUrlPath().equals("/usr/member/login")){
        Container.getUsrMemberController().actionLogin(rq);
      }
      else if(rq.getUrlPath().equals("/usr/member/logout")){
        Container.getUsrMemberController().actionLogout(rq);
      }
      else{
        System.out.println("입력된 명령어 : " + cmd);
      }
    }
    System.out.println("== 프로그램 종료 ==");

    Container.getSc().close();

  }

  private boolean runInterceptor(Rq rq) {
    List<Interceptor> interceptors = new ArrayList<>();
    interceptors.add(Container.getNeedLoginInterceptor());
    interceptors.add(Container.getNeedLogoutInterceptor());

    for(Interceptor interceptor : interceptors){
      if(interceptor.run(rq) == false){
        return false;
      }
    }
    return true;
  }

}
