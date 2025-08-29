/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.crue;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9;
import com.saviasaludeps.savia.dominio.crue.RefAnexo9Gestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.RefAnexo9Gestiones;
import com.saviasaludeps.savia.ejb.entidades.RefAnexos9;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.crue.RefAnexo9GestionaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(RefAnexo9GestionaRemoto.class)
@Local(RefAnexo9GestionaLocal.class)
public class RefAnexo9GestionaServicio extends GenericoServicio implements RefAnexo9GestionaRemoto, RefAnexo9GestionaLocal {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(r) FROM RefAnexo9Gestiones r "
                    + "WHERE r.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND r.refAnexos9Id.id = '" + paramConsulta.getParametroConsulta1() + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoId":
                            strQuery += "AND r.maeTipoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeMotivoId":
                            strQuery += "AND r.maeMotivoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.id":
                            strQuery += "AND r.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "observacion":
                            strQuery += "AND r.observacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND r.usuarioCrea LIKE '" + (String) e.getValue() + "%' ";
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
    public List<RefAnexo9Gestion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<RefAnexo9Gestion> listResult = new ArrayList<>();
        try {
            String strQuery = "SELECT r FROM RefAnexo9Gestiones r "
                    + "WHERE r.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND r.refAnexos9Id.id = '" + paramConsulta.getParametroConsulta1() + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoId":
                            strQuery += "AND r.maeTipoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "maeMotivoId":
                            strQuery += "AND r.maeMotivoId = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrestadorSede.id":
                            strQuery += "AND r.cntPrestadorSedesId.cntPrestadoresId.razonSocial LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "observacion":
                            strQuery += "AND r.observacion LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND r.usuarioCrea LIKE '" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "r." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "r.id DESC";
            }
            List<RefAnexo9Gestiones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RefAnexo9Gestiones per : list) {
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
    public int insertar(RefAnexo9Gestion obj) throws Exception {
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
    public RefAnexo9Gestion consultarPorRefAnexo9(int RefAnexo9Id) throws Exception {
        RefAnexo9Gestion resultado = new RefAnexo9Gestion();

        try {
            String strQuery = "SELECT r FROM RefAnexo9Gestiones r "
                    + "WHERE r.refAnexos9Id.id = :id ORDER BY r.id DESC ";

            resultado = castEntidadNegocio((RefAnexo9Gestiones) getEntityManager().createQuery(strQuery).setParameter("id", RefAnexo9Id).setMaxResults(1).getSingleResult());
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }
    
    @Override
    public RefAnexo9Gestion consultarPorRefAnexo9GestionRegulacion(int RefAnexo9Id) throws Exception {
        RefAnexo9Gestion resultado = new RefAnexo9Gestion();

        try {
            String strQuery = "SELECT r FROM RefAnexo9Gestiones r "
                    + "WHERE r.refAnexos9Id.id = :id AND r.maeTipoCodigo = 14 ORDER BY r.id ASC ";

            resultado = castEntidadNegocio((RefAnexo9Gestiones) getEntityManager().createQuery(strQuery).setParameter("id", RefAnexo9Id).setMaxResults(1).getSingleResult());
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }
    
    @Override
    public List<RefAnexo9Gestion> consultarPorRefAnexo9YEstado(int refAnexo9Id, String maeTipoTipo, String maeTipoValor) throws Exception {
        List<RefAnexo9Gestion> resultado = new ArrayList<>();

        try {
            String strQuery = "SELECT r FROM RefAnexo9Gestiones r "
                    + " INNER JOIN GnMaestros gnm ON r.maeTipoId = gnm.id "
                    + " WHERE r.refAnexos9Id.id = :id "
                    + " AND gnm.tipo.tipo = :maeTipoTipo "
                    + " AND gnm.valor = '" + maeTipoValor + "' "
                    + " ORDER BY r.id DESC ";

            List<RefAnexo9Gestiones> listaTmp = getEntityManager().createQuery(strQuery).
                    setParameter("id", refAnexo9Id).
                    setParameter("maeTipoTipo", maeTipoTipo).
                    getResultList();

            for (RefAnexo9Gestiones refAnexo9Gestiones : listaTmp) {
                resultado.add(castEntidadNegocio(refAnexo9Gestiones));
            }
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }
    
    @Override
    public List<RefAnexo9Gestion> consultarPorRefAnexo9PorId(int refAnexo9Id) throws Exception {
        List<RefAnexo9Gestion> resultado = new ArrayList<>();

        try {
            String strQuery = "SELECT r FROM RefAnexo9Gestiones r "
                    + " WHERE r.refAnexos9Id.id = :id "
                    + " ORDER BY r.id DESC ";

            List<RefAnexo9Gestiones> listaTmp = getEntityManager().createQuery(strQuery).
                    setParameter("id", refAnexo9Id).
                    getResultList();

            for (RefAnexo9Gestiones refAnexo9Gestiones : listaTmp) {
                resultado.add(castEntidadNegocio(refAnexo9Gestiones));
            }
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }
    
    @Override
    public RefAnexo9Gestion consultarPorRefAnexo9UltimoDireccionamiento(int RefAnexo9Id) throws Exception {
        RefAnexo9Gestion resultado = new RefAnexo9Gestion();

        try {
            String strQuery = "SELECT r "
                    + "FROM RefAnexo9Gestiones r "
                    + "INNER JOIN GnMaestros gnm ON r.maeTipoId = gnm.id "
                    + "WHERE r.refAnexos9Id.id = :id "
                    + "AND gnm.tipo.tipo = :tipo "
                    + "AND gnm.valor = '" + RefAnexo9Gestion.ESTADO_DIRECCIONA_CODIGO +"' "
                    + "ORDER BY r.id DESC ";

            resultado = castEntidadNegocio((RefAnexo9Gestiones) getEntityManager().createQuery(strQuery)
                    .setParameter("id", RefAnexo9Id)
                    .setParameter("tipo", RefAnexo9Gestion.ESTADO_GESTION_TIPO).setMaxResults(1).getSingleResult());
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }
    
    @Override
    public RefAnexo9Gestion consultarPorRefAnexo9EstadoCancelada(int RefAnexo9Id) throws Exception {
        RefAnexo9Gestion resultado = new RefAnexo9Gestion();

        try {
            String strQuery = "SELECT r "
                    + "FROM RefAnexo9Gestiones r "
                    + "INNER JOIN GnMaestros gnm ON r.maeTipoId = gnm.id "
                    + "WHERE r.refAnexos9Id.id = :id "
                    + "AND gnm.tipo.tipo = :tipo "
                    + "AND gnm.valor = '" + RefAnexo9Gestion.ESTADO_CANCELADA_CODIGO + "' "
                    + "ORDER BY r.id DESC ";

            resultado = castEntidadNegocio((RefAnexo9Gestiones) getEntityManager().createQuery(strQuery)
                    .setParameter("id", RefAnexo9Id)
                    .setParameter("tipo", RefAnexo9Gestion.ESTADO_GESTION_TIPO).setMaxResults(1).getSingleResult());
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }
    
    @Override
    public RefAnexo9Gestion consultarPorRefAnexo9EstadoCerrado(int RefAnexo9Id) throws Exception {
        RefAnexo9Gestion resultado = new RefAnexo9Gestion();

        try {
            String strQuery = "SELECT r "
                    + "FROM RefAnexo9Gestiones r "
                    + "INNER JOIN GnMaestros gnm ON r.maeTipoId = gnm.id "
                    + "WHERE r.refAnexos9Id.id = :id "
                    + "AND gnm.tipo.tipo = :tipo "
                    + "AND gnm.valor = '" + RefAnexo9Gestion.ESTADO_CERRADA_CODIGO + "' "
                    + "ORDER BY r.id DESC ";

            resultado = castEntidadNegocio((RefAnexo9Gestiones) getEntityManager().createQuery(strQuery)
                    .setParameter("id", RefAnexo9Id)
                    .setParameter("tipo", RefAnexo9Gestion.ESTADO_GESTION_TIPO).setMaxResults(1).getSingleResult());
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return resultado;
    }
    
    public static RefAnexo9Gestion castEntidadNegocio(RefAnexo9Gestiones ent) {
        RefAnexo9Gestion obj = new RefAnexo9Gestion();
        obj.setContactoNombre(ent.getContactoNombre());
        obj.setContactoTelefono(ent.getContactoTelefono());
        if (ent.getCntPrestadorSedesId() != null) {
            obj.setCntPrestadorSede(new CntPrestadorSede(ent.getCntPrestadorSedesId().getId()));
        }
        obj.setId(ent.getId());
        obj.setMaeMotivoCodigo(ent.getMaeMotivoCodigo());
        obj.setMaeMotivoId(ent.getMaeMotivoId());
        obj.setMaeMotivoValor(ent.getMaeMotivoValor());
        obj.setMaeTipoCodigo(ent.getMaeTipoCodigo());
        obj.setMaeTipoId(ent.getMaeTipoId());
        obj.setMaeTipoValor(ent.getMaeTipoValor());
        obj.setObservacion(ent.getObservacion());
        obj.setOrigen(ent.getOrigen());
        obj.setFechaHoraAceptacion(ent.getFechaHoraAceptacion());
        obj.setFechaHoraEgreso(ent.getFechaHoraEgreso());
        if (ent.getRefAnexos9Id() != null) {
            obj.setRefAnexo9(new RefAnexo9(ent.getRefAnexos9Id().getId()));
        }
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }

    public static RefAnexo9Gestiones castNegocioEntidad(RefAnexo9Gestion obj) {
        RefAnexo9Gestiones ent = new RefAnexo9Gestiones();
        ent.setContactoNombre(obj.getContactoNombre());
        ent.setContactoTelefono(obj.getContactoTelefono());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        if (obj.getCntPrestadorSede() != null) {
            ent.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
        }
        ent.setId(obj.getId());
        ent.setMaeMotivoCodigo(obj.getMaeMotivoCodigo());
        ent.setMaeMotivoId(obj.getMaeMotivoId());
        ent.setMaeMotivoValor(obj.getMaeMotivoValor());
        ent.setMaeTipoCodigo(obj.getMaeTipoCodigo());
        ent.setMaeTipoId(obj.getMaeTipoId());
        ent.setMaeTipoValor(obj.getMaeTipoValor());
        ent.setObservacion(obj.getObservacion());
        ent.setOrigen(obj.getOrigen());
        ent.setFechaHoraAceptacion(obj.getFechaHoraAceptacion());
        ent.setFechaHoraEgreso(obj.getFechaHoraEgreso());
        if (obj.getRefAnexo9() != null) {
            ent.setRefAnexos9Id(new RefAnexos9(obj.getRefAnexo9().getId()));
        }
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        return ent;
    }

}
