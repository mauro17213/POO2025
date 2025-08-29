/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCargaMasiva;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCargaMasivaCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusCaso;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCargaMasivaRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoServicioRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaEspecialidadRemoto;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusCargaMasivaCasosBean;
import com.saviasaludeps.savia.web.atencionusuario.utilities.PropAtencionUsuario;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Fichero;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Stiven Giraldo
 */
public class AusCargaMasivaCasosServicioImpl extends AccionesBO implements AusCargaMasivaCasosServicioIface {

    public static final int MAX_CARACTERES_OBSERVACION = 510;

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private AusCasoRemoto getCasoRemoto() throws Exception {
        return (AusCasoRemoto) RemotoEJB.getEJBRemoto("AusCasoServicio", AusCasoRemoto.class.getName());
    }

    private AusPersonaRemoto getPersonaRemoto() throws Exception {
        return (AusPersonaRemoto) RemotoEJB.getEJBRemoto("AusPersonaServicio", AusPersonaRemoto.class.getName());
    }

    private AusCasoServicioRemoto getServicioRemoto() throws Exception {
        return (AusCasoServicioRemoto) RemotoEJB.getEJBRemoto("AusCasoServicioServicio", AusCasoServicioRemoto.class.getName());
    }

    private AusSeguimientoRemoto getSeguimientoRemoto() throws Exception {
        return (AusSeguimientoRemoto) RemotoEJB.getEJBRemoto("AusSeguimientoServicio", AusSeguimientoRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private MaDiagnosticoRemoto getMaDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("MaDiagnosticoServicio", MaDiagnosticoRemoto.class.getName());
    }

    private MaEspecialidadRemoto getEspecialidadRemoto() throws Exception {
        return (MaEspecialidadRemoto) RemotoEJB.getEJBRemoto(("MaEspecialidadServicio"), MaEspecialidadRemoto.class.getName());
    }

    private AusCargaMasivaRemoto getAusCargaMasivaRemoto() throws Exception {
        return (AusCargaMasivaRemoto) RemotoEJB.getEJBRemoto(("AusCargaMasivaServicio"), AusCargaMasivaRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    @Override
    public void Accion(AusCargaMasivaCasosBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case AusCargaMasivaCasosBean.ACCION_BUSCAR_ESPECIALIDAD:
                            //verMaEspecialidad(bean);
                            break;
                        case AusCargaMasivaCasosBean.ACCION_BUSCAR_USUARIO:
                            //buscarUsuario(bean);
                            break;
                        case AusCargaMasivaCasosBean.ACCION_VERIFICAR_USO_RADICADOS:
                            //verificarUsoRadicadosEnCasos(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    //int idRegistroCarga = guardarRegistroCargaMasiva(bean);
                    //guardar( bean, idRegistroCarga);
                    guardar(bean);
                    //guardarFechaFinCargaMasiva( bean, idRegistroCarga );
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    descargar(bean);
                    break;
                case Url.ACCION_ADICIONAL_3:
                    abortar(bean);
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(AusCargaMasivaCasosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getAusCargaMasivaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAusCargaMasivaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(AusCargaMasivaCasosBean bean) {
        try {
            bean.setObjeto(new AusCargaMasiva());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(AusCargaMasivaCasosBean bean) {
        try {
            int id = 0;
            int indiceExtension;
            String extension;
            String nombre = null;
            Date fecha = new Date();
            String ruta = PropApl.getInstance().get(PropApl.AUS_RUTA_CARGA);
            if (!new File(ruta).exists()) {
                bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
            }
            // validamos si el archivo cargado es correcto
            switch(bean.getObjeto().getTipo()){
                case AusCargaMasiva.TIPO_CARGA_MASIVA_CASO:
                    validarArchivoCargaCaso(bean);
                    validarExitenciaVariablesPorDefecto(bean);
                    break;
                case AusCargaMasiva.TIPO_CARGA_MASIVA_CIERRE_CASO:
                    validarArchivoCerrarCaso(bean);
                    validarExitenciaVariablesPorDefectoCerrarCaso(bean);
                    break;
                case AusCargaMasiva.TIPO_CARGA_MASIVA_REVERTIR_CASO:
                    validarArchivoRevertirCaso(bean);
                    //validarExitenciaVariablesPorDefectoRevertirCaso(bean);
                    break;
            }
            
            if (!bean.isError()) {
                // actualizamos valores del objeto a guardar
                // el id de radicado es autoincremental, por eso no se asigna valor. El tipo se seleccionó en la lista de la pantalla
                bean.getObjeto().setRuta(ruta);
                // obtenemos la extensión del archivo, y el nombre separado
                indiceExtension = bean.getObjeto().getNombre().lastIndexOf(".");
                extension = bean.getObjeto().getNombre().substring(indiceExtension, bean.getObjeto().getNombre().length());
                //nombre = bean.getObjeto().getNombre().substring(0, indiceExtension);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                switch(bean.getObjeto().getTipo()){
                    case AusCargaMasiva.TIPO_CARGA_MASIVA_CASO:
                        nombre = "adjunto_crear_caso_";
                        break;
                    case AusCargaMasiva.TIPO_CARGA_MASIVA_CIERRE_CASO:
                        nombre = "adjunto_cerrar_caso_";
                        break;
                    case AusCargaMasiva.TIPO_CARGA_MASIVA_REVERTIR_CASO:
                        nombre = "adjunto_revertir_caso_";
                        break;
                }
                String filename = nombre + sdf.format(new Date()) + "_";
                // generamos el nombre de archivo
                bean.getObjeto().setArchivo(filename);
                // guardamos el estado
                //2020-07-17 jyperez los registros, iniciaran en el estado Procesando, debido a que automáticamente quedan
                // trabajando sobre un hilo
                bean.getObjeto().setEstado(AusCargaMasiva.ESTADO_CARGA_EN_COLA);
                // guardamos la fecha
                bean.getObjeto().setFechaHoraInicio(fecha);
                bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
                bean.getObjeto().setExiste(Boolean.TRUE);
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro en AusCargaMasiva
                id = getAusCargaMasivaRemoto().insertar(bean.getObjeto());
                bean.getObjeto().setId(id);
                bean.getObjeto().setArchivo(filename + id + extension);
                getAusCargaMasivaRemoto().actualizarArchivo(bean.getObjeto());
                //guardamos el archivo en la ruta
                generarArchivo(bean.getObjeto());
                // aca se llama al proceso que ejecutará el hilo
                if (id != 0) {
                    bean.addMensaje("El archivo " + bean.getObjeto().getNombre() + " se cargó con exito, con número de radicado " + bean.getObjeto().getId()); 
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void abortar(AusCargaMasivaCasosBean bean) {
        try {
            bean.setObjeto(getAusCargaMasivaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(PeConstantes.ESTADO_CANCELADO);
            bean.getObjeto().setFechaHoraFin(new Date());
            getAusCargaMasivaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se abortó la carga masiva con éxito. Recuerde esperar unos minutos antes de descargar los resultados, debido a que hay procesos internos que están finalizando.");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void descargar(AusCargaMasivaCasosBean bean) {
        try {
            bean.setObjeto(getAusCargaMasivaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void validarExitenciaVariablesPorDefecto(AusCargaMasivaCasosBean bean) {
        if (!isValidoEstadoSeguimiento(bean.getValorDefectoEstadoSeguimiento(), bean)) {
            bean.addError(" El estado del seguimiento por defecto ('Radicado') no esta configurado en lista del sistema.\n");
        }
        if (!isValidoEstadoCaso(bean.getValorDefectoEstadoCaso(), bean)) {
            bean.addError(" El estado de solicitud de caso por defecto ('Auditar') no esta configurado en lista del sistema.\n");
        }
        if (!isValidoEstadoPersona(bean.getValorDefectoEstadoPersona(), bean)) {
            bean.addError(" El estado de la persona por defecto ('Activo') no esta configurado en lista del sistema.\n");
        }
        if (!isValidoOrigenCaso(bean.getValorDefectoOrigenCaso(), bean)) {
            bean.addError(" El estado origen caso por defecto('Super Salud') no esta configurado en lista del sistema.\n");
        }
        if (!isValidoEstadoServicio(bean.getValorDefectoEstadoServicio(), bean)) {
            bean.addError(" El estado del servicio por defecto('Asignado') no esta configurado en lista del sistema.\n");
        }
        verMaEspecialidad(bean);
        MaEspecialidad especialidad = bean.getMaEspecialidadDefecto();
        if (especialidad == null) {
            bean.addError(" El servicio o especialidad del caso servicio por defecto no se encuentra en el sistema.\n");
        }
    }
    
    private void validarExitenciaVariablesPorDefectoCerrarCaso(AusCargaMasivaCasosBean bean) {
        if (!isValidoEstadoCaso(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO)), bean)) {
            bean.addError(" El estado de solicitud de caso por defecto ('Auditar') no esta configurado en lista del sistema.\n");
        }
        if (!isValidoEstadoServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO)), bean)) {
            bean.addError(" El estado de solicitud de caso por defecto ('Cerrado') no esta configurado en lista del sistema.\n");
        }
        Maestro estadoSeguimiento;
        try {
            estadoSeguimiento = getMaestroRemoto().consultar(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMIENTO_ESTADO_CERRADO)));
            if (estadoSeguimiento == null) {
                bean.addError(" El estado de seguimiento por defecto ('CERRADO') no esta configurado en lista del sistema.\n");
            }
        } catch (Exception ex) {
            bean.addError(" El estado de seguimiento por defecto ('CERRADO') no esta configurado en lista del sistema.\n");
        }
        
    }
    
    private void validarExitenciaVariablesPorDefectoRevertirCaso(AusCargaMasivaCasosBean bean) {
        if (!isValidoEstadoCaso(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_CERRADO)), bean)) {
            bean.addError(" El estado de solicitud de caso por defecto ('Auditar') no esta configurado en lista del sistema.\n");
        }
        if (!isValidoEstadoServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SERVICIO_ESTADO_CERRADO)), bean)) {
            bean.addError(" El estado de solicitud de caso por defecto ('Cerrado') no esta configurado en lista del sistema.\n");
        }
        Maestro estadoSeguimiento;
        try {
            estadoSeguimiento = getMaestroRemoto().consultar(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEGUIMIENTO_ESTADO_CERRADO)));
            if (estadoSeguimiento == null) {
                bean.addError(" El estado de seguimiento por defecto ('CERRADO') no esta configurado en lista del sistema.\n");
            }
        } catch (Exception ex) {
            bean.addError(" El estado de seguimiento por defecto ('CERRADO') no esta configurado en lista del sistema.\n");
        }
        
    }
    
    private void validarArchivoCargaCaso(AusCargaMasivaCasosBean bean) {
        int i = 0;
        String mensaje = "";
        String encabezado = "consecutivo|pqr_codigo|pqr_canal|fecha_creacion|pet_tipodoc|pet_numdoc|pet_nombres|pet_apellidos|pet_genero|pet_ubicacion|pet_telefono|pet_mail|afec_tipodo|afec_numdoc|afec_nombres|afec_apellidos|afec_sexo|afec_mpio|afec_telefono|afec_mail|afec_parentesco|pqr_tipopeticion|ente_control|riesgo_vida|responsable|ambito|patologia1|observacion|mae_tecnologia_alto_costo_codigo|mae_motivo_especifico_codigo|mae_tipo_motivo_especifico_codigo|mae_subtipo_motivo_especifico_codigo|fecha_creacion_caso|proteccion_datos";
        int cantidadCampos = 33;
        InputStream aux;
        BufferedReader br;
        InputStream copia;
        // variables para clonar el inputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        String line;
        try {
            aux = bean.getObjeto().getAdjuntoStream();
            while ((len = aux.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            copia = new ByteArrayInputStream(baos.toByteArray());
            //hacemos copia nuevamente en el inputStream que clonamos
            bean.getObjeto().setAdjuntoStream(new ByteArrayInputStream(baos.toByteArray()));

            br = new BufferedReader(new InputStreamReader(copia));

            while ((line = br.readLine()) != null) {
                // validamos el encabezado
                if (i == 0) {
                    if (!encabezado.equals(line)) {
                        mensaje = "*El encabezado del archivo no se encontró o es erróneo";
                        //break;
                    }
                }
                if (contarCaracteres(line, '|') != cantidadCampos) {
                    mensaje = mensaje + "*linea " + (i + 1) + ": la cantidad de campos no corresponde a " + (cantidadCampos + 1);
                }
                i++;
            }
            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjeto().setCantidadRegistros(i - 1);
            bean.getObjeto().setExitosos(0);
            bean.getObjeto().setFallidos(0);
            if (!mensaje.isEmpty()) {
                bean.addError(mensaje);
            }
        } catch (IOException e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void validarArchivoCerrarCaso(AusCargaMasivaCasosBean bean) {
        int i = 0;
        String mensaje = "";
        String encabezado = "consecutivo|numero_caso|motivo_cerrado|descripcion_caso|descripcion_servicio";
        int cantidadCampos = 4;
        InputStream aux;
        BufferedReader br;
        InputStream copia;
        // variables para clonar el inputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        String line;
        try {
            aux = bean.getObjeto().getAdjuntoStream();
            while ((len = aux.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            copia = new ByteArrayInputStream(baos.toByteArray());
            //hacemos copia nuevamente en el inputStream que clonamos
            bean.getObjeto().setAdjuntoStream(new ByteArrayInputStream(baos.toByteArray()));

            br = new BufferedReader(new InputStreamReader(copia));

            while ((line = br.readLine()) != null) {
                // validamos el encabezado
                if (i == 0) {
                    if (!encabezado.equals(line)) {
                        mensaje = "*El encabezado del archivo no se encontró o es erróneo";
                        //break;
                    }
                }
                if (contarCaracteres(line, '|') != cantidadCampos) {
                    mensaje = mensaje + "*linea " + (i + 1) + ": la cantidad de campos no corresponde a " + (cantidadCampos + 1);
                }
                i++;
            }
            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjeto().setCantidadRegistros(i - 1);
            bean.getObjeto().setExitosos(0);
            bean.getObjeto().setFallidos(0);
            if (!mensaje.isEmpty()) {
                bean.addError(mensaje);
            }
        } catch (IOException e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void validarArchivoRevertirCaso(AusCargaMasivaCasosBean bean) {
        int i = 0;
        String mensaje = "";
        String encabezado = "consecutivo|numero_caso|motivo|comentario";
        int cantidadCampos = 3;
        InputStream aux;
        BufferedReader br;
        InputStream copia;
        // variables para clonar el inputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        String line;
        try {
            aux = bean.getObjeto().getAdjuntoStream();
            while ((len = aux.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            copia = new ByteArrayInputStream(baos.toByteArray());
            //hacemos copia nuevamente en el inputStream que clonamos
            bean.getObjeto().setAdjuntoStream(new ByteArrayInputStream(baos.toByteArray()));

            br = new BufferedReader(new InputStreamReader(copia));

            while ((line = br.readLine()) != null) {
                // validamos el encabezado
                if (i == 0) {
                    if (!encabezado.equals(line)) {
                        mensaje = "*El encabezado del archivo no se encontró o es erróneo";
                        //break;
                    }
                }
                if (contarCaracteres(line, '|') != cantidadCampos) {
                    mensaje = mensaje + "*linea " + (i + 1) + ": la cantidad de campos no corresponde a " + (cantidadCampos + 1);
                }
                i++;
            }
            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjeto().setCantidadRegistros(i - 1);
            bean.getObjeto().setExitosos(0);
            bean.getObjeto().setFallidos(0);
            if (!mensaje.isEmpty()) {
                bean.addError(mensaje);
            }
        } catch (IOException e) {
            bean.addError(e.getMessage());
        }
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

    private boolean generarArchivo(AusCargaMasiva objeto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(objeto.getRuta(), objeto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(objeto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(objeto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            //Asignación de permisos FULL
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

    /*public void guardar(AusCargaMasivaCasosBean bean, int idCargaMasiva) {
        int numeroLinea = 1;
        try {
            
           HashMap<String,AsegAfiliado> mapAfiliadosEstado =  buscarEstadoUsuarioAsegAfiliado(bean.getListaCasos());
           HashMap<String,AusPersona> mapTelefonosAdicionales =  asignarTelefonosAdicionalesAusPersona(bean.getListaCasos());
           
           List<AusCargaMasivaCaso> listaCarga = bean.getListaCasos();
            if(listaCarga.size()>0 && idCargaMasiva > 0){
                for(AusCargaMasivaCaso carga : listaCarga){
                    numeroLinea++;
                    AusCaso caso =  carga.getCaso();
                    AusPersona afiliado =  carga.getAfiliado();
                    AusPersona peticionario =  carga.getPeticionario();
                    AusCasoServicio servicio = carga.getServicio();
                    AusSeguimiento seguimiento = carga.getSeguimiento();
                    
                    asignarFechaNacimientoIdAsegAfiliado(afiliado,mapAfiliadosEstado);
                    asignarUltimoEstadoExistenteAfiliado(afiliado, mapAfiliadosEstado);
                    asignarTelefonosAdicionalesAfiliado(afiliado, mapTelefonosAdicionales);
                    afiliado.setId( getPersonaRemoto().insertarSinValidacionTelefonoExistente(afiliado) );
                    caso.setAsuPersonasId(afiliado);
                    
                    if (caso.getParentesco() != null && !caso.getParentesco().equals("")) {
                        asignarDatosDeAsegAfiliado(peticionario);
                        peticionario.setId( getPersonaRemoto().insertar(peticionario));
                        caso.setPeticionario(peticionario);
                    }
                    bean.auditoriaGuardar(caso);
                    caso.setIdAusCargaMasiva(idCargaMasiva);
                    caso.setBorrado(Boolean.FALSE);
                    caso.setCantidadServicios(servicio != null ? AusCasoServicio.CANTIDAD_SERVICIOS_CARGA_MASIVA : AusCasoServicio.CANTIDAD_SERVICIOS_RESUELTOS_CARGA_MASIVA);                  
                    caso.setCantidadServiciosCerrados(AusCasoServicio.CANTIDAD_SERVICIOS_RESUELTOS_CARGA_MASIVA);
                    int idCaso = this.getCasoRemoto().insertar(caso);
                    
                    caso.setId(idCaso);
                    servicio.setCasosId(caso);
                    bean.auditoriaGuardar(servicio);
                    getServicioRemoto().insertar(servicio);
                    
                    seguimiento.setCasosId(caso);
                    bean.auditoriaGuardar(seguimiento);
                    getSeguimientoRemoto().insertar(seguimiento);
                }
            }
            
        } catch (Exception e) {
            String error = "Error al guardar casos : en archivo " + bean.getObjeto().getAdjunto().getNombre() +" - "+
                    " en número línea "+numeroLinea+", "+e;
            bean.addError(error); 
            guardarEstadoError(idCargaMasiva, error);
        }
    }*/
 /*public int guardarRegistroCargaMasiva(AusCargaMasivaCasosBean bean) {
        int idRegistroCargaMasiva = 0 ;
        try {
            AusCargaMasiva ausCargaMasiva = new AusCargaMasiva();
            List<AusCargaMasivaCaso> listaCarga = bean.getListaCasos();
            if( listaCarga.size()>0 ){
               ausCargaMasiva.setCantidadRegistros(listaCarga.size());
               ausCargaMasiva.setEstado(AusCargaMasiva.ESTADO_EN_PROCESO);
               ausCargaMasiva.setFechaHoraInicio(new Date());
               bean.auditoriaGuardar(ausCargaMasiva);
               idRegistroCargaMasiva = getAusCargaMasivaRemoto().insertar(ausCargaMasiva);
               ausCargaMasiva.setId(idRegistroCargaMasiva);
               bean.getObjeto().setAusCargaMasiva(ausCargaMasiva);
            }    
        } catch (Exception e) {
            String error = "Error al guardarRegistro tabla ausCargaMasiva : en archivo " +
                            bean.getObjeto().getAdjunto().getNombre() +" - " + e;
            bean.addError(error);
            guardarEstadoError(idRegistroCargaMasiva, error);
        }
        return idRegistroCargaMasiva;
    }*/
 /*public void guardarFechaFinCargaMasiva(AusCargaMasivaCasosBean bean, int idRegistroCarga ) {
        try {
            AusCargaMasiva ausCargaMasiva = bean.getObjeto().getAusCargaMasiva();
            List<AusCargaMasivaCaso> listaCarga = bean.getListaCasos();
            if (listaCarga.size() > 0 && idRegistroCarga > 0 && !bean.isError()) {
                ausCargaMasiva.setId(idRegistroCarga);
                ausCargaMasiva.setEstado(AusCargaMasiva.ESTADO_TERMINADA);
                ausCargaMasiva.setFechaHoraFin(new Date());
                getAusCargaMasivaRemoto().actualizarFechaMasEstado(ausCargaMasiva);
            }
        } catch (Exception e) {
            String error = "Error al guardarFechaFinCargaMasiva:  en archivo " + 
                            bean.getObjeto().getAdjunto().getNombre() +" - " + e;
            bean.addError(error);
            guardarEstadoError(idRegistroCarga, error);
        }
    }*/
    private void guardarEstadoError(int idCargaMasiva, String observacion) {
        try {
            if (idCargaMasiva > 0) {
                AusCargaMasiva ausCargaMasiva = new AusCargaMasiva();
                ausCargaMasiva.setId(idCargaMasiva);
                ausCargaMasiva.setObservacion(StringUtils.abbreviate(observacion, MAX_CARACTERES_OBSERVACION));
                ausCargaMasiva.setEstado(AusCargaMasiva.ESTADO_SUSPENDIDA_POR_ERROR);
                getAusCargaMasivaRemoto().actualizarFechaMasEstado(ausCargaMasiva);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public List<Date> obtenerFechas(Date fecha) {
        List<Date> resultado = new ArrayList<>();
        try {
            resultado = getCasoRemoto().consultarFechasNoHabiles(fecha);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return resultado;
    }

    @Override
    public MaDiagnostico obtenerDiagnostico(String nombreDiagnostico) {
        MaDiagnostico maDiagnosticoEncontrado = new MaDiagnostico();
        try {

            if (nombreDiagnostico != null) {
                nombreDiagnostico = nombreDiagnostico.trim();
                ParamConsulta param = new ParamConsulta();
                Map<String, Object> filtros = new HashMap();
                filtros.put("nombre", nombreDiagnostico);
                param.setFiltros(filtros);
                param.setRegistrosPagina(30);

                param.setCantidadRegistros(getMaDiagnosticoRemoto().consultarCantidadListaBuscador(param));
                List<MaDiagnostico> listDiagnosticos = getMaDiagnosticoRemoto().consultarListaBuscador(param);

                for (MaDiagnostico maDiagnostico : listDiagnosticos) {
                    if (maDiagnostico.getNombre().trim().equals(nombreDiagnostico)) {
                        maDiagnosticoEncontrado = maDiagnostico;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            maDiagnosticoEncontrado = new MaDiagnostico();
        }
        return maDiagnosticoEncontrado;
    }

    private void verMaEspecialidad(AusCargaMasivaCasosBean bean) {
        try {           
           if ( bean.getValorDefectoEspecialidadServicio() > 0 ){
               bean.setMaEspecialidadDefecto( getEspecialidadRemoto().consultar( bean.getValorDefectoEspecialidadServicio() ));
           }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
 /*private void buscarUsuario(AusCargaMasivaCasosBean bean) {
        try { 
            if(!"".equals(bean.getUsuarioCasos().getUsuario())){
              bean.setUsuarioCasos(getUsuarioRemoto().consultarPorUsuario(bean.getUsuarioCasos().getUsuario()) );
            } 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }*/
 /*private void verificarUsoRadicadosEnCasos(AusCargaMasivaCasosBean bean) {
        try {
            
            HashMap<String, Integer> nuevoMap = new HashMap<>();
            List<String> radicadosParaProcesar = new ArrayList<>(bean.getMapUtil().keySet());      
            String radicadosPreparados = Stream.of(radicadosParaProcesar.toArray()).map(String::valueOf).collect(Collectors.joining(","));      
            List<AusCaso> asuCasos = buscarCasosSegunRadicados(bean,radicadosPreparados);
            for (AusCaso asuCaso : asuCasos) {
                String llave = "'"+asuCaso.getRadicado()+"'";
                if(bean.getMapUtil().get(llave)!=null){
                    nuevoMap.put(asuCaso.getRadicado(), bean.getMapUtil().get(llave));
                }
            }
            bean.setMapUtil(nuevoMap);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }*/
    private List<AusCaso> buscarCasosSegunRadicados(AusCargaMasivaCasosBean bean, String radicados) {
        List<AusCaso> ausCasos = new ArrayList<>();
        try {
            if (radicados != null) {
                ParamConsulta paramConsulta = new ParamConsulta();
                paramConsulta.setParametroConsulta1(radicados);
                ausCasos = getAusCargaMasivaRemoto().consultarCasosPorRadicadoRegistrado(paramConsulta);
            }
        } catch (Exception e) {
            bean.addError("Error al buscarCasosSegunRadicados :" + e.getMessage());
        }
        return ausCasos;
    }

    public HashMap<Integer, Maestro> convertToHash(List<Maestro> list) {
        HashMap<Integer, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    public HashMap<Integer, Ubicacion> convertToHashUbicaciones(List<Ubicacion> list) {
        HashMap<Integer, Ubicacion> map = new HashMap<>();
        for (Ubicacion i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    public HashMap<Integer, Usuario> convertToHashUsuarios(List<Usuario> list) {
        HashMap<Integer, Usuario> map = new HashMap<>();
        for (Usuario i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    private HashMap<String, AsegAfiliado> buscarEstadoUsuarioAsegAfiliado(List<AusCargaMasivaCaso> listaCarga) {
        String idsAfilidos = "";
        String documentos = "";
        HashMap<String, AsegAfiliado> mapAfiliadosEncontrados = new HashMap<>();
        ParamConsulta paramConsulta = new ParamConsulta();

        try {
            if (!listaCarga.isEmpty()) {
                documentos = listaCarga.stream().map(ausCargaMasivaCaso -> "'" + ausCargaMasivaCaso.getAfiliado().getDocumento() + "',").reduce(documentos, String::concat);
                documentos = documentos.substring(0, documentos.length() - 1);
                paramConsulta.setParametroConsulta1(documentos);
                List<AsegAfiliado> asegAfiliadoList = getAusCargaMasivaRemoto().consultarUltimosRegistroAsegAfiliadoLista(paramConsulta);
                paramConsulta.setParametroConsulta1(null);

                if (asegAfiliadoList != null && !asegAfiliadoList.isEmpty()) {
                    idsAfilidos = asegAfiliadoList.stream().map(asegAfiliado -> "'" + asegAfiliado.getId() + "',").reduce(idsAfilidos, String::concat);
                    idsAfilidos = idsAfilidos.substring(0, idsAfilidos.length() - 1);
                    paramConsulta.setParametroConsulta2(idsAfilidos);

                    List<AsegAfiliado> asegAfiliadosList = getAusCargaMasivaRemoto().consultarDatosAsegAfiliadoLista(paramConsulta);

                    for (AsegAfiliado asegAfiliado : asegAfiliadosList) {
                        String prefijo = asegAfiliado.getMaeTipoDocumentoCodigo() == null ? "0" : asegAfiliado.getMaeTipoDocumentoCodigo();
                        prefijo = (prefijo + "_" + asegAfiliado.getNumeroDocumento()).toLowerCase().trim();
                        mapAfiliadosEncontrados.put(prefijo, asegAfiliado);
                    }
                }
            }
        } catch (Exception ex) {
            mapAfiliadosEncontrados = new HashMap<>();
        }
        return mapAfiliadosEncontrados;
    }

    private HashMap<String, AusPersona> asignarTelefonosAdicionalesAusPersona(List<AusCargaMasivaCaso> listaCarga) {
        HashMap<String, AusPersona> mapAfiliadosEncontrados = new HashMap<>();
        String documentos = "";
        String idsAfilidos = "";
        ParamConsulta paramConsulta = new ParamConsulta();
        try {
            if (!listaCarga.isEmpty()) {
                documentos = listaCarga.stream().map(ausCargaMasivaCaso -> "'" + ausCargaMasivaCaso.getAfiliado().getDocumento() + "',").reduce(documentos, String::concat);
                documentos = documentos.substring(0, documentos.length() - 1);
                paramConsulta.setParametroConsulta1(documentos);
                List<AusPersona> asegAfiliadoList = getPersonaRemoto().consultarUltimosRegistroPersonaLista(paramConsulta);
                if (asegAfiliadoList != null && !asegAfiliadoList.isEmpty()) {
                    idsAfilidos = asegAfiliadoList.stream().map(asegAfiliado -> "'" + asegAfiliado.getId() + "',").reduce(idsAfilidos, String::concat);
                    idsAfilidos = idsAfilidos.substring(0, idsAfilidos.length() - 1);
                    paramConsulta.setParametroConsulta1(idsAfilidos);
                    List<AusPersona> afiliadosTelefonos = getPersonaRemoto().consultarPersonaConTelefonosLista(paramConsulta);
                    for (AusPersona asegAfiliado : afiliadosTelefonos) {
                        String prefijo = asegAfiliado.getMae_tipo_documento_codigo() == null ? "0" : asegAfiliado.getMae_tipo_documento_codigo();
                        prefijo = (prefijo + "_" + asegAfiliado.getDocumento()).toLowerCase().trim();
                        mapAfiliadosEncontrados.put(prefijo, asegAfiliado);
                    }
                }
            }
        } catch (Exception ex) {
            mapAfiliadosEncontrados = new HashMap<>();
        }
        return mapAfiliadosEncontrados;
    }

    private void asignarUltimoEstadoExistenteAfiliado(AusPersona afiliado, HashMap<String, AsegAfiliado> mapAfiliadosEstado) {
        try {
            String llave = afiliado.getMae_tipo_documento_codigo() + "_" + afiliado.getDocumento();
            llave = llave.toLowerCase().trim();

            if (mapAfiliadosEstado.get(llave) != null) {
                AsegAfiliado afiliadoEncontrado = mapAfiliadosEstado.get(llave);
                if (afiliadoEncontrado.getMaeEstadoAfiliacion() > 0 && afiliadoEncontrado.getMaeEstadoAfiliacionCodigo() != null && afiliadoEncontrado.getMaeEstadoAfiliacionValor() != null) {
                    afiliado.setMae_estado_id(afiliadoEncontrado.getMaeEstadoAfiliacion());
                    afiliado.setMae_estado_codigo(afiliadoEncontrado.getMaeEstadoAfiliacionCodigo());
                    afiliado.setMae_estado_valor(afiliadoEncontrado.getMaeEstadoAfiliacionValor());
                }
            }
        } catch (Exception e) {
        }
    }

    private void asignarTelefonosAdicionalesAfiliado(AusPersona afiliado, HashMap<String, AusPersona> registrosAnterioresAfiliadosMap) {
        try {
            String llave = afiliado.getMae_tipo_documento_codigo() + "_" + afiliado.getDocumento();
            llave = llave.toLowerCase().trim();
            HashMap<Integer, Integer> telefonosInsertar = new HashMap<>();
            if (registrosAnterioresAfiliadosMap.get(llave) != null) {
                AusPersona afiliadoEncontrado = registrosAnterioresAfiliadosMap.get(llave);
                if (!afiliadoEncontrado.getListaTelefonos().isEmpty()) {
                    for (AusPersonaTelefono telefonoNuevo : afiliado.getListaTelefonos()) {
                        int telefonoValidar = convertirParaTelefonoValido(telefonoNuevo.getNumero());
                        telefonosInsertar.put(telefonoValidar, telefonoValidar);
                    }
                    for (AusPersonaTelefono telefonoInsertadoConAnterioridad : afiliadoEncontrado.getListaTelefonos()) {
                        int telefonoAnterior = convertirParaTelefonoValido(telefonoInsertadoConAnterioridad.getNumero());
                        if (telefonoAnterior > 0 && telefonosInsertar.get(telefonoAnterior) == null) {
                            afiliado.getListaTelefonos().add(telefonoInsertadoConAnterioridad);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private int convertirParaTelefonoValido(String telefono) {
        int valor;
        try {
            int telefonoInt = Integer.parseInt(telefono.trim());
            valor = telefonoInt;
        } catch (NumberFormatException e) {
            valor = 0;
        }
        return valor;
    }

    private void asignarDatosDeAsegAfiliado(AusPersona afiliado) {
        try {
            List<AsegAfiliado> ausPersona = getAfiliadoRemoto().consultarListaAfiliados(afiliado.getMae_tipo_documento_codigo(), afiliado.getDocumento());
            if (ausPersona != null && !ausPersona.isEmpty()) {
                AsegAfiliado afiliadoEncontrado = ausPersona.get(0);
                afiliado.setFechaNacimiento(afiliadoEncontrado.getFechaNacimiento());
                afiliado.setAseg_afiliados_id(afiliadoEncontrado.getId());
            }
        } catch (Exception e) {
        }
    }

    private void asignarFechaNacimientoIdAsegAfiliado(AusPersona afiliado, HashMap<String, AsegAfiliado> mapAfiliadosEstado) {
        try {
            String llave = afiliado.getMae_tipo_documento_codigo() + "_" + afiliado.getDocumento();
            llave = llave.toLowerCase().trim();

            if (mapAfiliadosEstado.get(llave) != null) {
                AsegAfiliado afiliadoEncontrado = mapAfiliadosEstado.get(llave);
                if (afiliadoEncontrado != null) {
                    afiliado.setFechaNacimiento(afiliadoEncontrado.getFechaNacimiento());
                    afiliado.setAseg_afiliados_id(afiliadoEncontrado.getId());
                }
            }
        } catch (Exception e) {
        }
    }
    
    private boolean isValidoEstadoSeguimiento(Integer estadoSeguimiento, AusCargaMasivaCasosBean bean) {
        boolean valor = false;
        if (estadoSeguimiento != null) {
            for (Maestro estado : bean.getListaTipoSeguimiento()) {
                if (estado.getId().compareTo(estadoSeguimiento) == 0) {
                    valor = true;
                    break;
                }
            }
        }
        return valor;
    }
    
    private boolean isValidoEstadoCaso(Integer estadoCaso, AusCargaMasivaCasosBean bean) {
        boolean valor = false;
        if (estadoCaso != null) {
            for (Maestro estado : bean.getListaTipoEstadoSolicitud()) {
                if (estado.getId().compareTo(estadoCaso) == 0) {
                    valor = true;
                    break;
                }
            }
        }
        return valor;
    } 
     
    private boolean isValidoEstadoPersona(Integer estado, AusCargaMasivaCasosBean bean) {
        boolean estadoEncontrado = false;
        if (estado != null) {
            for (Maestro estadoPersona : bean.getListaTipoEstadoPersona()) {
                if (estadoPersona.getId().compareTo(estado) == 0) {
                    estadoEncontrado = true;
                    break;
                }
            }
        }
        return estadoEncontrado;
    }
    
    
     private boolean isValidoOrigenCaso(int origenCaso, AusCargaMasivaCasosBean bean) {
        boolean valor = false;
        if (origenCaso > 0) {
            for (Maestro origen : bean.getListaTipoSolicitudOrigen()) {
                if (origen.getId().compareTo(origenCaso) == 0) {
                    valor = true;
                    break;
                }
            }
        }
        return valor;
    }
     
    private boolean isValidoEstadoServicio(int idEstadoServicio, AusCargaMasivaCasosBean bean) {
        boolean valor = false;
        if (idEstadoServicio > 0) {
            for (Maestro origen : bean.getListaTipoEstadoServicio()) {
                if (origen.getId().compareTo(idEstadoServicio) == 0) {
                    valor = true;
                    break;
                }
            }
        }
        return valor;
    }
     
    @Override
    public void cargaInicial(AusCargaMasivaCasosBean bean) {
        try {
            bean.setValorDefectoDestinoServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SERVICIO_PRESTADOR_SEDE_DESTINO)));
            bean.setValorDefectoEspecialidadServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SERVICIO_ESPECIALIDAD_DEFECTO)));
            bean.setValorDefectoEstadoServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SERVICIO_ESTADO_POR_DEFECTO)));
            bean.setValorDefectoPrescriptoraServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SERVICIO_SEDE_PRESCRIPTORA)));
            bean.setValorDefectoPrioridad(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_PRIORIDAD_POR_DEFECTO)));
            bean.setValorDefectoSede(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEDE_POR_DEFECTO)));
            bean.setValorDefectoUbicacion(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_UBICACION_POR_DEFECTO)));
            bean.setValorDefectoEstadoCaso(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_POR_DEFECTO)));
            bean.setValorDefectoEstadoPersona(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.PERSONA_ESTADO_POR_DEFECTO)));
            bean.setValorDefectoEstadoSeguimiento(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SEGUIMIENTO_ESTADO_POR_DEFECTO)));
            bean.setValorDefectoOrigenCaso(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ORIGEN_POR_DEFECTO)));
            
            bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));
            bean.setListaTipoEstadoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            bean.setListaTipoEstadoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setListaTipoSolicitudOrigen(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));
            bean.setListaTipoEstadoServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_ESTADO));
        } catch (NumberFormatException e) {
            bean.addError("Ha ocurrido un error al asignar las configuraciones por defacto:" + e.toString());
        } catch (Exception ex) {
            bean.addError("Ha ocurrido un error en carga inicial : " + ex.toString());
        }
        /*try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(convertToHash(bean.getListaTiposDocumento()));
            bean.setListaSexo(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashSexo(convertToHash(bean.getListaSexo()));
            bean.setListaTipoEstadoServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_ESTADO));
            bean.setHashTipoEstadoServicio(convertToHash(bean.getListaTipoEstadoServicio()));
            bean.setListaTipoEstadoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            bean.setHashTipoEstadoSolicitud(convertToHash(bean.getListaTipoEstadoSolicitud()));
            bean.setListaTipoEstadoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashTipoEstadosPersona(convertToHash(bean.getListaTipoEstadoPersona()));
            bean.setListaTipoSolicitud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_TIPO));
            bean.setHashTipoSolicitud(convertToHash(bean.getListaTipoSolicitud()));
            bean.setListaTipoSolicitudOrigen(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));
            bean.setHashTipoSolicitudOrigen(convertToHash(bean.getListaTipoSolicitudOrigen()));
            bean.setListaTipoCanalSuperSalud(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CANAL_SUPER_SALUD));
            bean.setHashTipoCanalSuperSalud(convertToHash(bean.getListaTipoCanalSuperSalud()));
            bean.setListaTipoSolicitudPrioridad(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_PRIORIDAD));
            bean.setHashTipoSolicitudPrioridad(convertToHash(bean.getListaTipoSolicitudPrioridad()));
            bean.setListaTipoSolicitudEnteControl(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ENTE_CONTROL));
            bean.setHashTipoSolicitudEnteControl(convertToHash(bean.getListaTipoSolicitudEnteControl()));    
            bean.setListaTipoSolicitudRiesgoVida(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_RIESGO_VIDA));
            bean.setHashTipoSolicitudRiesgoVida(convertToHash(bean.getListaTipoSolicitudRiesgoVida()));
            bean.setListaTipoServicioAmbito(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_AMBITO));
            bean.setHashTipoServicioAmbito(convertToHash(bean.getListaTipoServicioAmbito()));
            bean.setListaTipoServicioMotivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_MOTIVO));
            bean.setHashTipoServicioMotivo(convertToHash(bean.getListaTipoServicioMotivo()));        
            bean.setListaTipoPatologia(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_PATOLOGIA));
            bean.setHashTipoPatologia(convertToHash(bean.getListaTipoPatologia()));
            bean.setListaTipoSeguimiento(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));
            bean.setHashTipoSeguimiento(convertToHash(bean.getListaTipoSeguimiento()));  
            bean.setListaTecnologiaAltoCosto(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_TECNOLOGIA_ALTO_COSTO));
            bean.setHashTecnologiaAltoCosto(convertToHash( bean.getListaTecnologiaAltoCosto()));
            bean.setListaMotivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CASO_MOTIVO_ESPECIFICO));
            bean.setHashMotivo(convertToHash(bean.getListaMotivo()));
            bean.setListaTipoMotivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CASO_TIPO_MOTIVO_ESPECIFICO));
            bean.setHashTipoMotivo(convertToHash(bean.getListaTipoMotivo()));
            bean.setListaSubMotivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CASO_SUBTIPO_MOTIVO_ESPECIFICO));
            bean.setHashSubMotivo(convertToHash(bean.getListaSubMotivo()));
            
            
            
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            
            //bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
            //bean.setUbicacionesRecursiva(convertToHashUbicaciones(bean.getUbicaciones()));
          
            try {
                bean.setValorDefectoDestinoServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SERVICIO_PRESTADOR_SEDE_DESTINO)));
                bean.setValorDefectoEspecialidadServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SERVICIO_ESPECIALIDAD_DEFECTO)));
                bean.setValorDefectoEstadoServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SERVICIO_ESTADO_POR_DEFECTO)));
                bean.setValorDefectoPrescriptoraServicio(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SERVICIO_SEDE_PRESCRIPTORA)));
                bean.setValorDefectoPrioridad(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_PRIORIDAD_POR_DEFECTO)));
                bean.setValorDefectoSede(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_SEDE_POR_DEFECTO)));
                bean.setValorDefectoUbicacion(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_UBICACION_POR_DEFECTO)));
                bean.setValorDefectoEstadoCaso(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ESTADO_POR_DEFECTO)));
                bean.setValorDefectoEstadoPersona(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.PERSONA_ESTADO_POR_DEFECTO)));
                bean.setValorDefectoEstadoSeguimiento(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.SEGUIMIENTO_ESTADO_POR_DEFECTO)));
                bean.setValorDefectoOrigenCaso(Integer.parseInt(PropAtencionUsuario.getInstance().get(PropAtencionUsuario.CASO_ORIGEN_POR_DEFECTO)));
            } catch (NumberFormatException e) {
                bean.addError("Ha ocurrido un error al asignar las configuraciones por defacto:" + e.toString());
            }
        } catch (Exception ex) {
            bean.addError("Ha ocurrido un error en carga inicial : " + ex.toString());
        }*/
    }
    
    public boolean isUTF8(InputStream inputStream) throws IOException {
        DataInputStream input = new DataInputStream(inputStream);
        // Recorremos el fichero y comprobamos que todos los bytes del mismo
        // cumplen el formato UTF-8
        byte byteActual;
        try {
            // Mientras no llegemos al final del fichero
            while (true) { // El fin del bucle se produce con EOFException
                byteActual = input.readByte();
                // El formato UTF-8 coincide con el ISO en los 128 primero caracteres.
                // pero para valores superiores a 128 (1000 0000) 0x40 se utilizan de 2 a 4 bytes 
                // 0x80 --> la mascara es 0111 1111 ( 0x7F) Si es igual al 1111 1111
                if (((byte) (byteActual | 0x7F)) == (byte) 0xFF) {
                    // Los bytes menores coinciden con el formato ISO y ASCII ,
                    // pero los valores mayores de 128 deben ajustarse al tamaÃ±o
                    // de 2,3,4 bytes segÃºn lo requerido
                    // ComprobaciÃ³n para caracteres UTF-8 de 2 bytes Formato requerido: 110xxxxx 10xxxxxx
                    // Comprobamos si empieza por 110xxxxx 110xxxxx --> la mascara que busco es 00011111 --> hexadecimal : 0x1F
                    // El valor procesado debe ser igual a 110xxxxx --> 11011111 -> 0xDF
                    if (((byte) (byteActual | 0x1F)) == ((byte) 0xDF)) {
                        // Estamos en una estructura que tiene 2 bytes.
                        byteActual = input.readByte();
                        // Comprobamos si empieza por 10xxxxxx 10xxxxxx --> la mascara que busco es 10111111 --> a 0xBF
                        // El valor procesado debe ser igual a 10xxxxxx --> 10111111 -> 0xBF
                        if (((byte) (byteActual | 0xBF)) == (byte) 0xBF) {
                        // OK, el caracter es correcto
                        } else {
                            // No cumple con la condiciÃ³n de segundo byte 10xxxxxx
                            return false;
                        }
                    } else {
                        // El segundo caracter tiene que cumplir 10xxxxxxx, en otro caso es un error
                        return false; // 11110011
                    }
                }else{
                    // TODO : Por ahora solo se consideran un subconjunto 
                    // UTF-8 ( para caracteres de 1 o 2 bytes , considerando
                    // erroneos los de 3 o 4 bytes. ( Por ejemplo quedan fuera del reconocedor el Tibetano :p
                }
            }
        } catch (EOFException eof) {
            //log.debug("Procesado correctamente.");
        } catch (IOException e) {
            //log.warn(e.getMessage());
            return false;
        }
        return true;
    }
}
