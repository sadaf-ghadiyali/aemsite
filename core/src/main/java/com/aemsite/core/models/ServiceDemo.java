package com.aemsite.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;

import java.util.Iterator;

public interface ServiceDemo {
    Iterator<Page> getPagesList() throws LoginException;
}
