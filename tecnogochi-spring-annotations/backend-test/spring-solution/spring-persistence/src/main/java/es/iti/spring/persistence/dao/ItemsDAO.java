/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.spring.persistence.dao;

import es.iti.spring.model.entities.Items;

/**
 *
 * @author fjbarrena
 */
public class ItemsDAO extends GenericDAO<Items, Integer> {

    @Override
    public void init() {
    }

    @Override
    public boolean exists(Items object) {
        return true;
    }

    @Override
    public void initMetamodelQueries() {
    }

    @Override
    public Items getByPK(Integer primaryKey) {
        return null;
    }
    
}
