package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCargaContenido;
import com.saviasaludeps.savia.dominio.facturacionelectronica.CmFeRipsFactura;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargaContenidos;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeRipsCargaRemoto;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

@Stateless
@Remote(CmFeRipsCargaRemoto.class)
public class CmFeRipsCargaServicio extends GenericoServicio implements CmFeRipsCargaRemoto {


    @Override
    public CmFeRipsCarga eliminar(int id) throws java.lang.Exception {
        CmFeRipsCarga obj = null;
        try {
            CmFeRipsCargas ent = getEntityManager().find(CmFeRipsCargas.class, id);
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
    public CmFeRipsCarga consultar(int id) throws java.lang.Exception {
        CmFeRipsCarga objRes = null;
        try {
            String hql = "SELECT c FROM CmFeRipsCargas c "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("id", id);
            CmFeRipsCargas carga = (CmFeRipsCargas) query.getSingleResult();
            objRes = castEntidadNegocio(carga);
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
            String strQuery = "SELECT COUNT(c.id) FROM CmFeRipsCargas c LEFT JOIN CntContratos cntc ON c.cntContratosId = cntc.id "
                    + " LEFT JOIN GnUsuarios u ON u.id  = c.radicadorAsignado.id "
                    + " WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "facturaNumero":
                            strQuery += "AND c.facturaNumero = '" + (String) e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += "AND c.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "origenCarga":
                            strQuery += "AND c.origenCarga = " + e.getValue() + " ";
                            break;
                        case "gnPrestadorSede.nombreSede":
                            strQuery += "AND c.cntPrestadorSedesId.nombre = '" + e.getValue() + "' ";
                            break;
                        case "gnPrestadorSede.cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                         case "gnPrestadorSedeId":
                            strQuery += "AND c.cntPrestadorSedesId.id = '" + e.getValue() + "' ";
                            break;    
                        case "cntContrato.id":
                            strQuery += "AND c.cntContratosId = " + (String) e.getValue() + " ";
                            break;
                        case "contrato":
                            strQuery += "AND c.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += "AND c.fechaPrestacion = " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND c.fechaHoraCrea = " + e.getValue() + " ";
                            break;
                        case "maeContratoModalidadValor":
                            strQuery += "AND c.maeContratoModalidadValor = '" + e.getValue() + "' ";
                            break;
                        case "maeRegimenValor":
                            strQuery += "AND c.maeRegimenValor = '" + e.getValue() + "' ";
                            break;
                        case "pbs":
                        case "cobertura":
                            strQuery += " AND c.cobertura = " + e.getValue() + " ";
                            break;
                        case "camaFija":
                            strQuery += " AND c.camaFija = " + e.getValue() + " ";
                            break;
                        case "usuarioModifica":
                            strQuery += " AND c.usuarioModifica like '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND c.tipo = " + e.getValue() + " ";
                            break;
                        case "origen":
                            strQuery += "AND c.origen = " + e.getValue() + " ";
                            break;
                        case "capitaPeriodo":
                            strQuery += "AND c.capitaPeriodo = " + e.getValue() + " ";
                            break;
                        case "numeroNota":
                            strQuery += "AND c.numeroNota = '" + e.getValue() + "' ";
                            break;
                        case "soportesNumero":
                            strQuery += "AND c.soportesNumero = '" + e.getValue() + "' ";
                            break;
                        case "multiusuario":
                            strQuery += "AND c.multiusuario = '" + e.getValue() + "' ";
                            break;
                       case "radicadorAsignado.nombre":
                           String value = String.valueOf(e.getValue()).trim();
                           if (value.compareToIgnoreCase("null") == 0) {
                               strQuery += "AND c.radicadorAsignado IS NULL ";
                           } else {
                               strQuery += "AND CONCAT(u.usuario, '(', u.nombre, ')') like '%" + value + "%' ";
                           }
                            break;
                       case "idModalidadContratoAsignar":
                            strQuery += "AND c.maeContratoModalidadId IN (" + e.getValue() + ") ";
                            break;
                        case "estadoCargaAsignar":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "de5601SoporteFe":
                            strQuery += "AND c.de5601SoporteFe = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            //SI ES UNA IPS SOLO LISTAR LOS CARGUES DEL PRESTADOR
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND c.cntPrestadorSedesId.cntPrestadoresId.id = :id_prestador ";
            }
            
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += "AND c.radicadorAsignado.id = :id_radicador ";
            }
            
            //RANGO DE FECHA CARGA
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaHoraInicio >= :fh_inicio AND c.fechaHoraFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                strQuery += "AND c.fechaHoraInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                strQuery += "AND c.fechaHoraFin <= :fh_fin ";
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("id_prestador", paramConsulta.getParametroConsulta3());
            }
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            
            if (paramConsulta.getParametroConsulta4() != null) {
               query.setParameter("id_radicador", paramConsulta.getParametroConsulta4());
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
    public List<CmFeRipsCarga> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmFeRipsCarga> listaCargas = new ArrayList();
        try {
            String strQuery = "SELECT c, cntc , u FROM CmFeRipsCargas c LEFT JOIN CntContratos cntc ON c.cntContratosId = cntc.id "
                    + " LEFT JOIN GnUsuarios u ON u.id  = c.radicadorAsignado.id "
                    + " WHERE c.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "facturaNumero":
                            strQuery += "AND c.facturaNumero = '" + (String) e.getValue() + "' ";
                            break;
                        case "id":
                            strQuery += "AND c.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "origenCarga":
                            strQuery += "AND c.origenCarga = " + e.getValue() + " ";
                            break;    
                        case "gnPrestadorSede.nombreSede":
                            strQuery += "AND c.cntPrestadorSedesId.nombre = '" + e.getValue() + "' ";
                            break;
                        case "gnPrestadorSede.cntPrestador.numeroDocumento":
                            strQuery += "AND c.cntPrestadorSedesId.cntPrestadoresId.numeroDocumento = '" + e.getValue() + "' ";
                            break;
                        case "gnPrestadorSedeId":
                            strQuery += "AND c.cntPrestadorSedesId.id = '" + e.getValue() + "' ";
                            break;  
                        case "cntContrato.id":
                            strQuery += "AND c.cntContratosId = " + (String) e.getValue() + " ";
                            break;
                       case "contrato":
                            strQuery += "AND c.contrato = '" + (String) e.getValue() + "' ";
                            break;
                        case "fechaPrestacion":
                            strQuery += "AND c.fechaPrestacion > " + e.getValue() + " ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND c.fechaHoraCrea > " + e.getValue() + " ";
                            break;
                        case "maeContratoModalidadValor":
                            strQuery += "AND c.maeContratoModalidadValor = '" + e.getValue() + "' ";
                            break;
                        case "maeRegimenValor":
                            strQuery += "AND c.maeRegimenValor = '" + e.getValue() + "' ";
                            break;
                        case "pbs":
                        case "cobertura":
                            strQuery += " AND c.cobertura = " + e.getValue() + " ";
                            break;
                        case "camaFija":
                            strQuery += " AND c.camaFija = " + e.getValue() + " ";
                            break;
                        case "usuarioModifica":
                            strQuery += " AND c.usuarioModifica like '%" + e.getValue() + "%' ";
                            break;
                        case "tipo":
                            strQuery += "AND c.tipo = " + e.getValue() + " ";
                            break;
                        case "origen":
                            strQuery += "AND c.origen = " + e.getValue() + " ";
                            break;
                        case "capitaPeriodo":
                            strQuery += "AND c.capitaPeriodo = " + e.getValue() + " ";
                            break;
                        case "numeroNota":
                            strQuery += "AND c.numeroNota = '" + e.getValue() + "' ";
                            break;
                        case "soportesNumero":
                            strQuery += "AND c.soportesNumero = '" + e.getValue() + "' ";
                            break;
                        case "multiusuario":
                            strQuery += "AND c.multiusuario = '" + e.getValue() + "' ";
                            break;
                        case "radicadorAsignado.nombre":  
                            String value = String.valueOf(e.getValue()).trim();
                            if (value.compareToIgnoreCase("null") == 0) {
                                strQuery += "AND c.radicadorAsignado IS NULL ";
                            } else {
                                strQuery += "AND CONCAT(u.usuario, '(', u.nombre, ')') like '%" + value + "%' ";
                            }
                            break;
                         case "idModalidadContratoAsignar":
                            strQuery += "AND c.maeContratoModalidadId IN (" + e.getValue() + ") ";
                            break;
                        case "estadoCargaAsignar":
                            strQuery += "AND c.estado = " + e.getValue() + " ";
                            break;
                        case "de5601SoporteFe":
                            strQuery += "AND c.de5601SoporteFe = " + e.getValue() + " ";
                            break;
                        case "radicadorAsignado.vacio":
                           
                            break;
                    }
                }
            }
            //SI ES UNA IPS SOLO LISTAR LOS CARGUES DEL PRESTADOR
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND c.cntPrestadorSedesId.cntPrestadoresId.id = :id_prestador ";
            }
            //RANGO DE FECHA CARGA
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND c.fechaHoraInicio >= :fh_inicio AND c.fechaHoraFin <= :fh_fin ";
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                strQuery += "AND c.fechaHoraInicio >= :fh_inicio ";
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                strQuery += "AND c.fechaHoraFin <= :fh_fin ";
            }
            
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += "AND c.radicadorAsignado.id = :id_radicador ";
            }
                                  
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                String order = paramConsulta.getOrden();
                strQuery += " c." + order + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += " c.id DESC ";
            }
                      
            Query query = getEntityManager().createQuery(strQuery);
            //SI ES UNA IPS SOLO LISTAR LOS CARGUES DEL PRESTADOR
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("id_prestador", paramConsulta.getParametroConsulta3());
            }
            if (paramConsulta.getParametroConsulta1() != null
                    && paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() == null) {
                query.setParameter("fh_inicio", ((Date) paramConsulta.getParametroConsulta1()), TemporalType.TIMESTAMP);
            } else if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta1() == null) {
                query.setParameter("fh_fin", ((Date) paramConsulta.getParametroConsulta2()), TemporalType.TIMESTAMP);
            }
            
            if (paramConsulta.getParametroConsulta4() != null) {
                 query.setParameter("id_radicador", paramConsulta.getParametroConsulta4());
            }
            
              List<Object[]> listCargas = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            if (listCargas != null) {
                for (Object[] cargaObj : listCargas) {
                 CmFeRipsCargas cargaEntidad = (CmFeRipsCargas) Optional.ofNullable(cargaObj[0]).orElse(new CmRipsCargas());
                 CmFeRipsCarga cmFeRipsCarga = castEntidadNegocio(cargaEntidad);
                 cmFeRipsCarga.setGnPrestadorSede(castEntidadNegocioPrestador(cargaEntidad));
                 CntContratos contratoObj = (CntContratos) Optional.ofNullable(cargaObj[1]).orElse(new CntContratos());
                 String contratoNumero = Optional.ofNullable(contratoObj.getContrato()).orElse("");
                 cmFeRipsCarga.getCntContrato().setContrato(contratoNumero);
                 listaCargas.add(cmFeRipsCarga);
                }
            }
        } catch (NoResultException e) {
            listaCargas = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaCargas;
    }
    
    /**
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public List<CmFeRipsCarga> consultarExistenciaCargaFactura(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CmFeRipsCarga> listaCargas = new ArrayList();
        try {
            String strQuery = "FROM CmFeRipsCargas c "
                    + " WHERE c.id > 0 ";
            
            if(paramConsulta.getParametroConsulta1() != null){
               strQuery += " AND c.facturaNumero = :facturaNumero";
            }
                    
            if(paramConsulta.getParametroConsulta2() != null){
               strQuery += " AND c.documentoPrestador = :documentoPrestador";
            }
            
            if(paramConsulta.getParametroConsulta3() != null){
               strQuery += " AND c.cntPrestadorSedesId.id = :idSede";
            }
            
            if(paramConsulta.getParametroConsulta4() != null){
               strQuery += " AND c.maeRegimenId= :idRegimen";
            }
            
            if(paramConsulta.getParametroConsulta5() != null){
               strQuery += " AND c.capitaPeriodo= :capitaPeriodo";
            }
            
            if(paramConsulta.getParametroConsulta6() != null){
               strQuery += " AND c.tipo= :tipo";
            }
            
            if(paramConsulta.getParametroConsulta7() != null){
               strQuery += " AND c.numeroNota= :numeroNota";
            }
            
            strQuery += " ORDER BY c.id desc ";
            
            Query query = getEntityManager().createQuery(strQuery);
            
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("facturaNumero", paramConsulta.getParametroConsulta1());
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("documentoPrestador", paramConsulta.getParametroConsulta2());
            }
            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("idSede", paramConsulta.getParametroConsulta3());
            }
            if (paramConsulta.getParametroConsulta4() != null) {
                query.setParameter("idRegimen", paramConsulta.getParametroConsulta4());
            }
            if (paramConsulta.getParametroConsulta5() != null) {
                query.setParameter("capitaPeriodo", paramConsulta.getParametroConsulta5());
            }            
            if (paramConsulta.getParametroConsulta6() != null) {
                query.setParameter("tipo", paramConsulta.getParametroConsulta6());
            }
            if (paramConsulta.getParametroConsulta7() != null) {
                query.setParameter("numeroNota", paramConsulta.getParametroConsulta7());
            }
   
            List<CmFeRipsCargas> listCargas = query
                    .getResultList();

            if (listCargas != null) {
                for (CmFeRipsCargas cargaObj : listCargas) {
                    listaCargas.add(castEntidadNegocio(cargaObj));
                }
            }
        } catch (NoResultException e) {
            listaCargas = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaCargas;
    }

    @Override
    public int insertar(CmFeRipsCarga obj) throws java.lang.Exception {
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
    public void actualizar(CmFeRipsCarga obj) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void actualizarAtributosDevolucion(CmFeRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " de1601EpsErronea = :de1601EpsErronea ,"
                    + " de4401ProfesionalRed = :de4401ProfesionalRed ,"
                    + " de4402ProfesionalIndependiente = :de4402ProfesionalIndependiente ,"
                    + " de5001Pagada = :de5001Pagada ,"
                    + " de5002Radicada = :de5002Radicada ,"
                    + " de5601SoporteFe = :de5601SoporteFe ,"
                    + " de5601Soportes = :de5601Soportes ,"
                    + " usuarioModifica = :usuarioModifica ,"
                    + " terminalModifica = :terminalModifica ,"
                    + " fechaHoraModifica = :fechaHoraModifica "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("de1601EpsErronea", obj.getDe1601EpsErronea());
            query.setParameter("de4401ProfesionalRed", obj.getDe4401ProfesionalRed());
            query.setParameter("de4402ProfesionalIndependiente", obj.getDe4402ProfesionalIndependiente());
            query.setParameter("de5001Pagada", obj.getDe5001Pagada());
            query.setParameter("de5002Radicada", obj.getDe5002Radicada());
            query.setParameter("de5601SoporteFe", obj.getDe5601SoporteFe());
            query.setParameter("de5601Soportes", obj.getDe5601Soportes());
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
    
    @Override
    public void actualizarDescripcionDevolucion(CmFeRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " devolucion = :devolucion ,"
                    + " fechaHoraDevolucion = :fechaHoraDevolucion ,"
                    + " maeDevolucionId = :maeDevolucionId ,"
                    + " maeDevolucionCodigo = :maeDevolucionCodigo ,"
                    + " maeDevolucionValor = :maeDevolucionValor ,"
                    + " observacionDevolucion = :observacionDevolucion "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("devolucion", obj.getDevolucion());
            query.setParameter("fechaHoraDevolucion", obj.getFechaHoraDevolucion());
            query.setParameter("maeDevolucionId", obj.getMaeDevolucionId());
            query.setParameter("maeDevolucionCodigo", obj.getMaeDevolucionCodigo());
            query.setParameter("maeDevolucionValor", obj.getMaeDevolucionValor());
            query.setParameter("observacionDevolucion", obj.getObservacionDevolucion());
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
    
    @Override
    public void actualizarNumeroSoportes(CmFeRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " soportesNumero = :soportesNumero "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("soportesNumero", obj.getSoportesNumero());
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
    public void actualizarAtributoSoporteFe(CmFeRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " de5601SoporteFe = :de5601SoporteFe "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("de5601SoporteFe", obj.getDe5601SoporteFe());
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
    
    
    @Override
    public void actualizarFechaInicio(CmFeRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " fechaHoraInicio = :fechaHoraInicio"
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("fechaHoraInicio", obj.getFechaHoraInicio());
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
    
    @Override
    public void actualizarRequisitosManuales(CmFeRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " de4401_profesional_red = :de4401_profesional_red ,"
                    + " de4402_profesional_independiente = :de4402_profesional_independiente ,"
                    + " de5601_soportes = :de5601_soportes ,"
                    + " usuarioModifica = :usuarioModifica ,"
                    + " terminalModifica = :terminalModifica ,"
                    + " fechaHoraModifica = :fechaHoraModifica "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("de4401_profesional_red", obj.getDe4401ProfesionalRed());
            query.setParameter("de4402_profesional_independiente", obj.getDe4402ProfesionalIndependiente());
            query.setParameter("de5601_soportes", obj.getDe5601Soportes());
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
    
    
    @Override
    public void actualizarEstado(CmFeRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " estado = :estado "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
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
    
    @Override
    public void actualizarObservacion(CmFeRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " observacion = :observacion "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("observacion", obj.getObservacion());
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
    
    @Override
    public void actualizarRechazo(CmFeRipsCarga obj) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " rechazo = :rechazo, "
                    + " fechaHoraRechazo = :fechaHoraRechazo, "
                    + " observacionRechazo = :observacionRechazo "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("rechazo", obj.getRechazo());
            query.setParameter("fechaHoraRechazo", obj.getFechaHoraRechazo());
            query.setParameter("observacionRechazo", obj.getObservacionRechazo());
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
    
    @Override
    public void actualizarCargaPeriodo(int idCarga, int idCargaPeriodo) throws Exception {
        try {
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " cargaPeriodoId.id = :idCargaPeriodo "
                    + " WHERE id = :id ";
            query = getEntityManager().createQuery(hql);
            query.setParameter("idCargaPeriodo", idCargaPeriodo);
            query.setParameter("id", idCarga);
            query.executeUpdate();
            //Actualizar roles
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void actualizarRadicadorAsignado(String idCargas, int idRadicador) throws Exception {
        try {
            List<Integer> ids = Arrays.stream(idCargas.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            Query query;
            String hql = "UPDATE CmFeRipsCargas SET "
                    + " radicadorAsignado.id = :idRadicador "
                    + " WHERE id IN ( :idCargas )";
            query = getEntityManager().createQuery(hql);
            query.setParameter("idRadicador", idRadicador);
            query.setParameter("idCargas", ids);
            query.executeUpdate();
            //Actualizar roles
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public int insertarCmFeCargaContenido(CmFeRipsCargaContenido obj) throws java.lang.Exception {
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
    public int insertarFeRipsCmFacturas(CmFeRipsFactura factura) throws java.lang.Exception {
        int id = 0;
        try {
            String query = "INSERT INTO cm_fe_rips_facturas "
                    + " (cm_fe_rips_cargas_id, cm_facturas_id, usuario_crea,"
                    + " terminal_crea, fecha_hora_crea ) "
                    + " VALUES (?, ?, ?, ?, ?)";

            Query queryN = em.createNativeQuery(query);

            queryN.setParameter(1, factura.getCmFeRipsCarga().getId());
            queryN.setParameter(2, factura.getCmFactura().getId());
            queryN.setParameter(3, factura.getUsuarioCrea());
            queryN.setParameter(4, factura.getTerminalCrea());
            queryN.setParameter(5, factura.getFechaHoraCrea());
            queryN.executeUpdate();

            BigInteger lastInsertedId = (BigInteger) em.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
            id = lastInsertedId.intValue();

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
    public Map<String, CmFeRipsCarga> consultarCargasSegunNitFactura(List<String> nombresArchivos, String estado) throws java.lang.Exception {
        
        Map<String, CmFeRipsCarga> cargas = new HashMap<>();
        try {

            if (nombresArchivos.isEmpty()) {
                return Collections.emptyMap();
            }

            int batchSize = 20;
            for (int i = 0; i < nombresArchivos.size(); i += batchSize) {
                List<String> batch = nombresArchivos.subList(i, Math.min(i + batchSize, nombresArchivos.size()));
                String queryStr = " SELECT documento_prestador, factura_numero, id, estado, "
                        + " soportes_numero, multiusuario FROM cm_fe_rips_cargas"
                        + " WHERE id>0 AND CONCAT(documento_prestador, '_', factura_numero) IN ("
                        + batch.stream().map(n -> "'" + n + "'").collect(Collectors.joining(","))
                        + ") ";
                if (!estado.isEmpty()) {
                    queryStr += " AND estado IN (" + estado + ") ";
                }

                Query query = getEntityManager().createNativeQuery(queryStr);
                List<Object[]> lstObj = query.getResultList();

                for (Object[] cargaArray : lstObj) {
                    CmFeRipsCarga carga = new CmFeRipsCarga();
                    carga.setId((Integer) cargaArray[2]);
                    carga.setEstado((Integer) cargaArray[3]);
                    carga.setSoportesNumero((Integer) cargaArray[4]);
                    carga.setFacturaNumero((String)cargaArray[1]);
                    carga.setMultiusuario((Boolean) cargaArray[5]);
                    cargas.put(cargaArray[0].toString() + "_" + cargaArray[1].toString(), carga);
                } 
            }

        } catch (NoResultException e) {
            cargas = new HashMap<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return cargas;
    }

    public static CmFeRipsCarga castEntidadNegocio(CmFeRipsCargas ent) {
        CmFeRipsCarga neg = new CmFeRipsCarga();
        if (ent.getId() != null) {
            neg.setId(ent.getId());
        }                
          
        if (ent.getCntPrestadorSedesId() != null) {
            CntPrestadorSede prestador = new CntPrestadorSede(ent.getCntPrestadorSedesId().getId());
            prestador.setNombreSede(ent.getCntPrestadorSedesId().getNombre());
            CntPrestador cntPrestador = new CntPrestador();
            cntPrestador.setRazonSocial(ent.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
            prestador.setCntPrestador(cntPrestador);
            neg.setGnPrestadorSede(prestador);
        }
      
        neg.setTipo(ent.getTipo());
        if (ent.getCntContratosId() != null) {
            neg.setCntContrato(new CntContrato(ent.getCntContratosId().getId()));
        }
        if (ent.getCargaPeriodoId() != null && ent.getCargaPeriodoId().getId() != null) {
            neg.setCargaPeriodo(new CmFeRipsCarga( ent.getCargaPeriodoId().getId() ));
        }
        
        if (ent.getRadicadorAsignado() != null && ent.getRadicadorAsignado().getId() != null) {
            neg.setRadicadorAsignado(new Usuario(ent.getRadicadorAsignado().getId()));
            neg.getRadicadorAsignado().setNombre(ent.getRadicadorAsignado().getUsuario()+
                                      "("+ent.getRadicadorAsignado().getNombre()+")");
        }
        
        neg.setContrato(ent.getContrato());
        neg.setCntTipoContratoId(ent.getCntTipoContratoId());
        neg.setEstado(ent.getEstado());
        neg.setMaeRegimenId(ent.getMaeRegimenId());
        neg.setMaeRegimenCodigo(ent.getMaeRegimenCodigo());
        neg.setMaeRegimenValor(ent.getMaeRegimenValor());
        neg.setMaeContratoModalidadId(ent.getMaeContratoModalidadId());
        neg.setMaeContratoModalidadCodigo(ent.getMaeContratoModalidadCodigo());
        neg.setMaeContratoModalidadValor(ent.getMaeContratoModalidadValor());
        neg.setFechaHoraInicio(ent.getFechaHoraInicio());
        neg.setFechaHoraFin(ent.getFechaHoraFin());      
        neg.setTiempo(ent.getTiempo());
        neg.setCobertura(ent.getCobertura());
        neg.setFacturaNumero(ent.getFacturaNumero());
        neg.setSoportesNumero(ent.getSoportesNumero());
        neg.setFacturaValor(ent.getFacturaValor());
        neg.setRechazo(ent.getRechazo());
        neg.setFechaHoraRechazo(ent.getFechaHoraRechazo());
        neg.setObservacionRechazo(ent.getObservacionRechazo());
        neg.setDevolucion(ent.getDevolucion());
        neg.setFechaHoraDevolucion(ent.getFechaHoraDevolucion());
        neg.setMaeDevolucionId(ent.getMaeDevolucionId());
        neg.setMaeDevolucionCodigo(ent.getMaeDevolucionCodigo());
        neg.setMaeDevolucionValor(ent.getMaeDevolucionValor());
        neg.setObservacionDevolucion(ent.getObservacionDevolucion());
        neg.setObservacion(ent.getObservacion());
        neg.setDocumentoPrestador(ent.getDocumentoPrestador());
        neg.setCuv(ent.getCuv());
        neg.setCufe(ent.getCufe());
        neg.setDe1601EpsErronea(ent.getDe1601EpsErronea());
        neg.setDe4401ProfesionalRed(ent.getDe4401ProfesionalRed());
        neg.setDe4402ProfesionalIndependiente(ent.getDe4402ProfesionalIndependiente());
        neg.setDe5001Pagada(ent.getDe5001Pagada());
        neg.setDe5002Radicada(ent.getDe5002Radicada());
        neg.setDe5601SoporteFe(ent.getDe5601SoporteFe());
        neg.setDe5601Soportes(ent.getDe5601Soportes());
        neg.setFechaHoraMinisterio(ent.getFechaHoraMinisterio());
        neg.setFechaHoraEmision(ent.getFechaHoraEmision()); 
        neg.setOrigenCarga(ent.getOrigenCarga());
        neg.setOrigen(ent.getOrigen());
        neg.setCapitaPeriodo(ent.getCapitaPeriodo());
        neg.setNumeroNota(ent.getNumeroNota());
        neg.setUrlJson(ent.getUrlJson());
        neg.setUrlXml(ent.getUrlXml());
        neg.setValorCopago(ent.getValorCopago());
        neg.setValorCuotaModeradora(ent.getValorCuotaModeradora());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setMultiusuario(ent.getMultiusuario());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        neg.setTieneTransacciones(!ent.getCmFeTransaccionesList().isEmpty());
        return neg;
    }
    public static CmFeRipsCargas castNegocioEntidad(CmFeRipsCarga neg) {
        CmFeRipsCargas ent = new CmFeRipsCargas();
        if (neg.getId() != null) {
            ent.setId(neg.getId());
        }
        if (neg.getEmpresa() != null) {
            ent.setGnEmpresasId(new GnEmpresas(neg.getEmpresa().getId()));
        }   
        if (neg.getGnPrestadorSede() != null) {
            ent.setCntPrestadorSedesId(new CntPrestadorSedes(neg.getGnPrestadorSede().getId()));
        }
        ent.setTipo(neg.getTipo());
        if (neg.getCntContrato() != null && neg.getCntContrato() .getId() != null ) {
            ent.setCntContratosId(new CntContratos(neg.getCntContrato().getId()));
        }       
        if (neg.getCargaPeriodo() != null && neg.getCargaPeriodo().getId() != null) {
            ent.setCargaPeriodoId(new CmFeRipsCargas( neg.getCargaPeriodo().getId() ));
        }
        
        if (neg.getRadicadorAsignado() != null && neg.getRadicadorAsignado().getId() != null) {
            ent.setRadicadorAsignado(new GnUsuarios(neg.getRadicadorAsignado().getId() ));
        }
        
        ent.setContrato(neg.getContrato());
        ent.setCntTipoContratoId(neg.getCntTipoContratoId());
        ent.setEstado(neg.getEstado());
        ent.setMaeRegimenId(neg.getMaeRegimenId());
        ent.setMaeRegimenCodigo(neg.getMaeRegimenCodigo());
        ent.setMaeRegimenValor(neg.getMaeRegimenValor());
        ent.setMaeContratoModalidadId(neg.getMaeContratoModalidadId());
        ent.setMaeContratoModalidadCodigo(neg.getMaeContratoModalidadCodigo());
        ent.setMaeContratoModalidadValor(neg.getMaeContratoModalidadValor());
        ent.setFechaHoraInicio(neg.getFechaHoraInicio());
        ent.setFechaHoraFin(neg.getFechaHoraFin());      
        ent.setTiempo(neg.getTiempo());
        ent.setCobertura(neg.getCobertura());
        ent.setFacturaNumero(neg.getFacturaNumero());
        ent.setFacturaValor(neg.getFacturaValor());
        ent.setSoportesNumero(neg.getSoportesNumero());
        ent.setRechazo(neg.getRechazo());
        ent.setFechaHoraRechazo(neg.getFechaHoraRechazo());
        ent.setObservacionRechazo(neg.getObservacionRechazo());
        ent.setDevolucion(neg.getDevolucion());
        ent.setFechaHoraDevolucion(neg.getFechaHoraDevolucion());
        ent.setMaeDevolucionId(neg.getMaeDevolucionId());
        ent.setMaeDevolucionCodigo(neg.getMaeDevolucionCodigo());
        ent.setMaeDevolucionValor(neg.getMaeDevolucionValor());
        ent.setObservacionDevolucion(neg.getObservacionDevolucion());
        ent.setObservacion(neg.getObservacion());
        ent.setDocumentoPrestador(neg.getDocumentoPrestador());
        ent.setCuv(neg.getCuv());
        ent.setCufe(neg.getCufe());
        ent.setDe1601EpsErronea(neg.getDe1601EpsErronea());
        ent.setDe4401ProfesionalRed(neg.getDe4401ProfesionalRed());
        ent.setDe4402ProfesionalIndependiente(neg.getDe4402ProfesionalIndependiente());
        ent.setDe5001Pagada(neg.getDe5001Pagada());
        ent.setDe5002Radicada(neg.getDe5002Radicada());
        ent.setDe5601SoporteFe(neg.getDe5601SoporteFe());
        ent.setDe5601Soportes(neg.getDe5601Soportes());
        ent.setFechaHoraMinisterio(neg.getFechaHoraMinisterio());
        ent.setFechaHoraEmision(neg.getFechaHoraEmision());        
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setOrigenCarga(neg.getOrigenCarga());
        ent.setOrigen(neg.getOrigen());
        ent.setCapitaPeriodo(neg.getCapitaPeriodo());
        ent.setNumeroNota(neg.getNumeroNota());
        ent.setUrlJson(neg.getUrlJson());
        ent.setUrlXml(neg.getUrlXml());
        ent.setValorCopago(neg.getValorCopago());
        ent.setValorCuotaModeradora(neg.getValorCuotaModeradora());
        ent.setMultiusuario(neg.getMultiusuario());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        return ent;
    }
    
    public static CntPrestadorSede castEntidadNegocioPrestador(CmFeRipsCargas cargaEnt) {
        CntPrestadorSede pretadorSede = new CntPrestadorSede();
        if (cargaEnt.getCntPrestadorSedesId() != null) {
            pretadorSede.setId(cargaEnt.getCntPrestadorSedesId().getId());
            pretadorSede.setNombreSede(cargaEnt.getCntPrestadorSedesId().getNombre());
            CntPrestador cntPrestador = new CntPrestador();
            cntPrestador.setId(cargaEnt.getCntPrestadorSedesId().getCntPrestadoresId().getId());
            cntPrestador.setRazonSocial(cargaEnt.getCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
            cntPrestador.setNumeroDocumento(cargaEnt.getCntPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
            pretadorSede.setCntPrestador(cntPrestador);
        }
        
        return pretadorSede;
    }
    
    public static CmFeRipsCargaContenidos castNegocioEntidad(CmFeRipsCargaContenido neg) {
        CmFeRipsCargaContenidos ent = new CmFeRipsCargaContenidos();
        if (neg.getId() != null) {
            ent.setId(neg.getId());
        }
        ent.setCmFeRipsCargasId(new CmFeRipsCargas(neg.getCmFeRipsCarga().getId()));
        ent.setTipo(neg.getTipo());
        ent.setJson(neg.getJson());
        ent.setXml(neg.getXml());
        ent.setCuv(neg.getCuv());
        ent.setCuvJson(neg.getCuvJson());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        return ent;
    }
    
  

    
}
