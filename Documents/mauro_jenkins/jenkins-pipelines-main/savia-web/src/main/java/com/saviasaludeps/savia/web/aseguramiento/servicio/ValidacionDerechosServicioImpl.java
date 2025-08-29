/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegValidacionDerecho;
import com.saviasaludeps.savia.web.aseguramiento.bean.ValidacionDerechosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.aseguramiento.ValidacionDerechosRemoto;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.ValidacionDerechosHilo;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author jyperez
 */
public class ValidacionDerechosServicioImpl extends AccionesBO implements ValidacionDerechosServicioIface {
    
    private ValidacionDerechosRemoto getValidacionDerechosRemoto() throws Exception {
        return (ValidacionDerechosRemoto) RemotoEJB.getEJBRemoto(("ValidacionDerechosServicio"), ValidacionDerechosRemoto.class.getName());
    }

    @Override
    public void Accion(ValidacionDerechosBean bean) {
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
                    cargarListaDetalleCarga(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    abortar(bean);
                    break;
                
            }
            cargas(bean);
        }
    }

    private void listar(ValidacionDerechosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getValidacionDerechosRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getValidacionDerechosRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(ValidacionDerechosBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(ValidacionDerechosBean bean) {
        try {
            bean.setObjeto(new AsegValidacionDerecho());

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(ValidacionDerechosBean bean) {
        try {
            int id = 0;
            //PropApl propiedades = new PropApl();
            ValidacionDerechosHilo validacionDerechosHilo;
            String ruta;
            ruta = PropApl.getInstance().get(PropApl.RUTA_ASEGURAMIENTO_VALIDACION_DERECHOS);
            bean.addError(cargarRegistrosValidacion(bean));
            //validamos si existe algun registro en proceso
            bean.addError(validarValidacionDerechosEnProceso(bean));
            
            if (!bean.isError()) {
                // actualizamos valores del objeto a guardar
                // el id de radicado es autoincremental, por eso no se asigna valor. El tipo se seleccionó en la lista de la pantalla
                bean.getObjeto().setRuta(ruta);
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getObjeto());
                //adicionamos la fecha hora crea, similar al a fecha hora inicio
                bean.getObjeto().setFechaHoraInicio(bean.getObjeto().getFechaHoraCrea());
                bean.getObjeto().setEstado(AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_EN_PROCESO);
                //guardamos el registro en asegInforme
                id = getValidacionDerechosRemoto().insertar(bean.getObjeto());
                bean.getObjeto().setId(id);
                // Actualizamos el nombre del archivo con el correspondiente Id
                bean.getObjeto().setArchivo(bean.getObjeto().getArchivo()+ "_" + id + ".txt");
                getValidacionDerechosRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("La validación masiva de derechos " + bean.getObjeto().getArchivo() + " se cargó con exito, con número de radicado " + bean.getObjeto().getId());
                // aca se llama al proceso que ejecutará el hilo
                if (id != 0) {
                    validacionDerechosHilo = new ValidacionDerechosHilo(bean.getObjeto());
                    validacionDerechosHilo.start();
                    //el procesamiento del archivo se realiza en el hilo, y se actualiza solo.
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(ValidacionDerechosBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(ValidacionDerechosBean bean) {
        try {
            
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(ValidacionDerechosBean bean) {
        try {
            
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(ValidacionDerechosBean bean) {
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
    public void cargaInicial(ValidacionDerechosBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean generarArchivo(ValidacionDerechosBean bean) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(bean.getObjeto().getRuta(), bean.getObjeto().getArchivo());
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
                Logger.getLogger(ValidacionDerechosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return esArchivoGuardado;
    }

    private void cargarListaDetalleCarga(ValidacionDerechosBean bean) {
        try {
            bean.setObjeto(getValidacionDerechosRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception ex) {
            Logger.getLogger(ValidacionDerechosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Función que cargará los registros en el archivo cargado, en una lista que serán manipulados posterioremente
     * en el procesamiento del hilo
     * @param bean
     * @return 
     */
    private String cargarRegistrosValidacion(ValidacionDerechosBean bean) {
        int cantidad = 0;
        String mensaje = "";
        bean.getObjeto().setAfiliadosValidacion(new ArrayList<String>());
        // leemos el archivo cargado, para tomar cada valor e ingresarlo en la lista
        InputStream aux;
        BufferedReader br;
        String [] campos;
        InputStream copia;
        // variables para clonar el inputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        String line;
        try {
            aux = bean.getObjeto().getAdjuntoStream();
            while ((len = aux.read(buffer)) > -1 ) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            copia = new ByteArrayInputStream(baos.toByteArray());
            //hacemos copia nuevamente en el inputStream que clonamos
            bean.getObjeto().setAdjuntoStream(new ByteArrayInputStream(baos.toByteArray()));
            br = new BufferedReader(new InputStreamReader(copia));
            //recorremos el archivo, validando el tamaño de caracteres en la linea y adicionandolo a la lista
            //2023-07-26 jyperez validamos el tipo de carga, teniendo en cuenta el cambio de tipo y numero documento
            if (bean.getObjeto().getTipoValidacion() == AsegValidacionDerecho.TIPO_VALIDACION_DOCUMENTO) {
                while ((line = br.readLine()) != null) {
                    if (line.length() > 25 ) {
                        mensaje = mensaje + "*linea " + (cantidad+1) +  ": la cantidad de digitos máxima es 25.";
                        //break;
                    } else {
                        if (contarCaracteres(line, ',') != 1) {
                            mensaje = mensaje + "*linea " + (cantidad+1) +  ": la cantidad de campos no corresponde a 2 ";
                            //break;
                        }
                    }
                    bean.getObjeto().getAfiliadosValidacion().add(line);
                    cantidad++;
                }
            } else {
                while ((line = br.readLine()) != null) {
                    if (line.length() > 16 ) {
                        mensaje = mensaje + "*linea " + (cantidad+1) +  ": la cantidad de digitos máxima es 16.";
                        //break;
                    } else {
                        if (contarCaracteres(line, ',') != 0) {
                            mensaje = mensaje + "*linea " + (cantidad+1) +  ": la cantidad de campos no corresponde a 1 ";
                            //break;
                        }
                    }
                    bean.getObjeto().getAfiliadosValidacion().add(line);
                    cantidad++;
                }
            }
            
            if (mensaje.trim().equals("")) {
                if(cantidad == 0 ) {
                    mensaje = "No se encontraron registros a cargar en el archivo.";
                }
                //2020-10-23 jyperez INC 321 - se aumenta a 5000 la cantidad máxima permitida
                if (cantidad > AfiliadoParametro.CANTIDAD_MAXIMA_REGISTROS) {
                    mensaje = "La cantidad máxima de registros a cargar es de " + AfiliadoParametro.CANTIDAD_MAXIMA_REGISTROS + ".";
                }
                bean.getObjeto().setRegistrosCargados(cantidad);
                
            } else {
                bean.getObjeto().setRegistrosCargados(0);
            }
            
        } catch (IOException  ex) {
            Logger.getLogger(ValidacionDerechosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
    private void abortar(ValidacionDerechosBean bean) {
        try {
            bean.setObjeto(getValidacionDerechosRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_ABORTADO);
            //generamos la auditoria para el objeto modificado
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setFechaHoraFin(new Date());
            getValidacionDerechosRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se abortó la validación de derechos con éxito.");
        } catch (Exception ex) {
            Logger.getLogger(ValidacionDerechosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            bean.addError(ex.getMessage());
        }
    }

    private String validarValidacionDerechosEnProceso(ValidacionDerechosBean bean) {
        int cantidad = 0;
        String mensaje = "";
        try {
            cantidad = getValidacionDerechosRemoto().consultarValidacionDerechosPorEstado(AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_EN_PROCESO);
            if (cantidad > 0) {
                mensaje = "No se puede crear una Validación de Derechos si existe una en proceso.";
            }
        } catch (Exception ex) {
            Logger.getLogger(ReportesServicioImpl.class.getName()).log(Level.SEVERE, "[validarValidacionDerechosEnProceso] Error en ejecución de consulta.", ex);
        }
        
        return mensaje ;
        
    }    
}
