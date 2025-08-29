/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.negocio.especial.PeCargaMasivaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.web.especial.bean.GestionCargaMasivaBean;
import com.saviasaludeps.savia.web.especial.utilidades.CargaMasivaProgramaHilo;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Fichero;
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
import java.util.HashMap;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jaime Andres Olarte
 */
public class GestionCargaMasivaImpl extends AccionesBO implements GestionCargaMasivaIface {
    
    private PeCargaMasivaRemoto getPeCargaMasivaRemoto() throws Exception {
        return (PeCargaMasivaRemoto) RemotoEJB.getEJBRemoto(("PeCargaMasivaServicio"), PeCargaMasivaRemoto.class.getName());
    }
    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    @Override
    public void Accion(GestionCargaMasivaBean bean) {
        if (super.ValidarSesion(bean)) {
            bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
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
    public void cargaInicial(GestionCargaMasivaBean bean){
        bean.setListaEstadoCarga(PeConstantes.listaEstadoCargaMasiva());
    }
        
    private void listar(GestionCargaMasivaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPeCargaMasivaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPeCargaMasivaRemoto().consultarLista(bean.getParamConsulta()));
            
            //Programa Especiales
            bean.setListaPePrograma(getPeProgramaRemoto().consultarTodosEstado(PeConstantes.PE_PROGRAMA_ACTIVO));
            bean.setHashPePrograma(new HashMap<>());
            for (PePrograma pePrograma : bean.getListaPePrograma()) {
                bean.getHashPePrograma().put(pePrograma.getId(), pePrograma);
            }
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crear(GestionCargaMasivaBean bean) {
         try {
             bean.setObjeto(new PeCarga());             
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(GestionCargaMasivaBean bean) {
        try {
            String extension;
            Date fecha = new Date();
            String ruta = PropApl.getInstance().get(PropApl.PE_RUTA_CARGA_MASIVA);            
            if(!new File(ruta).exists()){
                bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
            }
            // validamos si el archivo cargado es correcto
            validarArchivo(bean);
            if (!bean.isError()) {
                // actualizamos valores del objeto a guardar
                bean.getObjeto().setRuta(ruta);
                bean.getObjeto().setTipo(PeConstantes.CARGA_MASIVA_AFILIADOS);
                bean.getObjeto().setEstado(PeConstantes.ESTADO_EN_COLA);
                bean.getObjeto().setFechaHoraInicio(fecha);    
                bean.getObjeto().setExiste(true);
                int indiceExtension = bean.getObjeto().getNombre().lastIndexOf(".");
                extension = bean.getObjeto().getNombre().substring(indiceExtension, bean.getObjeto().getNombre().length());
                bean.getObjeto().setArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_CARGA_MASIVA, PeConstantes.formato6.format(fecha),extension));
                //se establecen los datos de archivo de respuesta
                bean.getObjeto().setRespArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_RESPUESTA_CARGA_MASIVA, PeConstantes.formato6.format(fecha),extension));
                bean.getObjeto().setRespNombre(bean.getObjeto().getNombre());
                bean.getObjeto().setRespRuta(ruta);                
                bean.getObjeto().setRespExiste(false);                
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro en PeCarga  
                bean.getObjeto().setId(getPeCargaMasivaRemoto().insertar(bean.getObjeto()));
                //guardamos el archivo en la ruta
                generarArchivo(bean.getObjeto());
                bean.addMensaje("El archivo " + bean.getObjeto().getNombre() + " se cargó con exito, con número de radicado " + bean.getObjeto().getId());                
            } 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean generarArchivo(PeCarga objeto) throws Exception {
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
    
    private void validarArchivo(GestionCargaMasivaBean bean) {
        int i = 0;
        StringBuilder mensaje = new StringBuilder("");  
        int cantidadCampos = (PeConstantes.ENCABEZADO_CARGA_AFILIADOS.split(PeConstantes.COMA).length - 1);
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
                    if (!PeConstantes.ENCABEZADO_CARGA_AFILIADOS.equals(line)) {
                        mensaje.append("* El encabezado del archivo no se encontró o es erróneo.").append(PeConstantes.SALTO_LINEA); 
                        //break;
                    }
                }else if (contarColumnas(line, PeConstantes.COMA) != cantidadCampos) {
                    mensaje.append("* linea ").append((i+1)).append(": la cantidad de campos no corresponde al numero de campos permitidos (").append(cantidadCampos + 1).append(") ").append(PeConstantes.SALTO_LINEA);
                }
                i++;
            }
            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjeto().setRegistros(i-1);
            bean.getObjeto().setExitosos(0);
            bean.getObjeto().setFallidos(0);
            if(!mensaje.toString().isEmpty()){
                bean.addError(mensaje.toString());
            }
        } catch (IOException  e) {
            bean.addError(e.getMessage());
        }
    }
    
    private int contarColumnas(String cadena, String caracter) {        
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
    
    private void abortar(GestionCargaMasivaBean bean) {
        try {
            bean.setObjeto(getPeCargaMasivaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(PeConstantes.ESTADO_CANCELADO);
            bean.getObjeto().setFechaHoraFin(new Date());
            getPeCargaMasivaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se abortó la carga masiva con éxito. Recuerde esperar unos minutos antes de descargar los resultados, debido a que hay procesos internos que están finalizando.");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void descargar(GestionCargaMasivaBean bean) {
        try {
            bean.setObjeto(getPeCargaMasivaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
}
