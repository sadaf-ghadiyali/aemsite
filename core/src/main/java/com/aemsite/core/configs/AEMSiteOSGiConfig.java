package com.aemsite.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AEM Site - Modular OSGi Configuration",
        description = "Modular OSGi Configuration demo")
public @interface AEMSiteOSGiConfig {
    @AttributeDefinition(
            name = "Service ID",
            description = "Enter service id.",
            type = AttributeType.INTEGER)
    int serviceID();

    @AttributeDefinition(
            name = "Service Name",
            description = "Enter service name.",
            type = AttributeType.STRING)
    String serviceName() default "AEM Site Service";

    @AttributeDefinition(
            name = "Service URL",
            description = "Add Service URL.",
            type = AttributeType.STRING
    )
    String serviceURL() default "localhost";
}

