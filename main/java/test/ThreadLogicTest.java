package test;

import java.util.List;

import model.Thread;
import model.ThreadList;
import model.ThreadLogic;

public class ThreadLogicTest {

	public static void main(String[] args) {

		ThreadLogicTest test = new ThreadLogicTest();

		test.testExecute("明日雨になることを語るスレ", 1);

	}

	public void testExecute(String title, int user_id) {

		Thread thread = new Thread(title, user_id);

		ThreadLogic bo = new ThreadLogic();

		List<ThreadList> thList = bo.execute(thread, "テストメッセージ");

		if (!thList.isEmpty()) {
			System.out.println("スレッドテーブルへの登録が成功しました。");
		} else {
			System.out.println("スレッドテーブルへの登録が失敗しました。");
		}

	}
}
