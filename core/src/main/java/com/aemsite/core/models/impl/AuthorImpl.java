package com.aemsite.core.models.impl;

import com.aemsite.core.models.Author;
import com.day.cq.wcm.api.Page;
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


@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorImpl implements Author {

    public static final Logger log = LoggerFactory.getLogger(AuthorImpl.class);

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

    @RequestAttribute(name = "myAttribute")
    String myAttribute;

    @Inject
    @Via("resource")
    @Named("jcr:lastModifiedBy") //To change the name of the property of component
    String lastModifiedBy;

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

    public String getMyAttribute() {
        return myAttribute;
    }

    @Override
    public String getLastModifiedBy(){
        return lastModifiedBy;
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
