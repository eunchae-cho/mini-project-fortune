package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Employment;
import com.eomcs.util.Prompt;

public class EmploymentDetailCommand implements Command {

  List<Employment> employmentList;

  public EmploymentDetailCommand(List<Employment> list) {
    this.employmentList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
    	
      int no = Prompt.inputInt("번호? ", out, in);
      
      Employment employment = findByNo(no);

      if (employment == null) {
        out.println("해당 번호가 없습니다.");
        return;
      }

      out.printf("번호: %s\n", employment.getNo());
      out.printf("내용: %s\n", employment.getContent());
      

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생!\n");
      e.printStackTrace();
    }
  }

  private Employment findByNo(int no) {
    for (int i = 0; i < employmentList.size(); i++) {
    	Employment employment = employmentList.get(i);
      if (employment.getNo() == no) {
        return employment;
      }
    }
    return null;
  }
}
