package com.sg.dvdlibraryspringmvc.controller;

import com.sg.dvdlibraryspringmvc.dao.DvdListDao;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import com.sg.dvdlibraryspringmvc.model.Search;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
        
    private DvdListDao dao;
    
    public HomeController(DvdListDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Model model) {
        List<Dvd> allDvds = dao.getAllDvds();
        model.addAttribute("dvdList",allDvds);
        Search newSearch = new Search();
        model.addAttribute("newSearch", newSearch);
        return "home";
    }
}
