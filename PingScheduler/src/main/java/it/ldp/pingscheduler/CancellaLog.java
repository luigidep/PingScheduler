package it.ldp.pingscheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class CancellaLog {
	final static Logger log = Logger.getLogger(utility.class.getName());
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param conn
	 * @param table
	 * @return
	 */
	public String concellaLogPing(String periodo, String tipolog) {

		Statement st = null;
		Connection conn = null;
		String strRowsDeleted = "nessun record cancellato";
		try {

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			st = conn.createStatement();
			String sql = "delete from ping where 1=1 ";

			LocalDateTime localDateTime = LocalDateTime.now();
			switch (periodo) {
			case "1":
				localDateTime = localDateTime.minusDays(1);
				break;
			case "7":
				localDateTime = localDateTime.minusDays(7);
				break;
			case "1m":
				localDateTime = localDateTime.minusMonths(1);
				break;

			default:
				break;
			}

			String yyyymmdd = StringUtils.substringBefore(localDateTime.toString(), "T");
			sql += "\nand data < '" + yyyymmdd + "'";

			if (StringUtils.equals(constant.CANCELLA_PINGOK, tipolog))
				sql += "\nand ploss = 0";

			log.info("sql:" + sql);

			int rowsDeleted = st.executeUpdate(sql);
			if (rowsDeleted > 0)
				strRowsDeleted = "cancellati " + rowsDeleted + " records";

		} catch (Exception e) {
			log.error("Exception e:" + e.getMessage());

		} finally {
			try {
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				log.error("Exception e2:" + e2.getMessage());
			}
		}

		return strRowsDeleted;
	}

}
