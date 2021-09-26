package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Thread;
import model.ThreadList;

/**
 * スレッドテーブルを担当するＤＡＯ
 * 
 * @author bqc00
 *
 */
public class ThreadListDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/nchannel?characterEncoding=utf8&useSSL=false&serverTimezone=JST&rewriteBatchedStatements=true";
	private final String DB_USER = "root";
	private final String DB_PASS = "admin";

	/**
	 * スレッドタイトルとメッセージ件数を取得する
	 * 
	 * @return List<ThreadList> スレッドリスト
	 */
	public List<ThreadList> findAll() {

		// リストを定義
		List<ThreadList> thList = new ArrayList<ThreadList>();

		// データベースへの接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQL文の作成
			String sql = "SELECT "
			        + "A.THREAD_ID, "
			        + "A.TITLE, "
			        + "A.CREATE_USER, "
			        + "MAX(B.CREATE_DATE) AS MDATE, "
			        + "COUNT(B.MESSAGE_ID) AS MCOUNT "
			        + "FROM THREAD AS A "
			        + "LEFT JOIN MESSAGE AS B "
			        + "ON A.THREAD_ID = B.THREAD_ID "
			        + "GROUP BY "
			        + "A.THREAD_ID, "
			        + "A.TITLE, "
			        + "A.CREATE_USER "
			        + "ORDER BY "
			        + "B.CREATE_DATE DESC";

			System.out.println(sql);
			PreparedStatement pStmt = con.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				int thread_id = rs.getInt("THREAD_ID");
				int user_id = rs.getInt("CREATE_USER");
				String title = rs.getString("TITLE");
				Date mdate = rs.getDate("MDATE");
				int mcount = rs.getInt("MCOUNT");

				ThreadList threadList = new ThreadList(thread_id, user_id, title, mcount, mdate);

				thList.add(threadList);
			}

			rs.close();
			pStmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return thList;
	}

	public String findThreadTitle(int thread_id) {

		String title = null;

		// データベースへの接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQL文の作成
			String sql = "SELECT "
			        + "THREAD_ID, "
			        + "TITLE "
			        + "FROM THREAD "
			        + "WHERE THREAD_ID = ? ";

			System.out.println(sql);
			PreparedStatement pStmt = con.prepareStatement(sql);

			pStmt.setInt(1, thread_id);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				title = rs.getString("TITLE");

			}

			rs.close();
			pStmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return title;

	}

	public int findMaxThreadID(Thread thread) {

		int thread_id = 0;

		// データベースへの接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQL文の作成
			String sql = "SELECT "
			        + "MAX(THREAD_ID) AS MAXID "
			        + "FROM "
			        + "THREAD "
			        + "WHERE "
			        + "CREATE_USER = ? ";

			System.out.println(sql);
			PreparedStatement pStmt = con.prepareStatement(sql);

			pStmt.setInt(1, thread.getUser_id());

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				thread_id = rs.getInt("MAXID");

			}

			rs.close();
			pStmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

		return thread_id;

	}

	public boolean Insert(Thread thread) {

		boolean flag = true;

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO "
			        + "THREAD "
			        + "("
			        + "TITLE,"
			        + "CREATE_USER,"
			        + "CREATE_DATE"
			        + ") "
			        + "VALUE"
			        + "("
			        + "'" + thread.getTitle() + "',"
			        + " " + thread.getUser_id() + ","
			        + "'" + LocalDateTime.now() + "'"
			        + ")";

			System.out.println(sql);
			PreparedStatement pStmt = con.prepareStatement(sql);

			int result = pStmt.executeUpdate();

			if (result >= 1) {

				flag = true;

			} else {

				flag = false;

			}

			pStmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}
}
