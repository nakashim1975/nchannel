package model;

import dao.UserDAO;

public class UserLogic {

	public User execute(User user) {

		UserDAO dao = new UserDAO();

		if (dao.Insert(user)) {

			User user1 = dao.findByUser(user.getName(), user.getPass());

			if (user1 != null) {
				return user1;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

}
