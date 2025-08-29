/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1Adjunto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.web.externo.bean.AfiliadoActualizacionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.dominio.externo.AfiliadoActualizacion;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.Anexo1AdjuntoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.Anexo1Remoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Fabian Coronel
 */
public class AfiliadoActualizacionServicioImpl extends AccionesBO implements AfiliadoActualizacionServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
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

    @Override
    public void Accion(AfiliadoActualizacionBean bean) {
        switch (bean.getAccion()) {
            case AfiliadoActualizacionBean.ACCION_BUSCAR_AFILLIADO_SERVICE:
                consultarAfiliado(bean);
                break;
            case AfiliadoActualizacionBean.ACCION_GUARDAR_AFILIADO_SERVICE:
                guardarAfiliado(bean);
                break;
            default:
                break;
        }
    }

    private void consultarAfiliado(AfiliadoActualizacionBean bean) {
        try {
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(
                      bean.getObjeto().getMaeTipoDocumento(), 
                      bean.getObjeto().getNumeroDocumento());
            if (afiliado != null && bean.getObjeto()
                    .getFechaNacimiento().equals(afiliado.getFechaNacimiento())) { 
                bean.setObjeto(afiliado);
                // se setea valores del anexo1 a modificar
                bean.setObjetoAnexo1(new AsegAnexo1());
                Maestro maestro = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeTipoDocumento());
                bean.getObjetoAnexo1().setTipoDocumentoNuevo(maestro.getValor());
                bean.getObjetoAnexo1().setNumeroDocumentoNuevo(afiliado.getNumeroDocumento());
                bean.getObjetoAnexo1().setNombre1Nuevo(afiliado.getPrimerNombre());
                bean.getObjetoAnexo1().setNombre2Nuevo(afiliado.getSegundoNombre());
                bean.getObjetoAnexo1().setApellido1Nuevo(afiliado.getPrimerApellido());
                bean.getObjetoAnexo1().setApellido2Nuevo(afiliado.getSegundoApellido());
                bean.getObjetoAnexo1().setFechaNacimientoNuevo(afiliado.getFechaNacimiento());
                //2024-03-04 jyperez adicionamos este campo que no se modifica en algunos casos
                //2024-04-10 jyperez revisando sincronización de cambios
                bean.getObjetoAnexo1().setFechaExpedicionCedulaNuevo(afiliado.getFechaExpedicionCedula());
                bean.getObjetoAnexo1().setSexoNuevo(afiliado.getGenero());
                bean.setHabilitarSeccionResultados(true);
                //2024-04-03 jyperez adicionamos búsqueda de prestador
                bean.setSedeRequerida(false);
                if (bean.getNumeroDocumentoPrestador() != null && !bean.getNumeroDocumentoPrestador().equals("")) {
                    //2024-04-05 jyperez se ajusta la búsqueda por código minSalud que es único
                    bean.getObjetoAnexo1().setCntPrestador(getPrestadorRemoto().consultarPorCodigoMinSalud(bean.getNumeroDocumentoPrestador()));
                    if (bean.getObjetoAnexo1().getCntPrestador() != null) {
                        bean.setSedeRequerida(true);
                        consultarSedesPrestador(bean);
                    }
                }
            } else {
                bean.addError("Afiliado no encontrado");
            }
        } catch (Exception e) {
            bean.addError("Error consultar afiliado : " + e.getMessage());
        }
    }

    private boolean validarExistenciaUnTelefono(AfiliadoActualizacionBean bean) {
        boolean esValido = true;

        if ((bean.getObjetoAnexo1().getTelefonoNuevo() == null || bean.getObjetoAnexo1().getTelefonoNuevo().isEmpty())) {
            esValido = false;
        }
        if ( bean.getObjetoAnexo1().getCelularNuevo()== null || bean.getObjetoAnexo1().getCelularNuevo().isEmpty()) {
            esValido = false;
        }
        return esValido;
    }

    private boolean validarExpedicionSiTipoDocCedula(AfiliadoActualizacionBean bean) {
        boolean esValido = true;
        if (bean.getObjeto().getMaeTipoDocumento() == 401) {
            return bean.getObjetoAnexo1().getFechaExpedicionCedulaNuevo() != null;
        }
        return esValido;
    }
    
    private void guardarAfiliado(AfiliadoActualizacionBean bean) {
        try {
            int id = 0;
            if(bean.getObjetoAnexo1().getDireccionNuevo() == null){
                bean.getObjetoAnexo1().setDireccionNuevo(bean.getObjeto().getDireccionResidencia());
            }
            if(bean.getObjetoAnexo1().getFechaExpedicionCedulaNuevo() == null){
                bean.getObjetoAnexo1().setFechaExpedicionCedulaNuevo(bean.getObjeto().getFechaExpedicionCedula());
            }
            if(bean.getObjetoAnexo1().getTelefonoNuevo().equals("")){
                bean.getObjetoAnexo1().setTelefonoNuevo(bean.getObjeto().getTelefonoFijo());
            }
            if(bean.getObjetoAnexo1().getCelularNuevo().equals("")){
                bean.getObjetoAnexo1().setCelularNuevo(bean.getObjeto().getTelefonoMovil());
            }
            
            //pasar a mayusculas
            String nombre1 =bean.getObjetoAnexo1().getNombre1Nuevo().toUpperCase();
            String nombre2 =bean.getObjetoAnexo1().getNombre2Nuevo().toUpperCase();
            String apellido1 =bean.getObjetoAnexo1().getApellido1Nuevo().toUpperCase();
            String apellido2 =bean.getObjetoAnexo1().getApellido2Nuevo().toUpperCase();
            String nombreCompleto = bean.getObjetoAnexo1().getNombreContactoEmergenciaNuevo().toUpperCase();
            bean.getObjetoAnexo1().setNombre1Nuevo(nombre1);
            bean.getObjetoAnexo1().setNombre2Nuevo(nombre2);
            bean.getObjetoAnexo1().setApellido1Nuevo(apellido1);
            bean.getObjetoAnexo1().setApellido2Nuevo(apellido2);
            bean.getObjetoAnexo1().setNombreContactoEmergenciaNuevo(nombreCompleto);
            
            
            String tipoDocumentoAfiliado = "";
            boolean noCambios = false;
            //validaciones
            //validamos que se haya ingresado un afiliado para la gestión del anexo.
            if (bean.getObjeto().getId() == null){
                throw new Exception ("Se debe seleccionar un afiliado para la creación del Anexo1.");
            }
            //2024-04-04 jyperez copiamos los valores de los objetos en las variables correspondientes
            if (bean.getObjetoAnexo1().getCntPrestador() != null) {
                bean.getObjetoAnexo1().setCntPrestadoresId(bean.getObjetoAnexo1().getCntPrestador().getId());
                bean.getObjetoAnexo1().setCntPrestadorSedesId(bean.getObjetoAnexo1().getCntPrestadorSede().getId());
            }
            
            // validamos que al menos un campo haya cambiado con respecto al objeto Afiliado
            //obtenemos el valor del tipo de documento, para compararlo con el del objeto Anexo1
            Maestro maestro = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeTipoDocumento());
            if (maestro != null) {
                tipoDocumentoAfiliado = maestro.getValor();
            }
            if (bean.getObjetoAnexo1().getTipoDocumentoNuevo().equals(tipoDocumentoAfiliado) &&
                    bean.getObjetoAnexo1().getNumeroDocumentoNuevo().equals(bean.getObjeto().getNumeroDocumento()) &&
                    bean.getObjetoAnexo1().getNombre1Nuevo().equals(bean.getObjeto().getPrimerNombre()) &&
                    //bean.getObjeto().getNombre2Nuevo().equals(bean.getObjeto().getSegundoNombre()) &&
                    bean.getObjetoAnexo1().getApellido1Nuevo().equals(bean.getObjeto().getPrimerApellido()) &&
                    //bean.getObjeto().getApellido2Nuevo().equals(bean.getObjeto().getSegundoApellido()) &&
                    bean.getObjetoAnexo1().getFechaNacimientoNuevo().equals(bean.getObjeto().getFechaNacimiento()) &&
                    bean.getObjetoAnexo1().getSexoNuevo().equals(bean.getObjeto().getGenero()) &&
                    //bean.getObjeto().getFechaExpedicionCedulaNuevo().equals(bean.getObjeto().getFechaExpedicionCedula()) &&
                    bean.getObjetoAnexo1().getDireccionNuevo().equals(bean.getObjeto().getDireccionResidencia()) 
                    //bean.getObjeto().getTelefonoNuevo().equals(bean.getObjeto().getTelefonoFijo()) &&
                    //bean.getObjeto().getCelularNuevo().equals(bean.getObjeto().getTelefonoMovil())
                    ) {
                        noCambios = true;
                        if(bean.getObjeto().getSegundoNombre() != null &&
                                bean.getObjeto().getSegundoNombre().equals(bean.getObjetoAnexo1().getNombre2Nuevo())) {
                            noCambios = true;
                        }else if (bean.getObjeto().getSegundoNombre() != null &&
                                !bean.getObjeto().getSegundoNombre().equals(bean.getObjetoAnexo1().getNombre2Nuevo())) {
                            noCambios = false;
                        }
                        if (noCambios) {
                            if(bean.getObjeto().getSegundoApellido() != null &&
                                    bean.getObjeto().getSegundoApellido().equals(bean.getObjetoAnexo1().getApellido2Nuevo())) {
                                noCambios = true;
                            }else if(bean.getObjeto().getSegundoApellido() != null &&
                                    !bean.getObjeto().getSegundoApellido().equals(bean.getObjetoAnexo1().getApellido2Nuevo())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if (bean.getObjeto().getFechaExpedicionCedula() != null &&
                                bean.getObjeto().getFechaExpedicionCedula().equals(bean.getObjetoAnexo1().getFechaExpedicionCedulaNuevo())) {
                                noCambios = true;
                                //bean.addError("Se debe realizar algún cambio en la información para poder registrar el Anexo1.");
                            }else if (bean.getObjeto().getFechaExpedicionCedula() != null &&
                                    !bean.getObjeto().getFechaExpedicionCedula().equals(bean.getObjetoAnexo1().getFechaExpedicionCedulaNuevo())){
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjeto().getTelefonoFijo() != null &&
                                bean.getObjeto().getTelefonoFijo().equals(bean.getObjetoAnexo1().getTelefonoNuevo())) {
                            noCambios = true;
                            }else if (bean.getObjeto().getTelefonoFijo() != null &&
                                !bean.getObjeto().getTelefonoFijo().equals(bean.getObjetoAnexo1().getTelefonoNuevo())) {
                            noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjeto().getTelefonoMovil() != null &&
                                    bean.getObjeto().getTelefonoMovil().equals(bean.getObjetoAnexo1().getCelularNuevo())){
                                noCambios = true;
                            }else if (bean.getObjeto().getTelefonoMovil() != null &&
                                    !bean.getObjeto().getTelefonoMovil().equals(bean.getObjetoAnexo1().getCelularNuevo())) {
                                noCambios = false;
                            }
                        }
                        //2023-03-26 jyperez campos nuevos RES 2335
                        if (noCambios) {
                            if(bean.getObjeto().getResidenciaUbicacion()!= null && bean.getObjetoAnexo1().getResidenciaUbicacionNuevo() != null &&
                                    bean.getObjeto().getResidenciaUbicacion().getId().equals(bean.getObjetoAnexo1().getResidenciaUbicacionNuevo().getId())){
                                noCambios = true;
                            }else if (bean.getObjeto().getResidenciaUbicacion() != null && bean.getObjetoAnexo1().getResidenciaUbicacionNuevo() != null &&
                                    !bean.getObjeto().getResidenciaUbicacion().getId().equals(bean.getObjetoAnexo1().getResidenciaUbicacionNuevo().getId())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjeto().getEmail()!= null &&
                                    bean.getObjeto().getEmail().equals(bean.getObjetoAnexo1().getEmailNuevo())){
                                noCambios = true;
                            }else if (bean.getObjeto().getEmail() != null &&
                                    !bean.getObjeto().getEmail().equals(bean.getObjetoAnexo1().getEmailNuevo())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjeto().getDireccionAlternaContacto()!= null &&
                                    bean.getObjeto().getDireccionAlternaContacto().equals(bean.getObjetoAnexo1().getDireccionAlternaContactoNuevo())){
                                noCambios = true;
                            }else if (bean.getObjeto().getDireccionAlternaContacto() != null &&
                                    !bean.getObjeto().getDireccionAlternaContacto().equals(bean.getObjetoAnexo1().getDireccionAlternaContactoNuevo())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjeto().getNombreContactoEmergencia()!= null &&
                                    bean.getObjeto().getNombreContactoEmergencia().equals(bean.getObjetoAnexo1().getNombreContactoEmergenciaNuevo())){
                                noCambios = true;
                            }else if (bean.getObjeto().getNombreContactoEmergencia() != null &&
                                    !bean.getObjeto().getNombreContactoEmergencia().equals(bean.getObjetoAnexo1().getNombreContactoEmergenciaNuevo())) {
                                noCambios = false;
                            }
                        }
                        if (noCambios) {
                            if(bean.getObjeto().getTelefonoContactoEmergencia()!= null &&
                                    bean.getObjeto().getTelefonoContactoEmergencia().equals(bean.getObjetoAnexo1().getTelefonoContactoEmergenciaNuevo())){
                                noCambios = true;
                            }else if (bean.getObjeto().getTelefonoContactoEmergencia() != null &&
                                    !bean.getObjeto().getTelefonoContactoEmergencia().equals(bean.getObjetoAnexo1().getTelefonoContactoEmergenciaNuevo())) {
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
                    (!bean.getObjetoAnexo1().getTipoDocumentoNuevo().equals(tipoDocumentoAfiliado) ||
                    !bean.getObjetoAnexo1().getNumeroDocumentoNuevo().equals(bean.getObjeto().getNumeroDocumento()) ||
                    !bean.getObjetoAnexo1().getNombre1Nuevo().equals(bean.getObjeto().getPrimerNombre()) ||
                    (!bean.getObjetoAnexo1().getNombre2Nuevo().trim().equals("") && !bean.getObjetoAnexo1().getNombre2Nuevo().equals(bean.getObjeto().getSegundoNombre())) ||
                    !bean.getObjetoAnexo1().getApellido1Nuevo().equals(bean.getObjeto().getPrimerApellido()) ||
                    (!bean.getObjetoAnexo1().getApellido2Nuevo().trim().equals("") && !bean.getObjetoAnexo1().getApellido2Nuevo().equals(bean.getObjeto().getSegundoApellido())) ||
                    !bean.getObjetoAnexo1().getSexoNuevo().equals(bean.getObjeto().getGenero()) ||
                    !bean.getObjetoAnexo1().getFechaNacimientoNuevo().equals(bean.getObjeto().getFechaNacimiento())
                    )) {
                bean.addError("Se debe adjuntar un soporte si alguna de las inconsistencias reportada corresponde a Nombres, Apellidos, Tipo Documento, Número Documento, Fecha de Nacimiento o Sexo.");
            }
            
            if (!bean.isError()) {
                
                //actualizamos los estados de aquellas variables modificadas
                if (!bean.getObjetoAnexo1().getTipoDocumentoNuevo().equals(tipoDocumentoAfiliado)) {
                    bean.getObjetoAnexo1().setTipoDocumentoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getNumeroDocumentoNuevo().equals(bean.getObjeto().getNumeroDocumento())) {
                    bean.getObjetoAnexo1().setNumeroDocumentoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getNombre1Nuevo().equals(bean.getObjeto().getPrimerNombre())) {
                    bean.getObjetoAnexo1().setNombre1Inconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getNombre2Nuevo().trim().equals("") && !bean.getObjetoAnexo1().getNombre2Nuevo().equals(bean.getObjeto().getSegundoNombre())) {
                    bean.getObjetoAnexo1().setNombre2Inconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getApellido1Nuevo().equals(bean.getObjeto().getPrimerApellido())) {
                    bean.getObjetoAnexo1().setApellido1Inconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getApellido2Nuevo().trim().equals("") && !bean.getObjetoAnexo1().getApellido2Nuevo().equals(bean.getObjeto().getSegundoApellido())) {
                    bean.getObjetoAnexo1().setApellido2Inconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getFechaNacimientoNuevo().equals(bean.getObjeto().getFechaNacimiento())) {
                    bean.getObjetoAnexo1().setFechaNacimientoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getSexoNuevo().equals(bean.getObjeto().getGenero())) {
                    bean.getObjetoAnexo1().setSexoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (bean.getObjetoAnexo1().getFechaExpedicionCedulaNuevo() != null && !bean.getObjetoAnexo1().getFechaExpedicionCedulaNuevo().equals(bean.getObjeto().getFechaExpedicionCedula())) {
                    bean.getObjetoAnexo1().setFechaExpedicionCedulaInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getDireccionNuevo().equals(bean.getObjeto().getDireccionResidencia())) {
                    bean.getObjetoAnexo1().setDireccionInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getTelefonoNuevo().trim().equals("") && !bean.getObjetoAnexo1().getTelefonoNuevo().equals(bean.getObjeto().getTelefonoFijo())) {
                    bean.getObjetoAnexo1().setTelefonoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getCelularNuevo().trim().equals("") && !bean.getObjetoAnexo1().getCelularNuevo().equals(bean.getObjeto().getTelefonoMovil())) {
                    bean.getObjetoAnexo1().setCelularInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                 //2023-03-26 jyperez campos nuevos RES 2335
                if (bean.getObjetoAnexo1().getResidenciaUbicacionNuevo() != null && !bean.getObjetoAnexo1().getResidenciaUbicacionNuevo().getId().equals(bean.getObjeto().getResidenciaUbicacion().getId())) {
                    bean.getObjetoAnexo1().setResidenciaUbicacionInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getEmailNuevo().trim().equals("") && !bean.getObjetoAnexo1().getEmailNuevo().equals(bean.getObjeto().getEmail())) {
                    bean.getObjetoAnexo1().setEmailInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (bean.getObjetoAnexo1().getDireccionAlternaContactoNuevo() != null && !bean.getObjetoAnexo1().getDireccionAlternaContactoNuevo().trim().equals("") && !bean.getObjetoAnexo1().getDireccionAlternaContactoNuevo().equals(bean.getObjeto().getDireccionAlternaContacto())) {
                    bean.getObjetoAnexo1().setDireccionAlternaContactoInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getNombreContactoEmergenciaNuevo().trim().equals("") && !bean.getObjetoAnexo1().getNombreContactoEmergenciaNuevo().equals(bean.getObjeto().getNombreContactoEmergencia())) {
                    bean.getObjetoAnexo1().setNombreContactoEmergenciaInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                if (!bean.getObjetoAnexo1().getTelefonoContactoEmergenciaNuevo().trim().equals("") && !bean.getObjetoAnexo1().getTelefonoContactoEmergenciaNuevo().equals(bean.getObjeto().getTelefonoContactoEmergencia())) {
                    bean.getObjetoAnexo1().setTelefonoContactoEmergenciaInconsistencia(AsegAnexo1.ESTADO_INCONSISTENCIA_PENDIENTE_GESTION);
                }
                
                // actualizamos el estado del objeto Anexo1
                //2024-04-05 jyperez actualizamos el campo version a true
                bean.getObjetoAnexo1().setVersion(true);
                bean.getObjetoAnexo1().setEstado(AfiliadoActualizacionBean.ESTADO_PENDIENTE);
                bean.getObjetoAnexo1().setAsegAfiliadosId(bean.getObjeto());
                bean.getObjetoAnexo1().setGnEmpresa(null);
                //bean.auditoriaGuardar(bean.getObjeto());
                
                bean.getObjetoAnexo1().setUsuarioCrea("Externo");
                bean.getObjetoAnexo1().setTerminalCrea("Externo");
                bean.getObjetoAnexo1().setFechaHoraCrea(new Date());
                //2022-11-25 jyperez actualización nuevos campos
                bean.getObjetoAnexo1().setTratamientoDatosAutoriza(true);
                bean.getObjetoAnexo1().setTratamientoDatosFechaHora(new Date());
                //guardamos el anexo1
                id = getAnexo1Remoto().insertar(bean.getObjetoAnexo1());
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
    
    private void guardarAdjunto(AfiliadoActualizacionBean bean) {
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
            String tipoDocumento = bean.getHashTiposDocumento().get(bean.getObjetoAnexo1().getAsegAfiliadosId().getMaeTipoDocumento()).getValor();

            for (AsegAnexo1Adjunto asegAnexo1Adjunto : bean.getListaAsegAnexo1Adjuntos()) {
                nombreArchivo = new StringBuilder();
                nombreArchivo.append(tipoDocumento)
                        .append(bean.getObjetoAnexo1().getAsegAfiliadosId().getNumeroDocumento())
                        .append("_")
                        .append(sdf.format(new Date()));
                nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
                //bean.auditoriaGuardar(asegAnexo1Adjunto);
                asegAnexo1Adjunto.setUsuarioCrea("Externo");
                asegAnexo1Adjunto.setTerminalCrea("Externo");
                asegAnexo1Adjunto.setFechaHoraCrea(new Date());
                asegAnexo1Adjunto.setArchivo(nombreArchivo.append(".").append(asegAnexo1Adjunto.getExtensión()).toString());
                asegAnexo1Adjunto.setAsegAnexo1Id(new AsegAnexo1(bean.getObjeto().getId()));
                asegAnexo1Adjunto.setRuta(ruta);
                File archivo = new File(ruta, asegAnexo1Adjunto.getArchivo());
                OutputStream destino = new FileOutputStream(archivo);
                IOUtils.copy(asegAnexo1Adjunto.getAdjuntoStream(), destino);
                IOUtils.closeQuietly(asegAnexo1Adjunto.getAdjuntoStream());
                IOUtils.closeQuietly(destino);
                //bean.auditoriaGuardar(asegAnexo1Adjunto);
                asegAnexo1Adjunto.setAdjuntoStream(null);
                asegAnexo1Adjunto.setId(getAnexo1AdjuntoRemoto().insertar(asegAnexo1Adjunto));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
//    private  List<AfiliadoActualizacion> consultarAfiliadoActualizado(AfiliadoActualizacionBean bean){
//        List<AfiliadoActualizacion> afiliadoActualidado = new ArrayList<>();
//        try {
//            armarParametroConsultaAfiliadoActualizado(bean);
//            //afiliadoActualidado = getAfiliadoActualizacionRemoto().consultarLista(bean.getParamConsulta());
//        } catch (Exception e) {
//            bean.addError("Error al busca afiliado actualizado en DB:" + e.getMessage());
//        }
//        return afiliadoActualidado;
//    }
    
             
    @Override
    public void cargaInicial(AfiliadoActualizacionBean bean) {
        try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipiosAntioquia());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipiosAntioquia());            
            bean.setListaTiposGenero(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashTiposGenero(new HashMap());
            bean.getListaTiposGenero().forEach(m -> {
                bean.getHashTiposGenero().put(m.getId(), m);
            });
        } catch (Exception ex) {
             bean.addError("Error carga inicial : "+ ex.getMessage());
        }
    }
    
    private AfiliadoActualizacion castEntidadAfiliado(AsegAfiliado afiliadoService) {
        AfiliadoActualizacion afiliado = new AfiliadoActualizacion();
        try {
            afiliado.setId(afiliadoService.getId());
            afiliado.setTipoDocumento(afiliadoService.getMaeTipoDocumento()+"");
            afiliado.setNumeroDocumento(afiliadoService.getNumeroDocumento());
            afiliado.setPrimerNombre(afiliadoService.getPrimerNombre());
            afiliado.setSegundoNombre(afiliadoService.getSegundoNombre());
            afiliado.setPrimerApellido(afiliadoService.getPrimerApellido());
            afiliado.setSegundoApellido(afiliadoService.getSegundoApellido());
            afiliado.setFechaNacimiento(afiliadoService.getFechaNacimiento());
            afiliado.setCodigoSexo(afiliadoService.getGenero());
            afiliado.setFechaExpedicionDocumento(afiliadoService.getFechaExpedicionCedula());
            afiliado.setDireccionAfiliado(afiliadoService.getDireccionResidencia());
            afiliado.setTelefono(afiliadoService.getTelefonoFijo());
            afiliado.setCelular(afiliadoService.getTelefonoMovil());
            afiliado.setMunicipioAfiliacion(afiliadoService.getAfiliacionUbicacion().getNombre());
            afiliado.setObservacion(afiliadoService.getObservacionNovedad());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return afiliado;
    }
    
    public void consultarSedesPrestador(AfiliadoActualizacionBean bean) {
        try{
            //cargamos las sedes en la lista de sedes
            bean.setListaCntPrestadorSedes(getPrestadorSedeRemoto().consultarPorPrestador(bean.getObjetoAnexo1().getCntPrestador().getId()));
        } catch (Exception ex) {
             bean.addError(ex.getMessage());
        }
    }
    
    private Anexo1Remoto getAnexo1Remoto() throws Exception {
        return (Anexo1Remoto) RemotoEJB.getEJBRemoto(("Anexo1Servicio"), Anexo1Remoto.class.getName());
    }
    
    private Anexo1AdjuntoRemoto getAnexo1AdjuntoRemoto() throws Exception {
        return (Anexo1AdjuntoRemoto) RemotoEJB.getEJBRemoto(("Anexo1AdjuntoServicio"), Anexo1AdjuntoRemoto.class.getName());
    }
    
//    private boolean validarAusenciaAfiliadoEnDB(AfiliadoActualizacionBean bean) {
//        return consultarAfiliadoActualizado(bean).size() <= 0;
//    }

//    private void armarParametroConsultaAfiliadoActualizado(AfiliadoActualizacionBean bean) {
//        Map<String, Object> filtros = new HashMap<>();
//        bean.getParamConsulta().setFiltros(filtros);
//        bean.getParamConsulta().getFiltros().put("contratoAfiliado", bean.getObjeto().getContratoAfiliado());
//        bean.getParamConsulta().getFiltros().put("direccion", bean.getObjeto().getDireccionAfiliado());
//    }
    
}
