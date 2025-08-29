package com.saviasaludeps.savia.web.juridico.servicio;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.juridico.CntjComite;
import com.saviasaludeps.savia.dominio.juridico.CntjComiteAsistente;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoEjecucion;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjLinea;
import com.saviasaludeps.savia.dominio.juridico.CntjLineaAdjunto;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjUsuarioGrupo;
import com.saviasaludeps.savia.negocio.administracion.GnAlertaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjDocumentoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoEjecucionRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjExpedienteRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjComiteAsistenteRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjComiteRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjEstadoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjLineaAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjLineaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjProcesoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjUsuarioGrupoRemoto;
import com.saviasaludeps.savia.web.juridico.bean.ComiteBean;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjUtilidades;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Fichero;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author idbohorquez
 */
public class ComiteServicioImpl extends AccionesBO  implements ComiteServicioIface {

    
    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }
    
    private CtnjComiteRemoto getCtnjComiteRemoto() throws Exception {
        return (CtnjComiteRemoto) RemotoEJB.getEJBRemoto("CntjComiteServicio", CtnjComiteRemoto.class.getName());
    }
    
    private CtnjComiteAsistenteRemoto getCtnjComiteAsistenteRemoto() throws Exception {
        return (CtnjComiteAsistenteRemoto) RemotoEJB.getEJBRemoto("CntjComiteAsistenteServicio", CtnjComiteAsistenteRemoto.class.getName());
    }
    
    private CtnjLineaRemoto getCtnjLineaRemoto() throws Exception {
        return (CtnjLineaRemoto) RemotoEJB.getEJBRemoto("CntjLineaServicio", CtnjLineaRemoto.class.getName());
    }
    
    private CtnjLineaAdjuntoRemoto getCtnjLineaAdjuntoRemoto() throws Exception {
        return (CtnjLineaAdjuntoRemoto) RemotoEJB.getEJBRemoto("CntjLineaAdjuntoServicio", CtnjLineaAdjuntoRemoto.class.getName());
    }
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private CntjExpedienteRemoto getCntjExpedienteRemoto() throws Exception {
        return (CntjExpedienteRemoto) RemotoEJB.getEJBRemoto("CntjExpedienteServicio", CntjExpedienteRemoto.class.getName());
    }
    
    private CntjDocumentoRemoto getCntjDocumentoRemoto() throws Exception {
        return (CntjDocumentoRemoto) RemotoEJB.getEJBRemoto("CntjDocumentoServicio", CntjDocumentoRemoto.class.getName());
    }
    
    private CtnjEstadoRemoto getCtnjEstadoRemoto() throws Exception {
        return (CtnjEstadoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoServicio", CtnjEstadoRemoto.class.getName());
    }
    
    private CntjEstadoEjecucionRemoto getCntjEstadoEjecucionRemoto() throws Exception {
        return (CntjEstadoEjecucionRemoto) RemotoEJB.getEJBRemoto("CntjEstadoEjecucionServicio", CntjEstadoEjecucionRemoto.class.getName());
    }
    
    private CtnjUsuarioGrupoRemoto getCtnjUsuarioGrupoRemoto() throws Exception {
        return (CtnjUsuarioGrupoRemoto) RemotoEJB.getEJBRemoto("CntjUsuarioGrupoServicio", CtnjUsuarioGrupoRemoto.class.getName());
    }
    
    private GnAlertaRemoto getGnAlertaRemoto() throws Exception {
        return (GnAlertaRemoto) RemotoEJB.getEJBRemoto("GnAlertaServicio", GnAlertaRemoto.class.getName());
    }
    
    private CntjPlantillaRemoto getCntjPlantillaRemoto() throws Exception {
        return (CntjPlantillaRemoto) RemotoEJB.getEJBRemoto("CntjPlantillaServicio", CntjPlantillaRemoto.class.getName());
    }

    
    @Override
    public void Accion(ComiteBean bean) {
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
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            modificar(bean);
                             break;  
                        case Url.ACCION_ADICIONAL_1:
                            cerrarComite(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            abrirComite(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            supenderComite(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            reanudarComite(bean);
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            finalizarComite(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarUsuarios(bean);
                            break;                        
                    }
                    break;                    
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarLineas(bean);
                            break;                        
                        case Url.ACCION_VER:
                            verLineas(bean);
                            break;                        
                        case Url.ACCION_CREAR:
                            crearLinea(bean);
                            break;                        
                        case Url.ACCION_GUARDAR:
                            guardarLinea(bean);
                            break;                        
                        case Url.ACCION_EDITAR:
                            editarLinea(bean);
                            break;                        
                        case Url.ACCION_MODIFICAR:
                            modificarLinea(bean);
                            break;                        
                        case Url.ACCION_ADICIONAL_1:
                            evaluarLinea(bean);
                            break;                        
                        case Url.ACCION_ADICIONAL_2:
                            guardarEvaluacionLinea(bean);
                            break;                        
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                            guardarAdjuntoLinea(bean);
                            break;          
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            consultarDocumentos(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            consultarDocumento(bean);
                            break;
                    }
                    break;
                
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
    @Override
    public void cargaInicial(ComiteBean bean) {
        try {
            bean.setListaEstadosComite(CntjConstantes.listaEstadoComite());
            bean.setListaTipoLinea(CntjConstantes.listaTipoLinea());
            bean.setListaTipoArchivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_ADJUNTO_TIPO));
            bean.setHashlistaTipoArchivo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_ADJUNTO_TIPO));
            bean.setListaEstadoLinea(CntjConstantes.listaEstadoLinea());
            bean.setListaLineas(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al cargas información inicial. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al cargas información inicial.");
        }
    }
    
    private void listar(ComiteBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCtnjComiteRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCtnjComiteRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }
    
    private void ver(ComiteBean bean) {
        try {
            bean.setObjeto(getCtnjComiteRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaComiteAsistente(getCtnjComiteAsistenteRemoto().asistentesComite(bean.getObjeto().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información comité. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información comité." + e.getMessage());
        }
    }

    private void crear(ComiteBean bean) {
        try {
            bean.setObjeto(new CntjComite());
            bean.setListaComiteAsistente(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar el proceso. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar el proceso." + e.getMessage());
        }
    }
    
     private void guardar(ComiteBean bean) {
        try {
            if(bean.getObjeto().getFechaProgramacion() == null) {
                bean.addError("Debe indicar la fecha de programación.");
            }
            if(bean.getListaComiteAsistente().isEmpty()){
                bean.addError("Debe ingresar por lo menos un asistente para crear el comité.");
            }
            
            boolean existe = bean.getRegistros().stream()
                .anyMatch(elemento -> elemento.getFechaProgramacion().equals(bean.getObjeto().getFechaProgramacion()));
            if(existe){
                bean.addError("Ya existe un comite con la misma fecha, no pueden existir mas de un comite para una misma fecha de programación.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getCtnjComiteRemoto().insertar(bean.getObjeto()));
            for(CntjComiteAsistente asiste : bean.getListaComiteAsistente()){
                asiste.setCntjComiteId(bean.getObjeto());
                bean.auditoriaGuardar(asiste);
                getCtnjComiteAsistenteRemoto().insertar(asiste);
            }
            bean.addMensaje("Registro guardado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar." + e.getMessage());
        }
    }     
     
    private void editar(ComiteBean bean) {
        try {
            bean.setObjeto(getCtnjComiteRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaComiteAsistente(getCtnjComiteAsistenteRemoto().asistentesComite(bean.getObjeto().getId()));
            bean.setListaEliminarComiteAsistente(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información comité. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información comité." + e.getMessage());
        }
    }
    
    private void modificar(ComiteBean bean) {
        try {
            if(bean.getObjeto().getFechaProgramacion() == null) {
                bean.addError("Debe indicar la fecha de programación.");
            }
            
            if(bean.getListaComiteAsistente().isEmpty()){
                bean.addError("Debe ingresar por lo menos un asistente para modificar el comité.");
            }
            
            boolean existe = bean.getRegistros().stream()
                .anyMatch(elemento -> elemento.getFechaProgramacion().equals(bean.getObjeto().getFechaProgramacion()) && !elemento.getId().equals(bean.getObjeto().getId()));
            if(existe){
                bean.addError("Ya existe un comite con la misma fecha, no pueden existir mas de un comite para una misma fecha de programación.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            //se modifica la informacion de comite
            bean.auditoriaModificar(bean.getObjeto());
            getCtnjComiteRemoto().actualizar(bean.getObjeto());
            
            //validar si hay elementos para borrar
            if(!bean.getListaEliminarComiteAsistente().isEmpty()){
                for(CntjComiteAsistente asistente : bean.getListaEliminarComiteAsistente()){
                    getCtnjComiteAsistenteRemoto().eliminar(asistente.getId());
                }
            }
            
            //validar si existen nuevos asistentes a guardar
            List<CntjComiteAsistente> nuevosAsistentes = bean.getListaComiteAsistente().stream()
                .filter(asistente -> asistente.getId().equals(-1)) 
                .collect(Collectors.toList());
            
            if(!nuevosAsistentes.isEmpty()){
                for (CntjComiteAsistente asiste : nuevosAsistentes) {
                    asiste.setCntjComiteId(bean.getObjeto());
                    bean.auditoriaGuardar(asiste);
                    getCtnjComiteAsistenteRemoto().insertar(asiste);
                }
            }
            bean.addMensaje("Registro modificado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar información." + e.getMessage());
        }
    }

    private void listarUsuarios(ComiteBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(0).setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setListaUsuarios(getUsuarioRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar lista de usuarios. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar lista de usuarios.");
        }
    }

    private void cerrarComite(ComiteBean bean) {
        try {            
            bean.setObjeto(getCtnjComiteRemoto().consultar(bean.getObjeto().getId()));
            int cantidadLineas = getCtnjLineaRemoto().cantidadLineaComite(bean.getObjeto().getId());
            if(cantidadLineas == 0){
                bean.addError("No existen lineas asociadas, debe existir lieas asociadas para cerrar el comité. ");
            }
            
            //aca se debe validar que debe existir por lo menos una linea para poder
            //permitir que se cierre el comite.
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.getObjeto().setEstado(CntjConstantes.COMITE_CERRADO);            
            //se modifica la informacion de comite
            bean.auditoriaModificar(bean.getObjeto());
            getCtnjComiteRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Registro modificado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar información." + e.getMessage());
        }
    }

    private void abrirComite(ComiteBean bean) {
        try {            
            bean.setObjeto(getCtnjComiteRemoto().consultar(bean.getObjeto().getId()));
            
            Optional<CntjComite> comiteEnProceso = bean.getRegistros().stream()
                    .filter(elemento -> (elemento.getEstado() != CntjConstantes.COMITE_FINALIZADO && elemento.getEstado() != CntjConstantes.COMITE_CREADO))
                    .findFirst();
            
            boolean existeComiteNoFinalizado = false;
            if(comiteEnProceso.isPresent()){
                if(!comiteEnProceso.get().getId().equals(bean.getObjeto().getId())){
                    existeComiteNoFinalizado = true;
                    bean.addError("No puede abrir el comité, porque existen comités que no estan con estado Finalizado o Creado.");
                }
            }
             
            
            boolean existeComiteantiguo = bean.getRegistros().stream()
                .anyMatch(elemento -> (elemento.getFechaProgramacion().before(bean.getObjeto().getFechaProgramacion()) && elemento.getEstado().equals(CntjConstantes.COMITE_CREADO)));
            if(!existeComiteNoFinalizado && existeComiteantiguo){
                bean.addError("No puede abrir este comité, existen comités con fecha de programación más antigua.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.getObjeto().setEstado(CntjConstantes.COMITE_ABIERTO);            
            //se modifica la informacion de comite
            bean.auditoriaModificar(bean.getObjeto());
            getCtnjComiteRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Registro modificado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar información." + e.getMessage());
        }
    }

    private void listarLineas(ComiteBean bean) {
        try {
            bean.setObjeto(getCtnjComiteRemoto().consultar(bean.getObjeto().getId())); 
            bean.getParamConsulta(1).setCantidadRegistros(getCtnjLineaRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setListaLineas(getCtnjLineaRemoto().consultarLista(bean.getParamConsulta(1)));
            bean.setListaExpedienteComite(getCntjExpedienteRemoto().listaExpedienteEstadoComite());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar lista de lineas. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar lista de lineas.");
        }
    }
    
    private void verLineas(ComiteBean bean) {
        try {
            bean.setObjetoLinea(getCtnjLineaRemoto().consultar(bean.getObjetoLinea().getId()));
            bean.setListaAdjuntosLinea(getCtnjLineaAdjuntoRemoto().adjuntosLineas(bean.getObjetoLinea().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información.");
        }
    }

    private void crearLinea(ComiteBean bean) {
        try {
            bean.setObjetoLinea(new CntjLinea());
            bean.getObjetoLinea().setEstado(CntjConstantes.ESTADO_LINEA_REGISTRADO);
            bean.getObjetoLinea().setCntjComiteId(bean.getObjeto());
            bean.setListaAdjuntosLinea(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar el proceso. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar el proceso.");
        }
    }

    private void guardarLinea(ComiteBean bean) {
        try {
            if(bean.getObjetoLinea().getTipo() == null) {
                bean.addError("Debe indicar el tipo de linea.");
            }
            if(bean.getObjetoLinea().getUsuariosId() == null){
                bean.addError("Debe seleccionar un responsable.");
            }
            if(bean.getObjetoLinea().getDescripcion() == null){
                bean.addError("Debe ingresar una descripción.");
            }
            if(bean.getListaAdjuntosLinea().isEmpty()){
                bean.addError("Debe agregar por lo menos un adjunto para crear la linea.");
            }
            
            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_LINEAS_ADJUNTOS);        
            Files.createDirectories(Paths.get(ruta));
            if(!new File(ruta).exists()){
                bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
            }
                
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaGuardar(bean.getObjetoLinea());
            bean.getObjetoLinea().setEstado(CntjConstantes.ESTADO_LINEA_REGISTRADO);
            int idlinea = getCtnjLineaRemoto().insertar(bean.getObjetoLinea()); 
            for(CntjLineaAdjunto adjunto : bean.getListaAdjuntosLinea()){
                Date fecha = new Date();
                Maestro maestroTipoArchivo = bean.getHashlistaTipoArchivo().get(adjunto.getMaeTipoArchivoId());
                adjunto.setMaeTipoArchivoCodigo(maestroTipoArchivo.getValor());
                adjunto.setMaeTipoArchivoValor(maestroTipoArchivo.getNombre());                
                adjunto.setCntjLineasId(new CntjLinea(idlinea));
                adjunto.setRuta(ruta);
                adjunto.setExiste(true);
                int indiceExtension = adjunto.getNombre().lastIndexOf(".");
                String extension = adjunto.getNombre().substring(indiceExtension, adjunto.getNombre().length());
                adjunto.setArchivo(String.format("%s%s%s", CntjConstantes.NOMBRE_ADJUNTO_LINEA, CntjConstantes.formato6.format(fecha),extension));                               
                bean.auditoriaGuardar(adjunto);
                adjunto.setId(getCtnjLineaAdjuntoRemoto().insertar(adjunto));
                generarArchivo(adjunto);
            }
            
            bean.addMensaje("Registro guardado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar." + e.getMessage());
        }
    }

    private void editarLinea(ComiteBean bean) {
        try {
            bean.setObjetoLinea(getCtnjLineaRemoto().consultar(bean.getObjetoLinea().getId()));
            bean.setListaAdjuntosLinea(getCtnjLineaAdjuntoRemoto().adjuntosLineas(bean.getObjetoLinea().getId()));
            bean.setListaAdjuntosLineaEliminar(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información." + e.getMessage());
        }
    }

    private void modificarLinea(ComiteBean bean) {
        try {
            if(bean.getObjetoLinea().getTipo() == null) {
                bean.addError("Debe indicar el tipo de linea.");
            }
            if(bean.getObjetoLinea().getUsuariosId() == null){
                bean.addError("Debe seleccionar un responsable.");
            }
            if(bean.getObjetoLinea().getDescripcion() == null){
                bean.addError("Debe ingresar una observación.");
            }
            
            if(bean.getListaAdjuntosLinea().isEmpty()){
                bean.addError("Debe agregar por lo menos un adjunto para crear la linea.");
            }
            
            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_LINEAS_ADJUNTOS);            
            if(!new File(ruta).exists()){
                bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
            }
                        
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaModificar(bean.getObjetoLinea());
            getCtnjLineaRemoto().actualizar(bean.getObjetoLinea()); 
            
            //Eliminar adjuntos
            if(!bean.getListaAdjuntosLineaEliminar().isEmpty()){
                for(CntjLineaAdjunto adjunto : bean.getListaAdjuntosLineaEliminar()){
                    getCtnjLineaAdjuntoRemoto().eliminar(adjunto.getId());
                    eliminarArchivodirectorio(adjunto);
                }
            }
            
            List<CntjLineaAdjunto> nuevosAdjuntos = bean.getListaAdjuntosLinea().stream()
                .filter(asistente -> asistente.getId().equals(-1)) 
                .collect(Collectors.toList());
            //agregar nuevos adjuntos
            for(CntjLineaAdjunto adjunto : nuevosAdjuntos){
                Date fecha = new Date();
                Maestro maestroTipoArchivo = bean.getHashlistaTipoArchivo().get(adjunto.getMaeTipoArchivoId());
                adjunto.setMaeTipoArchivoCodigo(maestroTipoArchivo.getValor());
                adjunto.setMaeTipoArchivoValor(maestroTipoArchivo.getNombre());                
                adjunto.setCntjLineasId(bean.getObjetoLinea());
                adjunto.setRuta(ruta);
                adjunto.setExiste(true);
                int indiceExtension = adjunto.getNombre().lastIndexOf(".");
                String extension = adjunto.getNombre().substring(indiceExtension, adjunto.getNombre().length());
                adjunto.setArchivo(String.format("%s%s%s", CntjConstantes.NOMBRE_ADJUNTO_LINEA, CntjConstantes.formato6.format(fecha),extension));                               
                bean.auditoriaGuardar(adjunto);
                adjunto.setId(getCtnjLineaAdjuntoRemoto().insertar(adjunto));
                generarArchivo(adjunto);
            }
            
            bean.addMensaje("Registro modificado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar." + e.getMessage());
        }
    }

    private void guardarAdjuntoLinea(ComiteBean bean) {
        try {
            String extension;
            Date fecha = new Date();
            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_LINEAS_ADJUNTOS);            
            if(!new File(ruta).exists()){
                bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
            }
            
            if (!bean.isError()) {
                // actualizamos valores del objeto a guardar
                bean.getLineaAdjunto().setRuta(ruta);
                bean.getLineaAdjunto().setExiste(true);
                int indiceExtension = bean.getLineaAdjunto().getNombre().lastIndexOf(".");
                extension = bean.getLineaAdjunto().getNombre().substring(indiceExtension, bean.getLineaAdjunto().getNombre().length());
                bean.getLineaAdjunto().setArchivo(String.format("%s%s%s", CntjConstantes.NOMBRE_ADJUNTO_LINEA, CntjConstantes.formato6.format(fecha),extension));                               
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getLineaAdjunto());
                //guardamos el registro en PeCarga  
                bean.getObjeto().setId(getCtnjLineaAdjuntoRemoto().insertar(bean.getLineaAdjunto()));
                //guardamos el archivo en la ruta
                generarArchivo(bean.getLineaAdjunto());
                bean.addMensaje("El archivo " + bean.getLineaAdjunto().getNombre() + " se cargó con exito. ");                
            } 
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al subir el archivo. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al subir el archivo." + e.getMessage());
        }
    }
    
    private boolean generarArchivo(CntjLineaAdjunto objeto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(objeto.getRuta(), objeto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(objeto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(objeto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            Fichero.permisos(archivo.toPath());
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException e) {
                esArchivoGuardado = false;
            }
        }

        return esArchivoGuardado;
    } 
    
    private void eliminarArchivodirectorio(CntjLineaAdjunto adjunto){
        try {
            File archivo = new File(adjunto.getRuta(), adjunto.getArchivo());
            if(archivo.exists()){
                archivo.delete();
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al eliminar el archivo del directorio. %s", e.getMessage()), e);
        }
    }

    private void evaluarLinea(ComiteBean bean) {
        try {
            bean.setObjetoLinea(getCtnjLineaRemoto().consultar(bean.getObjetoLinea().getId()));
            bean.setListaAdjuntosLinea(getCtnjLineaAdjuntoRemoto().adjuntosLineas(bean.getObjetoLinea().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar la información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar la información.");
        }
    }

    private void guardarEvaluacionLinea(ComiteBean bean) {
        try {
            if(bean.getObjetoLinea().getEstado() == null){
                bean.addError("Debe indicar el estado de la linea.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            if(bean.getListaLineas().isEmpty()){
                bean.setListaAdjuntosLinea(getCtnjLineaAdjuntoRemoto().adjuntosLineas(bean.getObjetoLinea().getId()));
            }
            
            boolean existeLineaEvaluada = bean.getListaLineas().stream()
                .anyMatch(elemento -> elemento.getEstado() > CntjConstantes.ESTADO_LINEA_REGISTRADO);
            //Se guarda la evaluacion de la linea            
            bean.auditoriaModificar(bean.getObjetoLinea());
            getCtnjLineaRemoto().actualizar(bean.getObjetoLinea());
            cambiarEstadoExpedienteRelacionado(bean);
            //Se cambia el estado del comite cuando se haya evaluado la primera linea
            if(!existeLineaEvaluada && bean.getObjetoLinea().getEstado() > CntjConstantes.ESTADO_LINEA_REGISTRADO){
                //Se cambia el estado del comite a ejecución.
                CntjComite comite = getCtnjComiteRemoto().consultar(bean.getObjeto().getId());
                comite.setFechaInicio(new Date());
                comite.setEstado(CntjConstantes.COMITE_EN_EJECUCION);
                bean.auditoriaModificar(comite);
                getCtnjComiteRemoto().actualizar(comite);
            }
            
            bean.addMensaje("Información de la línea guardada correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar el proceso. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar el proceso.");
        }
    }

    private void supenderComite(ComiteBean bean) {
        try {            
            bean.setObjeto(getCtnjComiteRemoto().consultar(bean.getObjeto().getId()));
            
            boolean lineaNoEvaluada = bean.getListaLineas().stream()
                .anyMatch(elemento -> elemento.getEstado().equals(CntjConstantes.ESTADO_LINEA_REGISTRADO));
            if(lineaNoEvaluada){
                bean.addError("Se encontrarón lineas sin evaluar, todas las lineas deben tener un estado diferente a Registrado.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.getObjeto().setEstado(CntjConstantes.COMITE_EN_RECESO);            
            //se modifica la informacion de comite
            bean.auditoriaModificar(bean.getObjeto());
            getCtnjComiteRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Registro modificado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar información." + e.getMessage());
        }
    }

    private void reanudarComite(ComiteBean bean) {
         try {            
            bean.setObjeto(getCtnjComiteRemoto().consultar(bean.getObjeto().getId()));
                        
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.getObjeto().setEstado(CntjConstantes.COMITE_EN_EJECUCION);            
            //se modifica la informacion de comite
            bean.auditoriaModificar(bean.getObjeto());
            getCtnjComiteRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Registro modificado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar información." + e.getMessage());
        }
    }

    private void finalizarComite(ComiteBean bean) {
        try {            
            bean.setObjeto(getCtnjComiteRemoto().consultar(bean.getObjeto().getId()));
            
            boolean lineaNoEvaluada = bean.getListaLineas().stream()
                .anyMatch(elemento -> elemento.getEstado().equals(CntjConstantes.ESTADO_LINEA_REGISTRADO) || elemento.getEstado().equals(CntjConstantes.ESTADO_LINEA_POSPUESTO));
            if(lineaNoEvaluada){
                bean.addError("Se encontrarón lineas sin evaluar, todas las lineas deben tener uno de los siguientes estados (Aprobado o Rechazado).");
            }
                        
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.getObjeto().setEstado(CntjConstantes.COMITE_FINALIZADO);  
            bean.getObjeto().setFechaFin(new Date());
            //se modifica la informacion de comite
               bean.auditoriaModificar(bean.getObjeto());
            getCtnjComiteRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Registro modificado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar información." + e.getMessage());
        }
    }

    private void consultarDocumentos(ComiteBean bean) {
        try {
            bean.setObjetoLinea(getCtnjLineaRemoto().consultar(bean.getObjetoLinea().getId()));
            if (bean.getObjetoLinea().getExpedienteId() != null && bean.getObjetoLinea().getExpedienteId().getId() != null) {
                bean.getParamConsulta(2).getFiltros().put(CntjConstantes.ID_EXPEDIENTE, bean.getObjetoLinea().getExpedienteId().getId());
                bean.getParamConsulta(2).setCantidadRegistros(getCntjDocumentoRemoto().consultarCantidadLista(bean.getParamConsulta(2)));
                bean.setListaDocumentos(getCntjDocumentoRemoto().consultarLista(bean.getParamConsulta(2)));
            }            
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información.");
        }
    }
    
    private void consultarDocumento(ComiteBean bean) {
        try {
            bean.setObjDocumento(getCntjDocumentoRemoto().consultar(bean.getObjDocumento().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar informacion.");
        }
    }

    private void cambiarEstadoExpedienteRelacionado(ComiteBean bean) {
        try {
            CntjExpediente expediente = getCntjExpedienteRemoto().consultar(bean.getObjetoLinea().getExpedienteId().getId());
            if(!expediente.getEstadoActual().getTipo().equals(CntjConstantes.TIPO_LINEA)){
                bean.addError("No es posible cambiar de estado el expediente relacionado porque su estado actual es diferente para atencion de comité.");
            }
            
            Integer tipoResultado = CntjConstantes.TIPO_LINEA_APROBADA;
            if (bean.getObjetoLinea().getEstado().equals(CntjConstantes.ESTADO_LINEA_RECHAZADO)) {
                tipoResultado = CntjConstantes.TIPO_LINEA_RECHAZADA;
            }
                        
            CntjEstado estadoSiguiente = getCtnjEstadoRemoto().consultarEstadoResultadoLinea(expediente.getProcesoId().getId(), tipoResultado, expediente.getEstadoActual().getId());
            if(estadoSiguiente == null){
                bean.addError("No es posible cambiar de estado el expediente relacionado, no se encontro estado siguiente configurado para la evaluación de la linea.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            boolean continuar = true;
            
            CntjExpediente expActual = getCntjExpedienteRemoto().consultar(bean.getObjetoLinea().getExpedienteId().getId());;
            
            //Se construye el objeto ejecucion para guardar el historial de ejecucion
            CntjEstadoEjecucion estadoEjecucion = new CntjEstadoEjecucion();
            estadoEjecucion.setCntjExpedienteId(expediente);
            estadoEjecucion.setCntjEstadoId(estadoSiguiente);
            estadoEjecucion.setGnUsuariosId(bean.getConexion().getUsuario());
            estadoEjecucion.setCntjLineaId(bean.getObjetoLinea());
            estadoEjecucion.setObservacion(String.format(CntjConstantes.MSJ_EVALUACION_LINEA, CntjConstantes.getEstadoLineaStr(bean.getObjetoLinea().getEstado())));
            estadoEjecucion.setFechaEjecucion(new Date());
            bean.auditoriaGuardar(estadoEjecucion);
            try {
                estadoEjecucion.setId(getCntjEstadoEjecucionRemoto().insertar(estadoEjecucion));
            } catch (Exception e) {
                bean.addError("No fue posible guardar el cambio de estado.");
                continuar = false;
            }
                    
            if(continuar){
                //se actualiza el estado actual del expediente
                expediente.setEstadoActual(estadoSiguiente);
                expediente.setFechaEjecucionEstado(estadoEjecucion.getFechaEjecucion());
                bean.auditoriaModificar(expediente);
                try {
                    getCntjExpedienteRemoto().actualizar(expediente);
                } catch (Exception e) {
                    bean.addError("No fue posible actualizar información del expediente relacionado.");
                    continuar = false;
                    getCntjEstadoEjecucionRemoto().eliminar(estadoEjecucion.getId());
                }
            }
            
            if (continuar) {
                List<CntjDocumento> docCreado = new ArrayList<>();
                List<CntjDocumento> docActualizado = new ArrayList<>();
                try {
                    //se consultan las plantillas´para generar documentos
                    List<CntjPlantilla> plantillas = getCntjPlantillaRemoto().listaDocumentoEstadoGenerados(estadoSiguiente.getId());                    
                    if (!plantillas.isEmpty()) {
                        for (CntjPlantilla item : plantillas) {
                            String plantilla = CntjConstantes.ESTILO_DOCUMENTO + item.getEstructura();
                            JSONObject objetoData = new JSONObject(expediente.getJsonData() != null ? expediente.getJsonData() : "{}");
                            JSONArray arrayObj = new JSONArray();
                            if (objetoData.has(CntjConstantes.CAMPOS)) {
                                arrayObj = objetoData.getJSONArray(CntjConstantes.CAMPOS);
                            }
                            for (int index = 0; index < arrayObj.length(); index++) {
                                JSONObject objItem = arrayObj.getJSONObject(index);
                                plantilla = plantilla.replace("<!-- -->", "");
                                if (objItem.has(CntjConstantes.VALOR_STR)) {
                                    String valorstr = objItem.getString(CntjConstantes.VALOR_STR);
                                    if(valorstr.trim().isEmpty()){
                                       plantilla = plantilla.replace(objItem.getString(CntjConstantes.ETIQUETA), objItem.getString(CntjConstantes.VALOR)); 
                                    }else{
                                        plantilla = plantilla.replace(objItem.getString(CntjConstantes.ETIQUETA), valorstr);
                                    }                                    
                                } else {
                                    plantilla = plantilla.replace(objItem.getString(CntjConstantes.ETIQUETA), objItem.getString(CntjConstantes.VALOR));
                                }
                            }

                            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_EXPEDIENTES_ADJUNTOS);
                            Files.createDirectories(Paths.get(ruta));
                            File archivo = new File(ruta, CntjUtilidades.getNombreDocumento(expediente.getNumeroExpediente(), item.getProcesodocumentoId().getNombre()));
                            ConverterProperties properties = new ConverterProperties();
                            HtmlConverter.convertToPdf(plantilla, new FileOutputStream(archivo), properties);

                            CntjDocumento documento = new CntjDocumento();
                            documento.setCntjExpedienteId(expediente);
                            documento.setCntjPlantillaId(item);
                            documento.setDocumentoNombre(item.getProcesodocumentoId().getNombre());
                            documento.setDocumentoRuta(ruta);
                            documento.setDocumentoArchivo(archivo.getName());
                            documento.setDocumentoExiste(true);
                            documento.setNombre(item.getProcesodocumentoId().getNombre());
                            documento.setTipo(item.getProcesodocumentoId().getTipoDocumento().shortValue());
                            documento.setEtapaContratacion(item.getProcesodocumentoId().getEtapaContratacion());
                            Integer docExistente = getCntjDocumentoRemoto().existeDocumentoExpediente(expediente.getId(), item.getProcesodocumentoId().getNombre());
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
                    getCntjEstadoEjecucionRemoto().eliminar(estadoEjecucion.getId());
                }
            }
            
            if(continuar){
                //Se envia notificacion a los usuarios del nuevo estado
                List<CntjUsuarioGrupo> listaUsuario = getCtnjUsuarioGrupoRemoto().listarUsuariosGrupoPermisos(estadoSiguiente.getId());
                for (CntjUsuarioGrupo usuario : listaUsuario) {
                    try {
                        GnAlerta alerta = CntjConstantes.getAlertaGestionExpediente(usuario.getGnUsuarioId(), estadoEjecucion.getFechaEjecucion(), expediente.getNumeroExpediente(), estadoSiguiente.getNombre(), bean.getConexion().getUsuario().getNombre(), estadoEjecucion.getObservacion());
                        getGnAlertaRemoto().insertar(alerta);
                    } catch (Exception e) {
                        bean.addError("No fue posible enviar notificaciones para algunos usuarios.");
                        continue;
                    }
                }
            }
            
            if (continuar) {
                bean.addMensaje("Expediente actualizado correctamente.");
            }
            
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar el proceso de cambio de estado en el expediente asociado. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar el proceso de cambio de estado en el expediente asociado.");
        }
    }

    

}
