/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoCarga;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCargaRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoCargaSucesoRemoto;
import com.saviasaludeps.savia.web.contratacion.bean.CargaMasivaContratoBean;
import com.saviasaludeps.savia.web.contratacion.utilidades.ContratacionParametro;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
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
import java.util.Date;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jose Perez
 */
public class CargaMasivaContratoImpl extends AccionesBO implements CargaMasivaContratoIface {
    
    private CntContratoCargaRemoto getCargaMasivaContratoRemoto() throws Exception {
        return (CntContratoCargaRemoto) RemotoEJB.getEJBRemoto(("CntContratoCargaServicio"), CntContratoCargaRemoto.class.getName());
    }
    
    private CntContratoCargaSucesoRemoto getCargaMasivaContratoSucesoRemoto() throws Exception {
        return (CntContratoCargaSucesoRemoto) RemotoEJB.getEJBRemoto(("CntContratoCargaSucesoServicio"), CntContratoCargaSucesoRemoto.class.getName());
    }
    

    @Override
    public void Accion(CargaMasivaContratoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
//                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
//                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
//                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
//                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    descargar(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    abortar(bean);
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(CargaMasivaContratoBean bean) {
        
    }
    
    private void listar(CargaMasivaContratoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCargaMasivaContratoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCargaMasivaContratoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crear(CargaMasivaContratoBean bean) {
         try {
            bean.setObjeto(new CntContratoCarga());           
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(CargaMasivaContratoBean bean) {
        try {
            int cantidad = 0;
            int id = 0;
            int indiceExtension;
            String extension;
            String nombre;
            Date fecha = new Date();
            //PropApl propiedades = new PropApl();
            //CargaMasivaContratoHilo cargaMasivaContratoHilo;
            String ruta = PropApl.getInstance().get(PropApl.CNT_RUTA_CARGA_MASIVA_CONTRATOS);
            //2021-04-21 jyperez Adicionamos validación de nombre de archivo existente
            cantidad = getCargaMasivaContratoRemoto().consultarCantidadCargasArchivoExistente(bean.getObjeto().getNombreArchivo());
            if (cantidad > 0) {
                bean.addError("Ya existe un registro con el mismo nombre de archivo en el sistema. ");
            }
            // validamos si el archivo cargado es correcto
            String mensaje = validarArchivo(bean);
            if (!mensaje.trim().equals("")) {
                bean.addError("Error en formato del archivo: " + mensaje);
            }
            if (!bean.isError()) {
                // actualizamos valores del objeto a guardar
                // el id de radicado es autoincremental, por eso no se asigna valor. El tipo se seleccionó en la lista de la pantalla
                bean.getObjeto().setRuta(ruta);
                // obtenemos la extensión del archivo, y el nombre separado
                indiceExtension = bean.getObjeto().getNombreArchivo().lastIndexOf(".");
                extension = bean.getObjeto().getNombreArchivo().substring(indiceExtension, bean.getObjeto().getNombreArchivo().length());
                nombre = bean.getObjeto().getNombreArchivo().substring(0, indiceExtension);
                // generamos el nombre de archivo
                bean.getObjeto().setArchivo(nombre + "_" + extension);
                
                // guardamos el estado
                //2020-07-17 jyperez los registros, iniciaran en el estado Procesando, debido a que automáticamente quedan
                // trabajando sobre un hilo
                //2024-04-11 jyperez se ajusta para que el estado sea EN COLA y se tome por parametrización
                bean.getObjeto().setEstado(ContratacionParametro.ESTADO_EN_COLA);
                // guardamos la fecha
                //2024-04-11 jyperez no puede ponerse esta campo en nulo porque es requerido en la BD. Este deberia inicializarlo parametrización
                bean.getObjeto().setFechaHoraInicio(fecha);
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro en CntContratoCarga
                id = getCargaMasivaContratoRemoto().insertar(bean.getObjeto());
                bean.getObjeto().setId(id);
                //2024-04-11 jyperez se ajusta para que el estado sea EN COLA y se tome por parametrización
                bean.getObjeto().setEstado(ContratacionParametro.ESTADO_EN_COLA);
                bean.getObjeto().setArchivo(nombre + "_" + id + extension);
                getCargaMasivaContratoRemoto().actualizar(bean.getObjeto());
                //guardamos el archivo en la ruta
                generarArchivo(bean.getObjeto());
                bean.addMensaje("El archivo " + bean.getObjeto().getNombreArchivo() + " se cargó con exito, con número de radicado " + bean.getObjeto().getId());
                // aca se llama al proceso que ejecutará el hilo
                //2021-04-15 jyperez Se cambia el llamado al hilo debido a que, este se hará
                //desde un ear generico.
                //2024-04-11 jyperez se comenta llamado al servicio genérico, ya que las cargas serán tomadas independiente por parametrización
                /*
                if (id != 0) {
                    getGenericoRemoto().CargaMasivaContratos(bean.getObjeto());
                    //el procesamiento del archivo se realiza en el hilo, y se actualiza solo.
                }*/
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean generarArchivo(CntContratoCarga objeto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(objeto.getRuta(), objeto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(objeto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(objeto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
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
    
    
    private String validarArchivo(CargaMasivaContratoBean bean) {
        int i = 0;
        String mensaje = "";
        String encabezado = ContratacionParametro.ENCABEZADO_VALIDACION_ARCHIVO_CONTRATO_DETALLE;
        int cantidadCampos = ContratacionParametro.CANTIDAD_CAMPOS_ARCHIVO_CONTRATO_DETALLE;//realmente es la cantidad de comas a contar.
        InputStream aux;
        BufferedReader br;
        InputStream copia;
        // variables para clonar el inputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        String line;
        try {
            //actualizamos las variables encabezado y cantidad dependiendo del archivo a crear. por defecto tiene el de contrato detalle.
            switch(bean.getObjeto().getTipo()) {
                case CntContratoCarga.TIPO_NOTA_TECNICA:
                    encabezado = ContratacionParametro.ENCABEZADO_VALIDACION_ARCHIVO_NOTA_TECNICA;
                    cantidadCampos = ContratacionParametro.CANTIDAD_CAMPOS_NOTA_TECNICA;
                break;
                case CntContratoCarga.TIPO_COBERTURA:
                    encabezado = ContratacionParametro.ENCABEZADO_VALIDACION_ARCHIVO_COBERTURA;
                    cantidadCampos = ContratacionParametro.CANTIDAD_CAMPOS_COBERTURA;
                break;
                case CntContratoCarga.TIPO_MARCACION:
                    encabezado = ContratacionParametro.ENCABEZADO_VALIDACION_ARCHIVO_MARCACION;
                    cantidadCampos = ContratacionParametro.CANTIDAD_CAMPOS_MARCACION;
                break;
                case CntContratoCarga.TIPO_REPS_PRESTADORES:
                    encabezado = ContratacionParametro.ENCABEZADO_VALIDACION_ARCHIVO_REPS_PRESTADORES;
                    cantidadCampos = ContratacionParametro.CANTIDAD_CAMPOS_REPS_PRESTADORES;
                break;
            }
            aux = bean.getObjeto().getAdjuntoStream();
            while ((len = aux.read(buffer)) > -1 ) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            copia = new ByteArrayInputStream(baos.toByteArray());
            //hacemos copia nuevamente en el inputStream que clonamos
            bean.getObjeto().setAdjuntoStream(new ByteArrayInputStream(baos.toByteArray()));
            
            br = new BufferedReader(new InputStreamReader(copia));

            while ((line = br.readLine()) != null) {
                // validamos el encabezado
                if( i == 0) {
                    if (!encabezado.equals(line)) {
                        mensaje = "*El encabezado del archivo no se encontró o es erróneo";
                        //break;
                    }
                }
                if (contarCaracteres(line, ',') != cantidadCampos) {
                    mensaje = mensaje + "*linea " + (i+1) +  ": la cantidad de campos no corresponde a " + (cantidadCampos+1);
                }
                i++;
            }
            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjeto().setRegistrosTotal(i-1);
            bean.getObjeto().setRegistrosExitosos(0);
            bean.getObjeto().setRegistrosRechazados(0);
        } catch (IOException  e) {
            bean.addError(e.getMessage());
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
    
    
    private void abortar(CargaMasivaContratoBean bean) {
        try {
            bean.setObjeto(getCargaMasivaContratoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(ContratacionParametro.ESTADO_ABORTADO);
            bean.getObjeto().setFechaHoraFin(new Date());
            getCargaMasivaContratoRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se abortó la carga masiva con éxito. Recuerde esperar unos minutos antes de descargar los resultados, debido a que hay procesos internos que están finalizando.");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void descargar(CargaMasivaContratoBean bean) {
        try {
            bean.setObjeto(getCargaMasivaContratoRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaDetalleCarga(getCargaMasivaContratoSucesoRemoto().consultarPorContratoCarga(bean.getObjeto().getId()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
}
