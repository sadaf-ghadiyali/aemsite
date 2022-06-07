package com.aemsite.core.services.impl;


import com.aemsite.core.services.SearchService;
import com.aemsite.core.utils.ResolverUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = SearchService.class, immediate = true)
public class SearchServiceImpl implements SearchService {

    static final Logger LOG = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Reference
    QueryBuilder queryBuilder;

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Activate
    public void activate(){
        LOG.info("\n==============ACTIVATE SEARCH METHOD==================");
    }

    private Map<String, String> createTextSearchQuery(String searchText, int startResult, int resultPerPage){
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("type","cq:Page");
        queryMap.put("path","/content/we-retail");
        queryMap.put("fulltext",searchText);
        queryMap.put("offset",Long.toString(startResult));
        queryMap.put("p.limit",Long.toString(-1));
        return queryMap;

    }


    @Override
    public JSONObject searchResult(String searchText, int startResult, int resultPerPage) {
        JSONObject searchResult = new JSONObject();
        try{
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            final Session session = resourceResolver.adaptTo(Session.class);
            Query query = queryBuilder.createQuery(PredicateGroup.create(
                    createTextSearchQuery(searchText,startResult,resultPerPage)),session);
            SearchResult result = query.getResult();

            int resultsPerPage = result.getHits().size();
            long totalResults = result.getTotalMatches();
            long startingResult = result.getStartIndex();
            double totalPages = Math.ceil(totalResults / resultPerPage);

            searchResult.put("resultsPerPage",resultsPerPage);
            searchResult.put("totalResults",totalResults);
            searchResult.put("startingResult",startingResult);
            searchResult.put("pages",totalPages);

            List<Hit> hits = result.getHits();
            JSONArray jsonArray = new JSONArray();
            for(Hit hit : hits){
                Page page = hit.getResource().adaptTo(Page.class);
                JSONObject object = new JSONObject();
                object.put("title",page.getTitle());
                object.put("path",page.getPath());
                jsonArray.put(object);
            }
            searchResult.put("results",jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
    }
}
