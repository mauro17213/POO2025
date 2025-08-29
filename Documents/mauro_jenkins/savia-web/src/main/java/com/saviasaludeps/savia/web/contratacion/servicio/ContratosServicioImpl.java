/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCobertura;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoHistorico;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoHistoricoValidacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoNotaTecnica;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.DTOCntContrato;
import com.saviasaludeps.savia.dominio.contratacion.DTOCntContratoDetalle;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifarioValor;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCoberturaRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoHistoricoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoHistoricoValidacionRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoNotaTecnicaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.web.contratacion.bean.ContratosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoSedeRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaPaqueteRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaSoatTarifarioValorRemoto;
import com.saviasaludeps.savia.web.contratacion.utilidades.ContratacionParametro;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author jyperez
 */
public class ContratosServicioImpl extends AccionesBO implements ContratosServicioIface {

    private CntContratoSedeRemoto getContratoSedeRemoto() throws Exception {
        return (CntContratoSedeRemoto) RemotoEJB.getEJBRemoto(("CntContratoSedeServicio"), CntContratoSedeRemoto.class.getName());
    }
 
    private CntContratoRemoto getContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto(("CntContratoServicio"), CntContratoRemoto.class.getName());
    }

    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }

    private MaTecnologiaServicioHabilitacionRemoto getTecnologiaServicioHabilitacionRemoto() throws Exception {
        return (MaTecnologiaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto(("MaTecnologiaServicioHabilitacionServicio"), MaTecnologiaServicioHabilitacionRemoto.class.getName());
    }

    private MaSoatTarifarioValorRemoto getTarifarioValorRemoto() throws Exception {
        return (MaSoatTarifarioValorRemoto) RemotoEJB.getEJBRemoto(("MaSoatTarifarioValorServicio"), MaSoatTarifarioValorRemoto.class.getName());
    }

    private MaPaqueteRemoto getPaqueteRemoto() throws Exception {
        return (MaPaqueteRemoto) RemotoEJB.getEJBRemoto(("MaPaqueteServicio"), MaPaqueteRemoto.class.getName());
    }

    private CntContratoHistoricoRemoto getCntContratoHistoricoRemoto() throws Exception {
        return (CntContratoHistoricoRemoto) RemotoEJB.getEJBRemoto("CntContratoHistoricoServicio", CntContratoHistoricoRemoto.class.getName());
    }
    
    //2022-08-02 cmartins
    private CntContratoHistoricoValidacionRemoto getCntContratoHistoricoValidacionRemoto()throws Exception{
        return (CntContratoHistoricoValidacionRemoto) RemotoEJB.getEJBRemoto("CntContratoHistoricoValidacionServicio", CntContratoHistoricoValidacionRemoto.class.getName());
    }

    private CntContratoNotaTecnicaRemoto getContratoNotaTecnicaRemoto() throws Exception {
        return (CntContratoNotaTecnicaRemoto) RemotoEJB.getEJBRemoto(("CntContratoNotaTecnicaServicio"), CntContratoNotaTecnicaRemoto.class.getName());
    }

    private CntContratoCoberturaRemoto getContratoCoberturaRemoto() throws Exception {
        return (CntContratoCoberturaRemoto) RemotoEJB.getEJBRemoto(("CntContratoCoberturaServicio"), CntContratoCoberturaRemoto.class.getName());
    }
    
    private CntContratoHistoricoRemoto getContratoHistoricoRemoto() throws Exception {
        return (CntContratoHistoricoRemoto) RemotoEJB.getEJBRemoto(("CntContratoHistoricoServicio"), CntContratoHistoricoRemoto.class.getName());
    }

    @Override
    public void Accion(ContratosBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
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
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    if (bean.getDoAccion() == Url.ACCION_LISTAR) {
                        //listarContratoDetalle(bean);
                        listarGestionarContratoDetalle(bean);
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearContratoDetalle(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarContratoDetalle(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editarContratoDetalle(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarContratoDetalle(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    borrarContratoDetalle(bean);
                    break;
                case Url.ACCION_ADICIONAL_6:
                    imprimirDetalleContrato(bean);
                    break;
                case Url.ACCION_ADICIONAL_8:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarNotaTecnica(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarNotaTecnica(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarNotaTecnica(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarNotaTecnica(bean);
                            break;
                        case Url.ACCION_VER:
                            verNotaTecnica(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarNotaTecnica(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            imprimirNotaTecnica(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_9:
                    verHistoricos(bean);
                    break;
                case Url.ACCION_ADICIONAL_10:
                    autorizarGestionContrato(bean);
                    break;

            }
            cargas(bean);
        }
    }

    private void listar(ContratosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getContratoSedeRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getContratoSedeRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(ContratosBean bean) {
        try {
            bean.setObjetoContrato(getContratoRemoto().consultarConSedes(bean.getObjetoContrato().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(ContratosBean bean) {
        try {
            bean.setObjetoContrato(new CntContrato());
            bean.getObjetoContrato().setCntPrestador(new CntPrestador());
            bean.setListaContratoSedes(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(ContratosBean bean) {
        Maestro maestro;
        Maestro regimen;
        int countContrato;
        CntContrato objetoModificado;
        CntContratoHistorico contratoHistorico;
        //2022-08-02 cmartins
        try {
            //incluimos validación de fechas en esta parte, para corregir que se está mostrando el texto en inglés
            if (bean.getObjetoContrato().getFechaFin().compareTo(bean.getObjetoContrato().getFechaInicio()) < 0) {
                bean.addError("La fecha Inicial no puede ser mayor a la Fecha Final.");
            }
            //validamos contrato existente
            countContrato = getContratoRemoto().consultarCantidadPorContrato(bean.getObjetoContrato().getContrato());
            if (countContrato > 0) {
                bean.addError("Hay un contrato existente con el número de contrato digitado.");
            }
            //obtenemos la empresa del usuario
            bean.getObjetoContrato().setGnEmpresa(bean.getConexion().getUsuario().getEmpresa());
            //obtenemos el valor restante de maestros
            // 2021-06-30 jyperez se comenta esta parte debido a que, estos valores se cargaran cuando se modifiquen las fechas
            /*if (bean.getObjetoContrato().getMaeEstadoContratoId() != 0) {
                maestro = bean.getHashEstadoContrato().get(bean.getObjetoContrato().getMaeEstadoContratoId());
                bean.getObjetoContrato().setMaeEstadoContratoCodigo(maestro.getValor());
                bean.getObjetoContrato().setMaeEstadoContratoValor(maestro.getNombre());*/
            if (bean.getObjetoContrato().getMaeEstadoContratoId() == 0) {
                bean.addError("Estado Contrato: Se debe ingresar las fechas de vigencia del Contrato para poder cargar el estado correcto.");
            }
            //obtenemos el valor restante de maestro régimen
            if (bean.getObjetoContrato().getMaeRegimenId() != null && bean.getObjetoContrato().getMaeRegimenId() != 0) {
                regimen = bean.getHashRegimenContrato().get(bean.getObjetoContrato().getMaeRegimenId());
                bean.getObjetoContrato().setMaeRegimenCodigo(regimen.getValor());
                bean.getObjetoContrato().setMaeRegimenValor(regimen.getNombre());
            } else {
                bean.getObjetoContrato().setMaeRegimenCodigo("");
                bean.getObjetoContrato().setMaeRegimenValor("");
            }
            // validaciones
            if (bean.getObjetoContrato().getCntPrestador().getId() == null) {
                bean.addError("Se debe agregar el prestador al contrato.");
            }
            if (bean.getListaContratoSedes().isEmpty()) {
                bean.addError("Se debe agregar al menos una sede al contrato.");
            } else {
                //2021-03-23 jyperez INC 742 validamos que las fechas de las sedes esten dentro del rango de fechas del contrato
                for (CntContratoSede sede : bean.getListaContratoSedes()) {
                    if (!(sede.getFechaInicio().compareTo(bean.getObjetoContrato().getFechaInicio()) >= 0
                            && sede.getFechaInicio().compareTo(bean.getObjetoContrato().getFechaFin()) <= 0)) {
                        bean.addError("La Fecha Inicio en la sede " + sede.getCntPrestadorSede().getCodigoHabilitacionSede() + " - " + sede.getCntPrestadorSede().getNombreSede() + " no se encuentra entre las fechas inicial y final del contrato.");
                    }
                    if (!(sede.getFechaFin().compareTo(bean.getObjetoContrato().getFechaInicio()) >= 0
                            && sede.getFechaFin().compareTo(bean.getObjetoContrato().getFechaFin()) <= 0)) {
                        bean.addError("La Fecha Fin en la sede " + sede.getCntPrestadorSede().getCodigoHabilitacionSede() + " - " + sede.getCntPrestadorSede().getNombreSede() + " no se encuentra entre las fechas inicial y final del contrato.");
                    }
                }
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjetoContrato());
                //guardamos el registro
                bean.getObjetoContrato().setId(getContratoRemoto().insertar(bean.getObjetoContrato()));
                //guardamos cada una de las sedes del contrato
                //getContratoSedeRemoto().insertar(bean.getObjeto());
                //realizamos la creación de ContratoSedes
                for (CntContratoSede sede : bean.getListaContratoSedes()) {
                    bean.auditoriaGuardar(sede);
                    //ponerle información del contrato
                    sede.setCntContrato(new CntContrato(bean.getObjetoContrato().getId()));
                    if (sede.getMaeModalidadContratoId() != 0) {
                        maestro = bean.getHashModalidadContrato().get(sede.getMaeModalidadContratoId());
                        if (maestro != null) {
                            sede.setMaeModalidadContratoCodigo(maestro.getValor());
                            sede.setMaeModalidadContratoValor(maestro.getNombre());
                        }
                    }
                    sede.setId(getContratoSedeRemoto().insertar(sede));
                    //2022-07-14 jyperez adicionamos los registros de cobertura.
                    try {
                        if (sede.getListaCntContratoCobertura() != null && !sede.getListaCntContratoCobertura().isEmpty()) {
                            for (CntContratoCobertura cobertura : sede.getListaCntContratoCobertura()) {
                                cobertura.setCntContratoSede(sede);
                                bean.auditoriaGuardar(cobertura);
                                getContratoCoberturaRemoto().insertar(cobertura);
                            }
                        }
                    } catch (Exception ex) {
                        //lanzamos excepción controlada para avisar que hubo error almacenando los registros de cobertura
                        throw new Exception("Error insertando la lista de CntContratoCoberturas. Error: " + ex.getMessage());
                    }
                }
                //2022-01-13 jyperez consultamos el objeto contrato luego de modificación
                objetoModificado = getContratoRemoto().consultar(bean.getObjetoContrato().getId());
                if (objetoModificado == null) {
                    throw new Exception("Hubo un error obteniendo los datos del registro modificado para el histórico del contrato.");
                } else {
                    contratoHistorico = new CntContratoHistorico();
                    contratoHistorico.setTipo(CntContratoHistorico.TIPO_CONTRATO);
                    contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                    contratoHistorico.setCntContrato(objetoModificado);
                    bean.auditoriaGuardar(contratoHistorico);
                    //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContrato
                    contratoHistorico.setToString(new DTOCntContrato(objetoModificado).toStringJson());
                    getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                }
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(ContratosBean bean) {
        try {
            //bean.setObjeto(getContratoSedeRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoContrato(getContratoRemoto().consultar(bean.getObjetoContrato().getId()));
            //cargamos la lista de contratoSedes
            bean.setListaContratoSedes(bean.getObjetoContrato().getListaContratoSedes());
            bean.setListaContratoSedesBorrar(new ArrayList<>());
            //2022-04-07 jyperez copiamos la fecha antes de modificación del contrato
            bean.setFechaFinContrato(bean.getObjetoContrato().getFechaFin());
            bean.setActualizarContratoDetalles(false);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(ContratosBean bean) {
        Maestro maestro;
        Maestro regimen;
        CntContratoHistorico contratoHistorico;
        CntContrato objetoAnterior;
        CntContrato objetoModificado;
        CntContratoDetalle objetoContratoDetalle;
        
        try {
            // validaciones
            //incluimos validación de fechas en esta parte, para corregir que se está mostrando el texto en inglés
            if (bean.getObjetoContrato().getFechaFin().compareTo(bean.getObjetoContrato().getFechaInicio()) < 0) {
                bean.addError("La fecha Inicial del contrato no puede ser mayor a la Fecha Final.");
            }
            //2024-05-07 jyperez validamos que la fecha fin no sea menor a la fecha actual
            if (bean.getObjetoContrato().getFechaFin().before(bean.getFechaActual()) ){
                bean.addError("La fecha final del contrato no puede ser menor a la fecha actual.");
            }
            //obtenemos el valor restante de maestros
            // 2021-06-30 jyperez se comenta esta parte debido a que, estos valores se cargaran cuando se modifiquen las fechas
            /*if (bean.getObjetoContrato().getMaeEstadoContratoId() != 0) {
                maestro = bean.getHashEstadoContrato().get(bean.getObjetoContrato().getMaeEstadoContratoId());
                bean.getObjetoContrato().setMaeEstadoContratoCodigo(maestro.getValor());
                bean.getObjetoContrato().setMaeEstadoContratoValor(maestro.getNombre());*/
            if (bean.getObjetoContrato().getMaeEstadoContratoId() == 0) {
                bean.addError("Estado Contrato: Se debe ingresar las fechas de vigencia del Contrato para poder cargar el estado correcto.");
            }
            //obtenemos el valor restante de maestro régimen
            if (bean.getObjetoContrato().getMaeRegimenId() != null && bean.getObjetoContrato().getMaeRegimenId() != 0) {
                regimen = bean.getHashRegimenContrato().get(bean.getObjetoContrato().getMaeRegimenId());
                bean.getObjetoContrato().setMaeRegimenCodigo(regimen.getValor());
                bean.getObjetoContrato().setMaeRegimenValor(regimen.getNombre());
            } else {
                bean.getObjetoContrato().setMaeRegimenCodigo("");
                bean.getObjetoContrato().setMaeRegimenValor("");
            }
            // validaciones
            if (bean.getObjetoContrato().getCntPrestador().getId() == null) {
                bean.addError("Se debe agregar el prestador al contrato.");
            }
            if (bean.getListaContratoSedes().isEmpty()) {
                bean.addError("Se debe agregar al menos una sede al contrato.");
            } else {
                //2021-03-23 jyperez INC 742 validamos que las fechas de las sedes esten dentro del rango de fechas del contrato
                for (CntContratoSede sede : bean.getListaContratoSedes()) {
                    if (!(sede.getFechaInicio().compareTo(bean.getObjetoContrato().getFechaInicio()) >= 0
                            && sede.getFechaInicio().compareTo(bean.getObjetoContrato().getFechaFin()) <= 0)) {
                        bean.addError("La Fecha Inicio en la sede " + sede.getCntPrestadorSede().getCodigoHabilitacionSede() + " - " + sede.getCntPrestadorSede().getNombreSede() + " no se encuentra entre las fechas inicial y final del contrato.");
                    }
                    if (!(sede.getFechaFin().compareTo(bean.getObjetoContrato().getFechaInicio()) >= 0
                            && sede.getFechaFin().compareTo(bean.getObjetoContrato().getFechaFin()) <= 0)) {
                        bean.addError("La Fecha Fin en la sede " + sede.getCntPrestadorSede().getCodigoHabilitacionSede() + " - " + sede.getCntPrestadorSede().getNombreSede() + " no se encuentra entre las fechas inicial y final del contrato.");
                    }
                }
            }
            //2021-12-15 se deshabilita nuevamente
            //2022-01-13 jyperez consultamos el estado del objeto Anterior, solo con la información de Sedes
            objetoAnterior = getContratoRemoto().consultar(bean.getObjetoContrato().getId());
            if (objetoAnterior == null) {
                bean.addError("Hubo un error obteniendo los datos del registro antes de modificación para el histórico del contrato.");
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                //2022-04-07 jyperez realizamos la evaluación de la carga si se esta haciendo prorroga al contrato
                if (fechaDespues(bean.getObjetoContrato().getFechaFin(), bean.getFechaFinContrato())) {
                    bean.setActualizarContratoDetalles(true);
                } else {
                    bean.setActualizarContratoDetalles(false);
                }
                bean.auditoriaModificar(bean.getObjetoContrato());
                //guardamos el registro
                getContratoRemoto().actualizar(bean.getObjetoContrato());
                //ahora procedemos a realizar la validación de los registros a Editar y a Crear
                for (CntContratoSede sede : bean.getListaContratoSedes()) {
                    if (sede.isNuevoRegistro()) {
                        sede.setId(null);
                        sede.setCntContrato(bean.getObjetoContrato());
                        bean.auditoriaGuardar(sede);
                        //Actualizar información de la modalidad
                        if (sede.getMaeModalidadContratoId() != 0) {
                            maestro = bean.getHashModalidadContrato().get(sede.getMaeModalidadContratoId());
                            if (maestro != null) {
                                sede.setMaeModalidadContratoCodigo(maestro.getValor());
                                sede.setMaeModalidadContratoValor(maestro.getNombre());
                            }
                        }
                        //2022-04-07 actualizamos la fecha fin dependiendo de si hay prorroga.
                        if (bean.isActualizarContratoDetalles()) {
                            sede.setFechaFin(bean.getObjetoContrato().getFechaFin());
                        }
                        sede.setId(getContratoSedeRemoto().insertar(sede));
                        //2022-07-14 jyperez adicionamos los registros de cobertura.
                        try {
                            if (sede.getListaCntContratoCobertura() != null && !sede.getListaCntContratoCobertura().isEmpty()) {
                                for (CntContratoCobertura cobertura : sede.getListaCntContratoCobertura()) {
                                    cobertura.setCntContratoSede(sede);
                                    bean.auditoriaGuardar(cobertura);
                                    getContratoCoberturaRemoto().insertar(cobertura);
                                }
                            }
                        } catch (Exception ex) {
                            //lanzamos excepción controlada para avisar que hubo error almacenando los registros de cobertura
                            throw new Exception("Error insertando la lista de CntContratoCoberturas. Error: " + ex.getMessage());
                        }
                    } else if (sede.isEditado()) {
                        bean.auditoriaModificar(sede);
                        //Actualizar información de la modalidad
                        if (sede.getMaeModalidadContratoId() != 0) {
                            maestro = bean.getHashModalidadContrato().get(sede.getMaeModalidadContratoId());
                            if (maestro != null) {
                                sede.setMaeModalidadContratoCodigo(maestro.getValor());
                                sede.setMaeModalidadContratoValor(maestro.getNombre());
                            }
                        }
                        //2022-04-07 actualizamos la fecha fin dependiendo de si hay prorroga.
                        if (bean.isActualizarContratoDetalles()) {
                            sede.setFechaFin(bean.getObjetoContrato().getFechaFin());
                        }
                        getContratoSedeRemoto().actualizar(sede);
                        //2022-07-14 jyperez validamos si debemos crear o eliminar los registros de coberturas
                        try {
                            if (sede.getListaCntContratoCobertura() != null && !sede.getListaCntContratoCobertura().isEmpty()) {
                                for (CntContratoCobertura cobertura : sede.getListaCntContratoCobertura()) {
                                    if (cobertura.isNuevoRegistro()) {
                                        cobertura.setCntContratoSede(sede);
                                        bean.auditoriaGuardar(cobertura);
                                        getContratoCoberturaRemoto().insertar(cobertura);
                                    } else if (cobertura.isBorrar()) {
                                        getContratoCoberturaRemoto().eliminar(cobertura.getId());
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            //lanzamos excepción controlada para avisar que hubo error almacenando los registros de cobertura
                            throw new Exception("Error actualizando la lista de CntContratoCoberturas. Error: " + ex.getMessage());
                        }
                        //2022-04-19 jyperez Se valida si el item no fué actualizado pero el contrato si, entonces se actualiza la fecha de prórroga
                    } else if (bean.isActualizarContratoDetalles()) {
                        sede.setFechaFin(bean.getObjetoContrato().getFechaFin());
                        getContratoSedeRemoto().actualizar(sede);
                    }
                }
                // procedemos por ultimo a ejecutar los procesos de los registros a eliminar
                for (CntContratoSede sede : bean.getListaContratoSedesBorrar()) {
                    getContratoSedeRemoto().eliminar(sede.getId());
                }
                //2022-04-07 jyperez si hay prórroga, ejecutamos la actualización de la fecha en los contrato detalles activos
                if (bean.isActualizarContratoDetalles()) {
                    getContratoDetalleRemoto().actualizarProrrogaCntContratoDetalles(bean.getObjetoContrato().getId(), bean.getObjetoContrato().getFechaFin());
                }
                //2022-01-13 jyperez consultamos el objeto contrato luego de modificación
                objetoModificado = getContratoRemoto().consultar(bean.getObjetoContrato().getId());
                if (objetoModificado == null) {
                    throw new Exception("Hubo un error obteniendo los datos del registro modificado para el histórico del contrato.");
                }
                //2022-01-13 jyperez adición de almacenamiento de histórico afiliado. Inicialmente validamos que exista un histórico
                Boolean existehistorico = getCntContratoHistoricoRemoto().consultarHistoricoExistente(CntContratoHistorico.TIPO_CONTRATO, bean.getObjetoContrato().getId(), 0, 0);
                //2022-07-28 cmartins creacion de objeto DTOCntContrato para enviar JSON del contrato modificado
                if (existehistorico) {
                    contratoHistorico = new CntContratoHistorico();
                    contratoHistorico.setTipo(CntContratoHistorico.TIPO_CONTRATO);
                    contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                    contratoHistorico.setCntContrato(objetoModificado);
                    bean.auditoriaGuardar(contratoHistorico);
                    //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContrato
                    contratoHistorico.setToString(new DTOCntContrato(objetoModificado).toStringJson());
                    getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                } else {
                    // se ignresa esta validación teniendo en cuenta que el objeto se está consultando.
                    if (objetoAnterior != null) {
                        //2022-07-28 cmartins creacion de objeto DTOCntContrato para enviar JSON del contrato anterior
                        contratoHistorico = new CntContratoHistorico();
                        contratoHistorico.setTipo(CntContratoHistorico.TIPO_CONTRATO);
                        contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                        contratoHistorico.setCntContrato(objetoAnterior);
                        //bean.auditoriaGuardar(contratoHistorico);
                        //las fechas de auditoria serán las de creación o de modicicación segun exista en el registro.
                        if (objetoAnterior.getFechaHoraModifica() != null) {
                            contratoHistorico.setFechaHoraCrea(objetoAnterior.getFechaHoraModifica());
                            contratoHistorico.setUsuarioCrea(objetoAnterior.getUsuarioModifica());
                            contratoHistorico.setTerminalCrea(objetoAnterior.getTerminalModifica());
                        } else {
                            contratoHistorico.setFechaHoraCrea(objetoAnterior.getFechaHoraCrea());
                            contratoHistorico.setUsuarioCrea(objetoAnterior.getUsuarioCrea());
                            contratoHistorico.setTerminalCrea(objetoAnterior.getTerminalCrea());
                        }
                        //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContrato
                        contratoHistorico.setToString(new DTOCntContrato(objetoAnterior).toStringJson());
                        getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                    }
                    //ingresamos el registro actual
                    contratoHistorico = new CntContratoHistorico();
                    contratoHistorico.setTipo(CntContratoHistorico.TIPO_CONTRATO);
                    contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                    contratoHistorico.setCntContrato(objetoModificado);
                    bean.auditoriaGuardar(contratoHistorico);
                    //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContrato
                    contratoHistorico.setToString(new DTOCntContrato(objetoModificado).toStringJson());
                    getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                }
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(ContratosBean bean) {
        try {
            bean.setObjetoContrato(getContratoRemoto().eliminar(bean.getObjetoContrato().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(ContratosBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    //Estado
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargaInicial(ContratosBean bean) {
        try {
            //carga de maestros
//            bean.setListaModalidadContrato(bean.getContratacionSingle().listarPorTipo(MaestroTipo.CNT_MODALIDAD));
            bean.setListaModalidadContrato(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNT_MODALIDAD));
            bean.setHashModalidadContrato(Util.convertToHash(bean.getListaModalidadContrato()));
//            bean.setListaEstadoContrato(bean.getContratacionSingle().listarPorTipo(MaestroTipo.CNT_ESTADO));
            bean.setListaEstadoContrato(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNT_ESTADO));
            bean.setHashEstadoContrato(Util.convertToHashValor(bean.getListaEstadoContrato()));
//            bean.setListaClasePrestador(bean.getContratacionSingle().listarPorTipo(MaestroTipo.CNT_CLASE_PRESTADOR));
            bean.setListaClasePrestador(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNT_CLASE_PRESTADOR));
            bean.setHashClasePrestador(Util.convertToHash(bean.getListaClasePrestador()));
//            bean.setListaTipoDocumento(bean.getContratacionSingle().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setListaTipoDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setHashTipoDocumento(Util.convertToHash(bean.getListaTipoDocumento()));
            //2021-02-22 jyperez INC 687 - Se ajusta la lista al maestro tipo 06 solicitado
//            bean.setListaAmbito(bean.getContratacionSingle().listarPorTipo(MaestroTipo.GN_AMBITO));
            bean.setListaAmbito(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_AMBITO));
            bean.setHashAmbito(Util.convertToHash(bean.getListaAmbito()));
            //2021-06-15 jyperez Sprint 1 se adiciona maestro Régimen
//            bean.setListaRegimenContrato(bean.getContratacionSingle().listarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setListaRegimenContrato(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setHashRegimenContrato(Util.convertToHash(bean.getListaRegimenContrato()));
            //cargamos todas las IPS
            //bean.setListaUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
            //2022-07-14 jyperez cargamos lista de Municipios para usarlos en la cobertura
            //bean.setListaMunicipiosCobertura(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
            try{
                bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
                bean.setListaMunicipiosCobertura(UbicacionSingle.getInstance().getListaMunicipios());
            } catch (Exception ex) {
                bean.setListaUbicaciones(new ArrayList<>());
                if (bean.getListaUbicaciones() != null) {
                    bean.setListaMunicipiosCobertura(bean.getListaUbicaciones());
                } else {
                    bean.setListaMunicipiosCobertura(new ArrayList<>());
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarContratoDetalle(ContratosBean bean) {
        // obtenemos la lista de contrato Detalle
        try {
            bean.getParamConsultaContratoDetalle().setCantidadRegistros(getContratoDetalleRemoto().consultarCantidadLista(bean.getParamConsultaContratoDetalle()));
            bean.setListaContratoDetalle(getContratoDetalleRemoto().consultarLista(bean.getParamConsultaContratoDetalle()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarGestionarContratoDetalle(ContratosBean bean) {
        // obtenemos la lista de contrato Detalle
        try {
            //obtenemos los datos del objeto ContratoSede
            bean.setObjeto(getContratoSedeRemoto().consultar(bean.getObjeto().getId()));
            bean.getParamConsultaContratoDetalle().setCantidadRegistros(getContratoDetalleRemoto().consultarCantidadLista(bean.getParamConsultaContratoDetalle()));
            bean.setListaContratoDetalle(getContratoDetalleRemoto().consultarLista(bean.getParamConsultaContratoDetalle()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearContratoDetalle(ContratosBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void guardarContratoDetalle(ContratosBean bean) {
        Maestro maestro;
        CntContratoHistorico contratoHistorico;
        CntContratoHistoricoValidacion contratoHistoricoValidacion;
        CntContratoDetalle objetoModificado;
        CntContratoDetalle contratoDetalle;
        try {
            //validaciones
            //incluimos validación de fechas en esta parte, para corregir que se está mostrando el texto en inglés
            if (bean.getObjetoContratoDetalle().getFechaHoraFin().compareTo(bean.getObjetoContratoDetalle().getFechaHoraInicio()) < 0) {
                bean.addError("La fecha Inicio no puede ser mayor a la Fecha Fin.");
            }
            //validamos que en caso de tener tarifa Soat, se haya seleccionado un valor en la lista de Año Tarifa
            if (bean.getObjetoContratoDetalle().getTipoManualTarifario() == ContratacionParametro.TIPO_MANUAL_TARIFARIO_SOAT
                    && bean.getObjetoContratoDetalle().getMaManualTarifarioAgno() == null) {
                bean.addError("Año Soat: El valor es requerido si se selecciona Manual Tarifario SOAT.");
            }

            //validamos que se haya asignado una tecnología
            if (bean.getObjetoContratoDetalle().getMaTecnologiaId() == 0) {
                bean.addError("Debe seleccionarse una Tecnologia.");
            }

            if (bean.getObjetoContratoDetalle().getTipoTecnologia() == ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA
                    || bean.isPaqueteTecnologia()) {
                if (bean.getObjetoContratoDetalle().getMaServicioHabilitacionId() == null) {
                    bean.addError("Se debe seleccionar un Servicio de Habilitación cuando se configura una Tecnología o un Paquete de Tecnologías.");
                } else {
                    for (MaTecnologiaServicioHabilitacion serv : bean.getListaServicios()) {
                        if (serv.getMaServicioHabilitacion().getId().equals(bean.getObjetoContratoDetalle().getMaServicioHabilitacionId())) {
                            bean.getObjetoContratoDetalle().setMaServicioHabilitacionCodigo(String.valueOf(serv.getMaServicioHabilitacion().getCodigo()));
                            bean.getObjetoContratoDetalle().setMaServicioHabilitacionValor(serv.getMaServicioHabilitacion().getNombre());
                        }
                    }
                }
            }

            //obtenemos el valor restante de maestros
            if (bean.getObjetoContratoDetalle().getMaeAmbitoId() != null) {
                maestro = bean.getHashAmbito().get(bean.getObjetoContratoDetalle().getMaeAmbitoId());
                bean.getObjetoContratoDetalle().setMaeAmbitoCodigo(maestro.getValor());
                bean.getObjetoContratoDetalle().setMaeAmbitoValor(maestro.getNombre());
            }

            //incluimos validaciones
            //2024-05-07 jyperez validamos las fechas del contrato sede
            bean.addError(validarFechainicioContratoSede(bean.getObjetoContratoDetalle()));
            bean.addError(validarFechaFinContratoSede(bean.getObjetoContratoDetalle()));
            //2021-08-30 jyperez Se elimina la validación de contrato existente y vigente.
            //bean.addError(validarContratoExistenteYVigente(bean));
            if (bean.getObjetoContratoDetalle().getTipoTecnologia() == ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA) {
                // acá se incluye el servicio en la validación.
                bean.addError(validarContratoDetalleTecnologiaExistente(bean.getObjetoContratoDetalle()));
            } else {

                bean.addError(validarContratoDetalleExistente(bean.getObjetoContratoDetalle()));
            }
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjetoContratoDetalle());
                //guardamos el registro
                bean.getObjetoContratoDetalle().setId(getContratoDetalleRemoto().insertar(bean.getObjetoContratoDetalle()));
                //2022-01-13 jyperez consultamos el estado del objeto contrato detalle Anterior, con todos sus datos.
                objetoModificado = bean.getObjetoContratoDetalle();//getContratoDetalleRemoto().consultar(bean.getObjetoContratoDetalle().getId());
                //2022-01-14 jyperez adición de almacenamiento de histórico afiliado.
                if (objetoModificado != null) {
                    contratoHistorico = new CntContratoHistorico();
                    contratoHistorico.setTipo(CntContratoHistorico.TIPO_DETALLE);
                    contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                    bean.auditoriaGuardar(contratoHistorico);
                    contratoHistorico.setCntContrato(objetoModificado.getCntContrato());
                    contratoHistorico.setCntContratoSede(objetoModificado.getCntContratoSede());
                    contratoHistorico.setCntContratoDetalle(objetoModificado);
                    //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContratoDetalle                    
                    contratoHistorico.setToString(new DTOCntContratoDetalle(objetoModificado).toStringJson());
                    getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                    
                    //2022-08-05 cmartins - Almacenamiento de infromación en CntContratoHistoricoValidacion
                    contratoDetalle = getContratoDetalleRemoto().consultar(bean.getObjetoContratoDetalle().getId());
                    contratoHistoricoValidacion = new CntContratoHistoricoValidacion();
                    if (contratoDetalle != null){
                        contratoHistoricoValidacion.setCntPrestadorId(objetoModificado.getCntContrato().getCntPrestador().getId());
                        contratoHistoricoValidacion.setCntPrestadorNumeroDocumento(objetoModificado.getCntContrato().getCntPrestador().getNumeroDocumento());
                        contratoHistoricoValidacion.setCntPrestadorCodigoMinSalud(objetoModificado.getCntContrato().getCntPrestador().getCodigoMinSalud());
                        contratoHistoricoValidacion.setCntPrestadorSedeId(objetoModificado.getCntContratoSede().getCntPrestadorSede().getId());
                        contratoHistoricoValidacion.setCntPrestadorSedeCodigoHabilitacion(objetoModificado.getCntContratoSede().getCntPrestadorSede().getCodigoHabilitacionSede());
                        contratoHistoricoValidacion.setCntContratoId(objetoModificado.getCntContrato().getId());
                        contratoHistoricoValidacion.setCntContratoSedeId(objetoModificado.getCntContratoSede().getId());
                        contratoHistoricoValidacion.setCntContratoDetalleId(objetoModificado.getId());
                        contratoHistoricoValidacion.setTipoTecnologia(objetoModificado.getTipoTecnologia());
                        contratoHistoricoValidacion.setMaTecnologiaId(objetoModificado.getMaTecnologiaId());
                        contratoHistoricoValidacion.setMaTecnologiaCodigo(objetoModificado.getMaTecnologiaCodigo());
                        contratoHistoricoValidacion.setMaTecnologiaValor(objetoModificado.getMaTecnologiaValor());
                        contratoHistoricoValidacion.setValor(objetoModificado.getValorContratado());
                        contratoHistoricoValidacion.setFechaInicio(objetoModificado.getFechaHoraInicio());
                        contratoHistoricoValidacion.setFechaFin(objetoModificado.getFechaHoraFin());
                        //2024-02-22 jyperez nuevos campos
                        contratoHistoricoValidacion.setTipoManualTarifario(objetoModificado.getTipoManualTarifario());
                        contratoHistoricoValidacion.setMaManualTarifarioCodigo(objetoModificado.getMaManualTarifarioCodigo());
                        contratoHistoricoValidacion.setMaManualTarifarioAgno(objetoModificado.getMaManualTarifarioAgno());
                        contratoHistoricoValidacion.setValorManual(objetoModificado.getValorManual());
                        contratoHistoricoValidacion.setPorcentajeVariacion(objetoModificado.getPorcentajeVariacion());
                        contratoHistoricoValidacion.setFechaHoraCrea(objetoModificado.getFechaHoraCrea());
                    }
                        getCntContratoHistoricoValidacionRemoto().insertar(contratoHistoricoValidacion);
                }
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editarContratoDetalle(ContratosBean bean) {
        try {
            //bean.setObjeto(getContratoSedeRemoto().consultar(bean.getObjeto().getId()));
            bean.setObjetoContratoDetalle(getContratoDetalleRemoto().consultar(bean.getObjetoContratoDetalle().getId()));
            //validamos la habilitación del campo servicio
            if (bean.getObjetoContratoDetalle().getTipoTecnologia() == ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA) {
                //debemos llenar la lista de Servicios de Habilitación relacionada a la tacnologia
                bean.setListaServicios(getTecnologiaServicioHabilitacionRemoto().consultarPorTecnologia(bean.getObjetoContratoDetalle().getMaTecnologiaId()));
                bean.setPaqueteTecnologia(false);
                bean.setDeshabilitarServicio(false);
            }
            if (bean.getObjetoContratoDetalle().getTipoTecnologia() == ContratacionParametro.TIPO_TECNOLOGIA_PAQUETE) {
                MaPaquete paquete;
                paquete = getPaqueteRemoto().consultar(bean.getObjetoContratoDetalle().getMaTecnologiaId());
                //debemos llenar la lista de Servicios de Habilitación relacionada a la tacnologia
                if (paquete != null && paquete.getTipoTecnologia() == ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA) {
                    bean.setListaServicios(getTecnologiaServicioHabilitacionRemoto().consultarPorTecnologia(paquete.getMaTecnologia().getId()));
                    bean.setPaqueteTecnologia(true);
                    bean.setDeshabilitarServicio(false);
                }
            }
            //cargar lista Soat
            if (bean.getObjetoContratoDetalle().getTipoManualTarifario() == ContratacionParametro.TIPO_MANUAL_TARIFARIO_SOAT) {
                bean.setListaValoresSoat(getTarifarioValorRemoto().consultarPorSoatTarifario(bean.getObjetoContratoDetalle().getMaManualTarifarioId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarContratoDetalle(ContratosBean bean) {
        Maestro maestro;
        CntContratoHistorico contratoHistorico;
        CntContratoHistoricoValidacion contratoHistoricoValidacion;
        CntContratoDetalle objetoAnterior;
        CntContratoDetalle contratoDetalle;
        try {
            //validaciones
            //incluimos validación de fechas en esta parte, para corregir que se está mostrando el texto en inglés
            if (bean.getObjetoContratoDetalle().getFechaHoraFin().compareTo(bean.getObjetoContratoDetalle().getFechaHoraInicio()) < 0) {
                bean.addError("La fecha Inicio no puede ser mayor a la Fecha Fin.");
            }
            //2024-07-09 jyperez se elimina esta validación por solicitud del cliente
            //2024-05-03 jyperez validamos que la fecha de inicio no sea superior a la fecha actual
            /*if (!bean.getObjetoContratoDetalle().getFechaHoraInicio().before(bean.getFechaActual()) ){
                bean.addError("La fecha de inicio no puede ser mayor a la fecha actual.");
            }*/
            //la fecha de inicio no puede ser menor a la fecha actual
            if (bean.getObjetoContratoDetalle().getFechaHoraInicio().before(bean.getFechaActual()) ){
                bean.addError("La fecha de inicio no puede ser menor a la fecha actual.");
            }
            //2024-05-03 jyperez validamos que la fecha fin no sea menor a la fecha actual
            if (bean.getObjetoContratoDetalle().getFechaHoraFin().before(bean.getFechaActual()) ){
                bean.addError("La fecha fin no puede ser menor a la fecha actual.");
            }
            //validamos que en caso de tener tarifa Soat, se haya seleccionado un valor en la lista de Año Tarifa
            if (bean.getObjetoContratoDetalle().getTipoManualTarifario() == ContratacionParametro.TIPO_MANUAL_TARIFARIO_SOAT
                    && bean.getObjetoContratoDetalle().getMaManualTarifarioAgno() == null) {
                bean.addError("Año Soat: El valor es requerido si se selecciona Manual Tarifario SOAT.");
            }

            //validamos que se haya asignado una tecnología
            if (bean.getObjetoContratoDetalle().getMaTecnologiaId() == 0) {
                bean.addError("Debe seleccionarse una Tecnologia.");
            }

            if (bean.getObjetoContratoDetalle().getTipoTecnologia() == ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA
                    || bean.isPaqueteTecnologia()) {
                if (bean.getObjetoContratoDetalle().getMaServicioHabilitacionId() == null) {
                    bean.addError("Se debe seleccionar un Servicio de Habilitación cuando se configura una Tecnología o un Paquete de Tecnologías.");
                } else {
                    for (MaTecnologiaServicioHabilitacion serv : bean.getListaServicios()) {
                        if (serv.getMaServicioHabilitacion().getId().equals(bean.getObjetoContratoDetalle().getMaServicioHabilitacionId())) {
                            bean.getObjetoContratoDetalle().setMaServicioHabilitacionCodigo(String.valueOf(serv.getMaServicioHabilitacion().getCodigo()));
                            bean.getObjetoContratoDetalle().setMaServicioHabilitacionValor(serv.getMaServicioHabilitacion().getNombre());
                        }
                    }
                }
            }

            //obtenemos el valor restante de maestros
            if (bean.getObjetoContratoDetalle().getMaeAmbitoId() != null) {
                maestro = bean.getHashAmbito().get(bean.getObjetoContratoDetalle().getMaeAmbitoId());
                bean.getObjetoContratoDetalle().setMaeAmbitoCodigo(maestro.getValor());
                bean.getObjetoContratoDetalle().setMaeAmbitoValor(maestro.getNombre());
            }

            //incluimos validaciones MODIFICAR PARA EL UDPATE
            //2024-05-07 jyperez adicionamos validaciones de fechas con contrato sede
            bean.addError(validarFechainicioContratoSede(bean.getObjetoContratoDetalle()));
            bean.addError(validarFechaFinContratoSede(bean.getObjetoContratoDetalle()));
            //2021-08-31 jyperez Se adiciona permiso adicional, con el objetivo de ejecutar la validación de vigencia para
            // aquellos usuarios que no posean el servicio
            if (!bean.isAccionAdicional7()) {
                bean.addError(validarContratoExistenteYVigente(bean));
            }
            if (bean.getObjetoContratoDetalle().getTipoTecnologia() == ContratacionParametro.TIPO_TECNOLOGIA_TECNOLOGIA) {
                // acá se incluye el servicio en la validación.
                bean.addError(validarContratoDetalleTecnologiaExistenteModificar(bean.getObjetoContratoDetalle()));
            } else {
                bean.addError(validarContratoDetalleExistenteModificar(bean.getObjetoContratoDetalle()));
            }
            //2022-01-14 jyperez consultamos el estado del objeto Anterior, con todos sus datos.
            objetoAnterior = getContratoDetalleRemoto().consultar(bean.getObjetoContratoDetalle().getId());
            if (objetoAnterior == null) {
                bean.addError("Hubo un error obteniendo los datos del registro para el histórico del contrato.");
            }
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjetoContratoDetalle());
                //guardamos el registro
                getContratoDetalleRemoto().actualizar(bean.getObjetoContratoDetalle());
                //2021-12-15 se deshabilita nuevamente
                //2022-01-13 jyperez adición de almacenamiento de histórico afiliado. validamos si hay registros existentes.
                boolean historicoExistente = getCntContratoHistoricoRemoto().consultarHistoricoExistente(CntContratoHistorico.TIPO_DETALLE, bean.getObjetoContratoDetalle().getCntContrato().getId(), bean.getObjetoContratoDetalle().getCntContratoSede().getId(), bean.getObjetoContratoDetalle().getId());
                if (historicoExistente) {
                    contratoHistorico = new CntContratoHistorico();
                    contratoHistorico.setTipo(CntContratoHistorico.TIPO_DETALLE);
                    contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                    bean.auditoriaGuardar(contratoHistorico);
                    contratoHistorico.setCntContrato(bean.getObjetoContratoDetalle().getCntContrato());
                    contratoHistorico.setCntContratoSede(bean.getObjetoContratoDetalle().getCntContratoSede());
                    contratoHistorico.setCntContratoDetalle(bean.getObjetoContratoDetalle());
                    //2022-07-28 cmartins - Objeto DTOCntContratoDetalle para obtenner Json del contrato detalle modificado
                    contratoHistorico.setToString(new DTOCntContratoDetalle(bean.getObjetoContratoDetalle()).toStringJson());
                    
                    CntContratoHistorico ultimoHistorico = getCntContratoHistoricoRemoto().consultarUltimoHistoricoExistente(CntContratoHistorico.TIPO_DETALLE, bean.getObjetoContratoDetalle().getCntContrato().getId(), bean.getObjetoContratoDetalle().getCntContratoSede().getId(), bean.getObjetoContratoDetalle().getId());
                    getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                    
                    GsonBuilder gsonBuilderRespuesta = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha  
                    Gson gson = gsonBuilderRespuesta.create();
                    CntContratoDetalle objetoContratoDetalle = gson.fromJson(ultimoHistorico.getToString(), CntContratoDetalle.class);
                    if (!objetoContratoDetalle.getFechaHoraInicio().equals(bean.getObjetoContratoDetalle().getFechaHoraInicio())) {
                        JSONObject obj = new JSONObject(ultimoHistorico.getToString());
                        LocalDate fechaFinLocal = bean.getObjetoContratoDetalle().getFechaHoraInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate fechaFinal = fechaFinLocal.minusDays(1);
                        obj.put(CntContratoHistorico.FECHA_HORA_FIN, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( Date.from(fechaFinal.atStartOfDay(ZoneId.systemDefault()).toInstant())));                        
                        ultimoHistorico.setToString(obj.toString()); 
                        getCntContratoHistoricoRemoto().actualizar(ultimoHistorico);
                    }     
                    
                    //2022-08-05 cmartins - Almacenamiento de infromación en CntContratoHistoricoValidacion
                    contratoDetalle = getContratoDetalleRemoto().consultar(bean.getObjetoContratoDetalle().getId());
                    contratoHistoricoValidacion = new CntContratoHistoricoValidacion();
                    if (contratoDetalle != null){
                        contratoHistoricoValidacion.setCntPrestadorId(bean.getObjetoContratoDetalle().getCntContrato().getCntPrestador().getId());
                        contratoHistoricoValidacion.setCntPrestadorNumeroDocumento(bean.getObjetoContratoDetalle().getCntContrato().getCntPrestador().getNumeroDocumento());
                        contratoHistoricoValidacion.setCntPrestadorCodigoMinSalud(bean.getObjetoContratoDetalle().getCntContrato().getCntPrestador().getCodigoMinSalud());
                        contratoHistoricoValidacion.setCntPrestadorSedeId(bean.getObjetoContratoDetalle().getCntContratoSede().getCntPrestadorSede().getId());
                        contratoHistoricoValidacion.setCntPrestadorSedeCodigoHabilitacion(bean.getObjetoContratoDetalle().getCntContratoSede().getCntPrestadorSede().getCodigoHabilitacionSede());
                        contratoHistoricoValidacion.setCntContratoId(bean.getObjetoContratoDetalle().getCntContrato().getId());
                        contratoHistoricoValidacion.setCntContratoSedeId(bean.getObjetoContratoDetalle().getCntContratoSede().getId());
                        contratoHistoricoValidacion.setCntContratoDetalleId(bean.getObjetoContratoDetalle().getId());
                        contratoHistoricoValidacion.setTipoTecnologia(bean.getObjetoContratoDetalle().getTipoTecnologia());
                        contratoHistoricoValidacion.setMaTecnologiaId(bean.getObjetoContratoDetalle().getMaTecnologiaId());
                        contratoHistoricoValidacion.setMaTecnologiaCodigo(bean.getObjetoContratoDetalle().getMaTecnologiaCodigo());
                        contratoHistoricoValidacion.setMaTecnologiaValor(bean.getObjetoContratoDetalle().getMaTecnologiaValor());
                        contratoHistoricoValidacion.setValor(bean.getObjetoContratoDetalle().getValorContratado());
                        contratoHistoricoValidacion.setFechaInicio(bean.getObjetoContratoDetalle().getFechaHoraInicio());
                        contratoHistoricoValidacion.setFechaFin(bean.getObjetoContratoDetalle().getFechaHoraFin());
                        //2024-02-22 jyperez nuevos campos
                        contratoHistoricoValidacion.setTipoManualTarifario(bean.getObjetoContratoDetalle().getTipoManualTarifario());
                        contratoHistoricoValidacion.setMaManualTarifarioCodigo(bean.getObjetoContratoDetalle().getMaManualTarifarioCodigo());
                        contratoHistoricoValidacion.setMaManualTarifarioAgno(bean.getObjetoContratoDetalle().getMaManualTarifarioAgno());
                        contratoHistoricoValidacion.setValorManual(bean.getObjetoContratoDetalle().getValorManual());
                        contratoHistoricoValidacion.setPorcentajeVariacion(bean.getObjetoContratoDetalle().getPorcentajeVariacion());
                        contratoHistoricoValidacion.setFechaHoraCrea(bean.getObjetoContratoDetalle().getFechaHoraCrea());
                    }          
                    //Se consulta el ultimo registro de historico validacion
                    CntContratoHistoricoValidacion ultimoHisValidacion = getCntContratoHistoricoValidacionRemoto().consultarUltimoHistoricoValidacion(CntContratoHistorico.TIPO_DETALLE, bean.getObjetoContratoDetalle().getCntContrato().getId(), bean.getObjetoContratoDetalle().getCntContratoSede().getId(), bean.getObjetoContratoDetalle().getId());
                    //Se inserta el nuevo registro de historico validacion
                    getCntContratoHistoricoValidacionRemoto().insertar(contratoHistoricoValidacion); 
                    
                    if (!objetoContratoDetalle.getFechaHoraInicio().equals(bean.getObjetoContratoDetalle().getFechaHoraInicio())) {
                        LocalDate fechaFinLocal = bean.getObjetoContratoDetalle().getFechaHoraInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate fechaFinal = fechaFinLocal.minusDays(1);
                        ultimoHisValidacion.setFechaFin(Date.from(fechaFinal.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                        getCntContratoHistoricoValidacionRemoto().actualizar(ultimoHisValidacion);
                    } 
                    
                    
                } else {
                    if (objetoAnterior != null) {
                        contratoHistorico = new CntContratoHistorico();
                        contratoHistorico.setTipo(CntContratoHistorico.TIPO_DETALLE);
                        contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                        //bean.auditoriaGuardar(contratoHistorico);
                        if (objetoAnterior.getFechaHoraModifica() != null) {
                            contratoHistorico.setFechaHoraCrea(objetoAnterior.getFechaHoraModifica());
                            contratoHistorico.setUsuarioCrea(objetoAnterior.getUsuarioModifica());
                            contratoHistorico.setTerminalCrea(objetoAnterior.getTerminalModifica());
                        } else {
                            contratoHistorico.setFechaHoraCrea(objetoAnterior.getFechaHoraCrea());
                            contratoHistorico.setUsuarioCrea(objetoAnterior.getUsuarioCrea());
                            contratoHistorico.setTerminalCrea(objetoAnterior.getTerminalCrea());
                        }                        
                        contratoHistorico.setCntContrato(objetoAnterior.getCntContrato());
                        contratoHistorico.setCntContratoSede(objetoAnterior.getCntContratoSede());
                        contratoHistorico.setCntContratoDetalle(objetoAnterior);
                        //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContratoDetalle
                        contratoHistorico.setToString(new DTOCntContratoDetalle(objetoAnterior).toStringJson());
                        getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                        //2022-08-05 cmartins - Almacenamiento de infromación en CntContratoHistoricoValidacion
                        contratoDetalle = getContratoDetalleRemoto().consultar(bean.getObjetoContratoDetalle().getId());
                        contratoHistoricoValidacion = new CntContratoHistoricoValidacion();
                        if (contratoDetalle != null){
                            contratoHistoricoValidacion.setCntPrestadorId(objetoAnterior.getCntContrato().getCntPrestador().getId());
                            contratoHistoricoValidacion.setCntPrestadorNumeroDocumento(objetoAnterior.getCntContrato().getCntPrestador().getNumeroDocumento());
                            contratoHistoricoValidacion.setCntPrestadorCodigoMinSalud(objetoAnterior.getCntContrato().getCntPrestador().getCodigoMinSalud());
                            contratoHistoricoValidacion.setCntPrestadorSedeId(objetoAnterior.getCntContratoSede().getCntPrestadorSede().getId());
                            contratoHistoricoValidacion.setCntPrestadorSedeCodigoHabilitacion(objetoAnterior.getCntContratoSede().getCntPrestadorSede().getCodigoHabilitacionSede());
                            contratoHistoricoValidacion.setCntContratoId(objetoAnterior.getCntContrato().getId());
                            contratoHistoricoValidacion.setCntContratoSedeId(objetoAnterior.getCntContratoSede().getId());
                            contratoHistoricoValidacion.setCntContratoDetalleId(objetoAnterior.getId());
                            contratoHistoricoValidacion.setTipoTecnologia(objetoAnterior.getTipoTecnologia());
                            contratoHistoricoValidacion.setMaTecnologiaId(objetoAnterior.getMaTecnologiaId());
                            contratoHistoricoValidacion.setMaTecnologiaCodigo(objetoAnterior.getMaTecnologiaCodigo());
                            contratoHistoricoValidacion.setMaTecnologiaValor(objetoAnterior.getMaTecnologiaValor());
                            contratoHistoricoValidacion.setValor(objetoAnterior.getValorContratado());
                            contratoHistoricoValidacion.setFechaInicio(objetoAnterior.getFechaHoraInicio());
                            contratoHistoricoValidacion.setFechaFin(objetoAnterior.getFechaHoraFin());
                            //2024-02-22 jyperez nuevos campos
                            contratoHistoricoValidacion.setTipoManualTarifario(objetoAnterior.getTipoManualTarifario());
                            contratoHistoricoValidacion.setMaManualTarifarioCodigo(objetoAnterior.getMaManualTarifarioCodigo());
                            contratoHistoricoValidacion.setMaManualTarifarioAgno(objetoAnterior.getMaManualTarifarioAgno());
                            contratoHistoricoValidacion.setValorManual(objetoAnterior.getValorManual());
                            contratoHistoricoValidacion.setPorcentajeVariacion(objetoAnterior.getPorcentajeVariacion());
                            contratoHistoricoValidacion.setFechaHoraCrea(objetoAnterior.getFechaHoraCrea());
                        }
                        getCntContratoHistoricoValidacionRemoto().insertar(contratoHistoricoValidacion);
                    }
                    //almacenamos el registro actual
                    contratoHistorico = new CntContratoHistorico();
                    contratoHistorico.setTipo(CntContratoHistorico.TIPO_DETALLE);
                    contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                    bean.auditoriaGuardar(contratoHistorico);
                    contratoHistorico.setCntContrato(bean.getObjetoContratoDetalle().getCntContrato());
                    contratoHistorico.setCntContratoSede(bean.getObjetoContratoDetalle().getCntContratoSede());
                    contratoHistorico.setCntContratoDetalle(bean.getObjetoContratoDetalle());
                    //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContratoDetalle
                    contratoHistorico.setToString(new DTOCntContratoDetalle(bean.getObjetoContratoDetalle()).toStringJson());
                    getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                    
                    //2022-08-05 cmartins - Almacenamiento de infromación en CntContratoHistoricoValidacion
                    contratoDetalle = getContratoDetalleRemoto().consultar(bean.getObjetoContratoDetalle().getId());
                    contratoHistoricoValidacion = new CntContratoHistoricoValidacion();
                    if (contratoDetalle != null){
                        contratoHistoricoValidacion.setCntPrestadorId(bean.getObjetoContratoDetalle().getCntContrato().getCntPrestador().getId());
                        contratoHistoricoValidacion.setCntPrestadorNumeroDocumento(bean.getObjetoContratoDetalle().getCntContrato().getCntPrestador().getNumeroDocumento());
                        contratoHistoricoValidacion.setCntPrestadorCodigoMinSalud(bean.getObjetoContratoDetalle().getCntContrato().getCntPrestador().getCodigoMinSalud());
                        contratoHistoricoValidacion.setCntPrestadorSedeId(bean.getObjetoContratoDetalle().getCntContratoSede().getCntPrestadorSede().getId());
                        contratoHistoricoValidacion.setCntPrestadorSedeCodigoHabilitacion(bean.getObjetoContratoDetalle().getCntContratoSede().getCntPrestadorSede().getCodigoHabilitacionSede());
                        contratoHistoricoValidacion.setCntContratoId(bean.getObjetoContratoDetalle().getCntContrato().getId());
                        contratoHistoricoValidacion.setCntContratoSedeId(bean.getObjetoContratoDetalle().getCntContratoSede().getId());
                        contratoHistoricoValidacion.setCntContratoDetalleId(bean.getObjetoContratoDetalle().getId());
                        contratoHistoricoValidacion.setTipoTecnologia(bean.getObjetoContratoDetalle().getTipoTecnologia());
                        contratoHistoricoValidacion.setMaTecnologiaId(bean.getObjetoContratoDetalle().getMaTecnologiaId());
                        contratoHistoricoValidacion.setMaTecnologiaCodigo(bean.getObjetoContratoDetalle().getMaTecnologiaCodigo());
                        contratoHistoricoValidacion.setMaTecnologiaValor(bean.getObjetoContratoDetalle().getMaTecnologiaValor());
                        contratoHistoricoValidacion.setValor(bean.getObjetoContratoDetalle().getValorContratado());
                        contratoHistoricoValidacion.setFechaInicio(bean.getObjetoContratoDetalle().getFechaHoraInicio());
                        contratoHistoricoValidacion.setFechaFin(bean.getObjetoContratoDetalle().getFechaHoraFin());
                        //2024-02-22 jyperez nuevos campos
                        contratoHistoricoValidacion.setTipoManualTarifario(bean.getObjetoContratoDetalle().getTipoManualTarifario());
                        contratoHistoricoValidacion.setMaManualTarifarioCodigo(bean.getObjetoContratoDetalle().getMaManualTarifarioCodigo());
                        contratoHistoricoValidacion.setMaManualTarifarioAgno(bean.getObjetoContratoDetalle().getMaManualTarifarioAgno());
                        contratoHistoricoValidacion.setValorManual(bean.getObjetoContratoDetalle().getValorManual());
                        contratoHistoricoValidacion.setPorcentajeVariacion(bean.getObjetoContratoDetalle().getPorcentajeVariacion());
                        contratoHistoricoValidacion.setFechaHoraCrea(bean.getObjetoContratoDetalle().getFechaHoraCrea());
                    }
                        getCntContratoHistoricoValidacionRemoto().insertar(contratoHistoricoValidacion);
                }
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarContratoDetalle(ContratosBean bean) {
        try {
            bean.setObjetoContratoDetalle(getContratoDetalleRemoto().eliminar(bean.getObjetoContratoDetalle().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public List<MaTecnologiaServicioHabilitacion> consultarServiciosTecnologia(int idTecnologia) {
        List<MaTecnologiaServicioHabilitacion> result;
        try {
            result = getTecnologiaServicioHabilitacionRemoto().consultarPorTecnologia(idTecnologia);
        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<MaSoatTarifarioValor> consultarValoresSoat(int idSoatTarifario) {
        List<MaSoatTarifarioValor> result;
        try {
            result = getTarifarioValorRemoto().consultarPorSoatTarifario(idSoatTarifario);
        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

    private void listarNotaTecnica(ContratosBean bean) {
        // obtenemos la lista de contrato nota tecnica
        try {
            bean.getParamConsultaContratoNotaTecnica().setCantidadRegistros(getContratoNotaTecnicaRemoto().consultarCantidadLista(bean.getParamConsultaContratoNotaTecnica()));
            bean.setListaContratoNotaTecnica(getContratoNotaTecnicaRemoto().consultarLista(bean.getParamConsultaContratoNotaTecnica()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verNotaTecnica(ContratosBean bean) {
        try {
            bean.setObjetoContratoNotaTecnica(getContratoNotaTecnicaRemoto().consultar(bean.getObjetoContratoNotaTecnica().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearNotaTecnica(ContratosBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarNotaTecnica(ContratosBean bean) {
        Maestro maestro;
        try {
            //validaciones
            //incluimos validación de fechas en esta parte, para corregir que se está mostrando el texto en inglés
            if (bean.getObjetoContratoNotaTecnica().getFechaFin().compareTo(bean.getObjetoContratoNotaTecnica().getFechaInicio()) < 0) {
                bean.addError("La fecha Inicio no puede ser mayor a la Fecha Fin.");
            }

            //obtenemos el valor restante de maestros
            if (bean.getObjetoContratoNotaTecnica().getMaeAmbitoId() != 0) {
                maestro = bean.getHashAmbito().get(bean.getObjetoContratoNotaTecnica().getMaeAmbitoId());
                bean.getObjetoContratoNotaTecnica().setMaeAmbitoCodigo(maestro.getValor());
                bean.getObjetoContratoNotaTecnica().setMaeAmbitoValor(maestro.getNombre());
            }

            //validamos que se haya asignado una tecnología
            if (bean.getObjetoContratoNotaTecnica().getMaTecnologiaId() == 0) {
                bean.addError("Debe seleccionarse una Tecnologia.");
            } else {
                //incluimos validaciones
                bean.addError(validarRegistroExistente(bean.getObjetoContratoNotaTecnica(), true));
                bean.addError(validarTecnologiaEnContrato(bean.getObjetoContratoNotaTecnica()));
            }

            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjetoContratoNotaTecnica());
                //guardamos el registro
                bean.getObjetoContratoNotaTecnica().setId(getContratoNotaTecnicaRemoto().insertar(bean.getObjetoContratoNotaTecnica()));
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editarNotaTecnica(ContratosBean bean) {
        try {
            bean.setObjetoContratoNotaTecnica(getContratoNotaTecnicaRemoto().consultar(bean.getObjetoContratoNotaTecnica().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarNotaTecnica(ContratosBean bean) {
        Maestro maestro;
        try {
            //validaciones
            //incluimos validación de fechas en esta parte, para corregir que se está mostrando el texto en inglés
            if (bean.getObjetoContratoNotaTecnica().getFechaFin().compareTo(bean.getObjetoContratoNotaTecnica().getFechaInicio()) < 0) {
                bean.addError("La fecha Inicio no puede ser mayor a la Fecha Fin.");
            }

            //obtenemos el valor restante de maestros
            if (bean.getObjetoContratoNotaTecnica().getMaeAmbitoId() != 0) {
                maestro = bean.getHashAmbito().get(bean.getObjetoContratoNotaTecnica().getMaeAmbitoId());
                bean.getObjetoContratoNotaTecnica().setMaeAmbitoCodigo(maestro.getValor());
                bean.getObjetoContratoNotaTecnica().setMaeAmbitoValor(maestro.getNombre());
            }

            //incluimos validaciones
            bean.addError(validarRegistroExistente(bean.getObjetoContratoNotaTecnica(), false));
            bean.addError(validarTecnologiaEnContrato(bean.getObjetoContratoNotaTecnica()));

            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjetoContratoNotaTecnica());
                //guardamos el registro
                getContratoNotaTecnicaRemoto().actualizar(bean.getObjetoContratoNotaTecnica());
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarNotaTecnica(ContratosBean bean) {
        try {
            getContratoNotaTecnicaRemoto().eliminar(bean.getObjetoContratoNotaTecnica().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void imprimirNotaTecnica(ContratosBean bean) {
        try {
            bean.setListaImpresionNotaTecnica(getContratoNotaTecnicaRemoto().consultarPorContrato(bean.getObjetoContrato().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private String validarFechainicioContrato(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContrato obj;
        try {
            //obj = getContratoRemoto().consultarPorContrato(objeto.getCntContrato().getContrato());
            obj = objeto.getCntContrato();
            if (obj != null) {
                if (!(objeto.getFechaHoraInicio().compareTo(obj.getFechaInicio()) >= 0
                        && objeto.getFechaHoraInicio().compareTo(obj.getFechaFin()) <= 0)) {
                    mensaje = "La Fecha Hora Inicio no se encuentra entre las fechas inicial y final del contrato";
                }
            }
            //la validación inicial sabrá si existe el contrato ingresado o no
        } catch (Exception ex) {
            mensaje = "No se pudo validar la Fecha Hora Inicio";
        }
        return mensaje;
    }

    private String validarFechaFinContrato(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContrato obj;
        try {
            //obj = getContratoRemoto().consultarPorContrato(objeto.getCntContrato().getContrato());
            obj = objeto.getCntContrato();
            if (obj != null) {
                if (!(objeto.getFechaHoraFin().compareTo(obj.getFechaInicio()) >= 0
                        && objeto.getFechaHoraFin().compareTo(obj.getFechaFin()) <= 0)) {
                    mensaje = "La Fecha Hora Fin no se encuentra entre las fechas inicial y final del contrato";
                }
            }
            //la validación inicial sabrá si existe el contrato ingresado o no
        } catch (Exception ex) {
            mensaje = "No se pudo validar la Fecha Hora Fin";
        }
        return mensaje;
    }
    
    private String validarFechainicioContratoSede(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContratoSede obj;
        try {
            //obj = getContratoRemoto().consultarPorContrato(objeto.getCntContrato().getContrato());
            obj = objeto.getCntContratoSede();
            if (obj != null) {
                if (!(objeto.getFechaHoraInicio().compareTo(obj.getFechaInicio()) >= 0
                        && objeto.getFechaHoraInicio().compareTo(obj.getFechaFin()) <= 0)) {
                    mensaje = "La Fecha Hora Inicio no se encuentra entre las fechas inicial y final del contrato sede";
                }
            }
            //la validación inicial sabrá si existe el contrato ingresado o no
        } catch (Exception ex) {
            mensaje = "No se pudo validar la Fecha Hora Inicio";
        }
        return mensaje;
    }

    private String validarFechaFinContratoSede(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContratoSede obj;
        try {
            //obj = getContratoRemoto().consultarPorContrato(objeto.getCntContrato().getContrato());
            obj = objeto.getCntContratoSede();
            if (obj != null) {
                if (!(objeto.getFechaHoraFin().compareTo(obj.getFechaInicio()) >= 0
                        && objeto.getFechaHoraFin().compareTo(obj.getFechaFin()) <= 0)) {
                    mensaje = "La Fecha Hora Fin no se encuentra entre las fechas inicial y final del contrato sede";
                }
            }
            //la validación inicial sabrá si existe el contrato ingresado o no
        } catch (Exception ex) {
            mensaje = "No se pudo validar la Fecha Hora Fin";
        }
        return mensaje;
    }

    /**
     * función para determinar si las fecha inicio es menor a la fecha fin del
     * detalle.
     *
     * @param objeto
     * @return
     */
    private String validarFechas(CntContratoDetalle objeto) {
        String mensaje = "";
        if (objeto.getFechaHoraInicio().after(objeto.getFechaHoraFin())) {
            mensaje = "La Fecha Hora Inicio debe ser menor a la Fecha Hora Fin";
        }
        return mensaje;
    }

    private String validarContratoDetalleTecnologiaExistente(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContratoDetalle detalle;
        try {
            //2021-07-26 jyperez incluimos los campos TipoManualTarifario y el CódigoManualTarifario y el ambito
            // Tener en cuenta cuando es propia (no hay id manual tarifario) y soat (incluir en la validación el año)
            detalle = getContratoDetalleRemoto().consultar(objeto.getCntContrato().getId(),
                    objeto.getCntContratoSede().getId(), objeto.getMaTecnologiaId(), objeto.getMaServicioHabilitacionId(), objeto.getTipoTecnologia(),
                    objeto.getTipoManualTarifario(), objeto.getMaManualTarifarioId(), objeto.getMaManualTarifarioAgno(), objeto.getMaeAmbitoId());
            if (detalle != null) {
                mensaje = "Existe un contrato detalle para el mismo contrato, sede, tecnologia y servicio ingresado";
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error al validar Contrato Detalle Existente. Error: " + ex.getMessage();
        }
        return mensaje;
    }

    private String validarContratoDetalleTecnologiaExistenteModificar(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContratoDetalle detalle;
        try {
            detalle = getContratoDetalleRemoto().consultar(objeto.getCntContrato().getId(),
                    objeto.getCntContratoSede().getId(), objeto.getMaTecnologiaId(), objeto.getMaServicioHabilitacionId(), objeto.getTipoTecnologia(),
                    objeto.getTipoManualTarifario(), objeto.getMaManualTarifarioId(), objeto.getMaManualTarifarioAgno(), objeto.getMaeAmbitoId());
            if (detalle != null && !detalle.getId().equals(objeto.getId())) {
                mensaje = "Existe un contrato detalle para el mismo contrato, sede, tecnologia y servicio ingresado";
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error al validar Contrato Detalle Existente. Error: " + ex.getMessage();
        }
        return mensaje;
    }

    private String validarContratoDetalleExistente(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContratoDetalle detalle;
        try {
            detalle = getContratoDetalleRemoto().consultar(objeto.getCntContrato().getId(),
                    objeto.getCntContratoSede().getId(), objeto.getMaTecnologiaId(), objeto.getTipoTecnologia(),
                    objeto.getTipoManualTarifario(), objeto.getMaManualTarifarioId(), objeto.getMaManualTarifarioAgno(), objeto.getMaeAmbitoId());
            if (detalle != null) {
                mensaje = "Existe un contrato detalle para el mismo contrato, sede y tecnologia ingresado";
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error al validar Contrato Detalle Existente. Error: " + ex.getMessage();
        }
        return mensaje;
    }

    private String validarContratoDetalleExistenteModificar(CntContratoDetalle objeto) {
        String mensaje = "";
        CntContratoDetalle detalle;
        try {
            detalle = getContratoDetalleRemoto().consultar(objeto.getCntContrato().getId(),
                    objeto.getCntContratoSede().getId(), objeto.getMaTecnologiaId(), objeto.getTipoTecnologia(),
                    objeto.getTipoManualTarifario(), objeto.getMaManualTarifarioId(), objeto.getMaManualTarifarioAgno(), objeto.getMaeAmbitoId());
            if (detalle != null && !detalle.getId().equals(objeto.getId())) {
                mensaje = "Existe un contrato detalle para el mismo contrato, sede y tecnologia ingresado";
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error al validar Contrato Detalle Existente. Error: " + ex.getMessage();
        }
        return mensaje;
    }

    private String validarContratoExistenteYVigente(ContratosBean bean) {
        String mensaje = "";
        Maestro maestro;
        try {
            maestro = bean.getHashEstadoContrato().get(bean.getObjeto().getCntContrato().getMaeEstadoContratoCodigo());
            if (maestro == null) {
                mensaje = "No es posible validar la vigencia del contrato";
            } else if (maestro.getValor().equals(ContratacionParametro.ESTADO_CONTRATO_NO_VIGENTE)) {
                mensaje = "El contrato ingresado no esta vigente";
            }
        } catch (Exception ex) {
            mensaje = "El contrato ingresado no pudo ser consultado";
        }
        return mensaje;
    }

    private String validarRegistroExistente(CntContratoNotaTecnica objeto, boolean accionCrear) {
        String mensaje = "";
        CntContratoNotaTecnica nota;
        try {
            nota = getContratoNotaTecnicaRemoto().consultar(objeto.getCntContrato().getId(), objeto.getMaTecnologiaId(), objeto.getFrecuenciaUso(),
                    objeto.getTipoFrecuencia(), objeto.getCantidadAfiliados(), objeto.getCostoPromedio(), objeto.getFechaInicio(), objeto.getFechaFin(), objeto.getMaeAmbitoId());
            if (accionCrear) {
                if (nota != null) {
                    mensaje = "Existe una nota técnica registrada con los valores ingresados.";
                }
            } else {
                if (nota != null && !nota.getId().equals(objeto.getId())) {
                    mensaje = "Existe una nota técnica registrada con los valores ingresados.";
                }
            }
        } catch (Exception ex) {
            mensaje = "Los datos de la nota tecnica ingresados no pudieron ser consultados. Error: " + ex.getMessage();
        }
        return mensaje;
    }

    private String validarTecnologiaEnContrato(CntContratoNotaTecnica objeto) {
        String mensaje = "";
        try {
            int cantidadTecnologias = getContratoNotaTecnicaRemoto().contarCantidadTecnologiasEnContrato(objeto.getCntContrato().getId(), objeto.getMaTecnologiaId());
            if (cantidadTecnologias == 0) {
                mensaje = "La tecnología ingresada no se encuentra asignada a este Contrato.";
            }
        } catch (Exception ex) {
            mensaje = "La tecnología asociada a la nota tecnica ingresada no pudo ser consultada. Error: " + ex.getMessage();
        }
        return mensaje;
    }

    private void imprimirDetalleContrato(ContratosBean bean) {
        try {
            bean.setListaDetalleTecnologias(getContratoDetalleRemoto().consultarPorContratoSede(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verHistoricos(ContratosBean bean) {
        try {
            bean.getParamConsultaHistorico().setCantidadRegistros(getContratoHistoricoRemoto().consultarCantidadLista(bean.getParamConsultaHistorico()));
            bean.setRegistrosHistorico(getContratoHistoricoRemoto().consultarLista(bean.getParamConsultaHistorico()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void autorizarGestionContrato(ContratosBean bean) {
        try {
            CntContratoHistorico contratoHistorico;
            CntContrato objetoAnterior;
            CntContrato objetoModificado;
            
            //2022-01-13 jyperez consultamos el estado del objeto Anterior, solo con la información de Sedes
            objetoAnterior = getContratoRemoto().consultar(bean.getObjetoContrato().getId());
            
            if (objetoAnterior == null) {
                bean.addError("Hubo un error obteniendo los datos del registro antes de modificación para el histórico del contrato.");
            } else {
                //copiamos el objeto anterior en el objeto del bean que vamos a usar para modificarlo - lo clonamos para evitar referencias
                bean.setObjetoContrato( (CntContrato)objetoAnterior.clone());
                //actualizamos valor que indica la gestión
                bean.getObjetoContrato().setAutorizaGestion(true);
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                
                bean.auditoriaModificar(bean.getObjetoContrato());
                //guardamos el registro
                getContratoRemoto().actualizar(bean.getObjetoContrato());
                
                
                //2022-01-13 jyperez consultamos el objeto contrato luego de modificación
                objetoModificado = getContratoRemoto().consultar(bean.getObjetoContrato().getId());
                if (objetoModificado == null) {
                    throw new Exception("Hubo un error obteniendo los datos del registro modificado para el histórico del contrato.");
                }
                //2022-01-13 jyperez adición de almacenamiento de histórico afiliado. Inicialmente validamos que exista un histórico
                Boolean existehistorico = getCntContratoHistoricoRemoto().consultarHistoricoExistente(CntContratoHistorico.TIPO_CONTRATO, bean.getObjetoContrato().getId(), 0, 0);
                //2022-07-28 cmartins creacion de objeto DTOCntContrato para enviar JSON del contrato modificado
                if (existehistorico) {
                    contratoHistorico = new CntContratoHistorico();
                    contratoHistorico.setTipo(CntContratoHistorico.TIPO_CONTRATO);
                    contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                    contratoHistorico.setCntContrato(objetoModificado);
                    bean.auditoriaGuardar(contratoHistorico);
                    //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContrato
                    contratoHistorico.setToString(new DTOCntContrato(objetoModificado).toStringJson());
                    getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                } else {
                    // se ignresa esta validación teniendo en cuenta que el objeto se está consultando.
                    if (objetoAnterior != null) {
                        //2022-07-28 cmartins creacion de objeto DTOCntContrato para enviar JSON del contrato anterior
                        contratoHistorico = new CntContratoHistorico();
                        contratoHistorico.setTipo(CntContratoHistorico.TIPO_CONTRATO);
                        contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                        contratoHistorico.setCntContrato(objetoAnterior);
                        //bean.auditoriaGuardar(contratoHistorico);
                        //las fechas de auditoria serán las de creación o de modicicación segun exista en el registro.
                        if (objetoAnterior.getFechaHoraModifica() != null) {
                            contratoHistorico.setFechaHoraCrea(objetoAnterior.getFechaHoraModifica());
                            contratoHistorico.setUsuarioCrea(objetoAnterior.getUsuarioModifica());
                            contratoHistorico.setTerminalCrea(objetoAnterior.getTerminalModifica());
                        } else {
                            contratoHistorico.setFechaHoraCrea(objetoAnterior.getFechaHoraCrea());
                            contratoHistorico.setUsuarioCrea(objetoAnterior.getUsuarioCrea());
                            contratoHistorico.setTerminalCrea(objetoAnterior.getTerminalCrea());
                        }
                        //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContrato
                        contratoHistorico.setToString(new DTOCntContrato(objetoAnterior).toStringJson());
                        getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                    }
                    //ingresamos el registro actual
                    contratoHistorico = new CntContratoHistorico();
                    contratoHistorico.setTipo(CntContratoHistorico.TIPO_CONTRATO);
                    contratoHistorico.setOrigen(CntContratoHistorico.ORIGEN_MANUAL);
                    contratoHistorico.setCntContrato(objetoModificado);
                    bean.auditoriaGuardar(contratoHistorico);
                    //2022-07-28 cmartins - Se almacena el toStringJson proveniente de DTOCntContrato
                    contratoHistorico.setToString(new DTOCntContrato(objetoModificado).toStringJson());
                    getCntContratoHistoricoRemoto().insertar(contratoHistorico);
                }
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    /**
     * Método para validar si la fecha 1 se encuentra despues de la fecha 2
     *
     * @param fecha1
     * @param fecha2
     * @return
     */
    private boolean fechaDespues(Date fecha1, Date fecha2) {
        boolean estado = false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fecha1);
        cal2.setTime(fecha2);
        int anio1 = cal1.get(Calendar.YEAR);
        int mes1 = cal1.get(Calendar.MONTH);
        int dia1 = cal1.get(Calendar.DAY_OF_MONTH);
        int anio2 = cal2.get(Calendar.YEAR);
        int mes2 = cal2.get(Calendar.MONTH);
        int dia2 = cal2.get(Calendar.DAY_OF_MONTH);

        if (anio1 > anio2) {
            estado = true;
        } else if (anio1 == anio2) {
            if (mes1 > mes2) {
                estado = true;
            } else if (mes1 == mes2) {
                if (dia1 > dia2) {
                    estado = true;
                }
            }
        }
        return estado;
    }

}
