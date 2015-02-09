/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessions;

import com.middleware.model.User;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
@LocalBean
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {
    @PersistenceContext(unitName = "com.middleware_middleware-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    @Override
    public void create(User user){
        em.persist(user);
    }
    
    public User find(String username,String password){
        Query query=em.createNamedQuery("User.findByUsernameAndPassword").setParameter("username", username)
                .setParameter("password", password);
        List<User> users=query.getResultList();
        if(users.isEmpty())return null;
        return (User) users.get(0);
    }

}
