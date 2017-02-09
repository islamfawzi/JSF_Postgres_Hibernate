
package org.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.pojos.Cities;
import org.pojos.Degrees;
import org.pojos.Positions;
import org.utils.CitiesUtils;
import org.utils.DegreesUtils;
import org.utils.PositionsUtils;

/**
 *
 * @author islam
 */
@FacesConverter(value = "degreeConverter")
public class DegreeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return DegreesUtils.get(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Degrees) value).getId());
    }
  
}