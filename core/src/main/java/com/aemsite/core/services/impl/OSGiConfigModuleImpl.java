package com.aemsite.core.services.impl;

import com.aemsite.core.configs.AEMSiteOSGiConfig;
import com.aemsite.core.services.OSGiConfigModule;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = OSGiConfigModule.class, immediate = true)
@Designate(ocd = AEMSiteOSGiConfig.class)
public class OSGiConfigModuleImpl implements OSGiConfigModule {

    private int serviceID;
    private String serviceName;
    private String serviceURL;

    @Activate
    public void init(AEMSiteOSGiConfig aemSiteOSGiConfig){
        this.serviceID= aemSiteOSGiConfig.serviceID();
        this.serviceName=aemSiteOSGiConfig.serviceName();
        this.serviceURL= aemSiteOSGiConfig.serviceURL();
    }

    @Override
    public int getServiceID() {
        return serviceID;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getServiceURL() {
        return serviceURL;
    }
}
