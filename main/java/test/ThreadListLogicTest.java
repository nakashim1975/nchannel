package test;

import java.util.List;

import model.ThreadList;
import model.ThreadListLogic;

public class ThreadListLogicTest {

	public static void main(String[] args) {

		ThreadListLogicTest test = new ThreadListLogicTest();

		test.testget();

	}

	public void testget() {

		ThreadListLogic bo = new ThreadListLogic();

		List<ThreadList> thList = bo.get();

		if (!thList.isEmpty()) {
			System.out.println("スレッドリストの取得に成功しました。");
		} else {
			System.out.println("スレッドリストの取得に失敗しました。");
		}

	}

}
