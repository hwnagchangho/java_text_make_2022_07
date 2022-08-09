package com_hch_exam_make.repository;

import com_hch_exam_make.dto.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
  private int lastId;
  private List<Board> boards;

  public BoardRepository() {
    lastId = 0;
    boards = new ArrayList<>();
  }
  public Board getBoardById(int boardId) {
    for(Board board : boards){
      if(board.getId() == boardId){
        return board;
      }
    }
    return null;
  }
}
