package test;

import model.User;
import model.UserLogic;

public class UserLogicTest {

	public static void main(String[] args) {

		UserLogicTest test = new UserLogicTest();

		User user1 = new User(0, "中島　安宏", "1234567");
		test.testExecute(user1); // 正常系

		User user2 = new User(0, "中島　安宏", "12345678901234567890123456789012345678901234567890");
		test.testExecute(user2); // エラー系

	}

	public void testExecute(User user) {

		UserLogic bo = new UserLogic();

		User user1 = bo.execute(user);

		if (user1 != null) {
			System.out.println("ユーザーの登録・取得に成功しました。");
		} else {
			System.out.println("ユーザーの登録・取得に失敗しました。");
		}

	}

}
