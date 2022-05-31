package com.aemsite.core.models;

import com.aemsite.core.helpers.MultifieldHelper;

import java.util.List;
import java.util.Map;

public interface AuthorBooks {
    String getAuthorName();
    List<String> getBooks();
    List<Map<String,String>> getBookDetailsWithMap();
    List<MultifieldHelper> getBookDetailsWithBean();
    List<MultifieldHelper> getBookDetailsNestedMultifield();
}
