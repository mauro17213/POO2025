/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.financiera.servicio;

import com.saviasaludeps.savia.dominio.financiera.FinCarga;
import com.saviasaludeps.savia.negocio.financiera.FinCargaRemoto;
import com.saviasaludeps.savia.web.financiera.bean.FinCargaBean;
import com.saviasaludeps.savia.web.financiera.utilidades.HiloCargasMasivaFinanciera;
import com.saviasaludeps.savia.web.financiera.utilidades.Utilidades;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author jeperez
 */
public class FinCargaServicioImpl extends AccionesBO implements FinCargaServicioIface {

    public static final int ID_PRESTADOR_SAVIA = 1;
    public static final int NUMERO_COLUMNAS_ADJUNTO = 13;
    
    public static final String NOMBRE_ARCHIVO_CARGA_MASIVA = "carga_masiva_fin_";
    public static final String NOMBRE_ARCHIVO_CARGA_MASIVA_RESP = "carga_masiva_fin_res_";
    public static final String SEPARADOR_ARCHIVO = ",";

    private FinCargaRemoto getFinCargaRemoto() throws Exception {
        return (FinCargaRemoto) RemotoEJB.getEJBRemoto("FinCargaServicio", FinCargaRemoto.class.getName());
    }

    @Override
    public void Accion(FinCargaBean bean) {
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
                    switch (bean.getDoAccion()) {
                        case FinCargaBean.DO_ACCION_GUARDAR_CARGA:
                            guardar(bean);
                            break;
                        case FinCargaBean.DO_ACCION_GUARDAR_CANCELACION:
                            guardarCancelacion(bean);
                            break;
                    }
                    break;   
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                case Url.ACCION_ADICIONAL_4:
                    break;
                case Url.ACCION_ADICIONAL_5:
                    break;
                case Url.ACCION_ADICIONAL_6:
                    break;
            }
        }
    }
    
    private void crear(FinCargaBean bean) {
        try {
            bean.setObjeto(new FinCarga());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(FinCargaBean bean) {
        try {
            
            String ruta = PropApl.getInstance().get(PropApl.FIN_RUTA_CARGA_ARCHIVOS);           
            asignacionValoresRegistroCarga(bean, ruta);
           
            if (guardarAdjunto(bean, ruta)) {
                bean.getObjeto().setId(getFinCargaRemoto().insertar(bean.getObjeto()));
                HiloCargasMasivaFinanciera hilo = new HiloCargasMasivaFinanciera(bean.getObjeto().getId());
                hilo.start();
                bean.addMensaje("Se ha iniciado la creación de la carga masiva de forma exitosa. ");
            }
         
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarCancelacion(FinCargaBean bean) {
        try {
            getFinCargaRemoto().actualizarEstado(bean.getObjeto());
            bean.addMensaje("Se ha iniciado la cancelacion de la carga masiva. ");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void asignacionValoresRegistroCarga(FinCargaBean bean, String ruta) {
        bean.auditoriaGuardar(bean.getObjeto());
        bean.getObjeto().setArchivo(Utilidades.asignarNombreArchivo(bean.getObjeto().getNombre(), NOMBRE_ARCHIVO_CARGA_MASIVA));
        bean.getObjeto().setRespArchivo(Utilidades.asignarNombreArchivo(bean.getObjeto().getNombre(), NOMBRE_ARCHIVO_CARGA_MASIVA_RESP)); 
        bean.getObjeto().setRuta(ruta);
        bean.getObjeto().setExiste(true);
        bean.getObjeto().setRespNombre(bean.getObjeto().getNombre());
        bean.getObjeto().setRespRuta(ruta);
        bean.getObjeto().setRespExiste(false);
        bean.getObjeto().setEstado(0);
        bean.getObjeto().setRegistros(0);
        bean.getObjeto().setExitosos(0);
        bean.getObjeto().setFallidos(0);
        bean.getObjeto().setFechaHoraInicio(new Date());
    }

    
    private void editar(FinCargaBean bean) {
        try {
            bean.setObjeto(getFinCargaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void modificar(FinCargaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getFinCargaRemoto().actualizar(bean.getObjeto());;
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void ver(FinCargaBean bean) {
        try {
            bean.setObjeto(getFinCargaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargasInicial(FinCargaBean bean) {
        try {
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la carga inicial, favor contactar al administrador");
        }
    }

    private void listar(FinCargaBean bean) {
        try {   
            bean.getParamConsulta().setCantidadRegistros(getFinCargaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getFinCargaRemoto().consultarLista(bean.getParamConsulta()));

        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar, favor contactar al administrador");
        }
    }

   
    private boolean guardarAdjunto(FinCargaBean bean, String ruta) {
        boolean hayGuardado = false;
        try { 
            boolean validacion = validarCalidadArchivoTxt(bean);
            if (validacion) {
               return guardarArchivoFisico(ruta,  bean.getObjeto().getArchivo() , bean.getObjeto().getAdjuntoStream());
            }
        } catch (Exception ex) {
            bean.addError("Error guardarAdjunto : " + ex.toString());
            hayGuardado = false;
        }
        return hayGuardado;
    }

 

    private boolean guardarArchivoFisico(String ruta, String nombre, InputStream stream) throws Exception {
        boolean esArchivoGuardado;
        OutputStream destino;
        String rutaCompleta = ruta + nombre;

        File archivo = new File(rutaCompleta);
        destino = new FileOutputStream(archivo);
        IOUtils.copy(stream, destino);
        IOUtils.closeQuietly(stream);
        IOUtils.closeQuietly(destino);
        esArchivoGuardado = true;
        try {
            destino.close();
        } catch (IOException e) {
            esArchivoGuardado = false;
        }

        return esArchivoGuardado;
    }
 
    private boolean validarCalidadArchivoTxt(FinCargaBean bean) {
        boolean archivoValido = true;
        StringBuilder mensaje = new StringBuilder("");
        try {
            InputStream inputEntrada = bean.getObjeto().getAdjuntoStream();
            byte[] buffer = inputEntrada.readAllBytes();
            InputStream inputUtilizar = new ByteArrayInputStream(buffer);
            InputStream inputRemplazar = new ByteArrayInputStream(buffer);
            bean.getObjeto().setAdjuntoStream(inputRemplazar);

            if (inputUtilizar.available() <= 0) {
                bean.addError("En el archivo txt (" + bean.getObjeto().getNombre() + ") no viene contenido.");
                return false;
            }

            try ( BufferedReader lector = new BufferedReader(new InputStreamReader(inputUtilizar))) {
                int contadorLineas = 0;
                String linea;
                while ((linea = lector.readLine()) != null) {
                    contadorLineas++;
                    if (Utilidades.contarColumnas(linea, SEPARADOR_ARCHIVO) != NUMERO_COLUMNAS_ADJUNTO) {
                        mensaje.append("* linea ").append(contadorLineas).append(": la cantidad de campos no corresponde al numero de campos permitidos (").append(NUMERO_COLUMNAS_ADJUNTO).append(") ").append("\n");
                    }
                }

                if (!mensaje.toString().isEmpty()) {              
                    bean.addError( StringUtils.abbreviate(mensaje.toString(), 480) );
                    archivoValido = false;
                }
                
                asignarNumeroRegistrosCarga(bean, contadorLineas);
                
            } catch (IOException e) {
                bean.addError("Ocurrió un error al leer el archivo: " + e.getMessage());
            }

        } catch (IOException ex) {
            bean.addError("El archivo txt (" + bean.getObjeto().getNombre() + ") tiene problemas en su estructura de datos : " + ex.toString());
            archivoValido = false;
        }

        return archivoValido;
    }

    private void asignarNumeroRegistrosCarga(FinCargaBean bean, int contadorLineas) {
        bean.getObjeto().setRegistros(contadorLineas);
    }
}
