package com_hch_exam_make.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor //dto에는 기본적으로 생성자도 있어야하기 때문에 이녀석들이 있어야한다.
@Data
public class Member {
  private int id;

  private String loginId;

  private String loginPw;

}
