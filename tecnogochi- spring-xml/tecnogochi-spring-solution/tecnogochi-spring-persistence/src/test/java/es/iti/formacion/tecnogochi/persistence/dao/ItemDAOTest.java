///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package es.iti.formacion.tecnogochi.persistence.dao;
//
//import es.iti.formacion.tecnogochi.model.Item;
//import es.iti.formacion.tecnogochi.model.ItemType;
//import es.iti.formacion.tecnogochi.persistence.dao.ItemDAO;
//import es.iti.formacion.tecnogochi.persistence.dao.ItemTypeDAO;
//
//import java.util.List;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
///**
// *
// * @author fjbarrena
// */
//public class ItemDAOTest {
//    private static ItemDAO itemdao;
//    private static ItemTypeDAO itemtypedao;
//    
//    @BeforeClass
//    public static void setUpClass() {
//    	itemdao = new ItemDAO();
//        itemtypedao = new ItemTypeDAO();
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//    
//    /**
//     * Test of findByItemTypeName method, of class ItemDAO.
//     */
//    @Test
//    public void findByItemTypeName() {
//    	ItemType type = itemtypedao.findByName("COMER");
//        
//        if(type != null) {
//	        List<Item> items = itemdao.findByItemType(type);
//	        assertNotNull(items);
//	        
//	        for(Item i : items) {
//	            System.out.println(i.getName() + "-" + i.getEffect());
//	        }
//	        
//        } else {
//        	fail("Empty Database");
//        }
//    }
//    
//}
