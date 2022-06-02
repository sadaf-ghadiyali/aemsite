package com.aemsite.core.utils;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;


import java.util.HashMap;
import java.util.Map;

public class ResolverUtil {
    ResolverUtil(){}

    public static final String AEMSITE_SERVICE_USER = "aemsiteserviceuser";

    public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException {
        final Map<String,Object> paramMap= new HashMap<>();
        paramMap.put(ResourceResolverFactory.SUBSERVICE,AEMSITE_SERVICE_USER);
        //fetches the admin service resolver using service user
        ResourceResolver resourceResolver=resourceResolverFactory.getServiceResourceResolver(paramMap);
        return resourceResolver;
    }
}
