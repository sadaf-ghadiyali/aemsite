package com.aemsite.core.models.impl;

import com.aemsite.core.models.OSGiConfigDemo;
import com.aemsite.core.services.OSGiConfig;
import com.aemsite.core.services.OSGiConfigModule;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = OSGiConfigDemo.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiConfigDemoImpl implements OSGiConfigDemo {

    @OSGiService
    OSGiConfig osGiConfig;

    @OSGiService
    OSGiConfigModule osGiConfigModule;


    @Override
    public String getServiceName() {
        return osGiConfig.getServiceName();
    }

    @Override
    public int getServiceCount() {
        return osGiConfig.getServiceCount();
    }

    @Override
    public Boolean getLiveData() {
        return osGiConfig.getLiveData();
    }

    @Override
    public String[] getCountries() {
        return osGiConfig.getCountries();
    }

    @Override
    public String getRunMode() {
        return osGiConfig.getRunMode();
    }

    @Override
    public int getServiceID() {
        return osGiConfigModule.getServiceID();
    }

    @Override
    public String getServiceNameModular() {
        return osGiConfigModule.getServiceName();
    }

    @Override
    public String getServiceURL() {
        return osGiConfigModule.getServiceURL();
    }
}
