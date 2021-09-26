package test;

import java.util.List;

import dao.MessageDAO;
import model.Message;
import model.ThreadMessage;

public class MessageDAOTest {

	public static void main(String[] args) {

		MessageDAOTest test = new MessageDAOTest();

		test.testInsert(1, "明日は明日の風が吹く。間違いない。", 1); // 正常系
		test.testInsert(1,
				"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890",
				1); // エラー

		test.testfindByMessage(1); // 正常系
		test.testfindByMessage(2); // エラー

	}

	public void testfindByMessage(int thread_id) {

		Message msg = new Message(thread_id, null, 0);

		MessageDAO dao = new MessageDAO();

		List<ThreadMessage> thMsgList = dao.findByMessage(msg);

		if (!thMsgList.isEmpty()) {

			System.out.println("メッセージデータの取得に成功しました。");

			for (ThreadMessage tmsg : thMsgList) {
				System.out.println("Message:" + tmsg.getMessage());
				System.out.println("Name:" + tmsg.getName());
				System.out.println("thread_id:" + tmsg.getThread_id());
				System.out.println("cdate:" + tmsg.getCreate_date());
			}

		} else {
			System.out.println("メッセージデータの取得に失敗しました。");
		}
	}

	public void testInsert(int thread_id, String message, int user_id) {

		// ＩＮＳＥＲＴ文のテスト
		Message msg = new Message(thread_id, message, user_id);

		MessageDAO dao = new MessageDAO();

		boolean flag = dao.Insert(msg);

		if (flag) {
			System.out.println("メッセージデータの登録に成功しました。");
		} else {
			System.out.println("メッセージデータの登録に失敗しました。");
		}

	}

}
