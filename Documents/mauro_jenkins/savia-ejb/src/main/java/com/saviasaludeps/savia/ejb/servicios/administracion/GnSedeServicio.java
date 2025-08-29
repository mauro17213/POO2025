/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.administracion.GnSedeRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author acuartas
 */
@Stateless
@Remote(GnSedeRemoto.class)
public class GnSedeServicio extends GenericoServicio implements GnSedeRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GnSedes c "
                    + " WHERE 1 = 1 ";
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "gnEmpresaId.razonSocial":
                            strQuery += "AND c.gnEmpresasId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUbicacionId.nombre":
                            strQuery += "AND c.gnUbicacionesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo  = " + (String) e.getValue() + " ";
                            break;
                        case "maeTipoId":
                            agregarFiltro = false;
                            strQuery += "AND c.maeTipoId = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            if (agregarFiltro && paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.maeTipoId IN (" + paramConsulta.getParametroConsulta1() + ") ";
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
    public int consultarCantidadListaActiva(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM GnSedes c "
                    + " WHERE c.id >= 1 AND c.activo  = 1";
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "gnEmpresaId.razonSocial":
                            strQuery += "AND c.gnEmpresasId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUbicacionId.nombre":
                            strQuery += "AND c.gnUbicacionesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;                   
                        case "maeTipoId":
                            agregarFiltro = false;
                            strQuery += "AND c.maeTipoId = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            if (agregarFiltro && paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.maeTipoId IN (" + paramConsulta.getParametroConsulta1() + ") ";
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
    public List<GnSede> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<GnSede> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnSedes c WHERE 1 = 1 ";
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "gnEmpresaId.razonSocial":
                            strQuery += "AND c.gnEmpresasId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUbicacionId.nombre":
                            strQuery += "AND c.gnUbicacionesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo  = " + (String) e.getValue() + " ";
                            break;
                        case "maeTipoId":
                            agregarFiltro = false;
                            strQuery += "AND c.maeTipoId = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            if (agregarFiltro && paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.maeTipoId IN (" + paramConsulta.getParametroConsulta1() + ") ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            List<GnSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnSedes sedes : list) {
                listaResultados.add(castEntidadNegocio(sedes));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
        
    }
    
    
    @Override
    public List<GnSede> consultarListaActiva(ParamConsulta paramConsulta) throws Exception {
        List<GnSede> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM GnSedes c WHERE c.id >= 1 AND c.activo  = 1";
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "gnEmpresaId.razonSocial":
                            strQuery += "AND c.gnEmpresasId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "gnUbicacionId.nombre":
                            strQuery += "AND c.gnUbicacionesId.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        
                        case "maeTipoId":
                            agregarFiltro = false;
                            strQuery += "AND c.maeTipoId = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            if (agregarFiltro && paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.maeTipoId IN (" + paramConsulta.getParametroConsulta1() + ") ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            List<GnSedes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (GnSedes sedes : list) {
                listaResultados.add(castEntidadNegocio(sedes));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
        
    }

    @Override
    public GnSede consultar(int id) throws Exception {
        GnSede objRes = null;
        try {
            
            objRes = castEntidadNegocio((GnSedes) getEntityManager().find(GnSedes.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            objRes = null;
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(GnSede obj) throws Exception {
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
    public void actualizar(GnSede obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GnSedes a SET ";
            strQuery += "a.gnEmpresasId.id = :gnEmpresasId ,";
            strQuery += "a.gnUbicacionesId.id = :gnUbicacionesId ,";
            strQuery += "a.nombre = :nombre ,";
            strQuery += "a.descripcion = :descripcion ,";
            strQuery += "a.direccion = :direccion ,";
            strQuery += "a.telefono = :telefono ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.maeTipoId = :maeTipoId ,";
            strQuery += "a.maeTipoValor = :maeTipoValor ,";
            strQuery += "a.maeTipoCodigo = :maeTipoCodigo ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("gnEmpresasId", obj.getGnEmpresaId().getId());
            query.setParameter("gnUbicacionesId", obj.getGnUbicacionId().getId());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("direccion", obj.getDireccion());
            query.setParameter("telefono", obj.getTelefono());
            query.setParameter("maeTipoId", obj.getMaeTipoId());
            query.setParameter("maeTipoCodigo", obj.getMaeTipoCodigo());
            query.setParameter("maeTipoValor", obj.getMaeTipoValor());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
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
    public GnSede eliminar(int id) throws Exception {
        GnSede obj = null;
        try {
            GnSedes ent = getEntityManager().find(GnSedes.class, id);
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
    
    private GnSede castEntidadNegocio(GnSedes entidad) {
        GnSede negocio = new GnSede();
        negocio.setId(entidad.getId());
        negocio.setGnEmpresaId(new Empresa(entidad.getGnEmpresasId().getId()));
        negocio.getGnEmpresaId().setRazonSocial(entidad.getGnEmpresasId().getRazonSocial());
        negocio.setGnUbicacionId(new Ubicacion(entidad.getGnUbicacionesId().getId()));
        negocio.getGnUbicacionId().setNombre(entidad.getGnUbicacionesId().getNombre());
        negocio.setNombre(entidad.getNombre());
        negocio.setActivo(entidad.getActivo());
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setDireccion(entidad.getDireccion());
        negocio.setTelefono(entidad.getTelefono());
        negocio.setMaeTipoId(entidad.getMaeTipoId());
        negocio.setMaeTipoCodigo(entidad.getMaeTipoCodigo());
        negocio.setMaeTipoValor(entidad.getMaeTipoValor());
        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;
    }
    
    private GnSedes castNegocioEntidad(GnSede negocio) {
        GnSedes entidad = new GnSedes();
        entidad.setId(negocio.getId());
        entidad.setGnEmpresasId(new GnEmpresas(negocio.getGnEmpresaId().getId()));
        entidad.setGnUbicacionesId(new GnUbicaciones(negocio.getGnUbicacionId().getId()));
        entidad.setNombre(negocio.getNombre());
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setDireccion(negocio.getDireccion());
        entidad.setTelefono(negocio.getTelefono());
        entidad.setActivo(negocio.isActivo());
        entidad.setMaeTipoId(negocio.getMaeTipoId());
        entidad.setMaeTipoCodigo(negocio.getMaeTipoCodigo());
        entidad.setMaeTipoValor(negocio.getMaeTipoValor());
        //Auditoria
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        return entidad;
    }

    @Override
    public boolean validarExiste(String nombre) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM GnSedes a "
                + "WHERE a.nombre = '" + nombre + "' ";
            List<GnSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && list.size() > 0) {
                existe = true;
            }
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public List<GnSede> listarSedesPorFuncionario(int idFuncionario) throws Exception {
        List<GnSede> sedes = new ArrayList<>();
        try {
            String strQuery = "SELECT a.gnSedesId FROM GatSedeFuncionarios a "
                + "WHERE a.gnUsuariosId.id = " + idFuncionario + " "
                + " AND a.activo = 1 AND a.gnSedesId.activo = 1";
            List<GnSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null) {
                for (GnSedes sede  : list) {
                    sedes.add(castEntidadNegocio(sede));
                }
            }
        } catch (NoResultException e) {
            sedes = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return sedes;
    }

    @Override
    public boolean validarExisteConSede(String nombre, int id) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM GnSedes a "
                + "WHERE a.nombre = '" + nombre + "' "
                + " AND a.activo = 1"
                +  " AND a.id != "+ id;
            List<GnSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && list.size() > 0) {
                existe = true;
            }
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public List<GnSede> listarSedesTurnero() throws Exception {
        List<GnSede> sedes = new ArrayList<>();
        try {
            String strQuery = "SELECT DISTINCT a.gnSedesId FROM GatSedeConfiguraciones a "
                + "WHERE a.turnero > 0 ";
            List<GnSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null) {
                for (GnSedes sede  : list) {
                    sedes.add(castEntidadNegocio(sede));
                }
            }
        } catch (NoResultException e) {
            sedes = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return sedes;
    }

    @Override
    public List<GnSede> listarSedesTurno() throws Exception {
        List<GnSede> sedes = new ArrayList<>();
        try {
            String strQuery = "SELECT DISTINCT a.gnSedesId FROM GatSedeConfiguraciones a "
                + "WHERE a.turnero = 1 AND a.gnSedesId.activo = 1 ";
            List<GnSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null) {
                for (GnSedes sede  : list) {
                    sedes.add(castEntidadNegocio(sede));
                }
            }
        } catch (NoResultException e) {
            sedes = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return sedes;
    }

    @Override
    public List<GnSede> listarPorUbicacion(int idUbicacion) throws Exception {
        List<GnSede> listaSedes = new ArrayList<>();
        try {
            String strQuery = "SELECT DISTINCT a.gnSedesId FROM GatSedeFuncionarios a "
                + "WHERE a.gnSedesId.activo = 1 and a.gnSedesId.gnUbicacionesId.id = "+idUbicacion;
            List<GnSedes> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && list.size() > 0) {
                for (GnSedes sede : list) {
                    listaSedes.add(castEntidadNegocio(sede));
                }
            }
        } catch (NoResultException e) {
            listaSedes = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaSedes;
    }
    
}
