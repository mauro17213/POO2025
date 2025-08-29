/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.informe;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfInforme;
import com.saviasaludeps.savia.dominio.informe.InfInformeVariable;
import com.saviasaludeps.savia.ejb.entidades.InfGrupoUsuarios;
import com.saviasaludeps.savia.ejb.entidades.InfGrupos;
import com.saviasaludeps.savia.ejb.entidades.InfInformeVariables;
import com.saviasaludeps.savia.ejb.entidades.InfInformes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.informe.InformeRemoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(InformeRemoto.class)
public class InformeServicio extends GenericoServicio implements InformeRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(i) FROM InfInformes i "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                int[] estados = (int[]) paramConsulta.getParametroConsulta1();
                boolean primero = true;
                for (int i = 0; i <= (estados.length - 1); i++) {
                    if (primero) {
                        strQuery += "AND (i.infGruposId.id = " + estados[i] + " ";
                        primero = false;
                    } else {
                        strQuery += "OR i.infGruposId.id = " + estados[i] + " ";
                    }
                }
                strQuery += ") ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "grupo.nombre":
                            strQuery += "AND i.infGruposId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND i.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND i.estado = " + (String) e.getValue() + " ";
                            break;
                        case "programado":
                            strQuery += "AND i.programado = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND i.activo = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND i.usuarioCrea LIKE '%" + e.getValue() + "%' ";
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
    public List<InfInforme> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<InfInforme> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfInformes i "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                int[] estados = (int[]) paramConsulta.getParametroConsulta1();
                boolean primero = true;
                for (int i = 0; i <= (estados.length - 1); i++) {
                    if (primero) {
                        strQuery += "AND (i.infGruposId.id = " + estados[i] + " ";
                        primero = false;
                    } else {
                        strQuery += "OR i.infGruposId.id = " + estados[i] + " ";
                    }
                }
                strQuery += ") ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "grupo.nombre":
                            strQuery += "AND i.infGruposId.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombre":
                            strQuery += "AND i.nombre LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND i.estado = " + (String) e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += "AND i.activo = " + e.getValue() + " ";
                            break;
                        case "programado":
                            strQuery += "AND i.programado = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND i.usuarioCrea LIKE '%" + e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "i." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "i.id DESC ";
            }
            List<InfInformes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (InfInformes ent : list) {
                listResult.add(castEntidadNegocio(ent));
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
    public List<InfInforme> consultarListaPlantillas(ParamConsulta paramConsulta) throws Exception {
        List<InfInforme> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfInformes i "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                int[] grupos = (int[]) paramConsulta.getParametroConsulta1();
                boolean primero = true;
                for (int i = 0; i <= (grupos.length - 1); i++) {
                    if (primero) {
                        strQuery += "AND (i.infGruposId.id = " + grupos[i] + " ";
                        primero = false;
                    } else {
                        strQuery += "OR i.infGruposId.id = " + grupos[i] + " ";
                    }
                }
                strQuery += ") ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "i." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "i.nombre ASC ";
            }
            List<InfInformes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfInformes ent : list) {
                listResult.add(castEntidadNegocioConVariables(ent));
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
    public InfInforme consultar(int id) throws Exception {
        InfInforme objRes = null;
        try {
            objRes = castEntidadNegocioConVariables((InfInformes) getEntityManager().find(InfInformes.class, id));
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
    public boolean existeInformePorNombre(String nombre) throws Exception {
        boolean objRes = false;
        try {
            TypedQuery<InfInformes> consulta = getEntityManager().createNamedQuery("InfInformes.findByNombre", InfInformes.class);
            consulta.setParameter("nombre", nombre);
            List<InfInformes> lista = consulta.getResultList();
            if (!lista.isEmpty()) {
                objRes = true;
            } else {
                objRes = false;
            }
        } catch (NoResultException e) {
            objRes = false;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(InfInforme obj) throws Exception {
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
    public void actualizar(InfInforme obj) throws Exception {
        try {
            String hql = "UPDATE InfInformes SET "
                    + "infGruposId.id = :inf_grupos_id, "
                    + "nombre = :nombre, "
                    + "nombreSp = :nombre_sp, "
                    + "descripcion = :descripcion, "
                    + "estado = :estado, "
                    + "activo = :activo, "
                    + "programado = :programado, "
                    + "tipoPeriodicidad = :tipoPeriodicidad, "
                    + "periodicidad = :periodicidad, "
                    + "nombreArchivo = :nombre_archivo, "
                    + "carpeta = :carpeta, "
                    + "multipleGeneracion = :multipleGeneracion, "
                    + "multipleEmpresa = :multipleEmpresa, "
                    + "maeTipoFormatoId = :maeTipoFormatoId, "
                    + "maeTipoFormatoCodigo = :maeTipoFormatoCodigo, "
                    + "maeTipoFormatoValor = :maeTipoFormatoValor, "
                    + "fechaProgramadaInicio = :fecha_programada_inicio, "
                    + "fechaProgramadoFin = :fecha_programado_fin, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("inf_grupos_id", obj.getGrupo().getId());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("nombre_sp", obj.getNombreSp());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("estado", obj.getEstado());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("programado", obj.isProgramado());
            query.setParameter("periodicidad", obj.getPeriodicidad());
            query.setParameter("tipoPeriodicidad", obj.getTipoPeriodicidad());
            query.setParameter("nombre_archivo", obj.getNombreArchivo());
            query.setParameter("fecha_programada_inicio", obj.getFechaProgramadaInicio());
            query.setParameter("fecha_programado_fin", obj.getFechaProgramadoFin());
            query.setParameter("carpeta", obj.getCarpeta());
            query.setParameter("multipleGeneracion", obj.getMultipleGeneracion());
            query.setParameter("multipleEmpresa", obj.getMultipleEmpresa());
            query.setParameter("maeTipoFormatoId", obj.getMaeTipoFormatoId());
            query.setParameter("maeTipoFormatoCodigo", obj.getMaeTipoFormatoCodigo());
            query.setParameter("maeTipoFormatoValor", obj.getMaeTipoFormatoValor());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public InfInforme eliminar(int id) throws Exception {
        InfInforme obj = null;
        try {
            InfInformes ent = getEntityManager().find(InfInformes.class, id);
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

    private InfInforme castEntidadNegocio(InfInformes ent) {
        InfInforme negocio = new InfInforme();
        negocio.setId(ent.getId());
        negocio.setGrupo(new InfGrupo(ent.getInfGruposId().getId()));
        negocio.getGrupo().setNombre(ent.getInfGruposId().getNombre());
        negocio.setNombreSp(ent.getNombreSp());
        negocio.setDescripcion(ent.getDescripcion());
        negocio.setEstado(ent.getEstado());
        negocio.setActivo(ent.getActivo());
        negocio.setRequiereAprobacion(ent.getRequiereAprobacion());
        negocio.setProgramado(ent.getProgramado());
        negocio.setPeriodicidad(ent.getPeriodicidad());
        negocio.setTipoPeriodicidad(ent.getTipoPeriodicidad());
        negocio.setNombre(ent.getNombre());
        negocio.setNombreArchivo(ent.getNombreArchivo());
        negocio.setCarpeta(ent.getCarpeta());
        negocio.setFechaProgramadaInicio(ent.getFechaProgramadaInicio());
        negocio.setFechaProgramadoFin(ent.getFechaProgramadoFin());
        negocio.setMultipleGeneracion(ent.getMultipleGeneracion());
        negocio.setMultipleEmpresa(ent.getMultipleEmpresa());
        negocio.setMaeTipoFormatoId(ent.getMaeTipoFormatoId());
        negocio.setMaeTipoFormatoCodigo(ent.getMaeTipoFormatoCodigo());
        negocio.setMaeTipoFormatoValor(ent.getMaeTipoFormatoValor());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private InfInforme castEntidadNegocioConVariables(InfInformes ent) {
        InfInforme negocio = new InfInforme();
        negocio.setId(ent.getId());
        negocio.setGrupo(new InfGrupo(ent.getInfGruposId().getId()));
        negocio.getGrupo().setNombre(ent.getInfGruposId().getNombre());
        negocio.setNombreSp(ent.getNombreSp());
        negocio.setDescripcion(ent.getDescripcion());
        negocio.setEstado(ent.getEstado());
        negocio.setCarpeta(ent.getCarpeta());
        negocio.setActivo(ent.getActivo());
        negocio.setRequiereAprobacion(ent.getRequiereAprobacion());
        negocio.setProgramado(ent.getProgramado());
        negocio.setPeriodicidad(ent.getPeriodicidad());
        negocio.setTipoPeriodicidad(ent.getTipoPeriodicidad());
        negocio.setNombre(ent.getNombre());
        if (ent.getInfInformeVariablesList() != null) {
            negocio.setListaVariables(castEntidadNegocioVariables(ent.getInfInformeVariablesList()));
        }
        negocio.setNombreArchivo(ent.getNombreArchivo());
        negocio.setFechaProgramadaInicio(ent.getFechaProgramadaInicio());
        negocio.setFechaProgramadoFin(ent.getFechaProgramadoFin());
        negocio.setMultipleGeneracion(ent.getMultipleGeneracion());
        negocio.setMultipleEmpresa(ent.getMultipleEmpresa());
        negocio.setMaeTipoFormatoId(ent.getMaeTipoFormatoId());
        negocio.setMaeTipoFormatoCodigo(ent.getMaeTipoFormatoCodigo());
        negocio.setMaeTipoFormatoValor(ent.getMaeTipoFormatoValor());
        negocio.setUsuarioCrea(ent.getUsuarioCrea());
        negocio.setTerminalCrea(ent.getTerminalCrea());
        negocio.setFechaHoraCrea(ent.getFechaHoraCrea());
        negocio.setUsuarioModifica(ent.getUsuarioModifica());
        negocio.setTerminalModifica(ent.getTerminalModifica());
        negocio.setFechaHoraModifica(ent.getFechaHoraModifica());
        return negocio;
    }

    private InfInformes castNegocioEntidad(InfInforme neg) {
        InfInformes ent = new InfInformes();
        ent.setInfGruposId(new InfGrupos(neg.getGrupo().getId()));
        ent.setNombre(neg.getNombre());
        ent.setNombreSp(neg.getNombreSp());
        ent.setDescripcion(neg.getDescripcion());
        ent.setEstado(neg.getEstado());
        ent.setActivo(neg.isActivo());
        ent.setCarpeta(neg.getCarpeta());
        ent.setRequiereAprobacion(neg.isRequiereAprobacion());
        ent.setProgramado(neg.isProgramado());
        ent.setPeriodicidad(neg.getPeriodicidad());
        ent.setTipoPeriodicidad(neg.getTipoPeriodicidad());
        ent.setNombre(neg.getNombre());
        ent.setNombreArchivo(neg.getNombreArchivo());
        ent.setFechaProgramadaInicio(neg.getFechaProgramadaInicio());
        ent.setFechaProgramadoFin(neg.getFechaProgramadoFin());
        ent.setMultipleGeneracion(neg.getMultipleGeneracion());
        ent.setMultipleEmpresa(neg.getMultipleEmpresa());
        ent.setMaeTipoFormatoId(neg.getMaeTipoFormatoId());
        ent.setMaeTipoFormatoCodigo(neg.getMaeTipoFormatoCodigo());
        ent.setMaeTipoFormatoValor(neg.getMaeTipoFormatoValor());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }

    private List<InfInformeVariable> castEntidadNegocioVariables(List<InfInformeVariables> infInformeVariablesList) {
        List<InfInformeVariable> listaVariables = new ArrayList<>();
        for (InfInformeVariables ent : infInformeVariablesList) {
            InfInformeVariable variable = new InfInformeVariable();
            variable.setId(ent.getId());
            variable.setNombre(ent.getNombre());
            variable.setOrden(ent.getOrden());
            variable.setDinamico(ent.getDinamico());
            variable.setValor(ent.getValor());
            if (ent.getTipo() != null) {
                variable.setTipo(ent.getTipo());
            }
            listaVariables.add(variable);
        }
        return listaVariables;
    }

    @Override
    public List<InfInforme> consultarListaPlantillasNoProgramados(ParamConsulta paramConsulta) throws Exception {
        List<InfInforme> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfInformes i "
                    + "WHERE i.programado = 0 AND i.activo = 1 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                int[] grupos = (int[]) paramConsulta.getParametroConsulta1();
                boolean primero = true;
                for (int i = 0; i <= (grupos.length - 1); i++) {
                    if (primero) {
                        strQuery += "AND (i.infGruposId.id = " + grupos[i] + " ";
                        primero = false;
                    } else {
                        strQuery += "OR i.infGruposId.id = " + grupos[i] + " ";
                    }
                }
                strQuery += ") ";
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "i." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "i.nombre ASC ";
            }
            List<InfInformes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfInformes ent : list) {
                listResult.add(castEntidadNegocioConVariables(ent));
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
    public List<InfInforme> consultarTodos() throws Exception {
        List<InfInforme> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfInformes i "
                    + "WHERE i.activo = 1 ";
            strQuery += "ORDER BY i.nombre ASC ";
            
            List<InfInformes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfInformes ent : list) {
                listResult.add(castEntidadNegocioConVariables(ent));
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
    public List<InfInforme> consultarPorListaGrupo(String lista) throws Exception {
        List<InfInforme> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfInformes i "
                    + "WHERE i.activo = 1 ";
            strQuery += "AND i.infGruposId.id IN (" + lista + ") ";
            strQuery += "ORDER BY i.nombre ASC ";
            
            List<InfInformes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfInformes ent : list) {
                listResult.add(castEntidadNegocioConVariables(ent));
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
    public List<Empresa> consultarEmpresasPorInforme(int idInforme) throws Exception {
        List<Empresa> listResult = new ArrayList();
        HashMap<Integer, Empresa>  mapa = new HashMap<>();
        try {
            String strQuery = "FROM InfInformes i "
                    + "WHERE i.activo = 1 AND i.multipleGeneracion = 1 AND i.programado = 1 AND i.id = "+idInforme+ " ";
            strQuery += "ORDER BY i.nombre ASC ";
            
            List<InfInformes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfInformes ent : list) {
                if (ent.getInfGruposId() !=  null) {
                    for (InfGrupoUsuarios usuario : ent.getInfGruposId().getInfGrupoUsuariosList()) {
                        Empresa empresa = new Empresa();
                        empresa.setId(usuario.getGnUsuariosId().getGnEmpresasId().getId());
                        empresa.setRazonSocial(usuario.getGnUsuariosId().getGnEmpresasId().getRazonSocial());
                        empresa.setNit(usuario.getGnUsuariosId().getGnEmpresasId().getNit());
                        mapa.put(empresa.getId(), empresa);
                    }
                }
                
                break;
            }
            listResult.addAll(mapa.values());
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

}
