package com.aemsite.core.services;


import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.LoginException;

import java.util.Iterator;

public interface DemoService {
    Iterator<Page> getPages();

}
