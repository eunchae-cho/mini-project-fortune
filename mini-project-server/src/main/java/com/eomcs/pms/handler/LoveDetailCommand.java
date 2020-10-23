package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Love;
import com.eomcs.util.Prompt;

public class LoveDetailCommand implements Command {

  List<Love> loveList;

  public LoveDetailCommand(List<Love> list) {
    this.loveList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
    	
      int no = Prompt.inputInt("번호? ", out, in);
      
      Love love = findByNo(no);

      if (love == null) {
        out.println("해당 번호가 없습니다.");
        return;
      }

      out.printf("번호: %s\n", love.getNo());
      out.printf("내용: %s\n", love.getContent());
      

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  private Love findByNo(int no) {
    for (int i = 0; i < loveList.size(); i++) {
    	Love love = loveList.get(i);
      if (love.getNo() == no) {
        return love;
      }
    }
    return null;
  }
}
