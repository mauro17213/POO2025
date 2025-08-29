/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Diagnostico;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeDireccionado;
import com.saviasaludeps.savia.dominio.especial.PeDireccionadoItem;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.dominio.especial.ReporteDireccionado;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorContactoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeDireccionadoGestionRemoto;
import com.saviasaludeps.savia.negocio.especial.PeDireccionadoItemRemoto;
import com.saviasaludeps.savia.negocio.especial.PeDireccionadoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaDiagnosticosRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaTecnologiasRemoto;
import com.saviasaludeps.savia.negocio.especial.PeTelefonosRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import com.saviasaludeps.savia.solicitud.negocio.AuAnexo3SolicitudItemRemoto;
import com.saviasaludeps.savia.web.especial.bean.GestionDireccionadosBean;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author idbohorquez
 */
public class GestionDireccionadoServicioImpl extends AccionesBO implements GestionDireccionadoServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private PeProgramaDiagnosticosRemoto getPeProgramaDiagnosticosRemoto() throws Exception {
        return (PeProgramaDiagnosticosRemoto) RemotoEJB.getEJBRemoto("PeProgramaDiagnosticosServicio", PeProgramaDiagnosticosRemoto.class.getName());
    }

    private PeProgramaTecnologiasRemoto getPeProgramaTecnologiasRemoto() throws Exception {
        return (PeProgramaTecnologiasRemoto) RemotoEJB.getEJBRemoto("PeProgramaTecnologiasServicio", PeProgramaTecnologiasRemoto.class.getName());
    }

    private PeDireccionadoRemoto getPeDireccionadoRemoto() throws Exception {
        return (PeDireccionadoRemoto) RemotoEJB.getEJBRemoto("PeDireccionadoServicio", PeDireccionadoRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }

    private PeTelefonosRemoto getPeTelefonosRemoto() throws Exception {
        return (PeTelefonosRemoto) RemotoEJB.getEJBRemoto("PeTelefonosServicio", PeTelefonosRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private PeDireccionadoGestionRemoto getPeDireccionadoGestionRemoto() throws Exception {
        return (PeDireccionadoGestionRemoto) RemotoEJB.getEJBRemoto("PeDireccionadoGestionServicio", PeDireccionadoGestionRemoto.class.getName());
    }

    private MaDiagnosticoRemoto getDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("MaDiagnosticoServicio", MaDiagnosticoRemoto.class.getName());
    }

    private PeDireccionadoItemRemoto getPeDireccionadoItemRemoto() throws Exception {
        return (PeDireccionadoItemRemoto) RemotoEJB.getEJBRemoto("PeDireccionadoItemServicio", PeDireccionadoItemRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private AuAnexo3SolicitudItemRemoto getAuAnexo3SolicitudItemRemoto() throws Exception {
        return (AuAnexo3SolicitudItemRemoto) RemotoEJB.getEJBSolicitudRemoto("AuAnexo3ItemServicio", AuAnexo3SolicitudItemRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }

    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto("AuAnexo4Servicio", AuAnexo4Remoto.class.getName());
    }

    private CntPrestadorContactoRemoto getCntPrestadorContactoRemoto() throws Exception {
        return (CntPrestadorContactoRemoto) RemotoEJB.getEJBRemoto("CntPrestadorContactoServicio", CntPrestadorContactoRemoto.class.getName());
    }

    @Override
    public void Accion(GestionDireccionadosBean bean) {
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
                        case Url.ACCION_LISTAR:
                            listarGestion(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarGestion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    consulatarImprimirdireccionado(bean);
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            buscarDireccion(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            anularDireccion(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearGestionFecha(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarFechaAtencion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            rechazarDireccionadoItem(bean);
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(GestionDireccionadosBean bean) {
        try {
            if (bean.getHashTipoDocumento() == null) {
                bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
                bean.setHashTipoDocumentoEmpresa(PeConstantes.obtenerHashMaestro(bean.getListaTipoDocumento()));
            }
            if (bean.getListaSiNo() == null) {
                bean.setListaSiNo(PeConstantes.listaSino());
            }
            if (bean.getListaTipoGestion() == null) {
                bean.setListaTipoGestion(getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.PE_GESTION_TIPO_DIRECCIONADO));
                bean.setHashTipoGestion(PeConstantes.obtenerHashMaestro(bean.getListaTipoGestion()));
            }
            if (bean.getListaEstadosDireccionado() == null) {
                bean.setListaEstadosDireccionado(PeConstantes.listaEstadoDireccionado());
            }
            if (bean.getHashUbicaciones() == null) {
                bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(GestionDireccionadosBean bean) {
        try {
            bean.setObjeto(getPeDireccionadoRemoto().consultar(bean.getObjeto().getId()));

            if (bean.getHashTipoDocumentoEmpresa() == null) {
                bean.setListaTipoDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
                bean.setHashTipoDocumentoEmpresa(PeConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoEmpresa()));
            }
            if (bean.getListaTipoGestion() == null) {
                bean.setListaTipoGestion(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_GESTION_TIPO));
                bean.setHashTipoGestion(PeConstantes.obtenerHashMaestro(bean.getListaEstadosDireccionado()));
            }
            bean.getObjeto().setListaGestion(getPeDireccionadoGestionRemoto().listaGestionesDireccion(bean.getObjeto().getId()));
            bean.getObjeto().setAsegAfiliadosId(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
            List<PeTelefono> contactosPrograma = getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliadosId().getId(), "");
            bean.setListaContactosPrograma(contactosPrograma);
            bean.setObjetoAnexo3(getAuAnexo3Remoto().consultar(bean.getObjeto().getAuAnexos3Id().getId()));
            bean.getObjetoAnexo3().setPeProgramaId(getPeProgramaRemoto().consultar(bean.getObjeto().getPeProgramasId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void listar(GestionDireccionadosBean bean) {
        try {
            bean.getParamConsulta().setEmpresaId(bean.getParamConsulta().getEmpresaId());
            bean.getParamConsulta().setCantidadRegistros(getPeDireccionadoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPeDireccionadoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void listarGestion(GestionDireccionadosBean bean) {
        try {
            bean.setObjeto(getPeDireccionadoRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto() != null) {
                if (bean.getHashTiposGenero() == null) {
                    //Maestro Genero
                    bean.setListaTiposGenero(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
                    bean.setHashTiposGenero(new HashMap());
                    bean.getListaTiposGenero().forEach(m -> {
                        bean.getHashTiposGenero().put(m.getId(), m);
                    });
                }
                if (bean.getHashEstadosAfiliacion() == null) {
                    //Maestro Estado afiliación
                    bean.setListaEstadosAfiliacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
                    bean.setHashEstadosAfiliacion(new HashMap());
                    bean.getListaEstadosAfiliacion().forEach(m -> {
                        bean.getHashEstadosAfiliacion().put(m.getId(), m);
                    });
                }
                if (bean.getHashDiagnostico() == null) {
                    //Maestro Diagnostico
                    bean.setListaDiagnostico(getDiagnosticoRemoto().consultarTodos());
                    bean.setHashDiagnostico(new HashMap());
                    for (MaDiagnostico m : bean.getListaDiagnostico()) {
                        bean.getHashDiagnostico().put(m.getId(), m);
                    }
                }

                bean.getObjeto().setAsegAfiliadosId(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
                bean.getObjeto().setPeProgramasId(getPeProgramaRemoto().consultar(bean.getObjeto().getPeProgramasId().getId()));
                bean.getObjeto().getPeProgramasId().setUsuariosId(getUsuarioRemoto().consultar(bean.getObjeto().getPeProgramasId().getUsuariosId().getId()));
                bean.getObjeto().getAsegAfiliadosId().setAfiliacionUbicacion(bean.getHashUbicaciones().get(bean.getObjeto().getAsegAfiliadosId().getAfiliacionUbicacion().getId()));
                List<AsegAfiliadoContacto> contactoAfiliado = bean.getObjeto().getAsegAfiliadosId().getListaAsegAfiliadoContacto();
                bean.setListaContactosAfiliado(contactoAfiliado);
                List<PeTelefono> contactosPrograma = getPeTelefonosRemoto().getListaContatoAfiliado(bean.getObjeto().getAsegAfiliadosId().getId(), "");
                bean.setListaContactosPrograma(contactosPrograma);
                bean.getObjeto().setListaGestion(getPeDireccionadoGestionRemoto().listaGestionesDireccion(bean.getObjeto().getId()));
                bean.setObjetoAnexo3(getAuAnexo3Remoto().consultar(bean.getObjeto().getAuAnexos3Id().getId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarGestion(GestionDireccionadosBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoGestion());
            Maestro maeTipoGestion = new Maestro();
            maeTipoGestion = bean.getHashTipoGestion().get(bean.getObjetoGestion().getMaeTipoId());
            bean.getObjetoGestion().setPeDireccionadosId(new PeDireccionado(bean.getObjeto().getId()));
            bean.getObjetoGestion().setMaeTipoCodigo(maeTipoGestion.getValor());
            bean.getObjetoGestion().setMaeTipoValor(maeTipoGestion.getNombre());
            bean.getObjetoGestion().setId(getPeDireccionadoGestionRemoto().insertar(bean.getObjetoGestion()));
            bean.getObjeto().getListaGestion().add(bean.getObjetoGestion());

            //Se actualiza el estado de Direccionado a EN GESTION
            bean.getObjeto().setEstado(PeConstantes.PE_DIRECCIONADO_EN_GESTION);
            getPeDireccionadoRemoto().actualizarEstado(bean.getObjeto());

            bean.addMensaje("El registro se creo correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void buscarDireccion(GestionDireccionadosBean bean) {
        try {
            bean.setObjeto(getPeDireccionadoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void anularDireccion(GestionDireccionadosBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setEstado(PeConstantes.PE_DIRECCIONADO_ANULADO);
            getPeDireccionadoRemoto().anular(bean.getObjeto());
            bean.addMensaje("El registro se anuló correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearGestionFecha(GestionDireccionadosBean bean) {
        try {
            bean.setObjeto(getPeDireccionadoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarFechaAtencion(GestionDireccionadosBean bean) {
        try {

            if (bean.getObjetoDireccionadoItem().getFechaPrestacion() == null) {
                bean.addError("No se indico la fecha de prestación");
                return;
            }
            if (bean.getObjetoDireccionadoItem().getObservacion().trim().isEmpty()) {
                bean.addError("No se indico la observación de la fecha de atención. ");
                return;
            }
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjetoDireccionadoItem().setEstado(PeConstantes.PE_DIRECCIONADO_ITEM_GESTIONADO);
            getPeDireccionadoItemRemoto().establecerFechaGestion(bean.getObjetoDireccionadoItem());
            bean.setObjeto(getPeDireccionadoRemoto().consultar(bean.getObjeto().getId()));

            int cantidadItems = bean.getObjeto().getListDireccionadoItems().size();
            int cantidadItemGestioando = bean.getObjeto().getListDireccionadoItems().stream().map(n -> n.getEstado() > 0).collect(Collectors.toList()).size();
            if (cantidadItemGestioando == cantidadItems) {
                //Se actualiza el estado de Direccionado a GESTIONADO
                bean.getObjeto().setEstado(PeConstantes.PE_DIRECCIONADO_GESTIONADO);
                getPeDireccionadoRemoto().actualizarEstado(bean.getObjeto());
            } else if (cantidadItemGestioando > 0) {
                //Se actualiza el estado de Direccionado a EN GESTION
                bean.getObjeto().setEstado(PeConstantes.PE_DIRECCIONADO_EN_GESTION);
                getPeDireccionadoRemoto().actualizarEstado(bean.getObjeto());
            }
            bean.addMensaje("Se establecio fecha de atención correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void rechazarDireccionadoItem(GestionDireccionadosBean bean) {
        try {

            if (bean.getObjetoDireccionadoItem().getObservacion().trim().isEmpty()) {
                bean.addError("No se indico la observación de la fecha de atención. ");
                return;
            }
            bean.auditoriaModificar(bean.getObjeto());
            bean.auditoriaModificar(bean.getObjetoDireccionadoItem());
            bean.getObjetoDireccionadoItem().setEstado(PeConstantes.PE_DIRECCIONADO_ITEM_RECHAZADO);
            bean.getObjetoDireccionadoItem().setFechaPrestacion(new Date());
            getPeDireccionadoItemRemoto().establecerFechaGestion(bean.getObjetoDireccionadoItem());
            bean.setObjeto(getPeDireccionadoRemoto().consultar(bean.getObjeto().getId()));

            int cantidadItems = bean.getObjeto().getListDireccionadoItems().size();
            int cantidadItemGestioando = bean.getObjeto().getListDireccionadoItems().stream().map(n -> n.getEstado() > 0).collect(Collectors.toList()).size();
            if (cantidadItemGestioando == cantidadItems) {
                //Se actualiza el estado de Direccionado a GESTIONADO
                bean.getObjeto().setEstado(PeConstantes.PE_DIRECCIONADO_GESTIONADO);
                getPeDireccionadoRemoto().actualizarEstado(bean.getObjeto());
            } else if (cantidadItemGestioando > 0) {
                //Se actualiza el estado de Direccionado a EN GESTION
                bean.getObjeto().setEstado(PeConstantes.PE_DIRECCIONADO_EN_GESTION);
                getPeDireccionadoRemoto().actualizarEstado(bean.getObjeto());
            }
            bean.addMensaje("Se rechazo la tecnología correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consulatarImprimirdireccionado(GestionDireccionadosBean bean) {
        try {
            bean.setObjeto(getPeDireccionadoRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoAnexo3(getAuAnexo3Remoto().consultar(bean.getObjeto().getAuAnexos3Id().getId()));
            bean.getObjeto().setReporteDireccionados(getReporteDireccionados(bean));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private List<ReporteDireccionado> getReporteDireccionados(GestionDireccionadosBean bean) {
        List<ReporteDireccionado> listaReportes = new ArrayList<>();
        try {
            AuAnexo3 anexo3 = bean.getObjetoAnexo3();
            Empresa savia = getEmpresaRemoto().consultar(1);
            CntPrestadorSede cntPrestadorSede = getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedesId().getId());
            List<CntPrestadorContacto> contactosPrestador = getCntPrestadorContactoRemoto().consultarPorCntContratoSede(cntPrestadorSede.getCntPrestador().getId(), cntPrestadorSede.getId());
            List<CntPrestadorContacto> contactosPrestadorAux = new ArrayList();
            contactosPrestador.stream().filter(contacto -> ((contacto.getMaeTipoContactoCodigo().equals("1") || contacto.getMaeTipoContactoCodigo().equals("2")) && contacto.getMaeAreaContactoCodigo().equals("1"))).forEachOrdered(contacto -> {
                CntPrestadorContacto aux = new CntPrestadorContacto();
                aux.setContacto(contacto.getContacto());
                contactosPrestadorAux.add(aux);
            });

            List<PeDireccionadoItem> items = getPeDireccionadoItemRemoto().getDireccionadoItems(bean.getObjeto().getId());
            for (PeDireccionadoItem item : items) {
                ReporteDireccionado reporte = new ReporteDireccionado();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat horaFormat = new SimpleDateFormat("HH:mm");

                reporte.setStrNumeroAutorizacion(anexo3.getId().toString());
                reporte.setStrFechaAutorizacion(dateFormat.format(bean.getObjeto().getFechaHoraCrea()));
                reporte.setStrHoraAutorizacion(horaFormat.format(bean.getObjeto().getFechaHoraCrea()));
                //Entidad Responsable
                reporte.setStrEntidadResponsable(savia.getRazonSocial());
                String codigo = "EPS040";
                if (anexo3.getAsegAfiliadoId().getRegimen().equals("1")) {
                    codigo = "EPSS40";
                }
                reporte.setStrCodigoEntidad(codigo);
                //Prestador
                reporte.setStrNombrePrestador(cntPrestadorSede.getNombreSede());
                reporte.setStrTipoDocPrestador(cntPrestadorSede.getCntPrestador().getMaeTipoDocumentoCodigo());
                reporte.setStrNumDocPrestador(cntPrestadorSede.getCntPrestador().getNumeroDocumento());
                reporte.setStrCodigoPrestador(cntPrestadorSede.getCodigoHabilitacionSede());
                reporte.setStrCorreoPrestador(cntPrestadorSede.getCorreoElectronico());
                if (contactosPrestadorAux.isEmpty()) {
                    reporte.setStrTelefono1Prestador("");
                    reporte.setStrTelefono2Prestador("");
                } else {
                    reporte.setStrTelefono1Prestador(contactosPrestadorAux.get(0).getContacto());
                    if (contactosPrestadorAux.size() > 1) {
                        reporte.setStrTelefono2Prestador(contactosPrestadorAux.get(1).getContacto());
                    } else {
                        reporte.setStrTelefono2Prestador("");
                    }
                }
                reporte.setStrDireccionPrestador(cntPrestadorSede.getDireccion());
                reporte.setStrDepartamentoPrestador(bean.obtenerDepartamento(cntPrestadorSede.getUbicacionId()));
                reporte.setStrMunicipioPrestador(bean.obtenerMunicipio(cntPrestadorSede.getUbicacionId()));
                //Afiliado
                AsegAfiliadoContacto telefonoAfiliado = anexo3.getAsegAfiliadoId().getListaAsegAfiliadoContacto().stream().filter(iten -> iten.getMaeTipoContactoCodigo().equals("1"))
                        .findAny()
                        .orElse(null);
                AsegAfiliadoContacto celularAfiliado = anexo3.getAsegAfiliadoId().getListaAsegAfiliadoContacto().stream().filter(iten -> iten.getMaeTipoContactoCodigo().equals("2"))
                        .findAny()
                        .orElse(null);

                reporte.setStrPrimerApellidoPaciente(anexo3.getAsegAfiliadoId().getPrimerApellido());
                reporte.setStrSegundoApellidoPaciente(anexo3.getAsegAfiliadoId().getSegundoApellido());
                reporte.setStrPrimerNombrePaciente(anexo3.getAsegAfiliadoId().getPrimerNombre());
                reporte.setStrSegundoNombrePaciente(anexo3.getAsegAfiliadoId().getSegundoNombre());
                reporte.setStrTipoDocPaciente(anexo3.getAsegAfiliadoId().getMaeTipoDocumentoCodigo());
                reporte.setStrNumDocPaciente(anexo3.getAsegAfiliadoId().getNumeroDocumento());
                reporte.setStrFechaNacimientoPaciente(dateFormat.format(anexo3.getAsegAfiliadoId().getFechaNacimiento()));
                reporte.setStrHoraNacimientoPaciente(horaFormat.format(anexo3.getAsegAfiliadoId().getFechaNacimiento()));
                reporte.setStrDireccionPaciente(anexo3.getAsegAfiliadoId().getDireccionResidencia());
                reporte.setStrTelefonoFijoPaciente(telefonoAfiliado != null ? telefonoAfiliado.getNumeroContacto() : "");
                reporte.setStrDepartementoPaciente(bean.obtenerDepartamento(anexo3.getAsegAfiliadoId().getPrimariaPrestadorSede().getUbicacionId()));
                reporte.setStrMunicipioPaciente(bean.obtenerMunicipio(anexo3.getAsegAfiliadoId().getPrimariaPrestadorSede().getUbicacionId()));
                reporte.setStrTelefonoCelularPaciente(celularAfiliado != null ? celularAfiliado.getNumeroContacto() : "");
                reporte.setStrCorreoPaciente(anexo3.getAsegAfiliadoId().getEmail());
                reporte.setStrUbicacionPaciente("");
                //Item
                reporte.setStrManejoIntegral(anexo3.getMaeGuiaManejoIntegralValor());
                reporte.setStrCodigoCups(item.getAuAnexo3ItemsId().getMaTecnologiaCodigo());
                reporte.setStrCantidad("" + item.getAuAnexo3ItemsId().getCantidadSolicitada());
                reporte.setStrDescripcion(item.getAuAnexo3ItemsId().getMaTecnologiaValor());
                reporte.setStrNumeroOrigen(anexo3.getId().toString());

                reporte.setStrFechaOrigen(dateFormat.format(bean.getObjeto().getFechaHoraCrea()));
                reporte.setStrHoraOrigen(horaFormat.format(bean.getObjeto().getFechaHoraCrea()));
                reporte.setStrPorcentajeAutorizado("");
                reporte.setStrSemanaPaciente("");
                reporte.setStrValor("");
                reporte.setStrAplicaCobro(anexo3.getCopago() != null ? (anexo3.getCopago() ? "SI" : "NO") : "");

                reporte.setStrNombreAutoriza(anexo3.getUsuarioCrea());
                reporte.setStrCargoAutoriza("AUTORIZADOR SAVIASALUD");
                reporte.setStrTelefonoAutoriza("018000423683");

                reporte.setStrDias("");
                reporte.setStrCama(anexo3.getCama());
                reporte.setStrServicio(anexo3.getMaServicioHabilitadoValor());

                for (AuAnexo3Diagnostico diagno : anexo3.getAuAnexo3DiagnosticosList()) {
                    if (diagno.getPrincipal()) {
                        reporte.setStrDiagnosticoPrincipal(diagno.getMaDiagnosticosCodigo() + " - " + diagno.getMaDiagnosticosValor());
                    }
                }

                reporte.setStrValor("");
                reporte.setStrPorcentaje("%");
                reporte.setStrCuotaModeradora(anexo3.getCuotaModeradora() != null ? (anexo3.getCuotaModeradora() == true ? "SI" : "NO") : "");
                reporte.setStrCopago(anexo3.getCopago() == null || anexo3.getCopago() == false ? "NO" : "SI");
                reporte.setStrCuotaRecuperacion(anexo3.getCuotaRecuperacion() == null || anexo3.getCuotaRecuperacion() == false ? "NO" : "SI");
                reporte.setStrExcentoCuota("NO");
                reporte.setStrObservacion("Servicio incluido en la RIA o Programa Especial");
                listaReportes.add(reporte);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return listaReportes;
    }

}
