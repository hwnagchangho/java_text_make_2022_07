package com_hch_exam_make;

import java.util.Map;
import java.util.Scanner;

public class App {

  void run(){
    Scanner sc = Container.sc;

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");


    while(true){
      System.out.print("명령)");
      String cmd = sc.next();

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
        Container.usrArticleController.actionWrite(rq, sc);
      }
      else if(rq.getUrlPath().equals("/usr/article/modify")){
        Container.usrArticleController.actionModify(rq, sc);
      }
      else if(rq.getUrlPath().equals("/usr/article/delete")){
        Container.usrArticleController.actionDelete(rq);
      }
      else{
        System.out.println("입력된 명령어 : " + cmd);
      }
    }
    System.out.println("== 프로그램 종료 ==");

    sc.close();

  }

}
