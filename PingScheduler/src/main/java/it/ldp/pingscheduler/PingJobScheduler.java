package it.ldp.pingscheduler;

import java.lang.management.ManagementFactory;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 * @author luigi
 *
 */
public class PingJobScheduler {
	final static Logger log = Logger.getLogger(PingJobScheduler.class.getName());

	public static void main(String[] args) {
		
		dowork(15,"5","tutto","sempre");
		
		System.out.println("nameprocess:"+ManagementFactory.getRuntimeMXBean().getName());
		
	}
	
	/**
	 * 
	 * @param min
	 */
    public static void dowork(Integer min,String nping, String loglevel, String periodo) {
		
		try {
			PingJob.nping=nping;
			PingJob.loglevel=loglevel;
			PingJob.periodo=periodo;
			PingJob.intervallo=""+min;
			int sec=min*60;
			// specify the job' s details..
			JobDetail job = JobBuilder.newJob(PingJob.class)
			    .withIdentity("PingJob")
			    .build();
			
			// specify the running period of the job
			Trigger trigger = TriggerBuilder.newTrigger()
			      .withSchedule(  
	                    SimpleScheduleBuilder.simpleSchedule()
	                    .withIntervalInSeconds(sec)
	                    .repeatForever())  
                        .build();  
	    	
			//schedule the job
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
	    	sch.start();	    	
	    	sch.scheduleJob(job, trigger);		
		
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		
		//ManagementFactory.getRuntimeMXBean().getName();
		System.out.println("nameprocess:"+ManagementFactory.getRuntimeMXBean().getName());
	}
	
	
}
