/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.persistence.enums;

/**
 * @author Francisco Javier Barrena (twitter: @DogDeveloper)
 */
public enum ConfigurationEnum {
    PERSISTENCE_UNIT_NAME("tecnogochi-persistence");
    
    private final String value;
    
    ConfigurationEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
