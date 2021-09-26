package model;

/**
 * ユーザーテーブルの情報を保持するEntity
 * 検索や登録などで使用する
 */
public class User {
	private int user_id; // ユーザーＩＤ
	private String name; // 名前
	private String pass; // パスワード

	public User(int user_id, String name, String pass) {
		this.user_id = user_id;
		this.name = name;
		this.pass = pass;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

}
