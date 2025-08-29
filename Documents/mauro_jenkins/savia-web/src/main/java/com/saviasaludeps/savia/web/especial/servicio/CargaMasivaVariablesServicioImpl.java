/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoCargaVariable;
import com.saviasaludeps.savia.dominio.especial.PeCargaVariable;
import com.saviasaludeps.savia.dominio.especial.PeCertificadoCargaVariable;
import com.saviasaludeps.savia.dominio.especial.PeCierreCarga;
import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeTipoVariable;
import com.saviasaludeps.savia.dominio.especial.PeVariable;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.especial.PeCargaMasivaVariableRemoto;
import com.saviasaludeps.savia.negocio.especial.PeControlCierreVariablesRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeVariableRemoto;
import com.saviasaludeps.savia.web.especial.bean.CargaMasivaVariablesBean;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Fichero;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;

public class CargaMasivaVariablesServicioImpl extends AccionesBO implements CargaMasivaVariablesServicioIface {

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private PeCargaMasivaVariableRemoto getPeCargaMasivaVariableRemoto() throws Exception {
        return (PeCargaMasivaVariableRemoto) RemotoEJB.getEJBRemoto("PeCargaMasivaVariableServicio", PeCargaMasivaVariableRemoto.class.getName());
    }

    private PeVariableRemoto getPeVariableRemoto() throws Exception {
        return (PeVariableRemoto) RemotoEJB.getEJBRemoto("PeVariableServicio", PeVariableRemoto.class.getName());
    }

    private PeControlCierreVariablesRemoto getPeControlCierreVariableRemoto() throws Exception {
        return (PeControlCierreVariablesRemoto) RemotoEJB.getEJBRemoto("PeControlCierreVariableServicio", PeControlCierreVariablesRemoto.class.getName());
    }

    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }
    
        private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    @Override
    public void Accion(CargaMasivaVariablesBean bean) {
        if (super.ValidarSesion(bean)) {
            bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    this.listar(bean);
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    this.crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    this.guardar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    this.descargar(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    this.abortar(bean);
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarIps(bean);
                            break;
                        case Url.ACCION_CREAR:
                            this.generarCertificadoPorPeriodo(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarIps(bean);
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(CargaMasivaVariablesBean bean) {
        try {
            bean.setListaMeses(PeConstantes.listaMeses());
            bean.setListaPeriodoCarga(PeConstantes.listaPeriodoCargaMasivaVariable());
            bean.setListaEstadoCarga(PeConstantes.listaEstadoCargaMasiva());
            bean.setListaMesesCarga(PeConstantes.listaDosUltimosPeriodosCargaMasivaVariable());
            PeCargaVariable cargaVariable = new PeCargaVariable();
            cargaVariable.setPrograma(new PePrograma());
            bean.setObjeto(cargaVariable);
            bean.setProgramas(List.of(getPeProgramaRemoto().consultar(PeConstantes.ID_PROGRAMA_RIESGO_CARDIOVASCULAR)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public boolean verificarCierreCarga(CargaMasivaVariablesBean bean) {
        try {
            PeCierreCarga cierreConsultado = getPeControlCierreVariableRemoto()
                    .consultarIdProgramaPeriodo(bean.getObjeto().getPrograma().getId(), bean.getObjeto().getPeriodoCargue());

            if (cierreConsultado != null) {//si existe el cierre se verfica las fechas
                Date fechaCierre = cierreConsultado.getFechaHoraDesde();
                Date fechaApertura = cierreConsultado.getFechaHoraHasta();
                Date fechaCargue = new Date();

                if (fechaCargue.after(fechaCierre) && fechaCargue.before(fechaApertura)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            bean.addError(e.getMessage());
            return false;
        }
    }

    private void listar(CargaMasivaVariablesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPeCargaMasivaVariableRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPeCargaMasivaVariableRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(CargaMasivaVariablesBean bean) {
        PeCargaVariable cargaVariable = new PeCargaVariable();
        cargaVariable.setPrograma(new PePrograma());
        bean.setObjeto(cargaVariable);
    }

    private void guardar(CargaMasivaVariablesBean bean) {
        try {
            String extension;
            Date fecha = new Date();
            String ruta = PropApl.getInstance().get(PropApl.PE_RUTA_CARGA_MASIVA_VALORES_VARIABLES);
            if (!new File(ruta).exists()) {
                bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
            }
            // validamos si el archivo cargado es correcto
            this.validarArchivo(bean);

            if (!bean.isError()) {
                // actualizamos valores del objeto a guardar
                bean.getObjeto().setRuta(ruta);
                bean.getObjeto().setEstado(PeConstantes.ESTADO_EN_COLA);
                bean.getObjeto().setFechaHoraInicio(fecha);
                bean.getObjeto().setExiste(true);
                int indiceExtension = bean.getObjeto().getNombre().lastIndexOf(".");
                extension = bean.getObjeto().getNombre().substring(indiceExtension, bean.getObjeto().getNombre().length());
                bean.getObjeto().setArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_CARGA_MASIVA_VARIABLE, PeConstantes.formato6.format(fecha), extension));
                //se establecen los datos de archivo de respuesta
                bean.getObjeto().setRespArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_RESPUESTA_CARGA_MASIVA_VARIABLE, PeConstantes.formato6.format(fecha), extension));
                bean.getObjeto().setRespNombre(bean.getObjeto().getNombre());
                bean.getObjeto().setRespRuta(ruta);
                bean.getObjeto().setRespExiste(false);
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                bean.getObjeto().setId(getPeCargaMasivaVariableRemoto().insertar(bean.getObjeto()));
                //guardamos el archivo en la ruta
                generarArchivo(bean.getObjeto());
                bean.addMensaje("El archivo " + bean.getObjeto().getNombre() + " se cargó con exito, con número de radicado " + bean.getObjeto().getId());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void abortar(CargaMasivaVariablesBean bean) {
        try {
            bean.setObjeto(getPeCargaMasivaVariableRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(PeConstantes.ESTADO_CANCELADO);
            bean.getObjeto().setFechaHoraFin(new Date());
            getPeCargaMasivaVariableRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se abortó la carga masiva con éxito. Recuerde esperar unos minutos antes de descargar los resultados, debido a que hay procesos internos que están finalizando.");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void descargar(CargaMasivaVariablesBean bean) {
        try {
            bean.setObjeto(getPeCargaMasivaVariableRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void validarArchivo(CargaMasivaVariablesBean bean) throws Exception {
        int i = 0;
        StringBuilder mensaje = new StringBuilder("");
        int cantidadCamposLineaControl = this.obtenerTamColumnas(PeConstantes.ENCABEZADO_LINEA_CONTROL_CARGA_VARIABLES);
        int cantidadCamposDatos = this.obtenerTamColumnas(PeConstantes.ENCABEZADO_CARGA_VARIABLES);
        InputStream aux;
        BufferedReader br;
        InputStream copia;
        // variables para clonar el inputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        String linea;
        int cantidadCamposDatosArchivo = cantidadCamposDatos;
        Integer cantidadTotalRegistros = null;
        int cantidadLineasCargaVariable = 0;
        List<Integer> consecutivos = new ArrayList<>();
        List<PeAfiliadoCargaVariable> afiliados = new ArrayList<>();
        boolean procesado = true;//indica que el archivo fue procesado completamente

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

            while ((linea = br.readLine()) != null) {
                if (i == 0) {//Encabezado linea de control

                    if (!PeConstantes.ENCABEZADO_LINEA_CONTROL_CARGA_VARIABLES.equals(linea)) {
                        mensaje.append(" * La linea de control del archivo no se encontró o es erróneo o no tiene las columnas requeridas.").append(PeConstantes.SALTO_LINEA);
                    }
                } else if (i == 1) {//Datos linea de control

                    if (contarColumnas(linea, PeConstantes.PIPE) != cantidadCamposLineaControl) {
                        mensaje.append(" * linea ").append((i + 1)).append(": la cantidad de campos en la linea de control no corresponde al numero de campos permitidos (").append(cantidadCamposLineaControl + 1).append(") ").append(PeConstantes.SALTO_LINEA);
                    }

                    cantidadTotalRegistros = this.validacionDatosLineaControl(bean, linea, mensaje);
                    if (Objects.isNull(cantidadTotalRegistros)) {
                        mensaje.append(" * El valor cantidad_registros de la linea de control es requerido.").append(PeConstantes.SALTO_LINEA);
                    }

                } else if (i == 2) {//Encabezado carga variables

                    String encabezadoRequeridoLinea = this.obtenerEncabezadoRequerido(linea);
                    if (!encabezadoRequeridoLinea.equals(PeConstantes.ENCABEZADO_CARGA_VARIABLES)) {
                        mensaje.append(" * El encabezado del archivo no se encontró o es erróneo o no tiene las columnas requeridas.").append(PeConstantes.SALTO_LINEA);
                        procesado = false;
                        break;
                    }

                    if (linea.equals(PeConstantes.ENCABEZADO_CARGA_VARIABLES)) {
                        mensaje.append(" * El encabezado debe tener almenos un nombre de variable para agregar.").append(PeConstantes.SALTO_LINEA);
                        procesado = false;
                        break;
                    }

                    if (!this.validarVariables(bean, linea, mensaje)) {
                        procesado = false;
                        break;
                    }

                    cantidadCamposDatosArchivo = this.obtenerTamColumnas(linea);

                } else if (i > 2) {//Datos carga variables
                    cantidadLineasCargaVariable++;
                    boolean errorCantidadCamposDatosCarga = false;
                    if (contarColumnas(linea, PeConstantes.PIPE) != cantidadCamposDatosArchivo) {
                        mensaje.append(" * linea ").append((i + 1)).append(": la cantidad de campos no corresponde al numero de campos permitidos (").append(cantidadCamposDatosArchivo + 1).append(") ").append(PeConstantes.SALTO_LINEA);
                        errorCantidadCamposDatosCarga = true;
                    }

                    if (!errorCantidadCamposDatosCarga) {
                        this.validarConsecutivo(consecutivos, linea, i, mensaje);
                    }
                    afiliados.add(this.obtenerDatosAfiliadoCarga(linea));
                }
                i++;
            }

            this.validarDuplicidadAfiliados(afiliados, mensaje);

            //validamos la cantidad de registros indicada en la linea de control
            if (procesado && Objects.nonNull(cantidadTotalRegistros) && cantidadTotalRegistros != cantidadLineasCargaVariable) {
                mensaje.append(" * El valor cantidad_registros de la linea de control no coincide con la cantidad de lineas de datos cargadas.").append(PeConstantes.SALTO_LINEA);
            }

            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjeto().setRegistros(i - 3);//3 quitando la linea de control y el encabezado
            bean.getObjeto().setExitosos(0);
            bean.getObjeto().setFallidos(0);
            if (!mensaje.toString().isEmpty()) {
                bean.addError(mensaje.toString());
            }
        } catch (IOException e) {
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

    /**
     * Obtiene el tamaño de las columnas de un string a partir de un PIPE
     */
    private int obtenerTamColumnas(String linea) {
        return linea.split(String.format("\\%s", PeConstantes.PIPE)).length - 1;
    }

    /**
     * Obtiene los datos de un string a partir de un PIPE
     */
    private String[] obtenerDatos(String linea) {
        return linea.split(String.format("\\%s", PeConstantes.PIPE));
    }

    /**
     * Valida cada campo de la linea de control Retorna la cantidad de registros
     * indicada en la linea de control
     */
    private Integer validacionDatosLineaControl(CargaMasivaVariablesBean bean, String linea, StringBuilder mensaje) {
        String[] datosLineaControl = this.obtenerDatos(linea);
        Integer cantidadRegistros = null;
        Date fechaInicio = null;
        Date fechaFin;
        boolean fechaPeriodoValida = false;//indica si las fechas estan en un periodo valido

        for (int i = 0; i < datosLineaControl.length; i++) {
            if (i == 0) {
                this.validarCodigoPrestador(bean, datosLineaControl[i], mensaje);
            }

            if (i == 1) {
                fechaInicio = this.validarFormatoFecha(1, datosLineaControl[i], mensaje);
                fechaPeriodoValida = this.validarFechaPeriodo(1, fechaInicio, bean.getObjeto().getPeriodoCargue(), mensaje);
            }

            if (i == 2) {
                fechaFin = this.validarFormatoFecha(2, datosLineaControl[i], mensaje);
                boolean fechaPeriodoFinValida = this.validarFechaPeriodo(2, fechaFin, bean.getObjeto().getPeriodoCargue(), mensaje);
                fechaPeriodoValida = fechaPeriodoFinValida && fechaPeriodoValida;
                this.validarFechasLineaControl(fechaPeriodoValida, fechaInicio, fechaFin, mensaje);
            }

            if (i == 3) {
                cantidadRegistros = this.validarCantidadRegistros(datosLineaControl[i], mensaje);
            }

        }
        return cantidadRegistros;
    }

    private void validarCodigoPrestador(CargaMasivaVariablesBean bean, String codigo, StringBuilder mensaje) {
        try {
            if (codigo == null || codigo.isBlank()) {
                mensaje.append("* El codigo del prestador no esta presente en la linea de control.").append(PeConstantes.SALTO_LINEA);
                return;
            }
            if (!codigo.matches("^\\d+$")) {
                mensaje.append("* El codigo del prestador no puede tener espacios blancos.").append(PeConstantes.SALTO_LINEA);
                return;
            }
            CntPrestadorSede prestador = this.getPrestadoresRemoto().consultarSedePorCodigoHabilitacionActivo(codigo);
            if (Objects.isNull(prestador)) {
                mensaje.append("* El prestador indicado en linea de control no existe o esta inactivo.").append(PeConstantes.SALTO_LINEA);
                return;
            }

            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                Empresa empresa = bean.getConexion().getEmpresa();
                if (!prestador.getCodigoPrestador().equals(empresa.getCodigoHabilitacion())) {
                    mensaje.append("* El codigo del prestador indicado en la carga no coincide con el codigo del prestador logueado en el sistema.")
                            .append(PeConstantes.SALTO_LINEA);
                    return;
                }
            }
            bean.getObjeto().setPrestadorSede(prestador);

            boolean existeCargaEnColaOProceso = this.getPeCargaMasivaVariableRemoto().existeCargaEnColaOProcesoPorIdPrestador(prestador.getId());
            if (existeCargaEnColaOProceso) {
                mensaje.append("* Actualmente el prestador tiene una carga en  proceso favor validar mas tarde.").append(PeConstantes.SALTO_LINEA);
            }
        } catch (Exception ex) {
            Logger.getLogger(CargaMasivaVariablesServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Valida la fecha de inicio o fin en la linea de control (formato)
     */
    private Date validarFormatoFecha(int iteracion, String fecha, StringBuilder mensaje) {
        String campoValidacion = iteracion == 1 ? "fecha_inicio" : "fecha_fin";
        try {
            if (fecha == null || !fecha.matches(PeConstantes.FORMATO_FECHA)) {//necesario sino parsea correctamente fechas como 2025-02-01   2025-02-28 en el mismo string (parsea solo fecha inicial)
                mensaje.append(String.format("* El valor %s de la línea de control no cumple con el formato de fecha yyyy-MM-dd.", campoValidacion))
                        .append(PeConstantes.SALTO_LINEA);
                return null;
            }

            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            formateador.setLenient(false);
            return formateador.parse(fecha);
        } catch (ParseException ex) {
            mensaje.append(String.format("* El valor %s de la linea de control no cumple con el formato de fecha yyyy-MM-dd o no es una fecha valida.", campoValidacion)).append(PeConstantes.SALTO_LINEA);
            return null;
        }
    }

    /**
     * Valida la fecha de inicio o fin en la linea de control
     */
    private boolean validarFechaPeriodo(int iteracion, Date fecha, int periodoCargue, StringBuilder mensaje) {
        if (fecha == null) {
            return false;
        }
        String campoValidacion = iteracion == 1 ? "fecha_inicio" : "fecha_fin";
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int periodoExtraido = calendario.get(Calendar.MONTH) + 1;//Calendar entrega los meses con el formato 0 - 11

        if (periodoCargue != periodoExtraido) {//la fecha no cumple con el periodo
            mensaje.append(String.format("* La %s no se encuentra dentro del periodo seleccionado.", campoValidacion)).append(PeConstantes.SALTO_LINEA);
            return false;
        }

        int anioExtraido = calendario.get(Calendar.YEAR);
        Calendar calendarioActual = Calendar.getInstance();
        calendarioActual.setTime(new Date());
        int anioActual = calendarioActual.get(Calendar.YEAR);
        int mesActual = calendarioActual.get(Calendar.MONTH);

        if (mesActual == Calendar.JANUARY) {
            int anioAnterior = anioActual - 1;
            if (anioExtraido != anioAnterior) {//la fecha no cumple con el año anterior (SOLO ENERO)
                mensaje.append(String.format("* Por favor validar el año de %s.", campoValidacion)).append(PeConstantes.SALTO_LINEA);
                return false;
            }
            return true;
        } else if (anioExtraido != anioActual) {//la fecha no cumple con el año actual
            mensaje.append(String.format("* Por favor validar el año de %s.", campoValidacion)).append(PeConstantes.SALTO_LINEA);
            return false;
        }

        if ((iteracion == 1 && calendario.get(Calendar.DAY_OF_MONTH) != 1)
                || (iteracion == 2 && calendario.get(Calendar.DAY_OF_MONTH) != calendario.getActualMaximum(Calendar.DAY_OF_MONTH))) {
            mensaje.append(String.format("* Por favor validar %s debe ser el %s del mes.",
                    campoValidacion,
                    iteracion == 1 ? "primer día" : "último día"))
                    .append(PeConstantes.SALTO_LINEA);
            return false;
        }
        return true;
    }

    private void validarFechasLineaControl(boolean fechasValidas, Date fechaInicio, Date fechaFin, StringBuilder mensaje) {
        if (!fechasValidas || fechaInicio == null || fechaFin == null) {
            return;
        }

        if (!fechaInicio.before(fechaFin)) {
            mensaje.append(String.format("* La fecha_inicio debe ser menor a la fecha_fin.")).append(PeConstantes.SALTO_LINEA);
        }
    }

    private Integer validarCantidadRegistros(String cantidadCampos, StringBuilder mensaje) {
        try {
            return Integer.valueOf(cantidadCampos);
        } catch (NumberFormatException e) {
            mensaje.append("* El valor cantidad_registros de la linea de control no es un valor entero.").append(PeConstantes.SALTO_LINEA);
            return -1;
        }
    }

    private void validarConsecutivo(List<Integer> consecutivos, String line, int i, StringBuilder mensaje) {
        String[] datosCarga = this.obtenerDatos(line);
        String dato = datosCarga[0];
        int consecutivo;

        if (i == 3) { //i -> 3 inicio de linea de carga de datos
            consecutivo = this.convertirDatoEntero(dato);
            if (consecutivo != 1) {//valido que sea entero y que inicie en 1
                mensaje.append("* linea ").append((i + 1)).append(": El consecutivo debe ser entero e iniciar en 1.").append(PeConstantes.SALTO_LINEA);
            } else {
                consecutivos.add(consecutivo);
            }
        } else {

            consecutivo = this.convertirDatoEntero(dato);
            if (consecutivo == -1) {//valido que sea entero
                mensaje.append("* linea ").append((i + 1)).append(": El consecutivo debe ser entero.").append(PeConstantes.SALTO_LINEA);
            } else {
                if (consecutivos.isEmpty()) {
                    return;
                }
                Integer ultimoConsecutivo = consecutivos.get(consecutivos.size() - 1);
                int consecutivoRequerido = ultimoConsecutivo + 1;

                if (consecutivo != consecutivoRequerido) {
                    mensaje.append("* linea ").append((i + 1)).append(": El valor no es un consecutivo de la linea anterior.").append(PeConstantes.SALTO_LINEA);
                } else {

                    if (consecutivos.contains(consecutivo)) {
                        mensaje.append("* linea ").append((i + 1)).append(": El consecutivo debe ser único, no puede estar repetido.").append(PeConstantes.SALTO_LINEA);
                    } else {
                        consecutivos.add(consecutivo);
                    }
                }
            }
        }

    }

    /**
     * Valida la duplicidad de la variable y que exista o no este inactiva
     */
    private boolean validarVariables(CargaMasivaVariablesBean bean, String linea, StringBuilder mensaje) throws Exception {
        boolean validacion = true;
        String[] encabezadoCarga = this.obtenerDatos(linea);
        List<String> nombresVariables = new ArrayList<>();

        for (int i = 0; i < encabezadoCarga.length; i++) {
            if (i >= 4) {//posicion donde inician los nombres de las variables
                if (encabezadoCarga[i].matches(PeConstantes.PATRON_NOMBRE_VARIABLE)) {
                    nombresVariables.add(encabezadoCarga[i]);
                } else {
                    validacion = false;
                    mensaje.append(String.format("* La variable %s no es valida ya que no cumple con el patron estandar.", encabezadoCarga[i])).append(PeConstantes.SALTO_LINEA);
                }
            }
        }

        long conteo = nombresVariables.stream().distinct().count();
        if (conteo < nombresVariables.size()) {//validacion duplicidad
            validacion = false;
            mensaje.append("* El encabezado no puede tener duplicidad de variables para procesar.").append(PeConstantes.SALTO_LINEA);
        }

        nombresVariables = nombresVariables.stream().distinct().collect(Collectors.toList());

        for (String nombre : nombresVariables) {//validacion existencia y que este activa
            PeVariable variableConsultada = getPeVariableRemoto().consultarPorIdProgramaNombre(bean.getObjeto().getPrograma().getId(), nombre);
            if (Objects.isNull(variableConsultada) || !variableConsultada.isActiva()) {
                validacion = false;
                mensaje.append(String.format("* La variable %s no existe o esta inactiva.", nombre)).append(PeConstantes.SALTO_LINEA);
            }
            if (Objects.nonNull(variableConsultada) && variableConsultada.getTipo().equals(PeTipoVariable.CALCULO.getId())) {
                validacion = false;
                mensaje.append(String.format("* La variable %s no es valida ya que es tipo CALCULO.", nombre)).append(PeConstantes.SALTO_LINEA);
            }
        }
        if (!validacion) {
            return validacion;
        }
        return this.validarVariablesObligatorias(bean, nombresVariables, mensaje);
    }

    private int convertirDatoEntero(String dato) {
        try {
            return Integer.parseInt(dato);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean generarArchivo(PeCargaVariable objeto) throws Exception {
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

    /**
     * Devuelve el encabezado requerido. valida si no termina en | o si existe
     * el ultimo campo de acuerdo a la constante
     */
    private String obtenerEncabezadoRequerido(String linea) {
        if (linea == null || linea.isEmpty() || linea.endsWith(PeConstantes.PIPE)) {
            return "";
        }

        String[] encabezado = this.obtenerDatos(PeConstantes.ENCABEZADO_CARGA_VARIABLES);
        String ultimoCampo = encabezado[encabezado.length - 1];
        int posicion = linea.indexOf(ultimoCampo);

        if (posicion == -1) {//no existe en la linea el campo requerido
            return "";
        }

        String resultado = linea.substring(0, posicion) + ultimoCampo;

        int posicionDespuesCampo = posicion + ultimoCampo.length();
        if (posicionDespuesCampo < linea.length()) {
            String lineaVariables = linea.substring(posicionDespuesCampo);
            String lineaVariablesTrim = linea.substring(posicionDespuesCampo).trim();
            if (!lineaVariables.equals(lineaVariablesTrim)) {
                // hay caracteres después del último campo requerido y de la ultima variable
                return "";
            }
        }
        return resultado;
    }

    private String obtenerAnioCargueCertificado(Date fechaHoraCreacion, Integer periodoCargue) {
        Calendar calendarioFechaHoraCreacion = Calendar.getInstance();
        calendarioFechaHoraCreacion.setTime(fechaHoraCreacion);
        int anioCreacion = calendarioFechaHoraCreacion.get(Calendar.YEAR);
        periodoCargue = periodoCargue - 1;
        if (periodoCargue.equals(Calendar.DECEMBER)) {
            anioCreacion = anioCreacion - 1;
        }
        return String.valueOf(anioCreacion);
    }

   private void generarCertificadoPorPeriodo(CargaMasivaVariablesBean bean) {
    PeCertificadoCargaVariable certificado = new PeCertificadoCargaVariable();
    List<PeCertificadoCargaVariable> listaCertificado = new ArrayList<>();
    Date fechaActual = new Date();
    SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm a", Locale.US);
    List<PeCargaVariable> cargasPeriodo = new ArrayList<>();

    Integer periodoId = 0;
    Integer idEmpresa = 0;

    try {
        bean.setPrestadorSelec(bean.getObjetoIps().getCntPrestadorSedesId().getCntPrestador());
        periodoId = bean.getObjeto().getPeriodoCargue();
        cargasPeriodo = getPeCargaMasivaVariableRemoto().consultarCargasPorPeriodoSede(
            periodoId, bean.getObjetoIps().getCntPrestadorSedesId().getId());

        if (cargasPeriodo == null || cargasPeriodo.isEmpty()) {
            bean.addError("No se encontraron cargas para el periodo seleccionado.");
            bean.marcarErrorCertificado();
            return;
        }

        // Tomar datos generales de la primera carga
        PeCargaVariable cargaEjemplo = cargasPeriodo.get(0);
        CntPrestadorSede prestadorSede = cargaEjemplo.getPrestadorSede();
        if (prestadorSede == null) {
            bean.addError("No se encontró información de la sede del prestador.");
            bean.marcarErrorCertificado();
            return;
        }

        Ubicacion ubicacion = getUbicacionRemoto().consultar(prestadorSede.getUbicacionId());
        if (ubicacion == null) {
            bean.addError("No se encontró la ubicación del prestador.");
            bean.marcarErrorCertificado();
            return;
        }

        // Sumar exitosos de todas las cargas del periodo
        int totalExitosos = cargasPeriodo.stream()
                .mapToInt(PeCargaVariable::getExitosos)
                .sum();

        // ⚠️ Aquí se aplica el filtro para no incluir IDs con 0 exitosos
        String idsCargas = cargasPeriodo.stream()
                .filter(c -> c.getExitosos() > 0)
                .map(c -> String.valueOf(c.getId()))
                .collect(Collectors.joining(","));

        // Llenar datos del certificado
        certificado.setStrId(idsCargas);
        certificado.setStrNitSede(prestadorSede.getCntPrestador().getNumeroDocumento() != null
                ? prestadorSede.getCntPrestador().getNumeroDocumento() : "");
        certificado.setStrNombreInstitucion(prestadorSede.getNombreSede());
        certificado.setStrMunicipio(ubicacion.getNombre());
        certificado.setStrNombreArchivo(cargaEjemplo.getNombre());
        certificado.setStrRegistros(String.valueOf(totalExitosos));
        certificado.setStrPeriodo(String.valueOf(periodoId));
        certificado.setStrAnio(this.obtenerAnioCargueCertificado(cargaEjemplo.getFechaHoraCrea(), cargaEjemplo.getPeriodoCargue()));
        certificado.setStrFechaCargue(PeConstantes.formato1.format(cargaEjemplo.getFechaHoraCrea()));
        certificado.setStrHoraCargue(sdfHora.format(cargaEjemplo.getFechaHoraCrea()));
        certificado.setStrFechaGeneracion(PeConstantes.formato1.format(fechaActual));
        certificado.setStrHoraGeneracion(sdfHora.format(fechaActual));
        certificado.setStrCumplimiento(this.establecerCumplimiento(cargaEjemplo));

        listaCertificado.add(certificado);

        // Generar PDF con Jasper
        InputStream is = getClass().getResourceAsStream("/reportes/certificado_carga_variables.jasper");
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaCertificado);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));

        byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
        InputStream streamArchivo = new ByteArrayInputStream(bytes);
        bean.setAdjuntoStream(streamArchivo);
        bean.setObjeto(cargaEjemplo);

    } catch (Exception e) {
        bean.addError("Error inesperado al generar el certificado.");
    }
}



    private String establecerCumplimiento(PeCargaVariable carga) {
        Date fechaHoraCrea = carga.getFechaHoraCrea();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaHoraCrea);
        int diaDelMes = calendario.get(Calendar.DAY_OF_MONTH);
        if (diaDelMes > 10) {
            return "EXTEMPORÁNEO";
        }
        return "OPORTUNO";
    }

    /**
     * metodo auxiliar para obtener los datos basicos de un afiliado a partir de
     * una linea del archivo de cargue
     *
     * @param numeroLinea
     * @param linea
     * @return
     */
    private PeAfiliadoCargaVariable obtenerDatosAfiliadoCarga(
            String linea) {
        String[] datosAfiliado = this.obtenerDatos(linea);
        return new PeAfiliadoCargaVariable(datosAfiliado[1], datosAfiliado[2]);
    }

    /**
     * Valida duplicicidad de afiliados a partir del tipo de documento y la
     * identificacion
     *
     * @param afiliados
     * @param mensaje
     */
    private void validarDuplicidadAfiliados(List<PeAfiliadoCargaVariable> afiliados, StringBuilder mensaje) {
        Map<String, List<PeAfiliadoCargaVariable>> afiliadosAgrupados = afiliados
                .stream()
                .collect(Collectors.groupingBy(a -> a.getTipoDocumento() + "-" + a.getIdentificacion()));

        Map<String, List<PeAfiliadoCargaVariable>> duplicados = afiliadosAgrupados.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (!duplicados.isEmpty()) {
            mensaje.append(" \n * El archivo que intenta cargar tiene usuarios duplicados:\n ");

            String duplicadosStr = duplicados.keySet().stream()
                    .map(key -> {
                        String[] partes = key.split("-");
                        return "tipo: " + partes[0] + " documento: " + partes[1];
                    })
                    .collect(Collectors.joining(", "));

            mensaje.append(duplicadosStr);
        }
    }

    /**
     * Verifica listado de variables obligatorias que deben de estar contenidas
     * en la estructura de la carga
     *
     * @param bean
     * @param nombresVariablesCarga
     * @param mensaje
     * @return
     */
    private boolean validarVariablesObligatorias(CargaMasivaVariablesBean bean, List<String> nombresVariablesCarga, StringBuilder mensaje) throws Exception {
        List<String> nombresObligatorios = this.getPeVariableRemoto()
                .consultarListadoVariablesObligatoriasPorIdPrograma(bean.getObjeto().getPrograma().getId());

        nombresObligatorios.removeIf(variableObligatoria -> nombresVariablesCarga.contains(variableObligatoria));
        if (!nombresObligatorios.isEmpty()) {
            mensaje.append(String.format("* El archivo que intenta cargar no contiene todas las variables obligatorias. Faltan: %s. ", nombresObligatorios)).append(PeConstantes.SALTO_LINEA);
            return false;
        }
        return true;
    }
    
    
    private void listarIps(CargaMasivaVariablesBean bean) {
    try {
      

        if (bean.getConexion().getEmpresa().getId() > 1) {
            bean.getParamConsulta(0).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
        }

        bean.getParamConsulta(0).getFiltros().put("cntContratosId.activo", true);
        bean.getParamConsulta(0).getFiltros().put("fecha", Util.fechaDateAString(new Date()));

        bean.getParamConsulta(0).setCantidadRegistros(
            getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(0)));

        bean.setListaIps(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(0)));

    } catch (Exception e) {
        bean.addError("Error al listar IPS: " + e.getMessage());
    }
}
    
    private void guardarIps(CargaMasivaVariablesBean bean) {
        try {
            bean.setObjetoIps(new PeIpsPrograma());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
