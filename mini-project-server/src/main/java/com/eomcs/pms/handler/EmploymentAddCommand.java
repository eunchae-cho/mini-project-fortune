package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Employment;
import com.eomcs.util.Prompt;

public class EmploymentAddCommand implements Command {

  List<Employment> employmentList;

  public EmploymentAddCommand(List<Employment> list) {
    this.employmentList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[취업운 등록]");

      Employment employment = new Employment();
      employment.setNo(Prompt.inputInt("번호? ", out, in));
      employment.setContent(Prompt.inputString("내용? ", out, in));
     
      employmentList.add(employment);
      
      out.println("내용을 등록하였습니다.");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
}
