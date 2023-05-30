package hr.tvz.dodig.hardwareapp.scheduler;

import hr.tvz.dodig.hardwareapp.service.HardwareService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AvailableHardwareJob extends QuartzJobBean {


    @Autowired
    private HardwareService hardwareService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("-------------------------------");
        this.hardwareService.findAll().forEach(h -> System.out.println(h.toString()));
        System.out.println("-------------------------------\n");
    }

}
