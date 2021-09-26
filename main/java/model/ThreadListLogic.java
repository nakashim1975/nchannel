package model;

import java.util.List;

import dao.ThreadListDAO;

/**
 * スレッドリストを取得するＢＯ
 */
public class ThreadListLogic {

	public List<ThreadList> get() {

		// ＤＡＯをインスタンス化
		ThreadListDAO dao = new ThreadListDAO();

		// スレッド一覧を取得してリストに格納
		List<ThreadList> thList = dao.findAll();

		// 戻り値の判定
		return thList;

	}

	public String getTitle(int thread_id) {

		// ＤＡＯをインスタンス化
		ThreadListDAO dao = new ThreadListDAO();

		// スレッドタイトルを取得
		String title = dao.findThreadTitle(thread_id);

		// 戻り値の判定
		return title;

	}
}
