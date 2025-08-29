package com.saviasaludeps.savia.ejb.servicios.webservices;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliadoContacto;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AsegPortabilidades;
import com.saviasaludeps.savia.ejb.entidades.GnMaestros;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoContactoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.webservices.AseguramientoRemoto;
import com.saviasaludeps.savia.utilidades.AfiliadoValidacion;
import com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento.AfiliadoProgramaDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento.DetalleRespuestaSolicitudPortabilidadDTO;
import com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento.RespuestaAfiliadoInformacionSystemSaviaDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author rpalacios
 */
@Stateless
@Remote(AseguramientoRemoto.class)
public class AseguramientoServicio extends GenericoServicio implements AseguramientoRemoto {

    @EJB
    private PeAfiliadosProgramaRemoto peAfiliadosProgramaRemoto;

    @EJB
    private AfiliadoContactoRemoto afiliadoContactoRemoto;

//    private HashMap<Integer, GnMaestros> hashMaestros;
    @Override
    public List<RespuestaAfiliadoInformacionSystemSaviaDTO> consultarAfiliado(String tipodocumento, String numeroDocumento, String fechaNacimiento, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) throws Exception {
        List<RespuestaAfiliadoInformacionSystemSaviaDTO> listResult = null;
        try {
            String strQuery = "FROM AsegAfiliados p "
                    + "LEFT JOIN FETCH p.nacionalidadUbicacionesId "
                    + "LEFT JOIN FETCH p.nacimientoUbicacionesId "
                    + "LEFT JOIN FETCH p.gnUbicacionesLugarNacimientoId "
                    + "LEFT JOIN FETCH p.gnUbicacionBarriosId "
                    + "WHERE 1 = 1 ";
            if (!tipodocumento.equals("")) {
                strQuery += "AND p.maeTipoDocumentoId = " + tipodocumento + " ";
            }
            if (!numeroDocumento.equals("")) {
                strQuery += "AND p.numeroDocumento = '" + numeroDocumento + "' ";
            }
            if (!primerNombre.equals("")) {
                strQuery += "AND p.primerNombre = '" + primerNombre + "' ";
            }
            if (!segundoNombre.equals("")) {
                strQuery += "AND p.segundoNombre = '" + segundoNombre + "' ";
            }
            if (!primerApellido.equals("")) {
                strQuery += "AND p.primerApellido = '" + primerApellido + "' ";
            }
            if (!segundoApellido.equals("")) {
                strQuery += "AND p.segundoApellido = '" + segundoApellido + "' ";
            }
            if (!fechaNacimiento.equals("")) {
                strQuery += "AND p.fechaNacimiento = '" + fechaNacimiento + "' ";
            }
            strQuery += "ORDER BY p.maeEstadoAfiliacionId ASC";
            List<AsegAfiliados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null && !list.isEmpty()) {
//                hashMaestros = consultarHashMaestros();
                listResult = new ArrayList();
                if (!tipodocumento.equals("") && !numeroDocumento.equals("")) {
                    listResult.add(castAfiliadoInformacionSystemSavia(list.get(0)));
                } else {
                    for (AsegAfiliados per : list) {
                        listResult.add(castAfiliadoInformacionSystemSavia(per));
                    }
                }
            }
        } catch (NoResultException e) {
            listResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public List<DetalleRespuestaSolicitudPortabilidadDTO> consultarPortabilidadPorAfiliado(int id) throws Exception {
        List<DetalleRespuestaSolicitudPortabilidadDTO> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegPortabilidades a "
                    + " WHERE a.asegAfiliadosId.id = " + id
                    + " ORDER BY a.fechaHoraCrea DESC";
            List<AsegPortabilidades> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegPortabilidades per : list) {
                listResult.add(castDetalleRespuestaSolicitudPortabilidad(per));
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
    public DetalleRespuestaSolicitudPortabilidadDTO consultarPortabilidadPorId(int id) throws Exception {
        DetalleRespuestaSolicitudPortabilidadDTO objRes = null;
        try {
            AsegPortabilidades obj = (AsegPortabilidades) getEntityManager().find(AsegPortabilidades.class, id);
            if (obj != null) {
                objRes = castDetalleRespuestaSolicitudPortabilidad(obj);
            }

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
    public DetalleRespuestaSolicitudPortabilidadDTO consultarPortabilidadPorAfiliadoIdPortabilidad(int idAfiliado, int idPortabilidad) throws Exception {
        DetalleRespuestaSolicitudPortabilidadDTO objRes = null;
        try {
            String strQuery = "FROM AsegPortabilidades a "
                    + " WHERE a.asegAfiliadosId.id = " + idAfiliado
                    + " AND a.id = " + idPortabilidad
                    + " ORDER BY a.fechaHoraCrea DESC";
            List<AsegPortabilidades> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (!list.isEmpty()) {
                objRes = castDetalleRespuestaSolicitudPortabilidad(list.get(0));
            } else {
                objRes = null;
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private RespuestaAfiliadoInformacionSystemSaviaDTO castAfiliadoInformacionSystemSavia(AsegAfiliados afiliado) {
        RespuestaAfiliadoInformacionSystemSaviaDTO afiliadoDTO = new RespuestaAfiliadoInformacionSystemSaviaDTO();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            /**
             * rpalacic (2022-06-09) Corrección de uso de maestros
             */
//            if (hashMaestros != null) {
//                if (afiliado.getMaeTipoDocumentoCfId() != null) {
            afiliadoDTO.setTipoDocumentoCabezaFamilia(afiliado.getMaeTipoDocumentoCfCodigo());
//                    GnMaestros tipoDocumentoCF = hashMaestros.get(afiliado.getMaeTipoDocumentoCfId());
//                    if (tipoDocumentoCF != null) {
//                        afiliadoDTO.setTipoDocumentoCabezaFamilia(hashMaestros.get(afiliado.getMaeTipoDocumentoCfId()).getValor());
//                    }
//                }
            /**
             * rpalacic (2022-06-09) Corrección de uso de maestros
             */
            afiliadoDTO.setTipoDocumentoAfiliado(afiliado.getMaeTipoDocumentoCodigo());
//                afiliadoDTO.setTipoDocumentoAfiliado(hashMaestros.get(afiliado.getMaeTipoDocumentoId()).getValor());
            /**
             * rpalacic (2022-06-09) Corrección de uso de maestros
             */
            afiliadoDTO.setGrupoPoblacional(afiliado.getMaeGrupoPoblacionalValor());
            if (afiliado.getMaeGrupoPoblacionalCodigo() != null
                    && afiliado.getMaeGrupoPoblacionalCodigo().equals("5")) {
                if (afiliado.getMaeMetodologiaGrupoPoblacionalCodigo() != null
                        && afiliado.getMaeMetodologiaGrupoPoblacionalCodigo().equals("1")) {
                    afiliadoDTO.setObservacion("Usuario sin encuesta Versión 4 del SISBEN");
                } else {
                    afiliadoDTO.setObservacion("Usuario sin encuesta Versión 4 del SISBEN");
                }
            }
//                afiliadoDTO.setGrupoPoblacional(hashMaestros.get(afiliado.getMaeGrupoPoblacionalId()).getNombre());
            /**
             * rpalacic (2022-06-09) Corrección de uso de maestros
             */
            afiliadoDTO.setCodGrupoPoblacional(afiliado.getMaeGrupoPoblacionalCodigo());
            afiliadoDTO.setEtnia(String.valueOf(afiliado.getEtnia()));
            afiliadoDTO.setSexoAfiliado(afiliado.getMaeGeneroValor());
            afiliadoDTO.setEstadoAfiliacion(afiliado.getMaeEstadoAfiliacionValor());
//                afiliadoDTO.setEstadoAfiliacion(hashMaestros.get(afiliado.getMaeEstadoAfiliacionId()).getNombre());
            /**
             * rpalacic (2022-06-09) Corrección de uso de maestros
             */
            afiliadoDTO.setCausaEstado(afiliado.getMaeCausaNovedadValor());
            afiliadoDTO.setCodCausaEstado(afiliado.getMaeCausaNovedadCodigo());
//                if (afiliado.getMaeCausaNovedadId() != 0) {
//                    afiliadoDTO.setCausaEstado(hashMaestros.get(afiliado.getMaeCausaNovedadId()).getNombre());
//                    afiliadoDTO.setCodCausaEstado((hashMaestros.get(afiliado.getMaeCausaNovedadId()).getValor()));
//                } else {
//                    afiliadoDTO.setCausaEstado("");
//                }
            /**
             * rpalacic (2022-06-09) Corrección de uso de maestros
             */
            afiliadoDTO.setOrigenAfiliado(afiliado.getMaeOrigenAfiliadoValor());
            afiliadoDTO.setCodOrigenAfiliado(afiliado.getMaeOrigenAfiliadoCodigo());
//                afiliadoDTO.setOrigenAfiliado(hashMaestros.get(afiliado.getMaeOrigenAfiliadoId()).getNombre());
//                afiliadoDTO.setCodOrigenAfiliado(hashMaestros.get(afiliado.getMaeOrigenAfiliadoId()).getValor());
//            }
            afiliadoDTO.setTipoAfiliado(afiliado.getMaeTipoAfiliadoValor());
            afiliadoDTO.setRegimen(afiliado.getMaeRegimenValor());
            if (afiliado.getRegimen() != null) {
                switch (afiliado.getMaeRegimenCodigo()) {
                    case "01":
//                        afiliadoDTO.setRegimen("Subsidiado");
                        afiliadoDTO.setCodigoEntidad("EPSS40");
                        break;
                    case "02":
//                        afiliadoDTO.setRegimen("Contributivo");
                        afiliadoDTO.setCodigoEntidad("EPS040");
                        break;
                    default:
                        afiliadoDTO.setRegimen("EPSS40");
                        break;
                }
            } else {
                afiliadoDTO.setRegimen("EPSS40");
            }
            afiliadoDTO.setParentescoCabezaFamilia(afiliado.getMaeParentescoCotizanteValor());
//            afiliadoDTO.setParentescoCabezaFamilia(getParentescoCotizante(afiliado.getParentescoCotizante()));
            afiliadoDTO.setModalidadSubsidio("Subsidio Total");
            if (afiliado.getSerialBdua() != null) {
                afiliadoDTO.setConsecutivoBDUA(afiliado.getSerialBdua().toString());
            }

            afiliadoDTO.setDocumentoCabezaFamilia(afiliado.getNumeroDocumentoCf());
            afiliadoDTO.setDocumentoAfiliado(afiliado.getNumeroDocumento());
            afiliadoDTO.setPrimerApellidoAfiliado(afiliado.getPrimerApellido());
            afiliadoDTO.setSegundoApellidoAfiliado(afiliado.getSegundoApellido());
            afiliadoDTO.setPrimerNombreAfiliado(afiliado.getPrimerNombre());
            afiliadoDTO.setSegundoNombreAfiliado(afiliado.getSegundoNombre());
            if (afiliado.getFechaNacimiento() != null) {
                afiliadoDTO.setFechaNacimientoAfiliado(dateFormat.format(afiliado.getFechaNacimiento()));
            }

            afiliadoDTO.setNivelSisben(afiliado.getNivelSisben());
            /**
             * rpalacic (2022-06-09) Corrección de uso de maestros
             */
            afiliadoDTO.setCategoriaIBC(afiliado.getMaeCategoriaIbcValor());
            afiliadoDTO.setGrupoSisben(afiliado.getMaeGrupoSisbenCodigo());
//            if (afiliado.getMaeGrupoSisbenId()!= null) {
//                afiliadoDTO.setGrupoSisben(hashMaestros.get(afiliado.getMaeGrupoSisbenId()).getValor());
//            }
            /**
             * rpalacic (2022-06-09) Corrección de uso de maestros
             */
            afiliadoDTO.setSubGrupoSisben(afiliado.getMaeSubGrupoSisbenCodigo());
//            if (afiliado.getMaeGrupoSisbenId()!= null) {
//                afiliadoDTO.setSubGrupoSisben(hashMaestros.get(afiliado.getMaeSubGrupoSisbenId()).getValor());
//            }
            if (afiliado.getAfiliacionUbicacionesId() != null) {
                afiliadoDTO.setCodMunicipioAfiliacion(afiliado.getAfiliacionUbicacionesId().getPrefijo());
                afiliadoDTO.setMunicipioAfiliacion(afiliado.getAfiliacionUbicacionesId().getNombre());
                afiliadoDTO.setCiudadAfiliacion(afiliado.getAfiliacionUbicacionesId().getNombre());
                afiliadoDTO.setDescripcionCiudadResidencia(afiliado.getResidenciaUbicacionId().getNombre());
                if (afiliado.getAfiliacionUbicacionesId().getGnUbicacionesId() != null) {
                    afiliadoDTO.setCodDepartamentoAfiliacion(afiliado.getAfiliacionUbicacionesId().getGnUbicacionesId().getPrefijo());
                    afiliadoDTO.setDepartamentoAfiliacion(afiliado.getAfiliacionUbicacionesId().getGnUbicacionesId().getNombre());
                }

            }
            afiliadoDTO.setEstadoCivil(afiliado.getMaeEstadoCivilValor());
//            afiliadoDTO.setEstadoCivil(getEstadoCivil(afiliado.getEstadoCivil()));
            afiliadoDTO.setContratoInternoAfilado(afiliado.getIdAfiliado());
            afiliadoDTO.setZonaAfiliacion(afiliado.getMaeZonaValor());
            if (afiliado.getFechaAfiliacionSgsss() != null) {
                afiliadoDTO.setFechaAfiliacionSGSSS(dateFormat.format(afiliado.getFechaAfiliacionSgsss()));
            }
            if (afiliado.getFechaAfiliacionEps() != null) {
                afiliadoDTO.setFechaAfiliacionEntidad(dateFormat.format(afiliado.getFechaAfiliacionEps()));
            }
            if (afiliado.getFechaMovilidad() != null) {
                afiliadoDTO.setFechaMovilidadEntidad(dateFormat.format(afiliado.getFechaMovilidad()));
            }
            afiliadoDTO.setDireccion(afiliado.getDireccionResidencia());
            if (afiliado.getPuntajeSisben() != null) {
                afiliadoDTO.setPuntajeSisben(afiliado.getPuntajeSisben().toString());
            }

            // Se implementa tema de aseg_afiliado_contactos 
            AsegAfiliadoContacto afiliadoContactoTelefono = afiliadoContactoRemoto.consultarUltimoPorAfiliadoYTipo(afiliado.getId(), AuSeguimientoAfiliadoContacto.TIPO_CONTACTO_TELEFONO);

            if (afiliadoContactoTelefono != null) {
                afiliadoDTO.setTelefono(afiliadoContactoTelefono.getNumeroContacto());
            } else {
                afiliadoDTO.setTelefono(afiliado.getTelefonoFijo());
            }

            AsegAfiliadoContacto afiliadoContactoCelular = afiliadoContactoRemoto.consultarUltimoPorAfiliadoYTipo(afiliado.getId(), AuSeguimientoAfiliadoContacto.TIPO_CONTACTO_CELULAR);
            if (afiliadoContactoCelular != null) {
                afiliadoDTO.setTelefonoMovil(afiliadoContactoCelular.getNumeroContacto());
            } else {
                afiliadoDTO.setTelefonoMovil(afiliado.getTelefonoMovil());
            }

            if (afiliado.getEmail() != null) {
                afiliadoDTO.setEmail(afiliado.getEmail());
            }
            afiliadoDTO.setCodigoIPS(afiliado.getPrimariaCntPrestadorSedesId().getCodigoPrestador());
            afiliadoDTO.setCodigoSede(afiliado.getPrimariaCntPrestadorSedesId().getCodigoHabilitacion());
            if (afiliado.getPortabilidadCntPrestadorSedesId() != null) {
                afiliadoDTO.setSedeIPSPortabilidad(afiliado.getPortabilidadCntPrestadorSedesId().getNombre());
                afiliadoDTO.setIpsportabilidad(afiliado.getPortabilidadCntPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
                //bucar ubicacion de la portabilidad
                GnUbicaciones ubicacionPortabilidad = consultarUbicacionPorId(afiliado.getPortabilidadCntPrestadorSedesId().getUbicacionId());
                afiliadoDTO.setCodMunicipioPortabilidad(ubicacionPortabilidad.getPrefijo());
                afiliadoDTO.setCiudadPortabilidad(ubicacionPortabilidad.getNombre());
                if (ubicacionPortabilidad.getGnUbicacionesId() != null) {
                    afiliadoDTO.setCodDepartamentoPortabilidad(ubicacionPortabilidad.getGnUbicacionesId().getPrefijo());
                    afiliadoDTO.setDepartamentoPortabilidad(ubicacionPortabilidad.getGnUbicacionesId().getNombre());
                }
            }

            if (afiliado.getDiscapacidad()) {
                afiliadoDTO.setDiscapacidad("SI");
            } else {
                afiliadoDTO.setDiscapacidad("NO");
            }

            if (afiliado.getFechaReactivacion() != null) {
                afiliadoDTO.setFechaReactivacion(dateFormat.format(afiliado.getFechaReactivacion()));
            }
            if (afiliado.getFechaEgresoEps() != null && !afiliado.getMaeEstadoAfiliacionCodigo().equals(AfiliadoValidacion.ESTADO_AFILIACION_ACTIVO)) {
                afiliadoDTO.setFechaRetiro(dateFormat.format(afiliado.getFechaEgresoEps()));
            }
            if (afiliado.getFechaCambioEstadoEps() != null) {
                afiliadoDTO.setFechaNovedad(dateFormat.format(afiliado.getFechaCambioEstadoEps()));
            }
            if (afiliado.getVictima()) {
                afiliadoDTO.setVictimaLey1448("SI");
            } else {
                afiliadoDTO.setVictimaLey1448("NO");
            }
            afiliadoDTO.setTipoDiscapacidad(afiliado.getMaeTipoDiscapacidadValor());
//            afiliadoDTO.setTipoDiscapacidad(getTipoDiscapacidad(afiliado.getTipoDiscapacidad()));
            if (afiliado.getResidenciaUbicacionId() != null) {
                afiliadoDTO.setCodCiudadResidencia(afiliado.getResidenciaUbicacionId().getPrefijo());
                if (afiliado.getResidenciaUbicacionId().getGnUbicacionesId() != null) {
                    afiliadoDTO.setCodDepartamentoResidencia(afiliado.getResidenciaUbicacionId().getGnUbicacionesId().getPrefijo());
                }
            }

            afiliadoDTO.setCodBarrioResidencia(afiliado.getBarrio());
            afiliadoDTO.setSedeIPSPrimaria(afiliado.getPrimariaCntPrestadorSedesId().getNombre());
            afiliadoDTO.setIpsprimaria(afiliado.getPrimariaCntPrestadorSedesId().getNombre());
            afiliadoDTO.setDescrLiquidacion(afiliado.getMaeModeloLiquidacionValor());
//            afiliadoDTO.setDescrLiquidacion(getModeloLiquidacion(afiliado.getModeloLiquidacion()));
            if (afiliado.getFechaSuspensionEps() != null) {
                afiliadoDTO.setFechaSuspension(dateFormat.format(afiliado.getFechaSuspensionEps()));
            }

            if (afiliado.getNacionalidadUbicacionesId() != null) {
                afiliadoDTO.setPaisNacionalidad(afiliado.getNacionalidadUbicacionesId().getNombre());
            } else {
                afiliadoDTO.setPaisNacionalidad("");
            }

            if (afiliado.getNacimientoUbicacionesId() != null) {
                afiliadoDTO.setPaisNacimiento(afiliado.getNacimientoUbicacionesId().getNombre());
            } else {
                afiliadoDTO.setPaisNacimiento("");

            }

            if (afiliado.getGnUbicacionesLugarNacimientoId() != null) {
                afiliadoDTO.setDepartamentoNacimiento(afiliado.getGnUbicacionesLugarNacimientoId().getGnUbicacionesId().getNombre());
                afiliadoDTO.setMunicipioNacimiento(afiliado.getGnUbicacionesLugarNacimientoId().getNombre());
            } else {
                afiliadoDTO.setDepartamentoNacimiento("");
                afiliadoDTO.setMunicipioNacimiento("");

            }

            afiliadoDTO.setGeneroIdentificacion(afiliado.getMaeGeneroIdentificacionValor());

            if (afiliado.getGnUbicacionBarriosId() != null) {
                afiliadoDTO.setComuna(afiliado.getGnUbicacionBarriosId().getCodigoComuna() + " - " + afiliado.getGnUbicacionBarriosId().getComuna());
                afiliadoDTO.setBarrio(afiliado.getGnUbicacionBarriosId().getNombre());
            } else {
                afiliadoDTO.setComuna("");
                afiliadoDTO.setBarrio("");
            }

            /* Programas especiales afiliado */
            List<PeAfiliadosPrograma> programas = peAfiliadosProgramaRemoto.consultarAfiliadoActivo(afiliado.getId());
            List<AfiliadoProgramaDTO> programasDto = new ArrayList<>();

            if (programas != null && !programas.isEmpty()) {
                for (PeAfiliadosPrograma programa : programas) {
                    AfiliadoProgramaDTO dto = new AfiliadoProgramaDTO();
                    dto.setTipo(programa.getPePrograma().getMaeCategoriaValor());
                    dto.setDescripcion(programa.getPePrograma().getDescripcionPrograma());
                    programasDto.add(dto);
                }

                afiliadoDTO.setProgramas(programasDto);
            } else {
                afiliadoDTO.setProgramas(new ArrayList<>());
            }
        } catch (Exception ex) {
        }

        return afiliadoDTO;
    }

    private DetalleRespuestaSolicitudPortabilidadDTO castDetalleRespuestaSolicitudPortabilidad(AsegPortabilidades asegPortabilidad) {
        DetalleRespuestaSolicitudPortabilidadDTO detalle = new DetalleRespuestaSolicitudPortabilidadDTO();
        try {
            java.util.Date periodoInicio = new java.util.Date(asegPortabilidad.getPeriodoInicial().getTime());
            java.util.Date periodoFin = new java.util.Date(asegPortabilidad.getPeriodoFinal().getTime());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            detalle.setSolicitudPortabilidad(String.valueOf(asegPortabilidad.getId()));
            GnMaestros maestroTipoDocumentoAfiliado = consultarMaestroId(asegPortabilidad.getAsegAfiliadosId().getMaeTipoDocumentoId());
            if (maestroTipoDocumentoAfiliado != null) {
                detalle.setTipoDocumento(maestroTipoDocumentoAfiliado.getValor());
            }

            detalle.setDocumento(asegPortabilidad.getAsegAfiliadosId().getNumeroDocumento());
            detalle.setPrimerNombre(asegPortabilidad.getAsegAfiliadosId().getPrimerNombre());
            detalle.setSegundoNombre(asegPortabilidad.getAsegAfiliadosId().getSegundoNombre());
            detalle.setPrimerApellido(asegPortabilidad.getAsegAfiliadosId().getPrimerApellido());
            detalle.setSegundoApellido(asegPortabilidad.getAsegAfiliadosId().getSegundoApellido());
            detalle.setFechaNacimiento(dateFormat.format(asegPortabilidad.getAsegAfiliadosId().getFechaNacimiento()));
            detalle.setFechaSolicitud(dateFormat.format(asegPortabilidad.getFechaHoraCrea()));
            detalle.setTipoPortabilidad(String.valueOf(asegPortabilidad.getTipoPortabilidad()));
            detalle.setPeriodoInicial(dateFormat.format(asegPortabilidad.getPeriodoInicial()));
            detalle.setDuracion(calcularDuracionDias(periodoInicio, periodoFin));
            detalle.setTipoSolcitud(AsegPortabilidad.TIPO_SOLICITUD_NUEVA);
            detalle.setOrigenSolicitud(String.valueOf(asegPortabilidad.getOrigenSolicitud()));
            detalle.setMunicipioPortabilidad(asegPortabilidad.getUbicacionesId().getNombre());
            detalle.setIPSPortabilidad(asegPortabilidad.getCntPrestadorSedesId().getNombre());
            detalle.setNombreDepartamento(asegPortabilidad.getUbicacionesId().getNombre());
            detalle.setNombreMunicipio(asegPortabilidad.getUbicacionesId().getNombre());
            detalle.setEstadoSolicitud(String.valueOf(asegPortabilidad.getEstadoPortabilidad()));
            detalle.setCodigoRespuesta("0");
        } catch (Exception ex) {

        }
        return detalle;
    }

    private String calcularDuracionDias(Date periodoInicial, Date periodoFinal) {
        String dias = "";
        if (periodoInicial != null && periodoFinal != null) {
            LocalDate periodoInicio = periodoInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate periodoFin = periodoFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Long diasDuracion = Duration.between(periodoInicio.atStartOfDay(), periodoFin.atStartOfDay()).toDays();
            dias = diasDuracion.toString();
        }
        return dias;
    }

    public static Ubicacion castUbicacionEntidadNegocio(GnUbicaciones per) {
        Ubicacion obj = new Ubicacion();
        if (per != null) {
            obj.setId(per.getId());
            obj.setNombre(per.getNombre());
            obj.setTipo(per.getTipo());
            obj.setPrefijo(per.getPrefijo());
            if (per.getGnUbicacionesId() != null) {
                obj.setUbicacionPadre(new Ubicacion(null, per.getGnUbicacionesId().getId(), per.getGnUbicacionesId().getTipo(), per.getGnUbicacionesId().getCodigoPostal(), per.getGnUbicacionesId().getNombre()));
                obj.getUbicacionPadre().setPrefijo(per.getGnUbicacionesId().getPrefijo());
            }
        }
        return obj;
    }

//    public HashMap<Integer, GnMaestros> consultarHashMaestros() throws Exception {
//        HashMap<Integer, GnMaestros> hashResult = new HashMap();
//        String strQuery = "FROM GnMaestros e ";
//        try {
//            Query query = getEntityManager().createQuery(strQuery);
//            List<GnMaestros> list = query.getResultList();
//            for (GnMaestros per : list) {
//                hashResult.put(per.getId(), per);
//            }
//        } catch (NoResultException e) {
//            hashResult = new HashMap();
//        } catch (Exception e) {
//            Exception(CONSULTAR_TODOS, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return hashResult;
//    }
//    public String getParentescoCotizante(String valor) {
//        String equivalente;
//        if (valor != null) {
//            switch (valor) {
//                case "1":
//                    equivalente = "Cónyuge o compañero(a) permanente";
//                    break;
//                case "2":
//                    equivalente = "Hijos del cotizante o del compañero(a) permanente, menores de veinticinco años que dependen económicamente del cotizante";
//                    break;
//                case "3":
//                    equivalente = "Padre o madre del cotizante, cabeza de familia";
//                    break;
//                case "4":
//                    equivalente = "Hijos de beneficiarios menores de 25 años o con incapacidad permanente";
//                    break;
//                case "5":
//                    equivalente = "Hasta tercer grado de consanguinidad - Hijos de menores de 25 años o cualquier edad con incapacidad permanente";
//                    break;
//                case "7":
//                    equivalente = "Padres que dependan económicamente de alguno de los cónyuges o compañero(a) permanente del cotizante, cuando ambos cotizan, cabeza de familia";
//                    break;
//                case "8":
//                    equivalente = "Afiliado adicional";
//                    break;
//                case "9":
//                    equivalente = "Hijos del cotizante o del compañero(a) permanente, de cualquier edad si tienen incapacidad permanente y depende económicamente del cotizante, cabeza de familia";
//                    break;
//                default:
//                    equivalente = "";
//                    break;
//            }
//        } else {
//            equivalente = "";
//        }
//
//        return equivalente;
//    }
//    public String getTipoAfiliado(String valor) {
//        String equivalente;
//        if (valor != null) {
//            switch (valor) {
//                case "101":
//                    equivalente = "Cotizante";
//                    break;
//                case "102":
//                    equivalente = "Beneficiario";
//                    break;
//                case "103":
//                    equivalente = "Cabeza de Hogar";
//                    break;
//                case "104":
//                    equivalente = "Adicional";
//                    break;
//                default:
//                    equivalente = "";
//                    break;
//            }
//        } else {
//            equivalente = "";
//        }
//
//        return equivalente;
//    }
//    public String getRegimen(String id) {
//        String resultado;
//        if (id != null) {
//            switch (id) {
//                case "1":
//                    resultado = "Subsidiado";
//                    break;
//                case "2":
//                    resultado = "Contributivo";
//                    break;
//                default:
//                    resultado = "";
//                    break;
//            }
//        } else {
//            resultado = "";
//        }
//
//        return resultado;
//    }
    public GnMaestros consultarMaestroId(int id) throws Exception {
        GnMaestros objRes = null;
        try {
            objRes = (GnMaestros) getEntityManager().find(GnMaestros.class, id);
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

//    public String getEstadoCivil(String valor) {
//        String equivalente;
//        if (valor != null) {
//            switch (valor) {
//                case "1":
//                    equivalente = "Soltero";
//                    break;
//                case "2":
//                    equivalente = "Casado";
//                    break;
//                case "3":
//                    equivalente = "Viudo";
//                    break;
//                case "4":
//                    equivalente = "Divorciado";
//                    break;
//                case "5":
//                    equivalente = "Unión Libre";
//                    break;
//                case "6":
//                    equivalente = "Divorciado";
//                    break;
//                case "7":
//                    equivalente = "No reportado";
//                    break;
//                case "8":
//                    equivalente = "Otro";
//                    break;
//                default:
//                    equivalente = "";
//                    break;
//            }
//        } else {
//            equivalente = "";
//        }
//
//        return equivalente;
//    }
//    public String getModeloLiquidacion(String valor) {
//        String equivalente;
//        if (valor != null) {
//            switch (valor) {
//                case "0":
//                    equivalente = "CAPITA";
//                    break;
//                case "1":
//                    equivalente = "EVENTO";
//                    break;
//                default:
//                    equivalente = "";
//                    break;
//            }
//        } else {
//            equivalente = "";
//        }
//
//        return equivalente;
//    }
//    public String getTipoDiscapacidad(String valor) {
//        String equivalente;
//        if (valor != null) {
//            switch (valor) {
//                case "1":
//                    equivalente = "Física";
//                    break;
//                case "2":
//                    equivalente = "Neuro-Sensorial";
//                    break;
//                case "3":
//                    equivalente = "Mental";
//                    break;
//                default:
//                    equivalente = "";
//                    break;
//            }
//        } else {
//            equivalente = "";
//        }
//
//        return equivalente;
//    }
    private GnUbicaciones consultarUbicacionPorId(int idUbicacion) throws java.lang.Exception {
        GnUbicaciones ubicacion = null;

        try {
            String strQuery = "FROM GnUbicaciones u "
                    + "WHERE  u.id = '" + idUbicacion + "' ";

            List<GnUbicaciones> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            if (list != null && !list.isEmpty()) {
                ubicacion = list.get(0);
            }

        } catch (NoResultException e) {
            ubicacion = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ubicacion;

    }

    @Override
    public AsegAfiliado consultarAfiliadoId(String documento) throws java.lang.Exception {
        AsegAfiliado objRes = null;
        AsegAfiliados result = null;

        try {
            String strQuery = "FROM AsegAfiliados m "
                    + "WHERE m.numeroDocumento ='" + documento + "'";
            Query query = getEntityManager().createQuery(strQuery);
            result = (AsegAfiliados) query.getSingleResult();
            objRes = afiliado(result);

        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public static AsegAfiliado afiliado(AsegAfiliados per) {
        AsegAfiliado obj = new AsegAfiliado();
        obj.setId(per.getId());
        obj.setIdAfiliado(per.getIdAfiliado());
        return obj;
    }

//    private GnMaestros consultarMaestroPorValorTipo(String valor, String tipo) throws Exception {
//        GnMaestros objRes = null;
//        try {
//            String strQuery = "FROM GnMaestros m "
//                    + "WHERE m.valor ='" + valor
//                    + "' AND m.tipo='" + tipo + "'";
//            objRes = (GnMaestros) getEntityManager().createQuery(strQuery).getSingleResult();
//        } catch (NoResultException e) {
//            objRes = null;
//        } catch (Exception e) {
//            Exception(CONSULTAR, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return objRes;
//    }
}
