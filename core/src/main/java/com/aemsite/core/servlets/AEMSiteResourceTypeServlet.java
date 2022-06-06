package com.aemsite.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(resourceTypes = {"aemsite/components/page"},
        methods = {HttpConstants.METHOD_GET, HttpConstants.METHOD_POST},
        selectors = {"aem","test"},
        extensions = {"txt","xml"})
public class AEMSiteResourceTypeServlet extends SlingAllMethodsServlet {
    static final Logger LOG = LoggerFactory.getLogger(AEMSiteResourceTypeServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Resource resource = request.getResource();
        response.setContentType("text/plain");
        response.getWriter().write("Page Title: "+resource.getValueMap().get(JcrConstants.JCR_TITLE));
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try{
            LOG.info("==============STARTED POST=============");
            List<RequestParameter> parameterList = request.getRequestParameterList();
            for(RequestParameter parameter : parameterList){
                LOG.info("\n PARAMETER ====> {} :{} ",parameter.getName(),parameter.getString());
            }
        }catch (Exception e){
            LOG.info("\n ERROR in REQUEST {}",e.getMessage());
        }
        response.getWriter().write("==========FORM SUBMITTED===========");
    }
}
