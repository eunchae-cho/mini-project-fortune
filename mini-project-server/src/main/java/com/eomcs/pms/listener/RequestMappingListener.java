package com.eomcs.pms.listener;

import java.util.List;
import java.util.Map;

import com.eomcs.context.ApplicationContextListener;
import com.eomcs.pms.domain.Character;
import com.eomcs.pms.domain.Employment;
import com.eomcs.pms.domain.Love;
import com.eomcs.pms.domain.Money;
import com.eomcs.pms.handler.CharacterAddCommand;
import com.eomcs.pms.handler.CharacterDeleteCommand;
import com.eomcs.pms.handler.CharacterDetailCommand;
import com.eomcs.pms.handler.CharacterListCommand;
import com.eomcs.pms.handler.CharacterUpdateCommand;
import com.eomcs.pms.handler.ChatFortune;
import com.eomcs.pms.handler.EmploymentAddCommand;
import com.eomcs.pms.handler.EmploymentDeleteCommand;
import com.eomcs.pms.handler.EmploymentDetailCommand;
import com.eomcs.pms.handler.EmploymentListCommand;
import com.eomcs.pms.handler.EmploymentUpdateCommand;
import com.eomcs.pms.handler.FortuneServiceCommand;
import com.eomcs.pms.handler.LoveAddCommand;
import com.eomcs.pms.handler.LoveDeleteCommand;
import com.eomcs.pms.handler.LoveDetailCommand;
import com.eomcs.pms.handler.LoveListCommand;
import com.eomcs.pms.handler.LoveUpdateCommand;
import com.eomcs.pms.handler.MoneyAddCommand;
import com.eomcs.pms.handler.MoneyDeleteCommand;
import com.eomcs.pms.handler.MoneyDetailCommand;
import com.eomcs.pms.handler.MoneyListCommand;
import com.eomcs.pms.handler.MoneyUpdateCommand;
// 클라이언트 요청을 처리할 커맨드 객체를 준비한다.
public class RequestMappingListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String,Object> context) {
    // 옵저버가 작업한 결과를 맵에서 꺼낸다.
    List<Character> characterList = (List<Character>) context.get("characterList");
    List<Employment> employmentList =   (List<Employment>) context.get("employmentList");
    List<Money> moneyList = (List<Money>) context.get("moneyList");
    List<Love> loveList = (List<Love>) context.get("loveList");
   
    context.put("/character/add", new CharacterAddCommand(characterList));
    context.put("/character/list", new CharacterListCommand(characterList));
    context.put("/character/detail", new CharacterDetailCommand(characterList));
    context.put("/character/update", new CharacterUpdateCommand(characterList));
    context.put("/character/delete", new CharacterDeleteCommand(characterList));
    
    context.put("/employment/add", new EmploymentAddCommand(employmentList));
    context.put("/employment/list", new EmploymentListCommand(employmentList));
    context.put("/employment/detail", new EmploymentDetailCommand(employmentList));
    context.put("/employment/update", new EmploymentUpdateCommand(employmentList));
    context.put("/employment/delete", new EmploymentDeleteCommand(employmentList));
    
    context.put("/money/add", new MoneyAddCommand(moneyList));
    context.put("/money/list", new MoneyListCommand(moneyList));
    context.put("/money/detail", new MoneyDetailCommand(moneyList));
    context.put("/money/update", new MoneyUpdateCommand(moneyList));
    context.put("/money/delete", new MoneyDeleteCommand(moneyList));
    
    context.put("/love/add", new LoveAddCommand(loveList));
    context.put("/love/list", new LoveListCommand(loveList));
    context.put("/love/detail", new LoveDetailCommand(loveList));
    context.put("/love/update", new LoveUpdateCommand(loveList));
    context.put("/love/delete", new LoveDeleteCommand(loveList));
    
    context.put("/fortune", new FortuneServiceCommand());
    context.put("/chatfortune", new ChatFortune());
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
  }
}
