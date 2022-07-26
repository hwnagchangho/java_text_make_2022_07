package com_hch_exam_make;

public class Article {
  int num;

  String title;

  String body;

  public String toString(){
    return String.format("{num : %d, title : \"%s\", body : \"%s\"}", num, title, body);
  }

  Article(int num_, String title_, String body_){
    this.num = num_;
    this.title = title_;
    this.body = body_;

  }
}
