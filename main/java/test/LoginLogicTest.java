package test;

import model.Login;
import model.LoginLogic;
import model.User;

public class LoginLogicTest {

	public static void main(String[] args) {

		LoginLogicTest test = new LoginLogicTest();

		test.testExecute(1, "12345"); // ログイン成功
		test.testExecute(2, "12345"); // ログイン失敗

	}

	public void testExecute(int user_id, String pass) {
		Login login = new Login(user_id, pass);

		LoginLogic bo = new LoginLogic();

		User user = bo.execute(login);
		if (user != null) {
			System.out.println("ログイン処理に成功しました。");
		} else {
			System.out.println("ログイン処理に失敗しました。");
		}

	}

}
