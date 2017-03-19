/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdListDaoMemoryImpl implements DvdListDao{
    
    private Map<Long, Dvd> dvdList = new HashMap<>();
    private static long idCounter = 0;
    
    public DvdListDaoMemoryImpl(){
        Dvd dvd1 = new Dvd();
        dvd1.setTitle("a great tale");
        dvd1.setDirector("speilburg");
        dvd1.setReleaseYear("2010");
        dvd1.setRating("PG-13");
        dvd1.setNotes("great");
        addDvd(dvd1);
        
        Dvd dvd2 = new Dvd();
        dvd2.setTitle("a good tale");
        dvd2.setDirector("speilburg");
        dvd2.setReleaseYear("2000");
        dvd2.setRating("PG");
        dvd2.setNotes("it was good");
        addDvd(dvd2);
        
        Dvd dvd3 = new Dvd();
        dvd3.setTitle("an okay tale");
        dvd3.setDirector("speilburg");
        dvd3.setReleaseYear("1984");
        dvd3.setRating("R");
        dvd3.setNotes("eh it was okay");
        addDvd(dvd3);
    }
    
    
    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setDvdId(idCounter);
        idCounter++;
        dvdList.put(dvd.getDvdId(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(Long dvdId) {
        dvdList.remove(dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdList.put(dvd.getDvdId(), dvd);
    }

    @Override
    public Dvd getDvdById(Long dvdId) {
        return dvdList.get(dvdId);
    }

    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> dvds = dvdList.values();
        return new ArrayList(dvds);
    }

    @Override
    public List<Dvd> searchDvds(SearchTerms term, String criteria) {
        
        List<Dvd> matches = new ArrayList<>();
        Predicate<Dvd> match = null;
        
        if (term == SearchTerms.TITLE){
            match = (d) -> d.getTitle().startsWith(criteria);
        }
        if (term == SearchTerms.DIRECTOR){
            match = (d) -> d.getDirector().equalsIgnoreCase(criteria);
        }
        if (term == SearchTerms.RELEASE_YEAR){
            match = (d) -> d.getReleaseYear().equals(criteria);
        }
        if (term == SearchTerms.RATING){
            match = (d) -> d.getRating().equalsIgnoreCase(criteria);
        }
        
        matches = dvdList.values().stream()
                .filter(match)
                .collect(Collectors.toList());
        
        return matches;
    }
    
}
