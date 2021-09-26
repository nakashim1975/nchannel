package servelet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;
import model.ThreadList;
import model.ThreadListLogic;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*------------------------*/
	/*-- ｎちゃんねる起動時 --*/
	/*------------------------*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		/*------------------*/
		/*-- 画面遷移処理 --*/
		/*------------------*/
		// 最初はログイン画面に遷移する
		RequestDispatcher rdp = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rdp.forward(request, response);

	}

	/*--------------------------------------------*/
	/*-- ログイン画面でログインボタンクリック時 --*/
	/*--------------------------------------------*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		/*------------------------*/
		/*-- パラメータ取得処理 --*/
		/*------------------------*/
		request.setCharacterEncoding("UTF-8");
		int userid = Integer.parseInt(request.getParameter("user"));
		String pass = request.getParameter("pass");

		/*------------------*/
		/*-- ログイン処理 --*/
		/*------------------*/
		Login login = new Login(userid, pass);
		LoginLogic lo = new LoginLogic();
		User user = lo.execute(login);

		if (user != null) {

			/*--------------------------*/
			/*-- スレッド一覧取得処理 --*/
			/*--------------------------*/
			ThreadListLogic to = new ThreadListLogic();
			List<ThreadList> thList = to.get();

			/*------------------------*/
			/*-- パラメータ保存処理 --*/
			/*------------------------*/
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // ユーザー
			request.setAttribute("thList", thList); // スレッドリスト

			/*------------------*/
			/*-- 画面遷移処理 --*/
			/*------------------*/
			RequestDispatcher rdp = request.getRequestDispatcher("/WEB-INF/jsp/threadList.jsp"); // スレッド一覧画面
			rdp.forward(request, response);

		} else {

			// エラーメッセージを保存
			request.setAttribute("infoMsg", "ログインできません。<br>ユーザーＩＤとパスワードを確認してください。");
			request.setAttribute("kind", "1");

			RequestDispatcher rdp = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp"); // ログイン画面
			rdp.forward(request, response);

		}

	}

}
