package com.aemsite.core.services.impl;

import com.aemsite.core.services.MultiService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component(service = MultiService.class,immediate = true)
@ServiceRanking(1001) //Service implementation with higher ranking will be called first
public class MultiServiceBImpl implements MultiService {
    @Override
    public String getName() {
        return "MultiServiceB";
    }
}
