package model;

import dao.UserDAO;

public class LoginLogic {

	public User execute(Login login) {

		// ユーザーＤＡＯをインスタンス化
		UserDAO dao = new UserDAO();

		// ログイン情報からユーザーを取得
		User user = dao.findByLogin(login);

		// ユーザー情報が取得できたか判定
		return user;
	}

}
