package com.aemsite.core.configs;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AEM Site - Scheduler Configuration",
                        description =  "Sling Scheduler Configuration")
public @interface SchedulerConfig {
    @AttributeDefinition(name = "Scheduler Name",
    description = "Enter Name of Scheduler",
    type = AttributeType.STRING)
    String schedulerName() default "AEM Site Scheduler";

    @AttributeDefinition(name = "Cron Expression",
            description = "Cron Expression Used by the Scheduler",
            type = AttributeType.STRING)
    String cronExpression() default "0/20 * * * * ?";

}
