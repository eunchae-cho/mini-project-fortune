package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.eomcs.pms.domain.Money;

public class MoneyListCommand implements Command {

  List<Money> moneyList;

  public MoneyListCommand(List<Money> list) {
    this.moneyList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {

  
    Iterator<Money> iterator = moneyList.iterator();

    while (iterator.hasNext()) {
    	Money money = iterator.next();
      out.printf("%d, %s\n",
    		  money.getNo(),
    		  money.getContent());
    }
  }

}
