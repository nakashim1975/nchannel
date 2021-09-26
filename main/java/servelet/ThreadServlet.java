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
import model.ThreadMessage;
import model.User;

/**
 * Servlet implementation class ThreadServlet
 */
@WebServlet("/ThreadServlet")
public class ThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*------------------------------------------------*/
	/*-- メッセージ一覧で書き込むボタンをクリック時 --*/
	/*------------------------------------------------*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		/*------------------------*/
		/*-- パラメータ取得処理 --*/
		/*------------------------*/
		request.setCharacterEncoding("UTF-8");
		String msg = request.getParameter("message"); // メッセージ

		HttpSession ss = request.getSession();
		User user = (User) ss.getAttribute("user");
		int userid = user.getUser_id(); // ユーザーＩＤ
		int thread_id = (int) ss.getAttribute("threadid"); // スレッドＩＤ

		/*----------------------------------*/
		/*-- メッセージ登録・一覧取得処理 --*/
		/*----------------------------------*/
		Message msgjb = new Message(thread_id, msg, userid);
		MessageLogic mo = new MessageLogic();
		List<ThreadMessage> thMsgList = mo.execute(msgjb);

		/*------------------------*/
		/*-- パラメータ保存処理 --*/
		/*------------------------*/
		if (thMsgList != null) {
			request.setAttribute(
			        "thMsgList", thMsgList); // メッセージリスト
			int maxMsgId = mo.getMaxMessageId(thMsgList); // 最大メッセージ番号を取得（新着メッセージのみボタンで使用）
			ss.setAttribute("maxMsgId", maxMsgId); // 最大メッセージＩＤ
		}

		/*------------------*/
		/*-- 画面遷移処理 --*/
		/*------------------*/
		RequestDispatcher rdp = request.getRequestDispatcher(
		        "/WEB-INF/jsp/thread.jsp"); // メッセージ一覧画面
		rdp.forward(request, response);

	}

}
