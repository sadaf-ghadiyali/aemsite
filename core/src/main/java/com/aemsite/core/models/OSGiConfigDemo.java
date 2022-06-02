package com.aemsite.core.models;

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
}
