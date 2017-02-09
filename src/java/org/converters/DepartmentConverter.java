
package org.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.pojos.Departments;
import org.utils.DepartmentsUtils;

/**
 *
 * @author islam
 */
@FacesConverter(value = "deptConverter")
public class DepartmentConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return DepartmentsUtils.get(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Departments) value).getId());
    }
    
}
