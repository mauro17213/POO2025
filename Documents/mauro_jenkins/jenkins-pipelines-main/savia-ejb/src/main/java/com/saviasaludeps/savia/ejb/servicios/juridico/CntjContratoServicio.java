package com.saviasaludeps.savia.ejb.servicios.juridico;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.ejb.entidades.CntjContratos;
import com.saviasaludeps.savia.ejb.entidades.CntjExpedientes;
import com.saviasaludeps.savia.ejb.entidades.CntjTerceros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.juridico.CtnjContratoRemoto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author idbohorquez
 */

@Stateless
@Remote(CtnjContratoRemoto.class)
public class CntjContratoServicio extends GenericoServicio implements CtnjContratoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CntjContratos c  ");
            StringBuilder strWhere = new StringBuilder(" WHERE c.id > 0 ");
            if(paramConsulta.getParametroConsulta1() != null){
                strQuery.append(" inner join CntjExpedientes ex on c.cntjExpedientesId.id = ex.id  ");
                strQuery.append(" inner join CntjUsuarioGrupos ug on ex.gnUsuariosPropietarioId.id = ug.gnUsuariosId.id and ug.gnUsuariosId.id =  ").append(paramConsulta.getParametroConsulta1());
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strWhere.append(" AND c.id = ").append(e.getValue());
                            break;
                        case "contrato":
                            strWhere.append(" AND c.contrato = '").append(e.getValue()).append("' ");
                            break;
                        case "cntjTerceroId.numeroDocumento":
                            strWhere.append(" AND c.cntjTercerosId.numeroDocumento = '").append(e.getValue()).append("' ");
                            break;
                        case "cntjTerceroId.razonSocial":
                            strWhere.append(" AND c.cntjTercerosId.razonSocial like '%").append(e.getValue()).append("%' ");
                            break;
                        case "maeModalidadContratoId":
                            strWhere.append(" AND c.maeModalidadContratoId = ").append(e.getValue());
                            break;
                        case "maeEstadoContratoId":
                            strWhere.append(" AND c.maeEstadoContratoId = ").append(e.getValue());
                            break;
                        
                    }
                }
            }
            strQuery.append(strWhere.toString());
            cant = (int) (long) getEntityManager().createQuery(strQuery.toString())
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
    public List<CntjContrato> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntjContrato> listResult = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratos c  ");
            StringBuilder strWhere = new StringBuilder(" WHERE c.id > 0 ");
            if(paramConsulta.getParametroConsulta1() != null){
                strQuery.append(" inner join CntjExpedientes ex on c.cntjExpedientesId.id = ex.id  ");
                strQuery.append(" inner join CntjUsuarioGrupos ug on ex.gnUsuariosPropietarioId.id = ug.gnUsuariosId.id and ug.gnUsuariosId.id =  ").append(paramConsulta.getParametroConsulta1());
            }
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strWhere.append(" AND c.id = ").append(e.getValue());
                            break;
                        case "contrato":
                            strWhere.append(" AND c.contrato = '").append(e.getValue()).append("' ");
                            break;
                        case "cntjTerceroId.numeroDocumento":
                            strWhere.append(" AND c.cntjTercerosId.numeroDocumento = '").append(e.getValue()).append("' ");
                            break;
                        case "cntjTerceroId.razonSocial":
                            strQuery.append(" AND c.cntjTercerosId.razonSocial like '%").append(e.getValue()).append("%' ");
                            break;
                        case "maeModalidadContratoId":
                            strWhere.append(" AND c.maeModalidadContratoId = ").append(e.getValue());
                            break;
                        case "maeEstadoContratoId":
                            strWhere.append(" AND c.maeEstadoContratoId = ").append(e.getValue());
                            break;
                    }
                }
            }
            strQuery.append(strWhere.toString());
            strQuery.append(" ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append(" c." + paramConsulta.getOrden() + " " + (paramConsulta.isAscendente() ? "ASC" : "DESC"));
            } else {
                strQuery.append(" c.id DESC ");
            }
            List<CntjContratos> list = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntjContratos comite  : list) {
                listResult.add(castEntidadNegocio(comite));
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
    public int insertar(CntjContrato objeto) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(id);
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
    public CntjContrato consultar(int id) throws Exception {
        CntjContrato objRes = null;
        try {
            objRes = castEntidadNegocio((CntjContratos) getEntityManager().find(CntjContratos.class, id));
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
    public void actualizar(CntjContrato objeto) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CntjContratos SET  ");
            if (objeto.getCntjExpedienteId() != null && objeto.getCntjExpedienteId().getId() != null) {
                sql.append("cntjExpedientesId.id = :cntjExpedientesId,  ");
            }
            sql.append("cntjTercerosId.id = :terceroid,  ");
            sql.append("maeTipoContratoId = :maeTipoContratoId,  ");
            sql.append("maeTipoContratoCodigo = :maeTipoContratoCodigo,  ");
            sql.append("maeTipoContratoValor = :maeTipoContratoValor,  ");
            sql.append("fechaInicio = :fechaInicio,  ");
            sql.append("fechaFin = :fechaFin,  ");            
            sql.append("valorInicial = :valorInicial,  ");
            sql.append("valorPagadoTotal = :valorPagadoTotal,  ");
            sql.append("valorContratoMasAdiciones = :valorContratoMasAdiciones,  ");
            sql.append("valorTotalOtrosies = :valortotalOtrosies,  ");
            sql.append("contrato = :contrato,  ");
            sql.append("maeEstadoContratoId = :maeEstadoContratoId,  ");
            sql.append("maeEstadoContratoCodigo = :maeEstadoContratoCodigo,  ");
            sql.append("maeEstadoContratoValor = :maeEstadoContratoValor,  ");
            sql.append("proceso = :proceso,  ");
            sql.append("maeClaseContratoId = :maeClaseContratoId,  ");
            sql.append("maeClaseContratoCodigo = :maeClaseContratoCodigo,  ");
            sql.append("maeClaseContratoValor = :maeClaseContratoValor,  ");
            sql.append("estadoLegalizacion = :estadoLegalizacion,  ");
            sql.append("maeModalidadContratoId = :maeModalidadContratoId,  ");
            sql.append("maeModalidadContratoCodigo = :maeModalidadContratoCodigo,  ");
            sql.append("maeModalidadContratoValor = :maeModalidadContratoValor,  ");
            sql.append("complejidad = :complejidad,  ");
            sql.append("maeRegimenId = :maeRegimenId,  ");
            sql.append("maeRegimenCodigo = :maeRegimenCodigo,  ");
            sql.append("maeRegimenValor = :maeRegimenValor,  ");
            sql.append("plazoInicialMeses = :plazoInicialMeses,  ");
            sql.append("plazoInicialDias = :plazoInicialDias,  ");
            sql.append("plazoProrrogas = :plazoProrrogas,  ");
            sql.append("plazoTotalDias = :plazoTotalDias,  ");
            sql.append("valorMes = :valorMes,  ");
            sql.append("valorDia = :valorDia,  ");
            sql.append("valorUpc = :valorUpc,  ");
            sql.append("valorAdiciones = :valorAdiciones,  ");
            sql.append("valorTotal = :valorTotal,  ");
            sql.append("formaPago = :formaPago,  ");
            sql.append("tipoAnticipo = :tipoAnticipo,  ");
            sql.append("valorAnticipo = :valorAnticipo,  ");           
            sql.append("objeto = :objeto,  ");           
            sql.append("fechaSuscripcion = :fechaSuscripcion,  ");           
            sql.append("tipoGasto = :tipoGasto,  ");           
            sql.append("fechaSuspension = :fechaSuspension,  ");           
            sql.append("motivoSuspension = :motivoSuspension,  ");           
            sql.append("periodicidadSeguimiento = :periodicidadSeguimiento,  ");                
            sql.append("motivoTerminacionAnticipada = :motivoTerminacionAnticipada,  ");  
            sql.append("enlacePublicaSecop = :enlacePublicaSecop,  ");  
            sql.append("fechaPublicaSecop = :fechaPublicaSecop,  ");  
            sql.append("responsablePublicaSecop = :responsablePublicaSecop,  ");  
            sql.append("fechaLiquidacion = :fechaLiquidacion,  ");  
            sql.append("valorFacturado = :valorFacturado,  ");  
                       
            sql.append("usuarioModifica = :usuarioModifica, ");
            sql.append("terminalModifica = :terminalModifica, ");
            sql.append("fechaHoraModifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            if (objeto.getCntjExpedienteId() != null && objeto.getCntjExpedienteId().getId() != null) {
                query.setParameter("cntjExpedientesId", objeto.getCntjExpedienteId().getId());
            }
            query.setParameter("terceroid", objeto.getCntjTerceroId().getId());
            query.setParameter("maeTipoContratoId", objeto.getMaeTipoContratoId());
            query.setParameter("maeTipoContratoCodigo", objeto.getMaeTipoContratoCodigo());
            query.setParameter("maeTipoContratoValor", objeto.getMaeTipoContratoValor());
            query.setParameter("fechaInicio", objeto.getFechaInicio());
            query.setParameter("fechaFin", objeto.getFechaFin());
            query.setParameter("valorInicial", objeto.getValorInicial());
            query.setParameter("valorPagadoTotal", objeto.getValorPagadoTotal());
            query.setParameter("valorContratoMasAdiciones", objeto.getValorContratoMasAdiciones());
            query.setParameter("valortotalOtrosies", objeto.getValorTotalOtrosies());
            query.setParameter("contrato", objeto.getContrato());
            query.setParameter("maeEstadoContratoId", objeto.getMaeEstadoContratoId());
            query.setParameter("maeEstadoContratoCodigo", objeto.getMaeEstadoContratoCodigo());
            query.setParameter("maeEstadoContratoValor", objeto.getMaeEstadoContratoValor());
            query.setParameter("proceso", objeto.getProceso());
            query.setParameter("maeClaseContratoId", objeto.getMaeClaseContratoId());
            query.setParameter("maeClaseContratoCodigo", objeto.getMaeClaseContratoCodigo());
            query.setParameter("maeClaseContratoValor", objeto.getMaeClaseContratoValor());
            query.setParameter("estadoLegalizacion", objeto.getEstadoLegalizacion());
            query.setParameter("maeModalidadContratoId", objeto.getMaeModalidadContratoId());
            query.setParameter("maeModalidadContratoCodigo", objeto.getMaeModalidadContratoCodigo());
            query.setParameter("maeModalidadContratoValor", objeto.getMaeModalidadContratoValor());
            query.setParameter("complejidad", objeto.getComplejidad());
            query.setParameter("maeRegimenId", objeto.getMaeRegimenId());
            query.setParameter("maeRegimenCodigo", objeto.getMaeRegimenCodigo());
            query.setParameter("maeRegimenValor", objeto.getMaeRegimenValor());
            query.setParameter("plazoInicialMeses", Integer.parseInt(objeto.getPlazoInicialMeses()));
            query.setParameter("plazoInicialDias", objeto.getPlazoInicialDias());
            query.setParameter("plazoProrrogas", objeto.getPlazoProrrogas());
            query.setParameter("plazoTotalDias", objeto.getPlazoTotalDias());
            query.setParameter("valorMes", objeto.getValorMes());
            query.setParameter("valorDia", objeto.getValorDia());
            query.setParameter("valorUpc", objeto.getValorUpc());
            query.setParameter("valorAdiciones", objeto.getValorAdiciones());
            query.setParameter("valorTotal", objeto.getValorTotal());
            query.setParameter("formaPago", objeto.getFormaPago());
            query.setParameter("tipoAnticipo", objeto.getTipoAnticipo());
            query.setParameter("valorAnticipo", objeto.getValorAnticipo());     
            query.setParameter("objeto", objeto.getObjeto());     
            query.setParameter("fechaSuscripcion", objeto.getFechaSuscripcion());     
            query.setParameter("tipoGasto", objeto.getTipoGasto());     
            query.setParameter("fechaSuspension", objeto.getFechaSuspension());     
            query.setParameter("motivoSuspension", objeto.getMotivoSuspension());     
            query.setParameter("periodicidadSeguimiento", objeto.getPeriodicidadSeguimiento());               
            query.setParameter("motivoTerminacionAnticipada", objeto.getMotivoTerminaAnticipada());     
            query.setParameter("enlacePublicaSecop", objeto.getEnlacePublicaSecop());     
            query.setParameter("fechaPublicaSecop", objeto.getFechaPublicaSecop());     
            query.setParameter("responsablePublicaSecop", objeto.getResponsablePublicaSecop());     
            query.setParameter("fechaLiquidacion", objeto.getFechaLiquidacion());     
            query.setParameter("valorFacturado", objeto.getValorFacturado());     
                       
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public CntjContrato eliminar(int id) throws Exception {
        CntjContrato obj = null;
        try {
            CntjContratos ent = getEntityManager().find(CntjContratos.class, id);
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
    public void actualizarPlazoProrroga(CntjContrato objeto) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE cntj_contratos SET  ");   
            if(objeto.getPlazoProrrogas() > 0){
                sql.append("plazo_prorrogas = case when plazo_prorrogas is null then :diasprorroga else  plazo_prorrogas + :diasprorroga end,  ");
            }else{
                sql.append("plazo_prorrogas = case when plazo_prorrogas is null then :diasprorroga else  plazo_prorrogas - :diasprorroga end ,  ");
            }                
            sql.append("plazo_total_dias = plazo_inicial_dias + plazo_prorrogas , ");
            sql.append("fecha_inicio = :fechaInicial , ");
            sql.append("fecha_fin = :fechaFinal , ");
            sql.append("usuario_modifica = :usuarioModifica, ");
            sql.append("terminal_modifica = :terminalModifica, ");
            sql.append("fecha_hora_modifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = em.createNativeQuery(sql.toString());
            query.setParameter("diasprorroga", Math.abs(objeto.getPlazoProrrogas()));                        
            query.setParameter("fechaInicial", objeto.getFechaInicio());                        
            query.setParameter("fechaFinal", objeto.getFechaFin());                        
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
     public void actualizarValorAdiciones(CntjContrato objeto) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("update cntj_contratos set   ");   
            if(objeto.getValorAdiciones().compareTo(BigDecimal.ZERO) > 0){
                sql.append("valor_adiciones = case when valor_adiciones is null then :valorAdiciones else  valor_adiciones + :valorAdiciones end ,  ");
            }else if (objeto.getValorAdiciones().compareTo(BigDecimal.ZERO) < 0){
                sql.append("valor_adiciones = case when valor_adiciones is null then :valorAdiciones else  valor_adiciones - :valorAdiciones end ,  ");
            }                
            sql.append(" valor_total = ifnull(valor_inicial + valor_adiciones, 0) , ");
            sql.append("usuario_modifica = :usuarioModifica, ");
            sql.append("terminal_modifica = :terminalModifica, ");
            sql.append("fecha_hora_modifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = em.createNativeQuery(sql.toString());
            if (objeto.getValorAdiciones().compareTo(BigDecimal.ZERO) != 0) {
                query.setParameter("valorAdiciones", objeto.getValorAdiciones().abs());
            }                                    
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
     
     @Override
     public void actualizarFechaFin(CntjContrato objeto) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("update cntj_contratos set   ");
            sql.append("fecha_fin = :fechaFinal , ");
            sql.append("usuario_modifica = :usuarioModifica, ");
            sql.append("terminal_modifica = :terminalModifica, ");
            sql.append("fecha_hora_modifica = :fechaHoraModifica ");
            sql.append("WHERE id = :id");

            Query query = em.createNativeQuery(sql.toString());
            query.setParameter("fechaFinal", objeto.getFechaFin());                        
            query.setParameter("usuarioModifica", objeto.getUsuarioModifica());
            query.setParameter("terminalModifica", objeto.getTerminalModifica());
            query.setParameter("fechaHoraModifica", objeto.getFechaHoraModifica());
            query.setParameter("id", objeto.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
     public boolean existeContratoCodigo(String codigo) throws Exception {
        boolean existe = false;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT count(c) FROM CntjContratos c WHERE c.id > 0 ");            
            strQuery.append(" and contrato = :codigo ");            
            Integer existente = (int) (long) getEntityManager().createQuery(strQuery.toString())
                    .setParameter("codigo", codigo)
                    .getSingleResult();            
            if(existente > 0){
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
     public String ultimoNumeroContrato(String anio) throws Exception {
        String resultado = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT IFNULL(MAX(c.contrato), 0) FROM CntjContratos c WHERE c.id > 0 AND c.contrato LIKE '%-").append(anio).append("' ");            
            resultado = (String) getEntityManager().createQuery(strQuery.toString())
                    .getSingleResult();
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
     public CntjContrato contratoPorNumero(String numero) throws Exception {
        CntjContrato resultado = null;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT c FROM CntjContratos c WHERE c.id > 0 AND c.contrato = '").append(numero).append("' ");            
            CntjContratos result = (CntjContratos) getEntityManager().createQuery(strQuery.toString())
                    .getSingleResult();
            resultado = castEntidadNegocio(result);
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    private CntjContrato castEntidadNegocio(CntjContratos entidad) {
        CntjContrato objeto = new CntjContrato();
        objeto.setId(entidad.getId());
        if(entidad.getCntjExpedientesId() != null){
            objeto.setCntjExpedienteId(new CntjExpediente(entidad.getCntjExpedientesId().getId()));
        }       
        CntjTercero contratista = new CntjTercero(entidad.getCntjTercerosId().getId());
        contratista.setRazonSocial(entidad.getCntjTercerosId().getRazonSocial());
        contratista.setMaeTipoDocumentoValor(entidad.getCntjTercerosId().getMaeTipoDocumentoValor());
        contratista.setNumeroDocumento(entidad.getCntjTercerosId().getNumeroDocumento());
        contratista.setNaturalezaJuridica((int)entidad.getCntjTercerosId().getNaturalezaJuridica());
        Ubicacion ubicacion = new Ubicacion(entidad.getCntjTercerosId().getGnUbicacionesId().getId());
        ubicacion.setNombre(entidad.getCntjTercerosId().getGnUbicacionesId().getNombre());
        contratista.setGnUbicacionId(ubicacion);
        contratista.setDireccion(entidad.getCntjTercerosId().getDireccion());
        contratista.setCorreoElectronico(entidad.getCntjTercerosId().getCorreoElectronico());
        contratista.setTelefonoTercero(entidad.getCntjTercerosId().getTelefonoTercero());        
        objeto.setCntjTerceroId(contratista);        
        objeto.setMaeTipoContratoId(entidad.getMaeTipoContratoId());
        objeto.setMaeTipoContratoCodigo(entidad.getMaeTipoContratoCodigo());
        objeto.setMaeTipoContratoCodigo(entidad.getMaeTipoContratoValor());
        objeto.setFechaInicio(entidad.getFechaInicio());
        objeto.setFechaFin(entidad.getFechaFin());
        objeto.setValorInicial(entidad.getValorInicial());
        objeto.setValorPagadoTotal(entidad.getValorPagadoTotal());  
        objeto.setValorContratoMasAdiciones(entidad.getValorContratoMasAdiciones());
        objeto.setValorTotalOtrosies(entidad.getValorTotalOtrosies());
        objeto.setContrato(entidad.getContrato());
        objeto.setMaeEstadoContratoId(entidad.getMaeEstadoContratoId());
        objeto.setMaeEstadoContratoCodigo(entidad.getMaeEstadoContratoCodigo());
        objeto.setMaeEstadoContratoValor(entidad.getMaeEstadoContratoValor());
        objeto.setProceso(entidad.getProceso());
        objeto.setMaeClaseContratoId(entidad.getMaeClaseContratoId());
        objeto.setMaeClaseContratoCodigo(entidad.getMaeClaseContratoCodigo());
        objeto.setMaeClaseContratoValor(entidad.getMaeClaseContratoValor());
        objeto.setEstadoLegalizacion(entidad.getEstadoLegalizacion());
        objeto.setMaeModalidadContratoId(entidad.getMaeModalidadContratoId());
        objeto.setMaeModalidadContratoCodigo(entidad.getMaeModalidadContratoCodigo());
        objeto.setMaeModalidadContratoValor(entidad.getMaeModalidadContratoValor());
        objeto.setComplejidad(entidad.getComplejidad());
        objeto.setMaeRegimenId(entidad.getMaeRegimenId());
        objeto.setMaeRegimenCodigo(entidad.getMaeRegimenCodigo());
        objeto.setMaeRegimenValor(entidad.getMaeRegimenValor());
        objeto.setPlazoInicialMeses(String.valueOf(entidad.getPlazoInicialMeses()));
        objeto.setPlazoInicialDias(entidad.getPlazoInicialDias());
        objeto.setPlazoProrrogas(entidad.getPlazoProrrogas());
        objeto.setPlazoTotalDias(entidad.getPlazoTotalDias());
        objeto.setValorMes(entidad.getValorMes());
        objeto.setValorDia(entidad.getValorDia());
        objeto.setValorUpc(entidad.getValorUpc());
        objeto.setValorAdiciones(entidad.getValorAdiciones());
        objeto.setValorTotal(entidad.getValorTotal());
        objeto.setFormaPago(entidad.getFormaPago());
        objeto.setTipoAnticipo(entidad.getTipoAnticipo());
        objeto.setValorAnticipo(entidad.getValorAnticipo()); 
        objeto.setObjeto(entidad.getObjeto());
        objeto.setFechaSuscripcion(entidad.getFechaSuscripcion());
        objeto.setTipoGasto(entidad.getTipoGasto());
        objeto.setFechaSuspension(entidad.getFechaSuspension());
        objeto.setMotivoSuspension(entidad.getMotivoSuspension());
        objeto.setPeriodicidadSeguimiento(entidad.getPeriodicidadSeguimiento());        
        objeto.setMotivoTerminaAnticipada(entidad.getMotivoTerminacionAnticipada());
        objeto.setEnlacePublicaSecop(entidad.getEnlacePublicaSecop());
        objeto.setFechaPublicaSecop(entidad.getFechaPublicaSecop());
        objeto.setResponsablePublicaSecop(entidad.getResponsablePublicaSecop());
        objeto.setFechaLiquidacion(entidad.getFechaLiquidacion());
        objeto.setValorFacturado(entidad.getValorFacturado());
        
        objeto.setUsuarioCrea(entidad.getUsuarioCrea());
        objeto.setFechaHoraCrea(entidad.getFechaHoraCrea());
        objeto.setTerminalCrea(entidad.getTerminalCrea());
        objeto.setUsuarioModifica(entidad.getUsuarioModifica());
        objeto.setFechaHoraModifica(entidad.getFechaHoraModifica());
        objeto.setTerminalModifica(entidad.getTerminalModifica());
        return objeto;
    }
    
    private CntjContratos castNegocioEntidad(CntjContrato obj){
        CntjContratos ent = new CntjContratos();
        if(obj.getCntjExpedienteId() != null && obj.getCntjExpedienteId().getId() != null){
            ent.setCntjExpedientesId(new CntjExpedientes(obj.getCntjExpedienteId().getId()));
        }
        ent.setCntjTercerosId(new CntjTerceros(obj.getCntjTerceroId().getId()));        
        ent.setMaeTipoContratoId(obj.getMaeTipoContratoId());
        ent.setMaeTipoContratoCodigo(obj.getMaeTipoContratoCodigo());
        ent.setMaeTipoContratoCodigo(obj.getMaeTipoContratoValor());
        ent.setFechaInicio(obj.getFechaInicio());
        ent.setFechaFin(obj.getFechaFin());
        ent.setValorInicial(obj.getValorInicial());
        ent.setValorPagadoTotal(obj.getValorPagadoTotal());  
        ent.setValorContratoMasAdiciones(obj.getValorContratoMasAdiciones());
        ent.setValorTotalOtrosies(obj.getValorTotalOtrosies());
        ent.setContrato(obj.getContrato());
        ent.setMaeEstadoContratoId(obj.getMaeEstadoContratoId());
        ent.setMaeEstadoContratoCodigo(obj.getMaeEstadoContratoCodigo());
        ent.setMaeEstadoContratoValor(obj.getMaeEstadoContratoValor());
        ent.setProceso(obj.getProceso());
        ent.setMaeClaseContratoId(obj.getMaeClaseContratoId());
        ent.setMaeClaseContratoCodigo(obj.getMaeClaseContratoCodigo());
        ent.setMaeClaseContratoValor(obj.getMaeClaseContratoValor());
        ent.setEstadoLegalizacion(obj.getEstadoLegalizacion());
        ent.setMaeModalidadContratoId(obj.getMaeModalidadContratoId());
        ent.setMaeModalidadContratoCodigo(obj.getMaeModalidadContratoCodigo());
        ent.setMaeModalidadContratoValor(obj.getMaeModalidadContratoValor());
        ent.setComplejidad(obj.getComplejidad());
        ent.setMaeRegimenId(obj.getMaeRegimenId());
        ent.setMaeRegimenCodigo(obj.getMaeRegimenCodigo());
        ent.setMaeRegimenValor(obj.getMaeRegimenValor());
        ent.setPlazoInicialMeses(Integer.parseInt(obj.getPlazoInicialMeses()));
        ent.setPlazoInicialDias(obj.getPlazoInicialDias());
        ent.setPlazoProrrogas(obj.getPlazoProrrogas());
        ent.setPlazoTotalDias(obj.getPlazoTotalDias());
        ent.setValorMes(obj.getValorMes());
        ent.setValorDia(obj.getValorDia());
        ent.setValorUpc(obj.getValorUpc());
        ent.setValorAdiciones(obj.getValorAdiciones());
        ent.setValorTotal(obj.getValorTotal());
        ent.setFormaPago(obj.getFormaPago());
        ent.setTipoAnticipo(obj.getTipoAnticipo());
        ent.setValorAnticipo(obj.getValorAnticipo()); 
        ent.setObjeto(obj.getObjeto());
        ent.setFechaSuscripcion(obj.getFechaSuscripcion());
        ent.setTipoGasto(obj.getTipoGasto());
        ent.setFechaSuspension(obj.getFechaSuspension());
        ent.setMotivoSuspension(obj.getMotivoSuspension());
        ent.setPeriodicidadSeguimiento(obj.getPeriodicidadSeguimiento());
        ent.setMotivoTerminacionAnticipada(obj.getMotivoTerminaAnticipada());
        ent.setEnlacePublicaSecop(obj.getEnlacePublicaSecop());
        ent.setFechaPublicaSecop(obj.getFechaPublicaSecop());
        ent.setResponsablePublicaSecop(obj.getResponsablePublicaSecop());
        ent.setFechaLiquidacion(obj.getFechaLiquidacion());
        ent.setValorFacturado(obj.getValorFacturado());
        
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        return ent;
    }

    
}
