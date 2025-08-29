package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsValidacion;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsValidaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeRipsValidacionRemoto;

@Stateless
@Remote(CmFeRipsValidacionRemoto.class)
public class CmFeRipsValidacionServicio extends GenericoServicio implements CmFeRipsValidacionRemoto {

    @Override
    public CmFeRipsValidacion eliminar(int id) throws java.lang.Exception {
        CmFeRipsValidacion obj = null;
        try {
            CmFeRipsValidaciones ent = getEntityManager().find(CmFeRipsValidaciones.class, id);
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

    @Override
    public CmFeRipsValidacion consultar(int id) throws java.lang.Exception {
        CmFeRipsValidacion objRes = null;
        try {
            String hql = "SELECT c FROM CmFeRipsValidaciones c "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            CmFeRipsValidaciones validacion = (CmFeRipsValidaciones) query.getSingleResult();
            objRes = castEntidadNegocio(validacion);
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
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(v.id) FROM CmFeRipsValidaciones v"
                    + " WHERE v.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "estado":
                            strQuery += "AND v.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "idValidacion":
                            strQuery += "AND v.idValidacion = " + e.getValue() + " ";
                            break;
                    }
                }
            }

            //RANGO DE FECHA CARGA
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND v.fechaHoraInicio >= :fh_inicio AND v.fechaHoraFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                strQuery += "AND v.fechaHoraInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                strQuery += "AND v.fechaHoraFin <= :fh_fin ";
            }
            Query query = getEntityManager().createQuery(strQuery);

            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            cant = (int) (long) query
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<CmFeRipsValidacion> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmFeRipsValidacion> listaValidaciones = new ArrayList();
        try {
            String strQuery = "FROM CmFeRipsValidaciones v"
                    + " WHERE v.id > 0 ";
 
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "estado":
                            strQuery += "AND v.estado = '" + (String) e.getValue() + "' ";
                            break;
                        case "idValidacion":
                            strQuery += "AND v.idValidacion = " + e.getValue() + " ";
                            break;
                    }
                }
            }

            //RANGO DE FECHA CARGA
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND v.fechaHoraInicio >= :fh_inicio AND v.fechaHoraFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                strQuery += "AND v.fechaHoraInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                strQuery += "AND v.fechaHoraFin <= :fh_fin ";
            }
            strQuery += " ORDER BY v.id desc";
            Query query = getEntityManager().createQuery(strQuery);
         
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            
              List<CmFeRipsValidaciones> listCargas = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            if (listCargas != null) {
                for (CmFeRipsValidaciones cargaEntidad : listCargas) {
                 CmFeRipsValidacion cmFeRipsValidacion = castEntidadNegocio(cargaEntidad);
                 listaValidaciones.add(cmFeRipsValidacion);
                }
            }
        } catch (NoResultException e) {
            listaValidaciones = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaValidaciones;
    }
    

    @Override
    public int insertar(CmFeRipsValidacion obj) throws java.lang.Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(CmFeRipsValidacion obj) throws java.lang.Exception {
         try {
            Query query;
            String hql = "UPDATE CmFeRipsValidaciones SET "
                    + " nombreValidacion = :nombreValidacion ,"
                    + " descripcion = :descripcion ,"
                    + " estado = :estado ,"
                    + " usuarioModifica = :usuarioModifica ,"
                    + " terminalModifica = :terminalModifica ,"
                    + " fechaHoraModifica = :fechaHoraModifica "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("nombreValidacion", obj.getNombreValidacion());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
            //Actualizar roles
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
   
    public static CmFeRipsValidacion castEntidadNegocio(CmFeRipsValidaciones ent) {
        CmFeRipsValidacion neg = new CmFeRipsValidacion();
        neg.setId(ent.getId());
        neg.setNombreValidacion(ent.getNombreValidacion());
        neg.setDescripcion(ent.getDescripcion());
        neg.setEstado(ent.getEstado());
        neg.setIdValidacion(ent.getIdValidacion());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        return neg;
    }
    public static CmFeRipsValidaciones castNegocioEntidad(CmFeRipsValidacion neg) {
        CmFeRipsValidaciones ent = new CmFeRipsValidaciones();
        if (neg.getId() != null) {
            ent.setId(neg.getId());
        }
        ent.setNombreValidacion(neg.getNombreValidacion());
        ent.setDescripcion(neg.getDescripcion());
        ent.setEstado(neg.getEstado());
        ent.setIdValidacion(neg.getIdValidacion());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }
    
}
