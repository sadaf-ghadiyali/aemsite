package com.aemsite.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;

import java.util.Iterator;
import java.util.List;

public interface ServiceDemo {
    Iterator<Page> getPagesList() throws LoginException;
    List<String> getPageTitles() ;

    String getServiceName();

    String getServiceNameFromReference();
}
