/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.model;

import com.sg.dvdlibraryspringmvc.dao.SearchTerms;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Search {
    SearchTerms searchTerm;
    String criteria;

    public SearchTerms getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(SearchTerms searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.searchTerm);
        hash = 61 * hash + Objects.hashCode(this.criteria);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Search other = (Search) obj;
        if (!Objects.equals(this.criteria, other.criteria)) {
            return false;
        }
        if (this.searchTerm != other.searchTerm) {
            return false;
        }
        return true;
    }

   
}
