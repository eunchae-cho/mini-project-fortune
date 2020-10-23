package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.eomcs.pms.domain.Character;
import com.eomcs.util.Prompt;

public class CharacterDetailCommand implements Command {

  List<Character> characterList;

  public CharacterDetailCommand(List<Character> list) {
    this.characterList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
    	
      int no = Prompt.inputInt("번호? ", out, in);
      
      Character character = findByNo(no);

      if (character == null) {
        out.println("해당 번호가 없습니다.");
        return;
      }

      out.printf("번호: %s\n", character.getNo());
      out.printf("내용: %s\n", character.getContent());
      

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  private Character findByNo(int no) {
    for (int i = 0; i < characterList.size(); i++) {
    	Character character = characterList.get(i);
      if (character.getNo() == no) {
        return character;
      }
    }
    return null;
  }
}
