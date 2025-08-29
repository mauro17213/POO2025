
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.especial.PeCargaGestion;
import com.saviasaludeps.savia.negocio.especial.PeCargaGestionRemoto;
import com.saviasaludeps.savia.web.especial.bean.CargaMasivaGestionBean;
import com.saviasaludeps.savia.web.especial.bean.GestionCargaMasivaBean;
import com.saviasaludeps.savia.web.especial.utilidades.CargaMasivaGestionHilo;
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
import org.apache.commons.io.IOUtils;

/**
 *
 * @author idbohorquez
 */
public class CargaMasivaGestionServicioImpl extends AccionesBO implements CargaMasivaGestionServicioIface {

    private PeCargaGestionRemoto getPeCargaGestionRemoto() throws Exception {
        return (PeCargaGestionRemoto) RemotoEJB.getEJBRemoto(("PeCargaGestionServicio"), PeCargaGestionRemoto.class.getName());
    }

    @Override
    public void Accion(CargaMasivaGestionBean bean) {
        if (super.ValidarSesion(bean)) {
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
    public void cargaInicial(CargaMasivaGestionBean bean){
        bean.setListaEstadoCarga(PeConstantes.listaEstadoCargaMasiva());
    }
    private void listar(CargaMasivaGestionBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPeCargaGestionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPeCargaGestionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(CargaMasivaGestionBean bean) {
        try {
            bean.setObjeto(new PeCargaGestion());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(CargaMasivaGestionBean bean) {
        try {
            //int id = 0;
            int indiceExtension;
            String extension;
            String nombre;
            Date fecha = new Date();
            String ruta = PropApl.getInstance().get(PropApl.PE_RUTA_CARGA_MASIVA);
            //Validamos que la rita para guardar el archivo exista y sea valida
            if(!new File(ruta).exists()){
                bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
            }
            
            // validamos si el archivo cargado es correcto
            validarArchivo(bean);
            
            if (!bean.isError()) {
                // actualizamos valores del objeto a guardar
                // el id de radicado es autoincremental, por eso no se asigna valor. El tipo se seleccionó en la lista de la pantalla
                bean.getObjeto().setRuta(ruta);
                bean.getObjeto().setEstado(PeConstantes.ESTADO_EN_COLA);
                bean.getObjeto().setFechaHoraInicio(fecha);
                bean.getObjeto().setUsuario(new Usuario(bean.getConexion().getUsuario().getId()));
                // obtenemos la extensión del archivo, y el nombre separado
                indiceExtension = bean.getObjeto().getNombre().lastIndexOf(".");
                extension = bean.getObjeto().getNombre().substring(indiceExtension, bean.getObjeto().getNombre().length());
                // generamos el nombre de archivo
                bean.getObjeto().setArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_CARGA_GESTIONES, PeConstantes.formato6.format(fecha),extension));
                bean.getObjeto().setExiste(true);
                //se establecen los datos de archivo de respuesta
                bean.getObjeto().setRespArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_RESPUESTA_CARGA_GESTIONES, PeConstantes.formato6.format(fecha),extension));
                bean.getObjeto().setRespNombre(bean.getObjeto().getNombre());
                bean.getObjeto().setRespRuta(ruta);                
                bean.getObjeto().setRespExiste(false); 
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getObjeto());                
                //guardamos el registro en PeCarga
                bean.getObjeto().setId(getPeCargaGestionRemoto().insertar(bean.getObjeto()));                
                //guardamos el archivo en la ruta
                generarArchivo(bean.getObjeto());
                bean.addMensaje("El archivo " + bean.getObjeto().getNombre() + " se cargó con exito, con número de radicado " + bean.getObjeto().getId());                
            } 
        } catch (Exception e) {
            bean.addError("Se presento inconveninete al guardar el archivo de carga, intentelo nuevamente.");
        }
    }

    private boolean generarArchivo(PeCargaGestion objeto) throws Exception {
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

    private void validarArchivo(CargaMasivaGestionBean bean) {
        StringBuilder mensaje = new StringBuilder("");  
        int i = 0;
        int cantidadCampos = PeConstantes.ENCABEZADO_CARGA_GESTIONES.split(String.format("\\%s", PeConstantes.PIPE)).length;
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
                    if (!PeConstantes.ENCABEZADO_CARGA_GESTIONES.equals(line)) {
                        mensaje.append("* El encabezado del archivo no se encontró o es erróneo.").append(PeConstantes.SALTO_LINEA); 
                    }
                }else if (contarCaracteres(line, PeConstantes.PIPE) != cantidadCampos) {
                    mensaje.append("* linea ").append((i+1)).append(": la cantidad de campos no corresponde al numero de campos permitidos (").append(cantidadCampos).append(") ").append(PeConstantes.SALTO_LINEA);
                }
                i++;
            }
            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjeto().setRegistros(i - 1);
            bean.getObjeto().setExitosos(0);
            bean.getObjeto().setFallidos(0);
            if(!mensaje.toString().isEmpty()){
                bean.addError(mensaje.toString());
            }
            if(bean.getObjeto().getRegistros()<10){
                bean.addError("Error en formato del archivo: La cantidad minima de registros para carga masiva es 10, si va cargar menos registros debe realizarlo por aplicación.");
            }          
        } catch (IOException e) {
            bean.addError(e.getMessage());
        }
    }

    private int contarCaracteres(String cadena, String caracter) {
        return cadena.split(String.format("\\%s", caracter)).length;
    }

    private void abortar(CargaMasivaGestionBean bean) {
        try {
            bean.setObjeto(getPeCargaGestionRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(PeConstantes.ESTADO_CANCELADO);
            bean.getObjeto().setFechaHoraFin(new Date());
            getPeCargaGestionRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se abortó la carga masiva con éxito. Recuerde esperar unos minutos antes de descargar los resultados, debido a que hay procesos internos que están finalizando.");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void descargar(CargaMasivaGestionBean bean) {
        try {
            bean.setObjeto(getPeCargaGestionRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

}
