package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Love;
import com.eomcs.util.Prompt;

public class LoveAddCommand implements Command {

  List<Love> loveList;

  public LoveAddCommand(List<Love> list) {
    this.loveList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[애정운 등록]");

      Love love = new Love();
      love.setNo(Prompt.inputInt("번호? ", out, in));
      love.setContent(Prompt.inputString("내용? ", out, in));
     
      loveList.add(love);
      
      out.println("내용을 등록하였습니다.");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
}
