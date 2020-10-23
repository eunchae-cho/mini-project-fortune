package com.eomcs.pms.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.eomcs.context.ApplicationContextListener;
import com.eomcs.pms.domain.Employment;
import com.eomcs.pms.domain.Love;
import com.eomcs.pms.domain.Money;
import com.google.gson.Gson;

// 게시물, 회원, 프로젝트, 작업 데이터를 파일에서 로딩하고 파일로 저장하는 일을 한다.
public class DataHandlerListener implements ApplicationContextListener {

	List<Character> charaterList = new ArrayList<>();
	File charaterFile = new File("./charater.json"); // 게시글을 저장할 파일 정보
	List<Employment> employmentList = new ArrayList<>();
	File employmentFile = new File("./employment.json");
	List<Money> moneyList = new ArrayList<>();
	File moneyFile = new File("./money.json");
	List<Love> loveList = new ArrayList<>();
	File loveFile = new File("./love.json");

	@Override
	public void contextInitialized(Map<String, Object> context) {
		loadData(charaterList, charaterFile, Character[].class);
		loadData(employmentList, employmentFile, Employment[].class);
		loadData(moneyList, moneyFile, Money[].class);
		loadData(loveList, loveFile, Love[].class);

		context.put("charaterList", charaterList);
		context.put("employmentList", employmentList);
		context.put("moneyList", moneyList);
		context.put("loveList", loveList);
	}

	@Override
	public void contextDestroyed(Map<String, Object> context) {
		saveData(charaterList, charaterFile);
		saveData(employmentList, employmentFile);
		saveData(moneyList, moneyFile);
		saveData(loveList, loveFile);

	}

	private <T> void loadData(Collection<T> list, // 객체를 담을 컬렉션
			File file, Type fileType) {
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader(file));
			list.addAll(Arrays.asList(new Gson().fromJson(in, fileType)));
			System.out.printf("'%s' 파일에서 총 %d 개의 객체를 로딩했습니다.\n", file.getName(), list.size());

		} catch (Exception e) {
			System.out.printf("'%s' 파일 읽기 중 오류 발생! - %s\n", file.getName(), e.getMessage());

		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}

	private void saveData(Collection<?> list, File file) {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(file));

			Gson gson = new Gson();
			String jsonStr = gson.toJson(list);
			out.write(jsonStr);

			out.flush();

			System.out.printf("총 %d 개의 객체를 '%s' 파일에 저장했습니다.\n", list.size(), file.getName());

		} catch (IOException e) {
			System.out.printf("객체를 '%s' 파일에  쓰는 중 오류 발생! - %s\n", file.getName(), e.getMessage());

		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}
}
