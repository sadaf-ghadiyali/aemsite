package com.aemsite.core.servlets;

import com.aemsite.core.services.SearchService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/aemsite/search")
public class AEMSiteSearchServlet extends SlingSafeMethodsServlet {
    static final Logger LOG = LoggerFactory.getLogger(AEMSiteSearchServlet.class);

    @Reference
    SearchService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        JSONObject searchResult = null;
        try{
            String searchText = request.getParameter("searchText");
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"))-1;
            int resultPerPage = Integer.parseInt(request.getParameter("resultPerPage"));
            int startResult = pageNumber * resultPerPage;
            searchResult = service.searchResult(searchText,startResult,resultPerPage);
        }catch (Exception e){
            LOG.info("\n ERROR {}",e.getMessage());
        }

        response.setContentType("application/json");
        response.getWriter().write(searchResult.toString());
    }
}
