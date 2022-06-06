package com.aemsite.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Component(service = Servlet.class)
@SlingServletPaths(value = {"/bin/pages","/aemsite/pages"})
public class AEMSitePathServlet extends SlingAllMethodsServlet {
    static final Logger LOG = LoggerFactory.getLogger(AEMSitePathServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        Page page = pageManager.getPage("/content/aemsite/us/en");
        Iterator<Page> childrenPages = page.listChildren();
        JSONArray pagesArray = new JSONArray();
        try{
            while(childrenPages.hasNext()){
                Page childPage = childrenPages.next();
                JSONObject pageObject = new JSONObject();
                pageObject.put(childPage.getTitle(),childPage.getPath());
                pagesArray.put(pageObject);

            }
        }catch(Exception e){
            LOG.info("\n ERROR {}",e.getMessage());
        }
        response.setContentType("application/json");
        response.getWriter().write(pagesArray.toString());
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
