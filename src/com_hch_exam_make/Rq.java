package com_hch_exam_make;

import java.util.Map;

public class Rq {
  String url;
  // 필드추가가능
  Map<String, String> params;
  String path;



  Rq(String url) {
    this.url = url;
    this.params = Util.getParamsFromUrl(url);
    this.path = Util.getUrlPathFromUrl(url);
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
    return path;
  }
}
