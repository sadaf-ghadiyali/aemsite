package com.aemsite.core.schedulers;

import com.aemsite.core.configs.SchedulerConfig;
import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



@Component(service = Job.class, immediate = true)
@Designate(ocd = SchedulerConfig.class)
public class AEMSiteSchedulerJobs implements Job {

    static final Logger LOG = LoggerFactory.getLogger(AEMSiteSchedulerJobs.class);

    private int schedulerJobId;

    @Reference
    Scheduler scheduler;

    @Activate
    public void activate(SchedulerConfig schedulerConfig){
        schedulerJobId = schedulerConfig.schedulerName().hashCode();
        addSchedulerJob(schedulerConfig);
    }

    @Deactivate
    public void deactivate(){
        removeSchedulerJob();
    }

    private void removeSchedulerJob() {
        scheduler.unschedule(String.valueOf(schedulerJobId));
    }

    private void addSchedulerJob(SchedulerConfig schedulerConfig) {
        ScheduleOptions in = scheduler.EXPR("0 12 19 1/1 * ? *");
        Map<String, Serializable> inMap = new HashMap<>();
        inMap.put("country","IN");
        inMap.put("url","www.in.com");
        in.config(inMap);
        scheduler.schedule(this,in);

        ScheduleOptions de = scheduler.EXPR("0 13 19 1/1 * ? *");
        Map<String, Serializable> deMap = new HashMap<>();
        deMap.put("country","DE");
        deMap.put("url","www.de.com");
        de.config(deMap);
        scheduler.schedule(this,de);

        ScheduleOptions us = scheduler.EXPR("0 14 19 1/1 * ? *");
        Map<String, Serializable> usMap = new HashMap<>();
        usMap.put("country","US");
        usMap.put("url","www.us.com");
        us.config(usMap);
        scheduler.schedule(this,us);

    }

    @Override
    public void execute(JobContext jobContext) {
        LOG.info("\n======================================================================================");
        LOG.info("\n =======> COUNTRY {} : URL {} "
                ,jobContext.getConfiguration().get("country"),
                jobContext.getConfiguration().get("url")
        );
    }
}
