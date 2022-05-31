package com.aemsite.core.models;

import java.util.List;

public interface Author {
    String getFirstName();
    String getLastName();
    Boolean getProfessor();
    String getCurrentPage();
    String getHomePageName();
    String getLastModifiedBy();
    List<String> getBooks();
}
