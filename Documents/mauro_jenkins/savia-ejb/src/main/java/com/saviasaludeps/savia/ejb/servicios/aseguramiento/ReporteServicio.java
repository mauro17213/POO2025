/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegInforme;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegCartillaDerechoDeber;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegDigitacionesNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegMaestrosSubsidiado;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegNovedadSubsidiado;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegPortabilidad;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegTraslado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegInformes;
import com.saviasaludeps.savia.ejb.entidades.VAsegMaestroSubsidiado;
import com.saviasaludeps.savia.ejb.entidades.VAsegCartillaDerechosDeberes;
import com.saviasaludeps.savia.ejb.entidades.VAsegDigitacionNovedad;
import com.saviasaludeps.savia.ejb.entidades.VAsegNovedadesSubsidiado;
import com.saviasaludeps.savia.ejb.entidades.VAsegPortabilidades;
import com.saviasaludeps.savia.ejb.entidades.VAsegTraslados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.ReporteRemoto;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(ReporteRemoto.class)
public class ReporteServicio extends GenericoServicio implements ReporteRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegInformes p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " +  e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "fechaHoraFin":
                            strQuery += "AND p.fechaHoraFin = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<AsegInforme> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegInforme> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM AsegInformes p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipo = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND p.archivo LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " +  e.getValue() + " ";
                            break;
                        case "fechaHoraInicio":
                            strQuery += "AND p.fechaHoraInicio = '" + e.getValue().toString() + "' ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND p.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AsegInformes> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegInformes per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    public AsegInforme consultar(int id) throws Exception {
        AsegInforme objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegInformes) getEntityManager().find(AsegInformes.class, id));
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
    public int insertar(AsegInforme obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadLargo(obj)).getId();
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
    public void actualizar(AsegInforme obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidadLargo(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegInforme eliminar(int id) throws Exception {
        AsegInforme obj = null;
        try {
            AsegInformes ent = getEntityManager().find(AsegInformes.class, id);
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
    public List<AsegInforme> consultarTodos() throws Exception {
        List<AsegInforme> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegInformes p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegInformes> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegInformes per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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

    public static AsegInforme castEntidadNegocioLargo(AsegInformes per) {
        AsegInforme obj = new AsegInforme();
        obj.setId(per.getId());
        obj.setTipo(per.getTipo());
        obj.setEstado(per.getEstado());
        obj.setFechaDesde(per.getFechaDesde());
        obj.setFechaHasta(per.getFechaHasta());
        obj.setFechaHoraInicio(per.getFechaHoraInicio());
        obj.setFechaHoraFin(per.getFechaHoraFin());
        obj.setRegistros(per.getRegistros());
        obj.setRuta(per.getRuta());
        obj.setArchivo(per.getArchivo());
        obj.setObservacion(per.getObservacion());
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        //objetos

        return obj;
    }

    public static AsegInformes castNegocioEntidadLargo(AsegInforme obj) {
        AsegInformes per = new AsegInformes();
        per.setId(obj.getId());
        per.setTipo(obj.getTipo());
        per.setEstado(obj.getEstado());
        per.setFechaDesde(obj.getFechaDesde());
        per.setFechaHasta(obj.getFechaHasta());
        per.setFechaHoraInicio(obj.getFechaHoraInicio());
        per.setFechaHoraFin(obj.getFechaHoraFin());
        per.setRegistros(obj.getRegistros());
        per.setRuta(obj.getRuta());
        per.setArchivo(obj.getArchivo());
        per.setObservacion(obj.getObservacion());
        // auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        //objetos

        return per;
    }

    @Override
    public int consultarCantidadPorTipoYEstado(int tipo, int estado) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegInformes p "
                    + "WHERE p.tipo = " + tipo + " "
                    + "AND p.estado = "+ estado + " ";
            
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
    public List<VAsegMaestrosSubsidiado> consultarReporteAfiliadosNuevosMS(int idReporte) throws java.lang.Exception {
        List<VAsegMaestrosSubsidiado> listResult = new ArrayList();
        try {
            // 2020-08-24 jyperez INC 280 cambios Reportes
            String strQuery = "FROM VAsegMaestroSubsidiado p "
                    + "WHERE p.idReporte = " + idReporte;
            
            List<VAsegMaestroSubsidiado> list = getEntityManager().createQuery(strQuery).getResultList();
            for (VAsegMaestroSubsidiado reg : list) {
                listResult.add(castEntidadNegocioVAsegMaestroSubsidiado(reg));
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

    private VAsegMaestrosSubsidiado castEntidadNegocioVAsegMaestroSubsidiado(VAsegMaestroSubsidiado per) {
        VAsegMaestrosSubsidiado obj = new VAsegMaestrosSubsidiado();
        obj.setCodigoEps(per.getCodigoEps());
        obj.setTipoDocumento(per.getTipoDocumento());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setSexo(per.getSexo());
        obj.setCodigoDepartamento(per.getCodigoDepartamento());
        obj.setCodigoMunicipio(per.getCodigoMunicipio());
        obj.setZona(per.getZona());
        obj.setFechaAfiliacionEps(per.getFechaAfiliacionEps());
        obj.setGrupoPoblacional(per.getGrupoPoblacional());
        obj.setNivelSisben(per.getNivelSisben());
        obj.setCodigoHabilitacionSede(per.getCodigoHabilitacionSede());
        obj.setContratoAfiliado(per.getContratoAfiliado());
        obj.setEstadoAfiliacion(per.getEstadoAfiliacion());
        obj.setTipoDocumentoBdua(per.getTipoDocumentoBdua());
        obj.setNumeroDocumentoBdua(per.getNumeroDocumentoBdua());
        obj.setOrigenAfiliado(per.getOrigenAfiliado());
        //2022-02-24 jyperez nuevos campos
        obj.setMetodologiaGrupoPoblacional(per.getMetodologiaGrupoPoblacional());
        obj.setSubgrupoSisbenIv(per.getSubgrupoSisbenIv());
        obj.setCondicionBeneficiario(per.getCondicionBeneficiario());
        obj.setTipoDocumentoCabeza(per.getTipoDocumentoCabeza());
        obj.setNumeroIdentificacionCabeza(per.getNumeroIdentificacionCabeza());
        obj.setParentescoCabeza(per.getParentescoCabeza());
        obj.setTipoAfiliado(per.getTipoAfiliado());
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        // 2020-08-24 jyperez INC 280 cambios Reportes
        obj.setUsuarioCrea(per.getUsuarioCrea());
        //2020-09-15 jyperez cambios adicionales reporte
        obj.setIdReporte(per.getIdReporte());
        obj.setFechaMarcacion(per.getFechaMarcacion());
        return obj;
    }
    
    @Override
    public List<VAsegNovedadSubsidiado> consultarReporteNovedadesAfiliadoNS(int idReporte) throws java.lang.Exception {
        List<VAsegNovedadSubsidiado> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "FROM VAsegNovedadesSubsidiado p "
                    + "WHERE p.idReporte = " + idReporte;
            
            List<VAsegNovedadesSubsidiado> list = getEntityManager().createQuery(strQuery).getResultList();
            for (VAsegNovedadesSubsidiado reg : list) {
                listResult.add(castEntidadNegocioVAsegNovedadesSubsidiado(reg));
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

    private VAsegNovedadSubsidiado castEntidadNegocioVAsegNovedadesSubsidiado(VAsegNovedadesSubsidiado reg) {
        VAsegNovedadSubsidiado obj = new VAsegNovedadSubsidiado();
        obj.setId(reg.getId());
        obj.setCodigoEps(reg.getCodigoEps());
        obj.setTipoDocumento(reg.getTipoDocumento());
        obj.setNumeroDocumento(reg.getNumeroDocumento());
        obj.setPrimerApellido(reg.getPrimerApellido());
        obj.setSegundoApellido(reg.getSegundoApellido());
        obj.setPrimerNombre(reg.getPrimerNombre());
        obj.setSegundoNombre(reg.getSegundoNombre());
        obj.setFechaNacimiento(reg.getFechaNacimiento());
        obj.setCodigoDepartamento(reg.getCodigoDepartamento());
        obj.setCodigoMunicipio(reg.getCodigoMunicipio());
        obj.setCodigoNovedad(reg.getCodigoNovedad());
        obj.setFechaNovedad(reg.getFechaNovedad());
        obj.setValor1(reg.getValor1());
        obj.setValor2(reg.getValor2());
        obj.setValor3(reg.getValor3());
        obj.setValor4(reg.getValor4());
        obj.setValor5(reg.getValor5());
        obj.setValor6(reg.getValor6());
        obj.setValor7(reg.getValor7());
        obj.setContratoAfiliado(reg.getContratoAfiliado());
        obj.setEstadoAfiliacion(reg.getEstadoAfiliacion());
        obj.setCausaNovedad(reg.getCausaNovedad());
        // 2020-08-24 jyperez INC 280 cambios Reportes
        if (reg.getCodigoCausaNovedad() != null) {
            obj.setCodigoCausaNovedad(reg.getCodigoCausaNovedad());
        }
        // 2020-09-09 jyperez 
        obj.setIdReporte(reg.getIdReporte());
        obj.setFechaHoraReporte(reg.getFechaHoraReporte());
        obj.setFechaHoraCrea(reg.getFechaHoraCrea());
        obj.setUsuarioCrea(reg.getUsuarioCrea());
        //2022-02-24 jyperez nuevos campos
        obj.setMetodologiaGrupoPoblacional(reg.getMetodologiaGrupoPoblacional());
        obj.setSubgrupoSisbenIv(reg.getSubgrupoSisbenIv());
        obj.setCondicionBeneficiario(reg.getCondicionBeneficiario());
        obj.setTipoDocumentoCabeza(reg.getTipoDocumentoCabeza());
        obj.setNumeroIdentificacionCabeza(reg.getNumeroIdentificacionCabeza());
        obj.setParentescoCabeza(reg.getParentescoCabeza());
        obj.setTipoAfiliado(reg.getTipoAfiliado());
        return obj;
    }
    
    @Override
    public List<VAsegTraslado> consultarReporteTrasladoS1(int idReporte) throws java.lang.Exception {
        List<VAsegTraslado> listResult = new ArrayList();
        try {
            // 2020-08-24 jyperez INC 280 cambios Reportes
            String strQuery = "FROM VAsegTraslados p "
                    + " WHERE p.idReporte = " + idReporte;
            
            List<VAsegTraslados> list = getEntityManager().createQuery(strQuery).getResultList();
            for (VAsegTraslados reg : list) {
                listResult.add(castEntidadNegocioVAsegTraslados(reg));
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

    private VAsegTraslado castEntidadNegocioVAsegTraslados(VAsegTraslados reg) {
        VAsegTraslado obj = new VAsegTraslado();
        obj.setId(reg.getId());
        obj.setCodigoEntidad(reg.getCodigoEntidad());
        obj.setTipoDocumentoBdua(reg.getTipoDocumentoBdua());
        obj.setNumeroDocumentoBdua(reg.getNumeroDocumentoBdua());
        obj.setPrimerApellidoBdua(reg.getPrimerApellidoBdua());
        obj.setSegundoApellidoBdua(reg.getSegundoApellidoBdua());
        obj.setPrimerNombreBdua(reg.getPrimerNombreBdua());
        obj.setSegundoNombreBdua(reg.getSegundoNombreBdua());
        obj.setFechaNacimientoBdua(reg.getFechaNacimientoBdua());
        obj.setGeneroBdua(reg.getGeneroBdua());
        obj.setTipoDocumento(reg.getTipoDocumento());
        obj.setNumeroDocumento(reg.getNumeroDocumento());
        obj.setPrimerApellido(reg.getPrimerApellido());
        obj.setSegundoApellido(reg.getSegundoApellido());
        obj.setPrimerNombre(reg.getPrimerNombre());
        obj.setSegundoNombre(reg.getSegundoNombre());
        obj.setFechaNacimiento(reg.getFechaNacimiento());
        obj.setGenero(reg.getGenero());
        // 2020-08-24 jyperez INC 280 cambios Reportes
        obj.setCodigoDepartamentoAfiliacion(reg.getCodigoDepartamentoAfiliacion());
        obj.setCodigoMunicipioAfiliacion(reg.getCodigoMunicipioAfiliacion());
        obj.setZona(reg.getZona());
        obj.setFechaAfiliacionEps(reg.getFechaAfiliacionEps());
        obj.setCodigoGrupoPoblacional(reg.getCodigoGrupoPoblacional());
        obj.setNivelSisben(reg.getNivelSisben());
        obj.setTipoTraslado(reg.getTipoTraslado());
        obj.setEstadoAfiliado(reg.getEstadoAfiliado());
        obj.setFechaHoraCrea(reg.getFechaHoraCrea());
        // 2020-08-24 jyperez INC 280 cambios Reportes
        obj.setUsuarioCrea(reg.getUsuarioCrea());
        obj.setContratoAfiliado(reg.getContratoAfiliado());
        obj.setOrigenAfiliado(reg.getOrigenAfiliado());
        obj.setEpsAnterior(reg.getEpsAnterior());
        obj.setCodigoEpsAnterior(reg.getCodigoEpsAnterior());
        //2020-09-15 jyperez campos adicionales id Reporte
        obj.setIdReporte(reg.getIdReporte());
        obj.setFechaMarcacion(reg.getFechaMarcacion());
        //2022-02-24 jyperez nuevos campos
        obj.setMetodologiaGrupoPoblacional(reg.getMetodologiaGrupoPoblacional());
        obj.setSubgrupoSisbenIv(reg.getSubgrupoSisbenIv());
        obj.setCondicionBeneficiario(reg.getCondicionBeneficiario());
        obj.setTipoDocumentoCabeza(reg.getTipoDocumentoCabeza());
        obj.setNumeroIdentificacionCabeza(reg.getNumeroIdentificacionCabeza());
        obj.setParentescoCabeza(reg.getParentescoCabeza());
        obj.setTipoAfiliado(reg.getTipoAfiliado());
        return obj;
    }
    
    @Override
    public List<VAsegPortabilidad> consultarReportePortabilidad(Date fechaInicio, Date fechaFin) throws java.lang.Exception {
        List<VAsegPortabilidad> listResult = new ArrayList();
        int i = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM VAsegPortabilidades p "
                    + "WHERE p.fechaRadicacion >= '" + sdf.format(fechaInicio) + "' "
                    + "AND p.fechaRadicacion <= '" + sdf.format(fechaFin) + "' ";
            
            List<VAsegPortabilidades> list = getEntityManager().createQuery(strQuery).getResultList();
            for (VAsegPortabilidades reg : list) {
                listResult.add(castEntidadNegocioVAsegPortabilidades(reg));
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

    private VAsegPortabilidad castEntidadNegocioVAsegPortabilidades(VAsegPortabilidades reg) {
        VAsegPortabilidad obj = new VAsegPortabilidad();
        obj.setRadicadoPortabilidad(reg.getRadicadoPortabilidad());
        obj.setFechaRadicacion(reg.getFechaRadicacion());
        obj.setContratoAfiliado(reg.getContratoAfiliado());
        obj.setTipoDocumento(reg.getTipoDocumento());
        obj.setNumeroDocumento(reg.getNumeroDocumento());
        obj.setPrimerNombre(reg.getPrimerNombre());
        obj.setSegundoNombre(reg.getSegundoNombre());
        obj.setPrimerApellido(reg.getPrimerApellido());
        obj.setSegundoApellido(reg.getSegundoApellido());
        obj.setFechaNacimiento(reg.getFechaNacimiento());
        // 2020-08-25 jyperez INC 280 cambios Reportes
        obj.setCodigoDepartamentoAfiliacion(reg.getCodigoDepartamentoAfiliacion());
        obj.setCodigoMunicipioAfiliacion(reg.getCodigoMunicipioAfiliacion());
        obj.setDepartamentoAfiliacion(reg.getDepartamentoAfiliacion());
        obj.setMunicipioAfiliacion(reg.getMunicipioAfiliacion());
        obj.setDireccionResidencia(reg.getDireccionResidencia());
        obj.setTelefono1Afiliado(reg.getTelefono1Afiliado());
        obj.setTelefono2Afiliado(reg.getTelefono2Afiliado());
        obj.setTelefono3Portabilidad(reg.getTelefono3Portabilidad());
        obj.setTelefono4Portabilidad(reg.getTelefono4Portabilidad());
        obj.setCorreoElectronicoPortabilidad(reg.getCorreoElectronicoPortabilidad());
        obj.setGrupoPoblacional(reg.getGrupoPoblacional());
        obj.setEstadoPortabilidad(reg.getEstadoPortabilidad());
        obj.setIpsPrimariaSede(reg.getIpsPrimariaSede());
        // 2020-08-25 jyperez INC 280 cambios Reportes
        obj.setCodigoDepartamentoPortabilidad(reg.getCodigoDepartamentoPortabilidad());
        obj.setCodigoMunicipioPortabilidad(reg.getCodigoMunicipioPortabilidad());
        obj.setDepartamentoPortabilidad(reg.getDepartamentoPortabilidad());
        obj.setMunicipioPortabilidad(reg.getMunicipioPortabilidad());
        obj.setCodigoHabilitacionIpsPortabilidad(reg.getCodigoHabilitacionIpsPortabilidad());
        obj.setIpsPortabilidad(reg.getIpsPortabilidad());
        obj.setFichaSisben(reg.getFichaSisben());
        obj.setDescMigracion(reg.getDescMigracion());
        obj.setPeriodoInicial(reg.getPeriodoInicial());
        obj.setPeriodoFinal(reg.getPeriodoFinal());
        // 2020-08-24 jyperez INC 280 cambios Reportes
        obj.setDuracionPortabilidad(reg.getDuracionPortabilidad());
        obj.setUsuarioCrea(reg.getUsuarioCrea());
        obj.setObservacionAseguramiento(reg.getObservacionAseguramiento());
        // 2020-08-24 jyperez INC 280 cambios Reportes
        obj.setOrigenSolicitudPortabilidad(reg.getOrigenSolicitudPortabilidad());
        obj.setCantidadProrrogas(reg.getCantidadProrrogas());
        obj.setFechaProrroga(reg.getFechaProrroga());
        obj.setMesesAdicionProrroga(reg.getMesesAdicionProrroga());
        obj.setFechaCancelacion(reg.getFechaCancelacion());
        return obj;
    }
   
    @Override
    public List<VAsegDigitacionesNovedad> consultarReporteDigitacionUsuarios(Date fechaInicio, Date fechaFin) throws java.lang.Exception {
        List<VAsegDigitacionesNovedad> listResult = new ArrayList();
        int i = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM VAsegDigitacionNovedad p "
                    + "WHERE p.fechaDigitracion >= '" + sdf.format(fechaInicio) + "' "
                    + "AND p.fechaDigitracion <= '" + sdf.format(fechaFin) + "' ";
            
            List<VAsegDigitacionNovedad> list = getEntityManager().createQuery(strQuery).getResultList();
            for (VAsegDigitacionNovedad reg : list) {
                listResult.add(castEntidadNegocioVAsegDigitacionNovedad(reg));
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

    private VAsegDigitacionesNovedad castEntidadNegocioVAsegDigitacionNovedad(VAsegDigitacionNovedad reg) {
        VAsegDigitacionesNovedad obj = new VAsegDigitacionesNovedad();
        obj.setIdNovedad(reg.getIdNovedad());
        obj.setContratoAfiliado(reg.getContratoAfiliado());
        obj.setPrimerApellido(reg.getPrimerApellido());
        obj.setSegundoApellido(reg.getSegundoApellido());
        obj.setPrimerNombre(reg.getPrimerNombre());
        obj.setSegundoNombre(reg.getSegundoNombre());
        obj.setFechaNacimiento(reg.getFechaNacimiento());
        // 2020-08-24 jyperez INC 280 cambios Reportes
        obj.setCodigoDepartamentoAfiliacion(reg.getCodigoDepartamentoAfiliacion());
        obj.setCodigoMunicipioAfiliacion(reg.getCodigoMunicipioAfiliacion());
        obj.setDescripcionMunicipioAfiliacion(reg.getDescripcionMunicipioAfiliacion());
        obj.setRadicadoNovedad(reg.getRadicadoNovedad());
        obj.setCodigoNovedad(reg.getCodigoNovedad());
        obj.setDescripcionNovedad(reg.getDescripcionNovedad());
        obj.setFechaNovedad(reg.getFechaNovedad());
        obj.setValorNuevo(reg.getValorNuevo());
        obj.setValorAnterior(reg.getValorAnterior());
        obj.setUsuarioDigita(reg.getUsuarioDigita());
        obj.setFechaDigitacion(reg.getFechaDigitracion());
        return obj;
    }

    @Override
    public List<VAsegCartillaDerechoDeber> consultarReporteEncuestasAfiliados(Date fechaInicio, Date fechaFin) throws java.lang.Exception {
        List<VAsegCartillaDerechoDeber> listResult = new ArrayList();
        int i = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM VAsegCartillaDerechosDeberes p "
                    + "WHERE p.fechaHoraCrea >= '" + sdf.format(fechaInicio) + "' "
                    + "AND p.fechaHoraCrea <= '" + sdf.format(fechaFin) + "' ";
            
            List<VAsegCartillaDerechosDeberes> list = getEntityManager().createQuery(strQuery).getResultList();
            for (VAsegCartillaDerechosDeberes reg : list) {
                listResult.add(castEntidadNegocioVAsegCartillaDerechosDeberes(reg));
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

    private VAsegCartillaDerechoDeber castEntidadNegocioVAsegCartillaDerechosDeberes(VAsegCartillaDerechosDeberes reg) {
        VAsegCartillaDerechoDeber obj = new VAsegCartillaDerechoDeber();
        obj.setId(reg.getId());
        obj.setConsecutivoPregunta(reg.getConsecutivoPregunta());
        obj.setTipoDocumento(reg.getTipoDocumento());
        obj.setNumeroDocumento(reg.getNumeroDocumento());
        obj.setPrimerApellido(reg.getPrimerApellido());
        obj.setSegundoApellido(reg.getSegundoApellido());
        obj.setPrimerNombre(reg.getPrimerNombre());
        obj.setSegundoNombre(reg.getSegundoNombre());
        obj.setFechaNacimiento(reg.getFechaNacimiento());
        obj.setMunicipioAfiliacion(reg.getMunicipioAfiliacion());
        obj.setDireccionResidencia(reg.getDireccionResidencia());
        obj.setTelefonoFijo(reg.getTelefonoFijo());
        obj.setTelefonoMovil(reg.getTelefonoMovil());
        obj.setPregunta(reg.getPregunta());
        obj.setRespuesta(reg.getRespuesta());
        obj.setFechaHoraCrea(reg.getFechaHoraCrea());
        obj.setUsuarioCrea(reg.getUsuarioCrea());
        // 2020-08-24 jyperez INC 280 cambios Reportes
        obj.setContratoAfiliado(reg.getContratoAfiliado());
        obj.setCodigoDepartamentoAfiliacion(reg.getCodigoDepartamentoAfiliacion());
        obj.setCodigoMunicipioAfiliacion(reg.getCodigoMunicipioAfiliacion());
        return obj;
    }

    @Override
    public int consultarCantidadPorTipoYRangoFechas(int tipo, Date fechaDesde, Date fechaHasta) throws java.lang.Exception {
        int cantidad = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegInformes p "
                    + "WHERE p.fechaDesde = '" + sdf.format(fechaDesde) + "' "
                    + "AND p.fechaHasta = '" + sdf.format(fechaHasta) + "' "
                    + "AND p.tipo = " + tipo + " "
                    + "AND p.estado <> 3 "  ;// Reporte Rechazado
            
            cantidad = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getSingleResult();
            
        } catch (NoResultException e) {
            cantidad = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cantidad;
    }

//    @Override
//    public List<VAsegNovedad> consultarReporteNovedadesAseguramiento() throws java.lang.Exception {
//        List<VAsegNovedad> listResult = new ArrayList();
//        int i = 0;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            String strQuery = "FROM VAsegNovedades p "
//                    + "WHERE p.fechaHoraNotificacion IS NULL ";            
//            List<VAsegNovedades> list = getEntityManager().createQuery(strQuery).getResultList();
//            for (VAsegNovedades reg : list) {
//                listResult.add(castEntidadNegocioVAsegNovedades(reg));
//            }
//        } catch (NoResultException e) {
//            listResult = new ArrayList();
//        } catch (Exception e) {
//            Exception(CONSULTAR_TODOS, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return listResult;
//    }

//    private VAsegNovedad castEntidadNegocioVAsegNovedades(VAsegNovedades per) {
//        VAsegNovedad obj = new VAsegNovedad();
//        obj.setId(per.getId());
//        obj.setTipoDocumento(per.getTipoDocumento());
//        obj.setNumeroDocumento(per.getNumeroDocumento());
//        obj.setPrimerApellido(per.getPrimerApellido());
//        obj.setSegundoApellido(per.getSegundoApellido());
//        obj.setPrimerNombre(per.getPrimerNombre());
//        obj.setSegundoNombre(per.getSegundoNombre());
//        obj.setFechaNacimiento(per.getFechaNacimiento());
//        obj.setSexo(per.getSexo());
//        obj.setDepartamentoAfiliacion(per.getDepartamentoAfiliacion());
//        obj.setMunicipioAfiliacion(per.getMunicipioAfiliacion());
//        obj.setEstadoAfiliacion(per.getEstadoAfiliacion());
//        obj.setContratoAfiliado(per.getContratoAfiliado());
//        obj.setZona(per.getZona());
//        obj.setDireccionAfiliado(per.getDireccionAfiliado());
//        obj.setTelefono(per.getTelefono());
//        obj.setCelular(per.getCelular());
//        obj.setEmail(per.getEmail());
//        obj.setFechaExpedicionDocumento(per.getFechaExpedicionDocumento());
//        obj.setObservacion(per.getObservacion());
//        //auditoria
//        //obj.setFechaHoraCrea(per.getFechaHoraCrea());
//        obj.setFechaHoraNotificacion(per.getFechaHoraNotificacion());
//        //objetos
//
//        return obj;
//    }
}
