package model;

import java.util.List;

import dao.MessageDAO;
import dao.ThreadListDAO;

public class ThreadLogic {

	public List<ThreadList> execute(Thread thread, String msg) {

		ThreadListDAO tdao = new ThreadListDAO();

		/*----------------------*/
		/*-- スレッド登録処理 --*/
		/*----------------------*/
		if (tdao.Insert(thread)) {

			/*------------------------*/
			/*-- メッセージ登録処理 --*/
			/*------------------------*/
			int thread_id = tdao.findMaxThreadID(thread);
			Message msgjb = new Message(thread_id, msg, thread.getUser_id());
			MessageDAO mdao = new MessageDAO();
			mdao.Insert(msgjb);

			/*--------------------------*/
			/*-- スレッド一覧取得処理 --*/
			/*--------------------------*/
			List<ThreadList> thList = tdao.findAll();

			if (!thList.isEmpty()) {
				return thList;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

}
