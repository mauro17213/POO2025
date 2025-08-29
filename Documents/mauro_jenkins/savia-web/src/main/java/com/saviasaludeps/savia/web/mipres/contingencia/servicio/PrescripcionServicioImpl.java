/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.contingencia.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcProgramacionEntrega;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionDetalleRemoto;
import com.saviasaludeps.savia.negocio.mipres.contingencia.EntregaRemoto;
import com.saviasaludeps.savia.negocio.mipres.contingencia.PrescripcionAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.mipres.contingencia.PrescripcionRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.mipres.contingencia.bean.PrescripcionBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author raul-palacios
 */
public class PrescripcionServicioImpl extends AccionesBO implements PrescripcionServicioIface {

    private PrescripcionRemoto getPrescripcionRemoto() throws Exception {
        return (PrescripcionRemoto) RemotoEJB.getEJBRemoto("PrescripcionServicio", PrescripcionRemoto.class.getName());
    }

    private PrescripcionAdjuntoRemoto getPrescripcionAdjuntoRemoto() throws Exception {
        return (PrescripcionAdjuntoRemoto) RemotoEJB.getEJBRemoto("PrescripcionAdjuntoServicio", PrescripcionAdjuntoRemoto.class.getName());
    }

    private EntregaRemoto getEntragaRemoto() throws Exception {
        return (EntregaRemoto) RemotoEJB.getEJBRemoto("EntregaServicio", EntregaRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception {
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto("GnCorreoEnvioServicio", GnCorreoEnvioRemoto.class.getName());
    }

    private MpPrescripcionDetalleRemoto getMpPrescripcionDetalleRemoto() throws Exception {
        return (MpPrescripcionDetalleRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionDetalleServicio", MpPrescripcionDetalleRemoto.class.getName());
    }

    @Override
    public void Accion(PrescripcionBean bean) {
        if (super.ValidarSesion(bean)) {
            bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            listarIps(bean);
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                //Gestion
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            gestionar(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            guardarGestion(bean);
                            break;
                    }
                    break;
                //Entregas
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarEntregas(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearEntrega(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarEntrega(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(PrescripcionBean bean) {
        try {
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setEmpresaId(null);
            } else {
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(0).setCantidadRegistros(getPrescripcionRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getPrescripcionRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(PrescripcionBean bean) {
        try {
            bean.setObjeto(getPrescripcionRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setListaMpcPrescripcionAdjuntos(getPrescripcionAdjuntoRemoto().consultarLista(bean.getObjeto().getId()));
            bean.setObjetoHistorico(getPrescripcionRemoto().consultarHistorico(bean.getObjeto().getId()));
            bean.getObjeto().setListaMpcProgramacionEntregas(getEntragaRemoto().consultarLista(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public boolean conultarHistorico(int id) {
        boolean resul = false;
        try {
            resul = (getPrescripcionRemoto().consultarHis(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resul;
    }

    private void crear(PrescripcionBean bean) {
        try {
            bean.setObjeto(new MpcPrescripcion());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(PrescripcionBean bean) {
        try {
            //Completar Historicos
            Maestro tipoDoc = bean.getHashTipoDocumentoAfiliado().get(bean.getObjeto().getMaeAfiliadoTipoDocumentoId());
            bean.getObjeto().setMaeAfiliadoTipoDocumentoCodigo(tipoDoc.getValor());
            bean.getObjeto().setMaeAfiliadoTipoDocumentoValor(tipoDoc.getNombre());
            bean.auditoriaGuardar(bean.getObjeto());
            //Insertar dato
            bean.getObjeto().setId(getPrescripcionRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(PrescripcionBean bean) {
        try {
            bean.setObjeto(getPrescripcionRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoHistorico(getPrescripcionRemoto().consultarH(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(PrescripcionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            //Completar tipo documento afiliado
            Maestro tipoDocAfil = bean.getHashTipoDocumentoAfiliado().get(bean.getObjeto().getMaeAfiliadoTipoDocumentoId());
            bean.getObjeto().setMaeAfiliadoTipoDocumentoCodigo(tipoDocAfil.getValor());
            bean.getObjeto().setMaeAfiliadoTipoDocumentoValor(tipoDocAfil.getNombre());
            //Completar tipo documento Prescriptor
            Maestro tipoDocPresc = bean.getHashTipoDocumentoPrestador().get(bean.getObjeto().getMaePrescriptorTipoDocumentoId());
            bean.getObjeto().setMaePrescriptorTipoDocumentoCodigo(tipoDocPresc.getValor());
            bean.getObjeto().setMaePrescriptorTipoDocumentoValor(tipoDocPresc.getNombre());
            //Actualizar dato
            bean.auditoriaModificar(bean.getObjeto());
            //Actualizar dato           
            getPrescripcionRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Registro actualizado de manera exitosa");
            bean.auditoriaGuardar(bean.getObjetoHistorico());
            bean.auditoriaModificar(bean.getObjetoHistorico());
            getPrescripcionRemoto().insertarHistorico(bean.getObjetoHistorico());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void gestionar(PrescripcionBean bean) {
        try {
            bean.setObjeto(getPrescripcionRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setListaMpcPrescripcionAdjuntos(getPrescripcionAdjuntoRemoto().consultarLista(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarGestion(PrescripcionBean bean) {
        try {
            if (bean.getObjeto().getEstado() == MpcPrescripcion.ESTADO_DIRECCIONADO) {
                bean.getObjeto().setConsecutivo(genConsecutivo(bean));
                bean.getObjeto().setRechazoJustificacion(null);
                //enviarCorreoElectronico(bean);
            } else {
                bean.getObjeto().setNumeroEntregas((short) 0);
                bean.getObjeto().setPeriodicidad((short) 0);
                bean.getObjeto().setCantidad(0);
            }
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                getPrescripcionRemoto().direccionar(bean.getObjeto());
                if (bean.getObjeto().getEstado() == MpcPrescripcion.ESTADO_DIRECCIONADO) {
                    String mensaje = inicializarNotificacion(bean);
                    String correoDestino = consultarCorreoPrestadorSede(bean.getObjeto().getCntDireccionadoPrestadorSede().getCodigoPrestador());
                    String encabezadoDestino = "DIRECCIONADO";
                    GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_MIPRES, correoDestino, encabezadoDestino, mensaje, GnCorreoEnvio.TIPO_TEXTO);
                    try {
                        getGnCorreoEnvioRemoto().insertar(envio);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    bean.addMensaje("Se direccionó un registro de manera exitosa");
                } else if (bean.getObjeto().getEstado() == MpcPrescripcion.ESTADO_NO_DIRECCIONADO) {
                    String mensajeDestino = bean.getRechazoJustificacionCorreo() + " " + bean.getObjeto().getRechazoJustificacion();
                    String correoDestino = bean.getObjeto().getPrescriptorCorreoElectronico();
                    String encabezadoDestino = "NO DIRECCIONADO";
                    GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_MIPRES, correoDestino, encabezadoDestino, mensajeDestino, GnCorreoEnvio.TIPO_TEXTO);
                    try {
                        getGnCorreoEnvioRemoto().insertar(envio);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    bean.addMensaje("Se rechazo un registro de manera exitosa");
                }

            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarEntregas(PrescripcionBean bean) {
        try {
            bean.setObjeto(getPrescripcionRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setListaMpcProgramacionEntregas(getEntragaRemoto().consultarLista(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearEntrega(PrescripcionBean bean) {
        try {
            bean.setObjetoEntrega(new MpcProgramacionEntrega());
            bean.getObjetoEntrega().setEmpresa(bean.getConexion().getEmpresa());
            bean.getObjetoEntrega().setMpcPrescripcion(new MpcPrescripcion(bean.getObjeto().getId()));
            List<MpcProgramacionEntrega> listaEntregas = getEntragaRemoto().consultarLista(bean.getObjeto().getId());
            int cantidadEntrega = 0;
            if (!listaEntregas.isEmpty()) {
                for (MpcProgramacionEntrega entrega : listaEntregas) {
                    cantidadEntrega += entrega.getCantidad();
                }
            }
            if (listaEntregas.size() >= bean.getObjeto().getNumeroEntregas()) {
                bean.addMensaje("Supero el número de entregas programadas");
                bean.setFalloEntrega(true);
            }
            if (cantidadEntrega >= bean.getObjeto().getCantidad()) {
                bean.addMensaje("Supero la cantidad programada");
                bean.setFalloEntrega(true);
            }
            if (!bean.isError() && !bean.isFalloEntrega()) {
                bean.getObjetoEntrega().setNumeroEntrega((short) (listaEntregas.size() + 1));
                bean.getObjetoEntrega().setNumeroEntregaTotal(bean.getObjeto().getNumeroEntregas());
                bean.setObjeto(getPrescripcionRemoto().consultar(bean.getObjeto().getId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarEntrega(PrescripcionBean bean) {
        try {
            bean.getObjetoEntrega().setEmpresa(bean.getConexion().getEmpresa());
            bean.getObjetoEntrega().setMpcPrescripcion(new MpcPrescripcion(bean.getObjeto().getId()));
            if (bean.getObjetoEntrega().getEstado() == 2) {
                bean.getObjetoEntrega().setCantidad(0);
            }
            List<MpcProgramacionEntrega> listaEntregas = getEntragaRemoto().consultarLista(bean.getObjeto().getId());
            int cantidadEntrega = bean.getObjetoEntrega().getCantidad();
            if (!listaEntregas.isEmpty()) {
                for (MpcProgramacionEntrega entrega : listaEntregas) {
                    cantidadEntrega += entrega.getCantidad();
                }
            }
            if (listaEntregas.size() >= bean.getObjeto().getNumeroEntregas()) {
                bean.addError("Supero el número de entregas programadas");
                bean.setFalloEntrega(true);
            }
            if (cantidadEntrega > bean.getObjeto().getCantidad()) {
                bean.addError("Supero la cantidad programada");
                bean.setFalloEntrega(true);
            }

            if (!bean.isError() && !bean.isFalloEntrega()) {
                bean.getObjetoEntrega().setNumeroEntrega((short) (listaEntregas.size() + 1));
                bean.getObjetoEntrega().setNumeroEntregaTotal(bean.getObjeto().getNumeroEntregas());
                bean.auditoriaGuardar(bean.getObjetoEntrega());
                bean.getObjetoEntrega().setId(getEntragaRemoto().insertar(bean.getObjetoEntrega()));
                listaEntregas.add(bean.getObjetoEntrega());
                bean.addMensaje("Se creo un registro de entrega manera exitosa");
            }
            bean.getObjeto().setListaMpcProgramacionEntregas(listaEntregas);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(PrescripcionBean bean) {
        try {
            bean.setObjeto(getPrescripcionRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(PrescripcionBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargasInicial(PrescripcionBean bean) {
        try {
            //Afiliados
            bean.setListaTipoDocumentoAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumentoAfiliado(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoAfiliado()));
            //Prestadores
            bean.setListaTipoDocumentoPrestador(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setHashTipoDocumentoPrestador(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoPrestador()));
            //Ubicaciones
            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashCiudades(UbicacionSingle.getInstance().getHashMunicipios());
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

    public String genConsecutivo(PrescripcionBean bean) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String cons = sdf.format(bean.getObjeto().getFechaHora());
        cons += (bean.getObjeto().isRecobrante()) ? "0" : "1";
        cons += Util.completarCaracteres(String.valueOf(bean.getObjeto().getId()), '0', 7);
        return cons;
    }

    private void listarIps(PrescripcionBean bean) {
        try {
            if (bean.getObjeto().getMaTecnologiaId() != null) {
                bean.getParamConsulta(1).setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaSedePrestador(bean.getParamConsulta(1)));
                bean.setListaIps(getMpPrescripcionDetalleRemoto().consultarListaSedePrestador(bean.getParamConsulta(1)));
            } else {
                bean.addError("Se debe seleccionar la tecnologia");
            }

        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar los proveedores, favor contactar al administrador");
        }
    }

    private void enviarCorreoElectronico(PrescripcionBean bean) {
        try {
            GnCorreoEnvio envio = new GnCorreoEnvio();
            envio.setEstado(GnCorreoEnvio.ESTADO_PENDIENTE);
            envio.setOrigen(GnCorreoEnvio.ORIGEN_MIPRES);
            envio.setDestino(bean.getObjeto().getPrescriptorCorreoElectronico());
            envio.setTipo(GnCorreoEnvio.TIPO_TEXTO);
            envio.setEncabezado("MIPRES Radicado");
            envio.setDetalle("El MIPRES con radicado " + bean.getObjeto().getConsecutivo() + ", fue radicado con existo al proveedor " + bean.getObjeto().getCntDireccionadoPrestadorSede().getNombreSede());
            bean.auditoriaGuardar(envio);
            getGnCorreoEnvioRemoto().insertar(envio);
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar el correo favor contactar al administrador");
        }
    }

    public String inicializarNotificacion(PrescripcionBean bean) {
        StringBuilder mensaje = new StringBuilder();
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMM yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaFormateada = formatoHora.format(fechaActual);

        mensaje.append("Se creo un Mipres de manera exitosa ").append("\n");
        mensaje.append("•  Número : ").append(bean.getObjeto().getId()).append("\n");
        mensaje.append("•  Consecutivo : ").append(bean.getObjeto().getConsecutivo()).append("\n");
        mensaje.append("").append("\n");
        mensaje.append("•  Fecha : ").append(fechaFormateada).append("\n");
        mensaje.append("•  Hora : ").append(horaFormateada).append("\n");
        mensaje.append("").append("\n");
        mensaje.append("•  Tecnologia : ").append(obtenerTecnologia(bean.getObjeto().getTipoTecnologia())).append("\n");
        mensaje.append("•  Nombre Tecnologia : ").append(bean.getObjeto().getMaTecnologiaValor()).append("\n");
        mensaje.append("•  Cantidad : ").append(bean.getObjeto().getCantidad()).append("\n");
        mensaje.append("•  Entregas : ").append(bean.getObjeto().getNumeroEntregas()).append("\n");
        mensaje.append("•  Paciente : ");
        if (bean.getObjeto().getAfiliadoPrimerNombre() != null) {
            mensaje.append(bean.getObjeto().getAfiliadoPrimerNombre());
        }
        if (bean.getObjeto().getAfiliadoSegundoNombre() != null) {
            mensaje.append(" ").append(bean.getObjeto().getAfiliadoSegundoNombre());
        }
        if (bean.getObjeto().getAfiliadoPrimerApellido() != null) {
            mensaje.append(" ").append(bean.getObjeto().getAfiliadoPrimerApellido());
        }
        if (bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoCodigo() != null) {
            mensaje.append(" (").append(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoCodigo());
        }
        mensaje.append(" ").append(bean.getObjeto().getAsegAfiliado().getNumeroDocumento()).append(")\n");
        mensaje.append("•  Ámbito : ").append(obtenerAmbito(bean.getObjeto().getAmbito(), bean.getObjeto().getReferenciaAmbitoAtencion())).append("\n");
        mensaje.append("").append("\n");
        mensaje.append("").append("\n");
        mensaje.append("•  SAVIA SALUD E.P.S ").append("\n");
        mensaje.append("").append("\n");

        return mensaje.toString();
    }

    public String obtenerTecnologia(Short valor) {
        String resultado = "";
        if (valor != null) {

            switch (valor) {
                case 1:
                    resultado = "Medicamento/s ";
                    break;
                case 2:
                    resultado = "procedimiento/s ";
                    break;
                case 3:
                    resultado = "dispositivo/s ";
                    break;
                case 4:
                    resultado = "Producto/s Nutricional/es ";
                    break;
                case 5:
                    resultado = "Servicio/s Complementario/s";
                    break;
                default:
                    resultado = "";
                    break;
            }
        }
        return resultado;
    }

    public String obtenerAmbito(String id, boolean referencia) {
        String resultado;
        switch (id) {
            case "11":
                resultado = "Ambulatorio - Priorizado";
                break;
            case "12":
                resultado = "Ambulatorio - No Priorizado";
                break;
            case "21":
                resultado = "Hospitalario - Domiciliario";
                break;
            case "22":
                resultado = "Hospitalario - Internacion";
                break;
            case "30":
                resultado = "Urgencias";
                break;
            default:
                resultado = "";
                break;
        }
        if (referencia == true) {
            resultado = resultado + " - Referencia Contrarreferencia";
        }
        return resultado;

    }

    public String consultarCorreoPrestadorSede(String documento) {
        String Correo = "";
        try {
            Correo = (getMpPrescripcionDetalleRemoto().consultarCorreoPrestadorDir(documento));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Correo == null) {
            Correo = "brayanstevengomezrivera@gmail.com";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MENSAJE", "se realizo el direccionamiento pero no fue posible enviar la notificacion al prestador ya que no cuenta con e-mail disponible para notificar "));
        }
        return Correo;

    }
}
