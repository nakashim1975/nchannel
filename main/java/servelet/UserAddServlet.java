package servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserLogic;

/*------------------------------*/
/*-- ユーザー登録画面での処理 --*/
/*------------------------------*/
@WebServlet("/UserAddServlet")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*--------------------------------------------------------*/
	/*-- ログイン画面でユーザー登録はこちらからをクリック時 --*/
	/*--------------------------------------------------------*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*------------------*/
		/*-- 画面遷移処理 --*/
		/*------------------*/
		RequestDispatcher rdp = request.getRequestDispatcher("/WEB-INF/jsp/userAdd.jsp"); // ユーザー登録画面
		rdp.forward(request, response);

	}

	/*--------------------------------------------*/
	/*-- ユーザー登録画面で登録ボタンクリック時 --*/
	/*--------------------------------------------*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*------------------------*/
		/*-- パラメータ取得処理 --*/
		/*------------------------*/
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		/*----------------------*/
		/*-- ユーザー登録処理 --*/
		/*----------------------*/
		User adduser = new User(0, name, pass);
		UserLogic uo = new UserLogic();
		User user = uo.execute(adduser);

		/*------------------------*/
		/*-- パラメータ保存処理 --*/
		/*------------------------*/
		if (user != null) {
			request.setAttribute("user", user);
			request.setAttribute("infoMsg", user.getName() + "さんは、<br>ユーザーＩＤ：" +
					user.getUser_id() + "で登録しました。");
			request.setAttribute("kind", "2");
		}

		/*------------------*/
		/*-- 画面遷移処理 --*/
		/*------------------*/
		RequestDispatcher rdp = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp"); // ログイン画面
		rdp.forward(request, response);

	}

}
