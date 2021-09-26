package test;

import java.util.Date;
import java.util.List;

import dao.ThreadListDAO;
import model.Thread;
import model.ThreadList;

public class ThreadListDAOTest {

	public static void main(String[] args) {

		ThreadListDAOTest test = new ThreadListDAOTest();

		// スレッドとメッセージを左側結合した結果を取得
		test.testfindAll();

		// 正常に登録できるパターン
		test.testInsert("明日が天気か真面目に語る", 1);
		// 登録できないパターン
		test.testInsert("01234567890123456789012345678901234567890123456789", 999);
	}

	public void testfindAll() {

		ThreadListDAO dao = new ThreadListDAO();

		List<ThreadList> thList = dao.findAll();

		if (thList.isEmpty() == false) {

			System.out.println("スレッドデータの取得に成功しました。");

			for (ThreadList tList : thList) {
				int thread_id = tList.getThread_id();
				String title = tList.getTitle();
				int user_id = tList.getUser_id();
				int mcount = tList.getMcount();
				Date mdate = tList.getMdate();
				System.out.println("thread_id:" + thread_id);
				System.out.println("title:" + title);
				System.out.println("user_id:" + user_id);
				System.out.println("mcount:" + mcount);
				System.out.println("mdate:" + mdate);
			}

		} else {
			System.out.println("スレッドデータの取得に失敗しました。");

		}

	}

	public void testInsert(String title, int user_id) {

		ThreadListDAO dao = new ThreadListDAO();

		Thread thread = new Thread(title, user_id);

		boolean flag = dao.Insert(thread);

		if (flag) {
			System.out.println("Threadテーブルへの登録が成功しました。");
		} else {
			System.out.println("Threadテーブルへの登録が失敗しました。");
		}

	}

}
