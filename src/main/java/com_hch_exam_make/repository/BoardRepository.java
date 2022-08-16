package com_hch_exam_make.repository;

import com_hch_exam_make.dto.Board;
import com_hch_exam_util.Util;

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

  public int make(String code, String name) {
    int id = lastId + 1;
    String regDate = Util.getNowDateStr();
    String updateDate = regDate;

    Board board = new Board(id, regDate, updateDate, code, name);
    boards.add(board);
    lastId = id;

    return id;
  }
}
