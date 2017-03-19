/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class DvdListDaoTest {
    
    private DvdListDao dao;
    
    public DvdListDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dvdListDao", DvdListDao.class);
        for(Dvd dvd: dao.getAllDvds()){
            dao.removeDvd(dvd.getDvdId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvdListDao.
     */
    @Test
    public void testAddGetDeleteDvd() {
        Dvd dvd1 = new Dvd();
        dvd1.setTitle("bambi");
        dvd1.setDirector("speilburg");
        dvd1.setReleaseYear("1984");
        dvd1.setRating("R");
        dvd1.setNotes("eh it was okay");
        dao.addDvd(dvd1);
        Dvd dvdFromDoa = dao.getDvdById(dvd1.getDvdId());
        assertEquals(dvd1, dvdFromDoa);
        dao.removeDvd(dvd1.getDvdId());
        assertNull(dao.getDvdById(dvd1.getDvdId()));
    }
    /**
     * Test of updateDvd method, of class DvdListDao.
     */
    @Test
    public void testAddUpdateDvd() {
        Dvd dvd1 = new Dvd();
        dvd1.setTitle("bambi");
        dvd1.setDirector("speilburg");
        dvd1.setReleaseYear("1984");
        dvd1.setRating("R");
        dvd1.setNotes("eh it was okay");
        dao.addDvd(dvd1);
        dvd1.setReleaseYear("2002");
        dao.updateDvd(dvd1);
        Dvd checkUpdateDvd = dao.getDvdById(dvd1.getDvdId());
        assertEquals(checkUpdateDvd, dvd1);
    }
    /**
     * Test of getAllDvds method, of class DvdListDao.
     */
    @Test
    public void testGetAllDvds() {
        Dvd dvd1 = new Dvd();
        dvd1.setTitle("bambi");
        dvd1.setDirector("speilburg");
        dvd1.setReleaseYear("1984");
        dvd1.setRating("R");
        dvd1.setNotes("eh it was okay");
        dao.addDvd(dvd1);
        
        Dvd dvd2 = new Dvd();
        dvd2.setTitle("bambi");
        dvd2.setDirector("speilburg");
        dvd2.setReleaseYear("1984");
        dvd2.setRating("R");
        dvd2.setNotes("eh it was okay");
        dao.addDvd(dvd2);
        
        List<Dvd> checkList = dao.getAllDvds();
        
        assertEquals(2, checkList.size());
    }

    /**
     * Test of searchDvds method, of class DvdListDao.
      */
    @Test
    public void testSearchDvds() {
        Dvd dvd1 = new Dvd();
        dvd1.setTitle("bambi");
        dvd1.setDirector("speilburg");
        dvd1.setReleaseYear("1984");
        dvd1.setRating("R");
        dvd1.setNotes("eh it was okay");
        dao.addDvd(dvd1);
        
        Dvd dvd2 = new Dvd();
        dvd2.setTitle("fight club");
        dvd2.setDirector("speilburg");
        dvd2.setReleaseYear("1984");
        dvd2.setRating("G");
        dvd2.setNotes("eh it was okay");
        dao.addDvd(dvd2);
        
        Dvd dvd3 = new Dvd();
        dvd3.setTitle("rain man");
        dvd3.setDirector("speilburg");
        dvd3.setReleaseYear("1984");
        dvd3.setRating("PG");
        dvd3.setNotes("eh it was okay");
        dao.addDvd(dvd3);
        
        Dvd dvd4 = new Dvd();
        dvd4.setTitle("godzilla");
        dvd4.setDirector("speilburg");
        dvd4.setReleaseYear("2015");
        dvd4.setRating("R");
        dvd4.setNotes("eh it was okay");
        dao.addDvd(dvd4);
        
        Dvd dvd5 = new Dvd();
        dvd5.setTitle("star wars");
        dvd5.setDirector("speilburg");
        dvd5.setReleaseYear("2000");
        dvd5.setRating("R");
        dvd5.setNotes("eh it was okay");
        dao.addDvd(dvd5);
        
        List<Dvd> matches = dao.searchDvds(SearchTerms.TITLE, "bambi");
        
        assertEquals(1, matches.size());
        
        matches = dao.searchDvds(SearchTerms.RATING, "R");
        
        assertEquals(3, matches.size());
        
        matches = dao.searchDvds(SearchTerms.RATING, "test");
        
        assertEquals(0, matches.size());
        
        matches = dao.searchDvds(SearchTerms.DIRECTOR, "speilburg");
        
        assertEquals(5, matches.size());
    }
    
     /**
     * Test of searchDvds method, of class DvdListDao.
      */
    @Test
    public void testDeleteDvds() {
        Dvd dvd1 = new Dvd();
        dvd1.setTitle("bambi");
        dvd1.setDirector("speilburg");
        dvd1.setReleaseYear("1984");
        dvd1.setRating("R");
        dvd1.setNotes("eh it was okay");
        dao.addDvd(dvd1);
        
        Dvd dvd2 = new Dvd();
        dvd2.setTitle("fight club");
        dvd2.setDirector("speilburg");
        dvd2.setReleaseYear("1984");
        dvd2.setRating("G");
        dvd2.setNotes("eh it was okay");
        dao.addDvd(dvd2);
        
        Dvd dvd3 = new Dvd();
        dvd3.setTitle("rain man");
        dvd3.setDirector("speilburg");
        dvd3.setReleaseYear("1984");
        dvd3.setRating("PG");
        dvd3.setNotes("eh it was okay");
        dao.addDvd(dvd3);
        
        Dvd dvd4 = new Dvd();
        dvd4.setTitle("godzilla");
        dvd4.setDirector("speilburg");
        dvd4.setReleaseYear("2015");
        dvd4.setRating("R");
        dvd4.setNotes("eh it was okay");
        dao.addDvd(dvd4);
        
        Dvd dvd5 = new Dvd();
        dvd5.setTitle("star wars");
        dvd5.setDirector("speilburg");
        dvd5.setReleaseYear("2000");
        dvd5.setRating("R");
        dvd5.setNotes("eh it was okay");
        dao.addDvd(dvd5);
        
        List<Dvd> check = dao.getAllDvds();
        
        assertEquals(5, check.size());
        
        for(Dvd dvd : check){
            dao.removeDvd(dvd.getDvdId());
        }
        
        assertEquals(0, dao.getAllDvds().size());
    }
    
    
}
