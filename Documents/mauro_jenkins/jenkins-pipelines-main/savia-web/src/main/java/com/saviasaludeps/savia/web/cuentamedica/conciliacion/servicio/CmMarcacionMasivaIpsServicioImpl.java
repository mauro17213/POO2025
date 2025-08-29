/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmMarcacionMasivaIps;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmMarcacionMasivaIpsRemoto;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmMarcacionMasivaIpsBean;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Jorge Perez
 */
public class CmMarcacionMasivaIpsServicioImpl extends AccionesBO implements CmMarcacionMasivaIpsServicioIface {
    
    public final int INDICE_COLUMNA_NIT = 0;
    public final int INDICE_COLUMNA_NUMERO_FACTURADO = 1;
    public final int INDICE_COLUMNA_NUMERO_RADICADO  = 2;
    public final int INDICE_COLUMNA_RESPUESTA_IPS = 3;
    public final int CANTIDAD_CAMPOS_LINEA = 4;
    public final int ESTADO_REGISTRO_NO_EXISTE = 0;
    public final int ESTADO_REGISTRO_NO_AUDITADO_GLOSADO = 1;
    public final int ESTADO_NIT_NO_VALIDO  = 2;
    public final int ESTADO_REGISTRO_VALIDO = 3;
    
    Map<String, CmFactura> hashFacturasDeArchivo ;
    
    private CmMarcacionMasivaIpsRemoto getCmMarcacionMasivaIpsRemoto() throws Exception {
        return (CmMarcacionMasivaIpsRemoto) RemotoEJB.getEJBRemoto("CmMarcacionMasivaIpsServicio", CmMarcacionMasivaIpsRemoto.class.getName());
    }
   
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }


    public Map<String, CmFactura> getHashFacturasDeArchivo() {
        if (hashFacturasDeArchivo == null) {
            hashFacturasDeArchivo = new HashMap<>();
        }
        return hashFacturasDeArchivo;
    }

    public void setHashFacturasDeArchivo(Map<String, CmFactura> hashFacturasDeArchivo) {
        this.hashFacturasDeArchivo = hashFacturasDeArchivo;
    }
    
    
    
    @Override
    public void Accion(CmMarcacionMasivaIpsBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CmMarcacionMasivaIpsBean.ACCION_VER_CM_MARCADO_IPS_MASIVO:
                            ver(bean);
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    guardarMarcacionMasivaIps(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(CmMarcacionMasivaIpsBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    
    private boolean guardarMarcacionMasivaIps(CmMarcacionMasivaIpsBean bean) {
        boolean esValido = true;
        try {
            setHashFacturasDeArchivo(new HashMap<>());
            byte[] buffer = bean.getObjeto().getContenidoArchivo().readAllBytes(); 
            InputStream newInput = new ByteArrayInputStream(buffer);
            InputStream streamEstructura = new ByteArrayInputStream(buffer);
            InputStream streamPerfil = new ByteArrayInputStream(buffer);
            InputStream streamCalidadDato = new ByteArrayInputStream(buffer);
           
            bean.getObjeto().setContenidoArchivo(newInput);
            
            esValido = validarEstructuraArchivo(streamEstructura, bean);
            esValido = esValido ? validarNitPorPerfilUsuario(streamPerfil, bean) : esValido;
            esValido = esValido ? validarCalidadDatoDB(streamCalidadDato, bean) : esValido;
            esValido = esValido ? guardarArchivoMarcacionIpsMasiva(bean) : esValido;
            esValido = esValido ? guardarMarcacionIpsMasivaEnDB(bean) : esValido;
            esValido = esValido ? guardarMarcacionEnCmFactura(bean, getHashFacturasDeArchivo()) : esValido;
            esValido = esValido ? actualizarMarcacionFinProcesoDB(bean) : esValido;         
        } catch (Exception e) {
            esValido = false;
            bean.getObjeto().setHayErroresEnMarcacion(true);
            bean.getObjeto().setMensajeError(new StringBuilder().
                    append("Error al marcar el archivo:(").
                    append(bean.getObjeto().getNombreArchivo()).
                    append("):").
                    append(e.toString()));
        }
        return esValido;
    }
    
    private boolean validarNitPorPerfilUsuario(InputStream contenido, CmMarcacionMasivaIpsBean bean) {
        boolean esValido = true;
        InputStream is;
        BufferedReader br = null;
        String linea;
        String[] camposArchivo;
        StringBuilder builderMensajeError = new StringBuilder();
        Set<String> hashNitArchivo = new HashSet<>();

        try {
            is = contenido;
            br = new BufferedReader(new InputStreamReader(is));
            while ((linea = br.readLine()) != null) {
                if (!"".equals(linea)) {
                    camposArchivo = linea.split(",");
                    String nit = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NIT]).orElse("")).trim();
                    hashNitArchivo.add(nit);
                }
            }

            if (hashNitArchivo.size() > 1 && !bean.getConexion().getEmpresa().isAdministradora()) {
                builderMensajeError.append(" - El perfil de usuario no permite la marcación de facturas de diferente nit :(");
                builderMensajeError.append(hashNitArchivo.toString());
                builderMensajeError.append(" )");
                bean.getObjeto().setHayErroresEnMarcacion(true);
                bean.getObjeto().setMensajeError(builderMensajeError);
                esValido = false;
            }

        } catch (IOException e) {
            esValido = false;
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                bean.addError("Error en función validarEstructuraArchivoConciliar " + ioe.getMessage());
                esValido = false;
            }
        }

        return esValido;
    }
    
    
    private boolean validarEstructuraArchivo(InputStream contenido, CmMarcacionMasivaIpsBean bean) {
        boolean esValido = true;
        BufferedReader br = null;
        InputStream is;
        String linea = "";
        String[] camposArchivo;
        StringBuilder builderMensajeError = new StringBuilder();
        Map<String, String> hashRegistrosEncontrados = new HashMap<>();
                
        int numeroLinea = 0;
        try {
            is = contenido;
            br = new BufferedReader(new InputStreamReader(is));
            while ((linea = br.readLine()) != null) {
     
                StringBuilder builderErrorPorFactura = new StringBuilder();
                if (!"".equals(linea)) {
                    camposArchivo = linea.split(",");
                    validarCantidadColumnas(camposArchivo, builderErrorPorFactura);
                    validarCampoNoVacio(camposArchivo, builderErrorPorFactura);
                    validarCampoRespuestaIPS(camposArchivo, builderErrorPorFactura);
                    validarCampoNumeroRadicado(camposArchivo, builderErrorPorFactura);
                    validarRegistrosUnicos(camposArchivo, builderErrorPorFactura, hashRegistrosEncontrados);
                }
                numeroLinea++;
                
                formarRegistroError(builderErrorPorFactura, numeroLinea, builderMensajeError);
            }

            validarExistenciaContenidoArchivo(numeroLinea, builderMensajeError, bean);

            esValido = asignarErroresParaSerMostrados(builderMensajeError, bean);
            
        } catch (IOException e) {
            esValido = false;
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                bean.addError("Error en función validarEstructuraArchivoConciliar " + ioe.getMessage());
                esValido = false;
            }
        }

        return esValido;
    }
    
    private boolean validarCalidadDatoDB(InputStream contenido, CmMarcacionMasivaIpsBean bean) throws Exception {
        boolean esValido = true;
        InputStream is;
        BufferedReader br = null;
        String linea;
        String[] camposArchivo;
        int numeroLineas = 0;
       
        try {
            is = contenido;
            br = new BufferedReader(new InputStreamReader(is));
            Set<String> numerosRadicados = new HashSet<>();
            List<String> numerosFacturas = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                 numeroLineas++;
                if (!"".equals(linea)) {                   
                    CmFactura cmFactura = new CmFactura();
                    camposArchivo = linea.split(",");
                    String nit = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NIT]).orElse("")).trim();
                    String numeroFactura = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NUMERO_FACTURADO]).orElse("")).trim();
                    String respuestaIps = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_RESPUESTA_IPS]).orElse("")).trim();
                    String numeroRadicado = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NUMERO_RADICADO]).orElse("0")).trim();
                    numerosRadicados.add(numeroRadicado);
                    numerosFacturas.add("'" + numeroFactura + "'");

                    cmFactura.setNit(nit);
                    cmFactura.setNumeroFacturado(numeroFactura);
                    cmFactura.setRespuestaIps(respuestaIps.equals("1"));
                    cmFactura.setNumeroRadicado(Integer.parseInt(numeroRadicado));
                    cmFactura.setMarcacion(ESTADO_REGISTRO_NO_EXISTE);
                    cmFactura.setNumeroLineaArchivo(numeroLineas);
                    getHashFacturasDeArchivo().put(numeroRadicado + "-" + numeroFactura, cmFactura);
                }
            }

            bean.getObjeto().setRegistros(getHashFacturasDeArchivo().size());
            List<CmFactura> facturasObtenidas = consultarCmFacturas(numerosFacturas, numerosRadicados);
            esValido = validarExistenciaEstado(facturasObtenidas, bean);

        } catch (IOException e) {
            esValido = false;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                bean.addError("Error en función validarEstructuraArchivoConciliar " + ioe.getMessage());
                esValido = false;
            }
        }

        return esValido;
    }

    private boolean validarExistenciaEstado(  List<CmFactura> facturasObtenidas, CmMarcacionMasivaIpsBean bean) throws Exception {
        boolean esValido = false;
        StringBuilder builderMensajeError = new StringBuilder();
        
        if (facturasObtenidas.size() > 0) {
            for (CmFactura facturaObtenida : facturasObtenidas) {
                
                String llave = facturaObtenida.getNumeroRadicado() + "-" + facturaObtenida.getNumeroFacturado();
                if (getHashFacturasDeArchivo().get(llave) != null) {
                    CmFactura factura = getHashFacturasDeArchivo().get(llave);   
                    factura.setId(facturaObtenida.getId());
                    factura.setEstadoFactura(facturaObtenida.getEstadoFactura());
                    factura.setMarcacion(ESTADO_REGISTRO_VALIDO);
                    if (facturaObtenida.getEstadoFactura() != CmFactura.ESTADO_FACTURA_GLOSADA) {
                        factura.setMarcacion(ESTADO_REGISTRO_NO_AUDITADO_GLOSADO);
                    }  
                    if(!facturaObtenida.getNit().equals(factura.getNit().trim())){
                        factura.setMarcacion(ESTADO_NIT_NO_VALIDO);
                    }
                }
            }
                  
        }

        for (Map.Entry<String, CmFactura> entry : hashFacturasDeArchivo.entrySet()) {
            StringBuilder builderErrorPorFactura = new StringBuilder();
            CmFactura facturaArchivo = entry.getValue();
            boolean existeError = false;
            String mensajeError = "";
            switch (facturaArchivo.getMarcacion()) {
                case ESTADO_REGISTRO_NO_EXISTE:
                    existeError = true;
                    mensajeError = ". No se encuentra en el sistema.";
                    break;
                case ESTADO_REGISTRO_NO_AUDITADO_GLOSADO:
                    existeError = true;
                    mensajeError = ". No tienen el estado Auditado Glosado," + " el estado encontrado fue:" + facturaArchivo.getEstadoFacturaStr();
                    break;
                case ESTADO_NIT_NO_VALIDO:
                    existeError = true;
                    mensajeError = ". El número de nit encontrado en el archivo no es válido.";
                    break;
            }
            
            if(existeError){
                formarMensajeValidacionFacturaGenerico(builderErrorPorFactura, facturaArchivo, mensajeError);
                formarRegistroError(builderErrorPorFactura, facturaArchivo.getNumeroLineaArchivo(), builderMensajeError);
            }
        }

        esValido = asignarErroresParaSerMostrados(builderMensajeError, bean);
        
        return esValido;
    }

    private List<CmFactura> consultarCmFacturas(List<String> numerosFacturas, Set<String> numerosRadicados) throws Exception {
        List<CmFactura> facturasObtenidas;
        try {
            String numerosFacturadosAverificar = String.join(",", numerosFacturas);
            String numerosRadicadosAverificar = String.join(",", numerosRadicados);
            facturasObtenidas = getCmMarcacionMasivaIpsRemoto().consultarParaMarcacionIpsMasiva(numerosFacturadosAverificar, numerosRadicadosAverificar);
        } catch (Exception e) {
            facturasObtenidas = new ArrayList<>();
        }
        return facturasObtenidas;
    }

    private void formarMensajeValidacionFacturaGenerico(StringBuilder builderMensajeError, CmFactura factura, String Mensaje) {
        builderMensajeError.append(" - La factura de número facturado:( ");
        builderMensajeError.append(factura.getNumeroFacturado());
        builderMensajeError.append(" ) y número radicado ( ");
        builderMensajeError.append(factura.getNumeroRadicado());
        builderMensajeError.append(" )");
        builderMensajeError.append(Mensaje);
    }
    
    private boolean validarExistenciaContenidoArchivo(int numeroLinea, StringBuilder builderMensajeError, CmMarcacionMasivaIpsBean bean) {
        boolean esValido = true;
        if(numeroLinea==0){
            builderMensajeError.append("El archivo ");
            builderMensajeError.append(bean.getObjeto().getNombreArchivo());
            builderMensajeError.append(" no presenta datos o contenido que pueda ser procesado.");
            esValido = false;
        }
        return esValido;
    }
    
    private boolean validarCantidadColumnas(String[] camposArchivo, StringBuilder builderMensajeError) {
        boolean esValido = true;
     
        if (CANTIDAD_CAMPOS_LINEA != camposArchivo.length) {
            builderMensajeError.append(" - La cantidad de columnas o datos no es igual a ");
            builderMensajeError.append(CANTIDAD_CAMPOS_LINEA);
            builderMensajeError.append(" , además todos las columnas deben tener un valor sepadado por coma(',')");
            esValido = false;
        }
        return esValido;
    }

    private boolean validarCampoRespuestaIPS(String[] camposArchivo, StringBuilder builderMensajeError) {
        boolean esValido = true;
        if (CANTIDAD_CAMPOS_LINEA == camposArchivo.length) {
            String respuestaIps = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_RESPUESTA_IPS]).orElse("")).trim();
            if (validarDatoTipoEntero(respuestaIps, builderMensajeError, "Respuesta IPS")) {
                int valor = Integer.parseInt(respuestaIps);
                if (valor != 0 && valor != 1) {
                    builderMensajeError.append(" - El campo Respueta IPS debe ser : (");
                    builderMensajeError.append(" 0: sin respuesta IPS , 1 con respuesta IPS )");
                    builderMensajeError.append(", valor encontrado : ");
                    builderMensajeError.append(valor);
                    esValido = false;
                }
            } else {
                esValido = false;
            }
        }
        return esValido;
    }
    
    private boolean validarCampoNumeroRadicado(String[] camposArchivo, StringBuilder builderErrorPorFactura) {
        boolean esValido = true;
        if (CANTIDAD_CAMPOS_LINEA == camposArchivo.length) {
           validarDatoTipoEntero(camposArchivo[INDICE_COLUMNA_NUMERO_RADICADO], builderErrorPorFactura, "Número Radicado");
        }
        return esValido;
    }
    
    private boolean validarRegistrosUnicos(String[] camposArchivo, StringBuilder builderErrorPorFactura, Map<String, String> hashRegistrosEncontrados ) {
        boolean esValido = true;
        
        if (CANTIDAD_CAMPOS_LINEA == camposArchivo.length) {
            String llave ;
            String nit = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NIT]).orElse("")).trim();
            String numeroFactura = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NUMERO_FACTURADO]).orElse("")).trim();
            String numeroRadicado = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NUMERO_RADICADO]).orElse("0")).trim();
            llave = nit+"-"+numeroFactura+"-"+numeroRadicado;
            
            if (hashRegistrosEncontrados.get(llave) != null) {
                builderErrorPorFactura.append(" - Se presenta un error de registro repetido ( ");
                builderErrorPorFactura.append("Nit : ");
                builderErrorPorFactura.append(nit);
                builderErrorPorFactura.append(", Número Facturado :");
                builderErrorPorFactura.append(numeroFactura);
                builderErrorPorFactura.append(", Número Radicado :");
                builderErrorPorFactura.append(numeroRadicado);
                builderErrorPorFactura.append(" ).");
                esValido = false;
            }else{
                hashRegistrosEncontrados.put(llave, "registro");
            }
        }
        return esValido;
    }
     
    private boolean validarCampoNoVacio(String[] camposArchivo, StringBuilder builderMensajeError) {
        boolean esValido = true;
        String camposVacios = "";
        if (CANTIDAD_CAMPOS_LINEA == camposArchivo.length) {
            String nit = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NIT]).orElse("")).trim();
            if ("".equals(nit)) {
                camposVacios += " Nit,";
            }
            String numeroFacturado = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NUMERO_FACTURADO]).orElse("")).trim();
            if ("".equals(numeroFacturado)) {
                camposVacios += " Número Facturado,";
            }
            String numeroRadicado = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_NUMERO_RADICADO]).orElse("")).trim();
            if ("".equals(numeroRadicado)) {
                camposVacios += " Número Radicado,";
            }
            String respuetaIps = (Optional.ofNullable(camposArchivo[INDICE_COLUMNA_RESPUESTA_IPS]).orElse("")).trim();
            if ("".equals(respuetaIps)) {
                camposVacios += " Respuesta IPS";
            }
        }

        
        if (!"".equals(camposVacios)) {
            builderMensajeError.append(" - Los siguientes campos deben tener valor: ( ");
            builderMensajeError.append(camposVacios);
            builderMensajeError.append(" )");
            esValido = false;
        }

        return esValido;
    }

     private boolean validarDatoTipoEntero(String campoArchivo, StringBuilder builderMensajeError, String campo) {
         boolean esValido = true;
         campoArchivo  = campoArchivo != null ? campoArchivo.trim():"";
         try {
             Integer.parseInt(campoArchivo);
         } catch (NumberFormatException e) {
             builderMensajeError.append(" - Se presenta un error de tipo dato, el valor del campo ( ");
             builderMensajeError.append(campo);
             builderMensajeError.append(" )");
             builderMensajeError.append(" este debe ser de tipo entero.");
             builderMensajeError.append(" valor encontrado : ");
             builderMensajeError.append(campoArchivo);
             esValido = false;
         }
         return esValido;
    }

    private boolean asignarErroresParaSerMostrados(StringBuilder builderMensajeError, CmMarcacionMasivaIpsBean bean) {
        boolean esValido = true;
        if(builderMensajeError.length()>0){
            bean.getObjeto().setHayErroresEnMarcacion(true);
            bean.getObjeto().setMensajeError(builderMensajeError);
            esValido = false;
        }
        return esValido;
    }

    private void formarRegistroError(StringBuilder builderErrorPorFactura, int numeroLinea, StringBuilder builderMensajeError) {
        if (builderErrorPorFactura.length() > 0) {
            StringBuilder builderMensajeErrorEncabezado = new StringBuilder();
            builderMensajeErrorEncabezado.append("Se presentan errores en la línea : ");
            builderMensajeErrorEncabezado.append(numeroLinea);
            builderMensajeErrorEncabezado.append(" ( ");
            builderMensajeErrorEncabezado.append(builderErrorPorFactura);
            builderMensajeErrorEncabezado.append(" ) ");
            builderMensajeErrorEncabezado.append("\r\n");
            builderMensajeError.append(builderMensajeErrorEncabezado);
        }
    }
    
    private void listar(CmMarcacionMasivaIpsBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCmMarcacionMasivaIpsRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmMarcacionMasivaIpsRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void ver(CmMarcacionMasivaIpsBean bean) {
        try {
            bean.setObjeto(getCmMarcacionMasivaIpsRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private boolean guardarArchivoMarcacionIpsMasiva(CmMarcacionMasivaIpsBean bean) {
        boolean hayGuardado ;
        try {
            String nombreFinalArchivo = bean.getObjeto().getArchivo();
            String rutaCopiado = PropApl.getInstance().get(PropApl.CM_RUTA_MARCACION_MASIVA_IPS);

            if (rutaCopiado != null || !"".equals(rutaCopiado)) {
                File nuevoAr = new File(rutaCopiado, nombreFinalArchivo);

                try (FileOutputStream outputStream = new FileOutputStream(nuevoAr, false)) {
                    int read;
                    byte[] bytes = new byte[1024];
                    while ((read = bean.getObjeto().getContenidoArchivo().read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                }
            }
            hayGuardado = true;
        } catch (IOException e) {
            bean.addError(e.getMessage());
            hayGuardado = false;
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
            hayGuardado = false;
        }
        return hayGuardado;
    }
    
      private boolean guardarMarcacionIpsMasivaEnDB(CmMarcacionMasivaIpsBean bean) {
        boolean hayGuardado ;
        try {
            bean.getObjeto().setRuta(PropApl.getInstance().get(PropApl.CM_RUTA_MARCACION_MASIVA_IPS));
            bean.getObjeto().setEstado(CmMarcacionMasivaIps.ESTADO_MARCACION_EN_PROCESO);
            bean.getObjeto().setFechaHoraInicio(new Date());
            bean.getObjeto().setEmpresa(new Empresa(bean.getConexion().getEmpresa().getId()));
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId( getCmMarcacionMasivaIpsRemoto().insertar(bean.getObjeto()) );
            hayGuardado = true;
        } catch (IOException e) {
            bean.addError(e.getMessage());
            hayGuardado = false;
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
            hayGuardado = false;
        }
        return hayGuardado;
    }
      
      
     private boolean guardarMarcacionEnCmFactura(CmMarcacionMasivaIpsBean bean, Map<String, CmFactura>  cmFacturas) {
        boolean hayGuardado ;
        int facturasExitosas = 0;
        int facturaFallidas = 0;
        try {
            ParamConsulta parametro = new ParamConsulta();
            
            for (Map.Entry<String, CmFactura> entry : cmFacturas.entrySet()) {
                CmFactura cmFactura = entry.getValue();
                parametro.setParametroConsulta1(cmFactura.getId());
                parametro.setParametroConsulta2(cmFactura.isRespuestaIps());
                parametro.setParametroConsulta3(cmFactura.isRespuestaIps() ? new Date() : null);
                try {
                    getCmFacturaRemoto().actualizarMarcadoGlosaIPS(parametro);
                    facturasExitosas++;
                } catch (Exception e) {
                    facturaFallidas++;
                }
            }
            hayGuardado = true;
         } catch (Exception ex) {
            bean.addError(ex.getMessage());
            hayGuardado = false;
        }
        bean.getObjeto().setExitosos(facturasExitosas);
        bean.getObjeto().setEstado(CmMarcacionMasivaIps.ESTADO_MARCACION_PROCESADO);
        bean.getObjeto().setFallidos(facturaFallidas);
        
        return hayGuardado;
    }  
      
        
    private boolean actualizarMarcacionFinProcesoDB(CmMarcacionMasivaIpsBean bean) {
        boolean hayGuardado ;
        try {
            bean.getObjeto().setFechaHoraFin(new Date());
            bean.getObjeto().setEstado(CmMarcacionMasivaIps.ESTADO_MARCACION_PROCESADO);
            getCmMarcacionMasivaIpsRemoto().actualizarFinProceso(bean.getObjeto());
            hayGuardado = true;
        } catch (IOException e) {
            bean.addError(e.getMessage());
            hayGuardado = false;
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
            hayGuardado = false;
        }
        return hayGuardado;
    }

}
