package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.eomcs.pms.domain.Employment;

public class EmploymentListCommand implements Command {

  List<Employment> employmentList;

  public EmploymentListCommand(List<Employment> list) {
    this.employmentList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {

  
    Iterator<Employment> iterator = employmentList.iterator();

    while (iterator.hasNext()) {
    	Employment employment = iterator.next();
      out.printf("%d, %s\n",
    		  employment.getNo(),
    		  employment.getContent());
    }
  }

}
