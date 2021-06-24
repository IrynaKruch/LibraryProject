package ua.krucheniuk.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBConnection {

	private static final Logger log = Logger.getLogger(DBConnection.class);

	private static DBConnection instance;

	public static synchronized DBConnection getInstance() {
		DBConnection localInstance = instance;
		if (localInstance == null) {
			synchronized (DBConnection.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new DBConnection();
				}
			}
		}
		return localInstance;
	}

	private DBConnection() {
	}

	public static Connection createConnection() throws SQLException {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/Library");
			con = ds.getConnection();
		} catch (NamingException ex) {
			log.error("Cannot obtain a connection from the pool", ex);
		}
		return con;
	}

}
