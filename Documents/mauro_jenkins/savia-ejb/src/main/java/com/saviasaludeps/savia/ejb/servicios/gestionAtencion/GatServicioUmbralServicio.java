/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatServicioUmbral;
import com.saviasaludeps.savia.ejb.entidades.GatServicioUmbrales;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatServicioUmbralRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(GatServicioUmbralRemoto.class)
public class GatServicioUmbralServicio  extends GenericoServicio implements GatServicioUmbralRemoto {
    
    @Override
    public GatServicioUmbral consultar(int id) throws Exception {
        GatServicioUmbral objRes = null;
        try {
            objRes = castEntidadNegocio((GatServicioUmbrales) getEntityManager().find(GatServicioUmbrales.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    @Override
    public int insertar(GatServicioUmbral obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public GatServicioUmbral eliminar(int id) throws Exception {
        GatServicioUmbral obj = null;
        try {
            GatServicioUmbrales ent = getEntityManager().find(GatServicioUmbrales.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public void actualizar(GatServicioUmbral obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatServicioUmbrales a SET ";
            strQuery += "a.maeTipoServicioId = :maeTipoServicioId ,";
            strQuery += "a.maeTipoServicioCodigo = :maeTipoServicioCodigo ,";
            strQuery += "a.maeTipoServicioValor = :maeTipoServicioValor ,";
            strQuery += "a.tiempo = :tiempo ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("maeTipoServicioId", obj.getMaeTipoServicioId());
            query.setParameter("maeTipoServicioCodigo", obj.getMaeTipoServicioCodigo());
            query.setParameter("maeTipoServicioValor", obj.getMaeTipoServicioValor());
            query.setParameter("tiempo", obj.getTiempo());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica()); 
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<GatServicioUmbral> listarTodos() throws Exception {
        List<GatServicioUmbral> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatServicioUmbrales c "
                    + " ORDER BY c.id DESC";
            List<GatServicioUmbrales> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatServicioUmbrales umbral : list) {
                listaResultado.add(castEntidadNegocio(umbral));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }
    
    @Override
    public List<GatServicioUmbral> listarTipo(int tipo) throws java.lang.Exception {
        List<GatServicioUmbral> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GatServicioUmbrales c where tipo = " + tipo
                    + " ORDER BY c.id DESC";
            List<GatServicioUmbrales> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GatServicioUmbrales umbral : list) {
                listaResultado.add(castEntidadNegocio(umbral));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }
    
    private GatServicioUmbrales castNegocioEntidad(GatServicioUmbral negocio) {
        GatServicioUmbrales entidad = new GatServicioUmbrales();
        entidad.setMaeTipoServicioId(negocio.getMaeTipoServicioId());
        entidad.setMaeTipoServicioCodigo(negocio.getMaeTipoServicioCodigo());
        entidad.setMaeTipoServicioValor(negocio.getMaeTipoServicioValor());
        entidad.setTiempo(negocio.getTiempo());
        entidad.setTipo(negocio.getTipo());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }
    
    private GatServicioUmbral castEntidadNegocio(GatServicioUmbrales entidad) {
        GatServicioUmbral negocio = new GatServicioUmbral();
        negocio.setId(entidad.getId());
        negocio.setMaeTipoServicioId(entidad.getMaeTipoServicioId());
        negocio.setMaeTipoServicioCodigo(entidad.getMaeTipoServicioCodigo());
        negocio.setMaeTipoServicioValor(entidad.getMaeTipoServicioValor());
        negocio.setTiempo(entidad.getTiempo());
        negocio.setTipo(entidad.getTipo());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }

}
