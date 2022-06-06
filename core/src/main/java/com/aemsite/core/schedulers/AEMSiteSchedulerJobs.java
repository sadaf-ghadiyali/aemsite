package com.aemsite.core.schedulers;

import com.aemsite.core.configs.SchedulerJobFactoryConfig;
import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Component(service = Job.class, immediate = true)
@Designate(ocd = SchedulerJobFactoryConfig.class, factory = true)
public class AEMSiteSchedulerJobs implements Job {

    static final Logger LOG = LoggerFactory.getLogger(AEMSiteSchedulerJobs.class);

    private int schedulerJobId;

    private List<SchedulerJobFactoryConfig> schedulerConfigList;

    @Reference
    Scheduler scheduler;

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindSchedulerConfig(SchedulerJobFactoryConfig schedulerJobFactoryConfig){
        if(schedulerConfigList == null){
            schedulerConfigList = new ArrayList<>();
        }
        schedulerConfigList.add(schedulerJobFactoryConfig);
    }

    public void unbindSchedulerConfig(SchedulerJobFactoryConfig schedulerJobFactoryConfig){
        schedulerConfigList.remove(schedulerJobFactoryConfig);
    }

    @Activate
    public void activate(SchedulerJobFactoryConfig schedulerConfig){
        schedulerJobId = schedulerConfig.country().hashCode();
        addSchedulerJob(schedulerConfig);
    }

    @Deactivate
    public void deactivate(){
        removeSchedulerJob();
    }

    private void removeSchedulerJob() {
        scheduler.unschedule(String.valueOf(schedulerJobId));
    }

    private void addSchedulerJob(SchedulerJobFactoryConfig schedulerConfig) {
        ScheduleOptions scheduleOptions = scheduler.EXPR(schedulerConfig.cronExpression());
        Map<String, Serializable> scheduleMap = new HashMap<>();
        scheduleMap.put("country",schedulerConfig.country());
        scheduleMap.put("url",schedulerConfig.url());
        scheduleOptions.config(scheduleMap);
        scheduler.schedule(this,scheduleOptions);

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
