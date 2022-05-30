package com.aemsite.core.models.impl;

import com.aemsite.core.models.Author;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorImpl implements Author {

    //to get the current page
    @ScriptVariable
    Page currentPage;

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
    public String getCurrentPage(){
        return currentPage.getTitle();
    }


    @Override
    public String getHomePageName(){
        return resource.getName();
    }
}
