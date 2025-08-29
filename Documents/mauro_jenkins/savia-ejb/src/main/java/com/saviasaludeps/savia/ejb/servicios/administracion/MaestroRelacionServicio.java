/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroRelacion;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroRelaciones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.MaestroRelacionRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(MaestroRelacionRemoto.class)
public class MaestroRelacionServicio extends GenericoServicio implements MaestroRelacionRemoto {
    
    @Override
    public int insertar(MaestroRelacion obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación  maestrostipo ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public List<MaestroRelacion> consultarPadresPorIdMaestroHijo(int id) throws java.lang.Exception {
    List<MaestroRelacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnMaestroRelaciones m "
                    + "WHERE m.gnMaestrosIdHijo.id =  "+id
                    + " ORDER BY m.id";
            List< GnMaestroRelaciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnMaestroRelaciones per : list) {
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

    @Override
    public MaestroRelacion eliminar(int id) throws java.lang.Exception {
        MaestroRelacion obj = null;
        try {
            GnMaestroRelaciones ent = getEntityManager().find(GnMaestroRelaciones.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    public static MaestroRelacion castEntidadNegocio(GnMaestroRelaciones per) {
        MaestroRelacion obj = new MaestroRelacion();
        obj.setId(per.getId());
        obj.setMaestroPadre(new Maestro(per.getGnMaestrosIdPadre().getId()));
        obj.setMaestroHijo(new Maestro(per.getGnMaestrosIdHijo().getId()));
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static GnMaestroRelaciones castNegocioEntidad(MaestroRelacion obj) {
        GnMaestroRelaciones per = new GnMaestroRelaciones();
        
        per.setId(obj.getId());
        
        if (obj.getMaestroPadre() != null && obj.getMaestroPadre().getTipo() != null) {
            per.setGnMaestrosIdPadre(new GnMaestros(obj.getMaestroPadre().getId()));
        }
        if (obj.getMaestroHijo() != null && obj.getMaestroHijo().getTipo() != null) {
            per.setGnMaestrosIdHijo(new GnMaestros(obj.getMaestroHijo().getId()));
        }
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        
        return per;
    }

}
