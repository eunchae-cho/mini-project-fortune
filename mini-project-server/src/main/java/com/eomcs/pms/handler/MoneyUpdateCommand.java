package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Money;
import com.eomcs.util.Prompt;

public class MoneyUpdateCommand implements Command {

  List<Money> moneyList;

  public MoneyUpdateCommand(List<Money> list) {
    this.moneyList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[금전운 변경]");
      int no = Prompt.inputInt("번호? ", out, in);
      
      Money money = findByNo(no);

      if (money == null) {
        out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      String content = Prompt.inputString(
          String.format("이름(%s)? ", money.getContent()), out, in);
    
     
      String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ", out, in);
      if (!response.equalsIgnoreCase("y")) {
        out.println("회원 변경을 취소하였습니다.");
        return;
      }

      money.setContent(content);
 

      out.println("회원을 변경하였습니다.");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
  

  private Money findByNo(int no) {
    for (int i = 0; i < moneyList.size(); i++) {
    	Money money = moneyList.get(i);
      if (money.getNo() == no) {
        return money;
      }
    }
    return null;
  }
}
