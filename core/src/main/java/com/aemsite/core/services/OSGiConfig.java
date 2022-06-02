package com.aemsite.core.services;

public interface OSGiConfig {

    String getServiceName();
    int getServiceCount();
    Boolean getLiveData();
    String[] getCountries();
    String getRunMode();
}
