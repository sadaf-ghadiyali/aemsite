package com.aemsite.core.services.impl;

import com.aemsite.core.services.DemoService;
import com.aemsite.core.utils.ResolverUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

@Component(service = DemoService.class, immediate = true)
public class DemoServiceAImpl implements DemoService{

    static final Logger LOG= LoggerFactory.getLogger(DemoServiceAImpl.class);

    @Activate
    public void activate(ComponentContext componentContext){
        LOG.info("\n================ACTIVATE=================");
        LOG.info("\n {} = {}",componentContext.getBundleContext().getBundle(),componentContext.getBundleContext().getBundle().getSymbolicName());
    }

    @Deactivate
    public void deactivate(){
        LOG.info("/n=============DEACTIVATE===========");
    }

    @Modified
    public void modified(){
        LOG.info("\n============MODIFIED============");
    }

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Override
    public Iterator<Page> getPages(){
        try{
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            PageManager pageManager=resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage("/content/aemsite/us/en");
            Iterator<Page> pageIterator = page.listChildren();
            return pageIterator;
        }catch (LoginException e){
            LOG.info("\n=======ERROR: "+e.getMessage()+"===========");
        }
        return null;

    }
}
