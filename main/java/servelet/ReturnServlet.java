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
import model.ThreadList;
import model.ThreadListLogic;
import model.ThreadMessage;

/*------------------------------------------*/
/*-- メッセージ画面のヘッダ３ボタンの処理 --*/
/*------------------------------------------*/
@WebServlet("/ReturnServlet")
public class ReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*--------------------------------------------------*/
	/*-- メッセージ一覧の３ボタンのどれかがクリック時 --*/
	/*--------------------------------------------------*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*--------------------------*/
		/*-- 分岐パラメータの取得 --*/
		/*--------------------------*/
		int kind = Integer.parseInt(request.getParameter("kind"));

		int thread_id = 0;
		int maxMsgId = 0;

		ThreadListLogic to = null;
		RequestDispatcher rdp = null;
		Message msg = null;
		MessageLogic mo = null;
		List<ThreadMessage> thMsgList = null;

		HttpSession ss = request.getSession();

		switch (kind) {

		case 1: // 新着メッセージの表示

			/*------------------------*/
			/*-- パラメータ取得処理 --*/
			/*------------------------*/
			request.setCharacterEncoding("UTF-8");

			thread_id = (int) ss.getAttribute("threadid"); // スレッドＩＤ
			int message_id = (int) ss.getAttribute("maxMsgId"); // 最大メッセージＩＤ

			/*------------------------*/
			/*-- メッセージ取得処理 --*/
			/*------------------------*/
			msg = new Message(thread_id, message_id);
			mo = new MessageLogic();
			thMsgList = mo.get(msg);

			/*------------------------*/
			/*-- パラメータ保存処理 --*/
			/*------------------------*/
			if (thMsgList != null) {
				maxMsgId = mo.getMaxMessageId(thMsgList); // 最大メッセージ番号を取得（新着メッセージのみボタンで使用）
				request.setAttribute("thMsgList", thMsgList); // メッセージリスト
				ss.setAttribute("maxMsgId", maxMsgId); // 最大メッセージＩＤ
			}

			/*------------------*/
			/*-- 画面遷移処理 --*/
			/*------------------*/
			rdp = request.getRequestDispatcher(
					"/WEB-INF/jsp/thread.jsp"); // スレッドメッセージ画面
			rdp.forward(request, response);

			break;

		case 2: // 全てのメッセージの表示

			/*------------------------*/
			/*-- パラメータ取得処理 --*/
			/*------------------------*/
			request.setCharacterEncoding("UTF-8");

			thread_id = (int) ss.getAttribute("threadid"); // スレッドＩＤ

			/*------------------------*/
			/*-- メッセージ取得処理 --*/
			/*------------------------*/
			msg = new Message(thread_id);
			mo = new MessageLogic();
			thMsgList = mo.get(msg);
			maxMsgId = mo.getMaxMessageId(thMsgList); // 最大メッセージ番号を取得（新着メッセージのみボタンで使用）

			/*------------------------*/
			/*-- パラメータ保存処理 --*/
			/*------------------------*/
			if (thMsgList != null) {
				request.setAttribute("thMsgList", thMsgList); // メッセージリスト
				ss.setAttribute("maxMsgId", maxMsgId); // 最大メッセージＩＤ
			}

			/*------------------*/
			/*-- 画面遷移処理 --*/
			/*------------------*/
			rdp = request.getRequestDispatcher(
					"/WEB-INF/jsp/thread.jsp"); // スレッドメッセージ画面
			rdp.forward(request, response);

			break;

		case 3: // 掲示板に戻る

			/*--------------------------*/
			/*-- スレッド一覧取得処理 --*/
			/*--------------------------*/
			to = new ThreadListLogic();
			List<ThreadList> thList = to.get();

			/*------------------------*/
			/*-- パラメータ保存処理 --*/
			/*------------------------*/
			request.setAttribute("thList", thList); // スレッド一覧

			/*------------------------*/
			/*-- パラメータ削除処理 --*/
			/*------------------------*/
			ss.removeAttribute("threadid");
			ss.removeAttribute("title");
			ss.removeAttribute("maxMsgId");

			/*------------------*/
			/*-- 画面遷移処理 --*/
			/*------------------*/
			rdp = request.getRequestDispatcher(
					"/WEB-INF/jsp/threadList.jsp"); // スレッド一覧画面
			rdp.forward(request, response);

			break;

		}

	}

}
