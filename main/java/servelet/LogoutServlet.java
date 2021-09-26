package servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*--------------------*/
/*-- ログアウト処理 --*/
/*--------------------*/
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*------------------------------------*/
	/*-- スレッド一覧、メッセージ一覧で --*
	/*-- ログアウトボタンクリック時     --*/
	/*------------------------------------*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*------------------------*/
		/*-- パラメータ削除処理 --*/
		/*------------------------*/
		HttpSession ss = request.getSession();
		ss.removeAttribute("user");

		/*------------------*/
		/*-- 画面遷移処理 --*/
		/*------------------*/
		response.sendRedirect(
				"/nchannel/LoginServlet"); // ログイン画面
	}

}
