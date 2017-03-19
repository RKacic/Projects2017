/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.List;
import java.util.Map;


/**
 *
 * @author apprentice
 */
public interface DvdListDao {
    
    public Dvd addDvd(Dvd dvd);
    
    public void removeDvd(Long dvdId);
    
    public void updateDvd(Dvd dvd);
    
    public Dvd getDvdById(Long dvdId);
    
    public List<Dvd> getAllDvds();
    
    public List<Dvd> searchDvds(SearchTerms term, String criteria);
    
}
