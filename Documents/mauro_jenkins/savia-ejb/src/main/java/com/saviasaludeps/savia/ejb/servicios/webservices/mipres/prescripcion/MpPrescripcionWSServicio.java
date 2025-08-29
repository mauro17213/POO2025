package com.saviasaludeps.savia.ejb.servicios.webservices.mipres.prescripcion;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.mipres.MpAfiliado;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoUnirss;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoIndicacionUnirs;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoPrincipioActivo;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.entidades.MpAfiliados;
import com.saviasaludeps.savia.ejb.entidades.MpCodigoUnirs;
import com.saviasaludeps.savia.ejb.entidades.MpMedicamentoIndicacionesUnirs;
import com.saviasaludeps.savia.ejb.entidades.MpMedicamentoPrincipiosActivos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionHistoricos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionRecobrantes;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.negocio.webservices.mipres.prescripcion.MpPrescripcionWSRemoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
@Remote(MpPrescripcionWSRemoto.class)
public class MpPrescripcionWSServicio extends GenericoServicio implements MpPrescripcionWSRemoto {

    @Override
    public Map<String, AsegAfiliado> consultarListaAfiliados(
            List<String> listaAfiliadoPrescripcion) throws Exception {
        Map<String, AsegAfiliado> listaAfiliados = new HashMap<>();
        String strQuery = "select id, mae_tipo_documento_id,mae_tipo_documento_codigo, numero_documento,"
                + "primer_nombre, segundo_nombre, primer_apellido,segundo_apellido, "
                + "mae_genero_id, mae_genero_codigo, mae_genero_valor,"
                + "mae_estado_afiliacion_id, mae_estado_afiliacion_codigo, mae_estado_afiliacion_valor  "
                + "from aseg_afiliados WHERE (mae_tipo_documento_codigo, numero_documento) IN ";
        String whereIn = "(";
        for (String afiliado : listaAfiliadoPrescripcion) {
            whereIn += "('" + afiliado.substring(0, 2) + "',";
            whereIn += "'" + afiliado.substring(2) + "'),";
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ")";
        strQuery += whereIn;
        List<Object[]> listObjAfiliados = null;
        try {
            listObjAfiliados = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {
            listObjAfiliados = null;
        } finally {
            cerrarEntityManager();
        }
        if (listObjAfiliados != null) {
            for (Object[] obj : listObjAfiliados) {
                try {
                    AsegAfiliado afiliado = new AsegAfiliado();
                    afiliado.setId((Integer) (obj[0]));
                    afiliado.setMaeTipoDocumento((int) (obj[1]));
                    afiliado.setMaeTipoDocumentoCodigo((String) (obj[2]));
                    afiliado.setNumeroDocumento((String) (obj[3]));
                    afiliado.setPrimerNombre((String) (obj[4]));
                    afiliado.setSegundoNombre((String) (obj[5]));
                    afiliado.setPrimerApellido((String) (obj[6]));
                    afiliado.setSegundoApellido((String) (obj[7]));
                    afiliado.setMaeGeneroId((int) (obj[8]));
                    afiliado.setMaeGeneroCodigo((String) (obj[9]));
                    afiliado.setMaeGeneroValor((String) (obj[10]));
                    afiliado.setMaeEstadoAfiliacion((int) (obj[11]));
                    afiliado.setMaeEstadoCivilCodigo((String) (obj[12]));
                    afiliado.setMaeEstadoAfiliacionValor((String) (obj[13]));
                    listaAfiliados.put(afiliado.getMaeTipoDocumentoCodigo() + "||" + afiliado.getNumeroDocumento().trim(), afiliado);
                } catch (Exception ex) {
                    listaAfiliados = null;
                }
            }
        }
        return listaAfiliados;
    }

    @Override
    public MpAfiliado consultarMpAfiliado(
            String tipodocumento,
            String numeroDocumento) throws Exception {
        MpAfiliado afiliadoResult = null;
        try {
            String strQuery = "FROM MpAfiliados p "
                    + " WHERE p.maeTipoDocumentoCodigo = '" + tipodocumento
                    + "' AND p.numeroDocumento = '" + numeroDocumento + "' ";
            List<MpAfiliados> listObj = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (!listObj.isEmpty()) {
                for (MpAfiliados obj : listObj) {
                    return afiliadoResult = castNegocioEntidadMpAfiliado(obj);
                }
            }
        } catch (NoResultException e) {
            afiliadoResult = null;
        } catch (Exception e) {
            afiliadoResult = null;
        } finally {
            cerrarEntityManager();
        }
        return afiliadoResult;
    }

    @Override
    public int insertarMpAfiliado(MpAfiliado afiliado) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpAfiliados(afiliado)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpAfiliado castNegocioEntidadMpAfiliado(MpAfiliados per) {
        MpAfiliado obj = new MpAfiliado();
        obj.setId(per.getId());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        return obj;
    }

    public static MpAfiliados castEntidadNegocioMpAfiliados(MpAfiliado obj) {
        MpAfiliados per = new MpAfiliados();
        per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
        per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        per.setNumeroDocumento(obj.getNumeroDocumento());
        per.setPrimerNombre(obj.getPrimerNombre());
        per.setSegundoNombre(obj.getSegundoNombre());
        per.setPrimerApellido(obj.getPrimerApellido());
        per.setSegundoApellido(obj.getSegundoApellido());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        return per;
    }

    @Override
    public Map<String, CntProfesional> consultarListaProfesionales(
            List<String> listaProfesionalPrescripcion) throws Exception {
        Map<String, CntProfesional> listaProfesionales = new HashMap<>();
        try {
            String strQuery = "select id, mae_tipo_codumento_id, documento "
                    + "from cnt_profesionales "
                    + "WHERE (mae_tipo_documento_codigo, documento) IN ";
            String whereIn = "(";
            for (String profesional : listaProfesionalPrescripcion) {
                whereIn += "('" + profesional.substring(0, 2) + "',";
                whereIn += "'" + profesional.substring(2) + "'),";
            }
            //Remover ultima ,
            if (!whereIn.equals("(")) {
                whereIn = whereIn.substring(0, whereIn.length() - 1);
            }
            whereIn += ")";
            strQuery += whereIn;
            List<Object[]> listObjSedes = null;
            try {
                listObjSedes = getEntityManager().createNativeQuery(strQuery).getResultList();
            } catch (Exception ex) {
                listObjSedes = null;
            } finally {
                cerrarEntityManager();
            }
            for (Object[] obj : listObjSedes) {
                CntProfesional profesional = new CntProfesional();
                profesional.setId((Integer) (obj[0]));
                profesional.setMaeTipoCodumentoId((int) (obj[1]));
                profesional.setDocumento((String) (obj[2]));
                listaProfesionales.put(profesional.getDocumento(), profesional);
            }
        } catch (Exception ex) {
            throw new Exception("Error consultarListaProfesionales  - " + ex.getMessage());
        }
        return listaProfesionales;
    }

    @Override
    public Map<String, MpPrescripcion> consultarListaPrescripciones(
            List<String> listaNoPrescripciones) throws Exception {
        Map<String, MpPrescripcion> listaPrescripcionesLocales = new HashMap<>();
        String strQuery = "select id, numero_prescripcion "
                + "from mp_prescripciones "
                + "WHERE numero_prescripcion IN ";
        String whereIn = "(";
        for (String noPrescripcion : listaNoPrescripciones) {
            whereIn += "'" + noPrescripcion + "',";
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ")";
        strQuery += whereIn;
        List<Object[]> listObjPrescripciones = null;
        try {
            listObjPrescripciones = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {
            listObjPrescripciones = null;
        } finally {
            cerrarEntityManager();
        }
        for (Object[] obj : listObjPrescripciones) {
            MpPrescripcion prescripcion = new MpPrescripcion();
            prescripcion.setId((Integer) (obj[0]));
            prescripcion.setNumeroPrescripcion((String) (obj[1]));
            listaPrescripcionesLocales.put(
                    prescripcion.getNumeroPrescripcion(),
                    prescripcion);
        }
        return listaPrescripcionesLocales;
    }

    @Override
    public Map<String, CntPrestadorSede> consultarListaSedes(
            List<String> listaSedes) throws Exception {
        Map<String, CntPrestadorSede> listaSedesPr = new HashMap<>();
        String strQuery = "select id, codigo_habilitacion "
                + "from cnt_prestador_sedes "
                + "WHERE codigo_habilitacion IN ";
        String whereIn = "(";
        for (String sede : listaSedes) {
            whereIn += "'" + sede + "',";
        }
        //Remover ultima ,
        if (!whereIn.equals("(")) {
            whereIn = whereIn.substring(0, whereIn.length() - 1);
        }
        whereIn += ")";
        strQuery += whereIn;
        List<Object[]> listObjSedes = null;
        try {
            listObjSedes = getEntityManager().createNativeQuery(strQuery).getResultList();
        } catch (Exception ex) {

        } finally {
            cerrarEntityManager();
        }
        for (Object[] obj : listObjSedes) {
            CntPrestadorSede sede = new CntPrestadorSede();
            sede.setId((Integer) (obj[0]));
            sede.setCodigoHabilitacionSede((String) (obj[1]));
            listaSedesPr.put(sede.getCodigoHabilitacionSede(), sede);
        }
        return listaSedesPr;
    }

    @Override
    public int insertarMpPrescripcion(
            MpPrescripcion prescripcion) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpPrescripcion(prescripcion)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripciones castEntidadNegocioMpPrescripcion(MpPrescripcion obj) throws java.lang.Exception {
        MpPrescripciones per = new MpPrescripciones();
        try {
            //AsegAfiliado 
            if (obj.getAsegAfiliado() != null) {
                AsegAfiliados af = new AsegAfiliados();
                af.setId(obj.getAsegAfiliado().getId());
                per.setAsegAfiliadosId(af);
            }
            //MpAfiliado
            MpAfiliados mpAf = new MpAfiliados();
            mpAf.setId(obj.getMpAfiliado().getId());
            per.setMpAfiliadosId(mpAf);
            //Prestador
            if (obj.getMaTipoDocumentoPrestadorId() > 0) {
                per.setMaTipoDocumentoPrestadorId(obj.getMaTipoDocumentoPrestadorId());
                per.setMaTipoDocumentoPrestadorCodigo(obj.getMaTipoDocumentoPrestadorCodigo());
                per.setMaTipoDocumentoPrestadorValor(obj.getMaTipoDocumentoPrestadorValor());
                per.setPrestadorNumeroDocumento(obj.getPrestadorNumeroDocumento());
                per.setDireccionIpsPrescriptora(obj.getDireccionIpsPrescriptora());
                per.setTelefonoIpsPrescriptora(obj.getTelefonoIpsPrescriptora());
                per.setMunicipioIpsPrescriptora(obj.getMunicipioIpsPrescriptora());
                per.setPrestadorRazonSocial(obj.getPrestadorRazonSocial());
                per.setSedeCodigoHabilitacion(obj.getSedeCodigoHabilitacion());
            }
            //CntProfesional
            CntProfesionales prof = new CntProfesionales();
            prof.setId(obj.getCntProfesional().getId());
            per.setCntProfesionalesId(prof);
            //Diagnosticos
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                per.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
                per.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
                per.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                per.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
                per.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
                per.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                per.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
                per.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
                per.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
            //Prescripcion 
            per.setRecobrante(obj.getRecobrante());
            per.setNumeroPrescripcion(obj.getNumeroPrescripcion());
            per.setFechaPrescripcion(obj.getFechaPrescripcion());
            per.setHoraPrescripcion(obj.getHoraPrescripcion());
            per.setCodAmbAte(obj.getCodAmbAte());
            per.setReferenciaAmbitoAtencion(obj.getReferenciaAmbitoAtencion());
            per.setPacienteCovid19(obj.getPacienteCovid19());
            per.setEnfermedadHuerfana(obj.isEnfermedadHuerfana());
            per.setCodigoEnfermedadHuerfana(obj.getCodigoEnfermedadHuerfana());
            per.setSopNutricional(obj.getSopNutricional());
            per.setCodigoEps(obj.getCodigoEps());
            per.setAsegAfiliadoMadreTipoDocumento(obj.getAsegAfiliadoMadreTipoDocumento());
            per.setAsegAfiliadoMadreDocumento(obj.getAsegAfiliadoMadreDocumento());
            per.setTipoTransaccion(obj.getTipoTransaccion());
            per.setTipoDocumentoDonanteVivo(obj.getTipoDocumentoDonanteVivo());
            per.setEstado(obj.getEstado());
            per.setAfectaPresMax(obj.getAfectaPresMax());
            per.setCompradorHomologo(obj.getCompradorHomologo());
            per.setTranscripcion(obj.getTranscripcion());
            per.setDerechosVerificados(obj.getDerechosVerificados());
            per.setPortabilidad(obj.getPortabilidad());
            per.setMunicipioPortabilidad(obj.getMunicipioPortabilidad());
            per.setTransferidaPor(obj.getTransferidaPor());
            per.setActualizadaPor(obj.getActualizadaPor());
            per.setRequiereAnulacion(obj.getRequiereAnulacion());
            per.setNotaAuditoria(obj.getNotaAuditoria());
            //Auditoria
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en castEntidadNegocioMpPrescripcion - insertarMpPrescripcion" + ex.getMessage());
        }
        return per;
    }

    @Override
    public int insertarMpPrescripcionTecnologia(
            MpPrescripcionTecnologia tecnologia) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpPrescripcionTec(tecnologia)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionTecnologias castEntidadNegocioMpPrescripcionTec(
            MpPrescripcionTecnologia obj) throws java.lang.Exception {
        MpPrescripcionTecnologias per = new MpPrescripcionTecnologias();
        try {
            // prescripcion
            MpPrescripciones pre = new MpPrescripciones();
            pre.setId(obj.getMpPrescripcion().getId());
            per.setMpPrescripcionId(pre);
            //MaTecnologia
            per.setMaTecnologiaId(obj.getMaTecnologiaId());
            per.setMaTecnologiaValor(obj.getMaTecnologiaValor());
            per.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
            //tecnologia
            per.setEstado(obj.getEstado());
            per.setEstadoJuntaProfesionales(obj.getEstadoJuntaProfesionales());
            per.setConsecutivoOrden(obj.getConsecutivoOrden());
            per.setTipoTecnologia(obj.getTipoTecnologia());
            per.setTipoPrestacion(obj.getTipoPrestacion());
            per.setCausaSolicitud2(obj.getCausaSolicitud2());
            per.setCausaSolicitud3(obj.getCausaSolicitud3());
            per.setCausaSolicitud4(obj.getCausaSolicitud4());
            per.setCausaSolicitud5(obj.getCausaSolicitud5());
            per.setCausaSolicitud6(obj.getCausaSolicitud6());
            per.setCausaSolicitud7(obj.getCausaSolicitud7());
            per.setCausaSolicitud11(obj.getCausaSolicitud11());
            per.setCausaSolicitud12(obj.getCausaSolicitud12());
            per.setRazonCausaSolicitud51(obj.getRazonCausaSolicitud51());
            per.setDescripcionRazon51(obj.getDescripcionRazon51());
            per.setCodigoRazonCausa52(obj.getCodigoRazonCausa52());
            per.setDescripcionRazon52(obj.getDescripcionRazon52());
            per.setCantidadFormulada(obj.getCantidadFormulada());
            per.setCodigoUnidadTiempoFrecuenciaUso(obj.getCodigoUnidadTiempoFrecuenciaUso());
            per.setCantidadDuracionTratamiento(obj.getCantidadDuracionTratamiento());
            per.setCantidadTotal(obj.getCantidadTotal());
            per.setCantidadTotalEntrega(obj.getCantidadTotalEntrega());
            per.setPendientes(obj.getPendientes());
            per.setCodigoPeriodoDuracionTratamiento(obj.getCodigoPeriodoDuracionTratamiento());
            per.setJustificacionNoPbs(obj.getJustificacionNoPbs());
            per.setEntregados(obj.getEntregados());
            per.setBanderaAtencion(false);

            per.setFrecuenciaDeUso(obj.getFrecuenciaDeUso());
            per.setIndicacionesPaciente(obj.getIndicacionesPaciente());
            per.setAtendido(false);
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en  castEntidadNegocioMpPrescripcionTec - insertarMpPrescripcionTecnologia " + ex.getMessage());
        }
        return per;
    }

    @Override
    public int insertarMpPrescripcionMedicamento(
            MpPrescripcionMedicamento medicamento) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpPrescripcionMed(medicamento)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionMedicamentos castEntidadNegocioMpPrescripcionMed(MpPrescripcionMedicamento obj) throws java.lang.Exception {
        MpPrescripcionMedicamentos per = new MpPrescripcionMedicamentos();
        try {
            // prescripcion
            MpPrescripciones pre = new MpPrescripciones();
            pre.setId(obj.getMpPrescripcion().getId());
            per.setMpPrescripcionesId(pre);
            // medicamento
            per.setEstado(obj.getEstado());
            per.setEstadoJuntaProfesionales(obj.getEstadoJuntaProfesionales());
            per.setConsecutivoOrden(obj.getConsecutivoOrden());
            per.setTipoTecnologia(obj.getTipoTecnologia());
            per.setMedPbsUtilizado(obj.getMedPbsUtilizado());
            per.setTipoMedicamento(obj.getTipoMedicamento());
            per.setTipoPrestacion(obj.getTipoPrestacion());
            per.setCausaSolicitud1(obj.getCausaSolicitud1());
            per.setCausaSolicitud2(obj.getCausaSolicitud2());
            per.setCausaSolicitud3(obj.getCausaSolicitud3());
            per.setCausaSolicitud4(obj.getCausaSolicitud4());
            per.setCausaSolicitud5(obj.getCausaSolicitud5());
            per.setCausaSolicitud6(obj.getCausaSolicitud6());
            per.setMaeProductosNutricionalesId(obj.getMaeProductosNutricionalesId());
            per.setMaeProductosNutricionalesCodigo(obj.getMaeProductosNutricionalesCodigo());
            per.setMaeProductosNutricionalesValor(obj.getMaeProductosNutricionalesValor());
            per.setRazonCausaSolicitud31(obj.getRazonCausaSolicitud31());
            per.setDescripcionRazon31(obj.getDescripcionRazon31());
            per.setRazonCausaSolicitud32(obj.getRazonCausaSolicitud32());
            per.setDescripcionRazon32(obj.getDescripcionRazon32());
            per.setRazonCausaSolicitud41(obj.getRazonCausaSolicitud41());
            per.setDescripcionRazon41(obj.getDescripcionRazon41());
            per.setRazonCausaSolicitud42(obj.getRazonCausaSolicitud42());
            per.setDescripcionRazon42(obj.getDescripcionRazon42());
            per.setRazonCausaSolicitud43(obj.getRazonCausaSolicitud43());
            per.setDescripcionRazon43(obj.getDescripcionRazon43());
            per.setRazonCausaSolicitud44(obj.getRazonCausaSolicitud44());
            per.setDescripcionRazon44(obj.getDescripcionRazon44());
            per.setDescripcionMedicamentoPrincipioActivo(obj.getDescripcionMedicamentoPrincipioActivo());
            per.setCodigoFormulaFarmaceutica(obj.getCodigoFormulaFarmaceutica());
            per.setCodigoViaAdministracion(obj.getCodigoViaAdministracion());
            per.setJustificacionNoPbs(obj.getJustificacionNoPbs());
            per.setDosis(obj.getDosis());
            per.setDosisUnidadMedida(obj.getDosisUnidadMedida());
            per.setNumeroFrecuenciaAdministracion(obj.getNumeroFrecuenciaAdministracion());
            per.setCodigoFrecuenciaAdministracion(obj.getCodigoFrecuenciaAdministracion());
            per.setIndicacionesEspeciales(obj.getIndicacionesEspeciales());
            per.setCantidadTratamiento(obj.getCantidadTratamiento());
            per.setDuracionTratamiento(obj.getDuracionTratamiento());
            per.setCantidadTotalEntrega(obj.getCantidadTotalEntrega());
            per.setCantidadTotalFormulada(obj.getCantidadTotalFormulada());
            per.setPendientes(obj.getPendientes());
            per.setUnidadFarmaceuticaCantidadTotal(obj.getUnidadFarmaceuticaCantidadTotal());
            per.setIndicacionRecibida(obj.getIndicacionRecibida());
            per.setEntregados(obj.getEntregados());
            per.setBanderaAtencion(false);
            per.setAtendido(false);
            per.setTipoProductoNutricional(obj.getTipoProductoNutricional());
            per.setDescripcionProductoNutricional(obj.getDescripcionProductoNutricional());
            per.setCodFormulada(obj.getCodFormulada());
            //Auditoria
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en  castEntidadNegocioMpPrescripcionMed - insertarMpPrescripcionMedicamento " + ex.getMessage());
        }
        return per;
    }

    @Override
    public int insertarMpPrescripcionPrincAct(
            MpMedicamentoPrincipioActivo principio) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpMedicamentoPrincAct(principio)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpMedicamentoPrincipiosActivos castEntidadNegocioMpMedicamentoPrincAct(MpMedicamentoPrincipioActivo obj) throws java.lang.Exception {
        MpMedicamentoPrincipiosActivos per = new MpMedicamentoPrincipiosActivos();
        try {
            // MpMedicamento
            MpPrescripcionMedicamentos med = new MpPrescripcionMedicamentos();
            med.setId(obj.getMpPrescripcionMedicamentoId().getId());
            per.setMpPrescripcionMedicamentosId(med);
            // Princ Act
            per.setConsecutivoOrden(obj.getConsecutivoOrden());
            per.setCodigoPrincipioActivo(obj.getCodigoPrincipioActivo());
            per.setConcecutivoCantidad(obj.getConcecutivoCantidad());
            per.setUnidadMedidaConcentracion(obj.getUnidadMedidaConcentracion());
            per.setCantidadContenido(obj.getCantidadContenido());
            per.setUnidadCantidadContenido(obj.getUnidadCantidadContenido());
            // Auditoria
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en castEntidadNegocioMpMedicamentoPrincAct -insertarMpPrescripcionPrincAct " + ex.getMessage());
        }
        return per;
    }

    @Override
    public int insertarMpPrescripcionIndicacionUnirs(
            MpMedicamentoIndicacionUnirs unirs) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpMedicamentoIndicacionUnirs(unirs)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpMedicamentoIndicacionesUnirs castEntidadNegocioMpMedicamentoIndicacionUnirs(MpMedicamentoIndicacionUnirs obj) throws java.lang.Exception {
        MpMedicamentoIndicacionesUnirs per = new MpMedicamentoIndicacionesUnirs();
        try {
            // MpMedicamento
            MpPrescripcionMedicamentos med = new MpPrescripcionMedicamentos();
            med.setId(obj.getMpPrescripcionMedicamentoId().getId());
            per.setMpPrescripcionMedicamentosId(med);
            //Indicacion
            per.setConsecutivoOrden(obj.getConsecutivoOrden());
            per.setCodigoIndicacion(obj.getCodigoIndicacion());
            if (obj.getMpCodigoUnirsId() != null) {
                per.setMpCodigoUnirsId(new MpCodigoUnirs(obj.getMpCodigoUnirsId().getId()));
            } else {
                per.setMpCodigoUnirsId(null); 
            }
            //Auditoria
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en castEntidadNegocioMpMedicamentoIndicacionUnirs - insertarMpPrescripcionIndicacionUnirs " + ex.getMessage());
        }
        return per;
    }

    @Override
    public int insertarMpPrescripcionInsumo(
            MpPrescripcionInsumo insumo) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpPrescripcionInsumo(insumo)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionInsumos castEntidadNegocioMpPrescripcionInsumo(MpPrescripcionInsumo obj) throws java.lang.Exception {
        MpPrescripcionInsumos per = new MpPrescripcionInsumos();
        try {
            // MpPrescripcion
            MpPrescripciones pre = new MpPrescripciones();
            pre.setId(obj.getMpPrescripcion().getId());
            per.setMpPrescripcionId(pre);
            // Insumo
            per.setEstado(obj.getEstado());
            per.setEstadoJuntaProfesionales(obj.getEstadoJuntaProfesionales());
            per.setConsecutivoOrden(obj.getConsecutivoOrden());
            per.setTipoPrestacion(obj.getTipoPrestacion());
            per.setTipoTecnologia(obj.getTipoTecnologia());
            per.setCausaSolicitud1(obj.getCausaSolicitud1());
            per.setCodigoDispositivo(obj.getCodigoDispositivo());
            per.setMaeDispositivosId(obj.getMaeDispositivosId() != null
                    ? Integer.parseInt(obj.getMaeDispositivosId()) : 0);
            per.setMaeDispositivosCodigo(obj.getMaeDispositivosCodigo() != null
                    ? (obj.getMaeDispositivosCodigo()) : null);
            per.setMaedispositivosValor(obj.getMaeDispositivosNombre() != null
                    ? (obj.getMaeDispositivosNombre()) : null);
            per.setMaeServiciosComplementariosId(obj.getMaeServiciosComplementariosId() != null
                    ? Integer.parseInt(obj.getMaeServiciosComplementariosId()) : 0);
            per.setMaeServiciosComplementariosCodigo(obj.getMaeServiciosComplementariosCodigo() != null
                    ? (obj.getMaeServiciosComplementariosCodigo()) : null);
            per.setMaeServiciosComplementariosValor(obj.getMaeServiciosComplementariosNombre() != null
                    ? (obj.getMaeServiciosComplementariosNombre()) : null);
            per.setCantidad(obj.getCantidad());
            per.setCantidadTotalEntrega(obj.getCantidadTotalEntrega());
            per.setFrecuenciaUso(obj.getFrecuenciaUso());
            per.setCodFrecuenciaUso(obj.getCodFrecuenciaUso());
            per.setCantidadFormulada(obj.getCantidadFormulada());
            per.setPendiente(obj.getPendiente());
            per.setJustificacionNoPbs(obj.getJustificacionNoPbs());
            per.setIndicacionesRecomendaciones(obj.getIndicacionesRecomendaciones());
            per.setCodPerDurTrat(obj.getCodPerDurTrat());
            per.setEntregados(obj.getEntregados());
            per.setBanderaAtencion(false);
            per.setAtendido(false);
            per.setTipoTransporte(obj.getTipoTransporte());
            per.setReqAcom(obj.getReqAcom());
            per.setTipoDocumentoAcompananteAlbergue(obj.getTipoDocumentoAcompananteAlbergue());
            per.setNumeroDocumentoAcompanante(obj.getNumeroDocumentoAcompanante());
            per.setParentezcoAcompanante(obj.getParentezcoAcompanante());
            per.setCodigoMunicipioOrigenAlb(obj.getCodigoMunicipioOrigenAlb());
            per.setCodigoMunicipioDestinoAlb(obj.getCodigoMunicipioDestinoAlb());
            per.setParentezcoAcompanante(obj.getParentezcoAcompanante());
            per.setNombreAlb(obj.getNombreAlb());
            per.setDescripcionServicioComplementario(obj.getDescripcionServicioComplementario());
            // Auditoria
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en castEntidadNegocioMpPrescripcionInsumo - insertarMpPrescripcionInsumo " + ex.getMessage());
        }
        return per;
    }

    @Override
    public int insertarMpPrescripcionRecobrante(
            MpPrescripcionRecobrante prescripcion) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpPrescripcionRec(prescripcion)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionRecobrantes castEntidadNegocioMpPrescripcionRec(MpPrescripcionRecobrante obj) throws java.lang.Exception {
        MpPrescripcionRecobrantes per = new MpPrescripcionRecobrantes();
        try {
            // prescripcion
            MpPrescripciones pre = new MpPrescripciones();
            pre.setId(obj.getMpPrescripcionId().getId());
            per.setMpPrescripcionesId(pre);
            //Recobrante
            per.setFallo(obj.getFallo());
            per.setFechaFallo(obj.getFechaFallo());
            per.setFechaPrimeraInstancia(obj.getFechaPrimeraInstancia());
            per.setFechaSegundaInstancia(obj.getFechaSegundaInstancia());
            per.setFechaCorte(obj.getFechaCorte());
            per.setFechaDesacato(obj.getFechaDesacato());

            per.setMaDiagnosticoMotivaPrincipalId(obj.getMaDiagnosticoMotivaPrincipalId());
            per.setMaDiagnosticoMotivaPrincipalValor(obj.getMaDiagnosticoMotivaPrincipalValor());
            per.setMaDiagnosticoMotivaPrincipalCodigo(obj.getMaDiagnosticoMotivaPrincipalCodigo());

            per.setMaDiagnosticoMotiva2Id(obj.getMaDiagnosticoMotiva2Id());
            per.setMaDiagnosticoMotiva2Valor(obj.getMaDiagnosticoMotiva2Valor());
            per.setMaDiagnosticoMotiva2Codigo(obj.getMaDiagnosticoMotiva2Codigo());

            per.setMaDiagnosticoMotiva3Id(obj.getMaDiagnosticoMotiva3Id());
            per.setMaDiagnosticoMotiva3Valor(obj.getMaDiagnosticoMotiva3Valor());
            per.setMaDiagnosticoMotiva3Codigo(obj.getMaDiagnosticoMotiva3Codigo());

            per.setCriterio1Corte(obj.isCriterio1Corte());
            per.setCriterio2Corte(obj.isCriterio2Corte());
            per.setCriterio3Corte(obj.isCriterio3Corte());
            per.setCriterio4Corte(obj.isCriterio4Corte());
            per.setAclaracionFallo(obj.getAclaracionFallo());
            per.setJustificacionMedica(obj.getJustificacionMedica());
            //Auditoria
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en castEntidadNegocioMpPrescripcionRec -insertarMpPrescripcionRecobrante " + ex.getMessage());
        }
        return per;
    }

    @Override
    public int insertarMpPrescripcionHistorico(
            MpPrescripcionHistorico historico) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpPrescripcionHis(historico)).getId();

        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpPrescripcionHistoricos castEntidadNegocioMpPrescripcionHis(MpPrescripcionHistorico obj) throws java.lang.Exception {
        MpPrescripcionHistoricos per = new MpPrescripcionHistoricos();
        try {
            // prescripcion
            MpPrescripciones pre = new MpPrescripciones();
            pre.setId(obj.getMpPrescripcion().getId());
            per.setMpPrescripcionesId(pre);
            //Historico
            per.setIdPrescripcionTecnologia(obj.getIdPrescripcionTecnologia());
            per.setTipoTecnologia(obj.getTipoTecnologia());
            per.setEstado(obj.getEstado());
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en castEntidadNegocioMpPrescripcionHis - insertarMpPrescripcionHistorico" + ex.getMessage());
        }
        return per;
    }

    @Override
    public List<MpPrescripcion> consultarListaPrescripcionesPorAnular() throws Exception {
        List<MpPrescripcion> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM MpPrescripciones mp "
                    + "WHERE mp.requiereAnulacion = 1 "
                    + "AND mp.estado != 2 "
                    + " ORDER BY mp.id ";
            List<MpPrescripciones> list = getEntityManager()
                    .createQuery(strQuery).setMaxResults(5000)
                    .getResultList();
            for (MpPrescripciones per : list) {
                listResult.add(castMpPrescripcionId(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            throw new Exception("Error en consultarListaPrescripcionesPorAnular" + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    public static MpPrescripcion castMpPrescripcionId(MpPrescripciones per) {
        MpPrescripcion obj = new MpPrescripcion();
        obj.setId(per.getId());
        obj.setNumeroPrescripcion(per.getNumeroPrescripcion());
        return obj;
    }

    @Override
    public String consultarRazonSocial(String documento) throws Exception {
        String razon = "";
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT p.razonSocial FROM CntPrestadores p WHERE p.numeroDocumento = :documento");

            // Ejecuta la consulta y obtiene una lista de resultados
            List<String> resultados = getEntityManager()
                    .createQuery(strQuery.toString(), String.class)
                    .setParameter("documento", documento)
                    .getResultList();

            if (!resultados.isEmpty()) {
                razon = resultados.get(resultados.size() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al consultar la razón social", e);
        } finally {
            cerrarEntityManager();
        }
        return razon;
    }

    @Override
    public void gestionarDetalleMedicamento(String prescripcion, Integer consecutivoOrden, Integer tipoTecnologia, String dosisUM) throws Exception {
        Integer idDetalle = null;
        try {
            String strQuery = "SELECT m "
                    + " from MpPrescripcionMedicamentos m"
                    + " WHERE m.mpPrescripcionesId.numeroPrescripcion = :prescripcion "
                    + " AND m.consecutivoOrden = :consecutivoOrden "
                    + " AND m.tipoTecnologia = :tipoTecnologia ";
            MpPrescripcionMedicamentos obj = (MpPrescripcionMedicamentos) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("prescripcion", prescripcion)
                    .setParameter("consecutivoOrden", consecutivoOrden)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .getSingleResult();
            idDetalle = obj.getId();
            if (idDetalle != null) {
                String hql = "UPDATE MpPrescripcionMedicamentos SET "
                        + " dosisUnidadMedida = :dosisUM "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("id", idDetalle);
                query.setParameter("dosisUM", dosisUM);
                query.executeUpdate();
                getEntityManager().flush();
            }
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void gestionarDetalleNutri(String prescripcion, Integer consecutivoOrden, Integer tipoTecnologia, String dosisUM, String tippProNut, String descProdNutr, String codForma) throws Exception {
        Integer idDetalle = null;
        try {
            String strQuery = "SELECT m "
                    + " from MpPrescripcionMedicamentos m"
                    + " WHERE m.mpPrescripcionesId.numeroPrescripcion = :prescripcion "
                    + " AND m.consecutivoOrden = :consecutivoOrden "
                    + " AND m.tipoTecnologia = :tipoTecnologia ";
            MpPrescripcionMedicamentos obj = (MpPrescripcionMedicamentos) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("prescripcion", prescripcion)
                    .setParameter("consecutivoOrden", consecutivoOrden)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .getSingleResult();
            idDetalle = obj.getId();
            if (idDetalle != null) {
                String hql = "UPDATE MpPrescripcionMedicamentos SET "
                        + " dosisUnidadMedida = :dosisUM, "
                        + " tipoProductoNutricional = :tippProNut, "
                        + " descripcionProductoNutricional = :descProdNutr, "
                        + " codFormulada = :codForma "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("id", idDetalle);
                query.setParameter("dosisUM", dosisUM);
                query.setParameter("tippProNut", tippProNut);
                query.setParameter("descProdNutr", descProdNutr);
                query.setParameter("codForma", codForma);
                query.executeUpdate();
                getEntityManager().flush();
            }
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void gestionarDetalleComple(String prescripcion, Integer consecutivoOrden, Integer tipoTecnologia, String descSerComp,
            Integer codFreUso,
            Integer tipoTrans,
            Integer reqAcom,
            String tipoIDAcomAlb,
            String nroIDAcomAlb,
            Integer parentAcomAlb,
            String nombAlb,
            Integer codMunOriAlb,
            Integer codMunDesAlb
    ) throws Exception {
        Integer idDetalle = null;
        try {
            String strQuery = "SELECT i "
                    + " from MpPrescripcionInsumos i "
                    + " WHERE i.mpPrescripcionId.numeroPrescripcion = :prescripcion "
                    + " AND i.consecutivoOrden = :consecutivoOrden "
                    + " AND i.tipoTecnologia = :tipoTecnologia ";
            MpPrescripcionInsumos obj = (MpPrescripcionInsumos) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("prescripcion", prescripcion)
                    .setParameter("consecutivoOrden", consecutivoOrden)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .getSingleResult();
            idDetalle = obj.getId();
            if (idDetalle != null) {
                String hql = "UPDATE MpPrescripcionInsumos SET "
                        + " descripcionServicioComplementario = :descSerComp, "
                        //                        + " codigoServicioComplementario = :descSerComp, "
                        + " codFrecuenciaUso = :codFreUso, ";
                if (tipoTrans != null) {
                    hql += " tipoTransporte = :tipoTrans, ";
                }
                if (reqAcom != null) {
                    hql += " reqAcom = :reqAcom, ";
                }
                hql += " tipoDocumentoAcompananteAlbergue = :tipoIDAcomAlb, "
                        + " numeroDocumentoAcompanante = :nroIDAcomAlb, "
                        + " parentezcoAcompanante = :parentAcomAlb, ";
                if (codMunOriAlb != null) {
                    hql += " codigoMunicipioOrigenAlb = :codMunOriAlb, ";
                }
                if (codMunDesAlb != null) {
                    hql += " codigoMunicipioDestinoAlb = :codMunDesAlb, ";
                }
                hql += " nombreAlb = :nombAlb "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("id", idDetalle);
                query.setParameter("descSerComp", descSerComp);
                query.setParameter("codFreUso", codFreUso);
                if (tipoTrans != null) {
                    query.setParameter("tipoTrans", tipoTrans.toString());
                }
                if (reqAcom != null) {
                    query.setParameter("reqAcom", reqAcom.toString());
                }
                query.setParameter("tipoIDAcomAlb", tipoIDAcomAlb);
                query.setParameter("nroIDAcomAlb", nroIDAcomAlb);
                query.setParameter("parentAcomAlb", parentAcomAlb);
                query.setParameter("nombAlb", nombAlb);
                if (codMunOriAlb != null) {
                    query.setParameter("codMunOriAlb", codMunOriAlb.toString());
                }
                if (codMunDesAlb != null) {
                    query.setParameter("codMunDesAlb", codMunDesAlb.toString());
                }
                query.executeUpdate();
                getEntityManager().flush();
            }
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void gestionarDetalleProce(String prescripcion, Integer consecutivoOrden, Integer tipoTecnologia, String cadaFreUso, String indRec) throws Exception {
        Integer idDetalle = null;
        try {
            String strQuery = "SELECT t "
                    + " from MpPrescripcionTecnologias t"
                    + " WHERE t.mpPrescripcionId.numeroPrescripcion = :prescripcion "
                    + " AND t.consecutivoOrden = :consecutivoOrden "
                    + " AND t.tipoTecnologia = :tipoTecnologia ";
            MpPrescripcionTecnologias obj = (MpPrescripcionTecnologias) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("prescripcion", prescripcion)
                    .setParameter("consecutivoOrden", consecutivoOrden)
                    .setParameter("tipoTecnologia", tipoTecnologia)
                    .getSingleResult();
            idDetalle = obj.getId();
            if (idDetalle != null) {
                String hql = "UPDATE MpPrescripcionTecnologias SET "
                        + " frecuenciaDeUso = :cadaFreUso, "
                        + " indicacionesPaciente = :indRec "
                        + " WHERE id = :id";
                Query query = getEntityManager().createQuery(hql);
                query.setParameter("id", idDetalle);
                query.setParameter("cadaFreUso", Integer.parseInt(cadaFreUso));
                query.setParameter("indRec", indRec);
                query.executeUpdate();
                getEntityManager().flush();
            }
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MpCodigoUnirss consultarMpCodigoUnir(String tipo) throws Exception {
        MpCodigoUnirss unir = null;
        try {
            String strQuery = "FROM MpCodigoUnirs p WHERE p.codigo = :tipo";

            List<MpCodigoUnirs> resultados = getEntityManager()
                    .createQuery(strQuery, MpCodigoUnirs.class)
                    .setParameter("tipo", tipo)
                    .getResultList();

            return resultados.isEmpty() ? null : castCodigoUnirs(resultados.get(0));

        } catch (Exception e) {
            unir = null;
        } finally {
            cerrarEntityManager();
        }
        return unir;
    }

    public static MpCodigoUnirss castCodigoUnirs(MpCodigoUnirs per) {
        MpCodigoUnirss obj = new MpCodigoUnirss();
        obj.setId(per.getId());
        return obj;
    }

}
