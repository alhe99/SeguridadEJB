package com.seguridad.dao;

import java.sql.Connection;
import javax.ejb.Stateless;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// @Stateless
public class DAOBase {
    @PersistenceContext(name = "JpaMappingPU")
    private EntityManager entityManager;

    public DAOBase() {
    }    

    public void save(Object transientInstance) throws RuntimeException {
        getEntityManager().persist(transientInstance);
    }

    public Object merge(Object detachedInstance) throws RuntimeException {
        Object result = getEntityManager().merge(
                detachedInstance);
        return result;
    }

    public void remove(Object persistentInstance) throws RuntimeException {
        getEntityManager().remove(getEntityManager().contains(persistentInstance) ? persistentInstance : getEntityManager().merge(persistentInstance));
    }

    public Connection getJdbcConnection() throws RuntimeException {
        Connection connection = getEntityManager().unwrap(java.sql.Connection.class);
        return connection;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
