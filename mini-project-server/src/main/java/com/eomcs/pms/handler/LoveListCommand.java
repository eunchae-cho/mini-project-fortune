package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.eomcs.pms.domain.Love;

public class LoveListCommand implements Command {

  List<Love> loveList;

  public LoveListCommand(List<Love> list) {
    this.loveList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {

  
    Iterator<Love> iterator = loveList.iterator();

    while (iterator.hasNext()) {
    	Love love = iterator.next();
      out.printf("%d, %s\n",
    		  love.getNo(),
    		  love.getContent());
    }
  }

}
