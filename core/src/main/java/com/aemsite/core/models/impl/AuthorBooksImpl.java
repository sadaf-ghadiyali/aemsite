package com.aemsite.core.models.impl;

import com.aemsite.core.helpers.MultifieldHelper;
import com.aemsite.core.helpers.NestedMultifieldHelper;
import com.aemsite.core.models.AuthorBooks;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = AuthorBooks.class,
        resourceType = AuthorBooksImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name="xmlexporter", extensions = "xml")
@XmlRootElement(name = "demosite-exporter") //this is mandatory in xml exporter
public class AuthorBooksImpl implements AuthorBooks {

    public static final Logger log = LoggerFactory.getLogger(AuthorBooksImpl.class);
    static final String RESOURCE_TYPE="aemsite/components/author-books";

    @ValueMapValue
    String authorname;

    @ValueMapValue
    List<String> books;


    @Override
    @XmlElement //this is mandatory in xml exporter
    public String getAuthorName() {
        return authorname;
    }

    @Override
    @XmlElement
    public List<String> getBooks() {
        if(books!=null){
            return new ArrayList<String>(books) ;
        }
        else{
            return Collections.emptyList();
        }

    }

    @ChildResource(name = "bookdetailswithmap")
    Resource bookDetailsMapResource;

    @Override
    public List<Map<String, String>> getBookDetailsWithMap() {

        List<Map<String,String>> bookDetailsMap = new ArrayList<>();
        try{
            if(bookDetailsMapResource!=null){
                for(Resource book : bookDetailsMapResource.getChildren()){
                    Map<String, String> bookMap = new HashMap<>();
                    bookMap.put("bookname",book.getValueMap().get("bookname",String.class));
                    bookMap.put("booksubject",book.getValueMap().get("booksubject",String.class));
                    bookMap.put("publishyear",book.getValueMap().get("publishyear",String.class));
                    bookDetailsMap.add(bookMap);
                }
            }
        }catch (Exception e){
            log.info("\n ERROR white getting Book Details {} ",e.getMessage());
        }
        return bookDetailsMap;
    }

    @ChildResource(name="bookdetailswithbean")
    Resource bookDetailsBeanResource;

    @Override
    public List<MultifieldHelper> getBookDetailsWithBean() {
        List<MultifieldHelper> bookDetailsList = new ArrayList<>();
        try{
            if(bookDetailsBeanResource!=null){
                for(Resource book:bookDetailsBeanResource.getChildren()){
                    MultifieldHelper multifieldHelper = new MultifieldHelper(book);
                    bookDetailsList.add(multifieldHelper);
                }
            }
        }catch (Exception e){
            log.info("\nERROR while getting Book Details {} ",e.getMessage());
        }

        return bookDetailsList;
    }

    @ChildResource(name = "nestedbookdetails")
    Resource nestedBookDetailsResource;

    @Override
    public List<MultifieldHelper> getBookDetailsNestedMultifield() {
        List<MultifieldHelper> bookDetailsNested = new ArrayList<>();

        try{
            if(nestedBookDetailsResource!=null){
                for(Resource book : nestedBookDetailsResource.getChildren()){
                    MultifieldHelper multifieldHelper = new MultifieldHelper(book);
                    Resource nestedResource = book.getChild("bookeditions");

                    if(nestedResource!=null){
                        List<NestedMultifieldHelper> nestedList = new ArrayList<>();
                        for(Resource bookEdition:nestedResource.getChildren()){
                            NestedMultifieldHelper nestedMultifieldHelper = new NestedMultifieldHelper(bookEdition);
                            nestedList.add(nestedMultifieldHelper);
                        }
                        multifieldHelper.setBookEditions(nestedList);
                    }

                    bookDetailsNested.add(multifieldHelper);
                }
            }
        }catch (Exception e){
            log.info("\nERROR while getting Book Details {} ",e.getMessage());
        }

        return bookDetailsNested;
    }


}
