package com.seguridad.dao;

import com.seguridad.modelo.SecRolRoles;
import com.seguridad.modelo.SecRouRolesUsuarios;
import com.seguridad.modelo.SecUsrUsuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class SecRouRolesUsuariosDAO extends DAOBase {

    public SecRouRolesUsuariosDAO() {
    }

    public SecRouRolesUsuarios findByUsuarioAndSistema(SecUsrUsuarios usr, SecRolRoles rol) throws RuntimeException {
        SecRouRolesUsuarios rou;

        String queryString = "SELECT rol FROM SecRouRolesUsuarios as rol where rol.secUsrUsuarios.usrCodigo = :usrCodigo and rol.secRolRoles.rolId = :rolId ";
        Query queryObject = getEntityManager().createQuery(queryString);
        queryObject.setParameter("rolId", rol.getRolId());
        queryObject.setParameter("usrCodigo", usr.getUsrCodigo());
        rou = (SecRouRolesUsuarios) queryObject.getSingleResult();

        return rou;
    }
    
    public List<SecRouRolesUsuarios> findByUsuarioAndSistema(String usrId, Integer sisId) throws RuntimeException{
       String queryString = "SELECT rou "
               + "FROM SecRouRolesUsuarios as rou "
               + "WHERE rou.secUsrUsuarios.usrCodigo = :usrId and rou.secRolRoles.secSisSistemas.sisId = :sisId "; 
       
        Query queryObject = getEntityManager().createQuery(queryString);
        queryObject.setParameter("usrId", usrId);
        queryObject.setParameter("sisId", sisId);       
        
        return queryObject.getResultList();
    }
    
      public void deleteRolesOfUser(String usuarioCod, int sistemaId) throws RuntimeException{
       String queryString = "DELETE FROM SecRouRolesUsuarios rou "
               + "WHERE rou.secUsrUsuarios.usrCodigo = :usrId and rou.secRolRoles.secSisSistemas.sisId = :sisId "; 
       
        Query queryObject = getEntityManager().createQuery(queryString);
        queryObject.setParameter("usrId", usuarioCod);
        queryObject.setParameter("sisId", sistemaId);       
        queryObject.executeUpdate();
 
    }

}
