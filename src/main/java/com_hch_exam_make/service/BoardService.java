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
}
