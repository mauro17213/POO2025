package com.saviasaludeps.savia.web.juridico.servicio;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjComite;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoGarantia;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoIndicador;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoObligacion;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSeguimiento;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSupervisor;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoEjecucion;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoProcesoDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjLinea;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosi;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.dominio.juridico.CntjUsuarioGrupo;
import com.saviasaludeps.savia.negocio.administracion.GnAlertaRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjCampoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoGarantiaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoIndicadorRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoObligacionRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoSupervisorRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjDocumentoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoEjecucionRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoProcesoDocumentoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjExpedienteRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjComiteRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjContratoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjEstadoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjLineaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjOtrosiRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjProcesoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjTerceroRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjUsuarioGrupoRemoto;
import com.saviasaludeps.savia.web.juridico.bean.TareaBean;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjUtilidades;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Fichero;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import org.apache.commons.io.IOUtils;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author idbohorquez
 */
public class TareaServicioImpl extends AccionesBO implements TareaServicioIface {

    private CntjExpedienteRemoto getCntjExpedienteRemoto() throws Exception {
        return (CntjExpedienteRemoto) RemotoEJB.getEJBRemoto("CntjExpedienteServicio", CntjExpedienteRemoto.class.getName());
    }

    private CtnjUsuarioGrupoRemoto getCtnjUsuarioGrupoRemoto() throws Exception {
        return (CtnjUsuarioGrupoRemoto) RemotoEJB.getEJBRemoto("CntjUsuarioGrupoServicio", CtnjUsuarioGrupoRemoto.class.getName());
    }

    private CtnjEstadoRemoto getCtnjEstadoRemoto() throws Exception {
        return (CtnjEstadoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoServicio", CtnjEstadoRemoto.class.getName());
    }

    private CntjEstadoProcesoDocumentoRemoto getCntjEstadoProcesoDocumentoRemoto() throws Exception {
        return (CntjEstadoProcesoDocumentoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoProcesoDocumentoServicio", CntjEstadoProcesoDocumentoRemoto.class.getName());
    }

    private CntjCampoRemoto getCntjCampoRemoto() throws Exception {
        return (CntjCampoRemoto) RemotoEJB.getEJBRemoto("CntjCampoServicio", CntjCampoRemoto.class.getName());
    }

    private CtnjContratoRemoto getCtnjContratoRemoto() throws Exception {
        return (CtnjContratoRemoto) RemotoEJB.getEJBRemoto("CntjContratoServicio", CtnjContratoRemoto.class.getName());
    }

    private CntjEstadoEjecucionRemoto getCntjEstadoEjecucionRemoto() throws Exception {
        return (CntjEstadoEjecucionRemoto) RemotoEJB.getEJBRemoto("CntjEstadoEjecucionServicio", CntjEstadoEjecucionRemoto.class.getName());
    }

    private CntjPlantillaRemoto getCntjPlantillaRemoto() throws Exception {
        return (CntjPlantillaRemoto) RemotoEJB.getEJBRemoto("CntjPlantillaServicio", CntjPlantillaRemoto.class.getName());
    }

    private CntjDocumentoRemoto getCntjDocumentoRemoto() throws Exception {
        return (CntjDocumentoRemoto) RemotoEJB.getEJBRemoto("CntjDocumentoServicio", CntjDocumentoRemoto.class.getName());
    }

    private GnAlertaRemoto getGnAlertaRemoto() throws Exception {
        return (GnAlertaRemoto) RemotoEJB.getEJBRemoto("GnAlertaServicio", GnAlertaRemoto.class.getName());
    }

    private CntjContratoSupervisorRemoto getCntjContratoSupervisorRemoto() throws Exception {
        return (CntjContratoSupervisorRemoto) RemotoEJB.getEJBRemoto("CntjContratoSupervisorServicio", CntjContratoSupervisorRemoto.class.getName());
    }

    private CntjContratoGarantiaRemoto getCntjContratoGarantiaRemoto() throws Exception {
        return (CntjContratoGarantiaRemoto) RemotoEJB.getEJBRemoto("CntjContratoGarantiaServicio", CntjContratoGarantiaRemoto.class.getName());
    }

    private CntjContratoIndicadorRemoto getCntjContratoIndicadorRemoto() throws Exception {
        return (CntjContratoIndicadorRemoto) RemotoEJB.getEJBRemoto("CntjContratoIndicadorServicio", CntjContratoIndicadorRemoto.class.getName());
    }

    private CntjContratoObligacionRemoto getCntjContratoObligacionRemoto() throws Exception {
        return (CntjContratoObligacionRemoto) RemotoEJB.getEJBRemoto("CntjContratoObligacionServicio", CntjContratoObligacionRemoto.class.getName());
    }

    private CtnjProcesoRemoto getCtnjProcesoRemoto() throws Exception {
        return (CtnjProcesoRemoto) RemotoEJB.getEJBRemoto("CntjProcesoServicio", CtnjProcesoRemoto.class.getName());
    }

    private CntjContratoSeguimientoRemoto getCntjContratoSeguimientoRemoto() throws Exception {
        return (CntjContratoSeguimientoRemoto) RemotoEJB.getEJBRemoto("CntjContratoSeguimientoServicio", CntjContratoSeguimientoRemoto.class.getName());
    }

    private CtnjTerceroRemoto getCtnjTerceroRemoto() throws Exception {
        return (CtnjTerceroRemoto) RemotoEJB.getEJBRemoto("CntjTerceroServicio", CtnjTerceroRemoto.class.getName());
    }

    private CtnjOtrosiRemoto getCtnjOtrosiRemoto() throws Exception {
        return (CtnjOtrosiRemoto) RemotoEJB.getEJBRemoto("CntjOtrosiServicio", CtnjOtrosiRemoto.class.getName());
    }

    private CtnjComiteRemoto getCtnjComiteRemoto() throws Exception {
        return (CtnjComiteRemoto) RemotoEJB.getEJBRemoto("CntjComiteServicio", CtnjComiteRemoto.class.getName());
    }

    private CtnjLineaRemoto getCtnjLineaRemoto() throws Exception {
        return (CtnjLineaRemoto) RemotoEJB.getEJBRemoto("CntjLineaServicio", CtnjLineaRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    @Override
    public void Accion(TareaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    verDocumentos(bean);
                    break;
                case Url.ACCION_CREAR:
                    crearGestion(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardarGestion(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearCampo(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            agregarCampo(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarCampo(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarCampo(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            listarMaestro(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            listarTerceros(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            listarUsuarios(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            actualizarCamposEstado(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearAdjunto(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    consultarDocumento(bean);
                    break;
                case Url.ACCION_ADICIONAL_4:
                    guardarBorrador(bean);
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarDocumentosPrevisualizar(bean);
                            break;
                        case Url.ACCION_VER:
                            verDocumentosPrevisualizar(bean);
                            break;
                    }
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(TareaBean bean) {
        try {
            bean.setHashlistaModalidad(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.CNT_MODALIDAD));
            bean.setHashlistaClaseContrato(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.CNTJ_CONTRATOS_CLASES));
            bean.setHashlistaRegimen(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setHashlistaEstadoContrato(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.CNTJ_CONTRATO_ESTADO));
            bean.setHashlistaGarantiaContrato(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.CNTJ_CONTRATOS_GARANTIAS));
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al cargas información inicial. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al cargas información inicial.");
        }
    }

    private void listar(TareaBean bean) {
        try {
            bean.getParamConsulta().setParametroConsulta1(bean.getConexion().getUsuario().getId());
            bean.getParamConsulta().setCantidadRegistros(getCntjExpedienteRemoto().consultarCantidadTareas(bean.getParamConsulta()));
            bean.setRegistros(getCntjExpedienteRemoto().consultarListaTareas(bean.getParamConsulta()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }

    private void crearGestion(TareaBean bean) {
        try {
            //inicializamos objetos y listas a utilizar
            bean.setListaAdjuntos(new ArrayList<>());
            bean.setObjetoCampo(new CntjCampo());
            bean.setObjeToEjecucion(new CntjEstadoEjecucion());
            bean.setListaCamposTransicion(new ArrayList<>());
            bean.setObjeto(getCntjExpedienteRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeToEjecucion().setCntjExpedienteId(bean.getObjeto());
            bean.getObjeToEjecucion().setFechaEjecucion(new Date());
            bean.setListaEstadoSiguiente(getCtnjEstadoRemoto().consultarEstadoSiguientes(bean.getObjeto().getId(), bean.getConexion().getUsuario().getId()));
            bean.setHashListaEstadoSiguiente(CntjConstantes.convertEstadosToHash(bean.getListaEstadoSiguiente()));
            if (!bean.getListaEstadoSiguiente().isEmpty()) {
                bean.getObjeToEjecucion().setCntjEstadoId(bean.getListaEstadoSiguiente().get(0));
            }

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar acción. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar acción." + e.getMessage());
        }
    }

    private void guardarGestion(TareaBean bean) {
        try {
            List<CntjCampo> camposGestionados = bean.getListaCamposGestionados();
            CntjEstado estadoSiguiente = getCtnjEstadoRemoto().consultar(bean.getObjeToEjecucion().getCntjEstadoId().getId());
            if (bean.getObjeToEjecucion().getCntjEstadoId().getId() == null) {
                bean.addError("Debe seleccionar el Paso Permitido.");
            } else {
                List<CntjEstadoProcesoDocumento> docEstado = new ArrayList<>();
                if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_FIRMA_MANUAL)) {
                    docEstado = getCntjEstadoProcesoDocumentoRemoto().listaDocumentoEstadoAdjuntos(bean.getObjeToEjecucion().getCntjEstadoId().getId());
                } else {
                    docEstado = getCntjEstadoProcesoDocumentoRemoto().listaDocumentoEstadoGenerados(bean.getObjeToEjecucion().getCntjEstadoId().getId());
                }    
                
                for (CntjEstadoProcesoDocumento item : docEstado) {
                    boolean existe = bean.getListaAdjuntos().stream()
                            .anyMatch(elemento -> elemento.getEstadoDocumento().getId().equals(item.getId()));
                    if (!existe) {
                        bean.addError("No se han adjuntado todos los documentos requeridos.");
                        break;
                    }
                }

                /*List<CntjCampo> camposRequeridos = getCntjCampoRemoto().listaCamposDocumentoEstadoGenerados(bean.getObjeToEjecucion().getCntjEstadoId().getId());
                List<CntjCampo> camposExistentes = CntjUtilidades.getArrayCampos(bean.getObjeto().getJsonData());
                //Se filtran solo los campos que no esten ya agregados al expediente
                List<CntjCampo> camposValidar = camposRequeridos.stream()
                        .filter(item -> !camposExistentes.contains(item))
                        .collect(Collectors.toList());

                for (CntjCampo item : camposValidar) {
                    boolean existe = bean.getListaCamposTransicion().stream()
                            .anyMatch(elemento -> elemento.getEtiqueta().equals(item.getEtiqueta()));
                    if (!existe) {
                        bean.addError("No se han diligenciado todos los campos requeridos.");
                        break;
                    }
                }*/
                camposGestionados.addAll(bean.getListaCamposTransicion());

                if (!camposGestionados.isEmpty()) {
                    for (CntjCampo item : camposGestionados) {
                        if (item.getValor() == null || item.getValor().isEmpty()) {
                            bean.addError("Debe siligenciar el valor para el campo : " + item.getNombre());
                        }
                    }
                }

            }

            if (bean.getObjeToEjecucion().getFechaEjecucion() == null) {
                bean.addError("Debe elegir la Fecha de Ejecución");
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            //CntjEstado estadoSiguiente = getCtnjEstadoRemoto().consultar(bean.getObjeToEjecucion().getCntjEstadoId().getId());

            //Se arma el jsondata para almacenar
            JSONObject objeto = new JSONObject(bean.getObjeto().getJsonData() != null ? bean.getObjeto().getJsonData() : "{}");
            JSONArray arrayObjeto = new JSONArray();
            if (objeto.has(CntjConstantes.CAMPOS)) {
                arrayObjeto = objeto.getJSONArray(CntjConstantes.CAMPOS);
            }
            for (CntjCampo campo : camposGestionados) {
                JSONObject objCampo = new JSONObject();
                objCampo.put(CntjConstantes.ETIQUETA, campo.getEtiqueta());
                objCampo.put(CntjConstantes.NOMBRE, campo.getNombre());
                objCampo.put(CntjConstantes.VALOR, campo.getValor());
                objCampo.put(CntjConstantes.VALOR_STR, campo.getValorStr());
                objCampo.put(CntjConstantes.TABLA, campo.getTabla());
                objCampo.put(CntjConstantes.CAMPO, campo.getCampo());
                objCampo.put(CntjConstantes.TIPO, campo.getTipoDato());
                objCampo.put(CntjConstantes.IDCAMPO, campo.getId());
                objCampo.put(CntjConstantes.BORRADOR, false);
                Integer existeCampo = CntjUtilidades.getIndiceCampoExistente(objeto.toString(), campo.getId());
                if (existeCampo != null) {
                    arrayObjeto.put(existeCampo, objCampo);
                } else {
                    arrayObjeto.put(objCampo);
                }
                if (campo.getTipoDato().equals(CntjConstantes.TIPO_DATO_MONEDA)) {
                    objCampo.put(CntjConstantes.VALOR, CntjUtilidades.getValorMoneda(campo.getValor()));
                }
                if (campo.getTipoDato().equals(CntjConstantes.TIPO_DATO_TEXTO_LARGO) && campo.getValor() != null) {
                    objCampo.put(CntjConstantes.VALOR_STR, campo.getValor());
                }
                if (campo.getValorStr() == null) {
                    objCampo.put(CntjConstantes.VALOR_STR, objCampo.getString(CntjConstantes.VALOR));
                }
            }
            objeto.put(CntjConstantes.CAMPOS, arrayObjeto);
            bean.getObjeto().setJsonData(objeto.toString());

            //se valida si se debe crear un contrato
            if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_CONTRATO)) {
                CntjContrato contrato = CntjUtilidades.parsearJsonContrato(objeto.toString());
                try {
                    validarDatosContratos(bean, contrato);
                } catch (Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al validar información para creación de contrato. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente al validar información para creación de contrato.");
                }
            }
            //Se valida si se debe crear otrosi
            CntjOtrosi otrosi = null;
            if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_OTROSI)) {
                try {
                    otrosi = CntjUtilidades.parsearJsonOtrosi(objeto.toString());
                    otrosi.setEstado(CntjConstantes.OTROSI_VIGENTE);
                    CntjContrato contrato = getCtnjContratoRemoto().contratoPorNumero(bean.getObjeto().getContrato());
                    if (contrato != null) {
                        otrosi.setContratoId(contrato);
                    }
                    CntjOtrosi maxNumero = getCtnjOtrosiRemoto().ultimoNumeroOtroSi(bean.getObjeto().getId());
                    if (maxNumero != null) {
                        otrosi.setNumero(maxNumero.getNumero() + 1);
                    } else {
                        otrosi.setNumero(1);
                    }
                    validarDatoOtroSi(bean, otrosi);
                } catch (Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al validar información para creación de otrosi. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente al validar información para creación de otrosi.");
                }
            }
            //se valida si se debe crear linea
            CntjLinea objLinea = null;
            if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_LINEA)) {
                try {
                    //consultamos si existe un comite abierto.
                    CntjComite comite = getCtnjComiteRemoto().comiteAbierto();
                    if (comite == null) {
                        bean.addError("No se encontro comite en estado abierto.");
                    } else {
                        objLinea = CntjUtilidades.parsearJsonLinea(objeto.toString());
                        objLinea.setEstado(CntjConstantes.ESTADO_LINEA_REGISTRADO);
                        objLinea.setCntjComiteId(comite);
                        objLinea.setExpedienteId(bean.getObjeto());
                        validarDatosLinea(bean, objLinea);
                    }
                } catch (Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al validar información para creación de lineas. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente al validar información para creación de lineas.");
                }
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            //switch en caso de excepciones en el proceso normal
            boolean continuar = true;

            CntjExpediente expActual = getCntjExpedienteRemoto().consultar(bean.getObjeto().getId());

            bean.getObjeToEjecucion().setCntjExpedienteId(bean.getObjeto());
            bean.getObjeToEjecucion().setGnUsuariosId(bean.getConexion().getUsuario());
            bean.auditoriaGuardar(bean.getObjeToEjecucion());
            try {
                //Se guarda la ejecucion 
                bean.getObjeToEjecucion().setId(getCntjEstadoEjecucionRemoto().insertar(bean.getObjeToEjecucion()));
            } catch (Exception e) {
                continuar = false;
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar el nuevo estado del expediente. %s", e.getMessage()), e);
                bean.addError("Se presento inconveniente al guardar el nuevo estado del expediente." + e.getMessage());
            }

            if (continuar) {
                try {
                    bean.getObjeto().setEstado(estadoSiguiente.getNombre());
                    bean.getObjeto().setEstadoActual(estadoSiguiente);
                    bean.getObjeto().setFechaEjecucionEstado(bean.getObjeToEjecucion().getFechaEjecucion());
                    bean.auditoriaModificar(bean.getObjeto());
                    //Se actualiza el campo jsonData del expediente
                    getCntjExpedienteRemoto().actualizar(bean.getObjeto());
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al actualizar información del expediente. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente al actualizar información del expediente." + e.getMessage());
                    getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                }
            }

            if (continuar) {
                List<CntjDocumento> docCreado = new ArrayList<>();
                List<CntjDocumento> docActualizado = new ArrayList<>();
                try {
                    //se consultan las plantillas´para generar documentos
                    List<CntjPlantilla> plantillas = new ArrayList<>();
                    if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_FIRMA_MANUAL)) {
                        plantillas = getCntjPlantillaRemoto().listaDocumentoEstadoGenerados(estadoSiguiente.getId());
                    }else{
                        plantillas = getCntjPlantillaRemoto().listaDocumentoEstadoGeneradosMixtos(estadoSiguiente.getId());
                    }
                    
                    if (!plantillas.isEmpty()) {
                        for (CntjPlantilla item : plantillas) {
                            String plantilla = CntjConstantes.ESTILO_DOCUMENTO + item.getEstructura();
                            plantilla = plantilla.replace("<!-- -->", "");
                            JSONObject objetoData = new JSONObject(bean.getObjeto().getJsonData() != null ? bean.getObjeto().getJsonData() : "{}");
                            JSONArray arrayObj = new JSONArray();
                            if (objetoData.has(CntjConstantes.CAMPOS)) {
                                arrayObj = objetoData.getJSONArray(CntjConstantes.CAMPOS);
                            }
                            for (int index = 0; index < arrayObj.length(); index++) {
                                JSONObject objItem = arrayObj.getJSONObject(index);
                                if (objItem.has(CntjConstantes.BORRADOR) && objItem.getBoolean(CntjConstantes.BORRADOR) == true) {
                                    continue;
                                }
                                if (objItem.has(CntjConstantes.VALOR_STR)) {
                                    String valorstr = objItem.getString(CntjConstantes.VALOR_STR);
                                    if (valorstr.trim().isEmpty()) {
                                        plantilla = plantilla.replace(objItem.getString(CntjConstantes.ETIQUETA), objItem.getString(CntjConstantes.VALOR));
                                    } else {
                                        plantilla = plantilla.replace(objItem.getString(CntjConstantes.ETIQUETA), valorstr);
                                    }
                                } else if (objItem.has(CntjConstantes.VALOR)) {
                                    plantilla = plantilla.replace(objItem.getString(CntjConstantes.ETIQUETA), objItem.getString(CntjConstantes.VALOR));
                                }
                            }

                            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_EXPEDIENTES_ADJUNTOS);
                            Files.createDirectories(Paths.get(ruta));
                            File archivo = new File(ruta, CntjUtilidades.getNombreDocumento(bean.getObjeto().getNumeroExpediente(), item.getProcesodocumentoId().getNombre()));
                            ConverterProperties properties = new ConverterProperties();
                            HtmlConverter.convertToPdf(plantilla, new FileOutputStream(archivo), properties);

                            CntjDocumento documento = new CntjDocumento();
                            documento.setCntjExpedienteId(bean.getObjeto());
                            documento.setCntjPlantillaId(item);
                            documento.setDocumentoNombre(item.getProcesodocumentoId().getNombre());
                            documento.setDocumentoRuta(ruta);
                            documento.setDocumentoArchivo(archivo.getName());
                            documento.setDocumentoExiste(true);
                            documento.setNombre(item.getProcesodocumentoId().getNombre());
                            documento.setTipo(item.getProcesodocumentoId().getTipoDocumento().shortValue());
                            documento.setEtapaContratacion(item.getProcesodocumentoId().getEtapaContratacion());
                            Integer docExistente = getCntjDocumentoRemoto().existeDocumentoExpediente(bean.getObjeto().getId(), item.getProcesodocumentoId().getNombre());
                            if (docExistente == null) {
                                bean.auditoriaGuardar(documento);
                                documento.setId(getCntjDocumentoRemoto().insertar(documento));
                                docCreado.add(documento);
                            } else {
                                documento.setId(docExistente);
                                docActualizado.add(getCntjDocumentoRemoto().consultar(documento.getId()));
                                bean.auditoriaModificar(documento);
                                getCntjDocumentoRemoto().actualizar(documento);
                            }

                        }
                    }
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente en la generacion de documentos del estado. %s", e.getMessage()), e);
                    bean.addError("Inconveninete en la generacion de documentos del estado.");
                    if(!docCreado.isEmpty()){
                        for (CntjDocumento doc : docCreado) {
                            getCntjDocumentoRemoto().eliminar(doc.getId());
                        }
                    }
                    if(!docActualizado.isEmpty()){
                        for (CntjDocumento doc : docActualizado) {
                            getCntjDocumentoRemoto().restablecerActualizacion(doc);
                        }
                    }    
                    getCntjExpedienteRemoto().actualizar(expActual);
                    getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                }
            }

            if (continuar) {
                //guardar adjuntos
                List<CntjDocumento> docCreado = new ArrayList<>();
                List<CntjDocumento> docActualizado = new ArrayList<>();
                if (!bean.getListaAdjuntos().isEmpty()) {
                    try {
                        String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_EXPEDIENTES_ADJUNTOS);
                        Files.createDirectories(Paths.get(ruta));
                        for (CntjDocumento doc : bean.getListaAdjuntos()) {    
                            if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_FIRMA_MANUAL)) {
                                CntjDocumento documento = getCntjDocumentoRemoto().getDocumentoExpedienteNombre(bean.getObjeto().getId(), doc.getNombre());
                                if(documento == null){  
                                    throw new Exception("No se encontro registro existente de este documento, por lo cual no se puede procesar para sustituirlo.");
                                }else{
                                    docActualizado.add(documento);
                                    CntjDocumento actualizado = documento;
                                    actualizado.setAdjuntoStream(doc.getAdjuntoStream());                                    
                                    bean.auditoriaModificar(actualizado);
                                    getCntjDocumentoRemoto().actualizar(actualizado);
                                    boolean generado = generarArchivo(actualizado);
                                    if (!generado) {
                                        throw new Exception("No se pudo almacenar el archivo del adjunto.");
                                    } 
                                }
                            }else {
                                int indiceExtension = doc.getDocumentoNombre().lastIndexOf(".");
                                String extension = doc.getDocumentoNombre().substring(indiceExtension, doc.getDocumentoNombre().length());
                                doc.setDocumentoArchivo(String.format("%s%s%s", CntjConstantes.NOMBRE_ADJUNTO_EXPEDIENTE, CntjConstantes.formato6.format(new Date()), extension));
                                doc.setCntjExpedienteId(bean.getObjeto());
                                doc.setDocumentoRuta(ruta);
                                doc.setDocumentoExiste(true);
                                bean.auditoriaGuardar(doc);
                                doc.setId(getCntjDocumentoRemoto().insertar(doc));
                                docCreado.add(doc);
                                boolean generado = generarArchivo(doc);
                                if (!generado) {
                                    throw new Exception("No se pudo almacenar el archivo del adjunto.");
                                }
                            }                                                       
                        }
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente guardar adjuntos. %s", e.getMessage()), e);
                        bean.addError("Inconveniente al guardar adjuntos. " + e.getMessage());
                        if (!docCreado.isEmpty()) {
                            for (CntjDocumento doc : docCreado) {
                                getCntjDocumentoRemoto().eliminar(doc.getId());
                            }
                        }
                        if(!docActualizado.isEmpty()){
                            for (CntjDocumento doc : docActualizado) {
                                getCntjDocumentoRemoto().restablecerActualizacion(doc);
                            }
                        }
                        getCntjExpedienteRemoto().actualizar(expActual);
                        getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                    }
                }
            }

            if (continuar) {
                //se valida si se debe crear un contrato
                if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_CONTRATO)) {
                    try {
                        JSONObject json = new JSONObject(bean.getObjeto().getJsonData() != null ? bean.getObjeto().getJsonData() : "{}");
                        CntjContrato contrato = CntjUtilidades.parsearJsonContrato(json.toString());
                        contrato.setCntjExpedienteId(bean.getObjeto());
                        contrato.setProceso(bean.getObjeto().getProcesoId().getId().toString());
                        CntjProceso proceso = getCtnjProcesoRemoto().consultar(bean.getObjeto().getProcesoId().getId());
                        String ultimoNumcontrato = getCtnjContratoRemoto().ultimoNumeroContrato(String.valueOf(LocalDate.now().getYear()));
                        if (ultimoNumcontrato != null && !ultimoNumcontrato.isEmpty()) {
                            int ultimoValor = Integer.parseInt(ultimoNumcontrato.split(CntjConstantes.SEPARADOR)[0]);
                            contrato.setContrato(String.format("%s-%s", String.format("%04d", (ultimoValor + 1)), LocalDate.now().getYear()));
                        } else {
                            contrato.setContrato(String.format("%s-%s", String.format("%04d", 1), LocalDate.now().getYear()));
                        }

                        if (proceso != null && contrato.getFormaPago() != null) {
                            if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_ADMINISTRATIVO) && contrato.getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_VARIABLE)) {
                                contrato.setPeriodicidadSeguimiento(CntjConstantes.PERIODO_MENSUAL);
                            } else if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_ADMINISTRATIVO) && contrato.getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_FIJO)) {
                                contrato.setPeriodicidadSeguimiento(CntjConstantes.PERIODO_TRIMESTRAL);
                            } else {
                                contrato.setPeriodicidadSeguimiento(CntjConstantes.PERIODO_MENSUAL);
                            }
                        }
                        //contrato.setValorPagadoTotal(contrato.getValorTotal());
                        guardarContrato(bean, contrato);
                        bean.getObjeto().setContrato(contrato.getContrato());
                        getCntjExpedienteRemoto().actualizar(bean.getObjeto());
                        if (bean.isError()) {
                            throw new Exception("No fue posible guardar el contrato.");
                        }

                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente en la creación del contarto. %s", e.getMessage()), e);
                        bean.addError("Inconveniente en la creación del contarto.");
                        getCntjDocumentoRemoto().eliminarPorExpediente(bean.getObjeto().getId());
                        getCntjExpedienteRemoto().actualizar(expActual);
                        getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                    }
                }

                //se valida si se debe crear un otrosi
                if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_OTROSI)) {
                    try {
                        guardarOtrosi(bean, otrosi);
                        if (bean.isError()) {
                            throw new Exception("No fue posible guardar el otrosi.");
                        }
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente en la creación del otrosi. %s", e.getMessage()), e);
                        bean.addError("Inconveniente en la creación del otrosi.");
                        getCntjDocumentoRemoto().eliminarPorExpediente(bean.getObjeto().getId());
                        getCntjExpedienteRemoto().actualizar(expActual);
                        getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                    }
                }

                //se valida si se debe crear una linea
                if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_LINEA)) {
                    try {
                        guardarLinea(bean, objLinea);
                        if (bean.isError()) {
                            throw new Exception("No fue posible guardar la linea.");
                        }
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente en la creación del otrosi. %s", e.getMessage()), e);
                        if (!bean.isError()) {
                            bean.addError("Inconveniente en la creación de lineas.");
                        }
                        getCntjDocumentoRemoto().eliminarPorExpediente(bean.getObjeto().getId());
                        getCntjExpedienteRemoto().actualizar(expActual);
                        getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                    }
                }
            }

            if (continuar) {
                //Se establece notificacion al grupo del estado.
                List<CntjUsuarioGrupo> listaUsuario = getCtnjUsuarioGrupoRemoto().listarUsuariosGrupoPermisos(bean.getObjeToEjecucion().getCntjEstadoId().getId());
                for (CntjUsuarioGrupo usuario : listaUsuario) {
                    try {
                        GnAlerta alerta = CntjConstantes.getAlertaGestionExpediente(usuario.getGnUsuarioId(), bean.getObjeToEjecucion().getFechaEjecucion(), bean.getObjeto().getNumeroExpediente(), estadoSiguiente.getNombre(), bean.getConexion().getUsuario().getNombre(), bean.getObjeToEjecucion().getObservacion());
                        getGnAlertaRemoto().insertar(alerta);
                    } catch (Exception e) {
                        bean.addError("No fue posible enviar notificaciones para algunos usuarios.");
                        continue;
                    }
                }
            }

            if (continuar) {
                bean.addMensaje("Información guardada correctamente.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar.");
        }
    }

    private void validarDatosContratos(TareaBean bean, CntjContrato contrato) throws Exception {
        if (contrato.getCntjTerceroId() == null) {
            bean.addError("Creación contrato: Debe indicar el supervisor.");
        }
        if (contrato.getMaeEstadoContratoId() == null || contrato.getMaeEstadoContratoId() == 0) {
            bean.addError("Creación contrato: Debe selecionar un valor para el campo Estado de Contrato.");
        }
        if (contrato.getFechaInicio() == null) {
            bean.addError("Creación contrato: Debe indicar la fecha inicio del contrato");
        }
        if (contrato.getFechaFin() == null) {
            bean.addError("Creación contrato: Debe indicar la fecha fin del contrato");
        }
        if (contrato.getValorInicial() == null) {
            bean.addError("Creación contrato: Debe indicar el valor del contrato.");
        }
        if (contrato.getMaeClaseContratoId() == null) {
            bean.addError("Creación contrato: Debe indicar la clase de contrato.");
        }
        if (contrato.getPlazoInicialMeses() == null) {
            bean.addError("Creación contrato: Debe indicar el plazo inicial en meses.");
        }
        if (contrato.getPlazoInicialDias() == null) {
            bean.addError("Creación contrato: Debe indicar el plazo inicial en días.");
        }
        if (contrato.getPlazoTotalDias() == null) {
            bean.addError("Creación contrato: Debe indicar el plazo total en días.");
        }
        if (contrato.getValorTotal() == null) {
            bean.addError("Creación contrato: Debe indicar el valor total.");
        }
        if (contrato.getObjeto() == null) {
            bean.addError("Creación contrato: Debe indicar el objeto del contrato.");
        }

        boolean existe = getCtnjContratoRemoto().existeContratoCodigo(contrato.getContrato());

        if (existe) {
            bean.addError("Creación contrato: Ya existe un registro con el mismo contrato, el valor del campo contrato es único");
        }

        if (contrato.getTipoAnticipo() != null && contrato.getTipoAnticipo() == CntjConstantes.TIPO_ANTICIPO_ANTICIPADO) {
            if (contrato.getValorAnticipo() == null) {
                bean.addError("Creación contrato: Debe ingresar el valor anticipo.");
            }
        }

        if (contrato.getFechaSuspension() != null) {
            if (contrato.getMotivoSuspension() == null) {
                bean.addError("Creación contrato: Debe ingresar un motivo de suspensión porque selecciono una fecha de suspensión.");
            }
        }

    }

    private boolean generarArchivo(CntjDocumento objeto) throws Exception {
        boolean esArchivoGuardado = false;
        File archivo = new File(objeto.getDocumentoRuta(), objeto.getDocumentoArchivo());
        try (OutputStream destino = new FileOutputStream(archivo)) {
            IOUtils.copy(objeto.getAdjuntoStream(), destino);
            Fichero.permisos(archivo.toPath());
            esArchivoGuardado = true;
        } catch (IOException ex) {
            esArchivoGuardado = false;
            throw new Exception("Error al subir un adjunto: " + ex.getMessage(), ex);
        }

        return esArchivoGuardado;
    }

    private void guardarContrato(TareaBean bean, CntjContrato contrato) {
        try {
            boolean continuar = true;
            Maestro maemodalidad = bean.getHashlistaModalidad().get(contrato.getMaeModalidadContratoId());
            if (maemodalidad != null) {
                contrato.setMaeModalidadContratoCodigo(maemodalidad.getValor());
                contrato.setMaeModalidadContratoValor(maemodalidad.getNombre());
            }
            Maestro maeclasecontrato = bean.getHashlistaClaseContrato().get(contrato.getMaeClaseContratoId());
            if (maeclasecontrato != null) {
                contrato.setMaeClaseContratoCodigo(maeclasecontrato.getValor());
                contrato.setMaeClaseContratoValor(maeclasecontrato.getNombre());
            }
            Maestro maeregimen = bean.getHashlistaRegimen().get(contrato.getMaeRegimenId());
            if (maeregimen != null) {
                contrato.setMaeRegimenCodigo(maeregimen.getValor());
                contrato.setMaeRegimenValor(maeregimen.getNombre());
            }

            Maestro estadocontrato = bean.getHashlistaEstadoContrato().get(contrato.getMaeEstadoContratoId());
            contrato.setMaeEstadoContratoCodigo(estadocontrato.getValor());
            contrato.setMaeEstadoContratoValor(estadocontrato.getDescripcion());

            Integer idcontrato = null;
            //se consulta si ya existe un contrato asociado al expediente
            CntjContrato contratoExiste = getCtnjContratoRemoto().contratoPorNumero(bean.getObjeto().getContrato());
            if (contratoExiste == null) {
                bean.auditoriaGuardar(contrato);
                idcontrato = getCtnjContratoRemoto().insertar(contrato);
            } else {
                contrato.setId(contratoExiste.getId());
                contrato.setContrato(contratoExiste.getContrato());
                bean.auditoriaModificar(contrato);                
                getCtnjContratoRemoto().actualizar(contrato);
                bean.addMensaje("Ya existe un contrato asociado al expediente, el contrato fue modificado correctamente.");
                continuar = false;
            }
     
            //guardar supervisores
            if (!contrato.getCntjContratoSupervisorList().isEmpty() && continuar) {
                for (CntjContratoSupervisor supervisor : contrato.getCntjContratoSupervisorList()) {
                    supervisor.setCntjContratosId(new CntjContrato(idcontrato));
                    supervisor.setFechaInicio(contrato.getFechaInicio());
                    supervisor.setFechaFin(contrato.getFechaFin());
                    bean.auditoriaGuardar(supervisor);
                    try {
                        supervisor.setId(getCntjContratoSupervisorRemoto().insertar(supervisor));
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar supervisores del contrato. %s", e.getMessage()), e);
                        bean.addError("Se presento inconveniente al guardar supervisores del contrato." + e.getMessage());
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        continue;
                    }
                }
            }

            //guardar garantia
            if (!contrato.getCntjContratoGarantiaList().isEmpty() && continuar) {
                for (CntjContratoGarantia garantia : contrato.getCntjContratoGarantiaList()) {
                    try {
                        garantia.setCntjContratoId(new CntjContrato(idcontrato));

                        if (garantia.getMaeGarantiaContratoId() == null) {
                            bean.addError("Garantia del contrato: Falta definir la garantia.");
                        }
                        if (garantia.getEstado() == null) {
                            bean.addError("Garantia del contrato: Falta definir el estado de la garantia.");
                        }
                        if (bean.isError()) {
                            throw new Exception();
                        }

                        Maestro maegarantia = bean.getHashlistaGarantiaContrato().get(garantia.getMaeGarantiaContratoId());
                        garantia.setMaeGarantiaContratoCodigo(maegarantia.getValor());
                        garantia.setMaeGarantiaContratoValor(maegarantia.getNombre());
                        bean.auditoriaGuardar(garantia);

                        getCntjContratoGarantiaRemoto().insertar(garantia);
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar garantias del contrato. %s", e.getMessage()), e);
                        if (!bean.isError()) {
                            bean.addError("Se presento inconveniente al guardar garantias del contrato.");
                        }
                        getCntjContratoGarantiaRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        break;
                    }
                }
            }

            //guardar indicadores
            if (!contrato.getCntjContratoIndicadorList().isEmpty() && continuar) {
                for (CntjContratoIndicador indicador : contrato.getCntjContratoIndicadorList()) {
                    try {
                        if (indicador.getTipoIndicador() == null) {
                            bean.addError("Indicador del contrato: Falta definir el tipo de indicador.");
                        }
                        if (indicador.getDescripcion() == null) {
                            bean.addError("Indicador del contrato: Falta definir la descripción.");
                        }
                        if (indicador.getMeta() == null) {
                            bean.addError("Indicador del contrato: Falta definir la meta.");
                        }
                        if (indicador.getAplicaDescuento() == null) {
                            bean.addError("Indicador del contrato: Falta definir si aplica descuento.");
                        }

                        if (bean.isError()) {
                            throw new Exception();
                        }

                        indicador.setCntjContratosId(new CntjContrato(idcontrato));
                        bean.auditoriaGuardar(indicador);
                        getCntjContratoIndicadorRemoto().insertar(indicador);
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar indicadores del contrato. %s", e.getMessage()), e);
                        if (!bean.isError()) {
                            bean.addError("Se presento inconveniente al guardar indicadores del contrato." + e.getMessage());
                        }
                        getCntjContratoIndicadorRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoGarantiaRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        continue;
                    }
                }
            }

            //guardar obligaciones
            if (!contrato.getCntjContratoObligacionList().isEmpty() && continuar) {
                for (CntjContratoObligacion obligacion : contrato.getCntjContratoObligacionList()) {
                    try {
                        if (obligacion.getNumeroObligacion() == null) {
                            bean.addError("Obligación del contrato: Falta definir el numero de la obligacion.");
                        }
                        if (obligacion.getDescripcion() == null) {
                            bean.addError("Obligación del contrato: Falta definir el la descripción.");
                        }
                        if (bean.isError()) {
                            throw new Exception();
                        }

                        obligacion.setCntjContratosId(new CntjContrato(idcontrato));
                        bean.auditoriaGuardar(obligacion);
                        getCntjContratoObligacionRemoto().insertar(obligacion);
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar obligaciones del contrato. %s", e.getMessage()), e);
                        if (!bean.isError()) {
                            bean.addError("Se presento inconveniente al guardar obligaciones del contrato." + e.getMessage());
                        }
                        getCntjContratoObligacionRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoGarantiaRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoIndicadorRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        continue;
                    }
                }
            }

            //guardar seguimientos
            if (continuar) {
                if (contrato.getCntjContratoSeguimientoList().isEmpty()) {
                    CntjContratoSeguimiento seguimiento = new CntjContratoSeguimiento();
                    seguimiento.setCntjContratoId(new CntjContrato(idcontrato));
                    seguimiento.setCntjTerceroId(contrato.getCntjTerceroId());
                    CntjProceso proceso = getCtnjProcesoRemoto().consultar(Integer.parseInt(contrato.getProceso()));
                    if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_ADMINISTRATIVO)) {
                        seguimiento.setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_MENSUAL);
                    } else if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_SALUD)
                            && contrato.getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_VARIABLE)) {
                        seguimiento.setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_BIMESTRAL);
                    } else {
                        seguimiento.setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_SEMESTRAL);
                    }
                    seguimiento.setFechaInicioSeguimiento(contrato.getFechaInicio());
                    seguimiento.setFechaFinSeguimiento(contrato.getFechaFin());
                    seguimiento.setEstadoSeguimiento(CntjContratoSeguimiento.ESTADO_PENDIENTE);
                    seguimiento.setObservaciones("Inicial");
                    bean.auditoriaGuardar(seguimiento);
                    try {
                        getCntjContratoSeguimientoRemoto().insertar(seguimiento);
                    } catch (Exception e) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar seguimientos del contrato. %s", e.getMessage()), e);
                    }

                }
            }

            if (bean.getErrores().isEmpty()) {
                bean.addMensaje("Contrato guardado correctamente.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar contrato. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar contrato.");
        }
    }

    private void verDocumentos(TareaBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getCntjDocumentoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setListaDocumentos(getCntjDocumentoRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información.");
        }
    }

    private void crearCampo(TareaBean bean) {
        try {
            bean.setObjeto(getCntjExpedienteRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeToEjecucion().getCntjEstadoId() == null) {
                bean.addError("No se ha establecido un estado siguiente para cargar los campos.");
                return;
            }

            List<CntjCampo> listadoCampos = getCntjCampoRemoto().listaCamposDocumentoEstadoGenerados(bean.getObjeToEjecucion().getCntjEstadoId().getId());
            if (!listadoCampos.isEmpty()) {
                bean.setListaCampos(listadoCampos);
            } else {
                bean.setListaCampos(new ArrayList<>());
            }
            bean.setListaCamposTransicion(bean.getListaCampos());

            /*List<CntjCampo> listadoCampos = getCntjCampoRemoto().listaCamposDocumentoEstadoGenerados(bean.getObjeToEjecucion().getCntjEstadoId().getId());
            if (!listadoCampos.isEmpty()) {
                //se obtiene el json del expediente actual para verificar los campos existentes
                List<CntjCampo> camposExistentes = CntjUtilidades.getArrayCampos(bean.getObjeto().getJsonData());
                //Se filtran solo los campos que no esten ya agregados al expediente
                List<CntjCampo> campos = listadoCampos.stream()
                        .filter(item -> (!bean.getListaCamposTransicion().contains(item) && !camposExistentes.contains(item)))
                        .collect(Collectors.toList());
                bean.setListaCampos(campos);
            } else {
                bean.setListaCampos(new ArrayList<>());
            }*/
            bean.setHashListaCampos(CntjConstantes.obtenerHashCampos(bean.getListaCampos()));
            bean.setObjetoCampo(new CntjCampo());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información.");
        }
    }

    private void agregarCampo(TareaBean bean) {
        try {
            if (bean.getObjCampoAux().getTipoDato().equals(CntjConstantes.TIPO_DATO_TERCERO)) {
                if (bean.getObjCampoAux().getExistente()) {
                    if (!bean.getListaCamposGestionados().isEmpty()) {
                        bean.getListaCamposGestionados().stream()
                                .filter(obj -> obj.getId().equals(bean.getObjCampoAux().getId()))
                                .findFirst()
                                .ifPresent(obj -> {
                                    obj.setValor(bean.getTercero().getId().toString());
                                    obj.setValorStr(bean.getTercero().getRazonSocial());
                                });

                        List<CntjCampo> campoReferentes = getCntjCampoRemoto().camposPorReferencia(bean.getObjeToEjecucion().getCntjEstadoId().getId(), bean.getObjCampoAux().getNombre());
                        List<CntjCampo> camposActualizar = new ArrayList<>();
                        for (CntjCampo item : campoReferentes) {
                            String valorReferencia = CntjUtilidades.getValorTercero(bean.getTercero(), item.getValorReferencia());
                            bean.getListaCamposGestionados().stream()
                                    .filter(obj -> obj.getId().equals(item.getId()))
                                    .findFirst()
                                    .ifPresent(obj -> {
                                        obj.setValor(valorReferencia);
                                    });
                            CntjCampo auxiliar = new CntjCampo(item.getId());
                            auxiliar.setValor(valorReferencia);
                            camposActualizar.add(auxiliar);
                        }
                        bean.setTercero(new CntjTercero());
                        bean.actualizarComponentesFront(camposActualizar);
                    }
                } else {
                    if (!bean.getListaCamposTransicion().isEmpty()) {
                        bean.getListaCamposTransicion().stream()
                                .filter(obj -> obj.getId().equals(bean.getObjCampoAux().getId()))
                                .findFirst()
                                .ifPresent(obj -> {
                                    obj.setValor(bean.getTercero().getId().toString());
                                    obj.setValorStr(bean.getTercero().getRazonSocial());
                                });

                        List<CntjCampo> campoReferentes = getCntjCampoRemoto().camposPorReferencia(bean.getObjeToEjecucion().getCntjEstadoId().getId(), bean.getObjCampoAux().getNombre());
                        List<CntjCampo> camposActualizar = new ArrayList<>();
                        for (CntjCampo item : campoReferentes) {
                            String valorReferencia = CntjUtilidades.getValorTercero(bean.getTercero(), item.getValorReferencia());
                            bean.getListaCamposTransicion().stream()
                                    .filter(obj -> obj.getId().equals(item.getId()))
                                    .findFirst()
                                    .ifPresent(obj -> {
                                        obj.setValor(valorReferencia);
                                    });
                            CntjCampo auxiliar = new CntjCampo(item.getId());
                            auxiliar.setValor(valorReferencia);
                            camposActualizar.add(auxiliar);
                        }
                        bean.setTercero(new CntjTercero());
                        bean.actualizarComponentesFront(camposActualizar);
                    }
                }
            }

            if (bean.getObjCampoAux().getTipoDato().equals(CntjConstantes.TIPO_DATO_USUARIO)) {
                if (bean.getObjCampoAux().getExistente()) {
                    if (!bean.getListaCamposGestionados().isEmpty()) {
                        bean.getListaCamposGestionados().stream()
                                .filter(obj -> obj.getId().equals(bean.getObjCampoAux().getId()))
                                .findFirst()
                                .ifPresent(obj -> {
                                    obj.setValor(bean.getUsuario().getId().toString());
                                    obj.setValorStr(bean.getUsuario().getNombre());
                                });

                        List<CntjCampo> campoReferentes = getCntjCampoRemoto().camposPorReferencia(bean.getObjeToEjecucion().getCntjEstadoId().getId(), bean.getObjCampoAux().getNombre());
                        List<CntjCampo> camposActualizar = new ArrayList<>();
                        for (CntjCampo item : campoReferentes) {
                            String valorreferencia = CntjUtilidades.getValorUsuario(bean.getUsuario(), item.getValorReferencia());
                            bean.getListaCamposGestionados().stream()
                                    .filter(obj -> obj.getId().equals(item.getId()))
                                    .findFirst()
                                    .ifPresent(obj -> {
                                        obj.setValor(valorreferencia);
                                    });
                            CntjCampo auxiliar = new CntjCampo(item.getId());
                            auxiliar.setValor(valorreferencia);
                            camposActualizar.add(auxiliar);
                        }
                        bean.setUsuario(new Usuario());
                        bean.actualizarComponentesFront(camposActualizar);
                    }
                } else {
                    if (!bean.getListaCamposTransicion().isEmpty()) {
                        bean.getListaCamposTransicion().stream()
                                .filter(obj -> obj.getId().equals(bean.getObjCampoAux().getId()))
                                .findFirst()
                                .ifPresent(obj -> {
                                    obj.setValor(bean.getUsuario().getId().toString());
                                    obj.setValorStr(bean.getUsuario().getNombre());
                                });

                        List<CntjCampo> campoReferentes = getCntjCampoRemoto().camposPorReferencia(bean.getObjeToEjecucion().getCntjEstadoId().getId(), bean.getObjCampoAux().getNombre());
                        List<CntjCampo> camposActualizar = new ArrayList<>();
                        for (CntjCampo item : campoReferentes) {
                            String valorreferencia = CntjUtilidades.getValorUsuario(bean.getUsuario(), item.getValorReferencia());
                            bean.getListaCamposTransicion().stream()
                                    .filter(obj -> obj.getId().equals(item.getId()))
                                    .findFirst()
                                    .ifPresent(obj -> {
                                        obj.setValor(valorreferencia);
                                    });
                            CntjCampo auxiliar = new CntjCampo(item.getId());
                            auxiliar.setValor(valorreferencia);
                            camposActualizar.add(auxiliar);
                        }
                        bean.setUsuario(new Usuario());
                        bean.actualizarComponentesFront(camposActualizar);
                    }
                }
            }

            /*if (bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA)) {
                Maestro maestro = bean.getHashListaMaestroCampo().get(Integer.valueOf(bean.getObjetoCampo().getValor()));
                bean.getObjetoCampo().setValorStr(maestro.getNombre());
            }

            if (bean.getListaCamposTransicion().isEmpty()) {
                bean.getObjetoCampo().setId(-1);
            } else {
                Optional<CntjCampo> maxNumero = bean.getListaCamposTransicion().stream()
                        .filter(campo -> campo.getId() != null && campo.getId() < 0)
                        .max(Comparator.comparingInt(CntjCampo::getId));
                if (maxNumero.isPresent()) {
                    bean.getObjetoCampo().setId(maxNumero.get().getId() - 1);
                } else {
                    bean.getObjetoCampo().setId(-1);
                }
            }
            CntjCampo campoGuardar = bean.getHashListaCampos().get(bean.getObjetoCampo().getNombre());
            bean.getObjetoCampo().setId(campoGuardar.getId());
            bean.getObjetoCampo().setEtiqueta(campoGuardar.getEtiqueta());
            bean.getObjetoCampo().setTabla(campoGuardar.getTabla());
            bean.getObjetoCampo().setCampo(campoGuardar.getCampo());
            bean.getListaCamposTransicion().add(0, bean.getObjetoCampo());*/
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al agregar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al agregar información.");
        }
    }

    private void editarCampo(TareaBean bean) {
        try {
            CntjCampo campoEditar = getCntjCampoRemoto().consultar(bean.getObjetoCampo().getId());
            bean.setObjetoCampo(campoEditar);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información.");
        }
    }

    private void modificarCampo(TareaBean bean) {
        try {
            if (bean.getObjetoCampo().getId() == null) {
                bean.addError("Debe seleccionar el Campo que desea agregar.");
            }

            if (bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_FECHA)) {
                if (bean.getObjetoCampo().getValorDt() == null) {
                    bean.addError("Debe ingresar un valor para el campo seleccionado. ");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    bean.getObjetoCampo().setValor(sdf.format(bean.getObjetoCampo().getValorDt()));
                }
            }

            if (!bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_TERCERO) && bean.getObjetoCampo().getValor() == null) {
                bean.addError("Debe ingresar un valor para el campo seleccionado. ");
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            if (bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_TERCERO)) {
                bean.getObjetoCampo().setValor(bean.getTercero().getId().toString());
                List<CntjCampo> campoReferentes = getCntjCampoRemoto().camposPorReferenciaCampo(bean.getObjetoCampo().getNombre());
                for (CntjCampo item : campoReferentes) {
                    bean.getListaCamposTransicion().stream()
                            .filter(obj -> obj.getId().equals(item.getId()))
                            .findFirst()
                            .ifPresent(obj -> {
                                obj.setValor(CntjUtilidades.getValorTercero(bean.getTercero(), item.getValorReferencia()));
                            });
                }
                bean.setTercero(new CntjTercero());
            }

            if (bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA)) {
                Maestro maestro = bean.getHashListaMaestroCampo().get(Integer.valueOf(bean.getObjetoCampo().getValor()));
                bean.getObjetoCampo().setValorStr(maestro.getNombre());
            }

            bean.getListaCamposTransicion().stream()
                    .filter(obj -> obj.getId().equals(bean.getObjetoCampo().getId()))
                    .findFirst()
                    .ifPresent(obj -> {
                        obj.setValor(bean.getObjetoCampo().getValor());
                        obj.setValorStr(bean.getObjetoCampo().getValorStr());
                    });

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar campo. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar campo." + e.getMessage());
        }
    }

    private void listarMaestro(TareaBean bean) {
        try {
            bean.setListaMaestroCampo(MaestroSingle.getInstance().listarPorTipo(bean.getObjetoCampo().getMaestroTipo()));
            bean.setHashListaMaestroCampo(CntjConstantes.obtenerHashMaestro(bean.getListaMaestroCampo()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información.");
        }
    }

    private void crearAdjunto(TareaBean bean) {
        try {
            bean.setAdjunto(new CntjDocumento());
            bean.setObjeto(getCntjExpedienteRemoto().consultar(bean.getObjeto().getId()));
            CntjEstado estadoSiguiente = getCtnjEstadoRemoto().consultar(bean.getObjeToEjecucion().getCntjEstadoId().getId());
            if(estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_FIRMA_MANUAL)){
                bean.setListaEstadoDocumento(getCntjEstadoProcesoDocumentoRemoto().listaDocumentoEstadoAdjuntos(bean.getObjeToEjecucion().getCntjEstadoId().getId()));
            }else{                
                bean.setListaEstadoDocumento(getCntjEstadoProcesoDocumentoRemoto().listaDocumentoEstadoDigitalizado(bean.getObjeToEjecucion().getCntjEstadoId().getId()));
            }            
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar la acción %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar la acción.");
        }
    }

    private void listarTerceros(TareaBean bean) {
        try {
            bean.setListaTipoTercero(CntjConstantes.listaTipoTercero());
            bean.setListaTipoDocumentoTercero(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.getParamConsulta(1).setCantidadRegistros(getCtnjTerceroRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosTerceros(getCtnjTerceroRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }

    private void validarDatoOtroSi(TareaBean bean, CntjOtrosi objOtrosi) {
        try {
            if (objOtrosi.getContratoId() == null) {
                bean.addError("Creación Otrosí: Debe indicar el contrato.");
            }
            if (objOtrosi.getNumero() == null) {
                bean.addError("Creación Otrosí: Debe ingresar el número.");
            }
            if (objOtrosi.getTipo() == null) {
                bean.addError("Creación Otrosí: Debe selecionar un valor para el campo Tipo.");
            }
            if (objOtrosi.getFechasuscripcion() == null) {
                bean.addError("Creación Otrosí: Debe selecionar un valor para la fecha suscripción.");
            }
            if (objOtrosi.getJustificacion() == null) {
                bean.addError("Creación Otrosí: Debe ingresar la justificación.");
            }
            if (objOtrosi.getElementoAdicional() == null) {
                bean.addError("Creación Otrosí: Debe ingresar información de elementos adicionales.");
            }
            if (objOtrosi.getEstado() == null) {
                bean.addError("Creación Otrosí: Debe ingresar el estado.");
            }

            if (objOtrosi.getFechasuscripcion() != null && objOtrosi.getFechaInicio() != null) {
                if (objOtrosi.getFechasuscripcion().after(objOtrosi.getFechaInicio())) {
                    bean.addError("Creación Otrosí: La fecha de suscripción no puede ser mayor a la fecha de inicio.");
                }
            }

            if (objOtrosi.getTipo() != null) {
                switch (objOtrosi.getTipo()) {
                    case CntjConstantes.OTROSI_PRORROGA:
                    case CntjConstantes.OTROSI_PRORROGA_MODIFICACION:
                    case CntjConstantes.OTROSI_ACTA_INICIO:
                        if (objOtrosi.getPlazoMeses() == null) {
                            bean.addError("Creación Otrosí: Debe ingresar un valor para el campo Plazo Prorroga (Meses).");
                        }
                        if (objOtrosi.getPlazoDias() == null) {
                            bean.addError("Creación Otrosí: Debe ingresar un valor para el campo Plazo Prorroga (Días).");
                        }
                        break;
                    case CntjConstantes.OTROSI_ADICION:
                    case CntjConstantes.OTROSI_ADICION_MODIFICACION:
                        if (objOtrosi.getValor() == null) {
                            bean.addError("Creación Otrosí: Debe ingresar el Valor Otrosí");
                        }
                        break;
                    case CntjConstantes.OTROSI_PRORROGA_ADICION:
                    case CntjConstantes.OTROSI_PRORROGA_ADICION_MODIFICACION:
                        if (objOtrosi.getPlazoMeses() == null) {
                            bean.addError("Creación Otrosí: Debe ingresar un valor para el campo Plazo Prorroga (Meses).");
                        }
                        if (objOtrosi.getPlazoDias() == null) {
                            bean.addError("Creación Otrosí: Debe ingresar un valor para el campo Plazo Prorroga (Días).");
                        }
                        if (objOtrosi.getValor() == null) {
                            bean.addError("Creación Otrosí: Debe ingresar el Valor Otrosí");
                        }
                        break;
                    default:
                        break;
                }
            }

            CntjOtrosi actaInicio = getCtnjOtrosiRemoto().otrosiActaInicio(bean.getObjeto().getId());
            if (actaInicio != null && objOtrosi.getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                bean.addError("Creación Otrosí: Ya existe el acta de inicio.");
            }
            /*if (actaInicio == null && !objOtrosi.getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                bean.addError("Creación Otrosí: Debe crear el acta de inicio antes de registrar otrosies.");
            }*/

            if (objOtrosi.getContratoId() != null) {
                CntjOtrosi maxNumero = getCtnjOtrosiRemoto().ultimoNumeroOtroSi(objOtrosi.getContratoId().getId());
                if (maxNumero != null && objOtrosi.getFechasuscripcion().before(maxNumero.getFechasuscripcion())) {
                    bean.addError("Creación Otrosí: La fecha de suscripción del actual registro no puede ser inferior a la fecha de suscripción del ultimo otrosí registrado.");
                }
            }

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al validar informacion de otrosi. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al validar informacion de otrosi." + e.getMessage());
        }
    }

    private void guardarOtrosi(TareaBean bean, CntjOtrosi objOtrosi) {
        try {
            bean.auditoriaGuardar(objOtrosi);
            Integer idotrosi = getCtnjOtrosiRemoto().insertar(objOtrosi);
            boolean exitoso = true;
            try {
                //modificar plazo prorroga en contrato
                if (CntjConstantes.isProrroga(objOtrosi.getTipo())) {
                    CntjContrato contrato = objOtrosi.getContratoId();
                    contrato.setPlazoProrrogas(objOtrosi.getPlazoDias());
                    contrato.setFechaFin(objOtrosi.getFechaTerminacion());
                    if (objOtrosi.getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                        contrato.setFechaInicio(objOtrosi.getFechaInicio());
                    }
                    bean.auditoriaModificar(contrato);
                    getCtnjContratoRemoto().actualizarPlazoProrroga(contrato);
                    if (CntjConstantes.isAdicion(objOtrosi.getTipo())) {
                        contrato.setValorAdiciones(objOtrosi.getValor());
                        getCtnjContratoRemoto().actualizarValorAdiciones(contrato);
                    }
                } else if (CntjConstantes.isAdicion(objOtrosi.getTipo())) {
                    CntjContrato contrato = objOtrosi.getContratoId();
                    contrato.setValorAdiciones(objOtrosi.getValor());
                    bean.auditoriaModificar(contrato);
                    getCtnjContratoRemoto().actualizarValorAdiciones(contrato);
                }
            } catch (Exception e) {
                exitoso = false;
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar plazo prorroga en contrato. %s", e.getMessage()), e);
                bean.addError("Se presento inconveniente al modificar plazo prorroga en contrato." + e.getMessage());
                getCtnjOtrosiRemoto().eliminar(idotrosi);
            }

            if (exitoso) {
                bean.addMensaje("Otrosí guardado correctamente.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar otrosí. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar otrosí.");
        }
    }

    private void validarDatosLinea(TareaBean bean, CntjLinea objLinea) {
        try {
            if (objLinea.getTipo() == null) {
                bean.addError("Creación Linea: Debe indicar el tipo de linea.");
            }
            if (objLinea.getUsuariosId() == null) {
                bean.addError("Creación Linea: Debe seleccionar un responsable.");
            }
            if (objLinea.getDescripcion() == null) {
                bean.addError("Creación Linea: Debe ingresar una descripción.");
            }
            if (objLinea.getEstado() == null) {
                bean.addError("Creación Linea: Debe definir el estado.");
            }
            if (objLinea.getExpedienteId() == null) {
                bean.addError("Creación Linea: Debe inidicar el expediente contractual asociado.");
            }
            if (objLinea.getCntjComiteId() == null) {
                bean.addError("Creación Linea: Debe inidicar el comité asociado.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al validar informacion de lineas. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al validar informacion de lineas." + e.getMessage());
        }
    }

    private void guardarLinea(TareaBean bean, CntjLinea objLinea) {
        try {
            bean.auditoriaGuardar(objLinea);
            getCtnjLineaRemoto().insertar(objLinea);
            bean.addMensaje("Linea guardada correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar linea. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar linea.");
        }
    }

    private void listarUsuarios(TareaBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(2).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(2).getFiltros().put(CntjConstantes.ACTIVO_STR, String.valueOf(CntjConstantes.ACTIVO));
            bean.getParamConsulta(2).setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(2)));
            bean.setListaUsuario(getUsuarioRemoto().consultarLista(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el proceso.");
        }
    }

    private void consultarDocumento(TareaBean bean) {
        try {
            bean.setObjDocumento(getCntjDocumentoRemoto().consultar(bean.getObjDocumento().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar informacion.");
        }
    }

    private void actualizarCamposEstado(TareaBean bean) {
        try {
            bean.setListaCamposTransicion(new ArrayList<>());
            bean.setListaCamposGestionados(new ArrayList<>());
            bean.setObjeto(getCntjExpedienteRemoto().consultar(bean.getObjeto().getId()));
            CntjEstado estadoSiguiente = getCtnjEstadoRemoto().consultar(bean.getObjeToEjecucion().getCntjEstadoId().getId());
            bean.getObjeToEjecucion().setCntjEstadoId(estadoSiguiente);
            bean.getObjeToEjecucion().setEstadoSiguiente(estadoSiguiente.getNombre());
            List<CntjCampo> listadoCampos = getCntjCampoRemoto().listaCamposDocumentoEstadoGenerados(estadoSiguiente.getId());
            if (!listadoCampos.isEmpty()) {
                bean.setListaCampos(listadoCampos);
            } else {
                bean.setListaCampos(new ArrayList<>());
            }
            bean.setListaCamposTransicion(bean.getListaCampos());

            //Consultar campos ya diigenciados para presentar
            JSONObject objeto = new JSONObject(bean.getObjeto().getJsonData() != null ? bean.getObjeto().getJsonData() : "{}");
            JSONArray arrayObjeto = new JSONArray();
            List<Integer> idGestionados = new ArrayList<>();
            if (objeto.has(CntjConstantes.CAMPOS)) {
                arrayObjeto = objeto.getJSONArray(CntjConstantes.CAMPOS);
            }
            for (int index = 0; index < arrayObjeto.length(); index++) {
                JSONObject obj = arrayObjeto.getJSONObject(index);
                if (!obj.has(CntjConstantes.IDCAMPO)) {
                    continue;
                }
                CntjCampo campo = getCntjCampoRemoto().consultar(obj.getInt(CntjConstantes.IDCAMPO));
                campo.setEtiqueta(obj.getString(CntjConstantes.ETIQUETA));
                campo.setNombre(obj.getString(CntjConstantes.NOMBRE));
                campo.setTipoDato(obj.getInt(CntjConstantes.TIPO));
                if (obj.has(CntjConstantes.VALOR)) {
                    campo.setValor(obj.getString(CntjConstantes.VALOR));
                } else {
                    campo.setValor("");
                }
                if (obj.has(CntjConstantes.CAMPO)) {
                    campo.setCampo(obj.getString(CntjConstantes.CAMPO));
                }
                if (obj.has(CntjConstantes.TABLA)) {
                    campo.setTabla(obj.getString(CntjConstantes.TABLA));
                }
                if (obj.has(CntjConstantes.VALOR_STR)) {
                    campo.setValorStr(obj.getString(CntjConstantes.VALOR_STR));
                }
                if (obj.has(CntjConstantes.BORRADOR)) {
                    campo.setBorrador(obj.getBoolean(CntjConstantes.BORRADOR));
                }

                if (obj.getInt(CntjConstantes.TIPO) == CntjConstantes.TIPO_DATO_NUMERICO && (campo.getValor() != null && !campo.getValor().isEmpty())) {
                    String valornumerico = campo.getValor().replaceAll("\\D", "");
                    campo.setValor(valornumerico);
                } else if (obj.getInt(CntjConstantes.TIPO) == CntjConstantes.TIPO_DATO_MONEDA && (campo.getValor() != null && !campo.getValor().isEmpty())) {
                    String valornumerico = campo.getValor().replaceAll("\\D", "");
                    campo.setValor(new BigDecimal(valornumerico).toString());
                } else if (obj.getInt(CntjConstantes.TIPO) == CntjConstantes.TIPO_DATO_FECHA && (campo.getValor() != null && !campo.getValor().isEmpty())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechadt = sdf.parse(obj.getString(CntjConstantes.VALOR));
                    campo.setValorDt(fechadt);
                } else if (obj.getInt(CntjConstantes.TIPO) == CntjConstantes.TIPO_DATO_BOOLEAN && (campo.getValor() != null && !campo.getValor().isEmpty())) {
                    boolean valorbl = obj.getString(CntjConstantes.VALOR).equals(CntjConstantes.SI) ? true : false;
                    campo.setValorBl(valorbl);
                }

                if (campo.isBorrador()) {
                    IntStream.range(0, bean.getListaCamposTransicion().size())
                            .filter(i -> bean.getListaCamposTransicion().get(i).getId().equals(campo.getId()))
                            .findFirst()
                            .ifPresent(i -> bean.getListaCamposTransicion().set(i, campo));
                } else {
                    campo.setExistente(true);
                    bean.getListaCamposGestionados().add(campo);
                    idGestionados.add(campo.getId());
                }
            }

            bean.getListaCamposTransicion().removeIf(it -> idGestionados.contains(it.getId()));

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar informacion.");
        }
    }

    private void guardarBorrador(TareaBean bean) {
        try {

            if (bean.getObjeToEjecucion().getCntjEstadoId().getId() == null) {
                bean.addError("Debe seleccionar el Paso Permitido.");
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            bean.getListaCamposTransicion().stream()
                    .forEach(obj -> {
                        obj.setBorrador(true);
                    });

            List<CntjCampo> camposGestionados = bean.getListaCamposGestionados();
            camposGestionados.addAll(bean.getListaCamposTransicion());

            CntjExpediente expActual = getCntjExpedienteRemoto().consultar(bean.getObjeto().getId());

            //Se arma el jsondata para almacenar
            JSONObject objeto = new JSONObject(expActual.getJsonData() != null ? expActual.getJsonData() : "{}");
            JSONArray arrayObjeto = new JSONArray();
            if (objeto.has(CntjConstantes.CAMPOS)) {
                arrayObjeto = objeto.getJSONArray(CntjConstantes.CAMPOS);
            }
            for (CntjCampo campo : camposGestionados) {
                JSONObject objCampo = new JSONObject();
                objCampo.put(CntjConstantes.ETIQUETA, campo.getEtiqueta());
                objCampo.put(CntjConstantes.NOMBRE, campo.getNombre());
                objCampo.put(CntjConstantes.VALOR, campo.getValor());
                objCampo.put(CntjConstantes.VALOR_STR, campo.getValorStr());
                objCampo.put(CntjConstantes.TABLA, campo.getTabla());
                objCampo.put(CntjConstantes.CAMPO, campo.getCampo());
                objCampo.put(CntjConstantes.TIPO, campo.getTipoDato());
                objCampo.put(CntjConstantes.IDCAMPO, campo.getId());
                objCampo.put(CntjConstantes.BORRADOR, campo.isBorrador());

                if (campo.getTipoDato().equals(CntjConstantes.TIPO_DATO_MONEDA) && (campo.getValor() != null && !campo.getValor().isEmpty())) {
                    objCampo.put(CntjConstantes.VALOR, CntjUtilidades.getValorMoneda(campo.getValor()));
                }
                if (campo.getTipoDato().equals(CntjConstantes.TIPO_DATO_TEXTO_LARGO) && (campo.getValor() != null && !campo.getValor().isEmpty())) {
                    objCampo.put(CntjConstantes.VALOR_STR, campo.getValor());
                }

                Integer existeCampo = CntjUtilidades.getIndiceCampoExistente(objeto.toString(), campo.getId());
                if (existeCampo != null) {
                    arrayObjeto.put(existeCampo, objCampo);
                } else {
                    arrayObjeto.put(objCampo);
                }
            }
            objeto.put(CntjConstantes.CAMPOS, arrayObjeto);
            expActual.setJsonData(objeto.toString());
            bean.auditoriaModificar(expActual);
            getCntjExpedienteRemoto().actualizar(expActual);
            bean.addMensaje("Borrador guardado correctamente");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar.");
        }
    }

    private void consultarDocumentosPrevisualizar(TareaBean bean) {
        try {
            bean.setContenidoPdf(null);
            CntjEstado estadoSiguiente = getCtnjEstadoRemoto().consultar(bean.getObjeToEjecucion().getCntjEstadoId().getId());
            bean.setListaPrevisualizacion(getCntjPlantillaRemoto().listaDocumentoEstadoGeneradosMixtos(estadoSiguiente.getId()));
            bean.setObjPlantilla(new CntjPlantilla());
            List<CntjPlantilla> plantillas = getCntjPlantillaRemoto().listaDocumentoEstadoGeneradosMixtos(estadoSiguiente.getId());
            if (plantillas.isEmpty()) {
                bean.addError("No existen documentos para previsualizar.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar informacion.");
        }
    }

    private void verDocumentosPrevisualizar(TareaBean bean) {
        try {
            bean.setObjeto(getCntjExpedienteRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjPlantilla(getCntjPlantillaRemoto().consultar(bean.getObjPlantilla().getId()));
            List<CntjCampo> camposAuxiliar = new ArrayList<>();
            camposAuxiliar.addAll(bean.getListaCamposGestionados());
            camposAuxiliar.addAll(bean.getListaCamposTransicion());

            String plantilla = CntjConstantes.ESTILO_DOCUMENTO + bean.getObjPlantilla().getEstructura();

            for (CntjCampo campo : camposAuxiliar) {
                plantilla = plantilla.replace("<!-- -->", "");
                if (campo.getTipoDato().equals(CntjConstantes.TIPO_DATO_MONEDA)) {
                    plantilla = plantilla.replace(campo.getEtiqueta(), CntjUtilidades.getValorMoneda(campo.getValor()));
                } else {
                    if (campo.getValorStr() != null && !campo.getValorStr().isEmpty()) {
                        plantilla = plantilla.replace(campo.getEtiqueta(), campo.getValorStr());
                    } else if (campo.getValor() != null && !campo.getValor().isEmpty()) {
                        plantilla = plantilla.replace(campo.getEtiqueta(), campo.getValor());
                    } else {
                        plantilla = plantilla.replace(campo.getEtiqueta(), String.format("<< CAMPO NO COMPLETADO: [%s] >> ", campo.getEtiqueta()));
                    }
                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ConverterProperties properties = new ConverterProperties();
            HtmlConverter.convertToPdf(plantilla, outputStream, properties);
            byte[] pdfBytes = outputStream.toByteArray();
            String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);
            bean.setContenidoPdf(base64Pdf);
            bean.addMensaje("Esta es una vista previa. Algunos campos no han sido diligenciados y se mostrarán como campos incompletos.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar informacion.");
        }
    }

}
