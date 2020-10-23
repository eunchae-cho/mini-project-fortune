package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Money;
import com.eomcs.util.Prompt;

public class MoneyAddCommand implements Command {

  List<Money> moneyList;

  public MoneyAddCommand(List<Money> list) {
    this.moneyList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[금전운 등록]");

      Money money = new Money();
      money.setNo(Prompt.inputInt("번호? ", out, in));
      money.setContent(Prompt.inputString("내용? ", out, in));
     
      moneyList.add(money);
      
      out.println("내용을 등록하였습니다.");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
}
