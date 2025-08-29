/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.utilidades;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegValidacionDerecho;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.ValidacionDerechosRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jyperez
 */
public class ValidacionDerechosHilo extends Thread {
    
    public static final int REPORTE_AFILIADOS_NUEVOS_MS = 1;
    public static final int REPORTE_NOVEDADES_AFILIADO_NS = 2;
    public static final int REPORTE_SOLICITUD_TRASLADO_AFILIADOS_S1 = 3;
    public static final int REPORTE_PORTABILIDAD = 4;
    public static final int REPORTE_DIGITACION_USUARIOS = 5;
    public static final int REPORTE_ENCUESTAS_AFILIADOS = 6;
    public static final int REPORTE_NOVEDADES_ASEGURAMIENTO = 7;
    
    public final static int ESTADO_EN_PROCESO = 1;
    public final static int ESTADO_PROCESADO = 2;
    public final static int ESTADO_RECHAZADO = 3;
    
    private AsegValidacionDerecho validacionDerecho;
    private AsegValidacionDerecho validacionDerechoAux;
    // listas
    List<AsegValidacionDerecho> listavalidacionDerechos;
    List<String> listaAfiliadosValidacion;
    List<AsegAfiliado> listaAfiliados;
    //HashMap<Integer,Maestro> hashMaestros;
    
    
    public ValidacionDerechosHilo(AsegValidacionDerecho validacionDerecho) {
        this.validacionDerecho = validacionDerecho;
        this.listaAfiliados = new ArrayList<> ();

    }
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }
    
    private ValidacionDerechosRemoto getValidacionDerechosRemoto() throws Exception {
        return (ValidacionDerechosRemoto) RemotoEJB.getEJBRemoto(("ValidacionDerechosServicio"), ValidacionDerechosRemoto.class.getName());
    }
    
    @Override
    public void run() {
        try {
            //obtenemos las listas de consulta de maestros
            //hashMaestros = getMaestroRemoto().consultarHash();
            generarValidacionDerechos();
        } catch (Exception ex) {
        }
    }

    private void generarValidacionDerechos() {
        int registros = 0;
        int i = 0;
        AsegAfiliado afiliado = null;
        List<AsegAfiliado> listaAuxAfiliados = null;
        String[] campos;
        try {
            if (validacionDerecho != null) {
                // 1. recuperamos los registros del objeto
                    listaAfiliadosValidacion = validacionDerecho.getAfiliadosValidacion();
                // 2. buscamos cada uno de los registros a buscar, sea por contrato o por documento.
                // -- hacer funcion para obtener una lista de objetos por documento.
                if (validacionDerecho.getTipoValidacion() == AsegValidacionDerecho.TIPO_VALIDACION_CONTRATO ) {
                    for (String dato: listaAfiliadosValidacion) {
                        try {
                            afiliado= null;
                            afiliado = getAfiliadoRemoto().consultar(dato);
                        } catch (Exception ex) {
                            Logger.getLogger(ValidacionDerechosHilo.class.getName()).log(Level.SEVERE, "Error consultando un afiliado por contrato. Id " + dato, ex);
                        }
                        if (afiliado != null && afiliado.getId() != null && afiliado.getId() != 0) {
                            listaAfiliados.add(afiliado);
                        }
                        // validamos si el registro no ha sido abortado- cada 50 iteraciones
                        if (i++ == 50) {
                            validacionDerechoAux = getValidacionDerechosRemoto().consultar(validacionDerecho.getId());
                            if (validacionDerechoAux != null && validacionDerechoAux.getEstado() == AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_ABORTADO) {
                                break;
                            }
                            i = 1;
                        }
                    }
                } else {
                    //2.1 si la busqueda es por tipo y numero de documento
                    for (String dato: listaAfiliadosValidacion) {
                        try {
                            campos = dato.split(",",2);
                            listaAuxAfiliados = null;
                            listaAuxAfiliados = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(campos[0],campos[1]);
                            
                        } catch (Exception ex) {
                            Logger.getLogger(ValidacionDerechosHilo.class.getName()).log(Level.SEVERE, "Error consultando un afiliado por documento. Id " + dato, ex);
                        }
                        if (listaAuxAfiliados != null && !listaAuxAfiliados.isEmpty()) {
                            for (AsegAfiliado a: listaAuxAfiliados) {
                                listaAfiliados.add(a);
                            }
                        }
                        // validamos si el registro no ha sido abortado- cada 50 iteraciones
                        if (i++ == 50) {
                            validacionDerechoAux = getValidacionDerechosRemoto().consultar(validacionDerecho.getId());
                            if (validacionDerechoAux != null && validacionDerechoAux.getEstado() == AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_ABORTADO) {
                                break;
                            }
                            i = 1;
                        }
                    }
                }
                try {
                    // validamos si el registro no ha sido abortado
                    validacionDerechoAux = getValidacionDerechosRemoto().consultar(validacionDerecho.getId());
                } catch (Exception ex) {
                }
                //validamos nuevamente si el registro no ha sido abortado
                if (validacionDerechoAux != null && validacionDerechoAux.getEstado() != AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_ABORTADO) {
                    // 3. obtenemos la información y procedemos a realizar la creación del archivo de resultados.
                    registros = generarArchivoValidacionDerechos();
                }
                // validamos que el registro de validacion derechos no haya sido abortado
                try {
                    // validamos si el registro no ha sido abortado
                    validacionDerechoAux = getValidacionDerechosRemoto().consultar(validacionDerecho.getId());
                } catch (Exception ex) {
                }
                if (validacionDerechoAux != null && validacionDerechoAux.getEstado() != AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_ABORTADO) {
                    // 4 actualizamos los valores en el registro de validacion derechos
                    if (registros != 0) {
                        validacionDerecho.setEstado(AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_PROCESADO);
                        validacionDerecho.setRegistrosEncontrados(registros);
                        validacionDerecho.setFechaHoraFin(new Date());
                        //copiar auditoria similar al mismo objeto de edicion
                        validacionDerecho.setUsuarioModifica(validacionDerecho.getUsuarioCrea());
                        validacionDerecho.setFechaHoraModifica(validacionDerecho.getFechaHoraFin());
                        validacionDerecho.setTerminalModifica(validacionDerecho.getTerminalCrea());
                    } else {
                        // Error - Se rechaza el registro
                        validacionDerecho.setEstado(AfiliadoParametro.ESTADO_VALIDACION_DERECHOS_RECHAZADO);
                        validacionDerecho.setRegistrosEncontrados(registros);
                        validacionDerecho.setFechaHoraFin(new Date());
                        //copiar auditoria similar al mismo objeto de edicion
                        validacionDerecho.setUsuarioModifica(validacionDerecho.getUsuarioCrea());
                        validacionDerecho.setFechaHoraModifica(validacionDerecho.getFechaHoraFin());
                        validacionDerecho.setTerminalModifica(validacionDerecho.getTerminalCrea());
                    }
                    getValidacionDerechosRemoto().actualizar(validacionDerecho);
                    // 5 fin
                }
            }
        
        } catch (Exception ex) {
                Logger.getLogger(ValidacionDerechosHilo.class.getName()).log(Level.SEVERE, "[generarValidacionDerechos] Error acualizando AsegValidacionDerecho con Id " + validacionDerecho.getId(), ex);
        }
    }
    
    private int generarArchivoValidacionDerechos() {
        int cant = 0;
        File archivo;
        BufferedWriter bw= null;
        String encabezado;
        try {
            archivo = new File(validacionDerecho.getRuta(), validacionDerecho.getArchivo());
            // validamos que el archivo exista
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            bw = new BufferedWriter (new FileWriter(archivo));
            //generamos el encabezado
            bw.write("consecutivo,contrato_afiliado,serial_bdua,tipo_documento,numero_documento,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,sexo,fecha_afiliacion_eps,codigo_estado_afiliacion,estado_afiliacion,direccion_residencia,telefono_fijo,telefono_movil,email,departamento_afiliacion,municipio_afiliacion,departamento_residencia,municipio_residencia,zona,regimen,nivel_sisben,sub_grupo_sisben,categoria_ibc,puntaje_sisben,codigo_ips_primaria,codigo_grupo_poblacional,modelo_liquidacion,tiene_portabilidad,fecha_portabilidad,portabilidad_cnt_prestador_sedes_id,mae_etnia_valor,fecha_egreso_eps\n");
            for (AsegAfiliado reg: listaAfiliados) {
                String linea = "";
                // hacemos el tratamiento del dato, obteniendo incluso cada uno de los valores a consultar
                linea = reg.getId()+","+
                        reg.getIdAfiliado()+","+
                        transformarNuloEnVacio(reg.getSerialBdua())+","+
                        reg.getMaeTipoDocumentoCodigo()+","+//hashMaestros.get(reg.getMaeTipoDocumento()).getValor()+","+
                        reg.getNumeroDocumento()+","+
                        reg.getPrimerApellido()+","+
                        transformarNuloEnVacio(reg.getSegundoApellido())+","+
                        reg.getPrimerNombre()+","+
                        transformarNuloEnVacio(reg.getSegundoNombre())+","+
                        reg.getFechaNacimiento()+","+ // validar formato
                        reg.getMaeGeneroCodigo()+","+//2022-08-10 jyperez cambio de valores por código
                        reg.getFechaAfiliacionEps()+","+
                        reg.getMaeEstadoAfiliacionCodigo()+","+//hashMaestros.get(reg.getMaeEstadoAfiliacion()).getValor()+","+
                        reg.getMaeEstadoAfiliacionValor()+","+//hashMaestros.get(reg.getMaeEstadoAfiliacion()).getNombre()+","+
                        transformarNuloEnVacio(reg.getDireccionResidencia())+","+ 
                        transformarNuloEnVacio(reg.getTelefonoFijo())+","+
                        transformarNuloEnVacio(reg.getTelefonoMovil())+","+
                        transformarNuloEnVacio(reg.getEmail())+",";
                if (reg.getAfiliacionUbicacion() != null) {
                    if (reg.getAfiliacionUbicacion().getUbicacionPadre() != null) {
                        linea = linea + reg.getAfiliacionUbicacion().getUbicacionPadre().getPrefijo()+",";
                    } else {
                        linea = linea + ",";
                    }
                    linea = linea + reg.getAfiliacionUbicacion().getPrefijo()+",";
                }else{
                    linea = linea + ",,";
                }
               if (reg.getResidenciaUbicacion() != null) {
                    if (reg.getResidenciaUbicacion().getUbicacionPadre() != null) {
                        linea = linea + reg.getResidenciaUbicacion().getUbicacionPadre().getPrefijo()+",";
                    } else {
                        linea = linea + ",";
                    }   
                    linea = linea + reg.getResidenciaUbicacion().getPrefijo()+",";
                }else{
                    linea = linea + ",,";
                }
                //String transformarNuloEnVacio;
               linea = linea +
//                                      
                        reg.getMaeZonaCodigo()+","+//reg.getZona()+","+//2022-10-21 jyperez INC 1302 Cambiamos el valor por el código de zona
                        reg.getMaeRegimenValor()+","+//getRegimen(reg.getRegimen())+","+
                        transformarNuloEnVacio(reg.getMaeNivelSisbenCodigo())+",";//2022-08-10 jyperez cambio de valores por código
               
               if (reg.getMaeSubGrupoSisbenId() != null) {
                        linea = linea + transformarNuloEnVacio(reg.getMaeSubGrupoSisbenCodigo()) +",";//2022-08-10 jyperez cambio de valores por código 
                                        }else{
                    linea = linea + ",";
                }
                        linea = linea + transformarNuloEnVacio(reg.getMaeCategoriaIbcCodigo())+",";//2022-08-10 jyperez cambio de valores por código 
                        linea = linea + transformarNuloEnVacio(reg.getPuntajeSisben())+",";
                
                if (reg.getPrimariaPrestadorSede() != null) {
                    linea = linea + transformarNuloEnVacio(reg.getPrimariaPrestadorSede().getCodigoHabilitacionSede())+",";
                }else{
                    linea = linea + ",";
                }
                // 2020-10-23 jyperez INC 318 - se incluye campo faltante codigo_grupo_poblacional
                linea = linea + transformarNuloEnVacio(reg.getMaeGrupoPoblacionalCodigo())+","+//2022-08-10 jyperez cambio de valores por código 
                        reg.getMaeModeloLiquidacionValor()+",";//getModeloLiquidacion(reg.getModeloLiquidacion())+"\n";
                //2025-05-08 jyperez adicion nuevos campos
                linea = linea + transformarNuloEnVacio(reg.getTienePortabilidad())+",";
                linea = linea + transformarNuloEnVacio(reg.getFechaPortabilidad())+",";
                //portabilidad_cnt_prestador_sedes_id (debe salir el nombre del prestador),
                if (reg.getPortabilidadPrestadorSede() != null) {
                    linea = linea + reg.getPortabilidadPrestadorSede().getNombreSede()+",";
                } else {
                    linea = linea +",";
                }
                linea = linea + transformarNuloEnVacio(reg.getMaeEtniaValor())+",";
                linea = linea + transformarNuloEnVacio(reg.getFechaEgresoEps())+"\n";
                // imprimimos la linea en el archivo
                bw.write(linea);
                cant++;
            }
            bw.close();
        } catch (IOException ex) {
           // no se pdo crear el archivo
           //validacionDerecho.setObservacion("[generarArchivoReporte - " + getTipoReporte(tipoReporte) +"] Error creando el archivo " + validacionDerecho.getRuta() + validacionDerecho.getArchivo() + ". Mensaje : " + ex.getMessage());
           if ( bw != null ) {
               try {
                   bw.close();
               } catch (IOException ex1) {
                   
               }
           }
        } catch (Exception e) {
            //validacionDerecho.setObservacion("[generarArchivoReporte - " + getTipoReporte(tipoReporte) +"] Error creando el archivo " + validacionDerecho.getRuta() + validacionDerecho.getArchivo() + ". Mensaje : " + e.getMessage());
            if ( bw != null ) {
               try {
                   bw.close();
               } catch (IOException ex1) {
                   
               }
           }
        }
        return cant;
    }
    
    /**
     * Función para transformar un valor null de Texto en un valor de cadena vacia ""
     * @param texto
     * @return 
     */
    public String transformarNuloEnVacio(String texto) {
        if (texto == null) {
            return "";
        } else {
            return texto;
        }
    }
    
    /**
     * Función para transformar un valor Integer null de Texto en un valor de cadena vacia ""
     * @param texto
     * @return 
     */
    public String transformarNuloEnVacio(Integer numero) {
        if (numero == null) {
            return "";
        } else {
            return numero.toString();
        }
    }
    
    /**
     * Función para transformar un valor Integer null de Texto en un valor de cadena vacia ""
     * @param texto
     * @return 
     */
    public String transformarNuloEnVacio(BigInteger numero) {
        if (numero == null) {
            return "";
        } else {
            return numero.toString();
        }
    }
    
    /**
     * Función para transformar un valor Integer null de Texto en un valor de cadena vacia ""
     * @param texto
     * @return 
     */
    public String transformarNuloEnVacio(Double numero) {
        if (numero == null) {
            return "";
        } else {
            return numero.toString();
        }
    }
    
    /**
     * Función para transformar un valor null de Date en un valor de cadena vacia ""
     * @param fecha
     * @return 
     */
    public String transformarNuloEnVacio(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (fecha == null) {
            return "";
        } else {
            return sdf.format(fecha);
        }
    }
    
    /**
     * Función para transformar un valor null de Date en un valor de cadena vacia ""
     * @param fecha
     * @return 
     */
    public String transformarNuloEnVacio(Boolean dato) {
        if (dato == null) {
            return "";
        } else {
            return (dato) ? "1": "0";
        }
    }

    private String getGenero(String genero) {
        String mensaje = "";
        switch (genero) {
            case "1" :
                mensaje = "F";
            break;
            case "2":
                mensaje = "M";
            break;
            default:
                mensaje = "SD";
            break;
        }
        return mensaje;
    }

    private String getRegimen(String regimen) {
        String mensaje = "";
        switch (regimen) {
            case "1" :
                mensaje = "S";
            break;
            case "2":
                mensaje = "C";
            break;
            default:
                mensaje = "";
            break;
        }
        return mensaje;
    }

    private String getModeloLiquidacion(String modeloLiquidacion) {
        String mensaje = "";
        switch (modeloLiquidacion) {
            case "0" :
                mensaje = "Capita";
            break;
            case "1":
                mensaje = "Evento";
            break;
            default:
                mensaje = "";
            break;
        }
        return mensaje;
    }
}
