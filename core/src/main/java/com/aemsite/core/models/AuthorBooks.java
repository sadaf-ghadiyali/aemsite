package com.aemsite.core.models;

import java.util.List;
import java.util.Map;

public interface AuthorBooks {
    String getAuthorName();
    List<String> getBooks();
    List<Map<String,String>> getBookDetailsWithMap();
}
