/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.reserva.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
 public class RtConstantes {
    
    public static final String TEXTO_VACIO = "";
    //Estado reserva  0.En Cola  - 1.Generando - 2.Generada - 3.Autorizada - 4.Rechazada - 5.Cancelada
    public static final int EN_COLA = 0;
    public static final int GENERANDO = 1;
    public static final int GENERADA = 2;
    public static final int AUTORIZADA = 3;
    public static final int RECHAZADA = 4;
    public static final int CANCELADA = 5;
    
    public static final String EN_COLA_STR = "En Cola";
    public static final String GENERANDO_STR = "Generando";
    public static final String GENERADA_STR = "Generado";
    public static final String AUTORIZADA_STR = "Autorizado";
    public static final String RECHAZADA_STR = "Rechazado";
    public static final String CANCELADA_STR = "Cancelado";
    public static final String FALLIDO_STR = "Fallido";
    
    //ESTADOS RESERVA ARCHIVO
    public static final int RT_ARCHIVO_PENDIENTE = 0;
    public static final int RT_ARCHIVO_GENERANDO = 1;
    public static final int RT_ARCHIVO_GENERADO = 2;
    public static final int RT_ARCHIVO_RECHAZADO = 3;
    
    public static final int RT_ARCHIVO_FALLIDO = 3;
    
    //ESTADOS RESERVA ARCHVO PROCESOS
    public static final String EN_PROCESO = "En Proceso";
    public static final String FINALIZADO_EXITO = "Finalizado exitoso";
    public static final String FINALIZADO_ERROR = "Finalizado error";
    
            
    //TIPO ARCHIVO RESERVAS
    public static final String TIPO1 = "Contratos Evento";
    public static final String TIPO2 = "Contratos NO PBS";
    public static final String TIPO3 = "Autorizaciones PBS";
    public static final String TIPO4 = "Autorizaciones NO PBS";
    public static final String TIPO5 = "Facturas no pagadas PBS";
    public static final String TIPO6 = "Facturas no pagadas NO PBS";
    public static final String TIPO7 = "Facturas pagadas PBS";
    public static final String TIPO8 = "Facturas pagadas NO PBS";
    public static final String TIPO9 = "Incapacidades";
    public static final String TIPO10 = "Contratos Capita PGP";
    
    
    
    public static HashMap<Integer, Maestro> obtenerHashMaestro(List<Maestro> listaMaestros) {
        HashMap<Integer, Maestro> hash = new HashMap();
        listaMaestros.forEach(maestro -> {
            hash.put(maestro.getId(), maestro);
        });
        return hash;
    }
    
    public static String getNombreEstadoReserva(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case EN_COLA:
                return EN_COLA_STR;
            case GENERANDO:
                return GENERANDO_STR;
            case GENERADA:
                return GENERADA_STR;
            case AUTORIZADA:
                return AUTORIZADA_STR;
            case RECHAZADA:
                return RECHAZADA_STR;
            case CANCELADA:
                return CANCELADA_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> getListaEstadosReserva() {
        List<Maestro> lista = new ArrayList<>();
        int contador = 0;
        while (contador < 6) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case EN_COLA:
                    maestro.setNombre(EN_COLA_STR);
                    maestro.setDescripcion(EN_COLA_STR);
                    lista.add(maestro);
                    break;
                case GENERANDO:
                    maestro.setNombre(GENERANDO_STR);
                    maestro.setDescripcion(GENERANDO_STR);
                    lista.add(maestro);
                    break;
                case GENERADA:
                    maestro.setNombre(GENERADA_STR);
                    maestro.setDescripcion(GENERADA_STR);
                    lista.add(maestro);
                    break;
                case AUTORIZADA:
                    maestro.setNombre(AUTORIZADA_STR);
                    maestro.setDescripcion(AUTORIZADA_STR);
                    lista.add(maestro);
                    break;
                case RECHAZADA:
                    maestro.setNombre(RECHAZADA_STR);
                    maestro.setDescripcion(RECHAZADA_STR);
                    lista.add(maestro);
                    break;
                case CANCELADA:
                    maestro.setNombre(CANCELADA_STR);
                    maestro.setDescripcion(CANCELADA_STR);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }
    
    public static String getNombreTipoArchivo(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case 1:
                return TIPO1;
            case 2:
                return TIPO2;
            case 3:
                return TIPO3;
            case 4:
                return TIPO4;
            case 5:
                return TIPO5;
            case 6:
                return TIPO6;
            case 7:
                return TIPO7;
            case 8:
                return TIPO8;
            case 9:
                return TIPO9;
            case 10:
                return TIPO10;
            default:
                return TEXTO_VACIO;
        }
    }

    public static String getNombreEstadoArchivoReserva(Integer idEstado) {
       if(idEstado== null){
            return TEXTO_VACIO;
        }
        switch (idEstado) {
            case 0:
                return EN_COLA_STR;
            case 1:
                return GENERANDO_STR;
            case 2:
                return GENERADA_STR;
            case 3:
                return FALLIDO_STR;
            case 4:
                return CANCELADA_STR;
            
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static String getNombreEstadoArchivoReservaProceso(Integer idEstado) {
        if(idEstado== null){
            return TEXTO_VACIO;
        }
        switch (idEstado) {
            case 0:
                return EN_PROCESO;
            case 1:
                return FINALIZADO_EXITO;
            case 2:
                return FINALIZADO_ERROR;
            default:
                return TEXTO_VACIO;
        }
    }
    
}
