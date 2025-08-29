/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatPantalla;
import com.saviasaludeps.savia.ejb.entidades.GatPantallas;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatPantallaRemoto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author stive
 */
@Stateless
@Remote(GatPantallaRemoto.class)
public class GatPantallaServicio extends GenericoServicio implements GatPantallaRemoto {
    
    private static final SimpleDateFormat formatoCorto = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public GatPantalla consultar(int id) throws Exception {
        GatPantalla objRes = null;
        try {
            objRes = castEntidadNegocio((GatPantallas) getEntityManager().find(GatPantallas.class, id));
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
    public int insertar(GatPantalla obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(GatPantalla obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatPantallas a SET ";
            strQuery += "a.gnSedesId.id = :gnSedesId ,";
            strQuery += "a.idSesion = :idSesion ,";
            strQuery += "a.cuenta = :cuenta ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.maeTipoServicioId = :maeTipoServicioId ,";
            strQuery += "a.maeTipoServicioCodigo = :maeTipoServicioCodigo ,";
            strQuery += "a.maeTipoServicioValor = :maeTipoServicioValor ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnSedesId", obj.getGnSedeId().getId());
            query.setParameter("idSesion", obj.getIdSesion());
            query.setParameter("cuenta", obj.isCuenta());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("maeTipoServicioId", obj.getMaeTipoServicioId());
            query.setParameter("maeTipoServicioCodigo", obj.getMaeTipoServicioCodigo());
            query.setParameter("maeTipoServicioValor", obj.getMaeTipoServicioValor());
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
    public GatPantalla eliminar(int id) throws Exception {
        GatPantalla obj = null;
        try {
            GatPantallas ent = getEntityManager().find(GatPantallas.class, id);
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
    public GatPantalla consultaExiste(int idSede, String idSesion) throws Exception {
        GatPantalla obj = new GatPantalla();
        try {
            String strQuery = "FROM GatPantallas c "
                    + " WHERE c.gnSedesId.id = "+idSede
                    + " AND c.idSesion = '"+idSesion+"'"
                    + " AND c.activo = 1 "
                    + " AND c.fechaHoraCrea >= '"+formatoCorto.format(new Date())+" 00:00:00' "
                    + " ORDER BY c.id DESC";
            List<GatPantallas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GatPantallas pantalla : list) {
                    obj = castEntidadNegocio(pantalla);
                    break;
                }
            }            
        } catch (NoResultException e) {
            obj = new GatPantalla();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    @Override
    public GatPantalla consultaInactiva(int idSede) throws Exception {
        GatPantalla obj = new GatPantalla();
        try {
            String strQuery = "FROM GatPantallas c "
                    + " WHERE c.gnSedesId.id = "+idSede
                    + " AND c.activo = 0"
                    + " AND c.fechaHoraCrea <= '"+formatoCorto.format(new Date())+" 00:00:00' "
                    + " ORDER BY c.id DESC";
            List<GatPantallas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GatPantallas pantalla : list) {
                    obj = castEntidadNegocio(pantalla);
                    break;
                }
            }            
        } catch (NoResultException e) {
            obj = new GatPantalla();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    private GatPantalla castEntidadNegocio(GatPantallas entidad) {
        GatPantalla negocio = new GatPantalla();
        negocio.setId(entidad.getId());
        negocio.setGnSedeId(new GnSede(entidad.getGnSedesId().getId()));
        negocio.setIdSesion(entidad.getIdSesion());
        negocio.setCuenta(entidad.getCuenta());
        negocio.setActivo(entidad.getActivo());
        negocio.setMaeTipoServicioId(entidad.getMaeTipoServicioId());
        negocio.setMaeTipoServicioCodigo(entidad.getMaeTipoServicioCodigo());
        negocio.setMaeTipoServicioValor(entidad.getMaeTipoServicioValor());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private GatPantallas castNegocioEntidad(GatPantalla negocio) {
        GatPantallas entidad = new GatPantallas();
        entidad.setId(negocio.getId());
        entidad.setGnSedesId(new GnSedes(negocio.getGnSedeId().getId()));
        entidad.setIdSesion(negocio.getIdSesion());
        entidad.setCuenta(negocio.isCuenta());
        entidad.setActivo(negocio.isActivo());
        entidad.setMaeTipoServicioId(negocio.getMaeTipoServicioId());
        entidad.setMaeTipoServicioCodigo(negocio.getMaeTipoServicioCodigo());
        entidad.setMaeTipoServicioValor(negocio.getMaeTipoServicioValor());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        return entidad;
    }

    @Override
    public boolean existeCuenta(int idSede, Integer maeTipoServicioId) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM GatPantallas c "
                    + " WHERE c.gnSedesId.id = "+idSede
                    + " AND c.activo = 1";
            if (maeTipoServicioId == null) {
                strQuery += " AND c.maeTipoServicioId = "+ maeTipoServicioId;
            } else {
                strQuery += " AND (c.maeTipoServicioId =  NULL OR c.maeTipoServicioId = "+maeTipoServicioId+")";
            }
            strQuery +=  " AND c.fechaHoraCrea >= '"+formatoCorto.format(new Date())+" 00:00:00' "
                    + " ORDER BY c.id DESC";
            List<GatPantallas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null && list.size() > 0) {
                existe = true;
            }            
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public void inactivarPantallas(int idSede) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GatPantallas a SET ";
            strQuery += "a.cuenta = 0,";
            strQuery += "a.activo = 0 ";
            strQuery += " WHERE a.gnSedesId.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("id", idSede);
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
    public GatPantalla consultaExiste(String idSesion) throws Exception {
        GatPantalla obj = new GatPantalla();
        try {
            String strQuery = "FROM GatPantallas c "
                    + " WHERE c.idSesion = '"+idSesion+"'"
                    + " AND c.activo = 1 "
                    + " AND c.fechaHoraCrea >= '"+formatoCorto.format(new Date())+" 00:00:00' "
                    + " ORDER BY c.id DESC";
            List<GatPantallas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null) {
                for (GatPantallas pantalla : list) {
                    obj = castEntidadNegocio(pantalla);
                    break;
                }
            }            
        } catch (NoResultException e) {
            obj = new GatPantalla();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }
    
}
