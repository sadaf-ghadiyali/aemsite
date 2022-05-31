package com.aemsite.core.models.impl;

import com.aemsite.core.helpers.MultifieldHelper;
import com.aemsite.core.models.AuthorBooks;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = AuthorBooks.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorBooksImpl implements AuthorBooks {

    public static final Logger log = LoggerFactory.getLogger(AuthorBooksImpl.class);

    @ValueMapValue
    String authorname;

    @ValueMapValue
    List<String> books;


    @Override
    public String getAuthorName() {
        return authorname;
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


}
