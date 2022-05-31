package com.aemsite.core.helpers;

import org.apache.sling.api.resource.Resource;

import java.util.List;

public class MultifieldHelper {

    private String bookName;
    private String bookSubject;
    private String publishYear;
    private List<NestedMultifieldHelper> bookEditions;



    public MultifieldHelper(Resource resource) {

        this.bookName = resource.getValueMap().get("bookname",String.class);
        this.bookSubject = resource.getValueMap().get("booksubject",String.class);
        this.publishYear = resource.getValueMap().get("publishyear",String.class);
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookSubject() {
        return bookSubject;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public List<NestedMultifieldHelper> getBookEditions() {
        return bookEditions;
    }

    public void setBookEditions(List<NestedMultifieldHelper> bookEditions) {
        this.bookEditions = bookEditions;
    }



}
