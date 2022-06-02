package com.aemsite.core.services.impl;

import com.aemsite.core.services.OSGiConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.*;

import javax.annotation.PostConstruct;

@Component(service=OSGiConfig.class, immediate = true)
@Designate(ocd = OSGiConfigImpl.ServiceConfig.class)
public class OSGiConfigImpl implements OSGiConfig {

    @ObjectClassDefinition(name="AEM Site - OSGi Configuration",
    description = "OSGi Configuration demo")
    public @interface ServiceConfig{
        @AttributeDefinition(
                name="Service name",
                description = "Enter service name",
                type = AttributeType.STRING
        )
        String serviceName() default "AEM Site Service";

        @AttributeDefinition(
                name="Service Count",
                description = "Enter Service Count",
                type=AttributeType.INTEGER
        )
        int serviceCount() default 5;

        @AttributeDefinition(
                name="Live Data",
                description = "Check this to get Live data",
                type = AttributeType.BOOLEAN
        )
        boolean liveData() default false;

        @AttributeDefinition(
                name= "Countries",
                description = "Add countries locales for which you want to run this service.",
                type = AttributeType.STRING
        )
        String[] countries() default {"de","in"};

        @AttributeDefinition(
                name="Run modes",
                description = "Select Run Mode.",
                options = {
                    @Option(label = "Author",value = "author"),
                    @Option(label = "Publisher",value = "publish"),
                    @Option(label = "Both",value = "both"),
                }
        )
        String runMode() default "both";
    }

    private String serviceName;
    private int serviceCount;
    private boolean liveData;
    private String[] countries;
    private String runModes;

    @Activate
    public void init(ServiceConfig serviceConfig){
        this.serviceName=serviceConfig.serviceName();
        this.runModes=serviceConfig.runMode();
        this.countries=serviceConfig.countries();
        this.liveData=serviceConfig.liveData();
        this.serviceCount=serviceConfig.serviceCount();
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public int getServiceCount() {
        return serviceCount;
    }

    @Override
    public Boolean getLiveData() {
        return liveData;
    }

    @Override
    public String[] getCountries() {
        return countries;
    }

    @Override
    public String getRunMode() {
        return runModes;
    }

}
