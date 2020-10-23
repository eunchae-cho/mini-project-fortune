package com.eomcs.pms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.eomcs.pms.domain.Character;
import com.eomcs.pms.domain.Employment;
import com.eomcs.pms.domain.Love;
import com.eomcs.pms.domain.Money;
import com.eomcs.util.Prompt;

public class FortuneServiceCommand implements Command {
	
	List<Character> chracterList;
	List<Employment> employmentList;
	List<Money> moneyList;
	List<Love> loveList;
	
	CharacterDetailCommand characterDetailCommand = new CharacterDetailCommand(chracterList);
	EmploymentDetailCommand employmentDetailCommand = new EmploymentDetailCommand(employmentList);
	MoneyDetailCommand moneyDetailCommand = new MoneyDetailCommand(moneyList);
	LoveDetailCommand loveDetailCommand = new LoveDetailCommand(loveList);

	@Override
	public void execute(PrintWriter out, BufferedReader in) {
		try {

			while (true) {
				out.println(" ");
				out.println("==================================================== ");
				out.println(" ");
				out.println("                    [ 운세 보기 ]");
				out.println(" ");
				out.println("====================================================");
				out.println("  원하시는 운세 정보의 번호를 입력해주세요.");
				out.println(" ");
				out.println("   1. 성격운");
				out.println("   2. 취업운");
				out.println("   3. 금전운");
				out.println("   4. 애정운");
				out.println("----------------------------------------------------");
				out.println("   그만하기 : quit");
				out.println(" ");
				out.println("====================================================");
				out.println(" ");
				String input = Prompt.inputString("입력> ", out, in);

				if (input.equalsIgnoreCase("quit")) {
					break;
				}

				switch (input) {
				case "1":
					out.println(" ");
					out.println("====================================================");
					out.println(" ");
					out.println("                     [ 성격운  ]");
					out.println(" ");
					out.println("  1 ~ 5번까지의 숫자중에 원하는 숫자 하나를 골라주세요:)");
					out.println(" ");
					out.println("  1번    2번     3번    4번     5번  ");
					out.println(" ");
					out.println("----------------------------------------------------");
					characterDetailCommand.execute(out, in);
					out.println(" ");
					out.println("====================================================");
					out.println(" ");
					input = Prompt.inputString("다른 운세를 보시겠습니까?(y/N) ", out, in);
					out.println(" ");

					if (input.equalsIgnoreCase("y")) {
						break;
					} else {
						out.println("처음으로 돌아갑니다.");
						out.println(" ");
						return;
					}
				
				case "2":
					out.println(" ");
					out.println("====================================================");
					out.println(" ");
					out.println("                     [ 취업운  ]");
					out.println(" ");
					out.println("  1 ~ 5번까지의 숫자중에 원하는 숫자 하나를 골라주세요:)");
					out.println(" ");
					out.println("  1번    2번     3번    4번     5번  ");
					out.println(" ");
					out.println("----------------------------------------------------");
					employmentDetailCommand.execute(out, in);
					out.println("====================================================");
					out.println(" ");
					input = Prompt.inputString("다른 운세를 보시겠습니까?(y/N) ", out, in);
					out.println(" ");

					if (input.equalsIgnoreCase("y")) {
						break;
					} else {
						out.println("처음으로 돌아갑니다.");
						out.println(" ");
						return;
					}
					
				case "3":
					out.println(" ");
					out.println("====================================================");
					out.println(" ");
					out.println("                     [ 금전운  ]");
					out.println(" ");
					out.println("  1 ~ 5번까지의 숫자중에 원하는 숫자 하나를 골라주세요:)");
					out.println(" ");
					out.println("  1번    2번     3번    4번     5번  ");
					out.println(" ");
					out.println("----------------------------------------------------");
					moneyDetailCommand.execute(out, in);
					out.println("====================================================");
					out.println(" ");
					input = Prompt.inputString("다른 운세를 보시겠습니까?(y/N) ", out, in);
					out.println(" ");

					if (input.equalsIgnoreCase("y")) {
						break;
					} else {
						out.println("처음으로 돌아갑니다.");
						out.println(" ");
						return;
					}
					
				case "4":
					out.println(" ");
					out.println("====================================================");
					out.println(" ");
					out.println("                     [ 애정운  ]");
					out.println(" ");
					out.println("  1 ~ 5번까지의 숫자중에 원하는 숫자 하나를 골라주세요:)");
					out.println(" ");
					out.println("  1번    2번     3번    4번     5번  ");
					out.println(" ");
					out.println("----------------------------------------------------");
					loveDetailCommand.execute(out, in);
					out.println("====================================================");
					out.println(" ");
					input = Prompt.inputString("다른 운세를 보시겠습니까?(y/N) ", out, in);
					out.println(" ");

					if (input.equalsIgnoreCase("y")) {
						break;
					} else {
						out.println("처음으로 돌아갑니다.");
						out.println(" ");
						return;
					}
					
				default:
					out.println(" ");
					out.println("잘못된 번호를 입력하셨습니다.");
					out.println("다시 입력해주세요.");
					out.println(" ");
					break;
				}
			}

			out.println();
			out.flush();

		} catch (Exception e) {
			out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
		}

	}
		

}
