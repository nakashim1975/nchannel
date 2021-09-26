package model;

/**
 * スレッド一覧の検索や登録の為のEntity
 */
public class Thread {

	private String title;
	private int user_id;

	public Thread(String title, int user_id) {
		this.title = title;
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public int getUser_id() {
		return user_id;
	}

}
