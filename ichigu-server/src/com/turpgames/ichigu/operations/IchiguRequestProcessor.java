package com.turpgames.ichigu.operations;

import java.sql.SQLException;
import java.sql.Types;

import com.turpgames.ichigu.db.DbManager;
import com.turpgames.ichigu.db.SqlParameter;
import com.turpgames.ichigu.db.SqlQuery;

public class IchiguRequestProcessor {
	public static IchiguResponse process(IchiguRequest request) {
		DbManager man = null;
		try {
			man = new DbManager();
			Object id = man.delete(new SqlQuery("delete from users where id=?",
					new SqlParameter("4", Types.INTEGER)));
			System.out.println(id);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (man != null)
				man.close();
		}
		return new IchiguResponse();
	}
}