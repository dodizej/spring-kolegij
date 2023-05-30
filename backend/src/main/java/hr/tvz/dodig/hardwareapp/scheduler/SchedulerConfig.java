package hr.tvz.dodig.hardwareapp.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail getAvailableHardwareJob() {
        return JobBuilder.newJob(AvailableHardwareJob.class)
                .withIdentity("AvailableHardwareJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger getAvailableHardwareJobTrigger() {

//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(getAvailableHardwareJob())
                .withIdentity("AvailableHardwareJob")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?"))
                .build();
    }

}
