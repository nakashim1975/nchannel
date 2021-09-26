package model;

/**
 * メッセージ情報を表すEntity
 */
public class Message {

	private int thread_id; // スレッドＩＤ
	private int message_id; // メッセージＩＤ
	private String message; // メッセージ
	private int user_id; // ユーザーＩＤ

	public Message(int thread_id, String message, int user_id) {
		this.thread_id = thread_id;
		this.message = message;
		this.user_id = user_id;
	}

	public Message(int thread_id) {
		this.thread_id = thread_id;
	}

	public Message(int thread_id, int message_id) {
		this.thread_id = thread_id;
		this.message_id = message_id;
	}

	public int getThread_id() {
		return thread_id;
	}

	public int getmessage_id() {
		return message_id;
	}

	public String getMessage() {
		return message;
	}

	public int getUser_id() {
		return user_id;
	}

}
