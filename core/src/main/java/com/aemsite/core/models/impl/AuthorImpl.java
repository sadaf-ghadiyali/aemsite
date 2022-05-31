package com.aemsite.core.models.impl;

import com.aemsite.core.models.Author;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.QueryBuilder;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        resourceType = AuthorImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json", selector="aemsite",
options = {
        @ExporterOption(name= "SerializationFeature.WRAP_ROOT_VALUE" ,value="true"),
        @ExporterOption(name= "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value="true")
})
@JsonRootName("author-details")
public class AuthorImpl implements Author {

    public static final Logger log = LoggerFactory.getLogger(AuthorImpl.class);
    static final String RESOURCE_TYPE="aemsite/components/author";

    //to get the current page
    @ScriptVariable
    Page currentPage;

    @OSGiService //to inject any OOB Service or custom service created by us
    QueryBuilder queryBuilder;

    //To inject resource by path
    @ResourcePath(path = "/content/aemsite/us/en/home")
    @Via("resource")
    Resource resource;

    @ValueMapValue //Injecting value. if adaptables was Resource.class, @Inject annotation would have been used
    @Required
    @Default(values = "AEM")
    String fname;

    @ValueMapValue
    @Default(values = "SITE")
    String lname;

    @ValueMapValue
    Boolean professor;

    @ValueMapValue
    List<String> books;

    @RequestAttribute(name = "myAttribute")
    String myAttribute;

    @Inject
    @Via("resource")
    @Named("jcr:lastModifiedBy") //To change the name of the property of component
    String lastModifiedBy;

    @JsonProperty("authorName")
    public String authorName(){
        return "AEM AUthor";
    }

    @Override
    public String getFirstName() {
        return fname; //name as defined in the author component
    }

    @Override
    public String getLastName() {
        return lname;
    }

    @Override
    public Boolean getProfessor() {
        return professor;
    }

    @JsonIgnore
    public String getMyAttribute() {
        return myAttribute;
    }

    @Override
    public String getLastModifiedBy(){
        return lastModifiedBy;
    }

    @Override
    public List<String> getBooks() {
        if(books!=null){
            return new ArrayList<String>(books) ;
        }
        else{
            return Collections.emptyList();
        }
    }

    @Override
    public String getCurrentPage(){
        return currentPage.getTitle();
    }


    @Override
    public String getHomePageName(){
        return resource.getName();
    }

    @PostConstruct //Method annotated with postcontruct will be called once all the fields are injected
    public void init(){
        log.info(currentPage.getTitle()+" : "+resource.getName());
    }
}
