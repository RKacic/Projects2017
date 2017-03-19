/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DvdListDao;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import com.sg.dvdlibraryspringmvc.model.Search;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@RequestMapping(value = "/DvdLibraryController")
@Controller
public class DvdLibraryController {
    
    DvdListDao dao;
    
    @Inject
    public DvdLibraryController(DvdListDao dao){
        this.dao = dao;
    }
    
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(HttpServletRequest request, Model model){
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        Dvd dvd = dao.getDvdById(dvdId);
        model.addAttribute("dvd", dvd);
        
        return "dvdDetails";
    }
    
    @RequestMapping(value = "/removeDvd", method = RequestMethod.GET)
    public String removeDvd(HttpServletRequest request, Model model){
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        dao.removeDvd(dvdId);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/createNewDvd", method = RequestMethod.GET)
    public String createNewDvd(Model model){
        Dvd dvd = new Dvd();
        model.addAttribute("newDvd", dvd);
        return "createDvd";
    }
    
    @RequestMapping(value = "/addNewDvd", method = RequestMethod.POST)
    public String addNewDvd(@ModelAttribute("dvd") Dvd dvd){
        dao.addDvd(dvd);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/editDvd", method = RequestMethod.GET)
    public String editDvd(HttpServletRequest request, Model model){
        String dvdIdParameter = request.getParameter("dvdId");
        long dvdId = Long.parseLong(dvdIdParameter);
        model.addAttribute("oldDvd",dao.getDvdById(dvdId));
        return "editDvd";
    }
    
    @RequestMapping(value = "/addEditedDvd", method = RequestMethod.POST)
    public String addEditedDvd(@ModelAttribute("dvd") Dvd dvd){
        dao.updateDvd(dvd);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/searchDvds", method = RequestMethod.GET)
    public String searchDvds(@ModelAttribute("newSearch") Search search, Model model){
        List<Dvd> results = dao.searchDvds(search.getSearchTerm(), search.getCriteria());
        model.addAttribute("dvdList", results);
        int size = results.size();
        model.addAttribute("size", size);
        return "searchResults";
    }
    
}
