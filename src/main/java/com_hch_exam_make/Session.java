package com_hch_exam_make;

import java.util.HashMap;
import java.util.Map;

public class Session {

  private Map<String, Object> store;

  public Session(){
    store = new HashMap<>();
  }
  public void setAttribute(String key, Object value) {
    store.put(key, value); //  내부적으로 Map에 저장
  }

  public Object getAttribute(String key) { //** 질문 : object로 해야 왜 모든 키들을 받을수 있지?   ==> key의 value값의 변수타입이 Object라?
    return store.get(key);
  }

  public void removeAttribute(String key) { //로그아웃 할 때 필요
    store.remove(key);
  }

  public boolean hasAttribute(String key) { //현재 로그인 된 member에 key가 있는지 없는지 물어보기 위해서 만듬/ 있으면 true 없으면 false
    return store.containsKey(key);
  }

}
