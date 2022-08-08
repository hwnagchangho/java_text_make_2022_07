package com_hch_exam_make;

import com_hch_exam_make.container.Container;
import com_hch_exam_make.dto.Member;
import com_hch_exam_util.Util;

import java.util.Map;

public class Rq {
  private String url;
  // 필드추가가능
  private Map<String, String> params;
  private String urlPath;

  Rq(){

  }

  public void setCommand(String url) {
    urlPath = Util.getUrlPathFromUrl(url);
    params = Util.getParamsFromUrl(url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public int getIntParam(String paramsName, int defaultValue) {
    if (params.containsKey(paramsName) == false){
      return defaultValue;
    }
    try{
      return Integer.parseInt(params.get(paramsName)); // num이 들어있나 확인/ 들어있는 String값num을 int값으로 변환
    }
    catch(NumberFormatException e){
      return defaultValue; //continue 대신 쓸수 있는 함수는 return 이다. continue가 밑에껄 생략하고 위에올라가는것처럼 return도 똑같다.
      //continue는 반복문 안에서만 효율을 발휘한다.
    }
  }

  public String getParam(String paramsName, String defaultValue) {
    if (params.containsKey(paramsName) == false){
      return defaultValue;
    }
      return params.get(paramsName);
  }

  public String getUrlPath() {
    return urlPath;
  }

  public void setSessionAttr(String key, Object value) { // **질문 : object를 해야지 모든게 다 들어올 수있따는데 뭔 모든거?
    //value값에는 int stirng boolean등등 뭐가 들어올지 알수없다. 이것이 섞여서 들어올수도 있다. 따라서 Object타입으로 해서 다 받을수있게하기위함이다.
    Session session = Container.getSession(); //session이라는 객체에다가 가져온 녀석들을 저장

    session.setAttribute(key, value);  //속성설정// key, value를 저장하는 녀석
  }

  public void removeSessionAttr(String key) {
    Session session = Container.getSession();

    session.removeAttribute(key);
  }

  public boolean isLogined() {//이런녀석이 있냐 없냐 물어본다.
    return hasSessionAttr("loginedMember");
  }

  private boolean hasSessionAttr(String key) {
    Session session = Container.getSession();

    return session.hasAttribute(key);
  }

  public Member getLoginedMemeber() {
    return (Member) getSessionAttr("loginedMember");
  }

  private Object getSessionAttr(String key) {
    Session session = Container.getSession();


    return session.getAttribute(key);
  }

  public void logout() {
    removeSessionAttr("loginedMember");
  }

  public void login(Member member) {
    setSessionAttr("loginedMember", member);
  }
}
