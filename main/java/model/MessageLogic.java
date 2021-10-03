package model;

import java.util.List;

import dao.MessageDAO;

public class MessageLogic {

	public List<ThreadMessage> get(Message msg) {

		MessageDAO dao = new MessageDAO();

		List<ThreadMessage> thMsgList = dao.findByMessage(msg);

		if (!thMsgList.isEmpty()) {
			return thMsgList;
		} else {
			return null;
		}

	}

	public List<ThreadMessage> execute(Message msg) {

		MessageDAO dao = new MessageDAO();

		// メッセージ登録
		if (dao.Insert(msg)) {
			List<ThreadMessage> thMsgList = dao.findByMessage(msg);

			if (!thMsgList.isEmpty()) {
				return thMsgList;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	public int getMaxMessageId(List<ThreadMessage> thMsgList) {

		int maxMessageId = 0;

		for (ThreadMessage msg : thMsgList) {

			int currentId = msg.getMessage_id();

			if (currentId > maxMessageId) {
				maxMessageId = currentId;
			}
		}

		return maxMessageId;

	}

}
