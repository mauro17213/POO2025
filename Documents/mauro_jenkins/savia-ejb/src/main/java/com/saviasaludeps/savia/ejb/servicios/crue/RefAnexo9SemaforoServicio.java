/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9Semaforo;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9Semaforos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9SemaforoRemoto;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import javax.persistence.NoResultException;

/**
 *
 * @author Jaime Andres Olarte
 */


@Stateless
@Remote(RefAnexo9SemaforoRemoto.class)
@Local(RefAnexo9SemaforoLocal.class)
public class RefAnexo9SemaforoServicio extends GenericoServicio implements RefAnexo9SemaforoRemoto,RefAnexo9SemaforoLocal  {

    @Override
    public List<RefAnexo9Semaforo> consularTodos() throws Exception {
        List<RefAnexo9Semaforo> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT r FROM RefAnexo9Semaforos r ORDER BY r.tiempo ";
                    
            List<RefAnexo9Semaforos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            
            for (RefAnexo9Semaforos per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }
    
    public static RefAnexo9Semaforo castEntidadNegocio(RefAnexo9Semaforos ent) {
        RefAnexo9Semaforo obj = new RefAnexo9Semaforo();
        obj.setColor(ent.getColor());
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setTiempo(ent.getTiempo());
        return obj;
    }
    
    public static RefAnexo9Semaforos castNegocioEntidad(RefAnexo9Semaforo obj) {
        RefAnexo9Semaforos ent = new RefAnexo9Semaforos();
        ent.setColor(obj.getColor());
        ent.setId(obj.getId());
        ent.setNombre(obj.getNombre());
        obj.setTiempo(obj.getTiempo());
        return ent;
    }
    
}
