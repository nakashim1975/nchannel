package test;

import java.util.List;

import model.Message;
import model.MessageLogic;
import model.ThreadMessage;

public class MessageLogicTest {

	public static void main(String[] args) {

		MessageLogicTest test = new MessageLogicTest();

		Message msg1 = new Message(1, null, 0);
		test.testGet(msg1); // 正常系

		Message msg2 = new Message(100, null, 0);
		test.testGet(msg2); // エラー系

		Message msg3 = new Message(3, "こんにちは！", 1);
		test.testExecute(msg3); // 正常系

		Message msg4 = new Message(50,
		        "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789",
		        0);
		test.testExecute(msg4); // エラー系

	}

	public void testGet(Message msg) {

		MessageLogic bo = new MessageLogic();

		List<ThreadMessage> thMsgList = bo.get(msg);

		if (!thMsgList.isEmpty()) {
			System.out.println("メッセージの取得に成功しました。");
		} else {
			System.out.println("メッセージの取得に失敗しました。");
		}

	}

	public void testExecute(Message msg) {

		MessageLogic bo = new MessageLogic();

		List<ThreadMessage> thMsgList = bo.execute(msg);

		if (!thMsgList.isEmpty()) {
			System.out.println("メッセージの登録・取得に成功しました。");
		} else {
			System.out.println("メッセージの登録・取得に失敗しました。");
		}

	}

}
