package com.aemsite.core.services;

import java.util.List;

public interface AEMSiteFactoryOSGiConfigDemo {
    int getServiceID();

    String getServiceName();

    String getServiceURL();

    List<AEMSiteFactoryOSGiConfigDemo> getAllConfig();
}
