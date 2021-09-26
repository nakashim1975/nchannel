package model;

import java.util.Date;

/**
 * スレッド一覧を表すEntity
 */
public class ThreadList {

	private int thread_id; // スレッドＩＤ
	private int user_id; // ユーザーＩＤ
	private String title; // タイトル
	private int mcount; // メッセージ数
	private Date mdate; // 最終メッセージ投稿日時

	public ThreadList(int thread_id, int user_id, String title, int mcount, Date mdate) {
		this.thread_id = thread_id;
		this.user_id = user_id;
		this.title = title;
		this.mcount = mcount;
		this.mdate = mdate;
	}

	public int getMcount() {
		return mcount;
	}

	public Date getMdate() {
		return mdate;
	}

	public int getThread_id() {
		return thread_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getTitle() {
		return title;
	}

}
