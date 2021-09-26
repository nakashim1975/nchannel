package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;
import model.User;

/**
 * Userテーブルを担当するDAO
 * 
 * @author bqc00
 *
 */
public class UserDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/nchannel?characterEncoding=utf8&useSSL=false&serverTimezone=JST&rewriteBatchedStatements=true";
	private final String DB_USER = "root";
	private final String DB_PASS = "admin";

	/**
	 * ユーザー情報をUserテーブルから取得
	 * 
	 * @param login ログイン情報
	 * @return user ユーザー情報
	 * @throws ClassNotFoundException
	 */

	public User findByLogin(Login login) {

		// ユーザー情報変数の定義
		User user = null;

		// データベースへの接続
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// SELECT文を作成
			String sql = "SELECT "
			        + "USER_ID, "
			        + "NAME, "
			        + "PASS "
			        + "FROM "
			        + "USER "
			        + "WHERE "
			        + "USER_ID = ? "
			        + "AND "
			        + "PASS = ?";

			// ＳＱＬ文をプリコンパイルする
			PreparedStatement pStmt = con.prepareStatement(sql);

			// ＳＱＬ文のパラメータをセットする
			pStmt.setInt(1, login.getUser_id());
			pStmt.setString(2, login.getPass());

			// ＳＱＬクエリーを実行してレコードセットを取得
			ResultSet rs = pStmt.executeQuery();

			// レコードが存在したら、Userオブジェクトを作成してリターンする
			if (rs.next()) {

				// レコードセットから値を取得
				int user_id = rs.getInt("USER_ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");

				// Userオブジェクトの作成
				user = new User(user_id, name, pass);

			}

			rs.close();
			pStmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// 見つかったユーザー情報、もしくはnullを返す
		return user;

	}

	/**
	 * 指定されたユーザーを登録する
	 * 
	 * @param User 登録するメッセージ
	 * @return flag 成功・失敗フラグ
	 */
	public boolean Insert(User user) {

		boolean flag = false;

		// データベースの接続処理
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// ＳＱＬ文の作成
			String sql = "INSERT "
			        + "INTO USER "
			        + "("
			        + "NAME,"
			        + "PASS "
			        + ") VALUE ("
			        + "'" + user.getName() + "',"
			        + "'" + user.getPass() + "' "
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

		} catch (Exception e) {
			// 例外処理
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	/**
	 * ユーザーＩＤをnameとpassから取得
	 * 
	 * @param name 登録ユーザー名
	 * @param pass 登録パスワード
	 * @return user ユーザー情報
	 */

	public User findByUser(String name, String pass) {

		// ユーザー情報変数の定義
		User user = null;

		// データベースへの接続
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文を作成
			String sql = "SELECT "
			        + "USER_ID, "
			        + "NAME, "
			        + "PASS "
			        + "FROM "
			        + "USER "
			        + "WHERE "
			        + "NAME = ? "
			        + "AND "
			        + "PASS = ?";

			// ＳＱＬ文をプリコンパイルする
			PreparedStatement pStmt = con.prepareStatement(sql);

			// ＳＱＬ文のパラメータをセットする
			pStmt.setString(1, name);
			pStmt.setString(2, pass);

			// ＳＱＬクエリーを実行してレコードセットを取得
			ResultSet rs = pStmt.executeQuery();

			// レコードが存在したら、Userオブジェクトを作成してリターンする
			if (rs.next()) {

				// Userオブジェクトの作成
				user = new User(rs.getInt("USER_ID"), rs.getString("NAME"), rs.getString("PASS"));

			}

			rs.close();
			pStmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		// 見つかったユーザー情報、もしくはnullを返す
		return user;
	}
}
