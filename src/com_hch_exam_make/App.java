package com_hch_exam_make;

import java.util.Map;

public class App {

  void run(){

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");


    while(true){
      System.out.print("명령)");
      String cmd = Container.sc.next();

      Rq rq= new Rq(cmd);
      Map<String, String> params = rq.getParams();


      if (cmd.equals("exit")){
        break;
      }
      else if (rq.getUrlPath().equals("/usr/article/list")){ //cmd를 rq.getUrlPath()로바꾸면 아무리 복잡한 명령어를 넣어도 ? 이전으로 호출
        Container.usrArticleController.actionList(rq); // container로가서 usrarticlecontroller가실행되면서actionlist로들어간ㄷ?
      }
      else if (rq.getUrlPath().equals("/usr/article/detail")){
        Container.usrArticleController.actionDetail(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/write")){
        Container.usrArticleController.actionWrite(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/modify")){
        Container.usrArticleController.actionModify(rq);
      }
      else if(rq.getUrlPath().equals("/usr/article/delete")){
        Container.usrArticleController.actionDelete(rq);
      }
      else if(rq.getUrlPath().equals("/usr/member/join")){
        Container.usrMemberController.actionJoin(rq); //write나 join나 rq는 굳이 필요없지않나?
      }
      else if(rq.getUrlPath().equals("/usr/member/login")){
        Container.usrMemberController.actionLogin(rq);
      }
      else{
        System.out.println("입력된 명령어 : " + cmd);
      }
    }
    System.out.println("== 프로그램 종료 ==");

    Container.sc.close();

  }

}
