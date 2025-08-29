/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2Semaforo;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo2Semaforos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2SemaforoRemoto;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import javax.persistence.NoResultException;

/**
 *
 * @author AlexanderDiaz
 */


@Stateless
@Remote(AuAnexo2SemaforoRemoto.class)
@Local(AuAnexo2SemaforoLocal.class)
public class AuAnexo2SemaforoServicio extends GenericoServicio implements AuAnexo2SemaforoRemoto,AuAnexo2SemaforoLocal  {

    @Override
    public List<AuAnexo2Semaforo> consularTodos() throws Exception {
        List<AuAnexo2Semaforo> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT r FROM AuAnexo2Semaforos r ORDER BY r.tiempo ";
                    
            List<AuAnexo2Semaforos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            
            for (AuAnexo2Semaforos per : list) {
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
    
    public static AuAnexo2Semaforo castEntidadNegocio(AuAnexo2Semaforos ent) {
        AuAnexo2Semaforo obj = new AuAnexo2Semaforo();
        obj.setColor(ent.getColor());
        obj.setId(ent.getId());
        obj.setNombre(ent.getNombre());
        obj.setTiempo(ent.getTiempo());
        return obj;
    }
    
    public static AuAnexo2Semaforos castNegocioEntidad(AuAnexo2Semaforo obj) {
        AuAnexo2Semaforos ent = new AuAnexo2Semaforos();
        ent.setColor(obj.getColor());
        ent.setId(obj.getId());
        ent.setNombre(obj.getNombre());
        obj.setTiempo(obj.getTiempo());
        return ent;
    }
    
}
