package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.eomcs.pms.domain.Character;

public class CharacterListCommand implements Command {

  List<Character> characterList;

  public CharacterListCommand(List<Character> list) {
    this.characterList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {

  
    Iterator<Character> iterator = characterList.iterator();

    while (iterator.hasNext()) {
      Character character = iterator.next();
      out.printf("%d, %s\n",
    		  character.getNo(),
    		  character.getContent());
    }
  }

}
