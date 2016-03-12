package it.ldp.pingscheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * @author luigi
 *
 */
public class PingJob implements Job {

	private static Logger log = Logger.getLogger(PingJob.class.getName());

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

	//static Connection conn = null;
	//static Statement stmt = null;
	static String nping = "5";
	static String loglevel = "tutto";
	static String periodo = "sempre";
	static String intervallo = "15";

	public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
		log.info("start ping job parameter [intervallo,nping,loglevel,periodo]:[" + intervallo + "," + nping + ","
				+ loglevel + "," + periodo + "]");
		long ini = System.currentTimeMillis();
		try {
			
			LocalDateTime localDateTime = LocalDateTime.now();

			if (StringUtils.equals(periodo,constant.PERIODO_7_21)) {
				int ora=localDateTime.getHour();
				ora+=10;
				log.info("ora:"+ora);
				if (ora < 7 || ora > 21) {
					log.info("ore:"+ora+" ---> NO PING");
					return;
				}
			}
            utility.setConn();
			HashMap<String, String> hm = new HashMap<String, String>();
			hm = utility.getClientiXDSL(utility.conn);
			for (Map.Entry<String, String> cip : hm.entrySet()) {
				String ip = cip.getValue();
				String cliente = cip.getKey();

				List<String> commands = new ArrayList<String>();
				commands.add("ping");
				commands.add("-c");
				commands.add(nping);
				commands.add(ip);
				doCommand(commands, cliente);

			}

			utility.closeConn();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("Exception e:" + e);
		}

		log.info("time for all ping:" + (System.currentTimeMillis() - ini) / 60000 + " min");

	}

	public void doCommand(List<String> command, String cliente) {

		try {
			String s = null;

			ProcessBuilder pb = new ProcessBuilder(command);
			Process process = pb.start();

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			// read the output from the command
			log.info("Here is the standard output of the command:");
			String ip = "";
			int ptx = -1, prx = -1, ploss = -1, time = -1;
			double min = -1, avg = -1, max = -1, mdev = -1;
			while ((s = stdInput.readLine()) != null) {
				// System.out.println(s);
				if (s.startsWith("PING")) {
					log.warn("parsing s:" + s);
					s = StringUtils.substringAfter(s, "(");
					s = StringUtils.substringBefore(s, ")");
					log.warn("after parsing s:" + s);
					ip = s;
					continue;
				}
				if (s.contains("packets")) {
					log.warn("parsing s:" + s);
					StringTokenizer sp = new StringTokenizer(s, ",");
					// String sub=s., ",");
					ptx = new Integer(StringUtils.substringBefore(sp.nextToken(), "packets").trim()).intValue();
					prx = new Integer(StringUtils.substringBefore(sp.nextToken(), "received").trim()).intValue();
					ploss = new Integer(StringUtils.substringBefore(sp.nextToken(), "%").trim()).intValue();
					String t = StringUtils.substringAfter(sp.nextToken(), "time").trim();
					time = new Integer(StringUtils.substringBefore(t, "ms").trim()).intValue();
					log.warn("ptx  :" + ptx);
					log.warn("prx  :" + prx);
					log.warn("ploss:" + ploss);
					log.warn("time :" + time);
					continue;
				}
				if (s.startsWith("rtt")) {
					log.warn("parsing s:" + s);
					s = StringUtils.substringBefore(StringUtils.substringAfter(s, "="), "ms").trim();
					StringTokenizer sp = new StringTokenizer(s, "/");
					// String sub=s., ",");
					min = new Double(sp.nextToken());
					avg = new Double(sp.nextToken());
					max = new Double(sp.nextToken());
					mdev = new Double(sp.nextToken());
					log.warn("min  :" + min);
					log.warn("avg  :" + avg);
					log.warn("max:" + max);
					log.warn("mdev :" + mdev);
					continue;
				}

				// log.info(s);
			}

			// read any errors from the attempted command
			log.info("Here is the standard error of the command (if any):");
			while ((s = stdError.readLine()) != null) {
				// System.out.println(s);
				log.warn("---------------------------------------------------->Error:" + s);
			}

			if (StringUtils.equals(loglevel,constant.LOGLEVEL_TUTTO))
				logPingData(cliente,ip,nping,ptx,prx,ploss,min,max,avg,mdev,time);
			
			if (StringUtils.equals(loglevel, constant.LOGLEVEL_PINGKO) && ploss != 0) 
				logPingData(cliente,ip,nping,ptx,prx,ploss,min,max,avg,mdev,time);
			

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception:" + e.getMessage());
		}

	}

	
	private static void logPingData(String cl,String ip, String nping, int ptx, int prx, int ploss, double min, double max, double avg, double mdev, int time) throws SQLException {
		
		utility.stmt = utility.conn.createStatement();
		Timestamp now = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		String sql = "INSERT INTO ping(cliente, ip, nping, ptx, prx, ploss, min, max, avg, mdev,ttime, data)"
				+ " VALUES ('" + cl + "','" + ip + "'," + nping + "," + ptx + "," + prx + "," + ploss + ","
				+ min + "," + max + "," + avg + "," + mdev + "," + time + ",'" + now + "')";
		log.warn("sql:" + sql);
		utility.stmt.executeUpdate(sql);
		log.warn("inserted on db");
		
	}
	

}