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

import model.Message;
import model.MessageLogic;
import model.Thread;
import model.ThreadList;
import model.ThreadListLogic;
import model.ThreadLogic;
import model.ThreadMessage;
import model.User;

/*------------------------------*/
/*-- スレッド一覧画面での処理 --*/
/*------------------------------*/
@WebServlet("/ThreadListServlet")
public class ThreadListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*--------------------------------------------*/
	/*-- スレッド一覧でアンカーリンククリック時 --*/
	/*--------------------------------------------*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*------------------------*/
		/*-- パラメータ取得処理 --*/
		/*------------------------*/
		request.setCharacterEncoding("UTF-8");
		int thread_id = Integer.parseInt(
				request.getParameter("threadid")); // スレッドＩＤ

		ThreadListLogic to = new ThreadListLogic();
		String title = to.getTitle(thread_id); // タイトル

		/*------------------------*/
		/*-- メッセージ取得処理 --*/
		/*------------------------*/
		Message msg = new Message(thread_id);
		MessageLogic mo = new MessageLogic();
		List<ThreadMessage> thMsgList = mo.get(msg);

		/*------------------------*/
		/*-- パラメータ保存処理 --*/
		/*------------------------*/
		if (thMsgList != null) {
			int maxMsgId = mo.getMaxMessageId(thMsgList); // 最大メッセージ番号を取得（新着メッセージのみボタンで使用）

			request.setAttribute("thMsgList", thMsgList); // メッセージリスト

			HttpSession session = request.getSession();
			session.setAttribute("threadid", thread_id); // スレッドＩＤ
			session.setAttribute("title", title); // タイトル
			session.setAttribute("maxMsgId", maxMsgId); // 最大メッセージＩＤ

		}
		/*------------------*/
		/*-- 画面遷移処理 --*/
		/*------------------*/
		RequestDispatcher rdp = request.getRequestDispatcher(
				"/WEB-INF/jsp/thread.jsp"); // スレッドメッセージ画面
		rdp.forward(request, response);

	}

	/*----------------------------------------------*/
	/*-- スレッド一覧で新規作成ボタンをクリック時 --*/
	/*----------------------------------------------*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*------------------------*/
		/*-- パラメータ取得処理 --*/
		/*------------------------*/
		request.setCharacterEncoding("UTF-8");
		String thTitle = request.getParameter("threadtitle"); // スレッドタイトル

		HttpSession ss = request.getSession();
		User user = (User) ss.getAttribute("user");
		int userid = user.getUser_id(); // ユーザーＩＤ

		String msg = request.getParameter("message");

		/*------------------------------------------*/
		/*-- スレッドとメッセージの登録と取得処理 --*/
		/*------------------------------------------*/
		Thread thread = new Thread(thTitle, userid);
		ThreadLogic thLogic = new ThreadLogic();
		List<ThreadList> thList = thLogic.execute(thread, msg);

		/*------------------------*/
		/*-- パラメータ保存処理 --*/
		/*------------------------*/
		if (thList != null) {
			request.setAttribute("thList", thList); // スレッド一覧
		}

		/*------------------*/
		/*-- 画面遷移処理 --*/
		/*------------------*/
		RequestDispatcher rdp = request.getRequestDispatcher(
				"/WEB-INF/jsp/threadList.jsp"); // スレッド一覧画面
		rdp.forward(request, response);

	}

}
