package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Character;
import com.eomcs.util.Prompt;

public class CharacterAddCommand implements Command {

  List<Character> characterList;

  public CharacterAddCommand(List<Character> list) {
    this.characterList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[성격운 등록]");

      Character character = new Character();
      character.setNo(Prompt.inputInt("번호? ", out, in));
      character.setContent(Prompt.inputString("내용? ", out, in));
     
      characterList.add(character);
      
      out.println("내용을 등록하였습니다.");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }
}
