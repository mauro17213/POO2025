/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.cargas.servicio;

import com.saviasaludeps.savia.dominio.cargas.CarCargaRegistro;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoUsuario;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarCargaGestionRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarCargaRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarPeriodoRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarProcesoPrestadoresRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarProcesoUsuarioRemoto;
import com.saviasaludeps.savia.web.cargas.bean.CargaGestionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.web.cargas.bean.DTO.JsonDatoDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aguevara
 */
public class CargaGestionServicioImpl extends AccionesBO implements CargaGestionServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private CarCargaRemoto getCargaRemoto() throws Exception {
        return (CarCargaRemoto) RemotoEJB.getEJBRemoto("CarCargaServicio", CarCargaRemoto.class.getName());
    }

    private CarProcesoUsuarioRemoto getCarProcesoUsuarioRemoto() throws Exception {
        return (CarProcesoUsuarioRemoto) RemotoEJB.getEJBRemoto("CarProcesoUsuarioServicio", CarProcesoUsuarioRemoto.class.getName());
    }

    private CarPeriodoRemoto getCarPeriodoRemoto() throws Exception {
        return (CarPeriodoRemoto) RemotoEJB.getEJBRemoto("CarPeriodoServicio", CarPeriodoRemoto.class.getName());
    }

    private CarProcesoPrestadoresRemoto getCarProcesoPrestadoresRemoto() throws Exception {
        return (CarProcesoPrestadoresRemoto) RemotoEJB.getEJBRemoto("CarProcesoPrestadoresServicio", CarProcesoPrestadoresRemoto.class.getName());
    }

    private CarCargaGestionRemoto getCarCargaGestionRemoto() throws Exception {
        return (CarCargaGestionRemoto) RemotoEJB.getEJBRemoto("CarCargaGestionServicio", CarCargaGestionRemoto.class.getName());
    }

    @Override
    public void Accion(CargaGestionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    //crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    //guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    //modificarFila(bean);
                    break;
                case Url.ACCION_BORRAR:
                    //borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verCarga(bean);
                            break;

                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verFilaCarga(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarFilaCarga(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarFilaCarga(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargasInicial(CargaGestionBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   private void listar(CargaGestionBean bean) {
    try {
        // Consultar cantidad y registros
        bean.getParamConsulta().setCantidadRegistros(getCarCargaGestionRemoto().consultarCantidadLista(bean.getParamConsulta()));
        bean.setRegistros(getCarCargaGestionRemoto().consultarLista(bean.getParamConsulta()));

        // Obtener procesos únicos por usuario directamente desde la base de datos
        List<CarProceso> listaProcesos = getCarProcesoUsuarioRemoto()
            .listarProcesosUnicosPorUsuario(bean.getConexion().getUsuario().getId(), bean.getParamConsulta());
        bean.setListaProcesos(listaProcesos);

        // Obtener prestadores únicos directamente desde la base de datos
        List<CntPrestador> listaPrestadores = getCarCargaGestionRemoto()
            .listarPrestadoresUnicosPorRegistros(bean.getParamConsulta());
        bean.setListaPrestadores(listaPrestadores);

    } catch (Exception e) {
        bean.addError(e.getMessage());
    }
}



    private void ver(CargaGestionBean bean) {
        try {
            bean.setObjeto(getCarCargaGestionRemoto().consultar(bean.getObjeto().getId()));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void borrar(CargaGestionBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void verCarga(CargaGestionBean bean) {
        try {
            bean.setListaTipoCargas(getCarCargaGestionRemoto().consultarListaTipoCarga(bean.getObjeto().getTipo(), bean.getObjeto().getCarCargasId().getId()));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void editarFilaCarga(CargaGestionBean bean) {
        try {
            bean.setObjeto(getCarCargaGestionRemoto().consultar(bean.getObjeto().getId()));
            bean.setDatosFilaEditables(convertirJsonADto(bean.getObjeto().getJsonDatos()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verFilaCarga(CargaGestionBean bean) {
        try {
            bean.setObjeto(getCarCargaGestionRemoto().consultar(bean.getObjeto().getId()));
            bean.setDatosFilaEditables(convertirJsonADto(bean.getObjeto().getJsonDatos()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarFilaCarga(CargaGestionBean bean) {
        try {
            String mensajeError = "";
            String aux = "";
            //2025-06-17 jyperez realizamos la ejecución de la validación por regex para el campo en cuestión
            for(JsonDatoDTO dato: bean.getDatosFilaEditables() ) {
                aux = validarConExpresionRegular(dato.getValor(), dato.getExpresion());
                if(!aux.equals("")) {
                    mensajeError += aux + "con el tipo de Carga " + dato.getTipo() + ". \n";
                }
            }
            if (!mensajeError.equals("")) {
                bean.addError(mensajeError);
            }
            if (!bean.isError()) {
                String jsonDatos = convertirDtoAJson(bean.getDatosFilaEditables());
                bean.getObjeto().setJsonDatos(jsonDatos);
                bean.auditoriaModificar(bean.getObjeto());
                getCarCargaGestionRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public List<JsonDatoDTO> convertirJsonADto(String jsonDatos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonDatos, new TypeReference<List<JsonDatoDTO>>() {
        });
    }

    public String convertirDtoAJson(List<JsonDatoDTO> datos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(datos);
    }
    
    String validarConExpresionRegular(String texto, String expresion) {
        String mensaje = "";
        try{
            Pattern patron = Pattern.compile(expresion);
            Matcher matcher = patron.matcher(texto);
            if(!matcher.find()) {
                mensaje = "El valor " + texto +" no cumple ";
            }
        } catch (Exception ex) {
            mensaje = "Se presento un error en la validacion ";
        }
        return mensaje;
    }

}
