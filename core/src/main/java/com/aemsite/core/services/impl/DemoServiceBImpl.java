package com.aemsite.core.services.impl;


import com.aemsite.core.services.DemoService;
import com.aemsite.core.services.DemoServiceB;
import com.aemsite.core.services.MultiService;
import com.day.cq.wcm.api.Page;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = DemoServiceB.class,immediate = true)
public class DemoServiceBImpl implements DemoServiceB {

    @Reference //Annotation to use one service in another service
    //defines references to other services made available to the component by the Service Component Runtime
    DemoService demoService;

    //dont put space after component.name
    @Reference(target = "(component.name=com.aemsite.core.services.impl.MultiServiceBImpl)")
    MultiService multiService;

    @Override
    public String getNameFromReference(){
        return "Response coming from "+multiService.getName();
    }

    @Override
    public List<String> getPageTitleList() {
        Iterator<Page> pageIterator = demoService.getPages();
        List<String> pageList = new ArrayList<>();
        while(pageIterator.hasNext()){
            pageList.add(pageIterator.next().getTitle());
        }
        return pageList;
    }
}
