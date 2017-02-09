
package org.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.pojos.Cities;
import org.utils.CitiesUtils;

/**
 *
 * @author islam
 */
@FacesConverter(value = "cityConverter")
public class CityConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return CitiesUtils.get(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Cities) value).getId());
    }
  
}