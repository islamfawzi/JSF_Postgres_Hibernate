
package org.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.pojos.MilitaryStatus;
import org.utils.MilitaryUtils;

/**
 *
 * @author islam
 */
@FacesConverter(value = "militaryConverter")
public class MilitaryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return MilitaryUtils.get(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((MilitaryStatus) value).getId());
    }
  
}