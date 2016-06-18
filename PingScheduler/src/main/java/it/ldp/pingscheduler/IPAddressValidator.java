package it.ldp.pingscheduler;


import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddressValidator {
	private Pattern pattern;
	private Matcher matcher;
	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IPAddressValidator ipAddressValidator = new IPAddressValidator();
		String ip="03.003.013.009";
		boolean valid = ipAddressValidator.validate(ip);
		
		if(valid)
			System.out.println("clean IP:"+ipAddressValidator.cleanIP(ip));
		else
			System.out.println("IP no valid:"+ip);
	}
	
	
	public IPAddressValidator() {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		
	}

	/**
	 * Validate ip address with regular expression
	 * 
	 * @param ip
	 *            ip address for validation
	 * @return true valid ip address, false invalid ip address
	 */
	public boolean validate(final String ip) {
		matcher = pattern.matcher(ip);
		return matcher.matches();
	}

	public String cleanIP(String ip) {
		StringTokenizer st = new StringTokenizer(ip, ".");
		String ipcleaned = "";
		while(st.hasMoreTokens())
			ipcleaned += new Integer(st.nextToken()) + ".";

		ipcleaned = ipcleaned.substring(0, ipcleaned.length()-1);

		return ipcleaned;
	}
}

