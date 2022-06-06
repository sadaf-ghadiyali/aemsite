package com.aemsite.core.services.impl;

import com.aemsite.core.configs.AEMSiteFactoryOSGiConfig;
import com.aemsite.core.services.AEMSiteFactoryOSGiConfigDemo;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import java.util.ArrayList;
import java.util.List;

@Component(service = AEMSiteFactoryOSGiConfigDemo.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = AEMSiteFactoryOSGiConfig.class, factory = true)
public class AEMSiteFactoryOSGiConfigDemoImpl implements AEMSiteFactoryOSGiConfigDemo {

    private int serviceID;
    private String serviceName;
    private String serviceURL;

    private List<AEMSiteFactoryOSGiConfigDemo> configList;

    @Activate
    public void init(AEMSiteFactoryOSGiConfig config){
        this.serviceID= config.serviceID();
        this.serviceName=config.serviceName();
        this.serviceURL= config.serviceURL();
    }

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindOSGiFactory(final AEMSiteFactoryOSGiConfigDemo config){
        if(configList == null){
            configList = new ArrayList<>();
        }
        configList.add(config);

    }

    //02.06.2022 19:29:24.073 *ERROR* [0:0:0:0:0:0:0:1 [1654178363885]
    // GET /content/aemsite/us/en.html HTTP/1.1] org.apache.sling.servlets.get.impl.DefaultGetServlet
    // No renderer for extension html, cannot render resource JcrNodeResource,
    // type=aemsite/components/osgiconfigmoduledemo, superType=null,
    // path=/content/aemsite/us/en/jcr:content/root/container/container/osgiconfigmoduledemo

    public void unbindOSGiFactory(final AEMSiteFactoryOSGiConfigDemo config){
        configList.remove(config);
    }

    @Override
    public int getServiceID() {
        return serviceID;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getServiceURL() {
        return serviceURL;
    }

    @Override
    public List<AEMSiteFactoryOSGiConfigDemo> getAllConfig() {
        return configList;
    }
}
