package com.aemsite.core.schedulers;

import com.aemsite.core.configs.SchedulerConfig;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SchedulerConfig.class )
public class AEMSiteScheduler implements Runnable{
    static final Logger LOG = LoggerFactory.getLogger(AEMSiteScheduler.class);

    @Reference
    Scheduler scheduler;

    private int schedulerId;

    @Activate
    public void activate(SchedulerConfig schedulerConfig){
        schedulerId= schedulerConfig.schedulerName().hashCode();
        addScheduler(schedulerConfig);
    }

    @Deactivate
    public void deactivate(SchedulerConfig schedulerConfig){
        removeScheduler();
    }

    public void removeScheduler(){
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    public void addScheduler(SchedulerConfig schedulerConfig){
        ScheduleOptions scheduleOptions = scheduler.EXPR(schedulerConfig.cronExpression());
        scheduleOptions.name(schedulerConfig.schedulerName());
        scheduleOptions.canRunConcurrently(true);
        scheduler.schedule(this,scheduleOptions);
        LOG.info("\n----------Scheduler Added----------");
        ScheduleOptions scheduleOptionsNow = scheduler.NOW(2,10); //whenever we build the project this scheduler will run 2 times in a time interval of 10 secs
        scheduler.schedule(this,scheduleOptionsNow);
    }

    @Override
    public void run() {
        //LOG.info("\n============RUN METHOD EXECUTING=============");
    }
}
