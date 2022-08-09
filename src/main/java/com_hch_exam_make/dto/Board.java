package com_hch_exam_make.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board { //ArticleController 에서 Board 관리
  private int id;
  private String regDate; //작성날짜
  private String updateDate;//수정날짜
  private String name;
  private String code; //게시판 종류 , 자유게시판인지 공지게시판인지... 등등

}
