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

import model.Message;
import model.ThreadMessage;

public class MessageDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/nchannel?characterEncoding=utf8&useSSL=false&serverTimezone=JST&rewriteBatchedStatements=true";
	private final String DB_USER = "root";
	private final String DB_PASS = "admin";

	/**
	 * 指定したスレッドＩＤに該当するメッセージを返す
	 * 
	 * @param msg メッセージエンティティ
	 * @return threadMsg スレッドメッセージリスト
	 */
	public List<ThreadMessage> findByMessage(Message msg) {

		// スレッドメッセージを格納する為のリストを用意
		List<ThreadMessage> threadMsg = new ArrayList<ThreadMessage>();

		// データベースへの接続処理
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// ＳＱＬ文の作成
			String sql = "SELECT "
			        + "A.THREAD_ID, "
			        + "A.MESSAGE_ID, "
			        + "A.MESSAGE, "
			        + "B.NAME,"
			        + "A.CREATE_DATE "
			        + "FROM "
			        + "MESSAGE AS A "
			        + "INNER JOIN USER AS B "
			        + "ON A.CREATE_USER = B.USER_ID "
			        + "WHERE "
			        + "A.THREAD_ID = ? "
			        + "AND "
			        + "A.MESSAGE_ID > ? "
			        + "ORDER BY "
			        + "A.CREATE_DATE";

			// ＳＱＬ文のプリコンパイル
			PreparedStatement pStmt = con.prepareStatement(sql);

			// プレースホルダに条件を設定
			pStmt.setInt(1, msg.getThread_id());

			int msgid = msg.getmessage_id();
			if (msgid > 0) {
				pStmt.setInt(2, msgid);
			} else {
				pStmt.setInt(2, 0);
			}

			System.out.println(sql);

			// ＳＱＬ文を実行して結果セットを取得
			ResultSet rs = pStmt.executeQuery();

			// レコードが存在する間は、リストにレコードを追加
			while (rs.next()) {

				// レコード取得した値を変数にセット
				int thread_id = rs.getInt("THREAD_ID");
				int message_id = rs.getInt("MESSAGE_ID");
				String s = rs.getString("MESSAGE");
				String message = s.replaceAll("\\r\\n|\\n\\r|\\n|\r", "<br>");
				String name = rs.getString("NAME");
				Date create_date = rs.getTimestamp("CREATE_DATE");

				// スレッドメッセージオブジェクトを作成
				ThreadMessage thmsg = new ThreadMessage(thread_id, message_id,
				        message, name, create_date);

				// スレッドメッセージオブジェクトをリストに追加
				threadMsg.add(thmsg);
			}

			rs.close();
			pStmt.close();
			con.close();

		} catch (SQLException e) {
			// 例外処理
			e.printStackTrace();
			return null;
		}

		// 呼び出し元にスレッドメッセージリストを戻す
		return threadMsg;
	}

	/**
	 * 指定されたメッセージデータを登録する
	 * 
	 * @param message 登録するメッセージ
	 * @return flag 成功・失敗フラグ
	 */
	public boolean Insert(Message msg) {

		boolean flag = false;

		// データベースの接続処理
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// ＳＱＬ文の作成
			String sql = "INSERT "
			        + "INTO MESSAGE "
			        + "("
			        + "THREAD_ID,"
			        + "MESSAGE,"
			        + "CREATE_USER,"
			        + "CREATE_DATE "
			        + ") VALUE ("
			        + " " + msg.getThread_id() + ","
			        + "'" + msg.getMessage() + "',"
			        + " " + msg.getUser_id() + ","
			        + "'" + LocalDateTime.now() + "' "
			        + ")";

			// ＳＱＬ文をプリコンパイル
			PreparedStatement pStmt = con.prepareStatement(sql);

			// ＩＮＳＥＲＴ文を実行
			int result = pStmt.executeUpdate();

			// ＩＮＳＥＲＴ文の処理結果を判定
			if (result >= 1) {
				flag = true;
			} else {
				flag = false;
			}

			pStmt.close();
			con.close();

		} catch (SQLException e) {
			// 例外処理
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

}
