/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.CsContribucionSolidaria;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.negocio.administracion.GnAlertaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.CsContribucionSolidariaRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.PortabilidadRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoContactoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucEgresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionAdversoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionEstanciaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionInoportunidadRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionObjecionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionPatologiaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionServicioRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucIngresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucJustificacionEstanciasProlongadaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoSugeridoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeIpsProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeSugeridoAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeTelefonosRemoto;
import com.saviasaludeps.savia.web.especial.bean.PeAfiliadoSugeridoBean;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public class PeAfiliadoSugeridoServicioImpl extends AccionesBO implements PeAfiliadoSugeridoServicioIface {

    private PeAfiliadoSugeridoRemoto getPeAfiliadoSugeridoRemoto() throws Exception {
        return (PeAfiliadoSugeridoRemoto) RemotoEJB.getEJBRemoto("PeAfiliadoSugeridoServicio", PeAfiliadoSugeridoRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    private PortabilidadRemoto getPortabilidadRemoto() throws Exception {
        return (PortabilidadRemoto) RemotoEJB.getEJBRemoto("PortabilidadServicio", PortabilidadRemoto.class.getName());
    }

    private CsContribucionSolidariaRemoto getContribucionSolidariaRemoto() throws Exception {
        return (CsContribucionSolidariaRemoto) RemotoEJB.getEJBRemoto("CsContribucionSolidariaServicio", CsContribucionSolidariaRemoto.class.getName());
    }

    private AucHospitalizacionRemoto getAucHospitalizacionRemoto() throws Exception {
        return (AucHospitalizacionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionServicio", AucHospitalizacionRemoto.class.getName());
    }

    private AucAfiliadoRemoto getAucAfiliadoRemoto() throws Exception {
        return (AucAfiliadoRemoto) RemotoEJB.getEJBRemoto("AucAfiliadoServicio", AucAfiliadoRemoto.class.getName());
    }

    private AucIngresoRemoto getAucIngresoRemoto() throws Exception {
        return (AucIngresoRemoto) RemotoEJB.getEJBRemoto("AucIngresoServicio", AucIngresoRemoto.class.getName());
    }

    private AucEgresoRemoto getAucEgresoRemoto() throws Exception {
        return (AucEgresoRemoto) RemotoEJB.getEJBRemoto("AucEgresoServicio", AucEgresoRemoto.class.getName());
    }

    private AucHospitalizacionSeguimientoRemoto getAucHospitalizacionSeguimientoRemoto() throws Exception {
        return (AucHospitalizacionSeguimientoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionSeguimientoServicio", AucHospitalizacionSeguimientoRemoto.class.getName());
    }

    private AucHospitalizacionInoportunidadRemoto getAucHospitalizacionInoportunidadRemoto() throws Exception {
        return (AucHospitalizacionInoportunidadRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionInoportunidadServicio", AucHospitalizacionInoportunidadRemoto.class.getName());
    }

    private AucHospitalizacionObjecionRemoto getAucHospitalizacionObjecionRemoto() throws Exception {
        return (AucHospitalizacionObjecionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionObjecionServicio", AucHospitalizacionObjecionRemoto.class.getName());
    }

    private AucHospitalizacionPatologiaRemoto getAucHospitalizacionPatologiaRemoto() throws Exception {
        return (AucHospitalizacionPatologiaRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionPatologiaServicio", AucHospitalizacionPatologiaRemoto.class.getName());
    }

    private AucHospitalizacionServicioRemoto getAucHospitalizacionServicioRemoto() throws Exception {
        return (AucHospitalizacionServicioRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionServicioServicio", AucHospitalizacionServicioRemoto.class.getName());
    }

    private AucHospitalizacionEstanciaRemoto getAucHospitalizacionEstanciaRemoto() throws Exception {
        return (AucHospitalizacionEstanciaRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionEstanciaServicio", AucHospitalizacionEstanciaRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private AucJustificacionEstanciasProlongadaRemoto getAucJustificacionEstanciasProlongadaRemoto() throws Exception {
        return (AucJustificacionEstanciasProlongadaRemoto) RemotoEJB.getEJBRemoto("AucJustificacionEstanciasProlongadaServicio", AucJustificacionEstanciasProlongadaRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private AucHospitalizacionAdversoRemoto getAucHospitalizacionAdversoRemoto() throws Exception {
        return (AucHospitalizacionAdversoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionAdversoServicio", AucHospitalizacionAdversoRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private PeTelefonosRemoto getPeTelefonosRemoto() throws Exception {
        return (PeTelefonosRemoto) RemotoEJB.getEJBRemoto("PeTelefonosServicio", PeTelefonosRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private PeIpsProgramaRemoto getPeIpsProgramaRemoto() throws Exception {
        return (PeIpsProgramaRemoto) RemotoEJB.getEJBRemoto("PeIpsProgramaServicio", PeIpsProgramaRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private PeAfiliadoDiagnosticoRemoto getPeAfiliadoDiagnosticoRemoto() throws Exception {
        return (PeAfiliadoDiagnosticoRemoto) RemotoEJB.getEJBRemoto("PeAfiliadoDiagnosticoServicio", PeAfiliadoDiagnosticoRemoto.class.getName());
    }

    private AucAfiliadoContactoRemoto getAucAfiliadoContactoRemoto() throws Exception {
        return (AucAfiliadoContactoRemoto) RemotoEJB.getEJBRemoto("AucAfiliadoContactoServicio", AucAfiliadoContactoRemoto.class.getName());
    }

    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }

    private GnAlertaRemoto getGnAlertaRemoto() throws Exception {
        return (GnAlertaRemoto) RemotoEJB.getEJBRemoto("GnAlertaServicio", GnAlertaRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private PeSugeridoAdjuntoRemoto getPeSugeridoAdjuntoRemoto() throws Exception {
        return (PeSugeridoAdjuntoRemoto) RemotoEJB.getEJBRemoto("PeSugeridoAdjuntoServicio", PeSugeridoAdjuntoRemoto.class.getName());
    }

    /**
     * Método de acciones central
     *
     * @author idbohorquez
     * @creacion 03/11/2022
     * @param bean
     */
    @Override
    public void Accion(PeAfiliadoSugeridoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            marcar(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarMarca(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            consultarObjecto(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            rechazarSugerido(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarHospitalizacionAfiliado(bean);
                            break;
                        case Url.ACCION_VER:
                            verHospitalizacion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verSolicitudAnexo3(bean);
                            break;
                    }
                    break;
            }
        }
    }

    /**
     * Método para realizar carga inicial de información
     *
     * @author idbohorquez
     * @creacion 16/11/2022
     * @param bean
     */
    @Override
    public void cargasInicial(PeAfiliadoSugeridoBean bean) {
        try {
            //estados sugeridos
            bean.setListaEstadoSugerido(PeConstantes.listaEstadoSugeridos());
            bean.setHashEstadoSugerido(PeConstantes.obtenerHashMaestro(bean.getListaEstadoSugerido()));
            //tipo documento
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(PeConstantes.obtenerHashMaestro(bean.getListaTiposDocumento()));
            //generos
            bean.setListaTiposGenero(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashTiposGenero(PeConstantes.obtenerHashMaestro(bean.getListaTiposGenero()));
            //programas especiales activos
            bean.setListaPePrograma(getPeProgramaRemoto().consultarTodosEstado(PeConstantes.PE_PROGRAMA_ACTIVO));
            //Lista fuente origen
            bean.setListaFuenteOrigen(PeConstantes.listaFuenteOrigenSugeridos());
            //Ubicacion
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setListaSugeridoAdjuntos(new ArrayList<>());
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

    /**
     * Método encargado de cargar la informacion para el listado de sugeridos
     *
     * @author idbohorquez
     * @creacion 03/11/2022
     * @param bean
     */
    private void listar(PeAfiliadoSugeridoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPeAfiliadoSugeridoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPeAfiliadoSugeridoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    /**
     * Método encargado de cargar la informacion para el modal de ver
     *
     * @author idbohorquez
     * @creacion 03/11/2022
     * @param bean
     */
    private void ver(PeAfiliadoSugeridoBean bean) {
        try {
            bean.setObjeto(getPeAfiliadoSugeridoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            bean.setListaGrupoFamiliarAfiliado(getAfiliadoRemoto().consultarGrupoFamiliar(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumento(), bean.getObjeto().getAsegAfiliado().getNumeroDocumento()));
            bean.setListaPortabilidadAfiliado(getPortabilidadRemoto().consultarPorAfiliado(bean.getObjeto().getId()));
            List<PePrograma> lista = getPeProgramaRemoto().programasMatriculadosSugeridos(bean.getObjeto().getAsegAfiliado().getId());
            //Adjuntos hospitalizacion
            bean.setListaSugeridoAdjuntos(getPeSugeridoAdjuntoRemoto().listar());
            //Se consulta contribución solidaria
            BigDecimal valor = new BigDecimal(0);
            if (bean.getObjeto().getAsegAfiliado().getMaeSolidariaPorcentajeId() != null) {
                try {
                    CsContribucionSolidaria contribucionSolidaria = getContribucionSolidariaRemoto().consultarPorPorcentaje(bean.getObjeto().getAsegAfiliado().getMaeSolidariaPorcentajeId());
                    if (contribucionSolidaria != null) {
                        valor = contribucionSolidaria.getValor();
                        bean.getObjeto().getAsegAfiliado().setValorContribucionSolidaria(valor);
                    }
                } catch (Exception ex) {
                    bean.getObjeto().getAsegAfiliado().setValorContribucionSolidaria(valor);
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarHospitalizacionAfiliado(PeAfiliadoSugeridoBean bean) {
        try {
            bean.getParamConsultaHospitalizacion().setCantidadRegistros(getAucHospitalizacionRemoto().consultarCantidadListaConsultaTrescientosSesenta(bean.getParamConsultaHospitalizacion()));
            bean.setListaHospitalizacion(getAucHospitalizacionRemoto().consultarListaConsultaTrescientosSesenta(bean.getParamConsultaHospitalizacion()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verHospitalizacion(PeAfiliadoSugeridoBean bean) {
        try {
            bean.setObjeto(getPeAfiliadoSugeridoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            bean.setObjetoHospitalizacion(getAucHospitalizacionRemoto().consultar(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucAfiliadoId(getAucAfiliadoRemoto().consultar(bean.getObjetoHospitalizacion().getAucAfiliadoId().getId()));
            bean.getObjetoHospitalizacion().setAucIngresoId(getAucIngresoRemoto().consultar(bean.getObjetoHospitalizacion().getAucIngresoId().getId()));
            bean.getObjetoHospitalizacion().getAucIngresoId().setUltimaFechaIngreso(bean.getObjetoHospitalizacion().getAucIngresoId().getFechaIngreso());
            if (bean.getObjetoHospitalizacion().getAucIngresoId().getMaeRemisionNoPertinenteId() != null) {
                bean.getObjetoHospitalizacion().getAucIngresoId().setHabilitarDescripcion(1);
            } else {
                bean.getObjetoHospitalizacion().getAucIngresoId().setHabilitarDescripcion(0);
            }
            if (bean.getObjetoHospitalizacion().getAucEgresoId() != null && bean.getObjetoHospitalizacion().getAucEgresoId().getId() != null) {
                bean.getObjetoHospitalizacion().setAucEgresoId(getAucEgresoRemoto().consultar(bean.getObjetoHospitalizacion().getAucEgresoId().getId()));
            }
            bean.getObjetoHospitalizacion().setCntPrestadorId(getPrestadorRemoto().consultar(bean.getObjetoHospitalizacion().getCntPrestadorId().getId()));
            bean.getObjetoHospitalizacion().setCntPrestadorSedeId(getCntPrestadorSedeRemoto().consultar(bean.getObjetoHospitalizacion().getCntPrestadorSedeId().getId()));
            bean.getObjetoHospitalizacion().setAucHosptalizacionAdversoList(getAucHospitalizacionAdversoRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionInoportunidadList(getAucHospitalizacionInoportunidadRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionObjecionList(getAucHospitalizacionObjecionRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionPatologiaList(getAucHospitalizacionPatologiaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionServicioList(getAucHospitalizacionServicioRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.setListaContactosPrograma(getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliado().getId(), ""));
            bean.setListaSugeridoAdjuntos(getPeSugeridoAdjuntoRemoto().listar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void verSolicitudAnexo3(PeAfiliadoSugeridoBean bean) {
        try {
            bean.setObjetoAnexo3(getAuAnexo3Remoto().consultar(bean.getObjetoAnexo3().getId()));
            //para modal ver afiliado
            bean.setListaContactosPrograma(getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliado().getId(), ""));
            bean.getObjeto().setAsegAfiliado(bean.getObjetoAnexo3().getAsegAfiliadoId());
            bean.setListaSugeridoAdjuntos(getPeSugeridoAdjuntoRemoto().listar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarObjecto(PeAfiliadoSugeridoBean bean) {
        try {
            bean.setObjeto(getPeAfiliadoSugeridoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void rechazarSugerido(PeAfiliadoSugeridoBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            if (bean.getObjeto().getObservacionRechazo() == null || bean.getObjeto().getObservacionRechazo().isEmpty()) {
                bean.addError("Se debe ingresar la descripción del rechazo.");
                return;
            }
            bean.getObjeto().setEstado(PeConstantes.PE_ESTADO_SUGERIDO_RECHAZADO);
            bean.getObjeto().setOrigenRechazo(PeConstantes.PE_SUGERIDO_ORIGEN_MANUAL);
            getPeAfiliadoSugeridoRemoto().rechazar(bean.getObjeto());
            String[] usuarioCrea = bean.getObjeto().getUsuarioCrea().trim().split("\\(");
            if (usuarioCrea != null && usuarioCrea.length > 0) {
                GnAlerta alerta = PeConstantes.alertaRechazoSugerido(getUsuarioRemoto().consultarPorUsuario(usuarioCrea[0]), bean.getObjeto().getAsegAfiliado(), bean.getObjeto().getObservacionRechazo(), bean.getObjeto().getPePrograma().getDescripcionPrograma(), bean.getObjeto().getAucHospitalizaciones().getId(), bean.getObjeto().getFechaHoraModifica());
                bean.auditoriaGuardar(alerta);
                getGnAlertaRemoto().insertar(alerta);
            }
            bean.addMensaje("El registro se rechazó correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void marcar(PeAfiliadoSugeridoBean bean) {
        try {
            bean.setObjeto(getPeAfiliadoSugeridoRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoAfiliadosPrograma(new PeAfiliadosPrograma());
            bean.getObjetoAfiliadosPrograma().setActivo(true);
            if (bean.getObjeto() != null) {
                bean.getObjetoAfiliadosPrograma().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
                bean.getObjetoAfiliadosPrograma().setPePrograma(getPeProgramaRemoto().consultar(bean.getObjeto().getPePrograma().getId()));
                //bean.getObjeto().getPePrograma().setUsuariosId(getUsuarioRemoto().consultar(bean.getObjeto().getPePrograma().getUsuariosId().getId()));
                bean.getObjetoAfiliadosPrograma().setCntPrestadorSede(new CntPrestadorSede());
                List<AsegAfiliadoContacto> contacto_afiliado = bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto();
                bean.setListaContactos(contacto_afiliado);
                List<PeTelefono> telefonos = getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliado().getId(), "");
                bean.setListaContactosPrograma(telefonos);
                //bean.getObjeto().setPeAfiliadoDiagnosticoList(getPeAfiliadoDiagnosticoRemoto().getDialDiagnosticosAfiliadoPrograma(bean.getObjeto().getId()));
                //bean.getObjeto().setGnUsuario(getUsuarioRemoto().consultar(bean.getObjeto().getGnUsuario().getId()));  
                bean.setListaPrestadorSede(getPeIpsProgramaRemoto().consultarPorProgramaActivo(bean.getObjeto().getPePrograma().getId()));
            }
            //region corporal 
            bean.setListaRegionCorporal(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_REGION_CORPORAL));
            bean.setHashRegionCorporal(PeConstantes.obtenerHashMaestro(bean.getListaRegionCorporal()));
            //medio diagnostico
            bean.setListaMedioDiagnostico(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_MEDIO_DIAGNOSTICO));
            bean.setHashMedioDiagnostico(PeConstantes.obtenerHashMaestro(bean.getListaMedioDiagnostico()));
            //Causas Activo
            bean.setListCausasActivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_CAUSA_ESTADO_ACTIVO));
            bean.setHashCausasActivo(PeConstantes.obtenerHashMaestro(bean.getListCausasActivo()));
            //tipos de contactos
            bean.setListaTiposContacto(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_CONTACTO));
            bean.setHashTiposContacto(PeConstantes.obtenerHashMaestro(bean.getListaTiposContacto()));
            bean.setListaContactosBorrar(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarMarca(PeAfiliadoSugeridoBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoAfiliadosPrograma());
            bean.auditoriaModificar(bean.getObjeto());
            if (bean.getObjetoAfiliadosPrograma().getAsegAfiliado() == null || bean.getObjetoAfiliadosPrograma().getAsegAfiliado().getPrimerNombre() == null || bean.getObjetoAfiliadosPrograma().getAsegAfiliado().getPrimerNombre().trim().isEmpty()) {
                bean.addError("Debe seleccionar un afiliado");
                return;
            }
            if (bean.getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList().isEmpty()) {
                bean.addError("Debe existir por lo menos un diagnostico");
                return;
            }
            List<PeAfiliadoDiagnostico> principal = new ArrayList();
            bean.getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList().stream().filter(diagnostico -> (diagnostico.getPrincipal() == true)).forEachOrdered(diagnostico -> {
                principal.add(diagnostico);
            });
            if (principal.isEmpty()) {
                bean.addError("Debe existir diagnostico principal.");
                return;
            }

            bean.getObjetoAfiliadosPrograma().setGnUsuario(bean.getObjetoAfiliadosPrograma().getPePrograma().getUsuariosId());
            PePrograma pe_programa = getPeProgramaRemoto().consultar(bean.getObjeto().getPePrograma().getId());
            List<PeAfiliadosPrograma> listAfiliadoProgramas = getPeAfiliadosProgramaRemoto().consultarAfiliados(bean.getObjetoAfiliadosPrograma().getAsegAfiliado().getId());

            if (pe_programa.getCantidadRegistro() == 0) {
                for (PeAfiliadosPrograma afiliadoPrograma : listAfiliadoProgramas) {
                    if (afiliadoPrograma.getPePrograma().getId().intValue() == bean.getObjeto().getPePrograma().getId().intValue()) {

                        if (afiliadoPrograma.getFechaFinPrograma() != null && afiliadoPrograma.getFechaFinPrograma().before(new Date())) {
                            bean.addError("El afiliado ya tiene una matrícula finalizada para este programa de " + pe_programa.getDescripcionPrograma() + ", para activarlo nuevamente debe editar el registro en el modulo afiliado.");
                            return;
                        } else {
                            if (afiliadoPrograma.getActivo()) {
                                bean.addError("El afiliado ya tiene una matrícula para este programa de "
                                        + pe_programa.getDescripcionPrograma() + " en estado ACTIVO; no se podrá generar un nuevo registro si el usuario se encuentra vigente en el programa.");
                                return;
                            } else {
                                bean.addError("El afiliado ya tiene una matrícula INACTIVA para este programa de "
                                        + pe_programa.getDescripcionPrograma() + ", para activarlo nuevamente debe editar el registro en el modulo afiliado");
                                return;
                            }
                        }
                    }
                }
            } else {
                Integer activo = getPeAfiliadosProgramaRemoto().consultarCantidadProgramaEstado(bean.getObjeto().getAsegAfiliado().getId(), bean.getObjeto().getPePrograma().getId(), PeConstantes.PE_PROGRAMA_ACTIVO);
                if (activo > 0) {
                    bean.addError("El afiliado ya tiene una matrícula para este programa de "
                            + pe_programa.getDescripcionPrograma() + " en estado ACTIVO; no se podrá generar un nuevo registro si el usuario se encuentra vigente en el programa.");
                    return;
                }
            }

            bean.auditoriaGuardar(bean.getObjetoAfiliadosPrograma());
            if (bean.getObjetoAfiliadosPrograma().getMaeRegionCorporalId() != null) {
                Maestro maeRegionCorporal = bean.getHashRegionCorporal().get(bean.getObjetoAfiliadosPrograma().getMaeRegionCorporalId());
                bean.getObjetoAfiliadosPrograma().setMaeRegionCorporalId(maeRegionCorporal.getId());
                bean.getObjetoAfiliadosPrograma().setMaeRegionCorporalCodigo(maeRegionCorporal.getValor());
                bean.getObjetoAfiliadosPrograma().setMaeRegionCorporalValor(maeRegionCorporal.getNombre());
            }

            if (bean.getObjetoAfiliadosPrograma().getMaeMedioDxId() != null) {
                Maestro maeMedioDiagnostico = bean.getHashMedioDiagnostico().get(bean.getObjetoAfiliadosPrograma().getMaeMedioDxId());
                bean.getObjetoAfiliadosPrograma().setMaeMedioDxId(maeMedioDiagnostico.getId());
                bean.getObjetoAfiliadosPrograma().setMaeMedioDxCodigo(maeMedioDiagnostico.getValor());
                bean.getObjetoAfiliadosPrograma().setMaeMedioDxValor(maeMedioDiagnostico.getNombre());
            }

            bean.getObjetoAfiliadosPrograma().setFuenteOrigen(PeConstantes.ORIGEN_SUGERIDO);
            bean.getObjetoAfiliadosPrograma().setPeAfiliadoSugerido(new PeAfiliadoSugerido(bean.getObjeto().getId()));
            bean.getObjetoAfiliadosPrograma().setAucHospitalizacion(bean.getObjeto().getAucHospitalizaciones());
            bean.getObjetoAfiliadosPrograma().setTipoPaciente(PeConstantes.PE_TIPO_PACIENTE_NUEVO);
            bean.getObjetoAfiliadosPrograma().setId(getPeAfiliadosProgramaRemoto().insertar(bean.getObjetoAfiliadosPrograma()));
            for (PeAfiliadoDiagnostico diagnostico : bean.getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList()) {
                bean.auditoriaGuardar(diagnostico);
                diagnostico.setPeAfiliadosProgramasId(new PeAfiliadosPrograma(bean.getObjetoAfiliadosPrograma().getId()));
                getPeAfiliadoDiagnosticoRemoto().insertar(diagnostico);
            }

            if (bean.getListaContactosBorrar().size() > 0) {
                try {
                    borrarContactoPrograma(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (bean.getListaContactosPrograma().size() > 0) {
                try {
                    guardarContactoAfiliados(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            bean.getObjeto().setEstado(PeConstantes.PE_ESTADO_SUGERIDO_MARCADO);
            getPeAfiliadoSugeridoRemoto().cambiarEstado(bean.getObjeto());

            bean.addMensaje("Se marco el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarContactoPrograma(PeAfiliadoSugeridoBean bean) throws Exception {
        if (bean.getListaContactosBorrar().size() > 0) {
            for (PeTelefono item : bean.getListaContactosBorrar()) {
                bean.auditoriaModificar(item);
                getPeTelefonosRemoto().eliminar(item.getId());
            }
        }
    }

    private void guardarContactoAfiliados(PeAfiliadoSugeridoBean bean) throws Exception {
        if (bean.getListaContactosPrograma().size() > 0) {
            List<PeTelefono> tel_agregar = new ArrayList();
            bean.getListaContactosPrograma().stream().filter(contacto -> (contacto.isNuevo() == true)).forEachOrdered(contacto -> {
                tel_agregar.add(contacto);
            });
            for (PeTelefono item : tel_agregar) {
                bean.auditoriaGuardar(item);
                getPeTelefonosRemoto().insertarTelefonosAfiliadosProgramas(item);
            }
        }
    }

    private boolean validarSexoAplica(PeAfiliadoSugeridoBean bean) {
        if (bean.getObjetoAfiliadosPrograma().getPePrograma().getSexoAplica() == PeConstantes.APLICA_SEXO_AMBOS) {
            return true;
        }
        if (!PeConstantes.getCodigoSexoAplica(bean.getObjeto().getPePrograma().getSexoAplica()).equalsIgnoreCase(bean.getObjeto().getAsegAfiliado().getMaeGeneroCodigo())) {
            return false;
        }
        return true;
    }

}
