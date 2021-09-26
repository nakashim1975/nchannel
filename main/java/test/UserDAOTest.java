package test;

import dao.UserDAO;
import model.Login;
import model.User;

/**
 * ユーザーテーブルＤＡＯテストドライバ
 * 
 * @author bqc00
 *
 */
public class UserDAOTest {

	public static void main(String[] args) {

		UserDAOTest test = new UserDAOTest();

		test.testfindByLogin(1, "1234"); // ユーザーが見つかった場合
		test.testfindByLogin(1, "2222"); // ユーザーが見つからない場合

		test.testInsUser("織田信長", "12345");
		test.testInsUser("明智光秀", "012345678901234567890123456789");

	}

	public void testfindByLogin(int id, String pass) {

		// ログイン情報のコンストラクタ
		Login login = new Login(id, pass);

		UserDAO dao = new UserDAO();

		User user = dao.findByLogin(login);

		if (user != null) {
			System.out.println("testfindByLogin:ユーザー情報の取得に成功しました。");
		} else {
			System.out.println("testfindByLogin:ユーザー情報の取得に失敗しました。");
		}

	}

	public void testInsUser(String name, String pass) {
		User user = new User(0, name, pass);

		UserDAO dao = new UserDAO();

		boolean flag = dao.Insert(user);

		if (flag) {
			System.out.println("testInsUser:ユーザー情報の登録に成功しました。");
		} else {
			System.out.println("testInsUser:ユーザー情報の登録に失敗しました。");
		}

	}

}
