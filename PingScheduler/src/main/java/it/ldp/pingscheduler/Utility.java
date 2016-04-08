package it.ldp.pingscheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class Utility {
	final static Logger log = Logger.getLogger(Utility.class.getName());
	// JDBC driver name and database URL
	static String JDBC_DRIVER_p = "org.postgresql.Driver";
	static String DB_URL_p = "jdbc:postgresql://localhost:5432/astelu";
	// Database credentials
	static String USER_p = "astelu";
	static String PASS_p = "astelu";

	static final String JDBC_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	static final String DB_URL = "jdbc:jtds:sqlserver://server-hp/SQLEXPRESS;databaseName=Mexal_Telefonate";

	// Database credentials
	static final String USER = "jdbc";
	static final String PASS = "jdbc";
	static Connection conn = null;

	static Statement stmt = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// conn.setAutoCommit(false);
			System.out.println("Opened database successfully");
			/*
			 * String prf = getTipoChiamataColt("0438584844","0044775544456");
			 * log.info("prf:" + prf); prf =
			 * getTipoChiamataColt("0438584844","391775544456"); log.info("prf:"
			 * + prf);
			 * 
			 * prf = getTipoChiamataColt("04364246","0436866301");
			 * log.info("prf:" + prf);
			 * 
			 * prf = getTipoChiamataTiscali("0438584844","445544456");
			 * log.info("prf:" + prf);
			 * 
			 * prf = getTipoChiamataTiscali("0438584844","390438584844");
			 * log.info("prf:" + prf);
			 * 
			 * prf = getTipoChiamataTiscali("0438584844","393918584844");
			 * log.info("prf:" + prf); //mancano tanti prefissi cellulari
			 * conn.close();
			 */
			// sqlExpress_testConn();

			// } catch (SQLException se) {
			// Handle errors for JDBC
			// se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) {
				log.error("Exception se:" + se.getMessage());
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se1) {
				log.error("Exception se1:" + se1.getMessage());
			} // end finally try
		} // end try
		log.info("Good bye");
	}// end main

	/**
	 * 
	 * @param conn
	 * @param table
	 * @return
	 */
	public static HashMap<String, Long> getClientiServizi(Connection conn) {
		HashMap<String, Long> hm = new HashMap<String, Long>();
		Statement st = null;
		ResultSet rs = null;
		try {

			st = conn.createStatement();

			String sql = "SELECT id,ragsoc from ClientiServizi";

			rs = st.executeQuery(sql);

			while (rs.next())
				hm.put(rs.getString(2), rs.getLong(1));

		} catch (Exception e) {
			log.error("Exception e:" + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e2) {
				log.error("Exception e2:" + e2.getMessage());
			}
		}
		return hm;
	}

	/**
	 * 
	 * @param conn
	 * @param table
	 * @return
	 */
	public static HashMap<String, String> getClientiXDSL(Connection conn) {
		HashMap<String, String> hm = new HashMap<String, String>();
		Statement st = null;
		ResultSet rs = null;
		try {

			st = conn.createStatement();

			String sql = "SELECT a.ragsoc, b.ip from ClientiServizi a, clientiXDSL b "
					+ "where a.id=b.idcliente and b.ip <> '' and b.ping = 'X'";

			rs = st.executeQuery(sql);

			while (rs.next())
				hm.put(rs.getString(1), rs.getString(2));

		} catch (Exception e) {
			log.error("Exception e:" + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e2) {
				log.error("Exception e2:" + e2.getMessage());
			}
		}
		return hm;
	}

	public static Connection setConn() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// if (StringUtils.contains(JDBC_DRIVER, "postgresql"))
			// conn.setAutoCommit(false);

		} catch (SQLException se) {
			log.error("SQLException:" + se.getMessage());
		} catch (Exception e) {
			log.error("Exception:" + e.getMessage());
		}
		return conn;
	}

	public static void closeConn() {
		try {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			log.error("Exception:" + e.getMessage());
		}
	}

}
