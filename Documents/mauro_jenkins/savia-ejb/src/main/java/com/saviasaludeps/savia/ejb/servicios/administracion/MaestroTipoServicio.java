/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnMaestroTipos;
import com.saviasaludeps.savia.ejb.entidades.GnPerfiles;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.MaestroTipoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(MaestroTipoRemoto.class)
public class MaestroTipoServicio extends GenericoServicio implements MaestroTipoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM GnMaestroTipos m "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND m.tipo = '" + (String) e.getValue() + "' ";
                            break;
                        case "maestroTipo.nombre":
                            strQuery += "AND m.gnMaestroTiposTipo.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND m.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "perfil.nombre":
                            strQuery += "AND m.gnPerfilesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND m.activo = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<MaestroTipo> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaestroTipo> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnMaestroTipos m "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND m.tipo = '" + (String) e.getValue() + "' ";
                            break;
                        case "maestroTipo.nombre":
                            strQuery += "AND m.gnMaestroTiposTipo.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND m.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "perfil.nombre":
                            strQuery += "AND m.gnPerfilesId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND m.activo = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "m." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "m.nombre ASC ";
            }
            List< GnMaestroTipos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnMaestroTipos per : list) {
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
    public MaestroTipo consultar(String tipo) throws Exception {
        MaestroTipo objRes = null;
        try {
            objRes = castEntidadNegocio((GnMaestroTipos) getEntityManager().find(GnMaestroTipos.class, tipo));
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
    public void actualizar(MaestroTipo obj) throws Exception {
        try {
//            getEntityManager().merge(castNegocioEntidad(obj));
            String hql = "UPDATE GnMaestroTipos SET "
//                    + "activo = :activo, "
//                    + "gnMaestroTiposTipo.tipo = :gnMaestroTiposTipo, "
                    + "gnPerfilesId.id = :gnPerfilesId, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE tipo = :tipo ";
            Query query = getEntityManager().createQuery(hql);
//            query.setParameter("activo", obj.isActivo());
            if (obj.getPerfil() != null && obj.getPerfil().getId() != null) {
                query.setParameter("gnPerfilesId", obj.getPerfil().getId());
            } else {
                query.setParameter("gnPerfilesId", null);
            }
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("tipo", obj.getTipo());
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
    public List<MaestroTipo> consultarTodos() throws Exception {
        List<MaestroTipo> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnMaestroTipos m "
                    + "ORDER BY m.tipo";
            List< GnMaestroTipos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnMaestroTipos per : list) {
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
    public MaestroTipo consultarPadrePorTipo(String tipo) throws Exception {
        MaestroTipo objRes = null;
        try {
            GnMaestroTipos per = (GnMaestroTipos) getEntityManager().find(GnMaestroTipos.class, tipo);
//            if(per.getGnMaestroTiposTipo() != null){
//                objRes = castEntidadNegocio(per.getGnMaestroTiposTipo());
//            }
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
    public List<MaestroTipo> consultarActivos() throws Exception {
        List<MaestroTipo> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnMaestroTipos m "
                    + "WHERE m.activo = true "
                    + "ORDER BY m.id";
            List< GnMaestroTipos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnMaestroTipos per : list) {
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
    public List<MaestroTipo> consultarActivosPorPerfil(List<Perfil> listaPerfiles) throws Exception {
        List<MaestroTipo> listResult = new ArrayList();
        try {
            String strQuery = "FROM GnMaestroTipos t "
                    + "WHERE t.activo = true ";
            if (listaPerfiles != null) {
                String sqlPerfiles = null;
                for (Perfil per : listaPerfiles) {
                    if (sqlPerfiles == null) {
                        sqlPerfiles = "t.gnPerfilesId.id = " + per.getId() + " ";
                    } else {
                        sqlPerfiles += "OR t.gnPerfilesId.id = " + per.getId() + " ";
                    }
                }
                if (sqlPerfiles == null) {
                    strQuery += "AND false ";
                } else {
                    strQuery += "AND " + "(" + sqlPerfiles + ") ";
                }
            }
            strQuery += "ORDER BY t.nombre";
            List<GnMaestroTipos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnMaestroTipos per : list) {
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

    public static MaestroTipo castEntidadNegocio(GnMaestroTipos per) {
        MaestroTipo obj = new MaestroTipo();
        obj.setTipo(per.getTipo());
        obj.setNombre(per.getNombre());
        
        if (per.getGnPerfilesId() != null) {
            obj.setPerfil(new Perfil(
                    per.getGnPerfilesId().getId(),
                    per.getGnPerfilesId().getNombre(),
                    per.getGnPerfilesId().getDescripcion(),
                    per.getGnPerfilesId().getActivo()
            ));
        }
        obj.setActivo(per.getActivo());
        //Auditoría;
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }

    public static GnMaestroTipos castNegocioEntidad(MaestroTipo obj) {
        GnMaestroTipos per = new GnMaestroTipos();
        per.setTipo(obj.getTipo());
        per.setNombre(obj.getNombre());
        
        if (obj.getPerfil() != null && obj.getPerfil().getId() != null) {
            per.setGnPerfilesId(new GnPerfiles(obj.getPerfil().getId()));
        }
        per.setActivo(obj.isActivo());
        //Auditoría
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

}
