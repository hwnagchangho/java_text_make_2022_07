package com_hch_exam_make.service;

import com_hch_exam_make.container.Container;
import com_hch_exam_make.dto.Board;
import com_hch_exam_make.repository.BoardRepository;

public class BoardService {

  BoardRepository boardRepository;

  public BoardService(){
    boardRepository = Container.getBoardRepository();
  }
  public Board getBoardById(int boardId) {
    return boardRepository.getBoardById(boardId);
  }

  public void makeTestData() {
    make("notice", "공지사항");
    make("free", "자유");
  }

  private int make(String code, String name) {
    return boardRepository.make(code, name);
  }
}
