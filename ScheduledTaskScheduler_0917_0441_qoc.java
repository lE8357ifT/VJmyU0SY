// 代码生成时间: 2025-09-17 04:41:41
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.guice.JerseyGuiceBridge;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import javax.inject.Singleton;

@Singleton
public class ScheduledTaskScheduler {

    private Scheduler scheduler;

    public void initialize() throws SchedulerException {
        // Create a service locator instance
        ServiceLocator locator = ServiceLocatorFactory.getInstance().create("scheduledTaskScheduler");

        // Integrate Jersey with HK2
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(locator);

        // Initialize the scheduler
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
    }

    public void scheduleTask(String jobName, Class<? extends Job> jobClass, String cronExpression) throws SchedulerException {
        // Create a job detail instance
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, "group1")
                .build();

        // Create a cron trigger instance
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();

        // Schedule the job
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void shutdown() throws SchedulerException {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}

// Example of a job class that implements the Job interface
public class ExampleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Your job execution logic here
        System.out.println("Executing scheduled task...");

        try {
            // Simulate job execution time
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new JobExecutionException(e);
        }
    }
}
