package model;

import java.util.Date;

/**
 * スレッドについてメッセージを表すEntity
 */
public class ThreadMessage {

	private int thread_id; // スレッドＩＤ
	private int message_id; // メッセージＩＤ
	private String message; // メッセージ
	private String name; // 投稿者名
	private Date create_date; // 投稿日時

	public ThreadMessage(int thread_id, int message_id, String message, String name, Date create_date) {
		this.thread_id = thread_id;
		this.message_id = message_id;
		this.message = message;
		this.name = name;
		this.create_date = create_date;
	}

	public int getThread_id() {
		return thread_id;
	}

	public int getMessage_id() {
		return message_id;
	}

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	public Date getCreate_date() {
		return create_date;
	}
}
