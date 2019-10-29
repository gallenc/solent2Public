/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com600.example.journeyplanner.jpadao;

import java.util.Iterator;
import java.util.List;
import org.solent.com600.example.journeyplanner.model.SysUser;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solent.com600.example.journeyplanner.model.Role;
import org.solent.com600.example.journeyplanner.model.SysUserDAO;

/**
 *
 * @author cgallen
 */
public class SysUserDAOJpaImpl implements SysUserDAO {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserDAOJpaImpl.class);

    EntityManager entityManager = null;

    public SysUserDAOJpaImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public SysUser create(SysUser user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        // entityManager.flush() could be used
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM SysUser u WHERE u.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public SysUser retrieve(Long id) {
        SysUser usr = entityManager.find(SysUser.class, id);
        return usr;
    }

    @Override
    public List<SysUser> retrieveAll() {
        TypedQuery<SysUser> q = entityManager.createQuery("select u from SysUser u ORDER BY u.userInfo.surname, u.userInfo.firstname, u.userName ASC", SysUser.class);
        List<SysUser> sysUserList = q.getResultList();
        return sysUserList;
    }

    @Override
    public List<SysUser> retrieveLikeMatching(String surname, String firstname) {
        // see https://stackoverflow.com/questions/21456494/spring-jpa-query-with-like

        String queryString = "select u from SysUser u WHERE UPPER(u.userInfo.surname) LIKE CONCAT('%',UPPER(:surname),'%')"
                + " AND UPPER(u.userInfo.firstname) LIKE CONCAT('%',UPPER(:firstname),'%')  ORDER BY u.userInfo.surname, u.userInfo.firstname, u.userName ASC";

        TypedQuery<SysUser> q = entityManager.createQuery(queryString, SysUser.class);
        q.setParameter("surname", surname);
        q.setParameter("firstname", firstname);
        List<SysUser> sysUserList = q.getResultList();
        return sysUserList;
    }

    @Override
    public SysUser update(SysUser updatedSysUser) {
        entityManager.getTransaction().begin();
        SysUser returnSysUser = entityManager.merge(updatedSysUser);
        entityManager.getTransaction().commit();
        return returnSysUser;
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM SysUser").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public SysUser retrieveByUserName(String username) {
        TypedQuery<SysUser> q = entityManager.createQuery("select u from SysUser u WHERE u.userName=:userName ", SysUser.class);
        q.setParameter("userName", username);
        SysUser sysUser = null;
        try {
            sysUser = q.getSingleResult();
        } catch (NoResultException ex) {
        }
        return sysUser;
    }

    @Override
    public List<SysUser> retrieveAll(List<Role> userRoles) {
        String query = "select u from SysUser u WHERE " + queryForRole(userRoles);
        TypedQuery<SysUser> q = entityManager.createQuery(query, SysUser.class);
        List<SysUser> sysUserList = q.getResultList();
        return sysUserList;
    }

    @Override
    public List<SysUser> retrieveLikeMatching(String surname, String firstname, List<Role> userRoles) {
        String queryString = "select u from SysUser u WHERE UPPER(u.userInfo.surname) LIKE CONCAT('%',UPPER(:surname),'%')"
                + " AND UPPER(u.userInfo.firstname) LIKE CONCAT('%',UPPER(:firstname),'%')"
                + " AND " + queryForRole(userRoles);

        TypedQuery<SysUser> q = entityManager.createQuery(queryString, SysUser.class);
        q.setParameter("surname", surname);
        q.setParameter("firstname", firstname);
        List<SysUser> sysUserList = q.getResultList();
        return sysUserList;
    }

    public String queryForRole(List<Role> userRoles) {
        if (userRoles.isEmpty()) {
            return " (1=1)";
        }
        String query = " (";

        Iterator<Role> it = userRoles.iterator();
        while (it.hasNext()) {
            Role role = it.next();
            // this isnt great but it works
            query = query + "u.role = " + role.getClass().getName() + "." + role.name();
            if (it.hasNext()) {
                query = query + " OR ";
            }
        }
        query = query + " )";
        return query;
    }

}
