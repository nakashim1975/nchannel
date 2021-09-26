package model;

/**
 * ログイン情報を表すEntity
 */
public class Login {

	private int user_id; // ユーザーＩＤ
	private String pass; // パスワード

	public Login(int user_id, String pass) {
		this.user_id = user_id;
		this.pass = pass;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
