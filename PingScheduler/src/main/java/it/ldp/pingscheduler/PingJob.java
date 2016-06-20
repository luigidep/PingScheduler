package it.ldp.pingscheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

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

	static String JDBC_DRIVER = "";
	static String DB_URL = "";

	// Database credentials
	static String USER = "";
	static String PASS = "";

	// static Connection conn = null;
	// static Statement stmt = null;
	static String nping = "5";
	static String loglevel = "tutto";
	static String periodo = "sempre";
	static String intervallo = "15";

	TreeMap<String, String> pingKO = new TreeMap<String, String>();

	public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
		log.info("start ping job parameter [intervallo,nping,loglevel,periodo]:[" + intervallo + "," + nping + ","
				+ loglevel + "," + periodo + "]");
		long ini = System.currentTimeMillis();
		try {
            Utility.initConfig();
			LocalDateTime localDateTime = LocalDateTime.now();

			if (StringUtils.equals(periodo, Constant.PERIODO_ORARIO)) {
				// ore 8/20;
				StringTokenizer st = new StringTokenizer(Constant.PERIODO_ORARIO);
				st.nextToken();
				st = new StringTokenizer(st.nextToken(), "/");
				int oraIniPing = new Integer(st.nextToken()).intValue();
				int oraEndPing = new Integer(st.nextToken()).intValue();
				int ora = localDateTime.getHour();
				// ora-=10;
				log.info("ora:" + ora);
				if (ora < oraIniPing || ora > oraEndPing) {
					log.info("ore:" + ora + " ---> NO PING");
					return;
				}
			}
			pingKO.clear();
			Utility.setConn();
			HashMap<String, String> hm = new HashMap<String, String>();
			hm = Utility.getClientiXDSL(Utility.conn);
			
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

			Utility.closeConn();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("Exception e:" + e);
		}

		log.info("time for all ping:" + (System.currentTimeMillis() - ini) / 60000 + " min");

		// invio email in caso di ping KO
		if (!pingKO.isEmpty()) {
			
			String cl = " cliente";
			if (pingKO.size() > 1)
				cl = " clienti";

			String oggetto = "ping KO ADSL: " + pingKO.size() + cl;
			Set<Entry<String, String>> set = pingKO.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
			String textEmail = "lista IP e clienti:\n";
			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mentry = (Map.Entry) iterator.next();
				textEmail += mentry.getValue()+" - "+ mentry.getKey()+ "\n";
			}
			
			new SendMailTLS().sendEmailFromSupport(oggetto, textEmail);
		}

		log.info("email sended OK");
	}

	public void doCommand(List<String> command, String cliente) {

		try {
			String s = null;

			ProcessBuilder pb = new ProcessBuilder(command);
			Process process = pb.start();
			LocalDateTime localDateTime = LocalDateTime.now(); 
			
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
					String packetLoss = sp.nextToken();
					if (StringUtils.contains(packetLoss, "errors"))
						packetLoss = sp.nextToken();
					ploss = new Integer(StringUtils.substringBefore(packetLoss, "%").trim()).intValue();
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

			if (StringUtils.equals(loglevel, Constant.LOGLEVEL_TUTTO))
				logPingData(cliente, ip, nping, ptx, prx, ploss, min, max, avg, mdev, time);

			if (StringUtils.equals(loglevel, Constant.LOGLEVEL_PINGKO) && ploss != 0) {
				logPingData(cliente, ip, nping, ptx, prx, ploss, min, max, avg, mdev, time);
			}

			if (ploss != 0) {
				StringTokenizer st= new StringTokenizer(localDateTime.toString(),"T");
				String timeNow=st.nextToken()+" "+st.nextToken();
				pingKO.put(cliente,timeNow+" - "+ip);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception:" + e.getMessage());
		}

	}

	private static void logPingData(String cl, String ip, String nping, int ptx, int prx, int ploss, double min,
			double max, double avg, double mdev, int time) throws SQLException {

		Utility.stmt = Utility.conn.createStatement();
		Timestamp now = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		String sql = "INSERT INTO ping(cliente, ip, nping, ptx, prx, ploss, min, max, avg, mdev,ttime, data)"
				+ " VALUES ('" + cl + "','" + ip + "'," + nping + "," + ptx + "," + prx + "," + ploss + "," + min + ","
				+ max + "," + avg + "," + mdev + "," + time + ",'" + now + "')";
		log.warn("sql:" + sql);
		Utility.stmt.executeUpdate(sql);
		log.warn("inserted on db");

	}

}