
package org.helpers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.pojos.Orgs;
import org.pojos.Users;

/**
 *
 * @author islam
 */
@ManagedBean
@SessionScoped
public class UserSession implements Serializable{

    private Users user;
    private Orgs org;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Orgs getOrg() {
        return org;
    }

    public void setOrg(Orgs org) {
        this.org = org;
    }
}
