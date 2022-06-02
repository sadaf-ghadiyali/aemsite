package com.aemsite.core.models.impl;

import com.aemsite.core.services.DemoServiceB;
import com.aemsite.core.services.MultiService;
import com.day.cq.wcm.api.Page;
import com.aemsite.core.models.ServiceDemo;
import com.aemsite.core.services.DemoService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = ServiceDemo.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl implements ServiceDemo {

    static final Logger LOG = LoggerFactory.getLogger(ServiceDemoImpl.class);

    @OSGiService
    DemoService demoService;

    @OSGiService
    DemoServiceB demoServiceB;

    @OSGiService(
            //filter = "(component.name=com.aemsite.core.services.impl.MultiServiceAImpl)"//incase you want to call service with lower service ranking
            filter = "(component.name=ServiceA)"
    )
    MultiService multiService;

    @Override
    public Iterator<Page> getPagesList() {
        return demoService.getPages();
    }

    @Override
    public List<String> getPageTitles() {
        return demoServiceB.getPageTitleList();
    }

    @Override
    public String getServiceName() {
        return multiService.getName();
    }

    @Override
    public String getServiceNameFromReference(){
        return demoServiceB.getNameFromReference();
    }

}
