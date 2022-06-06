package com.aemsite.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AEM Site Scheduler Job - Factory Config",
        description = "Factory Config for Aem Scheduler Jobs")
public @interface SchedulerJobFactoryConfig {

    @AttributeDefinition(name = "Country",
    description = "Country for scheduler",
    type= AttributeType.STRING)
    String country() default "IN";

    @AttributeDefinition(name = "Cron Expression",
            description = "Cron Expression for scheduler",
            type= AttributeType.STRING)
    String cronExpression() default "0 0/1 * 1/1 * ? *";  //every minute

    @AttributeDefinition(name = "URL",
            description = "Country URL for scheduler",
            type= AttributeType.STRING)
    String url() default "www.in.com";


}
