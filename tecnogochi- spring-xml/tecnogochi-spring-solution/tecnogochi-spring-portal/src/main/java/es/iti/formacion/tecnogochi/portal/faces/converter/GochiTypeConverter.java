package es.iti.formacion.tecnogochi.portal.faces.converter;

import es.iti.formacion.tecnogochi.model.GochiType;
import es.iti.tecnogochi.core.service.GochiTypeService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.math.NumberUtils;


/**
 * @author Salvador Morera i Soler
 */
@FacesConverter("gochiTypeConverter")
public class GochiTypeConverter implements Converter {



    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }
        if (!NumberUtils.isNumber(submittedValue)) {
            throw new ConverterException(submittedValue + " is not a valid ID for gochiType.");
        }
        try {
            return GochiTypeService.getInstance().getById(Integer.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid Gochi ID", submittedValue)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }
        if (modelValue instanceof GochiType) {
            return String.valueOf(((GochiType) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid Gochi", modelValue)));
        }
    }

}
