package com.aemsite.core.exporters;

import org.apache.sling.models.export.spi.ModelExporter;
import org.apache.sling.models.factory.ExportException;
import org.osgi.service.component.annotations.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Map;

@Component(service = ModelExporter.class) //Annoted as Component because class is used as a service
public class AemSiteXmlExporter implements ModelExporter {
    @Override
    public boolean isSupported(Class<?> aClass) {
        return true;
    }

    @Override
    public <T> T export(Object model, Class<T> aClass, Map<String, String> map) throws ExportException {
        StringWriter stringWriter=new StringWriter();
        try{
            JAXBContext jaxbContext=JAXBContext.newInstance(model.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(model,stringWriter);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return(T)stringWriter.toString();
    }

    @Override
    public String getName() {
        return "xmlexporter";
    }
}