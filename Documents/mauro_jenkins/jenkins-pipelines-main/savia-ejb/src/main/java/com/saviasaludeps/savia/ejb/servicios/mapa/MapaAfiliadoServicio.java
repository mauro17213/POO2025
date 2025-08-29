/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mapa;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.mapa.MapaAfiliado;
import com.saviasaludeps.savia.dominio.mapa.MapaPrestador;
import com.saviasaludeps.savia.dominio.mapa.MapaPrestadorSede;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoDocumentos;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.text.SimpleDateFormat;
import com.saviasaludeps.savia.negocio.mapa.MapaAfiliadoRemoto;
import javax.persistence.Query;

/**
 *
 * @author rpalacic
 */
@Stateless
@Remote(MapaAfiliadoRemoto.class)
public class MapaAfiliadoServicio extends GenericoServicio implements MapaAfiliadoRemoto {

    @Override
    public List<MapaAfiliado> consultarListaMapaAfiliados(ParamConsulta paramConsulta) throws Exception {
        List<MapaAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + "WHERE p.id > 0 "
                    + "AND p.maeEstadoAfiliacionCodigo = '101' "
                    + "AND p.direccionGeoreferenciada = TRUE ";
            //Ubicación
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.residenciaUbicacionId.id = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            //Prestador sede - IPS Primaria
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND p.primariaCntPrestadorSedesId.id = " + (String) paramConsulta.getParametroConsulta2() + " ";
            }
            //Regimen
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND p.maeRegimenId = " + (String) paramConsulta.getParametroConsulta3() + " ";
            }
            //Genero
            if (paramConsulta.getParametroConsulta4() != null) {
                strQuery += "AND p.maeGeneroId = " + (String) paramConsulta.getParametroConsulta4() + " ";
            }
            //Modelo Liquidación
            if (paramConsulta.getParametroConsulta5() != null) {
                strQuery += "AND p.maeModeloLiquidacionId = " + (String) paramConsulta.getParametroConsulta5() + " ";
            }
            strQuery += "ORDER BY p.id DESC";
            List<AsegAfiliados> list = getEntityManager()
                    .createQuery(strQuery)
                    .setMaxResults(1000)
                    .getResultList();
            for (AsegAfiliados per : list) {
                MapaAfiliado af = castAfiliadoEntidadNegocio(per);
                listResult.add(af);
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
    public List<MapaAfiliado> consultarListaMapaAfiliados(double latitud, double longitud, int distancia) throws Exception {
        List<MapaAfiliado> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p "
                    + "FROM AsegAfiliados p "
                    + "WHERE p.id > 0 "
                    + "AND p.maeEstadoAfiliacionCodigo = '101' "
                    + "AND p.direccionGeoreferenciada = TRUE "
                    + "AND (6371 * ACOS( "
                    + "COS(RADIANS(" + latitud + ")) * COS(RADIANS(p.direccionGeorefLatitud)) * "
                    + "COS(RADIANS(p.direccionGeorefLongitud) - RADIANS(" + longitud + ")) + "
                    + "SIN(RADIANS(" + latitud + ")) * SIN(RADIANS(p.direccionGeorefLatitud)) "
                    + ")) < " + distancia + " ";
//                    + "ORDER BY p.distancia ASC";
            List<AsegAfiliados> list = getEntityManager()
                    .createQuery(strQuery)
                    .getResultList();
            for (AsegAfiliados per : list) {
                MapaAfiliado af = castAfiliadoEntidadNegocioCorto(per);
                listResult.add(af);
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

    private static MapaAfiliado castAfiliadoEntidadNegocio(AsegAfiliados per) {
        MapaAfiliado obj = new MapaAfiliado();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());
//        obj.setSerialBdua(per.getSerialBdua());
//        obj.setRegistroBdua(per.getRegistroBdua());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setFechaNacimiento(per.getFechaNacimiento());
//        obj.setGenero(per.getGenero());
        obj.setMaeGeneroId(per.getMaeGeneroId());
        obj.setMaeGeneroCodigo(per.getMaeGeneroCodigo());
        obj.setMaeGeneroValor(per.getMaeGeneroValor());
        //2023-06-15 jyperez N45 genero identificacion
        obj.setMaeGeneroIdentificacionId(per.getMaeGeneroIdentificacionId());
        obj.setMaeGeneroIdentificacionCodigo(per.getMaeGeneroIdentificacionCodigo());
        obj.setMaeGeneroIdentificacionValor(per.getMaeGeneroIdentificacionValor());
        obj.setFechaExpedicionCedula(per.getFechaExpedicionCedula());
        obj.setMaeTipoDocumento(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setMaeTipoDocumentoCf(per.getMaeTipoDocumentoCfId());
        obj.setMaeTipoDocumentoCfCodigo(per.getMaeTipoDocumentoCfCodigo());
        obj.setMaeTipoDocumentoCfValor(per.getMaeTipoDocumentoCfValor());
        obj.setNumeroDocumentoCf(per.getNumeroDocumentoCf());
        obj.setFechaAfiliacionSgsss(per.getFechaAfiliacionSgsss());
        obj.setFechaAfiliacionEps(per.getFechaAfiliacionEps());
        obj.setFechaEgresoEps(per.getFechaEgresoEps());
        obj.setFechaCambioEstadoEps(per.getFechaCambioEstadoEps());
        obj.setTipoBeneficiario(per.getTipoBeneficiario());
        obj.setMaeTipoAfiliadoId(per.getMaeTipoAfiliadoId());
        obj.setMaeTipoAfiliadoCodigo(per.getMaeTipoAfiliadoCodigo());
        obj.setMaeTipoAfiliadoValor(per.getMaeTipoAfiliadoValor());
        obj.setMaeEstadoAfiliacion(per.getMaeEstadoAfiliacionId());
        obj.setMaeEstadoAfiliacionCodigo(per.getMaeEstadoAfiliacionCodigo());
        obj.setMaeEstadoAfiliacionValor(per.getMaeEstadoAfiliacionValor());
        obj.setMaeOrigenAfiliado(per.getMaeOrigenAfiliadoId());
        obj.setMaeOrigenAfiliadoCodigo(per.getMaeOrigenAfiliadoCodigo());
        obj.setMaeOrigenAfiliadoValor(per.getMaeOrigenAfiliadoValor());
        obj.setParentescoCotizante(per.getParentescoCotizante());
        obj.setMaeParentescoCotizanteId(per.getMaeParentescoCotizanteId());
        obj.setMaeParentescoCotizanteCodigo(per.getMaeParentescoCotizanteCodigo());
        obj.setMaeParentescoCotizanteValor(per.getMaeParentescoCotizanteValor());
        obj.setAutorizaEnvioSms(per.getAutorizaEnvioSms());
        obj.setAutorizaEnvioEmail(per.getAutorizaEnvioEmail());
        obj.setTelefonoFijo(per.getTelefonoFijo());
        obj.setTelefonoMovil(per.getTelefonoMovil());
        obj.setEmail(per.getEmail());
        obj.setZona(per.getZona());
        obj.setMaeZonaId(per.getMaeZonaId());
        obj.setMaeZonaCodigo(per.getMaeZonaCodigo());
        obj.setMaeZonaValor(per.getMaeZonaValor());
        obj.setDireccionResidencia(per.getDireccionResidencia());

        obj.setDireccionGeoreferenciada(per.getDireccionGeoreferenciada());
        obj.setDireccionGeorefLongitud(per.getDireccionGeorefLongitud());
        obj.setDireccionGeorefLatitud(per.getDireccionGeorefLatitud());

        obj.setBarrio(per.getBarrio());
        obj.setRegimen(per.getRegimen());
        obj.setNivelSisben(per.getNivelSisben());
        //2021-05-20 jyperez nuevos campos
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        obj.setMaeNivelSisbenId(per.getMaeNivelSisbenId());
        obj.setMaeNivelSisbenCodigo(per.getMaeNivelSisbenCodigo());
        obj.setMaeNivelSisbenValor(per.getMaeNivelSisbenValor());
        if (per.getPuntajeSisben() != null) {
            obj.setPuntajeSisben(per.getPuntajeSisben().doubleValue());
        }
        obj.setFichaSisben(per.getFichaSisben());
        obj.setFechaSisben(per.getFechaSisben());
        obj.setCategoriaIbc(per.getCategoriaIbc());
        obj.setMaeCategoriaIbcId(per.getMaeCategoriaIbcId());
        obj.setMaeCategoriaIbcCodigo(per.getMaeCategoriaIbcCodigo());
        obj.setMaeCategoriaIbcValor(per.getMaeCategoriaIbcValor());
        obj.setTienePortabilidad(per.getTienePortabilidad());
        obj.setFechaPortabilidad(per.getFechaPortabilidad());
        obj.setTipoCotizante(per.getTipoCotizante());
        obj.setMaeTipoCotizanteId(per.getMaeTipoCotizanteId());
        obj.setMaeTipoCotizanteCodigo(per.getMaeTipoCotizanteCodigo());
        obj.setMaeTipoCotizanteValor(per.getMaeTipoCotizanteValor());
        obj.setDiscapacidad(per.getDiscapacidad());
        obj.setTipoDiscapacidad(per.getTipoDiscapacidad());
        obj.setMaeTipoDiscapacidadId(per.getMaeTipoDiscapacidadId());
        obj.setMaeTipoDiscapacidadCodigo(per.getMaeTipoDiscapacidadCodigo());
        obj.setMaeTipoDiscapacidadValor(per.getMaeTipoDiscapacidadValor());
        obj.setCondicionDiscapacidad(per.getCondicionDiscapacidad());
        obj.setMaeCondicionDiscapacidadId(per.getMaeDiscapacidadCondicionId());
        obj.setMaeCondicionDiscapacidadCodigo(per.getMaeDiscapacidadCondicionCodigo());
        obj.setMaeCondicionDiscapacidadValor(per.getMaeDiscapacidadCondicionValor());
        obj.setFechaIniciodiscapacidad(per.getFechaInicioDiscapacidad());
        obj.setFechaFinDiscapacidad(per.getFechaFinDiscapacidad());
        obj.setMaeGrupoPoblacional(per.getMaeGrupoPoblacionalId());
        obj.setMaeGrupoPoblacionalCodigo(per.getMaeGrupoPoblacionalCodigo());
        obj.setMaeGrupoPoblacionalValor(per.getMaeGrupoPoblacionalValor());
        obj.setVictima(per.getVictima());
        obj.setEtnia(per.getEtnia());
        obj.setMaeEtniaId(per.getMaeEtniaId());
        obj.setMaeEtniaCodigo(per.getMaeEtniaCodigo());
        obj.setMaeEtniaValor(per.getMaeEtniaValor());
        obj.setMaeComunidadEtniaId(per.getMaeComunidadEtniaId());
        obj.setMaeComunidadEtniaCodigo(per.getMaeComunidadEtniaCodigo());
        obj.setMaeComunidadEtniaValor(per.getMaeComunidadEtniaValor());
        obj.setMaeCausaNovedad(per.getMaeCausaNovedadId());
        obj.setMaeCausaNovedadCodigo(per.getMaeCausaNovedadCodigo());
        obj.setMaeCausaNovedadValor(per.getMaeCausaNovedadValor());
        obj.setFechaReactivacion(per.getFechaReactivacion());
        obj.setEstadoCivil(per.getEstadoCivil());
        obj.setMaeEstadoCivilId(per.getMaeEstadoCivilId());
        obj.setMaeEstadoCivilCodigo(per.getMaeEstadoCivilCodigo());
        obj.setMaeEstadoCivilValor(per.getMaeEstadoCivilValor());
        obj.setFechaMovilidad(per.getFechaMovilidad());
        obj.setModeloLiquidacion(per.getModeloLiquidacion());
        obj.setMaeModeloLiquidacionId(per.getMaeModeloLiquidacionId());
        obj.setMaeModeloLiquidacionCodigo(per.getMaeModeloLiquidacionCodigo());
        obj.setMaeModeloLiquidacionValor(per.getMaeModeloLiquidacionValor());
        obj.setMaeTipoDocumentoAportante(per.getMaeTipoDocumentoAportanteId());
        obj.setMaeTipoDocumentoAportanteCodigo(per.getMaeTipoDocumentoAportanteCodigo());
        obj.setMaeTipoDocumentoAportanteValor(per.getMaeTipoDocumentoAportanteValor());
        obj.setNumeroDocumentoAportante(per.getNumeroDocumentoAportante());
        obj.setMaeActividadEconomica(per.getMaeActividadEconomicaId());
        obj.setMaeActividadEconomicaCodigo(per.getMaeActividadEconomicaCodigo());
        obj.setMaeActividadEconomicaValor(per.getMaeActividadEconomicaValor());
        obj.setMaeArl(per.getMaeArlId());
        obj.setMaeArlCodigo(per.getMaeArlCodigo());
        obj.setMaeArlValor(per.getMaeArlValor());
        obj.setMaeAfp(per.getMaeAfpId());
        obj.setMaeAfpCodigo(per.getMaeAfpCodigo());
        obj.setMaeAfpValor(per.getMaeAfpValor());
        obj.setMaeCajaCompensacion(per.getMaeCajaCompensacionId());
        obj.setMaeCajaCompensacionCodigo(per.getMaeCajaCompensacionCodigo());
        obj.setMaeCajaCompensacionValor(per.getMaeCajaCompensacionValor());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setObservacionNovedad(per.getObservacion());
        obj.setSincronizado(per.getSincronizado());
        //2021-05-03 jyperez nuevo campo
        obj.setMaeGrupoSisbenId(per.getMaeGrupoSisbenId());
        obj.setMaeGrupoSisbenCodigo(per.getMaeGrupoSisbenCodigo());
        obj.setMaeGrupoSisbenValor(per.getMaeGrupoSisbenValor());
        //2022-04-25 jyperez nuevos campos
        obj.setMaeSubGrupoSisbenId(per.getMaeSubGrupoSisbenId());
        obj.setMaeSubGrupoSisbenCodigo(per.getMaeSubGrupoSisbenCodigo());
        obj.setMaeSubGrupoSisbenValor(per.getMaeSubGrupoSisbenValor());
        //2021-05-11 jyperez nuevo campo para el soundex
        obj.setCodigoFonetico(per.getCodigoFonetico());
        obj.setOrigenUltimoRegistro(per.getOrigenUltimoRegistro());
        //2022-02-21 jyperez nuevo campo resolución 2153 de 2021 de la Adres
        obj.setMaeMetodologiaGrupoPoblacionalId(per.getMaeMetodologiaGrupoPoblacionalId());
        obj.setMaeMetodologiaGrupoPoblacionalCodigo(per.getMaeMetodologiaGrupoPoblacionalCodigo());
        obj.setMaeMetodologiaGrupoPoblacionalValor(per.getMaeMetodologiaGrupoPoblacionalValor());
        //2022-06-09 jyperez nuevos campos Decreto 616 y Resolución 925 de 2022
        obj.setMaeSolidariaPorcentajeId(per.getMaeSolidariaPorcentajeId());
        obj.setMaeSolidariaPorcentajeCodigo(per.getMaeSolidariaPorcentajeCodigo());
        obj.setMaeSolidariaPorcentajeValor(per.getMaeSolidariaPorcentajeValor());
        //2022-07-21 jyperez nuevo campo
        obj.setAceptaContribucionSolidaria(per.getAceptaContribucionSolidaria());
        //2022-10-31 jyperez nuevo campo
        obj.setFechaSuspension(per.getFechaSuspensionEps());
        //2022-11-25 jyperez nuevos campos
        obj.setTratamientoDatosAutoriza(per.getTratamientoDatosAutoriza());
        obj.setTratamientoDatosFechaHora(per.getTratamientDatosFechaHora());
        //2023-06-15 jyperez N48 acuerdo de pago por suspensión X mora
        obj.setAcuerdoPago(per.getAcuerdoPago());
        obj.setFechaAcuerdoPago(per.getFechaAcuerdoPago());
        //2023-07-25 jyperez nuevo campo
        obj.setIncapacidadProlongada(per.getIncapacidadProlongada());
        //2023-08-16 jyperez N47 madre gestante
        obj.setUsuarioGestante(per.getUsuarioGestante());
        obj.setFechaFinPeriodoGestacion(per.getFechaFinPeriodoGestacion());
        //2024-01-10 jyperez Is 58 retiro por duplicado
        //2024-02-29 jyperez se realiza ajuste para actualizar el valor duplicado de nulo a cero
        //2024-03-05 rpalacic Corrección de forma para evitar dato nulo
        obj.setDuplicado((per.getDuplicado() == null) ? false : per.getDuplicado());
        //2024-03-13 jyperez N00-42 traslado preaprobado
        obj.setTrasladoPreaprobado((per.getTrasladoPreaprobado() == null) ? false : per.getTrasladoPreaprobado());
        //2024-03-22 jyperez RES 2335
        obj.setDireccionAlternaContacto(per.getDireccionAlternaContacto());
        obj.setNombreContactoEmergencia(per.getNombreContactoEmergencia());
        obj.setTelefonoContactoEmergencia(per.getTelefonoContactoEmergencia());
        //2024-09-23 jyperez nuevos campos portabilidad
        obj.setMaeTipoPortabilidadId(per.getMaeTipoPortabilidadId());
        obj.setMaeTipoPortabilidadCodigo(per.getMaeTipoPortabilidadCodigo());
        obj.setMaeTipoPortabilidadValor(per.getMaeTipoPortabilidadValor());
        obj.setMaeMotivoPortabilidadId(per.getMaeMotivoPortabilidadId());
        obj.setMaeMotivoPortabilidadCodigo(per.getMaeMotivoPortabilidadCodigo());
        obj.setMaeMotivoPortabilidadValor(per.getMaeMotivoPortabilidadValor());
        obj.setMaeOrigenSolicitudPortabilidadId(per.getMaeOrigenSolicitudPortabilidadId());
        obj.setMaeOrigenSolicitudPortabilidadCodigo(per.getMaeOrigenSolicitudPortabilidadCodigo());
        obj.setMaeOrigenSolicitudPortabilidadValor(per.getMaeOrigenSolicitudPortabilidadValor());
        obj.setPeriodoInicialPortabilidad(per.getPeriodoInicialPortabilidad());
        obj.setPeriodoFinalPortabilidad(per.getPeriodoFinalPortabilidad());
        obj.setTelefonoContactoPortabilidad(per.getTelefonoContactoPortabilidad());
        obj.setDireccionPortabilidad(per.getDireccionPortabilidad());
        obj.setCorreoElectronicoPortabilidad(per.getCorreoElectronicoPortabilidad());
        obj.setObservacionPortabilidad(per.getObservacionPortabilidad());
//        if (per.getPrimariaCntPrestadorSedesId() != null) {
//            obj.setPrimariaPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPrimariaCntPrestadorSedesId()));
//        }
//        if (per.getPortabilidadCntPrestadorSedesId() != null) {
//            obj.setPortabilidadPrestadorSede(castPrestadorSedesEntidadNegocio(per.getPortabilidadCntPrestadorSedesId()));
//        }
//        if (per.getNacionalidadUbicacionesId() != null) {
//            obj.setNacionalidadUbicacion(castUbicacionEntidadNegocio(per.getNacionalidadUbicacionesId()));
//        }
//        if (per.getNacimientoUbicacionesId() != null) {
//            obj.setNacimientoUbicacion(castUbicacionEntidadNegocio(per.getNacimientoUbicacionesId()));
//        }
//        if (per.getAfiliacionUbicacionesId() != null) {
//            obj.setAfiliacionUbicacion(castUbicacionEntidadNegocio(per.getAfiliacionUbicacionesId()));
//        }
//        if (per.getResidenciaUbicacionId() != null) {
//            obj.setResidenciaUbicacion(castUbicacionEntidadNegocio(per.getResidenciaUbicacionId()));
//        }
//        //2023-06-15 jyperez N46 nacimiento ubicacion
//        if (per.getGnUbicacionesLugarNacimientoId() != null) {
//            obj.setGnUbicacionesLugarNacimientoId(castUbicacionEntidadNegocio(per.getGnUbicacionesLugarNacimientoId()));
//        }
        //2021-05-27 jyperez adicionamos cast lista de Contactos
        if (per.getAsegAfiliadoContactosList() != null) {
            List<AsegAfiliadoContacto> listaContactos = new ArrayList<>();
            List<AsegAfiliadoContactoConsulta> listaContactosconsulta = new ArrayList<>();
//            for (AsegAfiliadoContactos contacto : per.getAsegAfiliadoContactosList()) {
//                listaContactos.add(castAfiliadoContactoEntidadNegocio(contacto));
//                listaContactosconsulta.add(castEntidadNegocioAfiliado(contacto));
//            }
            obj.setListaAsegAfiliadoContacto(listaContactos);
            obj.setListaContactoAfiliado(listaContactosconsulta);
//            per.getAsegAfiliadoContactosList().forEach(contacto -> {
//                listaContactos.add(castAfiliadoContactoEntidadNegocio(contacto));
//            });
//            obj.setListaAsegAfiliadoContacto(listaContactos);
        }
        //2022-09-08 jyperez se adiciona funcionalidad para leer el histórico de documentos del afiliado
        if (per.getAsegAfiliadoDocumentosList() != null) {
            String dato = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (AsegAfiliadoDocumentos ad : per.getAsegAfiliadoDocumentosList()) {
                dato = dato + ad.getMaeTipoDocumentoCodigo() + " " + ad.getNumeroDocumento() + " ";
                if (ad.getFechaExpedicion() != null) {
                    dato = dato + sdf.format(ad.getFechaExpedicion()) + " \n";
                } else {
                    dato = dato + "\n";
                }
            }
            obj.setHistoricoDocumentoAfiliado(dato);
        }
        //2023-02-02 jyperez FALLO PASO A PDN 02-02-2023 ajuste isaac
        //obj.setResidenciaUbicacion(new Ubicacion(per.getResidenciaUbicacionId().getId()));
        return obj;
    }

    private static MapaAfiliado castAfiliadoEntidadNegocioCorto(AsegAfiliados per) {
        MapaAfiliado obj = new MapaAfiliado();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setMaeGeneroId(per.getMaeGeneroId());
        obj.setMaeGeneroCodigo(per.getMaeGeneroCodigo());
        obj.setMaeGeneroValor(per.getMaeGeneroValor());
        obj.setMaeTipoDocumento(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setZona(per.getZona());
        obj.setMaeZonaId(per.getMaeZonaId());
        obj.setMaeZonaCodigo(per.getMaeZonaCodigo());
        obj.setMaeZonaValor(per.getMaeZonaValor());
        obj.setDireccionResidencia(per.getDireccionResidencia());
        obj.setDireccionGeoreferenciada(per.getDireccionGeoreferenciada());
        obj.setDireccionGeorefLongitud(per.getDireccionGeorefLongitud());
        obj.setDireccionGeorefLatitud(per.getDireccionGeorefLatitud());
        obj.setBarrio(per.getBarrio());
        obj.setRegimen(per.getRegimen());
        obj.setMaeRegimenId(per.getMaeRegimenId());
        obj.setMaeRegimenCodigo(per.getMaeRegimenCodigo());
        obj.setMaeRegimenValor(per.getMaeRegimenValor());
        return obj;
    }

    @Override
    public List<MapaPrestadorSede> consultarListaMapaPrestadorSedesIdMunicipio(Integer id, Integer municipioId) throws Exception {
        List<MapaPrestadorSede> listResult = new ArrayList();
        try {
            String strQuery = "SELECT s "
                    + "FROM CntPrestadorSedes AS s "
                    + "WHERE s.capitacion = :capitacion ";
            if (id != null) {
                strQuery += "AND s.id = :id ";
            }
            if (municipioId != null) {
                strQuery += "AND s.ubicacionId = :municipioId ";
            }
            strQuery += "ORDER BY s.id ASC";
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("capitacion", true);
            if (id != null) {
                query.setParameter("id", id);
            }
            if (municipioId != null) {
                query.setParameter("municipioId", municipioId);
            }
            List<CntPrestadorSedes> list = query.getResultList();
            for (CntPrestadorSedes per : list) {
                MapaPrestadorSede sede = castPrestadorSedeEntidadNegocioCorto(per);
                listResult.add(sede);
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

    public static MapaPrestadorSede castPrestadorSedeEntidadNegocioCorto(CntPrestadorSedes per) {
        MapaPrestadorSede obj = new MapaPrestadorSede();
        obj.setId(per.getId());
        obj.setUbicacionId(per.getUbicacionId());
        obj.setCodigoPrestador(per.getCodigoPrestador());
        obj.setMaeRegionId(per.getMaeRegionId());
        obj.setMaeRegionCodigo(per.getMaeRegionCodigo());
        obj.setMaeRegionValor(per.getMaeRegionValor());
        obj.setDireccion(per.getDireccion());
        obj.setNombreSede(per.getNombre());
        obj.setCodigoSede(per.getCodigo());
        obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());
        obj.setZonaPrecedencia(per.getZonaPrecedencia());
        obj.setEstadoSede(per.getEstadoSede());
        obj.setNivelAtencion(per.getNivelAtencion());
        obj.setClasePrestador(per.getMaeClasePrestadorId());
        obj.setMaeClasePrestadorCodigo(per.getMaeClasePrestadorCodigo());
        obj.setMaeClasePrestadorValor(per.getMaeClasePrestadorValor());
        obj.setFax(per.getFax());
        obj.setTelefonoCitas(per.getTelefonoCitas());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefonoAdministrativo(per.getTelefonoAdministrativo());
        obj.setCapitacion(per.getCapitacion());
        obj.setGrupoRipsMinisterio(per.getGrupoRipsMinisterio());
        obj.setFechaFacturaElectronica(per.getFechaFacturaElectronica());
        obj.setDireccionGeorreferenciada(per.getDireccionGeorreferenciada());
        if (per.getDireccionGeorefLatitud() != null) {
            obj.setDireccionGeorefLatitud(per.getDireccionGeorefLatitud().doubleValue());
        }
        if (per.getDireccionGeorefLongitud()!= null) {
            obj.setDireccionGeorefLongitud(per.getDireccionGeorefLongitud().doubleValue());
        }
        if (per.getCntPrestadoresId() != null) {
            MapaPrestador prestador = new MapaPrestador();
            prestador.setId(per.getCntPrestadoresId().getId());
            prestador.setMaeTipoDocumentoId(per.getCntPrestadoresId().getMaeTipoDocumentoId());
            prestador.setMaeTipoDocumentoCodigo(per.getCntPrestadoresId().getMaeTipoDocumentoCodigo());
            prestador.setMaeTipoDocumentoValor(per.getCntPrestadoresId().getMaeTipoDocumentoValor());
            prestador.setNumeroDocumento(per.getCntPrestadoresId().getNumeroDocumento());
            prestador.setRazonSocial(per.getCntPrestadoresId().getRazonSocial());
            obj.setPrestador(prestador);
        }
        return obj;
    }
}
