package com.aemsite.core.models;

import com.aemsite.core.services.AEMSiteFactoryOSGiConfigDemo;

import java.util.List;

public interface OSGiConfigDemo {

    String getServiceName();
    int getServiceCount();
    Boolean getLiveData();
    String[] getCountries();
    String getRunMode();

    //Writing Config in a separate file and then using it in service
    int getServiceID();
    String getServiceNameModular();
    String getServiceURL();

    //Factory Config
    List<AEMSiteFactoryOSGiConfigDemo> getAllOSGiConfig();
}
