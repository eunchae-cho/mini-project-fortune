package com.eomcs.pms.listener;

import java.util.Map;
import com.eomcs.context.ApplicationContextListener;

public class AppInitListener implements ApplicationContextListener {
	  @Override
	  public void contextInitialized(Map<String,Object> context) {
	    System.out.println("* 운세 시스템에 오신 것을 환영합니다! *");
	  }

	  @Override
	  public void contextDestroyed(Map<String,Object> context) {
	    System.out.println("* 운세 시스템을 종료합니다! *");
	  }
}
