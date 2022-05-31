package com.aemsite.core.helpers;

import org.apache.sling.api.resource.Resource;

import java.util.Date;

public class NestedMultifieldHelper {

    private String bookEdition;
    private Date editionDate;

    public String getBookEdition() {
        return bookEdition;
    }

        public Date getEditionDate() {
        return editionDate;
    }

    public NestedMultifieldHelper(Resource resource) {
        this.bookEdition = resource.getValueMap().get("bookedition",String.class);
        this.editionDate = resource.getValueMap().get("editiondate",Date.class);
    }
}
