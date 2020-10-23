package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Love;
import com.eomcs.util.Prompt;

public class LoveUpdateCommand implements Command {

  List<Love> loveList;

  public LoveUpdateCommand(List<Love> list) {
    this.loveList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[애정운 변경]");
      int no = Prompt.inputInt("번호? ", out, in);
      
      Love love = findByNo(no);

      if (love == null) {
        out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      String content = Prompt.inputString(
          String.format("이름(%s)? ", love.getContent()), out, in);
    
     
      String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ", out, in);
      if (!response.equalsIgnoreCase("y")) {
        out.println("회원 변경을 취소하였습니다.");
        return;
      }

      love.setContent(content);
 

      out.println("회원을 변경하였습니다.");

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
