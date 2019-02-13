package es.iti.formacion.tecnogochi.portal.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Salvador Morera i Soler
 */
@FacesConverter("myConverter")
public class MyConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("MyConverter getAsObject: " + value);
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("MyConverter getAsString: " + value);
        return (String) value;
    }

}
