/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipoRelacion;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroTipoRelaciones;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroTipos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.MaestroTipoRelacionRemoto;
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
@Remote(MaestroTipoRelacionRemoto.class)
public class MaestroTipoRelacionServicio extends GenericoServicio implements MaestroTipoRelacionRemoto {
    
    @Override
    public int insertar(MaestroTipoRelacion obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public List<MaestroTipoRelacion> consultarPadresPorTipoHijo(String tipo) throws java.lang.Exception {
        List<MaestroTipoRelacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnMaestroTipoRelaciones m "
                    + "WHERE m.gnMaestroTiposTipoHijo.tipo =  "+Integer.parseInt(tipo)
                    + " ORDER BY m.id";
            List< GnMaestroTipoRelaciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnMaestroTipoRelaciones per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            //Exception(CONSULTAR_TODOS, e);
            listResult = new ArrayList();
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public MaestroTipoRelacion eliminar(int id) throws java.lang.Exception {
        MaestroTipoRelacion obj = null;
        try {
            GnMaestroTipoRelaciones ent = getEntityManager().find(GnMaestroTipoRelaciones.class, id);
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

    public static MaestroTipoRelacion castEntidadNegocio(GnMaestroTipoRelaciones per) {
        MaestroTipoRelacion obj = new MaestroTipoRelacion();
        obj.setId(per.getId());
        obj.setMaestroTipoPadre(new MaestroTipo(per.getGnMaestroTiposTipoPadre().getTipo()));
        obj.setMaestroTipoHijo(new MaestroTipo(per.getGnMaestroTiposTipoHijo().getTipo()));
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        return obj;
    }

    public static GnMaestroTipoRelaciones castNegocioEntidad(MaestroTipoRelacion obj) {
        GnMaestroTipoRelaciones per = new GnMaestroTipoRelaciones();
        
        per.setId(obj.getId());
        
        if (obj.getMaestroTipoPadre() != null && obj.getMaestroTipoPadre().getTipo() != null) {
            per.setGnMaestroTiposTipoPadre(new GnMaestroTipos(obj.getMaestroTipoPadre().getTipo()));
        }
        if (obj.getMaestroTipoHijo() != null && obj.getMaestroTipoHijo().getTipo() != null) {
            per.setGnMaestroTiposTipoHijo(new GnMaestroTipos(obj.getMaestroTipoHijo().getTipo()));
        }
        //Auditoría
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        
        return per;
    }

}
