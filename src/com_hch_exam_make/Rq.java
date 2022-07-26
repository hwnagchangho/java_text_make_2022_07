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

  public String getUrlPath() {
    return path;
  }
}
