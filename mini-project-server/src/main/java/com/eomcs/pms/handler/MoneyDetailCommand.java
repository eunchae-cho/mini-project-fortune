package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Money;
import com.eomcs.util.Prompt;

public class MoneyDetailCommand implements Command {

  List<Money> moneyList;

  public MoneyDetailCommand(List<Money> list) {
    this.moneyList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
    	
      int no = Prompt.inputInt("번호? ", out, in);
      
      Money money = findByNo(no);

      if (money == null) {
        out.println("해당 번호가 없습니다.");
        return;
      }

      out.printf("번호: %s\n", money.getNo());
      out.printf("내용: %s\n", money.getContent());
      

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
