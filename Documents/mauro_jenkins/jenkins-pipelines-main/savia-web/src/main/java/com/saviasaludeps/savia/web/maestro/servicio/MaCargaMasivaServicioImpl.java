/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.maestro.MaCarga;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.web.maestro.bean.MaCargaMasivaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import com.saviasaludeps.savia.negocio.maestro.MaCargaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaDetalleCargaRemoto;
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
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.web.maestro.utilidades.MaestroParametro;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaCargaMasivaServicioImpl extends AccionesBO implements MaCargaMasivaServicioIface {

    private MaCargaRemoto getMaCargaRemoto() throws Exception {
        return (MaCargaRemoto) RemotoEJB.getEJBRemoto(("MaCargaServicio"), MaCargaRemoto.class.getName());
    }
    
    private MaDetalleCargaRemoto getDetalleCargaRemoto() throws Exception {
        return (MaDetalleCargaRemoto) RemotoEJB.getEJBRemoto(("MaDetalleCargaServicio"), MaDetalleCargaRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }
    

    @Override
    public void Accion(MaCargaMasivaBean bean) {
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

    private void listar(MaCargaMasivaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaCargaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMaCargaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public List<CntPrestadorSede> listarSedesPorMunicipio(String divipoliMunicipio) throws Exception{
        return getPrestadoresRemoto().consultarListaSedes(divipoliMunicipio);
    }


    private void ver(MaCargaMasivaBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(MaCargaMasivaBean bean) {
        try {
            bean.setObjeto(new MaCarga());
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(MaCargaMasivaBean bean) {
        try {
            int id = 0;
            int indiceExtension;
            String extension;
            String nombre;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date fecha = new Date();
            //PropApl propiedades = new PropApl();
            //CargaMasivaHilo cargaMasivaHilo;
            String ruta="";
            switch(bean.getObjeto().getTipo()) {
                case MaestroParametro.MAMEDICAMENTOS:
                case MaestroParametro.MAINSUMOS:
                case MaestroParametro.MATECNOLOGIAS:
                case MaestroParametro.MAPAQUETES:
                case MaestroParametro.MAESPECIALIDADES:
                case MaestroParametro.MADIAGNOSTICOS:
                case MaestroParametro.MASERVICIOSHABILITACION:
                case MaestroParametro.MARELACIONINSUMOSMIPRES:
                    ruta = PropApl.getInstance().get(PropApl.MA_RUTA_CARGA_MAESTROS);
                break;
            }
            // validamos si el archivo cargado es correcto
            String mensaje = validarArchivo(bean);
            if (mensaje.trim().equals("")) {
                // actualizamos valores del objeto a guardar
                // el id de radicado es autoincremental, por eso no se asigna valor. El tipo se seleccionó en la lista de la pantalla
                bean.getObjeto().setRuta(ruta);
                // obtenemos la extensión del archivo, y el nombre separado 
                indiceExtension = bean.getObjeto().getNombre().lastIndexOf(".");
                extension = bean.getObjeto().getNombre().substring(indiceExtension, bean.getObjeto().getNombre().length());
                nombre = bean.getObjeto().getNombre().substring(0, indiceExtension);
                // generamos el nombre de archivo
                bean.getObjeto().setArchivo(nombre + "_" + sdf.format(fecha) + extension);
                //2025-02-21 jyperez se actualizan los nuevos campos para carga masiva
                bean.getObjeto().setExiste(true);
                // guardamos el estado
                //2020-07-17 jyperez los registros, iniciaran en el estado Procesando, debido a que automáticamente quedan
                // trabajando sobre un hilo
                //2021-05-03 jyperez se ajusta para que el estado inicial sea en cola, por cambios en la forma de ejecución
                //2024-04-11 jyperez nuevamente se actualiza a estado en cola para ser consumidos por parametrización
                bean.getObjeto().setEstado(MaestroParametro.ESTADO_EN_COLA);
                // guardamos la fecha
                //bean.getObjeto().setFechaHoraInicio(fecha);
                //guardamos el archivo en la ruta
                generarArchivo(bean);
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getObjeto());
                //generamos la auditoria para el objeto modificado
                bean.auditoriaModificar(bean.getObjeto());
                //2025-03-03 jyperez actualizamos el nombre del archivo
                bean.getObjeto().setNombre(bean.getObjeto().getArchivo());
                //guardamos el registro en maCarga
                id = getMaCargaRemoto().insertar(bean.getObjeto());
                bean.getObjeto().setId(id);
                bean.addMensaje("El archivo " + bean.getObjeto().getNombre() + " se cargó con exito, con número de radicado " + bean.getObjeto().getId());
                //2024-04-11 jyperez se comenta llamado al servicio genérico, ya que las cargas serán tomadas independiente por parametrización
                /*if (id != 0) {
                    getGenericoRemoto().CargaMasivaMaestros(bean.getObjeto());
                    //el procesamiento del archivo se realiza en el hilo, y s|e actualiza solo.
                }*/
            } else {
                bean.addError("Error en formato del archivo: " + mensaje);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(MaCargaMasivaBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(MaCargaMasivaBean bean) {
        try {
            
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(MaCargaMasivaBean bean) {
        try {
            
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(MaCargaMasivaBean bean) {
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
    public void cargaInicial(MaCargaMasivaBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean generarArchivo(MaCargaMasivaBean bean) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(bean.getObjeto().getRuta(), bean.getObjeto().getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(bean.getObjeto().getAdjuntoStream(), destino);
            IOUtils.closeQuietly(bean.getObjeto().getAdjuntoStream());
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
                Logger.getLogger(MaCargaMasivaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    private String validarArchivo(MaCargaMasivaBean bean) {
        int i = 0;
        String mensaje = "";
        String encabezado = MaestroParametro.ENCABEZADO_VALIDACION_ARCHIVO_MEDICAMENTOS;
        int cantidadCampos = MaestroParametro.CANTIDAD_CAMPOS_ARCHIVO_MEDICAMENTOS;
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
            //actualizamos las variables encabezado y cantidad dependiendo del archivo a crear. por defecto tienen los de medicamentos.
            switch(bean.getObjeto().getTipo()) {
                case MaestroParametro.MAINSUMOS:
                    encabezado = MaestroParametro.ENCABEZADO_VALIDACION_ARCHIVO_INSUMOS;
                    cantidadCampos = MaestroParametro.CANTIDAD_CAMPOS_ARCHIVO_INSUMOS;
                break;
                case MaestroParametro.MATECNOLOGIAS:
                    encabezado = MaestroParametro.ENCABEZADO_VALIDACION_ARCHIVO_TECNOLOGIAS;
                    cantidadCampos = MaestroParametro.CANTIDAD_CAMPOS_ARCHIVO_TECNOLOGIAS;
                break;
                case MaestroParametro.MAPAQUETES:
                    encabezado = MaestroParametro.ENCABEZADO_VALIDACION_ARCHIVO_PAQUETES;
                    cantidadCampos = MaestroParametro.CANTIDAD_CAMPOS_ARCHIVO_PAQUETES;
                break;
                case MaestroParametro.MAESPECIALIDADES:
                    encabezado = MaestroParametro.ENCABEZADO_VALIDACION_ARCHIVO_ESPECIALIDADES;
                    cantidadCampos = MaestroParametro.CANTIDAD_CAMPOS_ARCHIVO_ESPECIALIDADES;
                break;
                case MaestroParametro.MADIAGNOSTICOS:
                    encabezado = MaestroParametro.ENCABEZADO_VALIDACION_ARCHIVO_DIAGNOSTICOS;
                    cantidadCampos = MaestroParametro.CANTIDAD_CAMPOS_ARCHIVO_DIAGNOSTICOS;
                break;
                case MaestroParametro.MASERVICIOSHABILITACION:
                    encabezado = MaestroParametro.ENCABEZADO_VALIDACION_ARCHIVO_SERV_HABILITACION;
                    cantidadCampos = MaestroParametro.CANTIDAD_CAMPOS_ARCHIVO_SERV_HABILITACION;
                break;
                case MaestroParametro.MARELACIONINSUMOSMIPRES:
                    encabezado = MaestroParametro.ENCABEZADO_VALIDACION_ARCHIVO_RE_INSUMOS_MIPRES;
                    cantidadCampos = MaestroParametro.CANTIDAD_CAMPOS_ARCHIVO_RE_INSUMOS_MIPRES;
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
            // validamos los valores de cantidad maxima y encabezado según el tipo de archivo. Inicialmente se cargan para tipo Afiliado
            
            
            br = new BufferedReader(new InputStreamReader(copia));

            while ((line = br.readLine()) != null) {
                // validamos el encabezado
                if( i == 0) {
                    if (!encabezado.equals(line)) {
                        mensaje = "*El encabezado del archivo no se encontró o es erróneo";
                        //break;
                    }
                }
                // contar los campos dividos por coma
                 //campos = line.split(",");
                // contarCaracteres 
                //if (campos.length != cantidadCampos) {
                if (contarCaracteres(line, ',') != cantidadCampos) {
                    mensaje = mensaje + "*linea " + (i+1) +  ": la cantidad de campos no corresponde a " + (cantidadCampos+1);
                    //break;
                }
                i++;
            }
            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjeto().setRegistros(i-1);
            bean.getObjeto().setExitosos(0);
            bean.getObjeto().setFallidos(0);
        } catch (IOException  ex) {
            Logger.getLogger(MaCargaMasivaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

    private void cargarListaDetalleCarga(MaCargaMasivaBean bean) {
        try {
            bean.setObjeto(getMaCargaRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaDetalleCarga(getDetalleCargaRemoto().consultarPorRadicado(bean.getObjeto().getId()));
        } catch (Exception ex) {
            Logger.getLogger(MaCargaMasivaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
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

    private void abortar(MaCargaMasivaBean bean) {
        try {
            bean.setObjeto(getMaCargaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(MaestroParametro.ESTADO_ABORTADO);
            //2020-07-21 jyperez adicionamos los valores de registros solicitados en el incidente mantis 256
            bean.getObjeto().setExitosos(getMaCargaRemoto().consultarCantidadRegistrosProcesadosPorEstado(bean.getObjeto().getId(), MaestroParametro.ESTADO_DETALLE_CARGA_INGRESADO));
            bean.getObjeto().setFallidos(getMaCargaRemoto().consultarCantidadRegistrosProcesadosPorEstado(bean.getObjeto().getId(), MaestroParametro.ESTADO_DETALLE_CARGA_FALLIDO));            
            //generamos la auditoria para el objeto modificado
            bean.auditoriaModificar(bean.getObjeto());
            bean.getObjeto().setFechaHoraFin(new Date());
            getMaCargaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se abortó la carga masiva con éxito. Recuerde esperar unos minutos antes de descargar los resultados, debido a que hay procesos internos que están finalizando.");
        } catch (Exception ex) {
            Logger.getLogger(MaCargaMasivaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            bean.addError(ex.getMessage());
        }
    }

}
