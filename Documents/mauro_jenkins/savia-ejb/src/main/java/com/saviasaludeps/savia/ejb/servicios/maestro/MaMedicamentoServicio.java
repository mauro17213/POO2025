/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaAgrupadorMedicamento;
import com.saviasaludeps.savia.ejb.entidades.MaAgrupadoresMedicamento;
import com.saviasaludeps.savia.ejb.entidades.MaMedicamentos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author jyperez
 */
@Stateless
@Remote(MaMedicamentoRemoto.class)
public class MaMedicamentoServicio extends GenericoServicio implements MaMedicamentoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaMedicamentos p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maAgrupadorMedicamento.codigo":
                            strQuery += " AND p.maAgrupadoresMedicamentoId.codigo LIKE  '%" + e.getValue() + "%' ";
                            break;
                        case "maAgrupadorMedicamento.nombre":
                            strQuery += " AND p.maAgrupadoresMedicamentoId.nombre LIKE  '%" + e.getValue() + "%' ";
                            break;
                        case "cum":
                            strQuery += " AND p.cum LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionInvima":
                            strQuery += " AND p.descripcionInvima LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionEstandarizada":
                            strQuery += " AND p.descripcionEstandarizada LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionId":
                            strQuery += " AND p.maeConcentracionId = " + e.getValue() + " ";
                            break;
                        case "maeConcentracionCodigo":
                            strQuery += " AND p.maeConcentracionCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionValor":
                            strQuery += " AND p.maeConcentracionValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoId":
                            strQuery += " AND p.maePrincipioActivoId = " + e.getValue() + " ";
                            break;
                        case "maePrincipioActivoCodigo":
                            strQuery += " AND p.maePrincipioActivoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoValor":
                            strQuery += " AND p.maePrincipioActivoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaId":
                            strQuery += " AND p.maeFormaFarmaceuticaId = " + e.getValue() + " ";
                            break;
                        case "maeFormaFarmaceuticaCodigo":
                            strQuery += " AND p.maeFormaFarmaceuticaCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaValor":
                            strQuery += " AND p.maeFormaFarmaceuticaValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoPpmId":
                            strQuery += " AND p.maeTipoPpmId = " + e.getValue() + " ";
                            break;
                        case "maeTipoPpmCodigo":
                            strQuery += " AND p.maeTipoPpmCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoPpmValor":
                            strQuery += " AND p.maeTipoPpmValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "esAltoCosto":
                            strQuery += " AND p.esAltoCosto = " + e.getValue() + " ";
                            break;
                        case "esCapitado":
                            strQuery += " AND p.esCapitado = " + e.getValue() + " ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += " AND p.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContributivo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += " AND p.sexoAplica = " + e.getValue() + " ";
                            break;
                        case "codigoIum":
                            strQuery += " AND p.codigoIum  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "edadDesde":
                            strQuery += " AND p.edadDesde = " + e.getValue() + " ";
                            break;
                        case "edadHasta":
                            strQuery += " AND p.edadHasta = " + e.getValue() + " ";
                            break;
                        case "esRegulado":
                            strQuery += " AND p.esRegulado = " + e.getValue() + " ";
                            break;
                        case "valorMaximoRegulado":
                            strQuery += " AND p.valorMaximoRegulado = " + e.getValue() + " ";
                            break;
                        case "valorReferenteMinimo":
                            strQuery += " AND p.valorReferenteMinimo = " + e.getValue() + " ";
                            break;
                        case "valorReferete":
                            strQuery += " AND p.valorReferete = " + e.getValue() + " ";
                            break;
                        case "codigoFinanciador":
                            strQuery += " AND p.codigoFinanciador  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idViejo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "maeEstadoRegistroSanitarioId":
                            strQuery += " AND p.maeEstadoRegistroSanitarioId  = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<MaMedicamento> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaMedicamento> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaMedicamentos p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maAgrupadorMedicamento.codigo":
                            strQuery += " AND p.maAgrupadoresMedicamentoId.codigo LIKE  '%" + e.getValue() + "%' ";
                            break;
                        case "maAgrupadorMedicamento.nombre":
                            strQuery += " AND p.maAgrupadoresMedicamentoId.nombre LIKE  '%" + e.getValue() + "%' ";
                            break;
                        case "cum":
                            strQuery += " AND p.cum LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionInvima":
                            strQuery += " AND p.descripcionInvima LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionEstandarizada":
                            strQuery += " AND p.descripcionEstandarizada LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionId":
                            strQuery += " AND p.maeConcentracionId = " + e.getValue() + " ";
                            break;
                        case "maeConcentracionCodigo":
                            strQuery += " AND p.maeConcentracionCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionValor":
                            strQuery += " AND p.maeConcentracionValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoId":
                            strQuery += " AND p.maePrincipioActivoId = " + e.getValue() + " ";
                            break;
                        case "maePrincipioActivoCodigo":
                            strQuery += " AND p.maePrincipioActivoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoValor":
                            strQuery += " AND p.maePrincipioActivoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaId":
                            strQuery += " AND p.maeFormaFarmaceuticaId = " + e.getValue() + " ";
                            break;
                        case "maeFormaFarmaceuticaCodigo":
                            strQuery += " AND p.maeFormaFarmaceuticaCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaValor":
                            strQuery += " AND p.maeFormaFarmaceuticaValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoPpmId":
                            strQuery += " AND p.maeTipoPpmId = " + e.getValue() + " ";
                            break;
                        case "maeTipoPpmCodigo":
                            strQuery += " AND p.maeTipoPpmCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoPpmValor":
                            strQuery += " AND p.maeTipoPpmValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "esAltoCosto":
                            strQuery += " AND p.esAltoCosto = " + e.getValue() + " ";
                            break;
                        case "esCapitado":
                            strQuery += " AND p.esCapitado = " + e.getValue() + " ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += " AND p.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContributivo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += " AND p.sexoAplica = " + e.getValue() + " ";
                            break;
                        case "codigoIum":
                            strQuery += " AND p.codigoIum  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "edadDesde":
                            strQuery += " AND p.edadDesde = " + e.getValue() + " ";
                            break;
                        case "edadHasta":
                            strQuery += " AND p.edadHasta = " + e.getValue() + " ";
                            break;
                        case "esRegulado":
                            strQuery += " AND p.esRegulado = " + e.getValue() + " ";
                            break;
                        case "valorMaximoRegulado":
                            strQuery += " AND p.valorMaximoRegulado = " + e.getValue() + " ";
                            break;
                        case "valorReferenteMinimo":
                            strQuery += " AND p.valorReferenteMinimo = " + e.getValue() + " ";
                            break;
                        case "valorReferete":
                            strQuery += " AND p.valorReferete = " + e.getValue() + " ";
                            break;
                        case "codigoFinanciador":
                            strQuery += " AND p.codigoFinanciador  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idViejo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "maeEstadoRegistroSanitarioId":
                            strQuery += " AND p.maeEstadoRegistroSanitarioId  = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "maAgrupadorMedicamento.codigo":
                        strQuery += "p.maAgrupadoresMedicamentoId.codigo "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "maAgrupadorMedicamento.nombre":
                        strQuery += "p.maAgrupadoresMedicamentoId.nombre "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaMedicamentos per : list) {
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
    public MaMedicamento consultar(int id) throws Exception {
        MaMedicamento objRes = null;
        try {
            objRes = castEntidadNegocioLargo((MaMedicamentos) getEntityManager().find(MaMedicamentos.class, id));
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
    public int insertar(MaMedicamento obj) throws Exception {
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
    public void actualizar(MaMedicamento obj) throws Exception {
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            MaMedicamentos medicamento = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE MaMedicamentos a SET ";
            strQuery += " a.cum = :cum ,";
            strQuery += " a.descripcionInvima = :descripcionInvima ,";
            strQuery += " a.descripcionEstandarizada = :descripcionEstandarizada ,";
            strQuery += " a.descripcionLargaEstandarizada = :descripcionLargaEstandarizada ,";
            strQuery += " a.maeCoberturaId = :maeCoberturaId ,";
            strQuery += " a.maeCoberturaCodigo = :maeCoberturaCodigo ,";
            strQuery += " a.maeCoberturaValor = :maeCoberturaValor ,";
            strQuery += " a.maeConcentracionId = :maeConcentracionId ,";
            strQuery += " a.maeConcentracionCodigo = :maeConcentracionCodigo ,";
            strQuery += " a.maeConcentracionValor = :maeConcentracionValor ,";
            strQuery += " a.maePrincipioActivoId = :maePrincipioActivoId ,";
            strQuery += " a.maePrincipioActivoCodigo = :maePrincipioActivoCodigo ,";
            strQuery += " a.maePrincipioActivoValor = :maePrincipioActivoValor ,";
            strQuery += " a.maeFormaFarmaceuticaId = :maeFormaFarmaceuticaId ,";
            strQuery += " a.maeFormaFarmaceuticaCodigo = :maeFormaFarmaceuticaCodigo ,";
            strQuery += " a.maeFormaFarmaceuticaValor = :maeFormaFarmaceuticaValor ,";
            strQuery += " a.maeTipoPpmId = :maeTipoPpmId ,";
            strQuery += " a.maeTipoPpmCodigo = :maeTipoPpmCodigo ,";
            strQuery += " a.maeTipoPpmValor = :maeTipoPpmValor ,";
            strQuery += " a.esAltoCosto = :esAltoCosto ,";
            strQuery += " a.esCapitado = :esCapitado ,";
            strQuery += " a.aplicaSubsidiado = :aplicaSubsidiado ,";
            strQuery += " a.aplicaContributivo = :aplicaContributivo ,";
            strQuery += " a.sexoAplica = :sexoAplica ,";
            strQuery += " a.codigoIum = :codigoIum ,";
            strQuery += " a.edadDesde = :edadDesde ,";
            strQuery += " a.edadHasta = :edadHasta ,";
            strQuery += " a.esRegulado = :esRegulado ,";
            strQuery += " a.valorMaximoRegulado = :valorMaximoRegulado ,";
            strQuery += " a.valorReferenteMinimo = :valorReferenteMinimo ,";
            strQuery += " a.valorReferete = :valorReferete ,";
            strQuery += " a.expediente = :expediente ,";
            strQuery += " a.nombreComercial = :nombreComercial ,";
            strQuery += " a.laboratorio = :laboratorio ,";
            strQuery += " a.registroSanitario = :registroSanitario ,";
            strQuery += " a.fechaExpedicion = :fechaExpedicion ,";
            strQuery += " a.fechaVencimiento = :fechaVencimiento ,";
            strQuery += " a.maeEstadoRegistroSanitarioId = :maeEstadoRegistroSanitarioId ,";
            strQuery += " a.maeEstadoRegistroSanitarioCodigo = :maeEstadoRegistroSanitarioCodigo ,";
            strQuery += " a.maeEstadoRegistroSanitarioValor = :maeEstadoRegistroSanitarioValor ,";
            strQuery += " a.cantidadCum = :cantidadCum ,";
            strQuery += " a.fechaActivo = :fechaActivo ,";
            strQuery += " a.fechaInactivo = :fechaInactivo ,";
            strQuery += " a.maeAtc1Id = :maeAtc1Id ,";
            strQuery += " a.maeAtc1Codigo = :maeAtc1Codigo ,";
            strQuery += " a.maeAtc1Valor = :maeAtc1Valor ,";
            strQuery += " a.maeAtc2Id = :maeAtc2Id ,";
            strQuery += " a.maeAtc2Codigo = :maeAtc2Codigo ,";
            strQuery += " a.maeAtc2Valor = :maeAtc2Valor ,";
            strQuery += " a.maeAtc3Id = :maeAtc3Id ,";
            strQuery += " a.maeAtc3Codigo = :maeAtc3Codigo ,";
            strQuery += " a.maeAtc3Valor = :maeAtc3Valor ,";
            strQuery += " a.normaRegulacion = :normaRegulacion ,";
            strQuery += " a.mce = :mce ,";
            strQuery += " a.monopolioEstado = :monopolioEstado ,";
            strQuery += " a.estrechoMargenTerapeutico = :estrechoMargenTerapeutico ,";
            strQuery += " a.aclaracion = :aclaracion ,";
            strQuery += " a.valorFraccion = :valorFraccion ,";
            strQuery += " a.valorPresentacionComercial = :valorPresentacionComercial ,";
            strQuery += " a.cantidad = :cantidad ,";
            strQuery += " a.diasTratamiento = :diasTratamiento ,";
            strQuery += " a.maeGrupoAnatomicoPpalId = :maeGrupoAnatomicoPpalId ,";
            strQuery += " a.maeGrupoAnatomicoPpalCodigo = :maeGrupoAnatomicoPpalCodigo ,";
            strQuery += " a.maeGrupoAnatomicoPpalValor = :maeGrupoAnatomicoPpalValor ,";
            strQuery += " a.maeGrupoTerapeuticoPpalId = :maeGrupoTerapeuticoPpalId ,";
            strQuery += " a.maeGrupoTerapeuticoPpalCodigo = :maeGrupoTerapeuticoPpalCodigo ,";
            strQuery += " a.maeGrupoTerapeuticoPpalValor = :maeGrupoTerapeuticoPpalValor ,";
            strQuery += " a.dci = :dci ,";
            strQuery += " a.descripcionDci = :descripcionDci ,";
            strQuery += " a.provenienteInvima = :provenienteInvima ,";
            strQuery += " a.agrupadorCondicionPbs = :agrupadorCondicionPbs ,";
            strQuery += " a.valorComercial = :valorComercial ,";
            strQuery += " a.listaUnirs = :listaUnirs ,";
            strQuery += " a.codigoSuficienciaUpc = :codigoSuficienciaUpc ,";
            strQuery += " a.cantidadConcentracionSufUpc = :cantidadConcentracionSufUpc ,";
            strQuery += " a.unidadConcentracionSufUpc = :unidadConcentracionSufUpc ,";
            strQuery += " a.unidadMedidaSuficiencia = :unidadMedidaSuficiencia ,";
            strQuery += " a.noPbsMenorValor = :noPbsMenorValor ,";
            strQuery += " a.muestraMedica = :muestraMedica ,";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.formaFarmaceutica = :formaFarmaceutica ,";
            
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //campo fechas
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "' ";
            //campos objetos
            if (medicamento.getMaAgrupadoresMedicamentoId()!= null) {
                strQuery += ", a.maAgrupadoresMedicamentoId.id = " + medicamento.getMaAgrupadoresMedicamentoId().getId() + " ";
            }
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(medicamento);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaMedicamento eliminar(int id) throws Exception {
        MaMedicamento obj = null;
        try {
            MaMedicamentos ent = getEntityManager().find(MaMedicamentos.class, id);
            if (ent != null) {
                obj = castEntidadNegocioLargo(ent);
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
    public List<MaMedicamento> consultarTodos() throws Exception {
        List<MaMedicamento> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaMedicamentos p "
                    + "ORDER BY p.id ";
            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaMedicamentos per : list) {
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

    public static MaMedicamento castEntidadNegocio(MaMedicamentos per) {
        MaMedicamento obj = new MaMedicamento();
        obj.setId(per.getId());        
        obj.setActivo(per.getActivo());
        obj.setCum(per.getCum());
        obj.setDescripcionInvima(per.getDescripcionInvima());
        obj.setDescripcionEstandarizada(per.getDescripcionEstandarizada());
        //2021-03-02 jyperez corrección campo Cobertura. Tener en cuenta que cambió
        obj.setMaeCoberturaId(per.getMaeCoberturaId());
        obj.setMaeCoberturaCodigo(per.getMaeCoberturaCodigo());
        obj.setMaeCoberturaValor(per.getMaeCoberturaValor());
        obj.setMaeConcentracionId(per.getMaeConcentracionId());
        obj.setMaeConcentracionCodigo(per.getMaeConcentracionCodigo());
        obj.setMaeConcentracionValor(per.getMaeConcentracionValor());
        obj.setMaePrincipioActivoId(per.getMaePrincipioActivoId());
        obj.setMaePrincipioActivoCodigo(per.getMaePrincipioActivoCodigo());
        obj.setMaePrincipioActivoValor(per.getMaePrincipioActivoValor());
        obj.setMaeFormaFarmaceuticaId(per.getMaeFormaFarmaceuticaId());
        obj.setMaeFormaFarmaceuticaCodigo(per.getMaeFormaFarmaceuticaCodigo());
        obj.setMaeFormaFarmaceuticaValor(per.getMaeFormaFarmaceuticaValor());
        obj.setMaeTipoPpmId(per.getMaeTipoPpmId());
        obj.setMaeTipoPpmCodigo(per.getMaeTipoPpmCodigo());
        obj.setMaeTipoPpmValor(per.getMaeTipoPpmValor());
        obj.setEsAltoCosto(per.getEsAltoCosto());
        obj.setEsCapitado(per.getEsCapitado());
        obj.setAplicaSubsidiado(per.getAplicaSubsidiado());
        obj.setAplicaContributivo(per.getAplicaContributivo());
        obj.setSexoAplica(per.getSexoAplica());
        obj.setCodigoIum(per.getCodigoIum());
        obj.setEdadDesde(per.getEdadDesde());
        obj.setEdadHasta(per.getEdadHasta());
        obj.setEsRegulado(per.getEsRegulado());
        obj.setValorMaximoRegulado(per.getValorMaximoRegulado());
        obj.setValorReferenteMinimo(per.getValorReferenteMinimo());
        obj.setValorReferete(per.getValorReferete());
        obj.setNoPbsMenorValor(per.getNoPbsMenorValor());
        //obj.setCodigoFinanciador(per.getCodigoFinanciador());
        //obj.setIdViejo(per.getIdViejo());
        //obj.setCobertura(per.getCobertura());
        //2021-06-28 jyperez Sprint 1 cambio campo estado registro sanitario por maestro
        obj.setMaeEstadoRegistroSanitarioId(per.getMaeEstadoRegistroSanitarioId());
        obj.setMaeEstadoRegistroSanitarioCodigo(per.getMaeEstadoRegistroSanitarioCodigo());
        obj.setMaeEstadoRegistroSanitarioValor(per.getMaeEstadoRegistroSanitarioValor());
        //2025-02-23 jyperez nuevo campo
        obj.setFormaFarmaceutica(per.getFormaFarmaceutica());
        if (per.getMaAgrupadoresMedicamentoId() != null) {
            obj.setMaAgrupadorMedicamento(new MaAgrupadorMedicamento(per.getMaAgrupadoresMedicamentoId().getId(), per.getMaAgrupadoresMedicamentoId().getCodigo(), per.getMaAgrupadoresMedicamentoId().getNombre()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }
    
    public static MaMedicamento castEntidadNegocioLargo(MaMedicamentos per) {
        MaMedicamento obj = new MaMedicamento();
        obj.setId(per.getId());        
        obj.setActivo(per.getActivo());
        obj.setCum(per.getCum());
        obj.setDescripcionInvima(per.getDescripcionInvima());
        obj.setDescripcionEstandarizada(per.getDescripcionEstandarizada());
        //2021-03-02 jyperez corrección campo Cobertura. Tener en cuenta que cambió
        obj.setMaeCoberturaId(per.getMaeCoberturaId());
        obj.setMaeCoberturaCodigo(per.getMaeCoberturaCodigo());
        obj.setMaeCoberturaValor(per.getMaeCoberturaValor());
        obj.setMaeConcentracionId(per.getMaeConcentracionId());
        obj.setMaeConcentracionCodigo(per.getMaeConcentracionCodigo());
        obj.setMaeConcentracionValor(per.getMaeConcentracionValor());
        obj.setMaePrincipioActivoId(per.getMaePrincipioActivoId());
        obj.setMaePrincipioActivoCodigo(per.getMaePrincipioActivoCodigo());
        obj.setMaePrincipioActivoValor(per.getMaePrincipioActivoValor());
        obj.setMaeFormaFarmaceuticaId(per.getMaeFormaFarmaceuticaId());
        obj.setMaeFormaFarmaceuticaCodigo(per.getMaeFormaFarmaceuticaCodigo());
        obj.setMaeFormaFarmaceuticaValor(per.getMaeFormaFarmaceuticaValor());
        obj.setMaeTipoPpmId(per.getMaeTipoPpmId());
        obj.setMaeTipoPpmCodigo(per.getMaeTipoPpmCodigo());
        obj.setMaeTipoPpmValor(per.getMaeTipoPpmValor());
        obj.setEsAltoCosto(per.getEsAltoCosto());
        obj.setEsCapitado(per.getEsCapitado());
        obj.setAplicaSubsidiado(per.getAplicaSubsidiado());
        obj.setAplicaContributivo(per.getAplicaContributivo());
        obj.setSexoAplica(per.getSexoAplica());
        obj.setCodigoIum(per.getCodigoIum());
        obj.setEdadDesde(per.getEdadDesde());
        obj.setEdadHasta(per.getEdadHasta());
        obj.setEsRegulado(per.getEsRegulado());
        obj.setValorMaximoRegulado(per.getValorMaximoRegulado());
        obj.setValorReferenteMinimo(per.getValorReferenteMinimo());
        obj.setValorReferete(per.getValorReferete());
        //obj.setCodigoFinanciador(per.getCodigoFinanciador());
        //obj.setIdViejo(per.getIdViejo());
        //obj.setCobertura(per.getCobertura());
        //2021-06-16 Sprint 1 nuevos campos
        obj.setDescripcionLargaEstandarizada(per.getDescripcionLargaEstandarizada());
        obj.setExpediente(per.getExpediente());
        obj.setNombreComercial(per.getNombreComercial());
        obj.setLaboratorio(per.getLaboratorio());
        obj.setRegistroSanitario(per.getRegistroSanitario());
        obj.setFechaExpedicion(per.getFechaExpedicion());
        obj.setFechaVencimiento(per.getFechaVencimiento());
        //obj.setEstadoRegistroSanitario(per.getEstadoRegistroSanitario());
        obj.setCantidadCum(per.getCantidadCum());
        obj.setFechaActivo(per.getFechaActivo());
        obj.setFechaInactivo(per.getFechaInactivo());
        obj.setMaeAtc1Id(per.getMaeAtc1Id());
        obj.setMaeAtc1Codigo(per.getMaeAtc1Codigo());
        obj.setMaeAtc1Valor(per.getMaeAtc1Valor());
        obj.setMaeAtc2Id(per.getMaeAtc2Id());
        obj.setMaeAtc2Codigo(per.getMaeAtc2Codigo());
        obj.setMaeAtc2Valor(per.getMaeAtc2Valor());
        obj.setMaeAtc3Id(per.getMaeAtc3Id());
        obj.setMaeAtc3Codigo(per.getMaeAtc3Codigo());
        obj.setMaeAtc3Valor(per.getMaeAtc3Valor());
        //obj.setViaAdministracion(per.getViaAdministracion());
        obj.setMce(per.getMce());
        obj.setMonopolioEstado(per.getMonopolioEstado());
        obj.setEstrechoMargenTerapeutico(per.getEstrechoMargenTerapeutico());
        obj.setAclaracion(per.getAclaracion());
        obj.setNormaRegulacion(per.getNormaRegulacion());
        obj.setValorFraccion(per.getValorFraccion());
        obj.setValorPresentacionComercial(per.getValorPresentacionComercial());
        obj.setCantidad(per.getCantidad());
        obj.setDiasTratamiento(per.getDiasTratamiento());
        obj.setMaeGrupoAnatomicoPpalId(per.getMaeGrupoAnatomicoPpalId());
        obj.setMaeGrupoAnatomicoPpalCodigo(per.getMaeGrupoAnatomicoPpalCodigo());
        obj.setMaeGrupoAnatomicoPpalValor(per.getMaeGrupoAnatomicoPpalValor());
        obj.setMaeGrupoTerapeuticoPpalId(per.getMaeGrupoTerapeuticoPpalId());
        obj.setMaeGrupoTerapeuticoPpalCodigo(per.getMaeGrupoTerapeuticoPpalCodigo());
        obj.setMaeGrupoTerapeuticoPpalValor(per.getMaeGrupoTerapeuticoPpalValor());
        //obj.setGrupoTerapeuticoPrincipal(per.getGrupoTerapeuticoPrincipal());
        obj.setDci(per.getDci());
        obj.setDescripcionDci(per.getDescripcionDci());
        obj.setProvenienteInvima(per.getProvenienteInvima());
        obj.setAgrupadorCondicionPbs(per.getAgrupadorCondicionPbs());
        obj.setValorComercial(per.getValorComercial());
        obj.setListaUnirs(per.getListaUnirs());
        obj.setCodigoSuficienciaUpc(per.getCodigoSuficienciaUpc());
        obj.setCantidadConcentracionSufUpc(per.getCantidadConcentracionSufUpc());
        obj.setUnidadConcentracionSufUpc(per.getUnidadConcentracionSufUpc());
        obj.setUnidadMedidaSuficiencia(per.getUnidadMedidaSuficiencia());
        obj.setNoPbsMenorValor(per.getNoPbsMenorValor());
        obj.setMuestraMedica(per.getMuestraMedica());
        //obj.setMaMedicamentoAdicionalCamposcol(per.getMaMedicamentoAdicionalCamposcol());
        //2021-06-28 jyperez Sprint 1 cambio campo estado registro sanitario por maestro
        obj.setMaeEstadoRegistroSanitarioId(per.getMaeEstadoRegistroSanitarioId());
        obj.setMaeEstadoRegistroSanitarioCodigo(per.getMaeEstadoRegistroSanitarioCodigo());
        obj.setMaeEstadoRegistroSanitarioValor(per.getMaeEstadoRegistroSanitarioValor());
        //2025-02-23 jyperez nuevo campo
        obj.setFormaFarmaceutica(per.getFormaFarmaceutica());
        if (per.getMaAgrupadoresMedicamentoId() != null) {
            obj.setMaAgrupadorMedicamento(new MaAgrupadorMedicamento(per.getMaAgrupadoresMedicamentoId().getId(), per.getMaAgrupadoresMedicamentoId().getCodigo(), per.getMaAgrupadoresMedicamentoId().getNombre()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    public static MaMedicamentos castNegocioEntidad(MaMedicamento obj) {
        MaMedicamentos per = new MaMedicamentos();
        per.setId(obj.getId());
        per.setActivo(obj.isActivo());
        per.setCum(obj.getCum());
        per.setDescripcionInvima(obj.getDescripcionInvima());
        per.setDescripcionEstandarizada(obj.getDescripcionEstandarizada().trim());
        //2021-03-02 jyperez corrección campo Cobertura. Tener en cuenta que cambió
        per.setMaeCoberturaId(obj.getMaeCoberturaId());
        per.setMaeCoberturaCodigo(obj.getMaeCoberturaCodigo());
        per.setMaeCoberturaValor(obj.getMaeCoberturaValor());
        per.setMaeConcentracionId(obj.getMaeConcentracionId());
        per.setMaeConcentracionCodigo(obj.getMaeConcentracionCodigo());
        per.setMaeConcentracionValor(obj.getMaeConcentracionValor());
        per.setMaePrincipioActivoId(obj.getMaePrincipioActivoId());
        per.setMaePrincipioActivoCodigo(obj.getMaePrincipioActivoCodigo());
        per.setMaePrincipioActivoValor(obj.getMaePrincipioActivoValor());
        per.setMaeFormaFarmaceuticaId(obj.getMaeFormaFarmaceuticaId());
        per.setMaeFormaFarmaceuticaCodigo(obj.getMaeFormaFarmaceuticaCodigo());
        per.setMaeFormaFarmaceuticaValor(obj.getMaeFormaFarmaceuticaValor());
        per.setMaeTipoPpmId(obj.getMaeTipoPpmId());
        per.setMaeTipoPpmCodigo(obj.getMaeTipoPpmCodigo());
        per.setMaeTipoPpmValor(obj.getMaeTipoPpmValor());
        per.setEsAltoCosto(obj.getEsAltoCosto() != null? obj.getEsAltoCosto(): false);
        per.setEsCapitado(obj.getEsCapitado() != null? obj.getEsCapitado(): false);
        per.setAplicaSubsidiado(obj.getAplicaSubsidiado() != null? obj.getAplicaSubsidiado(): false);
        per.setAplicaContributivo(obj.getAplicaContributivo() != null? obj.getAplicaContributivo(): false);
        per.setSexoAplica(obj.getSexoAplica());
        per.setCodigoIum(obj.getCodigoIum());
        per.setEdadDesde(obj.getEdadDesde());
        per.setEdadHasta(obj.getEdadHasta());
        per.setEsRegulado(obj.getEsRegulado() != null? obj.getEsRegulado(): false);
        per.setValorMaximoRegulado(obj.getValorMaximoRegulado());
        per.setValorReferenteMinimo(obj.getValorReferenteMinimo());
        per.setValorReferete(obj.getValorReferete());
        //per.setCodigoFinanciador(obj.getCodigoFinanciador());
        //per.setIdViejo(obj.getIdViejo());
        //per.setCobertura(obj.getCobertura());
        //2021-06-16 Sprint 1 nuevos campos
        per.setDescripcionLargaEstandarizada(obj.getDescripcionLargaEstandarizada());
        per.setExpediente(obj.getExpediente());
        per.setNombreComercial(obj.getNombreComercial());
        per.setLaboratorio(obj.getLaboratorio());
        per.setRegistroSanitario(obj.getRegistroSanitario());
        per.setFechaExpedicion(obj.getFechaExpedicion());
        per.setFechaVencimiento(obj.getFechaVencimiento());
        //per.setEstadoRegistroSanitario(obj.getEstadoRegistroSanitario());
        per.setCantidadCum(obj.getCantidadCum());
        per.setFechaActivo(obj.getFechaActivo());
        per.setFechaInactivo(obj.getFechaInactivo());
        per.setMaeAtc1Id(obj.getMaeAtc1Id());
        per.setMaeAtc1Codigo(obj.getMaeAtc1Codigo());
        per.setMaeAtc1Valor(obj.getMaeAtc1Valor());
        per.setMaeAtc2Id(obj.getMaeAtc2Id());
        per.setMaeAtc2Codigo(obj.getMaeAtc2Codigo());
        per.setMaeAtc2Valor(obj.getMaeAtc2Valor());
        per.setMaeAtc3Id(obj.getMaeAtc3Id());
        per.setMaeAtc3Codigo(obj.getMaeAtc3Codigo());
        per.setMaeAtc3Valor(obj.getMaeAtc3Valor());
        //per.setViaAdministracion(obj.getViaAdministracion());
        per.setMce(obj.getMce() != null? obj.getMce():false);
        per.setMonopolioEstado(obj.getMonopolioEstado() != null? obj.getMonopolioEstado(): false);
        per.setEstrechoMargenTerapeutico(obj.getEstrechoMargenTerapeutico() != null? obj.getEstrechoMargenTerapeutico(): false);
        per.setAclaracion(obj.getAclaracion());
        per.setNormaRegulacion(obj.getNormaRegulacion());
        per.setValorFraccion(obj.getValorFraccion());
        per.setValorPresentacionComercial(obj.getValorPresentacionComercial());
        per.setCantidad(obj.getCantidad());
        per.setDiasTratamiento(obj.getDiasTratamiento());
        per.setMaeGrupoAnatomicoPpalId(obj.getMaeGrupoAnatomicoPpalId());
        per.setMaeGrupoAnatomicoPpalCodigo(obj.getMaeGrupoAnatomicoPpalCodigo());
        per.setMaeGrupoAnatomicoPpalValor(obj.getMaeGrupoAnatomicoPpalValor());
        per.setMaeGrupoTerapeuticoPpalId(obj.getMaeGrupoTerapeuticoPpalId());
        per.setMaeGrupoTerapeuticoPpalCodigo(obj.getMaeGrupoTerapeuticoPpalCodigo());
        per.setMaeGrupoTerapeuticoPpalValor(obj.getMaeGrupoTerapeuticoPpalValor());
        //per.setGrupoTerapeuticoPrincipal(obj.getGrupoTerapeuticoPrincipal());
        per.setDci(obj.getDci());
        per.setDescripcionDci(obj.getDescripcionDci());
        per.setProvenienteInvima(obj.getProvenienteInvima());
        per.setAgrupadorCondicionPbs(obj.getAgrupadorCondicionPbs());
        per.setValorComercial(obj.getValorComercial());
        per.setListaUnirs(obj.getListaUnirs() != null? obj.getListaUnirs(): false);
        per.setCodigoSuficienciaUpc(obj.getCodigoSuficienciaUpc());
        per.setCantidadConcentracionSufUpc(obj.getCantidadConcentracionSufUpc());
        per.setUnidadConcentracionSufUpc(obj.getUnidadConcentracionSufUpc());
        per.setUnidadMedidaSuficiencia(obj.getUnidadMedidaSuficiencia());
        per.setNoPbsMenorValor(obj.getNoPbsMenorValor() != null? obj.getNoPbsMenorValor(): false);
        per.setMuestraMedica(obj.getMuestraMedica() != null? obj.getMuestraMedica(): false);
        //per.setMaMedicamentoAdicionalCamposcol(obj.getMaMedicamentoAdicionalCamposcol());
        //2021-06-28 jyperez Sprint 1 cambio campo estado registro sanitario por maestro
        per.setMaeEstadoRegistroSanitarioId(obj.getMaeEstadoRegistroSanitarioId());
        per.setMaeEstadoRegistroSanitarioCodigo(obj.getMaeEstadoRegistroSanitarioCodigo());
        per.setMaeEstadoRegistroSanitarioValor(obj.getMaeEstadoRegistroSanitarioValor());
        //2025-02-23 jyperez nuevo campo
        per.setFormaFarmaceutica(obj.getFormaFarmaceutica());
        if (obj.getMaAgrupadorMedicamento() != null) {
            per.setMaAgrupadoresMedicamentoId(new MaAgrupadoresMedicamento(obj.getMaAgrupadorMedicamento().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

    @Override
    public MaMedicamento consultarPorCodigoCum(String codigo) throws Exception {
        MaMedicamento objetoRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaMedicamentos p "
                    + "WHERE p.cum = '" + codigo +"'";
            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaMedicamentos per : list) {
                if (i == 0) {
                    objetoRes = castEntidadNegocio(per);
                    i++;
                }
            }
        } catch (NoResultException e) {
            objetoRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objetoRes;
    }
    
    @Override
    public List<MaMedicamento> consultarPorCodigoAgrupadorYEstadoRegSan(String codigo) throws Exception {
        List<MaMedicamento> resultList = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM MaMedicamentos p "
                    + "WHERE p.maAgrupadoresMedicamentoId.codigo = '" + codigo +"' "
                    + " AND p.maeEstadoRegistroSanitarioCodigo IN ('1','2','6','8') "
                    + " AND p.activo = 1 ";
            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaMedicamentos per : list) {
                    resultList.add(castEntidadNegocio(per));
                }
        } catch (NoResultException e) {
            resultList = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultList;
    }
    
    @Override
    public List<MaMedicamento> consultarPorAgrupadorIdYEstadoRegSan(int id) throws Exception {
        List<MaMedicamento> resultList = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM MaMedicamentos p "
                    + "WHERE p.maAgrupadoresMedicamentoId.id = " + id +" "
                    + " AND p.maeEstadoRegistroSanitarioCodigo IN ('1','2','6','8') "
                    + " AND p.activo = 1 ";
            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaMedicamentos per : list) {
                    resultList.add(castEntidadNegocio(per));
                }
        } catch (NoResultException e) {
            resultList = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultList;
    }
    
    @Override
    public List<MaMedicamento> consultarPorAgrupadorId(int id) throws Exception {
        List<MaMedicamento> resultList = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM MaMedicamentos p "
                    + " WHERE p.maAgrupadoresMedicamentoId.id = " + id 
                    + " AND p.activo = 1 ";
            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaMedicamentos per : list) {
                    resultList.add(castEntidadNegocio(per));
                }
        } catch (NoResultException e) {
            resultList = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultList;
    }
    
    @Override
    public List<MaMedicamento> consultarPorAgrupadorCodigo(String codigo) throws Exception {
        List<MaMedicamento> resultList = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM MaMedicamentos p "
                    + " WHERE p.maAgrupadoresMedicamentoId.codigo '" + codigo +"' "
                    + " AND p.activo = 1 ";
            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaMedicamentos per : list) {
                    resultList.add(castEntidadNegocio(per));
                }
        } catch (NoResultException e) {
            resultList = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return resultList;
    }

    @Override
    public int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaMedicamentos p WHERE p.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.activo = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.activo = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maAgrupadorMedicamento.codigo":
                            strQuery += " AND p.maAgrupadoresMedicamentoId.codigo LIKE  '%" + e.getValue() + "%' ";
                            break;
                        case "maAgrupadorMedicamento.nombre":
                            strQuery += " AND p.maAgrupadoresMedicamentoId.nombre LIKE  '%" + e.getValue() + "%' ";
                            break;
                        case "cum":
                            strQuery += " AND p.cum LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionInvima":
                            strQuery += " AND p.descripcionInvima LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionEstandarizada":
                            strQuery += " AND p.descripcionEstandarizada LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionId":
                            strQuery += " AND p.maeConcentracionId = " + e.getValue() + " ";
                            break;
                        case "maeConcentracionCodigo":
                            strQuery += " AND p.maeConcentracionCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionValor":
                            strQuery += " AND p.maeConcentracionValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoId":
                            strQuery += " AND p.maePrincipioActivoId = " + e.getValue() + " ";
                            break;
                        case "maePrincipioActivoCodigo":
                            strQuery += " AND p.maePrincipioActivoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoValor":
                            strQuery += " AND p.maePrincipioActivoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaId":
                            strQuery += " AND p.maeFormaFarmaceuticaId = " + e.getValue() + " ";
                            break;
                        case "maeFormaFarmaceuticaCodigo":
                            strQuery += " AND p.maeFormaFarmaceuticaCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaValor":
                            strQuery += " AND p.maeFormaFarmaceuticaValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoPpmId":
                            strQuery += " AND p.maeTipoPpmId = " + e.getValue() + " ";
                            break;
                        case "maeTipoPpmCodigo":
                            strQuery += " AND p.maeTipoPpmCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoPpmValor":
                            strQuery += " AND p.maeTipoPpmValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "esAltoCosto":
                            strQuery += " AND p.esAltoCosto = " + e.getValue() + " ";
                            break;
                        case "esCapitado":
                            strQuery += " AND p.esCapitado = " + e.getValue() + " ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += " AND p.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContributivo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += " AND p.sexoAplica = " + e.getValue() + " ";
                            break;
                        case "codigoIum":
                            strQuery += " AND p.codigoIum  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "edadDesde":
                            strQuery += " AND p.edadDesde = " + e.getValue() + " ";
                            break;
                        case "edadHasta":
                            strQuery += " AND p.edadHasta = " + e.getValue() + " ";
                            break;
                        case "esRegulado":
                            strQuery += " AND p.esRegulado = " + e.getValue() + " ";
                            break;
                        case "valorMaximoRegulado":
                            strQuery += " AND p.valorMaximoRegulado = " + e.getValue() + " ";
                            break;
                        case "valorReferenteMinimo":
                            strQuery += " AND p.valorReferenteMinimo = " + e.getValue() + " ";
                            break;
                        case "valorReferete":
                            strQuery += " AND p.valorReferete = " + e.getValue() + " ";
                            break;
                        case "codigoFinanciador":
                            strQuery += " AND p.codigoFinanciador  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idViejo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<MaMedicamento> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<MaMedicamento> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaMedicamentos p WHERE p.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.activo = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maAgrupadorMedicamento.codigo":
                            strQuery += " AND p.maAgrupadoresMedicamentoId.codigo LIKE  '%" + e.getValue() + "%' ";
                            break;
                        case "maAgrupadorMedicamento.nombre":
                            strQuery += " AND p.maAgrupadoresMedicamentoId.nombre LIKE  '%" + e.getValue() + "%' ";
                            break;
                        case "cum":
                            strQuery += " AND p.cum LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionInvima":
                            strQuery += " AND p.descripcionInvima LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "descripcionEstandarizada":
                            strQuery += " AND p.descripcionEstandarizada LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoId":
                            strQuery += " AND p.maeTipoId = " + e.getValue() + " ";
                            break;
                        case "maeTipoCodigo":
                            strQuery += " AND p.maeTipoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoValor":
                            strQuery += " AND p.maeTipoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionId":
                            strQuery += " AND p.maeConcentracionId = " + e.getValue() + " ";
                            break;
                        case "maeConcentracionCodigo":
                            strQuery += " AND p.maeConcentracionCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeConcentracionValor":
                            strQuery += " AND p.maeConcentracionValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoId":
                            strQuery += " AND p.maePrincipioActivoId = " + e.getValue() + " ";
                            break;
                        case "maePrincipioActivoCodigo":
                            strQuery += " AND p.maePrincipioActivoCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maePrincipioActivoValor":
                            strQuery += " AND p.maePrincipioActivoValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaId":
                            strQuery += " AND p.maeFormaFarmaceuticaId = " + e.getValue() + " ";
                            break;
                        case "maeFormaFarmaceuticaCodigo":
                            strQuery += " AND p.maeFormaFarmaceuticaCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeFormaFarmaceuticaValor":
                            strQuery += " AND p.maeFormaFarmaceuticaValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoPpmId":
                            strQuery += " AND p.maeTipoPpmId = " + e.getValue() + " ";
                            break;
                        case "maeTipoPpmCodigo":
                            strQuery += " AND p.maeTipoPpmCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoPpmValor":
                            strQuery += " AND p.maeTipoPpmValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "esAltoCosto":
                            strQuery += " AND p.esAltoCosto = " + e.getValue() + " ";
                            break;
                        case "esCapitado":
                            strQuery += " AND p.esCapitado = " + e.getValue() + " ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += " AND p.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContributivo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += " AND p.sexoAplica = " + e.getValue() + " ";
                            break;
                        case "codigoIum":
                            strQuery += " AND p.codigoIum  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "edadDesde":
                            strQuery += " AND p.edadDesde = " + e.getValue() + " ";
                            break;
                        case "edadHasta":
                            strQuery += " AND p.edadHasta = " + e.getValue() + " ";
                            break;
                        case "esRegulado":
                            strQuery += " AND p.esRegulado = " + e.getValue() + " ";
                            break;
                        case "valorMaximoRegulado":
                            strQuery += " AND p.valorMaximoRegulado = " + e.getValue() + " ";
                            break;
                        case "valorReferenteMinimo":
                            strQuery += " AND p.valorReferenteMinimo = " + e.getValue() + " ";
                            break;
                        case "valorReferete":
                            strQuery += " AND p.valorReferete = " + e.getValue() + " ";
                            break;
                        case "codigoFinanciador":
                            strQuery += " AND p.codigoFinanciador  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "idViejo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "maAgrupadorMedicamento.codigo":
                        strQuery += "p.maAgrupadoresMedicamentoId.codigo "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "maAgrupadorMedicamento.nombre":
                        strQuery += "p.maAgrupadoresMedicamentoId.nombre "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaMedicamentos per : list) {
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
    public HashMap<String, MaMedicamento> consultarHash() throws Exception {
        HashMap<String,MaMedicamento> hashResult = new HashMap();
        try {
            String strQuery = "FROM MaMedicamentos m "
                    + "ORDER BY m.id ";
            List<MaMedicamentos> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaMedicamentos per : list) {
                MaMedicamento obj = castEntidadNegocio(per);
                hashResult.put(obj.getCum(), obj);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }

    @Override
    public List<MaMedicamento> consultarTodoSingleton() throws Exception {
        List<MaMedicamento> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t.id, "
                    + "t.activo, "
                    + "t.cum, "
                    + "t.descripcionEstandarizada "
                    + "FROM MaMedicamentos t "
                    + "ORDER by t.id ";
            Query q = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = q.getResultList();
            for (Object[] per : lista) {
                MaMedicamento diagnostico = new MaMedicamento();
                diagnostico.setId((Integer) per[0]);
                diagnostico.setActivo((Boolean) per[1]);
                diagnostico.setCum(per[2].toString());
                diagnostico.setDescripcionEstandarizada(per[3].toString());
                listResult.add(diagnostico);
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
}
