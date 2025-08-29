/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1Adjunto;
import com.saviasaludeps.savia.dominio.aseguramiento.ReporteAnexo1;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoContactoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.web.aseguramiento.bean.Anexo1Bean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.aseguramiento.Anexo1AdjuntoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.Anexo1Remoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Jose Perez
 */
public class Anexo1ServicioImpl extends AccionesBO implements Anexo1ServicioIface {
    
    private Anexo1Remoto getAnexo1Remoto() throws Exception {
        return (Anexo1Remoto) RemotoEJB.getEJBRemoto(("Anexo1Servicio"), Anexo1Remoto.class.getName());
    }
    
    private Anexo1AdjuntoRemoto getAnexo1AdjuntoRemoto() throws Exception {
        return (Anexo1AdjuntoRemoto) RemotoEJB.getEJBRemoto(("Anexo1AdjuntoServicio"), Anexo1AdjuntoRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }
    
    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }
    
    private CntPrestadorSedeRemoto getPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }
    
    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }
    
    private AfiliadoContactoRemoto getAfiliadoContactoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoContactoServicio", AfiliadoContactoRemoto.class.getName());
        return (AfiliadoContactoRemoto) object;
    }

    @Override
    public void Accion(Anexo1Bean bean) {
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
                    generarReporteAnexo1(bean);
                    break;
                
            }
            cargas(bean);
        }
    }

    private void listar(Anexo1Bean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getAnexo1Remoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAnexo1Remoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }


    private void ver(Anexo1Bean bean) {
        try {
            bean.setObjeto(getAnexo1Remoto().consultar(bean.getObjeto().getId()));
            bean.setListaAsegAnexo1Adjuntos(bean.getObjeto().getListaAnexo1Adjuntos());
            //cargamos la información en el registro relacionada a la Sede y al Prestador
            if (bean.getObjeto().getCntPrestadoresId() != null && bean.getObjeto().getCntPrestadoresId() != 0) {
                bean.getObjeto().setCntPrestador(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadoresId()));
                bean.getObjeto().setCntPrestadorSede(getPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedesId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(Anexo1Bean bean) {
        try {
            bean.setObjeto(new AsegAnexo1());
            bean.setObjetoAfiliado(new AsegAfiliado());
            bean.setListaAsegAnexo1Adjuntos(new ArrayList());
            bean.setObjetoAdjunto(new AsegAnexo1Adjunto());
            //2024-03-26 jyperez obtenemos el prestador del usuario logueado. En caso de que sea savia. no se cargará
            if (bean.isSedeRequerida()) {
                bean.getObjeto().setCntPrestador(bean.getConexion().getEmpresa().getCntPrestador());
                bean.getObjeto().setCntPrestadoresId(bean.getObjeto().getCntPrestador().getId());
                consultarSedesPrestador(bean);
            }
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(Anexo1Bean bean) {
        try {
            int id = 0;
            String tipoDocumentoAfiliado = "";
            boolean noCambios = false;
            //validaciones
            //validamos que se haya ingresado un afiliado para la gestión del anexo.
            if (bean.getObjetoAfiliado().getId() == null){
                throw new Exception ("Se debe seleccionar un afiliado para la creación del Anexo1.");
            }
            //2023-03-26 jyperez copiamos los valores de los objetos en las variables correspondientes
            if (bean.getObjeto().getCntPrestador() != null) {
                bean.getObjeto().setCntPrestadoresId(bean.getObjeto().getCntPrestador().getId());
                bean.getObjeto().setCntPrestadorSedesId(bean.getObjeto().getCntPrestadorSede().getId());
            }
            // validamos que al menos un campo haya cambiado con respecto al objeto Afiliado
            //obtenemos el valor del tipo de documento, para compararlo con el del objeto Anexo1
            Maestro maestro = bean.getHashTiposDocumentoPersona().get(bean.getObjetoAfiliado().getMaeTipoDocumento());
            if (maestro != null) {
                tipoDocumentoAfiliado = maestro.getValor();
            }
            if (bean.getObjeto().getTipoDocumentoNuevo().equals(tipoDocumentoAfiliado) &&
                    bean.getObjeto().getNumeroDocumentoNuevo().equals(bean.getObjetoAfiliado().getNumeroDocumento()) &&
                    bean.getObjeto().getNombre1Nuevo().equals(bean.getObjetoAfiliado().getPrimerNombre()) &&
                    //bean.getObjeto().getNombre2Nuevo().equals(bean.getObjetoAfiliado().getSegundoNombre()) &&
                    bean.getObjeto().getApellido1Nuevo().equals(bean.getObjetoAfiliado().getPrimerApellido()) &&
                    //bean.getObjeto().getApellido2Nuevo().equals(bean.getObjetoAfiliado().getSegundoApellido()) &&
                    bean.getObjeto().getFechaNacimientoNuevo().equals(bean.getObjetoAfiliado().getFechaNacimiento()) &&
                    bean.getObjeto().getSexoNuevo().equals(bean.getObjetoAfiliado().getGenero()) &&
                    //bean.getObjeto().getFechaExpedicionCedulaNuevo().equals(bean.getObjetoAfiliado().getFechaExpedicionCedula()) &&
                    bean.getObjeto().getDireccionNuevo().equals(bean.getObjetoAfiliado().getDireccionResidencia()) 
                    //bean.getObjeto().getTelefonoNuevo().equals(bean.getObjetoAfiliado().getTelefonoFijo()) &&
                    //bean.getObjeto().getCelularNuevo().equals(bean.getObjetoAfiliado().getTelefonoMovil())
                    ) {
                        noCambios = true;
                        if(bean.getObjetoAfiliado().getSegundoNombre() != null &&
                                bean.getObjetoAfiliado().getSegundoNombre().equals(bean.getObjeto().getNombre2Nuevo())) {
                            noCambios = true;
                        }else if (bean.getObjetoAfiliado().getSegundoNombre() != null &&
                                !bean.getObjetoAfiliado().getSegundoNombre().equals(bean.getObjeto().getNombre2Nuevo())) {
                            noCambios = false;
                        }
                        if (noCambios) {
                            if(bean.getObjetoAfiliado().getSegundoApellido() != null &&
                                    bean.getObjetoAfiliado().getSegundoApellido().equals(bean.getObjeto().getApellido2Nuevo())) {
                                noCambios = true;
                            }else if(bean.getObjetoAfiliado().getSegundoApellido() != null &&
                                    !bean.getObjetoAfiliado().getSegundoApellido().equals(bean.getObjeto().getApellido2Nuevo())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if (bean.getObjetoAfiliado().getFechaExpedicionCedula() != null &&
                                bean.getObjetoAfiliado().getFechaExpedicionCedula().equals(bean.getObjeto().getFechaExpedicionCedulaNuevo())) {
                                noCambios = true;
                                //bean.addError("Se debe realizar algún cambio en la información para poder registrar el Anexo1.");
                            }else if (bean.getObjetoAfiliado().getFechaExpedicionCedula() != null &&
                                    !bean.getObjetoAfiliado().getFechaExpedicionCedula().equals(bean.getObjeto().getFechaExpedicionCedulaNuevo())){
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjetoAfiliado().getTelefonoFijo() != null &&
                                bean.getObjetoAfiliado().getTelefonoFijo().equals(bean.getObjeto().getTelefonoNuevo())) {
                            noCambios = true;
                            }else if (bean.getObjetoAfiliado().getTelefonoFijo() != null &&
                                !bean.getObjetoAfiliado().getTelefonoFijo().equals(bean.getObjeto().getTelefonoNuevo())) {
                            noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjetoAfiliado().getTelefonoMovil() != null &&
                                    bean.getObjetoAfiliado().getTelefonoMovil().equals(bean.getObjeto().getCelularNuevo())){
                                noCambios = true;
                            }else if (bean.getObjetoAfiliado().getTelefonoMovil() != null &&
                                    !bean.getObjetoAfiliado().getTelefonoMovil().equals(bean.getObjeto().getCelularNuevo())) {
                                noCambios = false;
                            }
                        }
                        //2023-03-26 jyperez campos nuevos RES 2335
                        if (noCambios) {
                            if(bean.getObjetoAfiliado().getResidenciaUbicacion()!= null && bean.getObjeto().getResidenciaUbicacionNuevo() != null &&
                                    bean.getObjetoAfiliado().getResidenciaUbicacion().getId().equals(bean.getObjeto().getResidenciaUbicacionNuevo().getId())){
                                noCambios = true;
                            }else if (bean.getObjetoAfiliado().getResidenciaUbicacion() != null && bean.getObjeto().getResidenciaUbicacionNuevo() != null &&
                                    !bean.getObjetoAfiliado().getResidenciaUbicacion().getId().equals(bean.getObjeto().getResidenciaUbicacionNuevo().getId())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjetoAfiliado().getEmail()!= null &&
                                    bean.getObjetoAfiliado().getEmail().equals(bean.getObjeto().getEmailNuevo())){
                                noCambios = true;
                            }else if (bean.getObjetoAfiliado().getEmail() != null &&
                                    !bean.getObjetoAfiliado().getEmail().equals(bean.getObjeto().getEmailNuevo())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjetoAfiliado().getDireccionAlternaContacto()!= null &&
                                    bean.getObjetoAfiliado().getDireccionAlternaContacto().equals(bean.getObjeto().getDireccionAlternaContactoNuevo())){
                                noCambios = true;
                            }else if (bean.getObjetoAfiliado().getDireccionAlternaContacto() != null &&
                                    !bean.getObjetoAfiliado().getDireccionAlternaContacto().equals(bean.getObjeto().getDireccionAlternaContactoNuevo())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjetoAfiliado().getNombreContactoEmergencia()!= null &&
                                    bean.getObjetoAfiliado().getNombreContactoEmergencia().equals(bean.getObjeto().getNombreContactoEmergenciaNuevo())){
                                noCambios = true;
                            }else if (bean.getObjetoAfiliado().getNombreContactoEmergencia() != null &&
                                    !bean.getObjetoAfiliado().getNombreContactoEmergencia().equals(bean.getObjeto().getNombreContactoEmergenciaNuevo())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjetoAfiliado().getTelefonoContactoEmergencia()!= null &&
                                    bean.getObjetoAfiliado().getTelefonoContactoEmergencia().equals(bean.getObjeto().getTelefonoContactoEmergenciaNuevo())){
                                noCambios = true;
                            }else if (bean.getObjetoAfiliado().getTelefonoContactoEmergencia() != null &&
                                    !bean.getObjetoAfiliado().getTelefonoContactoEmergencia().equals(bean.getObjeto().getTelefonoContactoEmergenciaNuevo())) {
                                noCambios = false;
                            }
                        }
                        //si no hubo cambios, es decir pasó por todas las validaciones entonces se lanza el mensaje
                        if(noCambios){
                            bean.addError("Se debe realizar algún cambio en la información para poder registrar el Anexo1.");
                        }
            }
            //validamos que se haya ingresado un soporte si se modifica alguno de los campos nombres, apellidos, tipo de documento y numero de documento y sexo
            if (bean.getListaAsegAnexo1Adjuntos().isEmpty() &&
                    (!bean.getObjeto().getTipoDocumentoNuevo().equals(tipoDocumentoAfiliado) ||
                    !bean.getObjeto().getNumeroDocumentoNuevo().equals(bean.getObjetoAfiliado().getNumeroDocumento()) ||
                    !bean.getObjeto().getNombre1Nuevo().equals(bean.getObjetoAfiliado().getPrimerNombre()) ||
                    (!bean.getObjeto().getNombre2Nuevo().trim().equals("") && !bean.getObjeto().getNombre2Nuevo().equals(bean.getObjetoAfiliado().getSegundoNombre())) ||
                    !bean.getObjeto().getApellido1Nuevo().equals(bean.getObjetoAfiliado().getPrimerApellido()) ||
                    (!bean.getObjeto().getApellido2Nuevo().trim().equals("") && !bean.getObjeto().getApellido2Nuevo().equals(bean.getObjetoAfiliado().getSegundoApellido())) ||
                    !bean.getObjeto().getSexoNuevo().equals(bean.getObjetoAfiliado().getGenero()) ||
                    !bean.getObjeto().getFechaNacimientoNuevo().equals(bean.getObjetoAfiliado().getFechaNacimiento())
                    )) {
                bean.addError("Se debe adjuntar un soporte si alguna de las inconsistencias reportada corresponde a Nombres, Apellidos, Tipo Documento, Número Documento, Fecha de Nacimiento o Sexo.");
            }
            
            if (!bean.isError()) {
                
                //actualizamos los estados de aquellas variables modificadas
                if (!bean.getObjeto().getTipoDocumentoNuevo().equals(tipoDocumentoAfiliado)) {
                    bean.getObjeto().setTipoDocumentoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getNumeroDocumentoNuevo().equals(bean.getObjetoAfiliado().getNumeroDocumento())) {
                    bean.getObjeto().setNumeroDocumentoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getNombre1Nuevo().equals(bean.getObjetoAfiliado().getPrimerNombre())) {
                    bean.getObjeto().setNombre1Inconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getNombre2Nuevo().trim().equals("") && !bean.getObjeto().getNombre2Nuevo().equals(bean.getObjetoAfiliado().getSegundoNombre())) {
                    bean.getObjeto().setNombre2Inconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getApellido1Nuevo().equals(bean.getObjetoAfiliado().getPrimerApellido())) {
                    bean.getObjeto().setApellido1Inconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getApellido2Nuevo().trim().equals("") && !bean.getObjeto().getApellido2Nuevo().equals(bean.getObjetoAfiliado().getSegundoApellido())) {
                    bean.getObjeto().setApellido2Inconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getFechaNacimientoNuevo().equals(bean.getObjetoAfiliado().getFechaNacimiento())) {
                    bean.getObjeto().setFechaNacimientoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getSexoNuevo().equals(bean.getObjetoAfiliado().getGenero())) {
                    bean.getObjeto().setSexoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (bean.getObjeto().getFechaExpedicionCedulaNuevo() != null && !bean.getObjeto().getFechaExpedicionCedulaNuevo().equals(bean.getObjetoAfiliado().getFechaExpedicionCedula())) {
                    bean.getObjeto().setFechaExpedicionCedulaInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getDireccionNuevo().equals(bean.getObjetoAfiliado().getDireccionResidencia())) {
                    bean.getObjeto().setDireccionInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getTelefonoNuevo().trim().equals("") && !bean.getObjeto().getTelefonoNuevo().equals(bean.getObjetoAfiliado().getTelefonoFijo())) {
                    bean.getObjeto().setTelefonoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getCelularNuevo().trim().equals("") && !bean.getObjeto().getCelularNuevo().equals(bean.getObjetoAfiliado().getTelefonoMovil())) {
                    bean.getObjeto().setCelularInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                //2023-03-26 jyperez campos nuevos RES 2335
                if (bean.getObjeto().getResidenciaUbicacionNuevo() != null && !bean.getObjeto().getResidenciaUbicacionNuevo().getId().equals(bean.getObjetoAfiliado().getResidenciaUbicacion().getId())) {
                    bean.getObjeto().setResidenciaUbicacionInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getEmailNuevo().trim().equals("") && !bean.getObjeto().getEmailNuevo().equals(bean.getObjetoAfiliado().getEmail())) {
                    bean.getObjeto().setEmailInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getDireccionAlternaContactoNuevo().trim().equals("") && !bean.getObjeto().getDireccionAlternaContactoNuevo().equals(bean.getObjetoAfiliado().getDireccionAlternaContacto())) {
                    bean.getObjeto().setDireccionAlternaContactoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getNombreContactoEmergenciaNuevo().trim().equals("") && !bean.getObjeto().getNombreContactoEmergenciaNuevo().equals(bean.getObjetoAfiliado().getNombreContactoEmergencia())) {
                    bean.getObjeto().setNombreContactoEmergenciaInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjeto().getTelefonoContactoEmergenciaNuevo().trim().equals("") && !bean.getObjeto().getTelefonoContactoEmergenciaNuevo().equals(bean.getObjetoAfiliado().getTelefonoContactoEmergencia())) {
                    bean.getObjeto().setTelefonoContactoEmergenciaInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                // actualizamos el estado del objeto Anexo1
                //2024-04-05 jyperez ponemos la versión en true para indicar que los los cambios a partir de la RES 2335
                //2024-04-19 jyperez se actualiza a false debido a que los cambios de la res 2335 deben inhabilitarse
                //2025-02-04 jyperez se habilita la versión pero se incluyen validaciones para su impresión.
                bean.getObjeto().setVersion(true);
                bean.getObjeto().setEstado(AfiliadoParametro.ESTADO_PENDIENTE);
                bean.getObjeto().setAsegAfiliadosId(bean.getObjetoAfiliado());
                bean.getObjeto().setGnEmpresa(bean.getConexion().getEmpresa());
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el anexo1
                id = getAnexo1Remoto().insertar(bean.getObjeto());
                bean.getObjeto().setId(id);
                //una vez almacenado, procedemos a guardar los items de adjunto
                if (!bean.getListaAsegAnexo1Adjuntos().isEmpty()) {
                    guardarAdjunto(bean);
                }
                //mensaje de exito
                bean.addMensaje("Se genera el Anexo1 con exito, con número de radicado " + bean.getObjeto().getId());
                
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarAdjunto(Anexo1Bean bean) {
        try {
            boolean error = false;
            String ruta = PropApl.getInstance().get(PropApl.RUTA_ASEGURAMIENTO_ANEXO1_SOPORTES);
            if (ruta == null || ruta.isEmpty()) {
                bean.addError("No esta configurada la ruta para los adjuntos.");
                error = true;
            }
            if (bean.getListaAsegAnexo1Adjuntos().isEmpty()) {
                bean.addError("No hay adjuntos para guardar.");
                error = true;
            }
            if (error) {
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            String tipoDocumento = bean.getHashTiposDocumentoPersona().get(bean.getObjeto().getAsegAfiliadosId().getMaeTipoDocumento()).getValor();

            for (AsegAnexo1Adjunto asegAnexo1Adjunto : bean.getListaAsegAnexo1Adjuntos()) {
                nombreArchivo = new StringBuilder();
                nombreArchivo.append(tipoDocumento)
                        .append(bean.getObjeto().getAsegAfiliadosId().getNumeroDocumento())
                        .append("_")
                        .append(sdf.format(new Date()));
                nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
                bean.auditoriaGuardar(asegAnexo1Adjunto);
                asegAnexo1Adjunto.setArchivo(nombreArchivo.append(".").append(asegAnexo1Adjunto.getExtensión()).toString());
                asegAnexo1Adjunto.setAsegAnexo1Id(new AsegAnexo1(bean.getObjeto().getId()));
                asegAnexo1Adjunto.setRuta(ruta);
                File archivo = new File(ruta, asegAnexo1Adjunto.getArchivo());
                OutputStream destino = new FileOutputStream(archivo);
                IOUtils.copy(asegAnexo1Adjunto.getAdjuntoStream(), destino);
                IOUtils.closeQuietly(asegAnexo1Adjunto.getAdjuntoStream());
                IOUtils.closeQuietly(destino);
                bean.auditoriaGuardar(asegAnexo1Adjunto);
                asegAnexo1Adjunto.setAdjuntoStream(null);
                asegAnexo1Adjunto.setId(getAnexo1AdjuntoRemoto().insertar(asegAnexo1Adjunto));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(Anexo1Bean bean) {
        try {
            bean.setObjeto(getAnexo1Remoto().consultar(bean.getObjeto().getId()));
            bean.setListaAsegAnexo1Adjuntos(bean.getObjeto().getListaAnexo1Adjuntos());
            //actualizamos las variables que están habilitadas y deshabilitadas
            bean.getObjeto().habilitarCamposInconsistencia();
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(Anexo1Bean bean) {
        boolean rechazado = true;
        List<AsegAfiliadoContacto> listaContactos = new ArrayList();
        try {
            //validaciones
            //validamos que no se encuentre ningun campo en estados que no sean finales (Aceptado/Rechazado)
            if (!bean.getObjeto().validarGestion()) {
                bean.addError("No es posible realizar la Gestión si no se han diligenciado los campos disponibles.");
            }else {
                //si la gestión es válida, ejecutamos esta validación
                if (bean.getObjeto().getTipoDocumentoInconsistencia()== AfiliadoParametro.ESTADO_INCONSISTENCIA_ACEPTADO ) {
                    bean.addError(validarAfiliadoExistenteTipoDocumentoYNumeroDocumento(bean));
                }
                //2021-07-08 jyperez INC 858 realizamos validaciones de duplicidad de registros con los cambios propuestos para el usuario
                if (bean.getObjeto().getNumeroDocumentoInconsistencia() == AfiliadoParametro.ESTADO_INCONSISTENCIA_ACEPTADO ) {
                    bean.addError(validarAfiliadoExistenteNumeroDocumento(bean));
                }
                if (bean.getObjeto().getNombre1Inconsistencia() == AfiliadoParametro.ESTADO_INCONSISTENCIA_ACEPTADO ||
                    bean.getObjeto().getNombre2Inconsistencia() == AfiliadoParametro.ESTADO_INCONSISTENCIA_ACEPTADO ||
                    bean.getObjeto().getApellido1Inconsistencia() == AfiliadoParametro.ESTADO_INCONSISTENCIA_ACEPTADO ||
                    bean.getObjeto().getApellido2Inconsistencia() == AfiliadoParametro.ESTADO_INCONSISTENCIA_ACEPTADO ||
                    bean.getObjeto().getFechaNacimientoInconsistencia() == AfiliadoParametro.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.addError(validarAfiliadoExistenteDatosBasicos(bean));
                }
            }
            
            if(!bean.isError()) {
                //procesamiento
                //obtenemos el afiliado a modificar los campos habilitados
                bean.setObjetoAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
                if (bean.getObjetoAfiliado() == null) {
                    throw new Exception("No se encontró el afiliado a actualizar.");
                }
                // calculamos si el estado a actualizar en el anexo es Gestionado. De lo contrario Se actualizará rechazado.
                // en este proceso realizamos, validación del cambio, actualización en el afiliado a modificar y actualización del estado del anexo
                if (bean.getObjeto().getTipoDocumentoInconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    Maestro mae = bean.getHashTipoDocumento().get(bean.getObjeto().getTipoDocumentoNuevo());
                    if (mae != null) {
                        bean.getObjetoAfiliado().setMaeTipoDocumento(mae.getId());
                        bean.getObjetoAfiliado().setMaeTipoDocumentoCodigo(mae.getValor());
                        bean.getObjetoAfiliado().setMaeTipoDocumentoValor(mae.getNombre());
                    }else {
                        throw new Exception("El Tipo de Documento a actualizar no es válido.");
                    }
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getNumeroDocumentoInconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setNumeroDocumento(bean.getObjeto().getNumeroDocumentoNuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getApellido1Inconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setPrimerApellido(bean.getObjeto().getApellido1Nuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getApellido2Inconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setSegundoApellido(bean.getObjeto().getApellido2Nuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getNombre1Inconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setPrimerNombre(bean.getObjeto().getNombre1Nuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getNombre2Inconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setSegundoNombre(bean.getObjeto().getNombre2Nuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getSexoInconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    try{
                        Integer valorGenero = Integer.valueOf(bean.getObjeto().getSexoNuevo());
                        Maestro mae = bean.getHashTiposGenero().get(valorGenero);
                        bean.getObjetoAfiliado().setMaeGeneroId(mae.getId());
                        bean.getObjetoAfiliado().setMaeGeneroCodigo(mae.getValor());
                        bean.getObjetoAfiliado().setMaeGeneroValor(mae.getNombre());
                        bean.getObjetoAfiliado().setGenero(mae.getValor());
                    } catch (Exception ex) {
                        throw new Exception("El Sexo a actualizar no es válido.");
                    }
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getFechaNacimientoInconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setFechaNacimiento(bean.getObjeto().getFechaNacimientoNuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getDireccionInconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setDireccionResidencia(bean.getObjeto().getDireccionNuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getFechaExpedicionCedulaInconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setFechaExpedicionCedula(bean.getObjeto().getFechaExpedicionCedulaNuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getTelefonoInconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    //bean.getObjetoAfiliado().setTelefonoFijo(bean.getObjeto().getTelefonoNuevo());
                    //2022-11-25 jyperez corrección afiliado contacto. Buscamos que el contacto no exista ya
                    boolean crearTelefonoFijo = true;
                    for ( AsegAfiliadoContacto contacto : bean.getObjetoAfiliado().getListaAsegAfiliadoContacto()) {
                        if (contacto.getMaeTipoContactoCodigo().equals(AfiliadoParametro.TIPO_CONTACTO_TELEFONO) &&
                                contacto.getNumeroContacto().equals(bean.getObjeto().getTelefonoNuevo())) {
                            crearTelefonoFijo = false;
                        }
                    }
                    if(crearTelefonoFijo) {
                        AsegAfiliadoContacto contactoFijo = new AsegAfiliadoContacto();
                        contactoFijo.setActivo(true);
                        contactoFijo.setAsegAfiliado(bean.getObjetoAfiliado());
                        contactoFijo.setNumeroContacto(bean.getObjeto().getTelefonoNuevo());
                        Maestro maestro = bean.getHashValorTiposContacto().get(AfiliadoParametro.TIPO_CONTACTO_TELEFONO);
                        if (maestro != null) {
                            contactoFijo.setMaeTipoContactoId(maestro.getId());
                            contactoFijo.setMaeTipoContactoCodigo(maestro.getValor());
                            contactoFijo.setMaeTipoContactoValor(maestro.getNombre());
                        }
                        //la auditoria se agrega cuando lo vayamos a crear
                        listaContactos.add(contactoFijo);
                    }
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getCelularInconsistencia() == AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    //bean.getObjetoAfiliado().setTelefonoMovil(bean.getObjeto().getCelularNuevo());
                    //2022-11-25 jyperez corrección afiliado contacto. Buscamos que el contacto no exista ya
                    boolean crearTelefonoMovil = true;
                    for ( AsegAfiliadoContacto contacto : bean.getObjetoAfiliado().getListaAsegAfiliadoContacto()) {
                        if (contacto.getMaeTipoContactoCodigo().equals(AfiliadoParametro.TIPO_CONTACTO_CELULAR) &&
                                contacto.getNumeroContacto().equals(bean.getObjeto().getCelularNuevo())) {
                            crearTelefonoMovil = false;
                        }
                    }
                    if(crearTelefonoMovil) {
                        AsegAfiliadoContacto contactoMovil = new AsegAfiliadoContacto();
                        contactoMovil.setActivo(true);
                        contactoMovil.setAsegAfiliado(bean.getObjetoAfiliado());
                        contactoMovil.setNumeroContacto(bean.getObjeto().getCelularNuevo());
                        Maestro maestro = bean.getHashValorTiposContacto().get(AfiliadoParametro.TIPO_CONTACTO_CELULAR);
                        if (maestro != null) {
                            contactoMovil.setMaeTipoContactoId(maestro.getId());
                            contactoMovil.setMaeTipoContactoCodigo(maestro.getValor());
                            contactoMovil.setMaeTipoContactoValor(maestro.getNombre());
                        }
                        //la auditoria se agrega cuando lo vayamos a crear
                        listaContactos.add(contactoMovil);
                    }
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                //2024-03-26 jyperez campos nuevos RES 2335
                if (bean.getObjeto().getResidenciaUbicacionInconsistencia()== AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    //2024-04-03 jyperez Se define que para este cambio, cuando se acepté el asesor realizará dicha modificación en afiliados.
                    // Esto teniendo en cuenta las validaciones de Regimen y los cambios que implican para afiliados mover la residencia ( hay que mover la IPS de atención)
                    //bean.getObjetoAfiliado().setResidenciaUbicacion(bean.getObjeto().getResidenciaUbicacionNuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getEmailInconsistencia()== AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setEmail(bean.getObjeto().getEmailNuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getDireccionAlternaContactoInconsistencia()== AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setDireccionAlternaContacto(bean.getObjeto().getDireccionAlternaContactoNuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getNombreContactoEmergenciaInconsistencia()== AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setNombreContactoEmergencia(bean.getObjeto().getNombreContactoEmergenciaNuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                if (bean.getObjeto().getTelefonoContactoEmergenciaInconsistencia()== AsegAnexo1.ESTADO_INCONSISTENCIA_ACEPTADO) {
                    bean.getObjetoAfiliado().setTelefonoContactoEmergencia(bean.getObjeto().getTelefonoContactoEmergenciaNuevo());
                    //actualizamos a false el estado rechazado, con lo cúal indicamos que por lo menos uno de los cambios fué aceptado
                    rechazado = false;
                }
                //actualizamos el afiliado, validamos si el estado finalmente es gestionado. Si es rechazado no hay necesidad
                if(!rechazado){
                    bean.getObjeto().setEstado(AfiliadoParametro.ESTADO_GESTIONADO);
                    //2021-06-15 jyperez INC 817 adicionar comentarios a la observación del afiliado
                    String observacion = "";
                    observacion = bean.getObjeto().getObservacion() + " " + bean.getObjetoAfiliado().getObservacionNovedad();
                    bean.getObjetoAfiliado().setObservacionNovedad(observacion);
                    //2022-11-25 jyperez adición campos de Anexo1 habeas data
                    bean.getObjetoAfiliado().setTratamientoDatosAutoriza(bean.getObjeto().getTratamientoDatosAutoriza());
                    bean.getObjetoAfiliado().setTratamientoDatosFechaHora(bean.getObjeto().getTratamientoDatosFechaHora());
                    //2021-11-03 adición campo nuevo
                    bean.getObjetoAfiliado().setOrigenUltimoRegistro(AfiliadoParametro.ORIGEN_ULTIMO_REGISTRO_ANEXO_1);
                    bean.auditoriaModificar(bean.getObjetoAfiliado());
                    getAfiliadoRemoto().actualizar(bean.getObjetoAfiliado());
                    //2022-11-25 jyperez actualizamos la lista de contactos
                    if (!listaContactos.isEmpty()) {
                        for (AsegAfiliadoContacto contacto: listaContactos) {
                            contacto.setId(null);
                            bean.auditoriaGuardar(contacto);
                            contacto.setId(getAfiliadoContactoRemoto().insertar(contacto));
                        }
                    }
                }else {
                    bean.getObjeto().setEstado(AfiliadoParametro.ESTADO_RECHAZADO);
                }
                //actualizamos el anexo1
                bean.auditoriaModificar(bean.getObjeto());
                getAnexo1Remoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(Anexo1Bean bean) {
        try {
            
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(Anexo1Bean bean) {
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
    public void cargaInicial(Anexo1Bean bean) {
        try {
            //maestro Tipo Documento Persona
            bean.setListaTipoDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumento(Util.convertToHashValor(bean.getListaTipoDocumento()));
            bean.setListaTiposDocumentoPersona(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumentoPersona(Util.convertToHash(bean.getListaTiposDocumentoPersona()));
            //Maestro Sexo(Genero)
            bean.setListaTiposGenero(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashTiposGenero(Util.convertToHash(bean.getListaTiposGenero()));
            //Maestro Contactos
            bean.setHashValorTiposContacto(MaestroSingle.getInstance().getHashValorPorTipo(MaestroTipo.GN_TIPO_CONTACTO));
            //Ubicaciones
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashMunicipios());
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean generarArchivo(Anexo1Bean bean) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(bean.getObjeto().getCelularNuevo(), bean.getObjeto().getCelularNuevo());
            destino = new FileOutputStream(archivo);
            //IOUtils.copy(bean.getObjeto().getAdjuntoStream(), destino);
            //IOUtils.closeQuietly(bean.getObjeto().getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {
                Logger.getLogger(Anexo1ServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return esArchivoGuardado;
    }
    
    /**
     * Función que validará el archivo teniendo los siguientes aspectos
     * - registros separados por comas
     * - cantidad de campos en cada registro correctos
     * 
     * @param bean
     * @return Se retorna un mensaje vacio si la validación es correcta, de lo contrario se retorna un mensaje
     * indicando el problema.
     */
    private String validarArchivo(Anexo1Bean bean) {
        String mensaje = "";
        return mensaje;
    }
    
    private int contarCaracteres(String cadena, char caracter) {
        int posicion, contador = 0;
        //se busca la primera vez que aparece
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) { //mientras se encuentre el caracter
            contador++;           //se cuenta
            //se sigue buscando a partir de la posición siguiente a la encontrada                                 
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
    }

    private void generarReporteAnexo1(Anexo1Bean bean) {
        Maestro maestro;
        ReporteAnexo1 reporteAnexo1 = new ReporteAnexo1();
        List<ReporteAnexo1> listaReporte = new ArrayList();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfHoraCert = new SimpleDateFormat("yyyyMMddHHmmss");
        try{
            Ubicacion ubicacion = null;
            CntPrestador prestador = null;
            
            if (bean.getConexion().getEmpresa().getCiudad() != null) {
                ubicacion = getUbicacionRemoto().consultar(bean.getConexion().getEmpresa().getCiudad().getId());
            }
            //se valida la relación de la empresa con el prestador. Tener en cuenta que solo los empleados
            // de savia no tienen prestador asociado.
            if(bean.getConexion().getEmpresa().getCntPrestador() != null) {
                prestador = getPrestadorRemoto().consultar(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId());
            if (afiliado != null) {
                // tomar información del afiliado para el reporteAnexo1
                //tomar información del anexo1 para el reporteAnexo1
                //y procedemos a generar el reporteAnexo1 para impresión
                reporteAnexo1.setId(bean.getObjeto().getId());
                reporteAnexo1.setStrNumeroInforme(bean.getObjeto().getId());
                reporteAnexo1.setStrFecha(sdf.format(bean.getObjeto().getFechaHoraCrea()));
                reporteAnexo1.setStrHora(sdfHora.format(bean.getObjeto().getFechaHoraCrea()));
                if (prestador != null) {
                    reporteAnexo1.setStrNombrePrestador(prestador.getRazonSocial());
                    reporteAnexo1.setStrTipoDocumentoPrestador(prestador.getMaeTipoDocumentoValor());
                    reporteAnexo1.setStrDocumentoPrestador(prestador.getNumeroDocumento());
                    reporteAnexo1.setStrCodigoPrestador(prestador.getCodigoMinSalud());
                    reporteAnexo1.setStrDireccionPrestador("");//NO TIENE EL CAMPO EN LA BD
                    reporteAnexo1.setStrTelefonoPrestador("");// NO TIENE EL CAMPO EN LA BD
                    if (ubicacion != null) {
                        if (ubicacion.getUbicacionPadre() != null) {
                            reporteAnexo1.setStrDepartamentoPrestador(ubicacion.getUbicacionPadre().getNombre());
                        }else {reporteAnexo1.setStrDepartamentoPrestador("");}
                        reporteAnexo1.setStrMunicipioPrestador(ubicacion.getNombre());
                    }else {
                        reporteAnexo1.setStrDepartamentoPrestador("");
                        reporteAnexo1.setStrMunicipioPrestador("");
                    }
                } else {
                    reporteAnexo1.setStrDepartamentoPrestador("");
                    reporteAnexo1.setStrMunicipioPrestador("");
                }
                reporteAnexo1.setStrNombreEntidad(AfiliadoParametro.NOMBRE_EPS);
                reporteAnexo1.setStrCodigoEntidad(AfiliadoParametro.CODIGO_EPS);
                reporteAnexo1.setStrTipoInconsistencia(AfiliadoParametro.TIPO_INCONSISTENCIA);
                //datos del afiliado
                reporteAnexo1.setStrPrimerApellidoUsuario(afiliado.getPrimerApellido());
                reporteAnexo1.setStrSegundoApellidoUsuario(afiliado.getSegundoApellido());
                reporteAnexo1.setStrPrimerNombreUsuario(afiliado.getPrimerNombre());
                reporteAnexo1.setStrSegundoNombreUsuario(afiliado.getSegundoNombre());
                Maestro mae = bean.getHashTiposDocumentoPersona().get(afiliado.getMaeTipoDocumento());
                if (mae != null) {
                    reporteAnexo1.setStrTipoDocumentoUsuario(mae.getValor());
                }else {
                    reporteAnexo1.setStrTipoDocumentoUsuario("");
                }
                reporteAnexo1.setStrDocumentoUsuario(afiliado.getNumeroDocumento());
                //reporteAnexo1.setStrTipoDocumentoUsuario("");
                reporteAnexo1.setStrFechaNacimientoUsuario(sdf.format(afiliado.getFechaNacimiento()));
                reporteAnexo1.setStrDireccionUsuario(afiliado.getDireccionResidencia());
                reporteAnexo1.setStrTelefonoUsuario(afiliado.getTelefonoFijo());
                //ubicación de residencia del afiliado
                if (afiliado.getResidenciaUbicacion() != null) {
                    if (afiliado.getResidenciaUbicacion().getUbicacionPadre() != null) {
                        reporteAnexo1.setStrDepartamentoUsuario(afiliado.getResidenciaUbicacion().getUbicacionPadre().getNombre());
                    }else {reporteAnexo1.setStrDepartamentoUsuario("");}
                    reporteAnexo1.setStrMunicipioUsuario(afiliado.getResidenciaUbicacion().getNombre());
                }else {
                    reporteAnexo1.setStrDepartamentoUsuario("");
                    reporteAnexo1.setStrMunicipioUsuario("");
                }
                if (afiliado.getMaeRegimenValor() != null) {
                    reporteAnexo1.setStrTipoCobertura(afiliado.getMaeRegimenCodigo());
                }else {
                    reporteAnexo1.setStrTipoCobertura(afiliado.getRegimen());
                }
                //datos anexo1
                if (bean.getObjeto().getApellido1Inconsistencia() != 0) {
                    reporteAnexo1.setStrOpcionPrimerApellido(AfiliadoParametro.APLICA_INCONSISTENCIA);
                } else {
                    reporteAnexo1.setStrOpcionPrimerApellido(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                }
                if (bean.getObjeto().getApellido2Inconsistencia() != 0) {
                    reporteAnexo1.setStrOpcionSegundoApellido(AfiliadoParametro.APLICA_INCONSISTENCIA);
                } else {
                    reporteAnexo1.setStrOpcionSegundoApellido(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                }
                if (bean.getObjeto().getNombre1Inconsistencia() != 0) {
                    reporteAnexo1.setStrOpcionPrimerNombre(AfiliadoParametro.APLICA_INCONSISTENCIA);
                } else {
                    reporteAnexo1.setStrOpcionPrimerNombre(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                }
                if (bean.getObjeto().getNombre2Inconsistencia() != 0) {
                    reporteAnexo1.setStrOpcionSegundoNombre(AfiliadoParametro.APLICA_INCONSISTENCIA);
                } else {
                    reporteAnexo1.setStrOpcionSegundoNombre(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                }
                if (bean.getObjeto().getTipoDocumentoInconsistencia() != 0) {
                    reporteAnexo1.setStrOpcionTipoDocumento(AfiliadoParametro.APLICA_INCONSISTENCIA);
                } else {
                    reporteAnexo1.setStrOpcionTipoDocumento(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                }
                if (bean.getObjeto().getNumeroDocumentoInconsistencia() != 0) {
                    reporteAnexo1.setStrOpcionDocumento(AfiliadoParametro.APLICA_INCONSISTENCIA);
                } else {
                    reporteAnexo1.setStrOpcionDocumento(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                }
                if (bean.getObjeto().getFechaNacimientoInconsistencia() != 0) {
                    reporteAnexo1.setStrOpcionFechaNacimiento(AfiliadoParametro.APLICA_INCONSISTENCIA);
                } else {
                    reporteAnexo1.setStrOpcionFechaNacimiento(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                }
                reporteAnexo1.setStrPrimerApellidoFisico(bean.getObjeto().getApellido1Nuevo());
                reporteAnexo1.setStrSegundoApellidoFisico(bean.getObjeto().getApellido2Nuevo());
                reporteAnexo1.setStrPrimerNombreFisico(bean.getObjeto().getNombre1Nuevo());
                reporteAnexo1.setStrSegundoNombreFisico(bean.getObjeto().getNombre2Nuevo());
                reporteAnexo1.setStrTipoDocumentoFisico(bean.getTipoDocumento(bean.getObjeto().getTipoDocumentoNuevo()));
                reporteAnexo1.setStrDocumentoFisico(bean.getObjeto().getNumeroDocumentoNuevo());
                reporteAnexo1.setStrFechaNacimientoFisico(sdf.format(bean.getObjeto().getFechaNacimientoNuevo()));
                reporteAnexo1.setStrObservacion(bean.getObjeto().getObservacion());
                //datos de auditoria
                reporteAnexo1.setStrNombreReporta(bean.getObjeto().getUsuarioCrea());
                reporteAnexo1.setStrTelefonoReporta("");//de donde se obtienen
                reporteAnexo1.setStrCargoReporta("");//de donde se obtienen
                reporteAnexo1.setStrCelularReporta("");//de donde se obtienen
                //2022-11-28 jyperez nuevos campos reporte - ley 1581 de 2012
                if (bean.getObjeto().getTratamientoDatosAutoriza() != null && bean.getObjeto().getTratamientoDatosAutoriza()) {
                    reporteAnexo1.setStrAutorizaDatos("SI");
                    if (bean.getObjeto().getTratamientoDatosFechaHora() != null) {
                        reporteAnexo1.setStrFechaActualizaDatos(sdf.format(bean.getObjeto().getTratamientoDatosFechaHora()));
                    } else {
                        reporteAnexo1.setStrFechaActualizaDatos(sdf.format(bean.getObjeto().getFechaHoraCrea()));
                    }
                } else {
                    reporteAnexo1.setStrAutorizaDatos("NO");
                    reporteAnexo1.setStrFechaActualizaDatos(sdf.format(bean.getObjeto().getFechaHoraCrea()));
                }
                //2024-04-05 jyperez RES2335 nuevos campos
                //municipio residencia
                if (bean.getObjeto().getResidenciaUbicacionInconsistencia() != 0) {
                    reporteAnexo1.setStrOpcionMunicipioResidencia(AfiliadoParametro.APLICA_INCONSISTENCIA);
                    if (bean.getObjeto().getResidenciaUbicacionNuevo() != null) {
                        reporteAnexo1.setStrMunicipioResidencia(bean.getObjeto().getResidenciaUbicacionNuevo().getNombre());
                    } else {
                        reporteAnexo1.setStrMunicipioResidencia("");
                    }
                } else {
                    reporteAnexo1.setStrOpcionMunicipioResidencia(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                    //se le asigna el municipio del usuario actualmente
                    reporteAnexo1.setStrMunicipioResidencia(reporteAnexo1.getStrMunicipioUsuario());
                }
                //email
                if (bean.getObjeto().getEmailInconsistencia() != 0) {
                    reporteAnexo1.setStrOpcionEmail(AfiliadoParametro.APLICA_INCONSISTENCIA);
                    reporteAnexo1.setStrEmail(bean.getObjeto().getEmailNuevo());
                } else {
                    reporteAnexo1.setStrOpcionEmail(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                    //se le asigna el email que tiene el usuario actualmente
                    if (afiliado.getEmail() != null) {
                        reporteAnexo1.setStrEmail(afiliado.getEmail());
                    } else {
                        reporteAnexo1.setStrEmail("");
                    }
                }
                //Nombre Contacto Emergencia
                if (bean.getObjeto().getNombreContactoEmergenciaInconsistencia()!= 0) {
                    reporteAnexo1.setStrOpcionNombreContactoEmergencia(AfiliadoParametro.APLICA_INCONSISTENCIA);
                    reporteAnexo1.setStrNombreContactoEmergencia(bean.getObjeto().getNombreContactoEmergenciaNuevo());
                } else {
                    reporteAnexo1.setStrOpcionNombreContactoEmergencia(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                    reporteAnexo1.setStrNombreContactoEmergencia("");
                }
                //Telefono Contacto Emergencia
                if (bean.getObjeto().getTelefonoContactoEmergenciaInconsistencia()!= 0) {
                    reporteAnexo1.setStrOpcionTelefonoContactoEmergencia(AfiliadoParametro.APLICA_INCONSISTENCIA);
                    reporteAnexo1.setStrTelefonoContactoEmergencia(bean.getObjeto().getTelefonoContactoEmergenciaNuevo());
                } else {
                    reporteAnexo1.setStrOpcionTelefonoContactoEmergencia(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                    reporteAnexo1.setStrTelefonoContactoEmergencia("");
                }
                //Direccion Alterna Afiliado
                if (bean.getObjeto().getDireccionAlternaContactoInconsistencia()!= 0) {
                    reporteAnexo1.setStrOpcionDireccionAlternaAfiliado(AfiliadoParametro.APLICA_INCONSISTENCIA);
                    reporteAnexo1.setStrDireccionAlternaAfiliado(bean.getObjeto().getDireccionAlternaContactoNuevo());
                } else {
                    reporteAnexo1.setStrOpcionDireccionAlternaAfiliado(AfiliadoParametro.NO_APLICA_INCONSISTENCIA);
                    reporteAnexo1.setStrDireccionAlternaAfiliado("");
                }
                //2024-04-12 jyperez RES2335 nuevos campo consecutivo
                if (bean.getObjeto().getConsecutivo() != null) {
                    reporteAnexo1.setStrConsecutivo(bean.getObjeto().getConsecutivo());
                } else {
                     reporteAnexo1.setStrConsecutivo("");
                }
                listaReporte.add(reporteAnexo1);
                // Generar report para guardarlo en la ruta.
                //Estructura de JasperReport
                InputStream is = null;
                if (!bean.getObjeto().isVersion()) {
                    is = getClass().getResourceAsStream("/reportes/Anexo1.jasper");
                } else {
                    //2024-04-19 jyperez se comenta el nuevo pdf por postergamiento de la res 2335
                    //is = getClass().getResourceAsStream("/reportes/Anexo1_v2.jasper");
                    //2025-01-31 jyperez consultamos el prestador sede
                    CntPrestadorSede sede= getPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedesId());
                    if (sede != null) {
                        //2025-01-31 jyperez adicionamos validación de prestador sede en campo grupo_rips_ministerio sea 1 para imprimir la nueva versión
                        if(sede.getGrupoRipsMinisterio()!= null &&
                                sede.getGrupoRipsMinisterio().equals(1)) {
                            is = getClass().getResourceAsStream("/reportes/Anexo1_v2.jasper");
                        } else if ( sede.getFechaFacturaElectronica() != null) {
                            is = getClass().getResourceAsStream("/reportes/Anexo1_v2.jasper");
                        } else {
                            is = getClass().getResourceAsStream("/reportes/Anexo1.jasper");
                        }
                    } else {
                        is = getClass().getResourceAsStream("/reportes/Anexo1.jasper");
                    }
                }
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReporte);
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream streamArchivo = new ByteArrayInputStream(bytes);
                bean.setAdjuntoStream(streamArchivo);
            }else {
                bean.addError("No se encontró información del Afiliado asociado al Anexo1.");
            }
            
        }catch (Exception e) {
            bean.addError("Ocurrió un error en el proceso de Generación del Reporte Anexo1: " + e.getMessage());
        }
    }
    
    private String validarAfiliadoExistenteNumeroDocumento(Anexo1Bean bean) {
        String mensaje = "";
        List<AsegAfiliado> listaAfiliados;
        try{
            listaAfiliados = getAfiliadoRemoto().consultarPorNumeroDocumento(bean.getObjeto().getNumeroDocumentoNuevo());
            if(!listaAfiliados.isEmpty()){
                //validamos si sólo existe un registro y si ese registro es el del afiliado a actualizar. Si es asi descartamos.
                //sino, quiere decir que existe uno o mas registros con el mismo dato.
                if(listaAfiliados.size() == 1 && bean.getObjeto().getAsegAfiliadosId().getId().compareTo(listaAfiliados.get(0).getId()) == 0 ) {
                    mensaje = "";
                }else {
                    mensaje = "No se puede generar la actualización debido a que en afiliados ya existe uno o mas registros con el Número de Documento ingresado.";
                }
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente por Número de Documento. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }
    
    private String validarAfiliadoExistenteTipoDocumentoYNumeroDocumento(Anexo1Bean bean) {
        String mensaje = "";
        List<AsegAfiliado> listaAfiliados;
        try{
            if (bean.getObjeto().getTipoDocumentoNuevo() != null &&
                    bean.getObjeto().getTipoDocumentoNuevo().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_PROTECCION_TEMPORAL)) {
                listaAfiliados = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(bean.getObjeto().getTipoDocumentoNuevo(), bean.getObjeto().getNumeroDocumentoNuevo());
                if(!listaAfiliados.isEmpty()){
                    //validamos si sólo existe un registro y si ese registro es el del afiliado a actualizar. Si es asi descartamos.
                    //sino, quiere decir que existe uno o mas registros con el mismo dato.
                    if(listaAfiliados.size() == 1 && bean.getObjeto().getAsegAfiliadosId().getId().compareTo(listaAfiliados.get(0).getId()) == 0 ) {
                        mensaje = "";
                    }else {
                        mensaje = "No se puede generar la actualización debido a que en afiliados ya existe uno o mas registros con el Tipo de Documento y Número de Documento ingresado.";
                    }
                }
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente por Tipo Documento y Número de Documento. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }

    private String validarAfiliadoExistenteDatosBasicos(Anexo1Bean bean) {
        String mensaje = "";
        AsegAfiliado afiliadoConsulta;
        String segundoApellido = "";
        String segundoNombre = "";
        
        if (bean.getObjeto().getApellido2Nuevo() != null) {
            segundoApellido = bean.getObjeto().getApellido2Nuevo();
        }
        if (bean.getObjeto().getNombre2Nuevo() != null) {
            segundoNombre = bean.getObjeto().getNombre2Nuevo();
        }
        try {
            afiliadoConsulta = getAfiliadoRemoto().consultar(bean.getObjeto().getApellido1Nuevo(),segundoApellido , bean.getObjeto().getNombre1Nuevo(), segundoNombre, bean.getObjeto().getFechaNacimientoNuevo());
            if (afiliadoConsulta != null
                    && afiliadoConsulta.getId() != null
                    && afiliadoConsulta.getId().compareTo(bean.getObjeto().getAsegAfiliadosId().getId()) != 0) {
                mensaje = "No se puede generar la actualización debido a que en afiliados ya existe un registro con los respectivos datos.";
            } else {
                mensaje = "";
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente por Datos Básicos. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }

    @Override
    public void consultarSedesPrestador(Anexo1Bean bean) {
        try{
            //cargamos las sedes en la lista de sedes
            bean.setListaCntPrestadorSedes(getPrestadorSedeRemoto().consultarPorPrestador(bean.getObjeto().getCntPrestadoresId()));
        } catch (Exception ex) {
             bean.addError(ex.getMessage());
            Logger.getLogger(Anexo1ServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
